<#import "../parts/common.ftl" as c>


<@c.page>
<h5>UserList</h5>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Login</th>
            <th scope="col">Options</th>
        </tr>
        </thead>
        <tbody>
            <#list users as user>
            <tr>
                <th scope="row">${user.userId}</th>
                <td>${user.userName}</td>
                <td><a href="/userlist/${user.userId}">Edit User</a></td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
</@c.page>