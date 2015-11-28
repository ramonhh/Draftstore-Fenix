/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramonhonorio
 */
@Entity
@Table(name = "TB_CARTAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cartao.findAll", query = "SELECT c FROM Cartao c"),
    @NamedQuery(name = "Cartao.findByIdCartao", query = "SELECT c FROM Cartao c WHERE c.idCartao = :idCartao"),
    @NamedQuery(name = "Cartao.findByNumeroCartao", query = "SELECT c FROM Cartao c WHERE c.numeroCartao = :numeroCartao"),
    @NamedQuery(name = "Cartao.findByExpiracaoCartao", query = "SELECT c FROM Cartao c WHERE c.expiracaoCartao = :expiracaoCartao"),
    @NamedQuery(name = "Cartao.findByNomeCartao", query = "SELECT c FROM Cartao c WHERE c.nomeCartao = :nomeCartao")})
public class Cartao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CARTAO")
    private Integer idCartao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CARTAO")
    private int numeroCartao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXPIRACAO_CARTAO")
    @Temporal(TemporalType.DATE)
    private Date expiracaoCartao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NOME_CARTAO")
    private String nomeCartao;
    @OneToMany(mappedBy = "fkCartao")
    private Collection<CarrinhoVenda> carrinhoVendaCollection;
    @JoinColumn(name = "FK_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario fkUsuario;

    public Cartao() {
    }

    public Cartao(Integer idCartao) {
        this.idCartao = idCartao;
    }

    public Cartao(Integer idCartao, int numeroCartao, Date expiracaoCartao, String nomeCartao) {
        this.idCartao = idCartao;
        this.numeroCartao = numeroCartao;
        this.expiracaoCartao = expiracaoCartao;
        this.nomeCartao = nomeCartao;
    }

    public Integer getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Integer idCartao) {
        this.idCartao = idCartao;
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Date getExpiracaoCartao() {
        return expiracaoCartao;
    }

    public void setExpiracaoCartao(Date expiracaoCartao) {
        this.expiracaoCartao = expiracaoCartao;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    @XmlTransient
    public Collection<CarrinhoVenda> getCarrinhoVendaCollection() {
        return carrinhoVendaCollection;
    }

    public void setCarrinhoVendaCollection(Collection<CarrinhoVenda> carrinhoVendaCollection) {
        this.carrinhoVendaCollection = carrinhoVendaCollection;
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
        hash += (idCartao != null ? idCartao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartao)) {
            return false;
        }
        Cartao other = (Cartao) object;
        if ((this.idCartao == null && other.idCartao != null) || (this.idCartao != null && !this.idCartao.equals(other.idCartao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wrm.draftstore.common.entidades.Cartao[ idCartao=" + idCartao + " ]";
    }
    
}
