var app = angular.module("demo", []);

app.controller("OrganizerCtrl", function($scope, $http){

    var idToUpdate;

    var time = performance.now();
    $scope.organizers = [];
     $http.get('/api/organizer/showAll').then(function (response){
         time = performance.now() - time;
         window.alert("Виведення відбулося за " + time + " мс.");
        $scope.organizers=response.data;
        console.log(response);
    });

    this.deleteOrganizer = function deleteOrganizer(id){
        var time = performance.now();
        $http.get('/api/organizer/delete?id=' + id).then(function(){
            time = performance.now() - time;
            window.alert("Видалення відбулося за " + time + " мс."):
            window.location.reload();
            console.log("deleted organizer with id " + id);
        });
    };

    this.createOrganizer = function createOrganizer(){
        var name = document.getElementById('organizerName').value;
        var time = performance.now();
        $http.get('/api/organizer/create?name=' + name).then(function(){
            time = performance.now() - time;
            window.alert("Створення відбулсоя за " + time + " мс.");
            window.location.reload();
        });
    };

    this.startUpdateOrganizer = function startUpdateOrganizer(id, name){
        document.getElementById('updateOrganizerName').value = name;

        idToUpdate = id;
    };

    this.updateOrganizer = function updateOrganizer(){
        var name = document.getElementById('updateOrganizerName').value;
        var request = {
            method: 'POST',
            url : '/api/organizer/update?id=' + idToUpdate,
            data: {
                name : name
            }
        };

        var time = performance.now();
        $http(request).then(function (response){
            time = performance.now() - time;
            window.alert("Оновлення відбулося за " + time + " мс.");
            window.location.reload();
            console.log(response);
        });
    }
});



