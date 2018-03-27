function ajax(url, data, func) {
    $.ajax(url, {
        method: 'post',
        dataType: 'json',
        data: data,
        complete: function (data) {
            func(data)
        },
        error: function (a, s, d) {
            console.log("ajax - error")
        }
    });
}


/**
 * При закрытии модального окна очищаются все
 * поля. При открытии модального окна фокус устанавливается
 * на первый input
 */
$('#newModal').on('hide.bs.modal', function () {
    const elems = document.getElementsByClassName('form-control');
    for (let i = 0; i < elems.length; i++) {
        elems[i].value = ''
    }
}).on('shown.bs.modal', function () {
    $('#nameInput').focus()
});

/**
 * Заполнение модального окна данными пользователя для
 * обновления его данных
 *
 * @param id - id пользователя для обновления
 */
function fillModal(id) {
    ajax('./json', {
        'command': 'fillUpdateForm',
        'id': id,
    }, function (data) {
        console.log(data.responseText);
        const user = JSON.parse(data.responseText);
        $('#id').val(user.id);
        $('#nameInput').val(user.name);
        $('#loginInput').val(user.login);
        $('#passInput').val(user.password);
        $('#emailInput').val(user.email);
        openModal(user);
    });
}


/**
 * Открытие модального окна. Установка кнопок в зависимости
 * от выполняемого действия:
 * 1. Если user = null значит форма открывается для
 * создания нового пользователя
 * 2. Если user != null форма для редактирования
 *
 * @param user - пользователь
 */
function openModal(user) {
    const button = document.getElementById('submitModal');
    if (user === undefined || user === null) {
        button.setAttribute('onclick', 'sendData("add")');
        button.innerHTML = 'Add';
    } else {
        button.setAttribute('onclick', 'sendData("save")');
        button.innerHTML = 'Save';
    }
    fillCountry(user);
    fillRole(user);
    $("#newModal").modal('show').on('shown.bs.modal', function () {
        fillCityByCountry(user);
    });
}
/**
 * Заполнение списка ролями
 *
 * @param user - пользователь
 */
function fillRole(user) {
    $.ajax('./json', {
        method: 'post',
        dataType: 'json',
        data: {'command': 'fillRole'},
        complete: function (data) {
            const roles = JSON.parse(data.responseText);
            let result = '';
            for (let index = 0; index < roles.length; index++) {
                if (user !== undefined && roles[index].type === user.role.type) {
                    result += "<option selected value='" + roles[index].id + "'>" + roles[index].type + "</option>"
                } else {
                    result += "<option value='" + roles[index].id + "'>" + roles[index].type + "</option>"
                }
            }
            const select = document.getElementById('roleSelect');
            select.innerHTML = result;
        }
    });
}
/**
 * Заполнение списка странами
 *
 * @param user - пользователь
 */
function fillCountry(user) {
    $.ajax('./json', {
        method: 'post',
        dataType: 'json',
        data: {'command': 'fillCountry'},
        complete: function (data) {
            const countries = JSON.parse(data.responseText);
            let result = '';
            for (let index = 0; index < countries.length; index++) {
                if (user !== undefined && countries[index].countryName === user.country.countryName) {
                    result += "<option selected value='" + countries[index].id + "'>" + countries[index].countryName + "</option>"
                } else {
                    result += "<option value='" + countries[index].id + "'>" + countries[index].countryName + "</option>"
                }
            }
            const select = document.getElementById('countrySelect');
            select.innerHTML = result;
        }
    });
}
/**
 * Заполнение списка городами
 *
 * @param user - пользователь
 */
function fillCityByCountry(user) {
    const country = document.getElementById('countrySelect').value;
    $.ajax('./json', {
        method: 'post',
        dataType: 'json',
        data: {
            'command': 'fillCity',
            'id': country
        },
        complete: function (data) {
            const cities = JSON.parse(data.responseText);
            let result = '';
            for (let index = 0; index < cities.length; index++) {
                if (user !== undefined && cities[index].cityName === user.city.cityName) {
                    result += "<option selected value='" + cities[index].id + "'>" + cities[index].cityName + "</option>"
                } else {
                    result += "<option value='" + cities[index].id + "'>" + cities[index].cityName + "</option>"
                }
            }
            const select = document.getElementById('citySelect');
            select.innerHTML = result;
        }
    })
}
/**
 * Отправка даднных модального окна на сервер для записи в базу.
 * @param command - комманда для сервлета
 */
function sendData(command) {
    $.ajax('./json', {
        method: 'post',
        dataType: 'json',
        data: {
            'command': command,
            'id': document.getElementById('id').value,
            'name': document.getElementById('nameInput').value,
            'login': document.getElementById('loginInput').value,
            'password': document.getElementById('passInput').value,
            'email': document.getElementById('emailInput').value,
            'role': document.getElementById('roleSelect').value,
            'country': document.getElementById('countrySelect').value,
            'city': document.getElementById('citySelect').value
        },
        complete: function (data) {
            $("#newModal").modal('hide');
            console.log(data.responseText);
            if (data.responseText === 'Администратор') {
                showAllUsers();
            }
        }
    });
}

function showAllUsers() {
    $.ajax('./json', {
        method: 'post',
        dataType: 'json',
        data: {
            'command': 'showAll'
        },
        complete: function (data) {
            document.getElementById('usersTable').innerHTML = "";
            document.getElementById('usersTable').innerHTML = "<h3>Application users:</h3>";
            const users = JSON.parse(data.responseText);
            let $table = $("<table class='table table-bordered'>").appendTo($("#usersTable"))
                .append($("<thead>"))
                .append($("<tr>"))
                .append($("<th>").text('ID'))
                .append($("<th>").text('Name'))
                .append($("<th>").text('Login'))
                .append($("<th>").text('Password'))
                .append($("<th>").text('Email'))
                .append($("<th>").text('Role'))
                .append($("<th>").text('Country'))
                .append($("<th>").text('City'))
                .append($("<th>").text('Update'))
                .append($("<th>").text('Delete'));
            $.each(users, function (index, user) {
                $("<tr>").appendTo($table)
                    .append($("<td>").text(user.id))
                    .append($("<td>").text(user.name))
                    .append($("<td>").text(user.login))
                    .append($("<td>").text(user.password))
                    .append($("<td>").text(user.email))
                    .append($("<td>").text(user.role.type))
                    .append($("<td>").text(user.country.countryName))
                    .append($("<td>").text(user.city.cityName))
                    .append($('<td><a href="#" onclick="fillModal(' + user.id + ')">update</a>'))
                    .append($('<td><a href="#" onclick="deleteUser(' + user.id + ')">delete</a>'))
            })
        }
    })
}

function deleteUser(id) {
    $.ajax('./json', {
        method: 'post',
        dataType: 'json',
        data: {
            'command': 'delete',
            'id': id
        },
        complete: function () {
            document.getElementById('usersTable').innerHTML = "";
            showAllUsers();
        }
    })
}

$('#login').on('input keyup', function () {
        const login = document.getElementById('login').value;
        if (login !== '' && document.getElementById('loginErrorDiv') !== null) {
            document.getElementById('loginErrorDiv').innerHTML = '';
        }
    }
);

$('#pass').on('input keyup', function () {
        const pass = document.getElementById('pass').value;
        if (pass !== '' && document.getElementById('passErrorDiv') !== null) {
            document.getElementById('passErrorDiv').innerHTML = '';
        }
    }
);

function tryLogin() {
    const login = document.getElementById('login').value;
    const pass = document.getElementById('pass').value;
    if (login === '' && document.getElementById('loginLabelError') === null) {
        $('<div class="col-sm-12" id="loginErrorDiv">').appendTo($('#mainDivLogin'))
            .append($('<label id="loginLabelError" style="color: red; font-size: small">Please insert login.</label>'));
    }
    if (pass === '' && document.getElementById('passLabelError') === null) {
        $('<div class="col-sm-12" id="passErrorDiv">').appendTo($('#mainDivPass'))
            .append($('<label id="passLabelError" style="color: red; font-size: small">Please insert password.</label>'));
    }
    if (login !== '' && pass !== '') {
        $.ajax('./login', {
            method: 'post',
            dataType: 'json',
            data: {
                'login': login,
                'password': pass
            },
            complete: function (data) {
                if (data.responseText === 'OK') {
                    window.location = '/json';
                } else if (document.getElementById('appendError') === null) {
                    $('<div class="input-group-append" id="appendError">').appendTo($('#loginGroup'))
                        .append($('<div class="input-group-text">')
                            .append($('<i class="fa fa-times" style="color: red">')));
                    $('<div class="input-group-append" id="appendError">').appendTo($('#passGroup'))
                        .append($('<div class="input-group-text">')
                            .append($('<i class="fa fa-times" style="color: red">')));
                }
            }
        });
    }
}

function logout() {
    $.ajax('./json', {
        method: 'post',
        dataType: 'json',
        data: {'command': 'logout'},
        complete: function () {
            window.location = '/login';
        }
    })
}


