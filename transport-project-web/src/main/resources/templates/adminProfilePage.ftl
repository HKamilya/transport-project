<#ftl encoding="UTF-8"/>
<#import "header.ftl" as base>
<#import  "spring.ftl" as spring/>

<@base.main>
    <h1>ADMIN'S PROFILE</h1>
    <div class="col-12" style="width: 1em"></div>
    <div class="container">
        <div class="row">
            <div class="col-2">
                <img src="/img/user.png" style="width: 100%">
            </div>
            <div class="col-1"></div>
            <div class="col-2">
                ${username}
            </div>
        </div>
        <div class="col-12" style="min-height: 3em">
        </div>
    </div>
    <div class="row">

    </div>
    <a href="/admin/admins">add new admin</a>
    <a href="/admin/flights/get">change flights</a>
    <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#modal">
        delete profile
    </button>









    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="/admin/adminProfile/delete">
                    <input type="hidden" value="${id}" name="id">
                    <div class="modal-body">
                        <p>are you sure you want to leave us? :(</p>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-warning">yes, i'm sure</button>
                        <button type="reset" class="btn btn-success" data-dismiss="modal">
                            no, i changed my mind
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@base.main>