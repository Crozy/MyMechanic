(function() {
    'use strict';

    angular
        .module('myMechanicApp')
        .controller('GarageDetailController', GarageDetailController);

    GarageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Garage'];

    function GarageDetailController($scope, $rootScope, $stateParams, previousState, entity, Garage) {
        var vm = this;

        vm.garage = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('myMechanicApp:garageUpdate', function(event, result) {
            vm.garage = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
