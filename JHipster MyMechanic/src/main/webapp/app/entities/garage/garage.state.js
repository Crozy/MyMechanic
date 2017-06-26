(function() {
    'use strict';

    angular
        .module('myMechanicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('garage', {
            parent: 'entity',
            url: '/garage',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'myMechanicApp.garage.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/garage/garages.html',
                    controller: 'GarageController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('garage');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('garage-detail', {
            parent: 'garage',
            url: '/garage/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'myMechanicApp.garage.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/garage/garage-detail.html',
                    controller: 'GarageDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('garage');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Garage', function($stateParams, Garage) {
                    return Garage.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'garage',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('garage-detail.edit', {
            parent: 'garage-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/garage/garage-dialog.html',
                    controller: 'GarageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Garage', function(Garage) {
                            return Garage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('garage.new', {
            parent: 'garage',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/garage/garage-dialog.html',
                    controller: 'GarageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nom: null,
                                adresse: null,
                                longitude: null,
                                latitude: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('garage', null, { reload: 'garage' });
                }, function() {
                    $state.go('garage');
                });
            }]
        })
        .state('garage.edit', {
            parent: 'garage',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/garage/garage-dialog.html',
                    controller: 'GarageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Garage', function(Garage) {
                            return Garage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('garage', null, { reload: 'garage' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('garage.delete', {
            parent: 'garage',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/garage/garage-delete-dialog.html',
                    controller: 'GarageDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Garage', function(Garage) {
                            return Garage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('garage', null, { reload: 'garage' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
