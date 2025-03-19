// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUsers();
  updateUserEmail();
  $('#dataTable').DataTable();
});

function updateUserEmail(){
document.getElementById('UserEmailTxt').outerHTML = localStorage.email;
}

 async function loadUsers(){


      const request = await fetch('api/users', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
           'Authorization' : localStorage.token
        },

      });
      const users = await request.json();

        let HtmlList = '';
        for(user of users){
            let celphone = user.telefono == '' ? '-': user.celphone;
            let HtmlUser = '<tr><td>'+ user.id +'</td><td>' + user.name + ' ' + user.lastName + '</td><td>'+ user.email +'</td><td>'+
                 celphone + '</td><td><a href="#" onclick="deleteUsers('+ user.id + ')" class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-trash"></i> </a></td>'

            HtmlList+=HtmlUser;
        }



      document.querySelector('#users, tbody').innerHTML = HtmlList;
}

 async function deleteUsers(id){

    if(!confirm("Are you sure you want to delete this user?")){
        return;
    }
     const request = await fetch('api/users/' + id, {
            method: 'DELETE',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
              'Authorization' : localStorage.token
            },
          });

          location.reload();
      }


