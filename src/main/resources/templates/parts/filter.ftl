<#import "color.ftl" as colorPage>
<#import "gender.ftl" as genderPage>
<#import "frameForm.ftl" as frameFormPage>
<#import "brand.ftl" as brandPage>

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
            // $("#priceFilter").text(ui.values[0] + " - " + ui.values[1] + " Р");
            // fillPriceInputs();
            $("#minPriceValue").val(ui.values[0]);
            $("#maxPriceValue").val(ui.values[1]);
        }
    });
    // function fillPriceInputs(){
    //    $("#minPriceValue").val()
    // }
    $("#minPriceValue").change(function(){
        var val = $("#minPriceValue").val();
        if(val>=minPrice&&(val<=$("#priceSlider").slider('values',1))) {
            $("#priceSlider").slider('values', 0, val);
        }
    });

    $("#maxPriceValue").change(function(){
        var val = $("#maxPriceValue").val();
        if(val<=maxPrice&&(val>=$("#priceSlider").slider('values',0))) {
            $("#priceSlider").slider('values', 1, val);
        }
    });

    // $("#maxPriceValue").trigger("change");
    // $("#minPriceValue").trigger("change");
    // $("#priceFilter").text($("#priceSlider").slider("values", 0) + " - " + $("#priceSlider").slider("values", 1)+ " Р");
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
<div class="col-sm-4 col-md-3">
    <div class="card">
        <div class="card-header">Параметры</div>
        <form id="filterForm" method="get" action="/ochki">
            <ul class="list-group list-group-flush">
                <li class="list-group-item" style="text-align: center">
                    <label for="priceFilter">Цена, Р:</label>
                    <label id="priceFilter"></label>
                    <div style="margin-bottom: 5px;">
                        <input name="minPriceValue" value=" ${setFilterMinPrice}" type="text" size="3"
                               id="minPriceValue">
                        -
                        <input name="maxPriceValue" value="${setFilterMaxPrice}" type="text" size="3"
                               id="maxPriceValue">
                    </div>
                    <div class="">
                        <div id="priceSlider"></div>
                    </div>
                </li>
                <li class="list-group-item"  style="text-align: center">
                    <label for="frameFilter">Размер рамки:</label>
                    <label id="frameFilter"></label>
                    <div id="frameSlider"></div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <@colorPage.colorPage filterProduct/>
                    </div>
                </li>
                <li class="list-group-item">
                    <@genderPage.genderPage filterProduct/>
                </li>
                <li class="list-group-item">
                    <@frameFormPage.frameFormPage filterProduct/>
                </li>
                <li class="list-group-item">
                    <@brandPage.brandPage filterProduct/>
                </li>
                <li class="list-group-item" style="text-align: center">
                    <a id="searchButton" class="btn btn-primary ml-2">Поиск</a>
                </li>
            </ul>
        </form>
    </div>
</div>