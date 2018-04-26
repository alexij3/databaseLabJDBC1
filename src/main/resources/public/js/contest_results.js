var app = angular.module("demo", []);

app.controller("ContestResultsCtrl", function($scope, $http){
    $scope.contestResults = [];
    $http.get('/api/contestresults/showall').then(function(response){
        $scope.contestResults = response.data;

        $http.get('/api/contestinpalace/showall').then(function(response){
            var contests = response.data;
            var select = document.getElementById('contestResultContest');
            var selectContestUpd = document.getElementById('updateContestResultContest');

            for (var i = 0; i < contests.length; i++) {
                var option = document.createElement("option");
                option.text = contests[i].name;
                option.value = contests[i].id;

                select.add(option);

                console.log(select);
            }

            for (var j = 0; j < contests.length; j++){
                var option2 = document.createElement("option");
                option2.text = contests[j].name;
                option2.value = contests[j].id;

                selectContestUpd.add(option2);

                console.log(selectContestUpd);
            }

        });

        $http.get('/api/artist/showall').then(function(response){
            var artists = response.data;
            var select = document.getElementById('contestResultArtist');
            var selectArtistUpd = document.getElementById('updateContestResultArtist');

            for (var i = 0; i < artists.length; i++) {
                var option = document.createElement("option");
                option.text = artists[i].name;
                option.value = artists[i].id;

                select.add(option);

                console.log(select);
            }

            for (var j = 0; j < artists.length; j++) {
                var option2 = document.createElement("option");
                option2.text = artists[j].name;
                option2.value = artists[j].id;

                selectArtistUpd.add(option2);

                console.log(selectArtistUpd);
            }
        });
    });



    this.createContestResult = function createContestResult(){
        var contestId = document.getElementById('contestResultContest').value;
        var artistId = document.getElementById('contestResultArtist').value;
        var place = document.getElementById('place').value;
        var isWinner = document.getElementById('isWinner').value;

        var request = {
            method: 'PUT',
            url : '/api/contestresults/insert',
            data :{
                contestId : contestId,
                artistId : artistId,
                place : place,
                isWinner : isWinner
            }
        };

        $http(request).then(function(response){
            console.log(response);
            window.location.reload();
        })
    }
});



