/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.bean;

import com.wrm.draftstore.common.entidades.Produto;
import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.fenix.entity.ProdutoCarrinho;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author ramonhonorio
 */
@Named(value = "carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {

    /**
     * Creates a new instance of CarrinhoBean
     */
    public CarrinhoBean() {
    }

    private ArrayList<ProdutoCarrinho> itens = new ArrayList<>();
    private HashMap<Produto, ProdutoCarrinho> itensMap = new HashMap<>();
    private int quantidadeItensMap = 0;

    public float getPrecoTotal() {
        float preco = calcularPrecoCarrinho();
        if (preco < 0) {
            return calcularPrecoCarrinho();
        }
        return preco;
    }

    public ArrayList<ProdutoCarrinho> getItens() {
        return itens;
    }

    public void adicionar(Produto produto) {
        System.out.println("Adicionando o produto " + produto.getModelo() + " ao carrinho...");
        ProdutoCarrinho pc = this.itensMap.get(produto);

        if (pc == null) {
            this.itensMap.put(produto, new ProdutoCarrinho(produto, new Date()));
            this.quantidadeItensMap++;
        } else {
            if (produto.getQuantidade() > pc.getQuantidade()) {
                pc.setQuantidade(pc.getQuantidade() + 1);

                this.quantidadeItensMap++;
            }
            this.animarCarrinho();
        }
    }

    public boolean removerProduto(Produto produto) {
        ProdutoCarrinho pc = this.itensMap.get(produto);

        if (pc != null) {
            System.out.println(produto.getModelo() + " foi removido com sucesso.");
            this.quantidadeItensMap -= pc.getQuantidade();
            this.itensMap.remove(produto);
            return true;
        }
        System.out.println("Erro ao tentar remover " + produto.getModelo());

        return false;
    }

    public void handleChange(ValueChangeEvent event) {
        if (Integer.parseInt(event.getNewValue().toString()) > 0) {
            this.quantidadeItensMap -= Integer.parseInt(event.getOldValue().toString());
            this.quantidadeItensMap += Integer.parseInt(event.getNewValue().toString());
        }
    }

    public int getQuantidadeProduto(Produto produto, int quantidade) {
        ProdutoCarrinho pc = this.itensMap.get(produto);
        return pc.getQuantidade();
    }

    public void limparCarrinho() {
        this.itensMap.clear();
        this.itensMap = new HashMap<>();
        this.quantidadeItensMap = 0;
        System.out.println("O carrinho foi limpo!");
    }

    public void checkout() {
        System.out.println("Bem, aqui temos que realizar a compra...");
    }

    public void animarCarrinho() {
        String script = "$('#labelCarrinho').change();";
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(script);
    }

    public void imprimirCarrinho() {
        System.out.println("Carrinho de compras:");
        for (Produto item : this.itensMap.keySet()) {
            System.out.println("ITEM: " + item.getModelo());
        }
        System.out.println("--------------------");
    }

    public String finalizarCompra() {
        limparCarrinho();
        return "/compra_sucesso.xhtml";
    }

    public float calcularPrecoCarrinho() {
        float valor = 0;

        for (ProdutoCarrinho pc : this.getItensMapValues()) {
            if (pc.getQuantidade() < 1) {
                return -1;
            }

            valor += pc.getProduto().getPrecoVenda().floatValue() * pc.getQuantidade();
        }

        return valor;
    }

    public HashMap<Produto, ProdutoCarrinho> getItensMap() {
        return itensMap;
    }

    public void setItensMap(HashMap<Produto, ProdutoCarrinho> mapaItens) {
        this.itensMap = mapaItens;
        this.quantidadeItensMap = this.itensMapRecount(mapaItens);
    }

    public List<Produto> getItensMapKeys() {
        return new ArrayList<>(this.itensMap.keySet());
    }

    public List<ProdutoCarrinho> getItensMapValues() {
        return new ArrayList<>(this.itensMap.values());
    }

    public int getQuantidadeItensMap() {
        return quantidadeItensMap;
    }

    public void setQuantidadeItensMap(int quantidadeItensMap) {
        this.quantidadeItensMap = quantidadeItensMap;
        this.animarCarrinho();
    }

    private ProdutoCarrinho getProdutoCarrinhoFromMap(Map<Produto, ProdutoCarrinho> hm, ProdutoCarrinho value) {
        for (Produto o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return hm.get(o);
            }
        }
        return null;
    }

    public int itensMapRecount(HashMap<Produto, ProdutoCarrinho> mapaItens) {
        int soma = 0;
        for (ProdutoCarrinho pc : mapaItens.values()) {
            soma += pc.getQuantidade();
        }
        return soma;
    }

    public String prosseguirCompra(Usuario u) {

        if (u == null) {
            System.out.println("Não está logado");
            return "home.xhtml";
        }

        return "compra_endereco.xhtml";
    }

}
