/**
 * Main application controller
 *
 * You can use this controller for your whole app if it is small
 * or you can have separate controllers for each logical section
 * 
 */
;(function() {

  angular
    .module('boilerplate')
    .controller('MainController', MainController);

  MainController.$inject = ['LocalStorage', 'QueryService'];


  function MainController(LocalStorage, QueryService) {

    // 'controller as' syntax
    var self = this;
    

    ////////////  function definitions

    /**
     * Load some data
     * @return {Object} Returned object
     */
    // QueryService.query('GET', 'posts', {}, {})
    //   .then(function(ovocie) {
    //     self.ovocie = ovocie.data;
    //   });
//      QueryService.listarPessoas().then(function(retorno){
//        self.listaPessoas = retorno;                                
//      }).catch(function(retornoErro){
//          
//      }).finally(function(){
//          
//      });
//  

    
    self.upload = function(documento){
        var file = documento;
        console.log(file);
        
    }
    self.getAmigosPorIdPessoa = function(id){
        QueryService.getListaAmigosPorId(id).then(function(retorno){
        self.listaAmigos = retorno;                             
      }).catch(function(retornoErro){
        listaAmigos = {};
      }).finally(function(){
          
      });
    }
    self.getPessoaPorEmail = function(email){
        QueryService.getPessoaPorEmail(email).then(function(retorno){
        if(retorno!=""){
        self.pessoaForm = retorno;
        self.getAmigosPorIdPessoa(self.pessoaForm.pessoaId);
        }else{
             alert("Não existe pessoa com email informado");
        }
      }).catch(function(retornoErro){
       
      }).finally(function(){
          
      });
    }
    
   self.limpaCampos = function(){
       self.pessoaForm = null;
   } 
   self.validaForm = function(pessoaForm){
       if(!pessoaForm)
           return false;
       if(!pessoaForm.nome)
           return false;
       if(!pessoaForm.email)
           return false;
       if(!pessoaForm.telefone)
           return false;
       
       return true;
   }
   self.salvar = function(pessoaForm){
       self.validaCampos = self.validaForm(pessoaForm);
       if(self.validaCampos){
       QueryService.salvarPessoas(pessoaForm).then(function(retorno){
      }).catch(function(retornoErro){
          
      }).finally(function(){
          
      });
    alert("Registro salvo com sucesso");
    self.limpaCampos();
   }else{
       alert("Campos obrigatório não informados");
   }}
   
   self.removerAmigo = function(amigo){
      alert(amigo.pessoaId);
   }
  }
    
  

})();