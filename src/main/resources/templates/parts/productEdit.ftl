<#import "color.ftl" as colorPage>
<#import "gender.ftl" as genderPage>
<#import "frameForm.ftl" as frameFormPage>
<#import "brand.ftl" as brandPage>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Создание товара
</a>
<div class="collapse <#if product??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if product??>${product.name}</#if>" name="name" placeholder="Наименование" />
                <#if textError??>
                    <div class="invalid-feedback">
                        ${nameError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if product??>${product.description}</#if>" name="description" placeholder="Описание">
                <#if descriptionError??>
                    <div class="invalid-feedback">
                        ${descriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Выберите изображение товара</label>
                </div>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <div class="row">
                        <@colorPage.colorPage product/>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <@genderPage.genderPage product/>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <@frameFormPage.frameFormPage product/>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <@brandPage.brandPage product/>
                    </div>
                </li>
            </ul>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if product??>${product.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Сохранить Продукт</button>
            </div>
        </form>
    </div>
</div>

