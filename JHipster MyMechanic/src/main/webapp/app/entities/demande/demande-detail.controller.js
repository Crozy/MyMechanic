(function() {
    'use strict';

    angular
        .module('myMechanicApp')
        .controller('DemandeDetailController', DemandeDetailController);

    DemandeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Demande'];

    function DemandeDetailController($scope, $rootScope, $stateParams, previousState, entity, Demande) {
        var vm = this;

        vm.demande = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('myMechanicApp:demandeUpdate', function(event, result) {
            vm.demande = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
