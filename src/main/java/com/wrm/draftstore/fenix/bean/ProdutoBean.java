/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.bean;

import com.wrm.draftstore.common.entidades.Categoria;
import com.wrm.draftstore.common.entidades.Produto;
import com.wrm.draftstore.common.service.ProdutoService;
import com.wrm.draftstore.common.service.jpaimpl.ProdutoServiceJPAImpl;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author ramonhonorio
 */
@Named(value = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

    /**
     * Creates a new instance of ProdutoBean
     */
    public ProdutoBean() {
    }

    // Produtos Fake
//  private List<Produto> ofertasSemana = new ProdutoServiceFakeImpl().listar(0, 10);
    // Produtos JPA
    private ProdutoService produtoService = new ProdutoServiceJPAImpl();
    private List<Produto> ofertasSemana;
    private String textoDeBusca = "";
    private Map<String, Produto> mapaProdutosBusca;

    private Produto produtoDetalhe;
    private int idProdutoDetalhe;

    public String carregarDetalhes() {
        this.produtoDetalhe = this.getProduto();
        System.out.println("PRODUTO DETALHE: " + this.produtoDetalhe.getModelo());
        return "detalhe?faces-redirect=true";
    }

    public void carregarDetalhesBusca(Produto p) {
        this.produtoDetalhe = p;
        System.out.println("PRODUTO DETALHE: " + this.produtoDetalhe.getModelo());
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("detalhe?id=" + this.produtoDetalhe.getIdProduto());
        } catch (IOException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Produto> getOfertasSemana() {
        this.ofertasSemana = produtoService.listar(0, 10);
        return ofertasSemana;
    }

    public Produto getProduto() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        Long id = (long) getId(contexto);
        return produtoService.obter(id);
    }

    private int getId(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return Integer.parseInt(params.get("id"));
    }

    public Produto getProdutoDetalhe() {
        return produtoDetalhe;
    }

    public void setProdutoDetalhe(Produto produtoDetalhe) {
        this.produtoDetalhe = produtoDetalhe;
    }

    public List<Produto> getProdutosPorCategoria(Categoria categoria) {
        return this.produtoService.obterPorCategoria(categoria.getIdCategoria(), 0, 10);
    }

    public int getIdProdutoDetalhe() {
        return idProdutoDetalhe;
    }

    public void setIdProdutoDetalhe(int idProdutoDetalhe) {
        this.idProdutoDetalhe = idProdutoDetalhe;
    }

    public String getTextoDeBusca() {
        return textoDeBusca;
    }

    public void setTextoDeBusca(String textoDeBusca) {
        this.textoDeBusca = textoDeBusca;
    }

    public List<String> completeText(String textoDeBusca) {
        List<Produto> listaProdutos = produtoService.obterPorParteDoNome(textoDeBusca, 0, 5);
        List<String> listaString = new ArrayList<>();
        this.mapaProdutosBusca = new HashMap<>();

        for (Produto p : listaProdutos) {
            String str = p.getMarca() + " " + p.getModelo();
            listaString.add(str);
            this.mapaProdutosBusca.put(str, p);
        }

        return listaString;

    }

    public void onItemSelect(SelectEvent event) {
        String stringProdutoSelecionado = event.getObject().toString();
        System.out.println("Um produto foi selecionado: " + stringProdutoSelecionado);
        Produto produtoSelecionado = this.mapaProdutosBusca.get(stringProdutoSelecionado);
        this.produtoDetalhe = produtoSelecionado;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("detalhe.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        carregarDetalhesBusca(this.mapaProdutosBusca.get(produtoSelecionado));
    }

}
