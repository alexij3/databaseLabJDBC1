var app = angular.module("demo", []);

app.controller("ArtistImpresarioCtrl", function($scope, $http) {
    var artistId;
    var impresariosToAdd = [];

    $scope.artists = [];
    $http.get('/api/artistimpresario/showall').then(function(response){
       $scope.artists = response.data;
    });

    $http.get('/api/impresario/showAll').then(function(response) {
        var impresarios = response.data;
        var select = document.getElementById('selectImpresarios');

        for (var i = 0; i < impresarios.length; i++) {
            var option = document.createElement("option");
            option.text = impresarios[i].name;
            option.value = impresarios[i].id;

            select.add(option);

            console.log(select);
        }
    });

    this.startAddImpresario = function startAddImpresario(id, name){
        artistId = id;
        impresarios = $scope.selectedImpresarios;
        document.getElementById('artistLabelName').innerHTML = name;
    };

    this.addImpresario = function addImpresario(){
        impresariosToAdd = $scope.selectedImpresarios;
        window.alert(impresariosToAdd[0].value);
        for (var i = 0; i < impresariosToAdd.length; i++){
            var request = {
                method: 'PUT',
                url: '/api/artistimpresario/insert?id=' + artistId,
                data: {
                    artist : null,
                    impresario : impresariosToAdd[i]
                }
            };

            $http(request).then(function(){
                window.location.reload();
            });
        }
    }
});