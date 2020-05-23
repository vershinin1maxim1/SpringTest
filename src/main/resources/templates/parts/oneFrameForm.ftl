<#macro oneFrameFormPage currentProduct='', frameForm=''>
        <div>
            <label class="cursor-pointer">
                <input type="checkbox" class="searchFilterElement standard-checkbox-margin cursor-pointer" id="${frameForm}" name="${frameForm.code}" <#if currentProduct!='' && currentProduct?? && currentProduct.frameForms??>${currentProduct.frameForms?seq_contains(frameForm)?string("checked", "")}</#if>>${frameForm.rusName}</label>
            <small class="badge" attributeId="${frameForm.id}"></small>
        </div>
</#macro>