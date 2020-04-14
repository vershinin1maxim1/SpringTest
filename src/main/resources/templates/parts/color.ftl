<#macro colorPage currentProduct=''>
    <#list colors as color>
            <div class="checkbox-color">
                <input type="checkbox" id="${color}" name="${color.code}"  class="searchFilterElement checkbox-color-input" <#if currentProduct!='' && currentProduct?? && currentProduct.colors??>${currentProduct.colors?seq_contains(color)?string("checked", "")}</#if>>
                <label for="${color}" class="checkbox-color-label checkbox-color-label-${color}"></label>
            </div>
    </#list>
</#macro>