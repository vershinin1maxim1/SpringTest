<#list genders as gender>
    <div>
        <label><input type="checkbox" class="searchFilterElement" id="${gender}" name="${gender.code}" <#if product?? && product.genders??>${product.genders?seq_contains(gender)?string("checked", "")}</#if>>${gender}</label>
    </div>
</#list>