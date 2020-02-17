<#list genders as gender>
    <div>
        <label><input type="checkbox" id="${gender}" name="${gender}" <#if product?? && product.genders??>${product.genders?seq_contains(gender)?string("checked", "")}</#if>>${gender}</label>
    </div>
</#list>