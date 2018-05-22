<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group">
        <label>Login</label>
        <input type="text" name="username" class="form-control" placeholder="Enter username">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" name="password" class="form-control"placeholder="Password">
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" class="btn btn-primary"><#if isRegisterForm>Sing In<#else>Log In</#if></button>
    <#if !isRegisterForm><a href="/registration">Sing In</a> </#if>
</form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Log Out</button>
    </form>
</#macro>