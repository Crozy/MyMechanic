(function() {
    'use strict';

    angular
        .module('myMechanicApp')
        .controller('GarageController', GarageController);

    GarageController.$inject = ['Garage'];

    function GarageController(Garage) {

        var vm = this;

        vm.garages = [];

        loadAll();

        function loadAll() {
            Garage.query(function(result) {
                vm.garages = result;
                vm.searchQuery = null;
            });
        }
    }
})();
