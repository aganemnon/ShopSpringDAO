<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <#if RequestParameters.error??>
    <div class="alert alert-warning" role="alert">
        Логин или пароль введены неверно!!
    </div>
    </#if>
    <div class="form-group">
        <label>Login</label>
        <input type="text" name="username" class="form-control" placeholder="Enter username">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" name="password" class="form-control"placeholder="Password">
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" class="btn btn-primary"><#if isRegisterForm>Registration<#else>Log In</#if></button>
    <#if !isRegisterForm><a href="/registration">Registration</a> </#if>
</form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Logout</button>
    </form>
</#macro>
<#macro loginn>
           <a href="/login"><button type="submit" class="btn btn-primary">Login</button></a>
</#macro>