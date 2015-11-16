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
@Table(name = "TB_FUNCIONARIO")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
  @NamedQuery(name = "Funcionario.findByIdFuncionario", query = "SELECT f FROM Funcionario f WHERE f.idFuncionario = :idFuncionario"),
  @NamedQuery(name = "Funcionario.findBySenha", query = "SELECT f FROM Funcionario f WHERE f.senha = :senha"),
  @NamedQuery(name = "Funcionario.findByNome", query = "SELECT f FROM Funcionario f WHERE f.nome = :nome"),
  @NamedQuery(name = "Funcionario.findByDataNascimento", query = "SELECT f FROM Funcionario f WHERE f.dataNascimento = :dataNascimento"),
  @NamedQuery(name = "Funcionario.findBySexo", query = "SELECT f FROM Funcionario f WHERE f.sexo = :sexo"),
  @NamedQuery(name = "Funcionario.findByCpf", query = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf"),
  @NamedQuery(name = "Funcionario.findByRg", query = "SELECT f FROM Funcionario f WHERE f.rg = :rg"),
  @NamedQuery(name = "Funcionario.findByTelefone", query = "SELECT f FROM Funcionario f WHERE f.telefone = :telefone"),
  @NamedQuery(name = "Funcionario.findByCelular", query = "SELECT f FROM Funcionario f WHERE f.celular = :celular"),
  @NamedQuery(name = "Funcionario.findByEmail", query = "SELECT f FROM Funcionario f WHERE f.email = :email"),
  @NamedQuery(name = "Funcionario.findByAtivo", query = "SELECT f FROM Funcionario f WHERE f.ativo = :ativo"),
  @NamedQuery(name = "Funcionario.findByDataCriacao", query = "SELECT f FROM Funcionario f WHERE f.dataCriacao = :dataCriacao")})
public class Funcionario implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID_FUNCIONARIO")
  private Integer idFuncionario;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "SENHA")
  private String senha;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 75)
  @Column(name = "NOME")
  private String nome;
  @Basic(optional = false)
  @NotNull
  @Column(name = "DATA_NASCIMENTO")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataNascimento;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "SEXO")
  private String sexo;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "CPF")
  private String cpf;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "RG")
  private String rg;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "TELEFONE")
  private String telefone;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "CELULAR")
  private String celular;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "EMAIL")
  private String email;
  @Basic(optional = false)
  @NotNull
  @Column(name = "ATIVO")
  private Boolean ativo;
  @Basic(optional = false)
  @NotNull
  @Column(name = "DATA_CRIACAO")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCriacao;
  @OneToMany(mappedBy = "fkFuncionario")
  private Collection<Log> logCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFuncionario")
  private Collection<Fornecedor> fornecedorCollection;
  @JoinColumn(name = "FK_PAPEL", referencedColumnName = "ID_PAPEL")
  @ManyToOne(optional = false)
  private Papel fkPapel;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFuncionario")
  private Collection<Venda> vendaCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFuncionario")
  private Collection<Produto> produtoCollection;

  public Funcionario() {
  }

  public Funcionario(Integer idFuncionario) {
    this.idFuncionario = idFuncionario;
  }

  public Funcionario(Integer idFuncionario, String senha, String nome, Date dataNascimento, String sexo, String cpf, String rg, String telefone, String celular, String email, Boolean ativo, Date dataCriacao) {
    this.idFuncionario = idFuncionario;
    this.senha = senha;
    this.nome = nome;
    this.dataNascimento = dataNascimento;
    this.sexo = sexo;
    this.cpf = cpf;
    this.rg = rg;
    this.telefone = telefone;
    this.celular = celular;
    this.email = email;
    this.ativo = ativo;
    this.dataCriacao = dataCriacao;
  }

  public Integer getIdFuncionario() {
    return idFuncionario;
  }

  public void setIdFuncionario(Integer idFuncionario) {
    this.idFuncionario = idFuncionario;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getRg() {
    return rg;
  }

  public void setRg(String rg) {
    this.rg = rg;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  @XmlTransient
  public Collection<Log> getLogCollection() {
    return logCollection;
  }

  public void setLogCollection(Collection<Log> logCollection) {
    this.logCollection = logCollection;
  }

  @XmlTransient
  public Collection<Fornecedor> getFornecedorCollection() {
    return fornecedorCollection;
  }

  public void setFornecedorCollection(Collection<Fornecedor> fornecedorCollection) {
    this.fornecedorCollection = fornecedorCollection;
  }

  public Papel getFkPapel() {
    return fkPapel;
  }

  public void setFkPapel(Papel fkPapel) {
    this.fkPapel = fkPapel;
  }

  @XmlTransient
  public Collection<Venda> getVendaCollection() {
    return vendaCollection;
  }

  public void setVendaCollection(Collection<Venda> vendaCollection) {
    this.vendaCollection = vendaCollection;
  }

  @XmlTransient
  public Collection<Produto> getProdutoCollection() {
    return produtoCollection;
  }

  public void setProdutoCollection(Collection<Produto> produtoCollection) {
    this.produtoCollection = produtoCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idFuncionario != null ? idFuncionario.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Funcionario)) {
      return false;
    }
    Funcionario other = (Funcionario) object;
    if ((this.idFuncionario == null && other.idFuncionario != null) || (this.idFuncionario != null && !this.idFuncionario.equals(other.idFuncionario))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.wrm.draftstore.common.entidades.Funcionario[ idFuncionario=" + idFuncionario + " ]";
  }
  
}
