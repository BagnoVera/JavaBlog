function downloadArticle() {
    const queryString = window.location.search;
    console.log(queryString);
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('id')
    console.log(id);
    //Загрузка поста
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var post = JSON.parse(this.responseText);
            var html = '';
            var newImg = document.createElement("img");
            newImg.id = "ItemPreview";
            html = html + `<div>
                    <img id="${newImg.id}" src="data:image/jpg;base64, ${post.postImage}" width="200" height="140" alt="Java"></a>
                    <div class="details">
                    <h2>${post.postTitle}</h2>
                    <p>${post.postText}</p>
                    <p>${post.postName}</p>
                    </div>
                    </div>`
            document.getElementById("post").innerHTML = html;
        }}
    xhttp.open("GET", "http://localhost:8080/posts/article/" + id, true);
    xhttp.send();

    //Загрузка комментариев
    var xhttpcom = new XMLHttpRequest();
    xhttpcom.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var comments = JSON.parse(this.responseText);
            var html2 = '';
                for (var i = 0; i < comments.length; i++) {
                    var comment = comments[i];
                    console.log(comment.commentImage);
                    var newImg = document.createElement("img");
                    newImg.id = "ItemPreview" + i;

                    html2 = html2 + `<div>
                        <img id="${newImg.id}" src="data:image/jpg;base64, ${comment.commentImage}" width="100" height="70" alt="Java"></a>
                        <div class="details">
                        <h2>${comment.commentName}</h2>
                        <p>${comment.commentText}</p>
                        <button onclick="deleteComment(${comment.commentId})">Удалить комментарий</button>
                        </div>
                        </div>`

        }
           document.getElementById("comment").innerHTML = html2;
        }
    }
    xhttpcom.open("GET", "http://localhost:8080/comments/findAll/" + id, true);
    xhttpcom.send();
}

function newComment(){
    const queryString = window.location.search;
    console.log(queryString);
    const urlParams = new URLSearchParams(queryString);
    const idPost = urlParams.get('id');
    console.log(idPost);
    var nameComment = document.getElementById("comment_name").value;
    var newComment = document.getElementById("comment_text").value;
    var file = document.getElementById("photo-upload").files[0];

    if (newComment === "") {
        //document.getElementById("error").innerHTML = "Впишите название поста и текст!";
        console.log("Нет поста и текста!");
        setTimeout(function() {alert ('Нет текста комментария!'), 3000});
    }
    else if (nameComment === "") {
        console.log("nameComment empty");
        if (localStorage.getItem("username") === null) {
            setTimeout(function () {alert('Нет имени!'), 3000});
        }
        else {
            nameComment = localStorage.getItem("username");
        }
        var formData = new FormData();
        console.log("Form data sending");
        formData.append("commentPostId", idPost);
        formData.append("commentName", nameComment);
        formData.append("commentText", newComment);
        formData.append("file", file);

        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("POST", "http://localhost:8080/comments/save/");
        xmlhttp.send(formData);
    }
}
function deleteComment(commentId){
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "http://localhost:8080/comments/delete/" + commentId, true);
    xhttp.send();
}