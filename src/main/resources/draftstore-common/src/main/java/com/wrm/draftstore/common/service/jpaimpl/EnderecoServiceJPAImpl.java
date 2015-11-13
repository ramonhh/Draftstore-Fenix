/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service.jpaimpl;

import com.wrm.draftstore.common.entidades.Endereco;
import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.jpaControllers.EnderecoJpaController;
import com.wrm.draftstore.common.jpaControllers.exceptions.NonexistentEntityException;
import com.wrm.draftstore.common.service.EnderecoService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ramonhonorio
 */
public class EnderecoServiceJPAImpl implements EnderecoService{

  private final EntityManagerFactory emFactory
          = Persistence.createEntityManagerFactory("com.wrm_draftstore-common_jar_1.0-SNAPSHOTPU");
  private final EnderecoJpaController enderecoController = new EnderecoJpaController(emFactory);

  public EnderecoServiceJPAImpl() {
  }
  
  @Override
  public List<Endereco> listar(int offset, int quantidade) {
    return enderecoController.findEnderecoEntities(offset, quantidade);
  }

  @Override
  public Endereco obter(Long idEndereco) {
    return enderecoController.findEndereco(idEndereco.intValue());
  }

  @Override
  public void incluir(Endereco e) {
    enderecoController.create(e);
  }

  @Override
  public void alterar(Endereco e) {
    try {
      enderecoController.edit(e);
    } catch (Exception ex) {
      Logger.getLogger(EnderecoServiceJPAImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void remover(Long idEndereco) {
    try {
      enderecoController.destroy(idEndereco.intValue());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(EnderecoServiceJPAImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public List<Endereco> listarDoUsuario(Usuario u) {
    EntityManager em = emFactory.createEntityManager();
    Query query = em.createNamedQuery("Endereco.findByUser")
            .setParameter("idUsuario", u.getIdUsuario());
    List<Endereco> resultados = query.getResultList();
    em.close();
    return resultados;
  }
  
}
