// Call the dataTables jQuery plugin
$(document).ready(function() {
//On ready
});

 async function registrarUsuario(){

      let datos = {};
      datos.nombre = document.querySelector("#nombre").value;
      datos.apellido = document.querySelector("#apellido").value;
      datos.email = document.querySelector("#email").value;
      datos.telefono = document.querySelector("#telefono").value;
      datos.password = document.querySelector("#password").value;

      let repetirPassword = document.querySelector("#repetirPassword").value;

      if(repetirPassword != datos.password ){
         toastr.info("Las contraseñas no coinciden");
        return;
      }



      const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },

         body: JSON.stringify(datos)

      });
      window.location.href = "usuarios.html";

}




