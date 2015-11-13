/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service.fakeimpl;

import com.wrm.draftstore.common.entidades.Endereco;
import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.service.UsuarioService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ramonhonorio
 */
public class UsuarioServiceFakeImpl implements UsuarioService  {

  private List<Usuario> lista = new ArrayList<>();

  public UsuarioServiceFakeImpl() {
    lista = new ArrayList<>();
    
    Usuario u = new Usuario();
    u.setNome("Ramon Honorio");
    u.setEmail("ramonaqh@gmail.com");
    u.setSenha("1234");
    
    Endereco e = new Endereco();
    e.setBairro("Vila Olimpia");
    e.setCep("04545-010");
    e.setComplemento("Casa");
    e.setRua("Rua Aleixo Garcia");
    e.setNumero("86");
    e.setEstado("SP");
    e.setCidade("São Paulo");
    
    u.setEnderecoCollection(new ArrayList());
    u.getEnderecoCollection().add(e);
    
    lista.add(u);
  }
  
  @Override
  public List<Usuario> listar(int offset, int quantidade) {
    return lista;
  }

  @Override
  public Usuario obter(Long idUsuario) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void incluir(Usuario u) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void alterar(Usuario u) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void remover(Long idUsuario) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Usuario realizarLogin(String email, String senha) {
    for (Usuario u : lista) {
      if (u.getEmail().equals(email) && u.getSenha().equals(senha)){
        return u;
      }
    }
    return null;
  }
  
}
