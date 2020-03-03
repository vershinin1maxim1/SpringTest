<#import "color.ftl" as colorPage>
<#import "gender.ftl" as genderPage>


<!--Plugin JavaScript file-->

<#--<link rel="stylesheet" href="css/jquery-ui-1.8.19.custom.css">-->
<#--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ion-rangeslider/2.3.1/css/ion.rangeSlider.css">-->
<#--<script src="https://cdnjs.cloudflare.com/ajax/libs/ion-rangeslider/2.3.1/js/ion.rangeSlider.min.js"></script>-->
<#--<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>-->
<#--аджакс надо будет куда-то вынести наружу-->

<script type="text/javascript" charset="utf-8">

    $(document).ready(function () {
        $(".js-range-slider").ionRangeSlider({
            type: "integer",
            grid: true,
            min: 0,
            max: 1000,
            from: 200,
            to: 800,
            prefix: "$"
        });
        // alert("sad");
        // alert();
        $("#searchButton").click(function (e) {
            // alert("asda");
            window.location.replace(makeSearchFunction());
        });
    });

    function makeSearchFunction(){
        var result="/ochki";

        var searchFilterElements=$("#filterForm").find(".searchFilterElement");

            for(var i=0;i< searchFilterElements.length;i++){
                debugger;
                if($(searchFilterElements[i]).is(":checked")) {
                    debugger;
                    result += "/" + searchFilterElements[i]["name"];
                }
            }

            debugger;


        debugger;
        return result;
    }
</script>
<div class="col-sm-3">
    <div class="card bg-light">
        <div class="card-header">Параметры</div>
        <div class="card-body">
<#--            <h5 class="card-title">Light card title</h5>-->
<#--            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>-->
                <div class="form-row">
                    <div class="form-group col-sd-12">
                        <form id="filterForm" method="get" action="/ochki" class="form-inline">
                            <div class="col-xs-12">
                                <input type="text" name="name" class="form-control" value="${filter?ifExists}" placeholder="Поиск по имени">
                            </div>
                            <div class="container-fluid col-xs-12">
ertyert
                                <input type="text" class="js-range-slider" name="my_range" value="" />
                            </div>
                            <@colorPage.colorPage filterProduct/>
                            <@genderPage.genderPage filterProduct/>
                            <a id="searchButton" class="btn btn-primary ml-2">Поиск</a>
                        </form>
                    </div>
                </div>

        </div>
    </div>
</div>