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
    xmlhttp.open("POST", "http://localhost:8080/users/login", true);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var responde = this.responseText;
            console.log(responde);
            if (responde === "true"){
                setCookie(email, password);
                console.log("Success!");
                localStorage.setItem("username", email);
                //Set-Cookie: user=email; path=/
                window.location.href = "userpage.html";
                console.log("Finally");
                }
            }
        }
        if (this.status !== 200) {
            console.log(this.status, this.responseText);
        }

    xmlhttp.send(JSON.stringify({email: email, passwd: password}));
    console.log("Sending creds");

}

function setCookie(name, value, options = {}) {

    options = {
        path: '/',
        // при необходимости добавьте другие значения по умолчанию
        //...options
    };

    // if (options.expires instanceof Date) {
    //     options.expires = options.expires.toUTCString();
    // }

    let updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);

    for (let optionKey in options) {
        updatedCookie += "; " + optionKey;
        let optionValue = options[optionKey];
        if (optionValue !== true) {
            updatedCookie += "=" + optionValue;
        }
    }

    document.cookie = updatedCookie;
}
