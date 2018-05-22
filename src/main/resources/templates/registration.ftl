<#import "parts/common.ftl" as c>
<#import "parts/form_auth_reg.ftl" as f>

<@c.page>
<div class="mb-1">Registration</div>
<div class="alert alert-warning" role="alert">
${message?ifExists}
</div>
<@f.login "/registration" true />
</@c.page>