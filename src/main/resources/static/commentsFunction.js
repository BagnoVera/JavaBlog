function addComment(){
    var newComment = document.getElementById("comment").value;
    xmlhttp.open("POST", "http://localhost:8080/comments/save");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({name: userName, commentText: commentText, email: userEmail}));
}

function loadComments() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var comments = JSON.parse(this.responseText);
            var html = '<tr>\n' +
                '        <th>User id</th>\n' +
                '        <th>User comment</th>\n' +
                '        <th>User email</th>\n' +
                '        <th>Delete</th>\n' +
                '    </tr>';
            for (var i = 0; i < comments.length; i++) {
                var comment = comments[i];
                console.log(comment);
                html = html + '<tr><td>' + comment.id + '</td>\n' +
                    '        <td>' + comment.name + '</td>\n' +
                    '        <td>' + comment.email + '</td>' +
                    '        <td><button onclick="deleteComment(' + comment.id + ')">Delete</button></td></tr>';

            }
            document.getElementById("commentsList").innerHTML = html;
        }
    };
    xhttp.open("GET", "http://localhost:8080/users/findAll", true);
    xhttp.send();
}