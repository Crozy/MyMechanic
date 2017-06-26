(function() {
    'use strict';

    angular
        .module('myMechanicApp')
        .controller('DemandeController', DemandeController);

    DemandeController.$inject = ['Demande'];

    function DemandeController(Demande) {

        var vm = this;

        vm.demandes = [];

        loadAll();

        function loadAll() {
            Demande.query(function(result) {
                vm.demandes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
