<#import "parts/common.ftl" as c>
<#import "parts/form_auth_reg.ftl" as f>

<@c.page>
<div class="mb-1">Registration</div>
<#if message??>
    <div class="alert alert-warning" role="alert">
        ${message?ifExists}
    </div>
</#if>
<@f.login "/registration" true />
</@c.page>