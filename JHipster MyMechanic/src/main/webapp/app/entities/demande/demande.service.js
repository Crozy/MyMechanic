(function() {
    'use strict';
    angular
        .module('myMechanicApp')
        .factory('Demande', Demande);

    Demande.$inject = ['$resource'];

    function Demande ($resource) {
        var resourceUrl =  'api/demandes/:id';

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
