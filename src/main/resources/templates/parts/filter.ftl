<div class="col-sm-3">
    <div class="card bg-light">
        <div class="card-header">Параметры</div>
        <div class="card-body">
<#--            <h5 class="card-title">Light card title</h5>-->
<#--            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>-->
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form method="get" action="/ochki" class="form-inline">
                            <input type="text" name="name" class="form-control" value="${filter?ifExists}" placeholder="Поиск по имени">

                            <#include "color.ftl" />
                            <#include "gender.ftl" />
                            <button type="submit" class="btn btn-primary ml-2">Поиск</button>
                        </form>
                    </div>
                </div>

        </div>
    </div>
</div>