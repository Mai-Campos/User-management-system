// Call the dataTables jQuery plugin
$(document).ready(function() {
//On ready
});

 async function iniciarSesion(){

      let datos = {};
      datos.email = document.querySelector("#email").value;
      datos.password = document.querySelector("#password").value;

      const request = await fetch('api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },

         body: JSON.stringify(datos)

      });

      const respuesta = await request.text();

      if(respuesta != "Fail"){
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = "usuarios.html"
      }else{
        swal("Credenciales incorrectas.", "Por favor intente nuevamente");
      }

}




