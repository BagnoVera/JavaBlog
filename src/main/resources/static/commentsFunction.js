function loadComments() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var comments = JSON.parse(this.responseText);
            /*var html = '<tr>\n' +
                '        <th>Comment id</th>\n' +
                '        <th>Text of comment</th>\n' +
                '        <th>User email</th>\n' +
                '        <th>Delete</th>\n' +
                '    </tr>';
            for (var i = 0; i < comments.length; i++) {
                var comment = comments[i];
                console.log(comment);
                html = html + '<tr><td>' + comment.id + '</td>\n' +
                    '        <td>' + comment.name + '</td>\n' +
                    '        <td>' + comment.email + '</td>' +
                    '        <td><button onclick="deleteComment(' + comment.id + ')">Delete</button></td></tr>';*/

            }
            document.getElementById("commentsList").innerHTML = html;
        }
    xhttp.open("GET", "http://localhost:8080/comments/findAll", true);
    xhttp.send();

}
function createComment(){
    var nameComment = document.getElementById("name").value;
    var newComment = document.getElementById("comment_text").value;
    xmlhttp.open("POST", "http://localhost:8080/comments/save");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({name: nameComment, commentText: newComment}));
    //loadComments();
}

function loadImage() {
    // берём любое изображение
    let img = document.querySelector('img');

// создаём <canvas> того же размера
    let canvas = document.createElement('canvas');
    canvas.width = img.clientWidth;
    canvas.height = img.clientHeight;

    let context = canvas.getContext('2d');

// копируем изображение в  canvas (метод позволяет вырезать часть изображения)
    context.drawImage(img, 0, 0);
// мы можем вращать изображение при помощи context.rotate() и делать множество других преобразований

// toBlob является асинхронной операцией, для которой callback-функция вызывается при завершении
    canvas.toBlob(function(blob) {
        // после того, как Blob создан, загружаем его
        let link = document.createElement('a');
        link.download = 'example.png';

        link.href = URL.createObjectURL(blob);
        link.click();

        // удаляем внутреннюю ссылку на Blob, что позволит браузеру очистить память
        URL.revokeObjectURL(link.href);
    }, 'image/png');
}