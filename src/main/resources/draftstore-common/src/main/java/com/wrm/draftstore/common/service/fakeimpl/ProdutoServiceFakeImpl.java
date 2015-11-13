/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service.fakeimpl;

import com.wrm.draftstore.common.entidades.Categoria;
import com.wrm.draftstore.common.service.jpaimpl.*;
import com.wrm.draftstore.common.entidades.Produto;
import com.wrm.draftstore.common.entidades.Subcategoria;
import com.wrm.draftstore.common.service.ProdutoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ramonhonorio
 */
public class ProdutoServiceFakeImpl implements ProdutoService {


  public ProdutoServiceFakeImpl() {
  }

  @Override
  public List<Produto> listar(int offset, int quantidade) {
    List<Produto> lista = new ArrayList<>();
    
    Produto p = new Produto();
    p.setIdProduto(1);
    p.setPrecoVenda(new BigDecimal(1078.80)); 
    p.setPrecoPromo(new BigDecimal(999.00)); 
    p.setPercentualLucro(new BigDecimal(20.00)); 
    p.setModelo("Core i7 3770");
    p.setMarca("Intel");
    p.setCusto(new BigDecimal(899.00)); 
    p.setFkCategoria(new Categoria(3, "Hardware"));
    p.setFkSubcategoria(new Subcategoria(2, "Processador"));
    p.setQuantidade(20);
    p.setCaminhoImagem("http://images.tcdn.com.br/img/img_prod/374123/1957_1_20140701183212.jpg");
    p.setDescricao("<p><span><strong>Caracter&iacute;sticas:<br /></strong>- Marca: Intel <br />- Modelo:&nbsp;</span>BX80637I73770</p> <p>&nbsp;</p> <p><span><strong>Especifica&ccedil;&otilde;es:<br /></strong>- Tipo: CPU / Microprocessador<br /></span><span>- Fam&iacute;lia: Intel Core i7</span><br /><span>- N&uacute;mero do modelo: i7-3770</span><br /><span>- Frequ&ecirc;ncia: 3400 MHz</span><br /><span>- Freq&uuml;&ecirc;ncia Turbo: 3900 MHz</span><br /><span>- Multiplicador de clock: 34</span><br /><span>- Pacote: 1155 terra-Flip-Chip Land Grid Array</span><br /><span>- Soquete: 1155 (soquete H2)&nbsp;</span><br /><span>- Microarquitetura: Ivy Bridge</span><br /><span>- N&uacute;cleo do processador: Ivy Bridge</span><br /><span>- Processo de fabrica&ccedil;&atilde;o: 0,022 micron</span><br /><span>- Largura de dados: 64 bits</span><br /><span>- 4x n&uacute;cleos</span><br /><span>- 8x threads</span><br /><span>- Unidade de Ponto Flutuante: Integrado</span><br /><span>- N&iacute;vel tamanho do cache de um: 4 x 32 KB cache de instru&ccedil;&atilde;o&nbsp;</span><br /><span>- 4 x 32 caches de dados KB</span><br /><span>- N&iacute;vel tamanho do cache de 2: 4 x 256 KB</span><br /><span>- N&iacute;vel tamanho do cache de 3: 8 MB de cache compartilhado</span><br /><span>- Multiprocessamento: Uniprocessor<br /></span>- Dual-channel controlador de mem&oacute;ria DDR3<br />- Interface: PCI Express 3.0<br />- HD 4000 controlador de gr&aacute;ficos <br />- Par&acirc;metros el&eacute;tricos / t&eacute;rmica: Thermal Design Power: 77 Watt</p> <p>&nbsp;</p> <p><strong>ATEN&Ccedil;&Atilde;O!</strong> Verifique se sua placa-m&atilde;e suporta a terceira gera&ccedil;&atilde;o de processadores da Intel (Ivy Bridge). Para o funcionamento correto dos novos processadores Ivy Bridge (3&ordm; gera&ccedil;&atilde;o da Intel), &eacute; altamente recomend&aacute;vel a atualiza&ccedil;&atilde;o da BIOS para a mais nova vers&atilde;o. Para tal procedimento, &eacute; necess&aacute;rio que um t&eacute;cnico de sua confian&ccedil;a o fa&ccedil;a. Qualquer erro na atualiza&ccedil;&atilde;o por falta de conhecimento t&eacute;cnico, poder&aacute; ocasionar a perda irrevers&iacute;vel da placa m&atilde;e.&nbsp;</p> <br>   <br>");
    p.setDescricaoImagem("Core i7 3770");
    
    lista.add(p);
    
    return lista;
  }

  @Override
  public Produto obter(Long idProduto) {
    return null;
  }

  @Override
  public void incluir(Produto p) {
    
  }

  @Override
  public void alterar(Produto p) {
    
  }

  @Override
  public void remover(Long idProduto) {
    
  }

}