package ru.kpfu.itis.transportprojectweb.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ru.kpfu.itis.transportprojectweb.security.jwt.JwtFilter;
import ru.kpfu.itis.transportprojectweb.security.oauth.CustomOAuth2UserService;
import ru.kpfu.itis.transportprojectweb.security.oauth.OAuth2LoginSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@EnableWebSecurity
public class GlobalSecurityConfiguration {

    @Order(2)
    @Configuration
    public static class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private JwtFilter jwtFilter;

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/flights").permitAll()
                    .antMatchers("/api/cities/**").permitAll()
                    .antMatchers("/api/auth/login").permitAll()
                    .antMatchers("/api/auth/refreshToken").permitAll()
                    .antMatchers("/api/reservations/**").authenticated()
                    .antMatchers("/api/search").permitAll()
                    .antMatchers("/api/test/admin").hasAuthority("ADMIN")
                    .antMatchers("/api/test/user").hasAuthority("USER")
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        }

    }

    @Order(1)
    @Configuration
    public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        @Qualifier("customUserDetailService")
        private UserDetailsService userDetailsService;
        @Autowired
        private DataSource dataSource;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.authorizeRequests()
                    .antMatchers("/signUp").permitAll()
                    .antMatchers("/flights/**").permitAll()
                    .antMatchers("/reserve").authenticated()
                    .antMatchers("/signIn").permitAll()
                    .antMatchers("/profile").authenticated()
                    .antMatchers("/search").permitAll()
                    .antMatchers("/admin").permitAll()
                    .antMatchers("/flights").permitAll()
                    .antMatchers("/admin/addAdmin").hasAuthority("ROLE_ADMIN")
                    .antMatchers("/admin/adminProfile").hasAuthority("ROLE_ADMIN")
                    .antMatchers("/admin/addFlight").hasAuthority("ROLE_ADMIN")
                    .antMatchers("/admin/addAdmin").hasAuthority("ROLE_ADMIN")
                    .antMatchers("/admin/flights/**").hasAuthority("ROLE_ADMIN")
                    .antMatchers("/admin/admins").hasAuthority("ROLE_ADMIN")
                    .antMatchers("/oauth2/**").permitAll()
                    .antMatchers("/schedule/**").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            System.out.println(authentication.getAuthorities());
                            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                                httpServletResponse.sendRedirect("/admin/adminProfile");
                            }
                            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                                httpServletResponse.sendRedirect("/profile");
                            }
                        }
                    })
                    .failureUrl("/signIn?error")
                    .and()
                    .oauth2Login()
                    .loginPage("/signIn")
                    .userInfoEndpoint().userService(oAuth2UserService)
                    .and()
                    .successHandler(oAuth2LoginSuccessHandler)
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .and()
                    .rememberMe()
                    .rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository());
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

        @Bean
        public PersistentTokenRepository persistentTokenRepository() {
            JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
            jdbcTokenRepository.setDataSource(dataSource);
            return jdbcTokenRepository;
        }

        @Autowired
        private CustomOAuth2UserService oAuth2UserService;

        @Autowired
        private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    }
}
