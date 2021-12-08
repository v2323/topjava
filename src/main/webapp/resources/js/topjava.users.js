const userAjaxUrl = "admin/users/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl
};

function enable(checkbox, id) {
    var enabled = checkbox.is(":checked");
    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl + "enable/" + id,
        data: "enable=" + enabled
    }).done(function () {
        $('#btn-on').click(function () {
            $('#checkbox').prop('checked', enabled);
            defaultUpdateTable();
        });
        $('#btn-off').click(function () {
            $('#checkbox').prop('checked', !enabled);
            defaultUpdateTable();
        });
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
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
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