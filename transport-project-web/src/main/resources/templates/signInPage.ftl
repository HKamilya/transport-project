<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>
<#import  "spring.ftl" as spring/>

<@base.main>

    <div class="col-12" style="min-height: 3em">

    </div>
    <div class="container">
        <div class="row justify-content-center">
            <div class="card text-white bg-dark mb-3" style="max-width: 20em;">
                <div class="card-body">
                    <form method="POST">
                        <div class="form-group">
                            <label for="exampleInputUsername">Username</label>
                            <input type="text" name="username" class="form-control" id="exampleInputUsername"
                                   placeholder="Username">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" name="password" class="form-control" id="exampleInputPassword1"
                                   placeholder="Password">
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="remember-me" type="checkbox">
                                Remember me
                            </label>
                        </div>
                        <button type="submit" class="btn btn-outline-success">signIn</button>
                    </form>
                    <br>
                    <a class="nav-link" href="/oauth2/authorization/google">signIn with google</a>
                </div>
            </div>
        </div>
    </div>
    </div>
</@base.main>