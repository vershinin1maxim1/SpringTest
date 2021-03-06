<#include "security.ftl">
<#import "addToBasket.ftl" as addToBasketPage>
<script type="text/javascript" charset="utf-8">
    var selectedProducts=[];
    $(document).ready(function() {
         <#list products as product>
            $("#addToBasketButton_${product.id}").on("click", function () {
                selectedProducts=[];
                selectedProducts.push("${product.id}");//запоминаем последний выбранный товар, чтоб использовать его на другой странице
                initAddToBasketWindow();
            });
          </#list>

    });
   function goToPage(pageNumber) {
       let url = new URL(window.location.href);
       url.searchParams.set("page", pageNumber);
       window.location.replace(url);
    };
</script>
<#--<div class="col-sm-8 col-md-9">-->
<div class="col-sm-8 col-md-9">
    <div>
        Сортировка:
        <i class="fas fa-caret-<#if (orderName?? && orderName=="ASC")>up<#else>down</#if>"></i> <a rel="nofollow" href="?sort=name&order=<#if orderName??&&orderName=="ASC">DESC<#else>ASC</#if>" <#if orderName??>style="font-weight: bold;"</#if>>по названию</a>
        <i class="fas fa-caret-<#if  (orderPrice??&& orderPrice=="ASC")>up<#else>down</#if>"></i> <a rel="nofollow" href="?sort=price&order=<#if orderPrice??&&orderPrice=="ASC">DESC<#else>ASC</#if>" <#if orderPrice??>style="font-weight: bold;"</#if>>по цене</a>
    </div>
    <div class="card-columns">
        <#list products as product>
            <div class="card">
<#--            <div class="card" style="width:250px">-->
                <div>

                        <#if product.filename??>
                            <img src="/img/${product.filename}" class="card-img-top">
                        </#if>
                        <div class="m-2">
                            <a href="/ochki/oprava_<#if product.vendor??&&product.brand??>${product.brand.code}_${product.vendor?c}</#if>">

                            <i>${product.name}</i>
        <#--                    <span>${product.description}</span><br/>-->

                        </div>
                    </a>
                </div>
                <#if isAdmin>
                <div class="card-footer text-muted">
                    <a class="btn btn-primary" href="/admin-products/edit?product=${product.id}">
                        Изменить
                    </a>
                </div>
                </#if>
                <div class="card-footer text-muted">
                    <a class="btn btn-primary" id="addToBasketButton_${product.id}"  data-toggle="modal" data-target="#myModal">
                        добавить в корзину
                    </a>
                </div>
            </div>
        <#else>
            Нет товаров
        </#list>
    </div>
    <#if totalPages?? && (totalPages >1 )>
        <div style=" display: block; margin-left: auto; margin-right: auto">
            <nav  style=" display: block; margin-left: auto; margin-right: auto">
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
        </div>
    </#if>
</div>
<@addToBasketPage.addToBasketPage/>