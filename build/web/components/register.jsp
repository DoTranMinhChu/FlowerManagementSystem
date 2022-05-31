

<section onmousedown="clickHiddenSeft(this)" id="registrationFormBox" class="center-page ${requestScope.ERROR ==  null ? "hidden" : ""} ">
    <form onmousedown="event.stopPropagation();" action="" method="post" class="form-register">
        <div class="background-form">
            <img src="images/img6.jpg" alt="" class="img-responsive">
        </div>
        <div class="content-form">
            <h3 class="title">Register</h3>
            <div class="content-inner">
                <label for="" class="name-input">Email</label>
                <input type="text" name="txtEmail" class="input-form" required="" value="${requestScope.txtEmail == null ? "" : requestScope.txtEmail}" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
                <label for="" class="name-input">Full name</label>
                <input type="text" name="txtFullname" class="input-form" required="" value="${requestScope.txtFullname == null ? "" : requestScope.txtFullname}">
                <label for="" class="name-input">Password</label>
                <input type="password" name="txtPassword" class="input-form" required="">
                <label for="" class="name-input">Phone</label>                      
                <input type="text" name="txtPhone" class="input-form" value="${requestScope.txtPhone == null ? "" : requestScope.txtPhone}" >

                <p class="error-msg"> ${requestScope.ERROR == null ? "" : requestScope.ERROR}  </p>


                <input type = "submit" value = "register" name="action" class="btn-submit">

            </div> 
        </div>
    </form> 
</section> 
