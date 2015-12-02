$(function(){
   $("#formEndereco\\:cep").change(function(){
      var cep_code = $(this).val();
      if( cep_code.length <= 0 ) return;
      $.get("http://apps.widenet.com.br/busca-cep/api/cep.json", { code: cep_code },
         function(result){
            if( result.status!=1 ){
               alert(result.message || "Houve um erro desconhecido");
               return;
            }
            $("input#formEndereco\\:estado").val( result.state );
            $("input#formEndereco\\:cidade").val( result.city );
            $("input#formEndereco\\:bairro").val( result.district );
            $("input#formEndereco\\:rua").val( result.address );
         });
   });
});
