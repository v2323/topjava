const mealAjaxUrl = "profile/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: mealAjaxUrl + "filter",
            data: $("#filter").serialize()
        }).done(updateTableByData);
    }
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable(
        {
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            return date.replace("T"," ").substring(0, 16);
                        }
                        return date;
                    }
                },
                {
                    "data": "description",
                },
                {
                    "data": "calories"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (!data.enabled) {
                    $(row).attr("data-meal-excess", data.excess);
                }
            }
        }
    );
});

var startDate = $('#startDate');
var endDate = $('#endDate');
startDate.datetimepicker({
    format: 'Y/m/d',
    onShow: function (ct) {
        this.setOptions({
            maxDate: endDate.val() ? endDate.val() : false
        })
    },
    timepicker: false
});
endDate.datetimepicker({
    format: 'Y/m/d',
    onShow: function (ct) {
        this.setOptions({
            minDate: startDate.val() ? startDate.val() : false
        })
    },
    timepicker: false
});