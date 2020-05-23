<#import "oneBrand.ftl" as oneBrandPage>
<#macro brandPage currentProduct=''>
    <#list brands as brand>
        <#if brand?index==5>
            <div class="collapse" id="collapseBrand">
        </#if>

        <@oneBrandPage.oneBrandPage currentProduct brand/>
    </#list>
    </div>
    <a class="filterCollapse" data-toggle="collapse" href="#collapseBrand" role="button" aria-expanded="false" aria-controls="collapseBrand">
        Показать все <i class="fa fa-fw"></i>
    </a>
</#macro>