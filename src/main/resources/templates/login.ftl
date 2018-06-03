<#import "parts/common.ftl" as c>
<#import "parts/form_auth_reg.ftl" as l>

<@c.page>
    <@l.login "/j_spring_security_check" false />
</@c.page>