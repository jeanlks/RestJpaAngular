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
      }).finally(function(){
            self.limpaCamposAmigoForm();
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
    
   self.limpaCamposPessoa = function(){
       self.pessoaForm = {};
   } 
   
   self.limpaCamposAmigoForm = function(){
       self.amigoForm = {};
   } 
   self.validaForm = function(pessoaForm){
       if(!self.pessoaForm)
           return false;
       if(!self.pessoaForm.nome)
           return false;
       if(!self.pessoaForm.email)
           return false;
       if(!self.pessoaForm.telefone)
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
       
   
   // self.limpaCamposPessoa();
   }else{
       alert("Campos obrigatório não informados");
   }}
   
      
   self.inserirAmigo = function(id,amigoForm){
       self.validaCampos = self.validaForm(amigoForm);
       if(self.validaCampos){
       QueryService.inserirAmigo(id,amigoForm).then(function(retorno){
      }).catch(function(retornoErro){
          
      }).finally(function(){
        self.getAmigosPorIdPessoa(id);
      });
   // self.limpaCamposPessoa();
    }else{
       alert("Campos obrigatório não informados");
    }}
   
   self.removerAmigo = function(amigo){
      alert(amigo.pessoaId);
   }
   
   
  }
    
  

})();