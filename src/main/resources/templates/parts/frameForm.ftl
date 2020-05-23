<#import "oneFrameForm.ftl" as oneFrameFormPage>
<#macro frameFormPage currentProduct=''>
    <#list frameForms as frameForm>
        <#if frameForm?index==5>
            <div class="collapse" id="collapseFrameForm">
        </#if>

        <@oneFrameFormPage.oneFrameFormPage currentProduct frameForm/>
    </#list>
    </div>
    <a class="filterCollapse" data-toggle="collapse" href="#collapseFrameForm" role="button" aria-expanded="false" aria-controls="collapseFrameForm">
        Показать все <i class="fa fa-fw"></i>
    </a>
</#macro>