<#import "color.ftl" as colorPage>
<#import "gender.ftl" as genderPage>

<script type="text/javascript" charset="utf-8">
var minPrice=${filterMinPrice?c};
var maxPrice=${filterMaxPrice?c};
var maxFrame=${filterMaxFrame?c};
var minFrame=${filterMinFrame?c};

$(document).ready(function () {
    $( "#priceSlider" ).slider({
        range: true,
        min: minPrice,
        max: maxPrice,
        values: [${setFilterMinPrice?c}, ${setFilterMaxPrice?c}],
        slide: function (event, ui) {
            $("#priceFilter").text(ui.values[0] + " - " + ui.values[1] + " Р");
        }
    });
    $("#priceFilter").text($("#priceSlider").slider("values", 0) + " - " + $("#priceSlider").slider("values", 1)+ " Р");

    $( "#frameSlider" ).slider({
        range: true,
        min: minFrame,
        max: maxFrame,
        values: [${setFilterMinFrame?c}, ${setFilterMaxFrame?c}],
        slide: function (event, ui) {
            $("#frameFilter").text(ui.values[0] + " - " + ui.values[1]);
        }
    });
    $("#frameFilter").text($("#frameSlider").slider("values", 0) + " - " + $("#frameSlider").slider("values", 1));

    $("#searchButton").click(function (e) {
        window.location.replace("/ochki"+makeSearchFunction());
    });
    fillFilterParams();
    function fillFilterParams(){
        $.ajax({url: '/ochkiGetActualFilter'+makeSearchFunction(), type: 'GET', contentType: "application/json",
            success:function(result) {

             updateBadges(result);
            }});
    }
    $("#filterForm").find(".searchFilterElement").change(function () {
        fillFilterParams();
    });
});

function updateBadges(result){
    var bages = $("#filterForm").find(".badge");
    for (var i = 0; i < bages.length; i++) {

        $(bages[i]).text(getCountByAttrIdAndFilterProxy($(bages[i]).attr("attributeId"),result));
        // $(bages[i]).val("sadasd")
        // bages[i].text("sadad");
    }
    // bages.forEach(function (element) {
    //     alert("qweqwe");
    //      bages.val("asd");
    //     // attributeId
    // });
}

function getCountByAttrIdAndFilterProxy(attributeId, result){
    for (var i = 0; i < result.length; i++) {
        debugger;
        if (result[i]["attributeId"] == attributeId) {
            return result[i]["count"];
        }
    }
    return 0;
}
function makeSearchFunction(){
    var result = "";
    var currentMinPrice= $("#priceSlider").slider("values", 0);
    var currentMaxPrice = $("#priceSlider").slider("values", 1);
    if(currentMinPrice!==minPrice||currentMaxPrice!==maxPrice){
        result+="/price/"+currentMinPrice+"-"+currentMaxPrice;
    }
    var currentMinFrame = $("#frameSlider").slider("values", 0);
    var currentMaxFrame = $("#frameSlider").slider("values", 1);
    if(currentMinFrame!==minFrame||currentMaxFrame!==maxFrame){
        result+="/razmer_ramki/"+currentMinFrame+"-"+currentMaxFrame;
    }
    var searchFilterElements = $("#filterForm").find(".searchFilterElement");
    for (var i = 0; i < searchFilterElements.length; i++) {
        if ($(searchFilterElements[i]).is(":checked")) {
            result += "/" + searchFilterElements[i]["name"];
        }
    }
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
<#--                    <div class="form-group col-sd-12">-->
                        <form id="filterForm" method="get" action="/ochki" class="form-inline">
                            <div class="col-xs-12">
                                <input type="text" name="name" class="form-control" value="${filter?ifExists}" placeholder="Поиск по имени">
                            </div>
                            <div class="container-fluid col-xs-12">
                                <label for="priceFilter">Цена:</label>
                                <label id="priceFilter"></label>
                                <div id="priceSlider"></div>
                            </div>
                            <div class="container-fluid col-xs-12">
                                <label for="frameFilter">Размер рамки:</label>
                                <label id="frameFilter"></label>
                                <div id="frameSlider"></div>
                            </div>
                            <@colorPage.colorPage filterProduct/>
                            <@genderPage.genderPage filterProduct/>
                            <a id="searchButton" class="btn btn-primary ml-2">Поиск</a>
                        </form>
<#--                    </div>-->
                </div>

        </div>
    </div>
</div>