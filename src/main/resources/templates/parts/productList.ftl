<#include "security.ftl">

<div class="card-columns">
    <#list products as product>
        <div class="card my-3">
            <#if product.filename??>
                <img src="/img/${product.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <i>${product.name}</i>
                <span>${product.description}</span><br/>

            </div>
            <#if isAdmin>
            <div class="card-footer text-muted">
                    <a class="btn btn-primary" href="/user-products/edit?product=${product.id}">
                        Изменить
                    </a>
            </div>
            </#if>
        </div>
    <#else>
        Нет товаров
    </#list>
</div>
