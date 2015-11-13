/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service;


import com.wrm.draftstore.common.entidades.Produto;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface ProdutoService {
  
  public List<Produto> listar(int offset, int quantidade);
  
  public Produto obter(Long idProduto);
  
  public void incluir(Produto p);
  
  public void alterar(Produto p);
  
  public void remover(Long idProduto);
  
}
