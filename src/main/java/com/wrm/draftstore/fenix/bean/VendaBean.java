/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.bean;

import com.wrm.draftstore.common.entidades.CarrinhoVenda;
import com.wrm.draftstore.common.entidades.Endereco;
import com.wrm.draftstore.common.entidades.TipoPagamento;
import com.wrm.draftstore.common.service.CarrinhoVendaService;
import com.wrm.draftstore.common.service.jpaimpl.CarrinhoVendaServiceJPAImpl;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ramonhonorio
 */
@ManagedBean
@SessionScoped
public class VendaBean implements Serializable {

    public Endereco enderecoSelecionado;
    public TipoPagamento tp = new TipoPagamento(2);
    public CarrinhoVenda cv;
    private final CarrinhoVendaService cvs = new CarrinhoVendaServiceJPAImpl();

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

    public CarrinhoVenda getCv() {
        return cv;
    }

    public void setCv(CarrinhoVenda cv) {
        this.cv = cv;
    }

    public VendaBean() {
        this.enderecoSelecionado = new Endereco();
        this.cv = new CarrinhoVenda();
        this.cv.setDataVenda(new Date());
        this.cv.setFrete(0);
        this.cv.setPrazoEntrega(10);
        this.cv.setFkTipoPagamento(tp);
    }

    public TipoPagamento getTp() {
        return tp;
    }

    public void setTp(TipoPagamento tp) {
        this.tp = tp;
    }

    public VendaBean(Endereco enderecoSelecionado, CarrinhoVenda cv) {
        this.enderecoSelecionado = enderecoSelecionado;
        this.cv = cv;
    }

    public String selecionarEndereco(Endereco e) {
        enderecoSelecionado = e;
        this.cv.setFkEndereco(e);
        return "compra_confirmarEndereco.xhtml?faces-redirect=true";
    }

    public CarrinhoVenda realizarVenda() {
        return cvs.incluir(this.cv);
    }

}
