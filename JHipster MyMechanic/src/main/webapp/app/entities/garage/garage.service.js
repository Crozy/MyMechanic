(function() {
    'use strict';
    angular
        .module('myMechanicApp')
        .factory('Garage', Garage);

    Garage.$inject = ['$resource'];

    function Garage ($resource) {
        var resourceUrl =  'api/garages/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
