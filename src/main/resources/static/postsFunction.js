function newPost() {
    var postName = document.getElementById("post_name").value;
    var postTitle = document.getElementById("post_title").value;
    var postText = document.getElementById("post_text").value;
    //var postImage = document.getElementById("post_image").value;
    var file = document.getElementById("photo-upload").files[0];
    var formData = new FormData();
    //console.log(file);
    formData.append("postName", postName);
    formData.append("postTitle", postTitle);
    formData.append("postText", postText);
    formData.append("file", file);


    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open("POST", "http://localhost:8080/posts/save");
    //xmlhttp.setRequestHeader("Content-Type", "multipart/form-data");
    xmlhttp.send(formData);
    //window.confirm("Ваш пост " + PostTitle + "опубликован");
    // if (window.confirm("Ваш пост " + PostTitle + "опубликован")) {
    //     console.log("you pressed OK!");
    // } else {
    //     console.log("You pressed Cancel!");
    // }
    document.getElementById("demo").innerHTML = txt;

    window.location.href = 'http://localhost:8080/index.html';

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
                html = html + `<div>
                    <a href="article.html?id=${j}"> <img id="${newImg.id}" src="data:image/jpg;base64, ${post.postImage}" width="100" height="70" alt="Java"></a>
                    <div class="details">+
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