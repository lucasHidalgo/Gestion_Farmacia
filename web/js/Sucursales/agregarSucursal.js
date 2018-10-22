var nuevaSucursal = {};
  function initMap() {    
    var uluru = {
        lat: -34.613089,
        lng: -58.499307
    };
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14,
        center: uluru
    });
    var infoWindow = new google.maps.InfoWindow;
    var geocoder = new google.maps.Geocoder();

      document.getElementById('buscarDireccion').addEventListener('click', function() {
        geocodeAddress(geocoder, map);
      });

      let autocompletar = new google.maps.places.Autocomplete(document.querySelector('#inDireccion'))      
}

   


function geocodeAddress(geocoder, resultsMap) {
    var address = document.querySelector('#inDireccion').value;
    geocoder.geocode({'address': address ,'region':'AR'}, function(results, status) {
      if (status === 'OK') {
        resultsMap.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
          map: resultsMap,
          position: results[0].geometry.location
        });
        nuevaSucursal.Latitud = results[0].geometry.location.lat();
        nuevaSucursal.Longitud = results[0].geometry.location.lng();
        nuevaSucursal.Direccion = address;        
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
  }

  document.querySelector('#agregarSucursal').addEventListener('click',function(){
      nuevaSucursal.Nombre = document.querySelector('#inNombre').value;
      nuevaSucursal.Barrio = document.querySelector("#inBarrio").value;
      nuevaSucursal.Telefono1 = document.querySelector('#inTelefono1').value;
      nuevaSucursal.telefono2 = document.querySelector('#inTelefono2').value;
      console.log(nuevaSucursal);
  },false)

  


