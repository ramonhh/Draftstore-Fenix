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
 * @author Wilson A. Oliveira
 */
@Entity
@Table(name = "TB_CATEGORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "Categoria.findByNomeCategoria", query = "SELECT c FROM Categoria c WHERE c.nomeCategoria = :nomeCategoria"),
    @NamedQuery(name = "Categoria.findByIcon", query = "SELECT c FROM Categoria c WHERE c.icon = :icon")})
public class Categoria implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCategoria")
    private Collection<Produto> produtoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCategoria")
    private Collection<Subcategoria> subcategoriaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOME_CATEGORIA")
    private String nomeCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ICON")
    private String icon;

    public Categoria() {
    }

    public Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria(Integer idCategoria, String nomeCategoria, String icon) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.icon = icon;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wrm.draftstore.common.entidades.Categoria[ idCategoria=" + idCategoria + " ]";
    }

    @XmlTransient
    public Collection<Produto> getProdutoCollection() {
        return produtoCollection;
    }

    public void setProdutoCollection(Collection<Produto> produtoCollection) {
        this.produtoCollection = produtoCollection;
    }

    @XmlTransient
    public Collection<Subcategoria> getSubcategoriaCollection() {
        return subcategoriaCollection;
    }

    public void setSubcategoriaCollection(Collection<Subcategoria> subcategoriaCollection) {
        this.subcategoriaCollection = subcategoriaCollection;
    }
    
}
