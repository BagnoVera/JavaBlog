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

$(document).ready(function(){

    //---------------------------- фильтры для проверки полей на недопустимые символы (отключены)
    //---------------------------- https://www.sitepoint.com/expressions-javascript/
    //var filterUsername = /^([a-zA-Z0-9_\-])+$/;
    //var filterPassword = /^[a-zA-Z0-9!%&@#$\^*?_~+]+$/;

    $('#pass').on('keyup', function(e){
        //---------------------------- если пользователь нажал enter
        if (e.keyCode === 13){
            $('.b-login').click();
        }
    });

    //=========================== Кнопка войти ==========================//

    $('.b-login').on("click", function(){

        //---------------------------- параметры для авторизации
        var data = {};
        data.username = $('#username').val();
        data.password = $('#pass').val();

        if (data.username === ''){
            //-------------------- showError(text, top) функция для отображения ошибки
            //-------------------- text - текст сообщения
            //-------------------- top - отступ от верха страницы
            showError('Пожалуйста введите свое имя!', 50);
        } else if (data.password === ''){
            showError('Пожалуйста введите свой пароль!', 50);
        /*} else if (!filterUsername.test(data.username)){
            showError('Недопустимые символы в имени', 50);
        } else if(!filterPassword.test(data.password)) {
            showError('Недопустимые символы в пароле', 50); */
        } else {
            $.ajax({
                url: '/login',
                type: 'POST',
                dataType: 'json',
                data: data,
                error: function(){
                    showError('Неверное имя или пароль!', errorTopMargin);
                }
            }).done(function(data){

                if (data.link){
                    window.location.href = data.link;
                } else {
                    showError('Неверное имя или пароль!', errorTopMargin);
                }

            })
                }
                })
            //----------------------- ajax-запрос на авторизацию
            showSuccess('Авторизация', 50);

    })
