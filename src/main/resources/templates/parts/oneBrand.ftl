<#macro oneBrandPage currentProduct='', brand=''>
        <div>
            <label class="cursor-pointer">
                <input type="checkbox" class="searchFilterElement standard-checkbox-margin cursor-pointer" id="${brand}" name="${brand.code}" <#if currentProduct!='' && currentProduct?? && currentProduct.brands??>${currentProduct.brands?seq_contains(brand)?string("checked", "")}</#if>>${brand.rusName}</label>
            <small class="badge" attributeId="${brand.id}"></small>
        </div>
</#macro>