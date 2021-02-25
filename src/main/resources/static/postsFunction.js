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

function loadPosts(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var posts = JSON.parse(this.responseText);
            var html = '<tr>\n' +
                '        <th>User id</th>\n' +
                '        <th>User name</th>\n' +
                '        <th>User passwd</th>\n' +
                '        <th>User email</th>\n' +
                '        <th>Delete</th>\n' +
                '    </tr>';
            for (var i = 0; i < posts.length; i++) {
                var post = posts[i];
                console.log(post);
                html = html + '<tr><td>' + post.id + '</td>\n' +
                    '        <td>' + post.name + '</td>\n' +
                    '        <td>' + post.title + '</td>\n' +
                    '        <td>' + post.text + '</td>' +
                    '        <td><button onclick="deletePost(' + post.id + '), location.reload();">Delete</button></td></tr>';

            }
            document.getElementById("usersList").innerHTML = html;
        }
    };
    xhttp.open("GET", "http://localhost:8080/posts/findAll", true);
    xhttp.send();

}