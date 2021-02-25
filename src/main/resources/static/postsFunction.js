class PostIt{

}

function newPost() {
    var namePost = document.getElementById("post_name").value;
    var postTitle = document.getElementById("post_title").value;
    var newPost = document.getElementById("post_text").value;

    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open("POST", "http://localhost:8080/posts/save");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({postName: namePost, postTitle: postTitle, postText: newPost}));
}