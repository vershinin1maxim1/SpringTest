<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
<div class="row">
    <#include "parts/filter.ftl" />
    <div class="col-sm-9">
<#--        <#if isAdmin>-->
<#--            <#include "parts/productEdit.ftl" />-->
<#--        </#if>-->
        <#include "parts/productList.ftl" />
    </div>
</div>
</@c.page>
