/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service;

import com.wrm.draftstore.common.entidades.Endereco;
import com.wrm.draftstore.common.entidades.Usuario;
import java.util.List;

/**
 *
 * @author ramonhonorio
 */
public interface EnderecoService {
  
  public List<Endereco> listar(int offset, int quantidade);
  
  public Endereco obter(Long idEndereco);
  
  public void incluir(Endereco e);
  
  public void alterar(Endereco e);
  
  public void remover(Long idEndereco);
  
  public List<Endereco> listarDoUsuario(Usuario u);
}
