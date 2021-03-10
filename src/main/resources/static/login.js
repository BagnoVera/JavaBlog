//*********** Описание поведения формы логина (страница login.html)
//*********** Проверка полей формы логина на валидность
//*********** ajax-запрос на авторизацию
//================================================================//
function showPopup(text, top, success) {

    if (success) {
        $('.error-holder').addClass('error-holder-success');
    } else {
        $('.error-holder').removeClass('error-holder-success');
    }
    $('.error-holder').css({"top":"" + top + "px", "z-index":"999999"});
    $('.error-holder span').text(text);

    $('.error-holder').fadeIn(function(){
        hideError();
    });
}
function showError(text, top){

    showPopup(text, top, false);
}
function showSuccess(text, top){

    showPopup(text, top, true);
}

function auth2() {
    console.log("Starting auth2 method");
    var email = document.getElementById("uname").value;
    var password = document.getElementById("psw").value;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "http://localhost:8080/users/findByEmail", true);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var users = this.responseText;
            console.log(users);
            if (users === "true") {
                //alert("авторизация успешна!");
                console.log("Success!");
                localStorage.setItem("username", email);
            }

        }
        if (this.status !== 200) {
            console.log(this.status, this.responseText);
        }

        }

    xmlhttp.send(JSON.stringify({email: email, passwd: password}));
    console.log("Sending creds");
}

