var app = angular.module("demo", []);

app.controller("ImpresarioGenreCtrl", function($scope, $http) {
    var impresarioId;
    var genres = [];

    $scope.impresarios = [];
    $http.get('/api/impresariogenre/showgenres').then(function (response) {
        $scope.impresarios = response.data;
    });


    this.startAddGenre = function startAddGenre(id, name) {
        impresarioId = id;
        document.getElementById('labelImpresario').innerHTML = name;


    };

    this.addGenre = function addGenre(){
        genres = $scope.selectedGenres;
        for (var i = 0; i < genres.length; i++){
            var request = {
                method: 'PUT',
                url: '/api/impresariogenre/insertgenre?id=' + impresarioId,
                data: {
                    impresario : null,
                    genre : genres[i]
                }
            };

            $http(request).then(function(){
                window.location.reload();
            });
        }
    };

    this.startUpdateGenres = function startUpdateGenres(id, name){
        impresarioId = id;
        document.getElementById('updLabelImpresario').innerHTML = name;
        document.getElementById('selectedGenre').value = "Комедія";

    };

    this.updateGenre = function updateGenre(){
        var genre = document.getElementById('selectGenre').value;
        var request = {
            method: 'PUT',
            url: '/api/impresariogenre/updategenre?id=' + impresarioId,
            data: {
                impresario : null,
                genre : genre
            }
        };

        $http(request).then(function(){
            window.location.reload();
        });
    };

    this.deleteGenre = function deleteGenre(impresarioId, genreName){
        var request = {
            method: 'POST',
            url: '/api/impresariogenre/deletegenre?id=' + impresarioId,
            data: {
                impresario : null,
                genre : genreName
            }
        };

        $http(request).then(function(response){
            window.location.reload();
        });
    }
});



