<#macro addToBasketPage>
    <script type="text/javascript" charset="utf-8">
function initAddToBasketWindow(){
            if(selectedProducts&&selectedProducts.length>0){
                var html="";
                var basketBody=$("#basketBody");
                basketBody.empty();
                selectedProducts.forEach(function(element){

                    basketBody.load("/ochkiGetById/"+element);
//                     debugger
//                     $.ajax({
//                         url: "/ochkiGetById/"+element,
//                         type: "GET",
//                         async: false,
//                         success:function(data) {
//
// //todo становился тут, сделать отображение полученной информации о товаре
//                             debugger
//                             if(data) {
//                                 html+="";
//                             }
//                             $("#basketBody").html(html);
//                         }
//                     });
                });
            }
        };
    </script>
    <!-- Модальное окно -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="basketBody">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Продолжить покупки</button>
                    <a class="btn btn-primary" href="/korzina">Оформить заказ</a>
                </div>
            </div>
        </div>
    </div>
</#macro>