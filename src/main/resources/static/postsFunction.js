function newPost() {
    var postName = document.getElementById("post_name").value;
    var postTitle = document.getElementById("post_title").value;
    var postText = document.getElementById("post_text").value;
    //var postImage = document.getElementById("post_image").value;
    var file = document.getElementById("photo-upload").files[0];
    var formData = new FormData();
    console.log(file);
    formData.append("postName", postName);
    formData.append("postTitle", postTitle);
    formData.append("postText", postText);
    formData.append("file", file);


    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open("POST", "http://localhost:8080/posts/save");
    /*xmlhttp.setRequestHeader("Content-Type", "multipart/form-data");*/
    xmlhttp.send(formData);
}

class Post{
    constructor(postName, postTitle, postText, postImage) {
        this.postName = postName;
        this.postTitle = postTitle;
        this.postText = postText;
        this.postImage = postImage;
    }
}

function downloadPosts(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var posts = JSON.parse(this.responseText);
            var html;
            for (var i = 0; i < posts.length; i++) {
                var post = posts[i];
                console.log(post.postImage);
                document.getElementById("ItemPreview").src = document.getElementById("ItemPreview").src + "data:image/png;base64," + post.postImage;

                html = html + '<div>\n' +
                    '            <a href="article/{{i}}.html"><img id="ItemPreview" width="50" height="30" alt="Java"></a>\n' +
                    '            <div class="details">\n' +
                '                <h2>'+ post.postTitle +'</h2>\n' +
                '                <p>'+ post.postText +'</p>\n' +
                '                <p>' + post.postName +'</p>\n' +
                    '            </div>\n' +
                    '\n' +
                    '        </div>';
                //var newPost = document.createElement(<div></div>)


            }
            document.getElementById("postsList").innerHTML = html;
        }
    }
    xhttp.open("GET", "http://localhost:8080/posts/findAll", true);
    xhttp.send();

}
/* олучаем картинку на клиенте
<img id="red-dot" alt="Red dot">
    <script>
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/base64/red-dot');
        xhr.onload = function() {
        var img = document.getElementById("red-dot");
        img.src = xhr.responseText;
    };
        xhr.send();
    </script>*/