;(function() {


  'use strict';


  /**
   * $http service abstraction to make API calls with any HTTP method,
   * custom url and data object to be sent as request.
   * Every REST API call is measured, you can see how much it took
   * in the console.
   *
   * @category  factory
   * @author    Jozef Butko
   * @example   Inject QueryService as the dependency and then use it this way:
   *
   * QueryService.query('GET', 'users/user/', {get: query}, {post: params})
      .then(function(data) {
        console.log(data);
      }, function(error) {
        console.log(error);
      });
   *
   * @param     {String} method HTTP method, eg. 'PUT', 'GET'...
   * @param     {String} url API endpoint, eg. 'users/user' or 'system-properties'
   * @param     {Object} params Map of strings or objects which will be turned
   *                     to `?key1=value1&key2=value2` after the url. If the value
   *                     is not a string, it will be
   *                     JSONified
   * @return    {Object} data Data to be sent as the request message data
   * @version   1.1
   *
   */


  angular
    .module('boilerplate')
    .factory('QueryService', [
      '$http', '$q', 'CONSTANTS', QueryService
    ]);



  //////////////// factory



  function QueryService($http, $q, CONSTANTS) {
    //////////////// definition
    function query(method, url, params, data) {
      var deferred = $q.defer();
      $http({
        method: method,
        url: CONSTANTS.API_URL + url,
        params: params,
        data: data
      }).then(function(data) {
        if (!data.config) {
          console.log('Server error occured.');
        }
        deferred.resolve(data);
      }, function(error) {
        deferred.reject(error);
      });
      return deferred.promise;
    }
      
      
   function listarPessoas(){
          var deferred = $q.defer();
          $http.get('//localhost:8080/WebServiceRest/rest/service/todasPessoas',{
              
          }).success(function(data){
            deferred.resolve(data);            
        }).error(function(err,status){
              deferred.reject({data:err,status:status});
          });
          return deferred.promise;
    }
    function getListaAmigosPorId(id){
          var deferred = $q.defer();
          $http.get('//localhost:8080/WebServiceRest/rest/service/getAmigosPorIdPessoa/'+id,{
              
          }).success(function(data){
            deferred.resolve(data);            
        }).error(function(err,status){
              deferred.reject({data:err,status:status});
          });
          return deferred.promise;
    }
    function getPessoaPorEmail(email){
          var deferred = $q.defer();
          $http.get('//localhost:8080/WebServiceRest/rest/service/getPessoaPorEmail/'+email,{
            
          }).success(function(data){
            deferred.resolve(data);            
        }).error(function(err,status){
              deferred.reject({data:err,status:status});
          });
          return deferred.promise;
      }
      
    function salvarPessoas(pessoa){
          var deferred = $q.defer();
          $http({ 
              url:' //localhost:8080/WebServiceRest/rest/service/cadastrar',
              method :'POST',
              data: pessoa
        }).success(function(data){
            deferred.resolve(data); 
        }).error(function(err,status){
              deferred.reject({data:err,status:status});
          });
          return deferred.promise;
      }   
    
    function inserirAmigo(id,pessoa){
          var deferred = $q.defer();
          $http({ 
              url:' //localhost:8080/WebServiceRest/rest/service/insereAmigo/'+id,
              method :'POST',
              data: pessoa
        }).success(function(data){
            deferred.resolve(data); 
        }).error(function(err,status){
              deferred.reject({data:err,status:status});
          });
          return deferred.promise;
      }  
      
          function removerAmigo(id1,id2){
          var deferred = $q.defer();
          $http({ 
              url:' //localhost:8080/WebServiceRest/rest/service/excluirAmizade/'+id1+'/'+id2,
              method :'DELETE'
        }).success(function(data){
            deferred.resolve(data); 
        }).error(function(err,status){
              deferred.reject({data:err,status:status});
          });
          return deferred.promise;
      }   
    
    return {
      query,
      listarPessoas,
      salvarPessoas,
      getListaAmigosPorId,
      getPessoaPorEmail,
      inserirAmigo,
      removerAmigo
    };
  }

    

})();
