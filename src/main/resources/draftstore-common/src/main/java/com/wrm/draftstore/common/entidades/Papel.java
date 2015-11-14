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
@Table(name = "TB_PAPEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Papel.findAll", query = "SELECT p FROM Papel p"),
    @NamedQuery(name = "Papel.findByIdPapel", query = "SELECT p FROM Papel p WHERE p.idPapel = :idPapel"),
    @NamedQuery(name = "Papel.findByNomePapel", query = "SELECT p FROM Papel p WHERE p.nomePapel = :nomePapel")})
public class Papel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PAPEL")
    private Integer idPapel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOME_PAPEL")
    private String nomePapel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPapel")
    private Collection<Funcionario> funcionarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPapel")
    private Collection<Pagina> paginaCollection;

    public Papel() {
    }

    public Papel(Integer idPapel) {
        this.idPapel = idPapel;
    }

    public Papel(Integer idPapel, String nomePapel) {
        this.idPapel = idPapel;
        this.nomePapel = nomePapel;
    }

    public Integer getIdPapel() {
        return idPapel;
    }

    public void setIdPapel(Integer idPapel) {
        this.idPapel = idPapel;
    }

    public String getNomePapel() {
        return nomePapel;
    }

    public void setNomePapel(String nomePapel) {
        this.nomePapel = nomePapel;
    }

    @XmlTransient
    public Collection<Funcionario> getFuncionarioCollection() {
        return funcionarioCollection;
    }

    public void setFuncionarioCollection(Collection<Funcionario> funcionarioCollection) {
        this.funcionarioCollection = funcionarioCollection;
    }

    @XmlTransient
    public Collection<Pagina> getPaginaCollection() {
        return paginaCollection;
    }

    public void setPaginaCollection(Collection<Pagina> paginaCollection) {
        this.paginaCollection = paginaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPapel != null ? idPapel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Papel)) {
            return false;
        }
        Papel other = (Papel) object;
        if ((this.idPapel == null && other.idPapel != null) || (this.idPapel != null && !this.idPapel.equals(other.idPapel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wrm.draftstore.common.entidades.Papel[ idPapel=" + idPapel + " ]";
    }
    
}
