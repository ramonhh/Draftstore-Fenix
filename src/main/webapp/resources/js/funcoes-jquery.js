/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

cancelarAlteracao = false;

// Permite a execucao inicial das funcionalidades javascript
$(function () { // Abreviação para $(document).ready(function() { ... });

//    Dimmer nas imagens
  $('.special.cards .image').dimmer({
    on: 'hover'
  });
//    Apresentar aquele alert (modal) daora
  $('#showModal').click(function () {
    $('.basic.modal')
            .modal('show')
            ;
  });
//    Quando clicar no carrinho fazer uma animacao
  $('.addCarrinho').click(function () {
    $(this).blur();
    var qtd = parseInt($('#labelCarrinho').text());
    animacaoAdicionouItens(qtd);
    var qtd = parseInt($('#labelCarrinho2').text());
    animacaoMobile(qtd);
  });
  // Função do Slider da pagina de detalhes
  $('#slider').nivoSlider({});

  //Função para os tabs da paginda de detalhes
  $('.tabular.menu .item').tab();

  $(".addCarrinho").focus(function () {
    $(this).removeClass("ui-state-focus");
  });
  $("#btslide").click(function () {
    $('.ui.labeled.icon.sidebar.menu')
            .sidebar('toggle')
            ;
  });
  $("#pesquisaMenu").click(function () {
    $('.ui.labeled.icon.sidebar.pesquisa')
            .sidebar('toggle')
            ;
  });
  //Função para os tabs da pagina do perfil
  $('.ui.vertical.fluid.tabular.menu .item').tab();

  $('.cadastrarEndereco').click(function () {
    $('.modal.endereco')
            .modal('show')
            ;
  });
  $('.alterarEndereco').click(function () {
    $('.modal.alterarEndereco')
            .modal('show')
            ;
  });
  $('.cancelaSalvarEndereco').click(function () {
    $('.modal.endereco')
            .modal('hide');
    $('.modal.alterarEndereco')
            .modal('hide');
  });
  $('.cancelaSalvarEndereco2').click(function () {
    $('.modal.alterarEndereco')
            .modal('hide');
  });
  $('.finalizarCompra').click(function () {
    $('.finalizarCompraModal')
            .modal('setting', 'closable', false)
            .modal('show')
            ;
  });

});



function animacaoAdicionouItens(quantidade) {
//    Testando uma animacao na contagem xD
//        var texto = parseInt($('#labelCarrinho').text());
  if (quantidade == 0) {
    $('#labelCarrinho')
            .transition('browse right');
  }

  $('#labelCarrinho')
          .text(quantidade + 1)
          .transition('jiggle');
}
function animacaoMobile(quantidade) {
//    Testando uma animacao na contagem xD
//        var texto = parseInt($('#labelCarrinho').text());
  if (quantidade == 0) {
    $('#labelCarrinho2')
            .transition('browse right');
  }

  $('#labelCarrinho2')
          .text(quantidade + 1)
          .transition('jiggle');
}


PrimeFaces.locales['pt'] = {
    closeText: 'Fechar',
    prevText: 'Anterior',
    nextText: 'Próximo',
    currentText: 'Começo',
    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun', 'Jul','Ago','Set','Out','Nov','Dez'],
    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb'],
    dayNamesMin: ['D','S','T','Q','Q','S','S'],
    weekHeader: 'Semana',
    firstDay: 0,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Só Horas',
    timeText: 'Tempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    ampm: false,
    month: 'Mês',
    week: 'Semana',
    day: 'Dia',
    allDayText : 'Todo o dia'
};