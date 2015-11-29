/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service;

import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.entidades.Carrinho;
import java.util.List;

/**
 *
 * @author ramonhonorio
 */
public interface CarrinhoService {
    public Carrinho obter(Long idCarrinho);

    public void incluir(Carrinho e);

    public List<Carrinho> listarDoUsuario(Usuario u);
}
