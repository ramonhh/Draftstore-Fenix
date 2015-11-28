/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramonhonorio
 */
@Entity
@Table(name = "TB_TIPO_PAGAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPagamento.findAll", query = "SELECT t FROM TipoPagamento t"),
    @NamedQuery(name = "TipoPagamento.findByIdTipoPagamento", query = "SELECT t FROM TipoPagamento t WHERE t.idTipoPagamento = :idTipoPagamento"),
    @NamedQuery(name = "TipoPagamento.findByDescTipoPagamento", query = "SELECT t FROM TipoPagamento t WHERE t.descTipoPagamento = :descTipoPagamento")})
public class TipoPagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_PAGAMENTO")
    private Integer idTipoPagamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESC_TIPO_PAGAMENTO")
    private String descTipoPagamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTipoPagamento")
    private Collection<CarrinhoVenda> carrinhoVendaCollection;

    public TipoPagamento() {
    }

    public TipoPagamento(Integer idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public TipoPagamento(Integer idTipoPagamento, String descTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
        this.descTipoPagamento = descTipoPagamento;
    }

    public Integer getIdTipoPagamento() {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento(Integer idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public String getDescTipoPagamento() {
        return descTipoPagamento;
    }

    public void setDescTipoPagamento(String descTipoPagamento) {
        this.descTipoPagamento = descTipoPagamento;
    }

    @XmlTransient
    public Collection<CarrinhoVenda> getCarrinhoVendaCollection() {
        return carrinhoVendaCollection;
    }

    public void setCarrinhoVendaCollection(Collection<CarrinhoVenda> carrinhoVendaCollection) {
        this.carrinhoVendaCollection = carrinhoVendaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPagamento != null ? idTipoPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPagamento)) {
            return false;
        }
        TipoPagamento other = (TipoPagamento) object;
        if ((this.idTipoPagamento == null && other.idTipoPagamento != null) || (this.idTipoPagamento != null && !this.idTipoPagamento.equals(other.idTipoPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wrm.draftstore.common.entidades.TipoPagamento[ idTipoPagamento=" + idTipoPagamento + " ]";
    }
    
}
