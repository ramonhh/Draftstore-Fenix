/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service;

import com.wrm.draftstore.common.entidades.Usuario;
import java.util.List;

/**
 *
 * @author ramonhonorio
 */
public interface UsuarioService {
  
  public List<Usuario> listar(int offset, int quantidade);
  
  public Usuario obter(Long idUsuario);
  
  public void incluir(Usuario u);
  
  public void alterar(Usuario u);
  
  public void remover(Long idUsuario);
  
  public Usuario realizarLogin(String email, String senha);
  
}
