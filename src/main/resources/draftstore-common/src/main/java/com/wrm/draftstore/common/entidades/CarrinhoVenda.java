/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramonhonorio
 */
@Entity
@Table(name = "TB_CARRINHO_VENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarrinhoVenda.findAll", query = "SELECT c FROM CarrinhoVenda c"),
    @NamedQuery(name = "CarrinhoVenda.findByIdVenda", query = "SELECT c FROM CarrinhoVenda c WHERE c.idVenda = :idVenda"),
    @NamedQuery(name = "CarrinhoVenda.findByFrete", query = "SELECT c FROM CarrinhoVenda c WHERE c.frete = :frete"),
    @NamedQuery(name = "CarrinhoVenda.findByPrazoEntrega", query = "SELECT c FROM CarrinhoVenda c WHERE c.prazoEntrega = :prazoEntrega"),
    @NamedQuery(name = "CarrinhoVenda.findByDataVenda", query = "SELECT c FROM CarrinhoVenda c WHERE c.dataVenda = :dataVenda")})
public class CarrinhoVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VENDA")
    private Integer idVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FRETE")
    private int frete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRAZO_ENTREGA")
    private int prazoEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_VENDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVenda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCarrinhoVenda")
    private Collection<Carrinho> carrinhoCollection;
    @JoinColumn(name = "FK_CARTAO", referencedColumnName = "ID_CARTAO")
    @ManyToOne
    private Cartao fkCartao;
    @JoinColumn(name = "FK_ENDERECO", referencedColumnName = "ID_ENDERECO")
    @ManyToOne(optional = false)
    private Endereco fkEndereco;
    @JoinColumn(name = "FK_TIPO_PAGAMENTO", referencedColumnName = "ID_TIPO_PAGAMENTO")
    @ManyToOne(optional = false)
    private TipoPagamento fkTipoPagamento;

    public CarrinhoVenda() {
    }

    public CarrinhoVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public CarrinhoVenda(Integer idVenda, int frete, int prazoEntrega, Date dataVenda) {
        this.idVenda = idVenda;
        this.frete = frete;
        this.prazoEntrega = prazoEntrega;
        this.dataVenda = dataVenda;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public int getFrete() {
        return frete;
    }

    public void setFrete(int frete) {
        this.frete = frete;
    }

    public int getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(int prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public Date getDataEntrega() {
        Calendar c = Calendar.getInstance();
        c.setTime(this.dataVenda);
        c.add(Calendar.DATE, this.prazoEntrega);
        return c.getTime();
    }

    public boolean isVendaEntregue() {
        return this.getDataEntrega().compareTo(Calendar.getInstance().getTime()) <= 0;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    @XmlTransient
    public Collection<Carrinho> getCarrinhoCollection() {
        return carrinhoCollection;
    }

    public void setCarrinhoCollection(Collection<Carrinho> carrinhoCollection) {
        this.carrinhoCollection = carrinhoCollection;
    }

    public Cartao getFkCartao() {
        return fkCartao;
    }

    public void setFkCartao(Cartao fkCartao) {
        this.fkCartao = fkCartao;
    }

    public Endereco getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(Endereco fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public TipoPagamento getFkTipoPagamento() {
        return fkTipoPagamento;
    }

    public void setFkTipoPagamento(TipoPagamento fkTipoPagamento) {
        this.fkTipoPagamento = fkTipoPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenda != null ? idVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarrinhoVenda)) {
            return false;
        }
        CarrinhoVenda other = (CarrinhoVenda) object;
        if ((this.idVenda == null && other.idVenda != null) || (this.idVenda != null && !this.idVenda.equals(other.idVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wrm.draftstore.common.entidades.CarrinhoVenda[ idVenda=" + idVenda + " ]";
    }

}
