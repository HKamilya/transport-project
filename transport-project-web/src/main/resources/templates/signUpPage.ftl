<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <div class="col-12" style="min-height: 3em">

    </div>
    <div class="container">
        <div class="card text-white bg-dark mb-3" style="max-width: available;">
            <div class="card-body">
                <#if existErr??>
                    <p class="text-danger">
                        ${existErr}
                    </p>
                </#if>
                <form method="POST" action="/signUp">
                    <div class="row justify-content-md-center">
                        <div class="col-6">
                            <div class="form-group">
                                <label for="exampleInputUsername">Username</label>
                                <input type="text" name="username" class="form-control" id="exampleInputUsername"
                                       placeholder="Username" value="<#if userForm??>${userForm.username}</#if>">
                                <#if usernameError??>
                                    <div class="invalid-feedback">
                                        ${usernameError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                        <div class=" col-6">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input type="email" class="form-control ${(emailError??)?string('is-invalid','')}"
                                       name="email" id="exampleInputEmail1"
                                       aria-describedby="emailHelp" placeholder="Enter email"
                                       value="<#if userForm??>${userForm.email}</#if>">
                                <#if emailError??>
                                    <div class="invalid-feedback">
                                        ${emailError}
                                    </div>
                                </#if>
                                <small id="emailHelp" class="form-text text-muted">We'll never share your email with
                                    anyone
                                    else.</small>
                            </div>
                        </div>
                    </div>
                    <div class="row justify-content-md-center">
                        <div class="col-6">
                            <div class="form-group">
                                <label for="exampleInputFirstname">Firstname</label>
                                <input type="text" name="firstname" class="form-control" id="exampleInputFirstname"
                                       placeholder="Firstname">
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-group">
                                <label for="exampleInputLastname">Lastname</label>
                                <input type="text" name="lastname" class="form-control" id="exampleInputLastname"
                                       placeholder="Lastname">
                            </div>
                        </div>
                    </div>
                    <div class="row justify-content-md-center">
                        <div class="col-6">
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" name="password"
                                       class="form-control ${(passwordError??)?string('is-invalid','')}"
                                       id="password"
                                       placeholder="Password">
                                <label for="password"
                                       style="color: #d33682;width: 100%;margin-top: 0.25rem;font-size: 80%;">
                                    <#if passwordMatchError??>
                                        ${passwordMatchError}
                                    </#if>
                                </label>
                                <#if passwordError??>
                                    <div class="invalid-feedback">
                                        ${passwordError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-group">
                                <label for="exampleInputPhone">Phone</label>
                                <input type="number" name="phoneNumber" class="form-control" id="exampleInputPhone"
                                       placeholder="Phone" value="<#if userForm??>${userForm.phoneNumber}</#if>">
                            </div>
                        </div>
                    </div>
                    <div class=" row justify-content-md-center">
                        <div class="col-6">
                            <div class="form-group">
                                <label for="password2">Confirm Password</label>
                                <input type="password" name="confirmPassword"
                                       class="form-control ${(confirmPasswordError??)?string('is-invalid','')}"
                                       id="confirmPassword"
                                       placeholder="Password">
                                <#if confirmPasswordError??>
                                    <div class="invalid-feedback">
                                        ${confirmPasswordError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-group">
                                <label for="exampleInputDate">Date of birth</label>
                                <input type="date" name="dateOfBirth" class="form-control" id="exampleInputDate"
                                       placeholder="Date of birth"
                                       value="<#if userForm??>${userForm.dateOfBirth}</#if>">
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-outline-success">signUp</button>
                </form>
            </div>
        </div>
    </div>
</@base.main>
