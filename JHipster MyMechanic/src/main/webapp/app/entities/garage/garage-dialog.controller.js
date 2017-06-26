(function() {
    'use strict';

    angular
        .module('myMechanicApp')
        .controller('GarageDialogController', GarageDialogController);

    GarageDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Garage'];

    function GarageDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Garage) {
        var vm = this;

        vm.garage = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.garage.id !== null) {
                Garage.update(vm.garage, onSaveSuccess, onSaveError);
            } else {
                Garage.save(vm.garage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('myMechanicApp:garageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
