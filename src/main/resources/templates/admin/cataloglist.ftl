<#import "../parts/common.ftl" as c>


<@c.page>
<div class="row">
    <div class="col-4 col-md-2">
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><a href="/cataloglist">Все категории</a></li>
            <#list categories as category>
                <li class="list-group-item">
                    <a href="/cataloglist/${category.id}">${category.name} </a>
                    <i class="icon-search"></i>
                    <a href="/cataloglist/editcategory/${category.id}">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </a>
                </li>
            </#list>
            <li class="list-group-item"><a href="/cataloglist/addcategory">
                Add new category
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            </a>
            </li>
        </ul>
    </div>
    <div class="col-12 col-md-10">
        <div class="row">
            <div class="col-md-9">
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
                            <input type="file" name="file" class="form-control-file">
                        </div>
                        <div class="col">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-sm-8">
                <div class="card-columns">
        <#list items as item>
            <div class="card">
                <#if item.image??>
                <img class="card-img-top" src="/img/${item.image}">
                </#if>
                <div class="card-body">
                    <div class="row justify-content-between">
                        <h5 class="card-title col-6">${item.name}</h5>
                        <div class="col col-2">
                            <a href="/cataloglist/edititem/${item.id}">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </a>
                            <a href="/cataloglist/remove/${item.id}">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                    <p class="card-text">${item.description}</p>
                    <p class="card-text">
                        <small class="text-muted">Cost: ${item.cost/100}$</small>
                    </p>


                </div>
            </div>
        </#list>
                </div>
            </div>
        </div>
    </div>

</div>

</@c.page>