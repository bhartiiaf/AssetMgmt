/*!
 * Remark Material (http://getbootstrapadmin.com/remark)
 * Copyright 2017 amazingsurge
 * Licensed under the Themeforest Standard Licenses
 */

!function(global,factory){if("function"==typeof define&&define.amd)define("/advanced/animation",["jquery","Site"],factory);else if("undefined"!=typeof exports)factory(require("jquery"),require("Site"));else{var mod={exports:{}};factory(global.jQuery,global.Site),global.advancedAnimation=mod.exports}}(this,function(_jquery,_Site){"use strict";var _jquery2=babelHelpers.interopRequireDefault(_jquery),Site=babelHelpers.interopRequireWildcard(_Site);(0,_jquery2.default)(document).ready(function($){Site.run(),$(document).on("click",".select-loader",function(e){var type=$(this).data("type"),curr=$(".example-loading .loader").data("type");type!==curr&&$(".example-loading .loader").removeClass("loader-"+curr).addClass("loader-"+type).data("type",type)}),$(document).on("click",".btn",function(e){switch($(e.target).attr("id")){case"exampleNProgressStart":NProgress.start();break;case"exampleNProgressSet":NProgress.set(.5);break;case"exampleNProgressInc":NProgress.inc();break;case"exampleNProgressDone":NProgress.done(!0);break;case"exampleNProgressDefault":NProgress.done(!0),NProgress.configure({template:'<div class="bar" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'}),NProgress.start();break;case"exampleNProgressHeader":NProgress.done(!0),NProgress.configure({template:'<div class="bar nprogress-bar-header" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'}),NProgress.start();break;case"exampleNProgressBottom":NProgress.done(!0),NProgress.configure({template:'<div class="bar nprogress-bar-bottom" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'}),NProgress.start();break;case"exampleNProgressPrimary":NProgress.done(!0),NProgress.configure({template:'<div class="bar nprogress-bar-primary" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'}),NProgress.start();break;case"exampleNProgressSuccess":NProgress.done(!0),NProgress.configure({template:'<div class="bar nprogress-bar-success" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'}),NProgress.start();break;case"exampleNProgressInfo":NProgress.done(!0),NProgress.configure({template:'<div class="bar nprogress-bar-info" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'}),NProgress.start();break;case"exampleNProgressWarning":NProgress.done(!0),NProgress.configure({template:'<div class="bar nprogress-bar-warning" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'}),NProgress.start();break;case"exampleNProgressDanger":NProgress.done(!0),NProgress.configure({template:'<div class="bar nprogress-bar-danger" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'}),NProgress.start();break;case"exampleNProgressDark":NProgress.done(!0),NProgress.configure({template:'<div class="bar nprogress-bar-dark" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'}),NProgress.start();break;case"exampleNProgressLight":NProgress.done(!0),NProgress.configure({template:'<div class="bar nprogress-bar-light" role="bar"></div><div class="spinner" role="spinner"><div class="spinner-icon"></div></div>'}),NProgress.start()}})})});