$(function(){
   $("#formAlterarEndereco\\:cep").change(function(){
      var cep_code = $(this).val();
      if( cep_code.length <= 0 ) return;
      $.get("http://apps.widenet.com.br/busca-cep/api/cep.json", { code: cep_code },
         function(result){
            if( result.status!=1 ){
               alert(result.message || "Houve um erro desconhecido");
               return;
            }
            $("input#formAlterarEndereco\\:estado").val( result.state );
            $("input#formAlterarEndereco\\:cidade").val( result.city );
            $("input#formAlterarEndereco\\:bairro").val( result.district );
            $("input#formAlterarEndereco\\:rua").val( result.address );
         });
   });
});
