var app = angular.module("demo", []);

app.controller("ConcertInHallCtrl", function($scope, $http){
    var idToUpdate;

    $http.get('/api/cinema/showall').then(function(response) {
        console.log(response);
        var cinemas = response.data;
        var select = document.getElementById('ConcertInHallCinema');
        var selectCinemaUpd = document.getElementById('updateConcertInHallCinema');

        for (var i = 0; i < cinemas.length; i++) {
            var option = document.createElement("option");
            option.text = cinemas[i].name;
            option.value = cinemas[i].id;

            select.add(option);

            console.log(select);
        }

        for (var j = 0; j < cinemas.length; j++){
            var option2 = document.createElement("option");
            option2.text = cinemas[j].name;
            option2.value = cinemas[j].id;

            selectCinemaUpd.add(option2);
            console.log(selectCinemaUpd);
        }


    });

    $scope.concertInHalls = [];
     $http.get('/api/concertinhall/showall').then(function (response){
        $scope.concertInHalls=response.data;
        console.log(response);
    });

    this.deleteConcertInHall = function deleteConcertInHall(id){
        $http.get('/api/cinemamovie/delete?id=' + id).then(function(response){
            console.log(response);
            window.location.reload();

        });
    };

    this.startCreateConcertInHall = function startCreateConcertInHall(){

    };

    this.createConcertInHall = function createConcertInHall(){
        var concertHallId = document.getElementById('ConcertInHallConcertHall').value;
        var name = document.getElementById('ConcertInHallName').value;
        var genre = document.getElementById('ConcertInHallGenre').value;
        var date = document.getElementById('datePicker').value;

        var request = {
            method: 'POST',
            url: '/api/cinemamovie/create?id=' + id,
            data: {
                name : name,
                genre: genre,
                cinemaId: concertHallId,
                date: date
            }
        };

        $http(request).then(function(response){
            console.log(response);
        });

        window.location.reload();
    };

    this.startUpdateConcertInHall = function startUpdateConcertInHall(id, name, genre, cinemaId, date) {
        idToUpdate = id;
            document.getElementById('updateConcertInHallName').value = name;
            document.getElementById('updateConcertInHallGenre').value = genre;
            document.getElementById('updateConcertInHallCinema').value = cinemaId;
            document.getElementById('updateDatePicker').value = date;

    };

    this.updateConcertInHall = function updateConcertInHall(){
        var name = document.getElementById('updateConcertInHallName').value;
        var genre = document.getElementById('updateConcertInHallGenre').value;
        var cinemaId = document.getElementById('updateConcertInHallCinema').value;
        var date = document.getElementById('updateDatePicker').value;

        var request = {
            method: 'POST',
            url : '/api/cinemamovie/update?id=' + idToUpdate,
            data: {
                name : name,
                genre : genre,
                cinemaId : cinemaId,
                date : date
            }
        };

        $http(request).then(function (response){
            console.log(response);
        });

        window.location.reload();
    }
});



