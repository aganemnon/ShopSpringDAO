<#import "../parts/common.ftl" as c>


<@c.page>
    <h5>Edit User</h5>
<form method="post" action="/userlist">
    <div class="form-group">
        <label>Username</label>
        <input type="text" class="form-control" name="username" value="${user.userName}">
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" class="form-control" name="password" placeholder="Введите новый пароль или оставте пустым">
    </div>
    <div class="form-group">
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" value="ROLE_ADMIN" <#if role == "ROLE_ADMIN">checked<#else ></#if>>
            <label class="form-check-label">Admin</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" value="ROLE_USER" <#if role == "ROLE_USER">checked<#else ></#if>>
            <label class="form-check-label">User</label>
        </div>
    </div>
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <input type="hidden" value="${user.userId}" name="userId">
    <button type="submit" class="btn btn-primary">Save User</button>
</form>
</@c.page>