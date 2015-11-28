/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.bean;


import com.wrm.draftstore.common.entidades.Categoria;
import com.wrm.draftstore.common.service.jpaimpl.CategoriaServiceJPAImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author Maikon Evangelista
 */
@Named(value = "categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable {

  /**
   * Creates a new instance of ProdutoBean
   */
  public CategoriaBean() {
  }
  
  // Categorias JPA
  private List<Categoria> categorias = new CategoriaServiceJPAImpl().listar();
 
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
  private int getId(FacesContext fc) {
    Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
    return Integer.parseInt(params.get("id"));
  }

  
}


