$("#brandSelect").select2({
    ajax: {
        url: "./json",
        method: 'GET',
        dataType: 'json',
        data: function (params) {
            return {
                action: 'fillBrand',
                query: params.term,
            };
        },
        processResults: function (data) {
            return {
                results: $.map(data, function (item) {
                    return {
                        id: item.id,
                        name: item.modelName
                    }
                })
            };
        },
    },
    placeholder: "Брэнд",
    //minimumResultsForSearch: Infinity,
    allowClear: true
});
//
// $("#modelSelect").select2({
//     ajax: ajax('./json', {action: 'fillModel', id: $("#brandSelect").val()}),
//     placeholder: "Модель",
//     allowClear: true
// });