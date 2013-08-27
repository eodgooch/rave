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

requirejs.config({
    "paths":{
        "backbone":"//cdnjs.cloudflare.com/ajax/libs/backbone.js/0.9.10/backbone-min",
        "bootstrap": '//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.3/bootstrap.min',
        "clientMessages": "../../app/messagebundle/rave_client_messages",
        "handlebars":"//cdnjs.cloudflare.com/ajax/libs/handlebars.js/1.0.rc.2/handlebars.min",
        "jquery": "//ajax.aspnetcdn.com/ajax/jquery/jquery-1.7.2.min",
        "jqueryUi":"//ajax.aspnetcdn.com/ajax/jquery.ui/1.8.17/jquery-ui.min",
        "jqueryValidate":"//ajax.aspnetcdn.com/ajax/jquery.validate/1.8.1/jquery.validate.min",
        "jqueryHashChange":'//cdnjs.cloudflare.com/ajax/libs/jquery-hashchange/v1.3/jquery.ba-hashchange.min',
        "jqueryTouchPouch": '//cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.2/jquery.ui.touch-punch.min',
        "angular": '//cdnjs.cloudflare.com/ajax/libs/angular.js/1.1.5/angular.min',
        "angularUIBootstrap" : "//cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.5.0/ui-bootstrap-tpls.min",
        "angularResource": '//cdnjs.cloudflare.com/ajax/libs/angular.js/1.1.5/angular-resource.min',
        //TODO: We need to get this url from the server either via an endpoint or jsp
        "osapi":"/gadgets/js/container:pubsub-2:open-views.js?c=1&amp;container=default&amp;debug=1",
//        "osapi":"//placeholder.url.will.be.replaced.by.opensocial.environment",
        "rave": "core/main",
        "underscore": "//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.4.4/underscore-min"
    },
    shim: {
        backbone: {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        bootstrap:{
            deps:['jquery']
        },
        handlebars:{
            exports:'Handlebars'
        },
        jquery:{
            exports:'$'
        },
        jqueryUi:{
            deps: ['jquery']
        },
        jqueryValidate:{
            deps:['jquery']
        },
        jqueryHashChange:{
            deps:['jquery']
        },
        jqueryTouchPouch:{
            deps:['jquery']
        },
        underscore: {
            exports: '_'
        },
        angular:{
            deps: ['jquery'],
            exports: 'angular'
        },
        angularResource:{
            deps: ['angular']
        },
        angularUIBootstrap : {
            deps : ['angular']
        }
    }
});

require(['angular', 'portal/portal'], function(angular, app){
    angular.bootstrap(document, [app.name]);
});