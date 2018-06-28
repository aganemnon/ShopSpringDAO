<#import "../parts/common.ftl" as c>


<@c.page>
  <form action="/cataloglist/editcategory/${category.id}" method="post">
      <div class="input-group mb-3">
          <input type="text" name="name" class="form-control sm-8" value="${category.name}">
          <div class="input-group-append">
              <input type="hidden" name="_csrf" value="${_csrf.token}">
              <button type="submit" class="btn btn-primary">Save</button>
              <a href="/cataloglist" class="btn btn-primary">Cancel</a>
          </div>
      </div>
  </form>
<form action="/cataloglist/editcategory/remove/${category.id}" method="post">
    <p>Удалить категорию</p>
    <p>Выберите категорию куда будет перемещен удаленный товар</p>
                    <#if error_r??>
                        <div class="alert alert-danger" role="alert">
                            ${error_r}
                        </div>
                    </#if>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="inputGroupSelect01">Категория</label>
        </div>
        <select name="newCategory" class="custom-select">
            <option selected value="-1">Choose...</option>
            <#list categories as category1>
                <#if category.id != category1.id><option value="${category1.id}">${category1.name}</option></#if>
            </#list>
        </select>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Удалить</button>
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
        </#list>
</div>
</@c.page>