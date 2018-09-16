
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
   const data =JSON.stringify({usuarioMail:usuario, clave:clave });       
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
            //hay que rellenar como si fuera UN SOLO div, el while se va a encargar del resto
            //vas a tener que seleccionar el tbody con el querySelector, y agregarle +innerHTML
            //y con eso agregar LO QUE ESTA DENTRO DEL <TR> incluido
            //vas a tener que reemplazar, el SRC dela img por respJson.img, por ejemplo:
            //<img src=respJson.img />
            //vas a tener que reemplazar, descripcion, via, nombre,precio
            //un while, copiar del fetch de sucursales
               //selector.innerHTML += "<tr><td> elementos div con clases  </td></tr>"
               //} de while
            if(respJson[0]){
              window.location.replace("Reservas.html");
            }else{
               alert("usuario incorrecto"); 
           }          
        });
    }).catch((err)=>{
            console.log(err);
        });  
}
