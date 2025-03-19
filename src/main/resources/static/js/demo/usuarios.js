// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  actualizarEmailUsuario();
  $('#dataTable').DataTable();
});

function actualizarEmailUsuario(){
document.getElementById('emailUsuarioTxt').outerHTML = localStorage.email;
}

 async function cargarUsuarios(){

      const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
           'Authorization' : localStorage.token
        },

      });
      const usuarios = await request.json();

        let listadoHTML = '';
        for(usuario of usuarios){
            let telefono = usuario.telefono == '' ? '-': usuario.telefono
            let usuarioHtml = '<tr><td>'+ usuario.id +'</td><td>' + usuario.nombre + ' ' + usuario.apellido + '</td><td>'+ usuario.email +'</td><td>'+
                 telefono + '</td><td><a href="#" onclick="eliminarUsuarios('+ usuario.id + ')" class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-trash"></i> </a></td>'

            listadoHTML+=usuarioHtml;
        }

      document.querySelector('#usuarios, tbody').innerHTML = listadoHTML;
}

 async function eliminarUsuarios(id){

    if(!confirm("Desea eliminar este usuario?")){
        return;
    }
     const request = await fetch('api/usuario/' + id, {
            method: 'DELETE',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
              'Authorization' : localStorage.token
            },
          });

          location.reload();
      }
