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
@Table(name = "TB_USUARIO")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
  @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
  @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
  @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
  @NamedQuery(name = "Usuario.findByTelefone", query = "SELECT u FROM Usuario u WHERE u.telefone = :telefone"),
  @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
  @NamedQuery(name = "Usuario.findByDataCriacao", query = "SELECT u FROM Usuario u WHERE u.dataCriacao = :dataCriacao"),
  @NamedQuery(name = "Usuario.findByCpf", query = "SELECT u FROM Usuario u WHERE u.cpf = :cpf"),
  @NamedQuery(name = "Usuario.findByDtNascimento", query = "SELECT u FROM Usuario u WHERE u.dtNascimento = :dtNascimento"),
  @NamedQuery(name = "Usuario.findBySexo", query = "SELECT u FROM Usuario u WHERE u.sexo = :sexo"),
  @NamedQuery(name = "Usuario.findByCelular", query = "SELECT u FROM Usuario u WHERE u.celular = :celular"),
  @NamedQuery(name = "Usuario.realizarLogin", query = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")})
public class Usuario implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID_USUARIO")
  private Integer idUsuario;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 250)
  @Column(name = "NOME")
  private String nome;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 500)
  @Column(name = "EMAIL")
  private String email;
  @Size(max = 8)
  @Column(name = "TELEFONE")
  private String telefone;
  @Size(max = 250)
  @Column(name = "SENHA")
  private String senha;
  @Basic(optional = false)
  @NotNull
  @Column(name = "DATA_CRIACAO")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCriacao;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 11)
  @Column(name = "CPF")
  private String cpf;
  @Basic(optional = false)
  @NotNull
  @Column(name = "DT_NASCIMENTO")
  @Temporal(TemporalType.DATE)
  private Date dtNascimento;
  @Column(name = "SEXO")
  private Character sexo;
  @Size(max = 9)
  @Column(name = "CELULAR")
  private String celular;
  @OneToMany(mappedBy = "fkUsuario")
  private Collection<Endereco> enderecoCollection;

  public Usuario() {
  }

  public Usuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public Usuario(Integer idUsuario, String nome, String email, Date dataCriacao, String cpf, Date dtNascimento) {
    this.idUsuario = idUsuario;
    this.nome = nome;
    this.email = email;
    this.dataCriacao = dataCriacao;
    this.cpf = cpf;
    this.dtNascimento = dtNascimento;
  }

  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Date getDtNascimento() {
    return dtNascimento;
  }

  public void setDtNascimento(Date dtNascimento) {
    this.dtNascimento = dtNascimento;
  }

  public Character getSexo() {
    return sexo;
  }

  public void setSexo(Character sexo) {
    this.sexo = sexo;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  @XmlTransient
  public Collection<Endereco> getEnderecoCollection() {
    return enderecoCollection;
  }

  public void setEnderecoCollection(Collection<Endereco> enderecoCollection) {
    this.enderecoCollection = enderecoCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idUsuario != null ? idUsuario.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Usuario)) {
      return false;
    }
    Usuario other = (Usuario) object;
    if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.wrm.draftstore.common.entidades.Usuario[ idUsuario=" + idUsuario + " ]";
  }
  
}
