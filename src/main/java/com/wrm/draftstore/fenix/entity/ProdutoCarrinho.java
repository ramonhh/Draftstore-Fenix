/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.entity;

import com.wrm.draftstore.common.entidades.Produto;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ramonhonorio
 */
public class ProdutoCarrinho implements Serializable {

  private Produto produto;
  private Date dataInclusao;
  private int quantidade;

  public ProdutoCarrinho() {
  }

  public ProdutoCarrinho(Produto produto, Date dataInclusao) {
    this.produto = produto;
    this.dataInclusao = dataInclusao;
    this.quantidade = 1;
  }

  public ProdutoCarrinho(Produto produto, Date dataInclusao, int quantidade) {
    this.produto = produto;
    this.dataInclusao = dataInclusao;
    this.quantidade = quantidade;
  }

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public Date getDataInclusao() {
    return dataInclusao;
  }

  public void setDataInclusao(Date dataInclusao) {
    this.dataInclusao = dataInclusao;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

}
