(function() {
    'use strict';

    angular
        .module('myMechanicApp')
        .controller('DemandeDeleteController',DemandeDeleteController);

    DemandeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Demande'];

    function DemandeDeleteController($uibModalInstance, entity, Demande) {
        var vm = this;

        vm.demande = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Demande.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
