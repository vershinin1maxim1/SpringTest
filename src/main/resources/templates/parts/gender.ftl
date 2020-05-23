<#macro genderPage currentProduct=''>
    <#list genders as gender>
        <div>
            <label class="cursor-pointer">
                <input type="checkbox" class="searchFilterElement standard-checkbox-margin cursor-pointer" id="${gender}" name="${gender.code}" <#if currentProduct!='' && currentProduct?? && currentProduct.genders??>${currentProduct.genders?seq_contains(gender)?string("checked", "")}</#if>>${gender.rusName}</label>
            <small class="badge" attributeId="${gender.id}"></small>
        </div>
    </#list>
</#macro>