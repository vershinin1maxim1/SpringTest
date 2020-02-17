<#list colors as color>
    <div>
        <label><input type="checkbox" id="${color}" name="${color}" <#if product?? && product.colors??>${product.colors?seq_contains(color)?string("checked", "")}</#if>>${color}</label>
    </div>
</#list>