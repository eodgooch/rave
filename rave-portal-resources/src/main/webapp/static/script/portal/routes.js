/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

define(['angular', 'common/resources/index', 'common/services/index'], function (angular) {
    var router = angular.module('portal.routes', ['common.resources', 'common.services']);

    router.config(['constants', '$routeProvider', '$locationProvider', '$httpProvider',
        function (constants, $routeProvider, $locationProvider, $httpProvider) {

            /**
             * The resolve functions guarantee that needed data is requested and resolved from the server BEFORE route
             * change is triggered. Notice that each function checks if the property already exists before triggering
             * resource request. This is to prevent a new request on every change in navigation - it will only happen
             * on initial load.
             */
            //TODO: once angular 1.2 is released, the resources will expose a $promise property
            var resolve = {
                pages: ['PagesForRender', '$q',
                    function (PagesForRender, $q) {
                        var deferred = $q.defer();

                        PagesForRender.query({
                            context: 'portal',
                            identifier: '@self'
                        }).$then(function (response) {
                                deferred.resolve(response.resource)
                            });

                        return deferred.promise;
                    }
                ],
                user: [ 'Users', '$q',
                    function (Users, $q) {
                        var deferred = $q.defer();

                        Users.get({
                            id: '@self'
                        }).$then(function (response) {
                                deferred.resolve(response.resource)
                            });

                        return deferred.promise;
                    }
                ]
            }

            /**
             * The portal context single page app only supports the following routes: "/" or "/:tabId".
             * Routing should change as user clicks on page tabs.
             */
            $routeProvider
                .when('/', {
                    resolve: resolve,
                    templateUrl: function () {
                        return constants.hostedPath + '/static/html/portal/portal.html'
                    }
                })
                .when('/:tabId', {
                    resolve: resolve,
                    templateUrl: function () {
                        return constants.hostedPath + '/static/html/portal/portal.html'
                    }
                })
                .otherwise({ templateUrl: function () {
                    return constants.hostedPath + '/static/html/portal/404.html'
                }});

            $locationProvider.html5Mode(true);
        }
    ]);

    /**
     * This guarantees that pages, user (currently authenticated user), and currentPageId are always available on the
     * root scope - and therefore any child scopes. currentPageId defaults to the pageId of the [0] element in the pages
     * array.
     */
    router.run(['$route', '$rootScope', function ($route, $rootScope) {
        $rootScope.$on('$routeChangeSuccess', function (evt, curr, prev) {
            $rootScope.pages = curr.locals.pages;
            $rootScope.user = curr.locals.user;
            $rootScope.currentPageId = curr.params.tabId || $rootScope.pages[0].id;
        });
    }]);

    return router;
});