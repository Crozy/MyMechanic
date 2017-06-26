(function() {
    'use strict';

    angular
        .module('myMechanicApp')
        .controller('GarageDeleteController',GarageDeleteController);

    GarageDeleteController.$inject = ['$uibModalInstance', 'entity', 'Garage'];

    function GarageDeleteController($uibModalInstance, entity, Garage) {
        var vm = this;

        vm.garage = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Garage.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
