<#macro genderPage currentProduct>
    <#list genders as gender>
        <div>
            <label><input type="checkbox" class="searchFilterElement" id="${gender}" name="${gender.code}" <#if currentProduct?? && currentProduct.genders??>${currentProduct.genders?seq_contains(gender)?string("checked", "")}</#if>>${gender}</label>
        </div>
    </#list>
</#macro>