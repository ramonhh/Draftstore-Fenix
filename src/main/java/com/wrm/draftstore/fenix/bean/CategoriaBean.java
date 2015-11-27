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
    private int auxCategoria, auxCategoria2, auxIcone, auxIcone2;
    private Categoria categoriaSelecionada;

    public String getNomeCategoria(){
        String nome = "";
        auxCategoria = auxCategoria2;
        for (int i = auxCategoria; i < categorias.size(); i++) {
            nome = categorias.get(i).getNomeCategoria();
            break;
        }
        auxCategoria2 = auxCategoria + 1;
        return nome;
    }
    
    public String getIconCategoria(){
        String nome = "";
        auxIcone = auxIcone2;
        for (int i = auxIcone; i < categorias.size(); i++) {
            nome = categorias.get(i).getIcon();
            break;
        }
        auxIcone2 = auxIcone + 1;
        return nome;
    }
    
    public List<Categoria> getCategorias() {
        return categorias;
    }
    
    public Categoria getCategoria() {
    FacesContext contexto = FacesContext.getCurrentInstance();
    return categorias.get(getId(contexto)-1);
  }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
  private int getId(FacesContext fc) {
    Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
    return Integer.parseInt(params.get("id"));
  }

    public Categoria getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }
  
  public String irParaListagem(Categoria categoria){
      this.categoriaSelecionada = categoria;
      return "listaCategoria.xhtml?faces-redirect=true";
  }

  
}




