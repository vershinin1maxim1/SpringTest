<#import "parts/common.ftl" as c>

<@c.page>

<div class="row">
    <div class="col-sm-6 col-md-6">
        <div class="col-sm-12">
            <h1>${product.name}</h1>
        </div>
        <div class="col-sm-12">
            <h5>Арт: ${product.vendor?c}</h5>
        </div>
        <#if product.filename??>
            <img src="/img/${product.filename}" class="card-img-top">
        </#if>
    </div>
    <div class="col-sm-6 col-md-6">

<#--        <div class="m-2">-->

            <#--                    <span>${product.description}</span><br/>-->
<#--        </div>-->
        <ul class="list-group list-group-flush">
            <li class="list-group-item">
                <label class="price">${product.price} Р</label>
                <a id="inBasket" class="btn btn-primary ml-2">В корзину</a>
            </li>
            <li class="list-group-item">
                <p class="leftstr">Брэнд</p>
                <p class="rightstr">${product.brand}</p>
<#--                <label class="standardText">Цвет</label>-->
<#--                <label class="standardText">Цвет</label>-->
<#--                <div class="col-sm-12">-->
<#--                <div class="col-sm-3 align-left">-->
<#--                    <label class="standardText">Цвет</label>-->
<#--                </div>-->
<#--                <div class="col-sm-9 align-right">-->
<#--                    <label class="standardText">Цвет</label>-->
<#--                </div>-->
<#--                </div>-->
            </li>
        </ul>
    </div>
</div>
</@c.page>

