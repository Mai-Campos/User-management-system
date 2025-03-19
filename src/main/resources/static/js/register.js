// Call the dataTables jQuery plugin
$(document).ready(function() {
//On ready
});

 async function register(){

      let data = {};
      data.name = document.querySelector("#name").value;
      data.lastName = document.querySelector("#lastName").value;
      data.email = document.querySelector("#email").value;
      data.celphone = document.querySelector("#celphone").value;
      data.password = document.querySelector("#password").value;

      let repeatPassword = document.querySelector("#repeatPassword").value;

      if(repeatPassword != data.password ){
         toastr.info("Passwords do not match");
        return;
      }



      const request = await fetch('api/users', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },

         body: JSON.stringify(data)

      });
      window.location.href = "users.html";

}




