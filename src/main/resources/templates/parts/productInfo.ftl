<#include "security.ftl">
<div class="row">
    <div class="col-sm-3 col-md-3">
        <#if product.filename??>
            <img src="/img/${product.filename}" class="card-img-top">
        </#if>
    </div>
    <div class="col-sm-3 col-md-3">
        <i>${product.name}</i>
    </div>
</div>