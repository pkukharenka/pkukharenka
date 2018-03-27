let showStrategy = false;

function ajax(url, parameters, func) {
    $.ajax(url, {
        method: 'post',
        dataType: 'json',
        data: parameters,
        success: function () {
            console.log('OK')
        },
        complete: function (data) {
            func(data)
        }
    })
}

function showAll(done) {
    $("#itemList").empty();
    ajax('./item', {
        'command': 'showAll',
        'done': done
    }, function create(data) {
        var items = JSON.parse(data.responseText);
        let table = $('#itemList').append($("<h3>").text("Все задачи"))
            .append($("<table class='table table-bordered'>")
                .append($("<thead>")
                    .append($("<tr>")
                        .append($("<th>").text('#'))
                        .append($("<th style='width: 70%'>").text('Description'))
                        .append($("<th>").text('Create date'))
                        .append($("<th>").text('Done'))
                        .append($("<th>").text('Delete'))
                    )
                )
                .append($("<tbody id='itemsBody'>")));
        $.each(items, function (index, item) {
            let tr = $("<tr>").appendTo($("#itemsBody"))
                .append($("<td>").text(index + 1))
                .append($("<td>").text(item.desc))
                .append($("<td>").text(item.created));
            if (item.done) {
                tr.append($("<td style='text-align: center'>")
                    .append("<input type='checkbox' id='itemBox' value='" + item.id + "' class='form-check-input' checked>"));
            } else {
                tr.append($("<td style='text-align: center'>")
                    .append("<input type='checkbox' id='itemBox' value='" + item.id + "' class='form-check-input'>"));
            }
            tr.append($("<td>")
                .append("<input type='button' value='X' onclick='deleteItem(" + item.id + ")'>"))
        })
    })
}

$("#showBtn").on('click', function () {
    showAll(showStrategy);
});

$(document).on('change', '#showing', function () {
    var div = $("#itemList");
    console.log(div.html().trim());
    if (div.html().trim() === '') {
        showStrategy = !!this.checked;
    } else {
        div.empty();
        if (this.checked) {
            showStrategy = true;
            showAll(true)
        } else {
            showStrategy = false;
            showAll(false)
        }
    }
});

$(document).on('change', '#itemBox', function () {
    if (this.checked) {
        updateDone(this.value, true)
    } else {
        updateDone(this.value, false)
    }
});


function updateDone(id, done) {
    ajax('./item', {
        'command': 'edit',
        'id': id,
        'done': done
    }, function (data) {
    })
}

function add() {
    let desc = document.getElementById('descArea').value;
    if (desc !== '') {
        $("#descArea").css('border-color', 'black').css('border-width', 'thin')
        ajax('./item', {
            'command': 'add',
            'desc': desc
        }, function (data) {
            $("#itemList").empty();
            showAll(showStrategy);
            document.getElementById('descArea').value = '';
        })
    } else {
        $("#descArea").css('border-color', 'red').css('border-width', 'medium')
    }
}

function deleteItem(id) {
    ajax('./item', {
        'command': 'delete',
        'id': id
    }, function (data) {
        $("#itemList").empty();
        showAll(showStrategy);
    })
}

