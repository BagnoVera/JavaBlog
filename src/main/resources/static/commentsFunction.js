// function loadComments() {
//     var xhttp = new XMLHttpRequest();
//     xhttp.onreadystatechange = function () {
//         if (this.readyState == 4 && this.status == 200) {
//             var comments = JSON.parse(this.responseText);
//             /*var html = '<tr>\n' +
//                 '        <th>Comment id</th>\n' +
//                 '        <th>Text of comment</th>\n' +
//                 '        <th>User email</th>\n' +
//                 '        <th>Delete</th>\n' +
//                 '    </tr>';
//             for (var i = 0; i < comments.length; i++) {
//                 var comment = comments[i];
//                 console.log(comment);
//                 html = html + '<tr><td>' + comment.id + '</td>\n' +
//                     '        <td>' + comment.name + '</td>\n' +
//                     '        <td>' + comment.email + '</td>' +
//                     '        <td><button onclick="deleteComment(' + comment.id + ')">Delete</button></td></tr>';*/
//
//             }
//             document.getElementById("commentsList").innerHTML = html;
//         }
//     xhttp.open("GET", "http://localhost:8080/comments/findAll", true);
//     xhttp.send();
//
// }
