(function() {
    'use strict';

    angular
        .module('myMechanicApp')
        .controller('DemandeDialogController', DemandeDialogController);

    DemandeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Demande'];

    function DemandeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Demande) {
        var vm = this;

        vm.demande = entity;
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
            if (vm.demande.id !== null) {
                Demande.update(vm.demande, onSaveSuccess, onSaveError);
            } else {
                Demande.save(vm.demande, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('myMechanicApp:demandeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
