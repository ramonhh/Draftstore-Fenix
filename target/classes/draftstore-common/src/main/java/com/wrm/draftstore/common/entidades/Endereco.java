/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.entidades;

import java.io.Serializable;
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
 * @author ramonhonorio
 */
@Entity
@Table(name = "TB_ENDERECO")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
  @NamedQuery(name = "Endereco.findByIdEndereco", query = "SELECT e FROM Endereco e WHERE e.idEndereco = :idEndereco"),
  @NamedQuery(name = "Endereco.findByCep", query = "SELECT e FROM Endereco e WHERE e.cep = :cep"),
  @NamedQuery(name = "Endereco.findByEstado", query = "SELECT e FROM Endereco e WHERE e.estado = :estado"),
  @NamedQuery(name = "Endereco.findByRua", query = "SELECT e FROM Endereco e WHERE e.rua = :rua"),
  @NamedQuery(name = "Endereco.findByBairro", query = "SELECT e FROM Endereco e WHERE e.bairro = :bairro"),
  @NamedQuery(name = "Endereco.findByCidade", query = "SELECT e FROM Endereco e WHERE e.cidade = :cidade"),
  @NamedQuery(name = "Endereco.findByComplemento", query = "SELECT e FROM Endereco e WHERE e.complemento = :complemento"),
  @NamedQuery(name = "Endereco.findByNumero", query = "SELECT e FROM Endereco e WHERE e.numero = :numero"),
  @NamedQuery(name = "Endereco.findByUser", query = "SELECT e FROM Usuario u JOIN u.enderecoCollection e WHERE u.idUsuario = :idUsuario")})
  
public class Endereco implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID_ENDERECO")
  private Integer idEndereco;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 8)
  @Column(name = "CEP")
  private String cep;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 2)
  @Column(name = "ESTADO")
  private String estado;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "RUA")
  private String rua;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "BAIRRO")
  private String bairro;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "CIDADE")
  private String cidade;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "COMPLEMENTO")
  private String complemento;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "NUMERO")
  private String numero;
  @JoinColumn(name = "FK_USUARIO", referencedColumnName = "ID_USUARIO")
  @ManyToOne
  private Usuario fkUsuario;

  public Endereco() {
  }

  public Endereco(Integer idEndereco) {
    this.idEndereco = idEndereco;
  }

  public Endereco(Integer idEndereco, String cep, String estado, String rua, String bairro, String cidade, String complemento, String numero) {
    this.idEndereco = idEndereco;
    this.cep = cep;
    this.estado = estado;
    this.rua = rua;
    this.bairro = bairro;
    this.cidade = cidade;
    this.complemento = complemento;
    this.numero = numero;
  }

  public Integer getIdEndereco() {
    return idEndereco;
  }

  public void setIdEndereco(Integer idEndereco) {
    this.idEndereco = idEndereco;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getRua() {
    return rua;
  }

  public void setRua(String rua) {
    this.rua = rua;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
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
    hash += (idEndereco != null ? idEndereco.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Endereco)) {
      return false;
    }
    Endereco other = (Endereco) object;
    if ((this.idEndereco == null && other.idEndereco != null) || (this.idEndereco != null && !this.idEndereco.equals(other.idEndereco))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.wrm.draftstore.common.entidades.Endereco[ idEndereco=" + idEndereco + " ]";
  }
  
}
