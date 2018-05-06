var app = angular.module("demo", []);

app.controller("ContestInPalaceCtrl", function($scope, $http){
    var idToUpdate;

    $http.get('/api/culturepalace/showAll').then(function(response) {
        console.log(response);
        var culturePalaces = response.data;
        var select = document.getElementById('ContestInPalaceCulturePalace');
        var selectCulturePalaceUpd = document.getElementById('updateContestInPalaceCulturePalace');

        for (var i = 0; i < culturePalaces.length; i++) {
            var option = document.createElement("option");
            option.text = culturePalaces[i].name;
            option.value = culturePalaces[i].id;

            select.add(option);

            console.log(select);
        }

        for (var j = 0; j < culturePalaces.length; j++){
            var option2 = document.createElement("option");
            option2.text = culturePalaces[j].name;
            option2.value = culturePalaces[j].id;

            selectCulturePalaceUpd.add(option2);
            console.log(selectCulturePalaceUpd);
        }

        $http.get('/api/organizer/showAll').then(function(response){
            var organizers = response.data;
            var select = document.getElementById('ContestInPalaceOrganizer');
            var selectOrganizersUpd = document.getElementById('updateContestInPalaceOrganizer');

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
    $scope.contestInPalaces = [];
     $http.get('/api/contestinpalace/showall').then(function (response){
         time = performance.now() - time;
         window.alert("Виведення відбулося за " + time + " мс.");
        $scope.contestInPalaces=response.data;
        console.log(response);
    });

    this.deleteContestInPalace = function deleteContestInPalace(id){
        var time = performance.now();
        $http.post('/api/contestinpalace/delete?id=' + id).then(function(response){
            time = performance.now()- time;
            window.alert("Видалення відбулося за " + time + " мс.");
            window.location.reload();
            console.log(response);
        });
    };

    this.startCreateContestInPalace = function startCreateContestInPalace(){

    };

    this.createContestInPalace = function createContestInPalace(){
        /*window.alert(document.getElementById('ContestInPalaceCulturePalace').value);
        window.alert(document.getElementById('ContestInPalaceName').value);
        window.alert(document.getElementById('ContestInPalaceOrganizer').value);
        window.alert(document.getElementById('datePicker').value);*/
        var culturePalaceId = document.getElementById('ContestInPalaceCulturePalace').value;
        var name = document.getElementById('ContestInPalaceName').value;
        var organizerId = document.getElementById('ContestInPalaceOrganizer').value;
        var date = document.getElementById('datePicker').value;

        var request = {
            method: 'PUT',
            url: '/api/contestinpalace/insert',
            data: {
                name : name,
                culturePalaceId : culturePalaceId,
                organizerId: organizerId,
                date : date
            }
        };

        var time = performance.now();
        $http(request).then(function(response){
            time = performance.now() - time;
            window.alert("Створення відбулося за " + time + " мс.");
            window.location.reload();
        })

        //window.alert(date);
    };

    this.startUpdateContestInPalace = function startUpdateContestInPalace(id, name, culturePalaceId, organizerId, date) {
        idToUpdate = id;
        document.getElementById('updateContestInPalaceName').value = name;
        document.getElementById('updateContestInPalaceCulturePalace').value = culturePalaceId;
        document.getElementById('updateContestInPalaceOrganizer').value = organizerId;
        document.getElementById('updateDatePicker').value = date;

    };

    this.updateContestInPalace = function updateContestInPalace(){
        var culturePalaceId = document.getElementById('updateContestInPalaceCulturePalace').value;
        var name = document.getElementById('updateContestInPalaceName').value;
        var organizerId = document.getElementById('updateContestInPalaceOrganizer').value;
        var date = document.getElementById('updateDatePicker').value;

        var request = {
            method: 'POST',
            url : '/api/contestinpalace/update',
            data: {
                id: idToUpdate,
                culturePalaceId : culturePalaceId,
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



