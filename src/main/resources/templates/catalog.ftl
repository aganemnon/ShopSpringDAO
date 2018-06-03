<#import "parts/common.ftl" as c>


<@c.page>
<h5>Категории</h5>
<div class="btn-group mb-1" role="group">
    <#list categories as category>
    <a class="btn" href="/catalog/${category.id}">${category.name}</a>
    </#list>
</div>
<form>
    <h5>Поиск</h5>
    <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2">
        <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button">Button</button>
        </div>
    </div>
</form>
<form method="post" enctype="multipart/form-data" class="mb-5">
    <h5>Добавить товар</h5>
    <div class="form-row">
        <div class="col">
            <input type="text" name="name" class="form-control" placeholder="Название товара">
        </div>
        <div class="col">
            <input type="text" name="description" class="form-control" placeholder="Описание">
        </div>
        <div class="col">
            <input type="text" name="cost" class="form-control" placeholder="Цена">
        </div>
        <div class="form-group col-md-3">
            <select id="inputState" name="category" class="form-control">
                <option selected>Выберете категорию</option>
                <#list categories as category>
                    <option>${category.name}</option>
                </#list>
            </select>
        </div>
        <div class="form-group">
            <input type="file" name="file" class="form-control-file" id="exampleFormControlFile1">
        </div>
        <div class="col">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</form>

<div class="card-columns">
        <#list items as item>
            <div class="card">
                <#if item.image??>
                <img class="card-img-top" src="img/${item.image}">
                </#if>
                <div class="card-body">
                    <h5 class="card-title">${item.name}</h5>
                    <p class="card-text">${item.description}</p>
                    <p class="card-text"><small class="text-muted">Cost: ${item.cost/100}$</small></p>
                </div>
            </div>
        </#list>
</div>
</@c.page>