function loadUsers() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var users = JSON.parse(this.responseText);
            var html = '<tr>\n' +
                '        <th>User id</th>\n' +
                '        <th>User name</th>\n' +
                '        <th>User passwd</th>\n' +
                '        <th>User email</th>\n' +
                '        <th>Delete</th>\n' +
                '    </tr>';
            for (var i = 0; i < users.length; i++) {
                var user = users[i];
                console.log(user);
                html = html + '<tr><td>' + user.id + '</td>\n' +
                    '        <td>' + user.name + '</td>\n' +
                    '        <td>' + user.passwd + '</td>\n' +
                    '        <td>' + user.email + '</td>' +
                    '        <td><button onclick="deleteUser(' + user.id + '), location.reload();">Delete</button></td></tr>';

            }
            document.getElementById("usersList").innerHTML = html;
        }
    };
    xhttp.open("GET", "http://localhost:8080/users/findAll", true);
    xhttp.send();

}

function deleteUser(userId) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "http://localhost:8080/users/delete/" + userId, true);
    xhttp.send();
    loadUsers();
}

function createUser() {
    var userName = document.getElementById("user_name").value;
    var userEmail = document.getElementById("user_email").value;
    var userPasswd = document.getElementById("user_passwd").value;

    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open("POST", "http://localhost:8080/users/save");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({name: userName, passwd: userPasswd, email: userEmail}));

    loadUsers();
}
