var app = angular.module("demo", []);

app.controller("CinemaCtrl", function($scope, $http){
    var idToUpdate;

    var time = performance.now();
    $scope.cinemas = [];
     $http.get('/api/cinema/showall').then(function (response){
         time = performance.now() - time;
         window.alert("Виведення відбулося за " + time + " мс.");
        $scope.cinemas=response.data;
        console.log(response);
    });

    this.deleteCinema = function deleteCinema(id){
        var time = performance.now();
        $http.get('/api/cinema/delete?id=' + id).then(function(){
            time = performance.now() - time;
            window.alert("Видалення відбулося за " + time + " мс.");
            window.location.reload();
            console.log("deleted Cinema with id " + id);
        });
    };

    this.createCinema = function createCinema(){
        var name = document.getElementById('CinemaName').value;
        var address = document.getElementById('CinemaAddress').value;
        var screenSize = document.getElementById('CinemaScreenSize').value;

        var createRequest ={
            method: 'POST',
            url: '/api/cinema/create',
            data: {
                name : name,
                address : address,
                screenSize : screenSize
            }
        };

        var time = performance.now();
        $http(createRequest).success(
            console.log('created Cinema with name ' + name)
        ).then(function(){
            time = performance.now() - time;
            window.alert("Створення відбулося за " + time + " мс.");
            window.location.reload();
        });
    };

    this.startUpdateCinema = function startUpdateCinema(id, name, address, screenSize){
        document.getElementById('updateCinemaName').value = name;
        document.getElementById('updateCinemaAddress').value = address;
        document.getElementById('updateCinemaScreenSize').value = screenSize;

        idToUpdate = id;
    };

    this.updateCinema = function updateCinema(){
        var name = document.getElementById('updateCinemaName').value;
        var address = document.getElementById('updateCinemaAddress').value;
        var screenSize = document.getElementById('updateCinemaScreenSize').value;

        var request = {
            method: 'POST',
            url : '/api/cinema/update?id=' + idToUpdate,
            data: {
                name : name,
                address : address,
                screenSize : screenSize
            }
        };

        var time = performance.now();
        $http(request).then(function (response){
            time = performance.now() - time;
            window.alert("Оновлення відбулося за " + time + " мс.");
            console.log(response);
            window.location.reload();
        });
    }
});



