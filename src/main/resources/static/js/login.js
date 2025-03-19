// Call the dataTables jQuery plugin
$(document).ready(function() {
//On ready
});

 async function login(){

      let data = {};
      data.email = document.querySelector("#email").value;
      data.password = document.querySelector("#password").value;


      const request = await fetch('api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },

         body: JSON.stringify(data)

      });

      const response = await request.text();

      if(response != "Fail"){
        localStorage.token = response;
        localStorage.email = data.email;

        window.location.href = "users.html"
      }else{
        swal("Incorrect credentials.", "Please Try Again");
      }

}




