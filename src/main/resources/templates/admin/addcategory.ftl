<#import "../parts/common.ftl" as c>


<@c.page>
    Add category
    <form method="post">
        <div class="input-group mb-3">
            <input type="text" name="name" class="form-control" placeholder="Recipient's category name" aria-label="Recipient's category name">
            <div class="input-group-append">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </div>
    </form>
</@c.page>