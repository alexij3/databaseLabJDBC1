var app = angular.module("demo", []);

app.controller("ArtistGenreCtrl", function($scope, $http) {
    var artistId;
    var oldGenre;
    var genres = [];

    //var time = performance.now();
    $scope.artists = [];
    $http.get('/api/artistgenre/showgenres').then(function (response) {
      //  time = performance.now() - time;
       // window.alert("Виведення відбулося за " + time + " мс.");
        $scope.artists = response.data;
    });

    $http.get('/api/artist/showall').then(function(response){
        var artists = response.data;
        var select = document.getElementById('selectedArtist');
        for (var i = 0; i < artists.length; i++){
            var option = document.createElement("option");
            option.text = artists[i].name;
            option.value = artists[i].id;

            select.add(option);
        }
    });

    this.addGenre = function addGenre(){
        var time;
        var time2;
        artistId = document.getElementById('selectedArtist').value;
        genres = $scope.selectedGenres;
        for (var i = 0; i < genres.length; i++){
            var request = {
                method: 'PUT',
                url: '/api/artistgenre/insertgenre?id=' + artistId,
                data: {
                    genre : genres[i]
                }
            };

            time = performance.now();
            $http(request).then(function(response){
                time2 = performance.now() - time;
                window.alert("Створення відбулося за " + time2 + " мс.");
                window.location.reload();
            });
        }
    };

    this.startUpdateGenres = function startUpdateGenres(id, name, genre){
        artistId = id;
        oldGenre = genre;
        document.getElementById('updLabelArtist').innerHTML = name;
        document.getElementById('selectedGenre').value = genre;

    };

    this.updateGenre = function updateGenre(){
        var time = performance.now();
        var genre = document.getElementById('selectGenre').value;
        var request = {
            method: 'PUT',
            url: '/api/artistgenre/updategenre?oldGenre=' + oldGenre,
            data: {
                artistId : artistId,
                genre : genre
            }
        };

        $http(request).then(function(){
            time = performance.now() - time;
            window.alert("Оновлення відбулося за " + time + " мс.");
            window.location.reload();
        });
    };

    this.deleteGenre = function deleteGenre(artistId, genreName){
        var time = performance.now();
        var request = {
            method: 'POST',
            url: '/api/artistgenre/deletegenre?id=' + artistId,
            data: {
                artist : null,
                genre : genreName
            }
        };

        $http(request).then(function(response){
            time = performance.now() - time;
            window.alert("Видалення відбулося за " + time + " мс.");
            window.location.reload();
        });
    }
});



