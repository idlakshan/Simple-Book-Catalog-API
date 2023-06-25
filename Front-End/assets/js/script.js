createNewId();


var newId;

function createNewId(){
    $.ajax({
        url: "http://localhost:8090/api/v1/books/newId",
        dataType: "json",
        async: false,
        success: function (resp) {
            let lastBookId = resp.data;

            let tempId = parseInt(lastBookId.split("-")[1]);
            tempId = tempId + 1;

            if (tempId <= 9) {

                newId = "B-00" + tempId;

            } else if (tempId < 99) {
                newId = "B-0" + tempId;

            } else {
                newId = "B-" + tempId;

            }
            $("#txtId").val(newId);
        },
        error: function (error) {
            alert("Error When Getting Last Book ID From Back End!!");
        }
    });
}

$("#btnSaveUpdate").click(function (){
    uploadImgAndSaveDetails();
});

function uploadImgAndSaveDetails(){
    let file=$("#txtFile")[0].files[0];
    let fileName = newId + "_BookImage_" +  $("#txtFile")[0].files[0].name;

    var data = new FormData();
    data.append("bookImg",file, fileName);

    $.ajax({
        url: "http://localhost:8090/api/v1/books/image",
        method: 'post',
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (res) {
            alert("Successfully Uploaded");
            saveBook();
        },
        error: function (error) {
            let cause = JSON.parse(error.responseText).message;
            alert(cause);
        }
    });
}

function saveBook(){
    let id = $("#txtId").val();
    let title = $("#txtTitle").val();
    let author = $("#txtAuthor").val();
    let edition = $("#txtEdition").val();
    let qty = $("#txtQty").val();
    let price = $("#txtPrice").val();

    var book={
        id:id,
        title:title,
        author:author,
        edition:edition,
        qty:qty,
        price:price,
        bookImg: "",
    }

    $.ajax({
        url: "http://localhost:8090/api/v1/books",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(book),
        dataType: 'json',
        success: function (res) {
            alert(res.message);

        },
        error: function (error) {
            let cause = JSON.parse(error.responseText).message;
            alert(cause);
        }
    });
}