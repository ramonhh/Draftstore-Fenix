/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramonhonorio
 */
@Entity
@Table(name = "TB_CARRINHO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrinho.findAll", query = "SELECT c FROM Carrinho c"),
    @NamedQuery(name = "Carrinho.findByIdCarrinho", query = "SELECT c FROM Carrinho c WHERE c.idCarrinho = :idCarrinho"),
    @NamedQuery(name = "Carrinho.findByDataAlteracao", query = "SELECT c FROM Carrinho c WHERE c.dataAlteracao = :dataAlteracao"),
    @NamedQuery(name = "Carrinho.findByQuantidade", query = "SELECT c FROM Carrinho c WHERE c.quantidade = :quantidade"),
    @NamedQuery(name = "Carrinho.findByUser", query = "SELECT c FROM Usuario u JOIN u.carrinhoCollection c WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Carrinho.findByPreco", query = "SELECT c FROM Carrinho c WHERE c.preco = :preco")})
public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CARRINHO")
    private Integer idCarrinho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_ALTERACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTIDADE")
    private int quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECO")
    private BigDecimal preco;
    @JoinColumn(name = "FK_CARRINHO_VENDA", referencedColumnName = "ID_VENDA")
    @ManyToOne(optional = false)
    private CarrinhoVenda fkCarrinhoVenda;
    @JoinColumn(name = "FK_PRODUTO", referencedColumnName = "ID_PRODUTO")
    @ManyToOne(optional = false)
    private Produto fkProduto;
    @JoinColumn(name = "FK_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario fkUsuario;

    public Carrinho() {
    }

    public Carrinho(Integer idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public Carrinho(Integer idCarrinho, Date dataAlteracao, int quantidade, BigDecimal preco) {
        this.idCarrinho = idCarrinho;
        this.dataAlteracao = dataAlteracao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Integer getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Integer idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
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

    public CarrinhoVenda getFkCarrinhoVenda() {
        return fkCarrinhoVenda;
    }

    public void setFkCarrinhoVenda(CarrinhoVenda fkCarrinhoVenda) {
        this.fkCarrinhoVenda = fkCarrinhoVenda;
    }

    public Produto getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Produto fkProduto) {
        this.fkProduto = fkProduto;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarrinho != null ? idCarrinho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrinho)) {
            return false;
        }
        Carrinho other = (Carrinho) object;
        if ((this.idCarrinho == null && other.idCarrinho != null) || (this.idCarrinho != null && !this.idCarrinho.equals(other.idCarrinho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wrm.draftstore.common.entidades.Carrinho[ idCarrinho=" + idCarrinho + " ]";
    }

}
