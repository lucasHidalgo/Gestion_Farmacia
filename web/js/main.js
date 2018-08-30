
$('.toggle').click(function(){
   $('.formulario').animate({
       height: "toggle",
       'padding-top': 'toggle',
       'padding-bottom': 'toggle',
       opacity: 'toggle'
   }); 
});


function logIn(){
     let usuario = document.querySelector("#usuarioEmail").value;
    let clave = document.querySelector("#usuarioClave").value;
   const data =JSON.stringify({
       usuarioMail:usuario,
       clave:clave
   });
   console.log(data);
   /*
    $.ajax({
    url: './LoginServlet',
    type: 'POST', 
    contentType: 'application/json',
    data: data,
    success: function(result) {
        console.log(result);            
    }

});*/
    
    fetch("./LoginServlet", {
        method: "post",
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },  
  body: data
    })  
    .then( (response) => { 
        response.json()
        .then((respJson)=>{
            //respuesta true o false dependiendo si el usuario fue validado.
            if(respJson[0]){
              window.location.replace("Reservas.html");
            }else{
               alert("usuario incorrecto"); 
           }          
        });
    })
    .catch((err)=>{
            console.log(err);
        });  
}
