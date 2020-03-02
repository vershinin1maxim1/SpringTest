<#macro colorPage currentProduct=''>
    <#list colors as color>
        <div>
            <label><input type="checkbox" class="searchFilterElement" id="${color}" name="${color.code}" <#if currentProduct!='' && currentProduct?? && currentProduct.colors??>${currentProduct.colors?seq_contains(color)?string("checked", "")}</#if>>${color}</label>
        </div>
    </#list>
</#macro>