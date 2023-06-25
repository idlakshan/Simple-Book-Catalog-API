createNewId();
loadData();

var newId;

function clearFields(){
     $("#txtId").val("");
     $("#txtTitle").val("");
     $("#txtAuthor").val("");
     $("#txtEdition").val("");
     $("#txtQty").val("");
     $("#txtPrice").val("");
     $("#txtFile").val("")
}

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
            //alert("Successfully Uploaded");
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
            clearFields();
            createNewId();
            loadData();

        },
        error: function (error) {
            let cause = JSON.parse(error.responseText).message;
            alert(cause);
        }
    });
}

function loadData(){
    $("#tblBooks").empty();
    $.ajax({
        url: "http://localhost:8090/api/v1/books",
        method: "get",
        contentType: "application/json",
        dataType: 'json',
        success: function (res) {

           /* for(let tempData of res.data){
                console.log(tempData);
                let row=$("#tblBooks").insertRow();

                let cell1=row.insertCell();
                let cell2=row.insertCell();
                let cell3=row.insertCell();
                let cell4=row.insertCell();
                let cell5=row.insertCell();
                let cell6=row.insertCell();
                let cell7=row.insertCell();
                let cell8=row.insertCell();

                cell1.textContent=`$(tempData.id)`;
                cell2.textContent=`$(tempData.title)`;
                cell3.textContent=`$(tempData.author)`;
                cell4.textContent=`$(tempData.edition)`;
                cell5.textContent=`$(tempData.qty)`;
                cell6.textContent=`$(tempData.price)`;
                cell7.textContent=``;
                cell8.textContent=``;



            }*/

            let data=res.data;
            console.log(data)
            data.forEach((record)=>{

                let btnUpdate=$('<button>').text("Update")
                btnUpdate.addClass('btnTblUpdate')
                btnUpdate.addClass('warning')
                let btnDelete=$('<button>').text("Delete")
                btnDelete.addClass('btnTblDelete')
                btnDelete.addClass('danger')

                btnDelete.click(()=>{
                    deleteBook(record.id);
                })

                let row=$('<tr>');
                let cell1=$('<td>').text(record.id)
                let cell2=$('<td>').text(record.title)
                let cell3=$('<td>').text(record.author)
                let cell4=$('<td>').text(record.edition)
                let cell5=$('<td>').text(record.qty)
                let cell6=$('<td>').text(record.price)
                let cell7=$('<td>').append(btnUpdate)
                let cell8=$('<td>').append(btnDelete)


                row.append(cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8);
                $("#tblBooks").append(row);

            })

        },
        error: function (error) {
            let cause = JSON.parse(error.responseText).message;
            alert(cause);
        }
    });
}

function deleteBook(id){
    console.log(id);
}