const mealsAjaxUrl = "profile/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealsAjaxUrl,
    updateTable1: function () {
        $.ajax({
            url: ctx.ajaxUrl + "filter",
            type: "GET",
            data: $('#filter').serialize()
        }).done(updateTable);
    }
}

function updateTable() {
    $.get(ctx.ajaxUrl, function (data) {
        ctx.datatableApi.clear().rows.add(data).draw();
    });
}

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});