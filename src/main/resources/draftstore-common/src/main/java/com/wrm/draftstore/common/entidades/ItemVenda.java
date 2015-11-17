/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wilson A. Oliveira
 */
@Entity
@Table(name = "TB_ITEM_VENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemVenda.findAll", query = "SELECT i FROM ItemVenda i"),
    @NamedQuery(name = "ItemVenda.findByIdItemVenda", query = "SELECT i FROM ItemVenda i WHERE i.idItemVenda = :idItemVenda"),
    @NamedQuery(name = "ItemVenda.findByQuantidade", query = "SELECT i FROM ItemVenda i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "ItemVenda.findByPreco", query = "SELECT i FROM ItemVenda i WHERE i.preco = :preco"),
    @NamedQuery(name = "ItemVenda.findByNomeProduto", query = "SELECT i FROM ItemVenda i WHERE i.nomeProduto = :nomeProduto")})
public class ItemVenda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ITEM_VENDA")
    private Integer idItemVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTIDADE")
    private int quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECO")
    private BigDecimal preco;
    @Size(max = 50)
    @Column(name = "NOME_PRODUTO")
    private String nomeProduto;
    @JoinColumn(name = "FK_VENDA", referencedColumnName = "ID_VENDA")
    @ManyToOne(optional = false)
    private Venda fkVenda;
    @JoinColumn(name = "FK_PRODUTO", referencedColumnName = "ID_PRODUTO")
    @ManyToOne(optional = false)
    private Produto fkProduto;

    public ItemVenda() {
    }

    public ItemVenda(Integer idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public ItemVenda(Integer idItemVenda, int quantidade, BigDecimal preco) {
        this.idItemVenda = idItemVenda;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Integer getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(Integer idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Venda getFkVenda() {
        return fkVenda;
    }

    public void setFkVenda(Venda fkVenda) {
        this.fkVenda = fkVenda;
    }

    public Produto getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Produto fkProduto) {
        this.fkProduto = fkProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemVenda != null ? idItemVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemVenda)) {
            return false;
        }
        ItemVenda other = (ItemVenda) object;
        if ((this.idItemVenda == null && other.idItemVenda != null) || (this.idItemVenda != null && !this.idItemVenda.equals(other.idItemVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wrm.draftstore.common.entidades.ItemVenda[ idItemVenda=" + idItemVenda + " ]";
    }
    
}
