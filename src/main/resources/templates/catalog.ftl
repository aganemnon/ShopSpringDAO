<#import "parts/common.ftl" as c>


<@c.page>
<div class="btn-group mb-5" role="group">
    <a class="btn" href="#">Link</a>
    <a class="btn" href="#">Link</a>
    <a class="btn" href="#">Link</a>
</div>
<form method="post" enctype="multipart/form-data" class="mb-5">
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
                <option>...</option>
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

<div class="card" style="width: 18rem;">
    <img class="card-img-top" src="/img/1.jpg" alt="Card image cap">
    <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
    </div>
</div>
</@c.page>