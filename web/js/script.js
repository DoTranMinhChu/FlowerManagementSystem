function clickHiddenSeft(seft) {
    seft.classList.add("hidden");
}

function clickShow(idShow) {
    document.getElementById(idShow).classList.remove("hidden");
}

function clickHidden(idHidden) {
    document.getElementById(idHidden).classList.add("hidden");
}

function showPreview(event, idImg) {
    if (event.target.files.length > 0) {
        var src = URL.createObjectURL(event.target.files[0]);
        var preview = document.getElementById(idImg);
        preview.src = src;
        preview.style.display = "block";
    }
}

function setSrcImg(idImg, src) {
    var img = document.getElementById(idImg);
    img.src = src;
    img.style.display = "block";
}

function remove(seft) {
    const ntf = seft.parentElement;
    const ntf_container = ntf.parentElement;
    ntf.remove();
    if (ntf_container.innerHTML.trim() === "") {
        ntf_container.remove();
        console.log("check");
    }
}

function removeInterval(className, second) {
    setTimeout(() => {
        document.getElementsByClassName(className)[0].remove();
    }, second * 1000);
}

removeInterval("ncf-container", 6);
