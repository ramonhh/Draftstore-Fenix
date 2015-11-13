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
@Table(name = "TB_FORNECEDOR")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
  @NamedQuery(name = "Fornecedor.findByIdFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.idFornecedor = :idFornecedor"),
  @NamedQuery(name = "Fornecedor.findByRazaoSocial", query = "SELECT f FROM Fornecedor f WHERE f.razaoSocial = :razaoSocial"),
  @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj"),
  @NamedQuery(name = "Fornecedor.findByCep", query = "SELECT f FROM Fornecedor f WHERE f.cep = :cep"),
  @NamedQuery(name = "Fornecedor.findByEndereco", query = "SELECT f FROM Fornecedor f WHERE f.endereco = :endereco"),
  @NamedQuery(name = "Fornecedor.findByNumero", query = "SELECT f FROM Fornecedor f WHERE f.numero = :numero"),
  @NamedQuery(name = "Fornecedor.findByBairro", query = "SELECT f FROM Fornecedor f WHERE f.bairro = :bairro"),
  @NamedQuery(name = "Fornecedor.findByCidade", query = "SELECT f FROM Fornecedor f WHERE f.cidade = :cidade"),
  @NamedQuery(name = "Fornecedor.findByEstado", query = "SELECT f FROM Fornecedor f WHERE f.estado = :estado"),
  @NamedQuery(name = "Fornecedor.findByTelefone", query = "SELECT f FROM Fornecedor f WHERE f.telefone = :telefone"),
  @NamedQuery(name = "Fornecedor.findByEmail", query = "SELECT f FROM Fornecedor f WHERE f.email = :email"),
  @NamedQuery(name = "Fornecedor.findBySite", query = "SELECT f FROM Fornecedor f WHERE f.site = :site"),
  @NamedQuery(name = "Fornecedor.findByDataCriacao", query = "SELECT f FROM Fornecedor f WHERE f.dataCriacao = :dataCriacao"),
  @NamedQuery(name = "Fornecedor.findByNomeUsuario", query = "SELECT f FROM Fornecedor f WHERE f.nomeUsuario = :nomeUsuario")})
public class Fornecedor implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID_FORNECEDOR")
  private Integer idFornecedor;
  @Size(max = 100)
  @Column(name = "RAZAO_SOCIAL")
  private String razaoSocial;
  @Size(max = 20)
  @Column(name = "CNPJ")
  private String cnpj;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "CEP")
  private String cep;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 75)
  @Column(name = "ENDERECO")
  private String endereco;
  @Basic(optional = false)
  @NotNull
  @Column(name = "NUMERO")
  private int numero;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 75)
  @Column(name = "BAIRRO")
  private String bairro;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "CIDADE")
  private String cidade;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "ESTADO")
  private String estado;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "TELEFONE")
  private String telefone;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 75)
  @Column(name = "EMAIL")
  private String email;
  @Size(max = 75)
  @Column(name = "SITE")
  private String site;
  @Basic(optional = false)
  @NotNull
  @Column(name = "DATA_CRIACAO")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCriacao;
  @Size(max = 50)
  @Column(name = "NOME_USUARIO")
  private String nomeUsuario;
  @JoinColumn(name = "FK_FUNCIONARIO", referencedColumnName = "ID_FUNCIONARIO")
  @ManyToOne(optional = false)
  private Funcionario fkFuncionario;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFornecedor")
  private Collection<Produto> produtoCollection;

  public Fornecedor() {
  }

  public Fornecedor(Integer idFornecedor) {
    this.idFornecedor = idFornecedor;
  }

  public Fornecedor(Integer idFornecedor, String cep, String endereco, int numero, String bairro, String cidade, String estado, String telefone, String email, Date dataCriacao) {
    this.idFornecedor = idFornecedor;
    this.cep = cep;
    this.endereco = endereco;
    this.numero = numero;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
    this.telefone = telefone;
    this.email = email;
    this.dataCriacao = dataCriacao;
  }

  public Integer getIdFornecedor() {
    return idFornecedor;
  }

  public void setIdFornecedor(Integer idFornecedor) {
    this.idFornecedor = idFornecedor;
  }

  public String getRazaoSocial() {
    return razaoSocial;
  }

  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
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

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
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

  public Funcionario getFkFuncionario() {
    return fkFuncionario;
  }

  public void setFkFuncionario(Funcionario fkFuncionario) {
    this.fkFuncionario = fkFuncionario;
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
    hash += (idFornecedor != null ? idFornecedor.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Fornecedor)) {
      return false;
    }
    Fornecedor other = (Fornecedor) object;
    if ((this.idFornecedor == null && other.idFornecedor != null) || (this.idFornecedor != null && !this.idFornecedor.equals(other.idFornecedor))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.wrm.draftstore.common.entidades.Fornecedor[ idFornecedor=" + idFornecedor + " ]";
  }
  
}
