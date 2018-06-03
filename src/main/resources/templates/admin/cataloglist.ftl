<#import "../parts/common.ftl" as c>


<@c.page>
<div class="row">
    <div class="col-sm-3">
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Cras justo odio</li>
            <li class="list-group-item">Dapibus ac facilisis in</li>
            <li class="list-group-item">Morbi leo risus</li>
            <li class="list-group-item">Porta ac consectetur ac</li>
            <li class="list-group-item">Vestibulum at eros</li>
        </ul>
    </div>
    <div class="col-sm-9">
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
    </div>
</div>

</@c.page>