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