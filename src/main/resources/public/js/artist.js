var app = angular.module("demo", []);

app.controller("ArtistCtrl", function($scope, $http){
    var idToUpdate;
    var genres = [];

    $scope.artists = [];
    var time = performance.now();
     $http.get('/api/artist/showall').then(function (response){
        $scope.artists=response.data;
        time = performance.now() - time;
        window.alert("Виведення відбулося за " + time + " мс.");
        console.log(response);
    });

    this.deleteArtist = function deleteArtist(id){
        var time = performance.now();
        $http.get('/api/artist/delete?id=' + id).then(function(){
            window.alert("Видалено артиста з ІД " + id);
            time = performance.now() - time;
            window.alert("Видалення відбулося за " + time + " мс.");
            window.location.reload();
        });
    };

    this.createArtist = function createArtist(){
        console.log("In createartist()");
        var id = document.getElementById('artistId').value;
        var name = document.getElementById('artistName').value;
        genres = $scope.selectedGenres;

        var createRequest = {
            method: 'PUT',
            url: '/api/artist/create?id=' + id,
            data: {
                name: name
            }
        };

        var time = performance.now();
        $http(createRequest).then(function(response){
            time = performance.now() - time;
            window.alert("Створення відбулося за " + time + " мс.");
            genres = [];
            window.location.reload();
            console.log(response);
        });
    };

    this.startUpdateArtist = function startUpdateArtist(id, name){
        document.getElementById('updateArtistName').value = name;
        idToUpdate = id;
    };

    this.updateArtist = function updateArtist(){
        var time = performance.now();
        var name = document.getElementById('updateArtistName').value;
        var request = {
            method: 'POST',
            url : '/api/artist/update?id=' + idToUpdate,
            data: {
                name : name
            }
        };

        $http(request).then(function (response){
            time = performance.now() - time;
            window.alert("Оновлення відбулося за " + time + " мс.");
            console.log(response);
            window.location.reload();
        });
    };

    this.getArtist = function getArtist(id){
        $http.get('/api/artist/get?id=' + id);
    }
});



