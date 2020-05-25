<#macro materialPage currentProduct=''>
    <#list materials as material>
        <div>
            <label class="cursor-pointer">
                <input type="checkbox" class="searchFilterElement standard-checkbox-margin cursor-pointer" id="${material}" name="${material.code}" <#if currentProduct!='' && currentProduct?? && currentProduct.materials??>${currentProduct.materials?seq_contains(material)?string("checked", "")}</#if>>${material.rusName}</label>
            <small class="badge" attributeId="${material.id}"></small>
        </div>
    </#list>
</#macro>