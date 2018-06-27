<#import "../parts/common.ftl" as c>


<@c.page>
<form method="post" enctype="multipart/form-data" class="mb-5">
    <h5>Добавить товар</h5>
    <div class="form-row">
        <div class="col">
            <input type="text" name="name" class="form-control" value="${item.name}">
        </div>
        <div class="col">
            <input type="text" name="description" class="form-control" value="${item.description}">
        </div>
        <div class="col">
            <input type="text" name="cost" class="form-control" value="${item.cost/100}">
        </div>
        <div class="form-group col-md-3">
            <select id="inputState" name="category" class="form-control">
                <option selected>${selectCategory.name}</option>
                <#list categories as category>
                    <option>${category.name}</option>
                </#list>
            </select>
        </div>
        <div class="form-group">
            <input type="file" name="file" class="form-control-file">
        </div>
        <div class="col">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</form>
</@c.page>