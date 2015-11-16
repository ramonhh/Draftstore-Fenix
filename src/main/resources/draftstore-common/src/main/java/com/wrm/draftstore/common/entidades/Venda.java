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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramonhonorio
 */
@Entity
@Table(name = "TB_VENDA")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
  @NamedQuery(name = "Venda.findByIdVenda", query = "SELECT v FROM Venda v WHERE v.idVenda = :idVenda"),
  @NamedQuery(name = "Venda.findByDataCriacao", query = "SELECT v FROM Venda v WHERE v.dataCriacao = :dataCriacao"),
  @NamedQuery(name = "Venda.findByNomeUsuario", query = "SELECT v FROM Venda v WHERE v.nomeUsuario = :nomeUsuario")})
public class Venda implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID_VENDA")
  private Integer idVenda;
  @Basic(optional = false)
  @NotNull
  @Column(name = "DATA_CRIACAO")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCriacao;
  @Size(max = 50)
  @Column(name = "NOME_USUARIO")
  private String nomeUsuario;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkVenda")
  private Collection<ItemVenda> itemVendaCollection;
  @JoinColumn(name = "FK_FUNCIONARIO", referencedColumnName = "ID_FUNCIONARIO")
  @ManyToOne(optional = false)
  private Funcionario fkFuncionario;

  public Venda() {
  }

  public Venda(Integer idVenda) {
    this.idVenda = idVenda;
  }

  public Venda(Integer idVenda, Date dataCriacao) {
    this.idVenda = idVenda;
    this.dataCriacao = dataCriacao;
  }

  public Integer getIdVenda() {
    return idVenda;
  }

  public void setIdVenda(Integer idVenda) {
    this.idVenda = idVenda;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public String getNomeUsuario() {
    return nomeUsuario;
  }

  public void setNomeUsuario(String nomeUsuario) {
    this.nomeUsuario = nomeUsuario;
  }

  @XmlTransient
  public Collection<ItemVenda> getItemVendaCollection() {
    return itemVendaCollection;
  }

  public void setItemVendaCollection(Collection<ItemVenda> itemVendaCollection) {
    this.itemVendaCollection = itemVendaCollection;
  }

  public Funcionario getFkFuncionario() {
    return fkFuncionario;
  }

  public void setFkFuncionario(Funcionario fkFuncionario) {
    this.fkFuncionario = fkFuncionario;
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
    if (!(object instanceof Venda)) {
      return false;
    }
    Venda other = (Venda) object;
    if ((this.idVenda == null && other.idVenda != null) || (this.idVenda != null && !this.idVenda.equals(other.idVenda))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.wrm.draftstore.common.entidades.Venda[ idVenda=" + idVenda + " ]";
  }
  
}
