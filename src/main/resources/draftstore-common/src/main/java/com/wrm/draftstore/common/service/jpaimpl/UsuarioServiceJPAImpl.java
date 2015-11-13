/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service.jpaimpl;

import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.jpaControllers.UsuarioJpaController;
import com.wrm.draftstore.common.jpaControllers.exceptions.NonexistentEntityException;
import com.wrm.draftstore.common.service.UsuarioService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ramonhonorio
 */
public class UsuarioServiceJPAImpl implements UsuarioService {

  private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("com.wrm_draftstore-common_jar_1.0-SNAPSHOTPU");
  private UsuarioJpaController usuarioController = new UsuarioJpaController(emFactory);

  public UsuarioServiceJPAImpl() {
  }
  
  @Override
  public List<Usuario> listar(int offset, int quantidade) {
    return usuarioController.findUsuarioEntities(offset, quantidade);
  }

  @Override
  public Usuario obter(Long idUsuario) {
    return usuarioController.findUsuario(idUsuario.intValue());
  }

  @Override
  public void incluir(Usuario u) {
    usuarioController.create(u);
  }

  @Override
  public void alterar(Usuario u) {
    try {
      usuarioController.edit(u);
    } catch (Exception ex) {
      Logger.getLogger(UsuarioServiceJPAImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void remover(Long idUsuario) {
    try {
      usuarioController.destroy(idUsuario.intValue());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(UsuarioServiceJPAImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Usuario realizarLogin(String email, String senha) {
//    EntityManager em = emFactory.createEntityManager();
//    Usuario resultado = null;
//    Query query = em.createNamedQuery("Usuario.realizarLogin")
//            .setParameter("email", email)
//            .setParameter("senha", senha);
//    try {
//      resultado = (Usuario) query.getSingleResult();
//    } catch (Exception ex) {
//      System.out.println(">> Usuario não encontrado no banco. || "+ex);
//    }
//    em.close();
//    return resultado;
    return usuarioController.realizarLogin(email, senha);
  }
  
}
