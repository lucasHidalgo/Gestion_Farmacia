
var ventaProducto = {
    Sucursal:null,
    Productos: new Array
};
  function initMap() {
    var marcadores = new Array;
    var uluru = {
        lat: -34.613089,
        lng: -58.499307
    };
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14,
        center: uluru
    });
    obtenerSucursales();
function obtenerSucursales() {
    fetch('./Sucursal')
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function(data) {
                    var i = 0;
                    //while por cada barrio
                    while (i < data.length) {                        
                        let barrio = data[i].Nombre;
                        //html de la cabeza del barrio
                        let barrioHtml = ` <div class="media"> <span class="message_userpic"><i class="fa fa-map-marker"></i> <span class="user-status bg-success "></span></span>
                              <div class="media-body">
                            <h6 class="mt-0 mb-1">${barrio} </h6>
                            <p class="description">`;
                    //for por cada sucursal dentro del barrio                    
                        for (let x = 0; x < data[i].Sucursales.length; x++) {
                            let sucursal = data[i].Sucursales[x];
                            // por cada sucursal agrego marcador del maps
                            agregarMarcador({lat: parseFloat(sucursal.Latitud),lng: parseFloat(sucursal.Longitud)}, sucursal.Nombre, sucursal.Id);
                            //agrego nombre,direccion y telefono de la sucursal
                             barrioHtml += `<a class="seleccionSucursal" data-id="${sucursal.Id}" data-lat="${sucursal.Latitud}" data-long="${sucursal.Longitud}"> 
                             ${sucursal.Nombre} , ${sucursal.Direccion} , ${sucursal.Telefono1}  </a><br>`;
                        }
                        //cuando no hay mas sucursales dentro del barrio, sigo con el siguiente barrio.
                         barrioHtml += `</p></div></div><hr>`;
                         document.querySelector("#sucursales").innerHTML += barrioHtml;                               
                        i++;
                    }                        
                      agregarEventosClickZoomMarcador();
                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });                      
}
//esta funcion, genera un click en las lista de sucursales las cuales haran zoom en el mapa.
function agregarEventosClickZoomMarcador(){
      var el = document.querySelectorAll('.seleccionSucursal');
        for(var i=0; i < el.length; i++){
            el[i].addEventListener('click', function () {
                // el sucursalId es el mismo que se le agrega como id al marcador
                let sucursalId = this.dataset.id;                                  
                 for(let x = 0; x < marcadores.length;x++){
                     if(marcadores[x].id == sucursalId){
                          map.setZoom(16);
                          map.panTo(marcadores[x].position);
                          ventaProducto.Sucursal = sucursalId;                          
                     }                     
                 }                                  
            }, false);
        }
}

    //funcion para agregar marcador en el mapa con su nombre
    function agregarMarcador(coordenadas, nombreSucursal, id) {
        var marker = new google.maps.Marker({
            position: coordenadas,
            map: map,
            id: id
        });
        var infoWindow = new google.maps.InfoWindow({
            content: '<h5> ' + nombreSucursal + '</h5>'
        });

        marker.addListener('click', function() {
            infoWindow.open(map, marker);
        });
        marcadores.push(marker);

    }                                        
}
