/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.bean;

import com.wrm.draftstore.common.entidades.Endereco;
import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.service.EnderecoService;
import com.wrm.draftstore.common.service.jpaimpl.EnderecoServiceJPAImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ramonhonorio
 */
@ManagedBean
@SessionScoped
public class EnderecoBean implements Serializable {

    private final EnderecoService enderecoService = new EnderecoServiceJPAImpl();

    public List<Endereco> enderecos;
    public Endereco novoEndereco;
    public Endereco enderecoSelecionado;
    
    public String tipoEntrega;

    public EnderecoBean() {
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void carregarEnderecos(Usuario u) {
        this.enderecos = enderecoService.listarDoUsuario(u);
    }

    public Endereco getNovoEndereco() {
        return novoEndereco;
    }

    public void setNovoEndereco(Endereco novoEndereco) {
        this.novoEndereco = novoEndereco;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }
    
    public String salvarEndereco() {
//        System.out.println("Logradouro:"+novoEndereco.getRua());
//        System.out.println("IDUSUARIO:"+novoEndereco.getFkUsuario());
        if (novoEndereco != null) {
            enderecoService.incluir(novoEndereco);
        } else {
            System.out.println("Endereço não salvo.");
        }
        return "perfil.xhtml?faces-redirect=true";
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }
    
    public void inicializarEndereco(Usuario u){
        novoEndereco = new Endereco();
        novoEndereco.setFkUsuario(u);
        this.carregarEnderecos(u);
        enderecoSelecionado = new Endereco();
    }

    public String selecionarEndereco(Endereco e){
        enderecoSelecionado = e;
        return "compra_confirmarEndereco.xhtml?faces-redirect=true";
    }
    
    public String removerEndereco(Endereco e) {
        enderecoService.remover(e.getIdEndereco().longValue());
        return "perfil.xhtml?faces-redirect=true";
    }
    
    public String editarEndereco(Endereco e){
        enderecoService.alterar(e);
        return "perfil.xhtml?faces-redirect=true";
    }
}
