(function() {
    'use strict';

    angular
        .module('myMechanicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('demande', {
            parent: 'entity',
            url: '/demande',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'myMechanicApp.demande.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/demande/demandes.html',
                    controller: 'DemandeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('demande');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('demande-detail', {
            parent: 'demande',
            url: '/demande/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'myMechanicApp.demande.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/demande/demande-detail.html',
                    controller: 'DemandeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('demande');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Demande', function($stateParams, Demande) {
                    return Demande.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'demande',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('demande-detail.edit', {
            parent: 'demande-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/demande/demande-dialog.html',
                    controller: 'DemandeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Demande', function(Demande) {
                            return Demande.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('demande.new', {
            parent: 'demande',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/demande/demande-dialog.html',
                    controller: 'DemandeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                utilisateur: null,
                                demande: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('demande', null, { reload: 'demande' });
                }, function() {
                    $state.go('demande');
                });
            }]
        })
        .state('demande.edit', {
            parent: 'demande',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/demande/demande-dialog.html',
                    controller: 'DemandeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Demande', function(Demande) {
                            return Demande.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('demande', null, { reload: 'demande' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('demande.delete', {
            parent: 'demande',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/demande/demande-delete-dialog.html',
                    controller: 'DemandeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Demande', function(Demande) {
                            return Demande.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('demande', null, { reload: 'demande' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
