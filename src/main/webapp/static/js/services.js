var serviceM= angular.module('servicesM', ['ngResource']);

serviceM.factory('ThetaService', function($resource){
    var webResource= {};
    webResource.core= $resource('/theta/ctrl/core/:action',{
        action: '@action'
    });
    webResource.message= $resource('/theta/ctrl/message/:action',{
        action: '@action'
    });
    webResource.complaint= $resource('/theta/ctrl/complaint/:action',{
        action: '@action'
    });
    webResource.user= $resource('/theta/ctrl/user/:action',{
        action: '@action'
    });
    webResource.address= $resource('/theta/ctrl/address/:action',{
        action: '@action'
    });    
    return webResource;
});

serviceM.factory('ThetaGlobleDataService', function($resource){
    var ThetaGlobleDataService= {};
    return ThetaGlobleDataService;
});