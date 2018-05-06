var app = angular.module("demo", []);

app.controller("TheatrePerformanceCtrl", function($scope, $http){
    var idToUpdate;

    $http.get('/api/theatre/showAll').then(function(response) {
        console.log(response);
        var theatres = response.data;
        var select = document.getElementById('TheatrePerformanceTheatre');
        var selectTheatreUpd = document.getElementById('updateTheatrePerformanceTheatre');

        for (var i = 0; i < theatres.length; i++) {
            var option = document.createElement("option");
            option.text = theatres[i].name;
            option.value = theatres[i].id;

            select.add(option);

            console.log(select);
        }

        for (var j = 0; j < theatres.length; j++){
            var option2 = document.createElement("option");
            option2.text = theatres[j].name;
            option2.value = theatres[j].id;

            selectTheatreUpd.add(option2);
            console.log(selectTheatreUpd);
        }

        $http.get('/api/organizer/showAll').then(function(response){
            var organizers = response.data;
            var select = document.getElementById('TheatrePerformanceOrganizer');
            var selectOrganizersUpd = document.getElementById('updateTheatrePerformanceOrganizer');

            for (var i = 0; i < organizers.length; i++) {
                var option = document.createElement("option");
                option.text = organizers[i].name;
                option.value = organizers[i].id;

                select.add(option);

                console.log(select);
            }

            for (var j = 0; j < organizers.length; j++) {
                var option2 = document.createElement("option");
                option2.text = organizers[j].name;
                option2.value = organizers[j].id;

                selectOrganizersUpd.add(option2);

                console.log(selectOrganizersUpd);
            }
        });

    });

    var time = performance.now();
    $scope.theatrePerformances = [];
     $http.get('/api/theatreperformance/showall').then(function (response){
         time = performance.now() - time;
         window.alert("Виведення відбулося за " + time + " мс.");
        $scope.theatrePerformances=response.data;
        console.log(response);
    });

    this.deleteTheatrePerformance = function deleteTheatrePerformance(id){
        time = performance.now();
        $http.post('/api/theatreperformance/delete?id=' + id).then(function(response){
            time = performance.now() - time;
            window.alert("Видалення відбулося за " + time + " мс.");
            window.location.reload();
            console.log(response);
        });
    };

    this.startCreateTheatrePerformance = function startCreateTheatrePerformance(){

    };

    this.createTheatrePerformance = function createTheatrePerformance(){
        /*window.alert(document.getElementById('TheatrePerformanceTheatre').value);
        window.alert(document.getElementById('TheatrePerformanceName').value);
        window.alert(document.getElementById('TheatrePerformanceOrganizer').value);
        window.alert(document.getElementById('datePicker').value);*/
        var theatreId = document.getElementById('TheatrePerformanceTheatre').value;
        var name = document.getElementById('TheatrePerformanceName').value;
        var organizerId = document.getElementById('TheatrePerformanceOrganizer').value;
        var date = document.getElementById('datePicker').value;

        var request = {
            method: 'PUT',
            url: '/api/theatreperformance/insert',
            data: {
                name : name,
                theatreId : theatreId,
                organizerId: organizerId,
                date : date
            }
        };

        time = performance.now();
        $http(request).then(function(response){
            time = performance.now() - time;
            window.alert("Створення відбулося за " + time + " мс.");
            window.location.reload();
        });

        //window.alert(date);
    };

    this.startUpdateTheatrePerformance = function startUpdateTheatrePerformance(id, name, theatreId, organizerId, date) {
        idToUpdate = id;
        document.getElementById('updateTheatrePerformanceName').value = name;
        document.getElementById('updateTheatrePerformanceTheatre').value = theatreId;
        document.getElementById('updateTheatrePerformanceOrganizer').value = organizerId;
        document.getElementById('updateDatePicker').value = date;

    };

    this.updateTheatrePerformance = function updateTheatrePerformance(){
        var theatreId = document.getElementById('updateTheatrePerformanceTheatre').value;
        var name = document.getElementById('updateTheatrePerformanceName').value;
        var organizerId = document.getElementById('updateTheatrePerformanceOrganizer').value;
        var date = document.getElementById('updateDatePicker').value;

        var request = {
            method: 'POST',
            url : '/api/theatreperformance/update',
            data: {
                id: idToUpdate,
                theatreId : theatreId,
                name: name,
                organizerId: organizerId,
                date: date
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



