<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
<div class="row">
    <#include "parts/filter.ftl" />

<#--        <#if isAdmin>-->
<#--            <#include "parts/productEdit.ftl" />-->
<#--        </#if>-->
        <#include "parts/productList.ftl" />
</div>
</@c.page>
