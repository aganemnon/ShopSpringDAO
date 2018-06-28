<#import "parts/common.ftl" as c>


<@c.page>
<h5>Категории</h5>
<div class="btn-group mb-1" role="group">
    <a class="btn" href="/catalog/">Все категории</a>
    <#list categories as category>
        <a class="btn" href="/catalog/${category.id}">${category.name}</a>
    </#list>
</div>
<form method="post">
    <div class="form-group">
        <label>Название товара</label>
        <input type="text" class="form-control" name="name" placeholder="Название товара">
    </div>
    <div class="form-group">
        <label>Описание товара</label>
        <input type="text" class="form-control" name="description" placeholder="Описание товара">
    </div>
    <div class="form-group">
        <label>Цена(0-10000)</label>
        <input type="range" class="form-control-range" name="price" min="0" max="10000">
    </div>
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-primary">Поиск</button>
</form>

   <div class="card-columns">
        <#list items as item>
            <div class="card">
                <#if item.image??>
                <img class="card-img-top" src="/img/${item.image}">
                </#if>
                <div class="card-body">
                    <h5 class="card-title">${item.name}</h5>
                    <p class="card-text">${item.description}</p>
                    <p class="card-text">
                        <small class="text-muted">Cost: ${item.cost/100}$</small>
                    </p>
                </div>
            </div>
        <#else>
<p>Ничего не найдено</p>
        </#list>
   </div>




</@c.page>