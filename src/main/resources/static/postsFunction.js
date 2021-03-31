function newPost() {
    var postName = document.getElementById("post_name").value;
    var postTitle = document.getElementById("post_title").value;
    var postText = document.getElementById("post_text").value;
    var file = document.getElementById("photo-upload").files[0];
    if (postTitle === "" || postText === "") {
        document.getElementById("error").innerHTML = "Впишите название поста и текст!";
        console.log("Нет поста и текста!");
        setTimeout(function() {alert ('1'), 3000});
    }
    else if (postName === "") {
        if (localStorage.getItem("username") === null) {
            document.getElementById("error").innerHTML ="Впишите свое имя!";
            console.log("Впишите ваше имя!")
        }
        else {
            postName = localStorage.getItem("username");
            var formData = new FormData();
            formData.append("postName", postName);
            formData.append("postTitle", postTitle);
            formData.append("postText", postText);
            formData.append("file", file);
        }
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("POST", "http://localhost:8080/posts/save");
        xmlhttp.send(formData);
    }
}

function downloadPosts(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var posts = JSON.parse(this.responseText);
            var html = '';
            for (var i = 0; i < posts.length; i++) {
                var post = posts[i];
                //console.log(post.postImage);
                var newImg = document.createElement("img");
                newImg.id = "ItemPreview" + i;
                var j = i+1;
                html = html +
                    `<div>
                    <a href="article.html?id=${j}"> <img id="${newImg.id}" src="data:image/jpg;base64, ${post.postImage}" width="100" height="70" alt="Java"></a>
                    <div class="details">
                        <h2>${post.postTitle}</h2>
                        <p>${post.postText}</p> 
                        <p>${post.postName}</p>
                    </div>
                    </div>`
            }
            document.getElementById("postsList").innerHTML = html;
        }
    }
    xhttp.open("GET", "http://localhost:8080/posts/findAll", true);
    xhttp.send();
}

function deletePost(postId) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "http://localhost:8080/posts/delete/" + postId, true);
    xhttp.send();
    loadUsers();
}

function searchDocument() {
    var filename = document.getElementById("lfi").value;
    var url = new URL("localhost:8080/index.html?filename=" + filename);
    url.searchParams.set("path", filename);
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/posts/getFile/path=" + filename, true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var responde = this.responseText;
            if (responde === "true"){
                document.getElementById("lfi_result").innerHTML = "Документ " + filename + " существует!";
            }
            else{document.getElementById("lfi_result").innerHTML = "Документ " + filename + " не существует!";}
        }
    }
    xhttp.send();
}