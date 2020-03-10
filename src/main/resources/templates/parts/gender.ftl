<#macro genderPage currentProduct=''>
    <#list genders as gender>
        <div>
            <label><input type="checkbox" class="searchFilterElement" id="${gender}" name="${gender.code}" <#if currentProduct!='' && currentProduct?? && currentProduct.genders??>${currentProduct.genders?seq_contains(gender)?string("checked", "")}</#if>>${gender}</label>
            <small class="badge" attributeId="${gender.id}"></small>
        </div>
    </#list>
</#macro>