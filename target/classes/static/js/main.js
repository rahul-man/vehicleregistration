$(document).ready(function() {
    console.log("page is ready");
    $('#registerForm').on('submit', function(event){
       event.preventDefault();
       var formData = {}
       formData["name"] = $("#name").val();
       formData["vehicleType"] = $("#vehicleType").val();
       formData["address"] = $("#address").val();
       formData["engineNo"] = $("#engineNo").val();
       formData["gender"] = $("#gender").val();
       formData["model"] = $("#model").val();
       formData["age"] = $("#age").val();
       formData["make"] = $("#make").val();
       formData["rtoOffice"] = $("#RTOOffice").val();
       formData["license"] = $("#DLNumber").val();
       formData["insuranceNo"] = $("#insuranceNumber").val();

       console.log(formData);

       $("#register").prop("disabled", false);
       $.ajax({
            type: 'POST',
            contentType: "application/json",
            url:"newRegistration",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function(data){
                console.log(data);
                $("#errors").html("Registration Successful");
                $("#register").prop("disabled", true);
            },
            error: function(data){
                console.log(data)
                $('#errors').html("Invalid Input");
                $("#register").prop("disabled", true);
            }
       });
    });
});

$(document).ready(function() {
      console.log("Inside Admin Action Page");
      var make = $('#myForm input[name="make"]').val();
      var plateNo = $('#myForm input[name="plateNo"]').val();
      console.log(make);
      console.log(plateNo);
      if(plateNo == ''){
        $("#approve").prop("disabled", false);
        $("#reject").prop("disabled", false);
      }else{
        $("#approve").prop("disabled", true);
        $("#reject").prop("disabled", true);
      }
});


const myStatus = document.getElementById("status");
myStatus.addEventListener("change",function() {
  console.log(myStatus);
  sessionStorage.setItem("statValue",this.value);
});
let val = sessionStorage.getItem("statValue");
if (val) myStatus.value=val;

const myRTO = document.getElementById("RTOOffice");
myRTO.addEventListener("change",function() {
  console.log(myRTO);
  sessionStorage.setItem("rtoValue",this.value);
});
let rtoVal = sessionStorage.getItem("rtoValue");
if (rtoVal) myRTO.value=rtoVal;

function logout(){
 sessionStorage.clear();
}

