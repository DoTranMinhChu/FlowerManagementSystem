<section  onmousedown="clickHiddenSeft(this)" id="${param.idForm}" class="center-page hidden">
        <div onmousedown="event.stopPropagation();" class="confirm-box">
            <h3 class="title">${param.title}</h3>
            <h4 class="message">${param.message}</h4>
            <div class="button-container">
                <button class="btn-edc btn-create" type="submit" name="${param.nameYes}" value="${param.valueYes}" onclick="clickHidden('${param.idForm}')">Yes</button>
                <button class="btn-edc btn-delete"  type="button" name="${param.nameBo}" value="${param.valueNo}" onclick="clickHidden('${param.idForm}')">No</button>
            </div>
        </div>
</section>
