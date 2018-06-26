<#import "../parts/common.ftl" as c>


<@c.page>
  <form method="post">
      <div class="input-group mb-3">
          <input type="text" name="name" class="form-control sm-8" value="${category.name}">
          <div class="input-group-append">
              <input type="hidden" name="_csrf" value="${_csrf.token}">
              <button type="submit" class="btn btn-primary">Save</button>
              <a href="/cataloglist" class="btn btn-primary">Cancel</a>
          </div>
      </div>
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