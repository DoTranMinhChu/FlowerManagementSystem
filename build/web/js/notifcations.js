
function remove(seft) {
  const ntf = seft.parentElement;
  const ntf_container = ntf.parentElement;
  ntf.remove();
  if (ntf_container.innerHTML.trim() == "") {
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
