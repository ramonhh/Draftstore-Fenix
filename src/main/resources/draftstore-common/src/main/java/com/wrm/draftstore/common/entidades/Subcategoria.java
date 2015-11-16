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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TB_SUBCATEGORIA")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Subcategoria.findAll", query = "SELECT s FROM Subcategoria s"),
  @NamedQuery(name = "Subcategoria.findByIdSubcategoria", query = "SELECT s FROM Subcategoria s WHERE s.idSubcategoria = :idSubcategoria"),
  @NamedQuery(name = "Subcategoria.findByNomeSubcategoria", query = "SELECT s FROM Subcategoria s WHERE s.nomeSubcategoria = :nomeSubcategoria")})
public class Subcategoria implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID_SUBCATEGORIA")
  private Integer idSubcategoria;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "NOME_SUBCATEGORIA")
  private String nomeSubcategoria;
  @JoinColumn(name = "FK_CATEGORIA", referencedColumnName = "ID_CATEGORIA")
  @ManyToOne(optional = false)
  private Categoria fkCategoria;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSubcategoria")
  private Collection<Produto> produtoCollection;

  public Subcategoria() {
  }

  public Subcategoria(Integer idSubcategoria) {
    this.idSubcategoria = idSubcategoria;
  }

  public Subcategoria(Integer idSubcategoria, String nomeSubcategoria) {
    this.idSubcategoria = idSubcategoria;
    this.nomeSubcategoria = nomeSubcategoria;
  }

  public Integer getIdSubcategoria() {
    return idSubcategoria;
  }

  public void setIdSubcategoria(Integer idSubcategoria) {
    this.idSubcategoria = idSubcategoria;
  }

  public String getNomeSubcategoria() {
    return nomeSubcategoria;
  }

  public void setNomeSubcategoria(String nomeSubcategoria) {
    this.nomeSubcategoria = nomeSubcategoria;
  }

  public Categoria getFkCategoria() {
    return fkCategoria;
  }

  public void setFkCategoria(Categoria fkCategoria) {
    this.fkCategoria = fkCategoria;
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
    hash += (idSubcategoria != null ? idSubcategoria.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Subcategoria)) {
      return false;
    }
    Subcategoria other = (Subcategoria) object;
    if ((this.idSubcategoria == null && other.idSubcategoria != null) || (this.idSubcategoria != null && !this.idSubcategoria.equals(other.idSubcategoria))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.wrm.draftstore.common.entidades.Subcategoria[ idSubcategoria=" + idSubcategoria + " ]";
  }
  
}
