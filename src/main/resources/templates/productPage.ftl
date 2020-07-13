<#import "parts/common.ftl" as c>

<@c.page>

    <#if product.filename??>
        <img src="/img/${product.filename}" class="card-img-top">
    </#if>
    <div class="m-2">
        <i>${product.name}</i>
        <#--                    <span>${product.description}</span><br/>-->

    </div>
</@c.page>

