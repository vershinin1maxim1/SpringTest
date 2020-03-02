<#include "security.ftl">
<script type="text/javascript" charset="utf-8">

   function goToPage(pageNumber) {
       let url = new URL(window.location.href);
       url.searchParams.set("page", pageNumber);
       window.location.replace(url);
    };
</script>

<div>
    Сортировка: <a rel="nofollow" href="?sort=name">по названию</a> <a>по цене</a>
</div>
<div class="card-columns">
    <#list products as product>
        <div class="card my-3">
            <#if product.filename??>
                <img src="/img/${product.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <i>${product.name}</i>
                <span>${product.description}</span><br/>

            </div>
            <#if isAdmin>
            <div class="card-footer text-muted">
                    <a class="btn btn-primary" href="/user-products/edit?product=${product.id}">
                        Изменить
                    </a>
            </div>
            </#if>
        </div>
    <#else>
        Нет товаров
    </#list>
    <#if totalPages?? && (totalPages >1 )>
    <nav aria-label="Page navigation example">
        <ul class="pagination">

            <#if currentPage!=minPage>
            <li class="page-item">
                <a class="page-link" onClick="goToPage(${currentPage-1})" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            </#if>
            <#list 1..totalPages as pageNumber>
        <#if  ((totalPages-currentPage!=4)&&(totalPages - currentPage>3)&&(pageNumber==totalPages-1))||((currentPage!=5)&&(currentPage>4)&&(pageNumber==2))>
            <li class="page-item"><a class="page-link">...</a></li>
        <#else>
             <#if  (((pageNumber==totalPages-1)&&(totalPages-currentPage==4))||((pageNumber==2)&&(currentPage==5))||(pageNumber==1)||(pageNumber==totalPages)||(pageNumber==currentPage)||(pageNumber==currentPage-1)||(pageNumber==currentPage-2)||(pageNumber==currentPage+1)||(pageNumber==currentPage+2))>
                <li class="page-item"><a ? class="page-link <#if currentPage==pageNumber>selected</#if>" <#if currentPage!=pageNumber>onClick="goToPage(${pageNumber})"</#if>>${pageNumber}</a></li>
             </#if>
        </#if>

            </#list>
            <#if currentPage!=maxPage>
            <li class="page-item">
                <a class="page-link" onClick="goToPage(${currentPage+1})" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
            </#if>
        </ul>
    </nav>
    </#if>
</div>
