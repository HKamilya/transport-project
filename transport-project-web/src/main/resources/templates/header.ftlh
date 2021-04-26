<#import  "spring.ftl" as spring/>
<#macro main css=[]>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>alpha flight</title>
        <link rel="stylesheet" href="/css/bootstrap.css">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">ALPHA FLIGHT</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02"
                aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor02">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item"><#if username??>
                        <a class="nav-link" href="/profile">Profile</a>
                    <#else >
                        <a class="nav-link" href="/signIn">SignIn</a>
                    </#if>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/signUp">SignUp</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/search">Search</a>
                </li>
            </ul>
        </div>
    </nav>
    <#nested>
    </body>
    </html>
</#macro>