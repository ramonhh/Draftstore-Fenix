/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.jpaControllers;

import com.wrm.draftstore.common.entidades.Endereco;
import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.jpaControllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ramonhonorio
 */
public class UsuarioJpaController implements Serializable {

  public UsuarioJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Usuario usuario) {
    if (usuario.getEnderecoCollection() == null) {
      usuario.setEnderecoCollection(new ArrayList<Endereco>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Collection<Endereco> attachedEnderecoCollection = new ArrayList<Endereco>();
      for (Endereco enderecoCollectionEnderecoToAttach : usuario.getEnderecoCollection()) {
        enderecoCollectionEnderecoToAttach = em.getReference(enderecoCollectionEnderecoToAttach.getClass(), enderecoCollectionEnderecoToAttach.getIdEndereco());
        attachedEnderecoCollection.add(enderecoCollectionEnderecoToAttach);
      }
      usuario.setEnderecoCollection(attachedEnderecoCollection);
      em.persist(usuario);
      for (Endereco enderecoCollectionEndereco : usuario.getEnderecoCollection()) {
        Usuario oldFkUsuarioOfEnderecoCollectionEndereco = enderecoCollectionEndereco.getFkUsuario();
        enderecoCollectionEndereco.setFkUsuario(usuario);
        enderecoCollectionEndereco = em.merge(enderecoCollectionEndereco);
        if (oldFkUsuarioOfEnderecoCollectionEndereco != null) {
          oldFkUsuarioOfEnderecoCollectionEndereco.getEnderecoCollection().remove(enderecoCollectionEndereco);
          oldFkUsuarioOfEnderecoCollectionEndereco = em.merge(oldFkUsuarioOfEnderecoCollectionEndereco);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
      Collection<Endereco> enderecoCollectionOld = persistentUsuario.getEnderecoCollection();
      Collection<Endereco> enderecoCollectionNew = usuario.getEnderecoCollection();
      Collection<Endereco> attachedEnderecoCollectionNew = new ArrayList<Endereco>();
      for (Endereco enderecoCollectionNewEnderecoToAttach : enderecoCollectionNew) {
        enderecoCollectionNewEnderecoToAttach = em.getReference(enderecoCollectionNewEnderecoToAttach.getClass(), enderecoCollectionNewEnderecoToAttach.getIdEndereco());
        attachedEnderecoCollectionNew.add(enderecoCollectionNewEnderecoToAttach);
      }
      enderecoCollectionNew = attachedEnderecoCollectionNew;
      usuario.setEnderecoCollection(enderecoCollectionNew);
      usuario = em.merge(usuario);
      for (Endereco enderecoCollectionOldEndereco : enderecoCollectionOld) {
        if (!enderecoCollectionNew.contains(enderecoCollectionOldEndereco)) {
          enderecoCollectionOldEndereco.setFkUsuario(null);
          enderecoCollectionOldEndereco = em.merge(enderecoCollectionOldEndereco);
        }
      }
      for (Endereco enderecoCollectionNewEndereco : enderecoCollectionNew) {
        if (!enderecoCollectionOld.contains(enderecoCollectionNewEndereco)) {
          Usuario oldFkUsuarioOfEnderecoCollectionNewEndereco = enderecoCollectionNewEndereco.getFkUsuario();
          enderecoCollectionNewEndereco.setFkUsuario(usuario);
          enderecoCollectionNewEndereco = em.merge(enderecoCollectionNewEndereco);
          if (oldFkUsuarioOfEnderecoCollectionNewEndereco != null && !oldFkUsuarioOfEnderecoCollectionNewEndereco.equals(usuario)) {
            oldFkUsuarioOfEnderecoCollectionNewEndereco.getEnderecoCollection().remove(enderecoCollectionNewEndereco);
            oldFkUsuarioOfEnderecoCollectionNewEndereco = em.merge(oldFkUsuarioOfEnderecoCollectionNewEndereco);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = usuario.getIdUsuario();
        if (findUsuario(id) == null) {
          throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Usuario usuario;
      try {
        usuario = em.getReference(Usuario.class, id);
        usuario.getIdUsuario();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
      }
      Collection<Endereco> enderecoCollection = usuario.getEnderecoCollection();
      for (Endereco enderecoCollectionEndereco : enderecoCollection) {
        enderecoCollectionEndereco.setFkUsuario(null);
        enderecoCollectionEndereco = em.merge(enderecoCollectionEndereco);
      }
      em.remove(usuario);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Usuario> findUsuarioEntities() {
    return findUsuarioEntities(true, -1, -1);
  }

  public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
    return findUsuarioEntities(false, maxResults, firstResult);
  }

  private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Usuario.class));
      Query q = em.createQuery(cq);
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public Usuario findUsuario(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Usuario.class, id);
    } finally {
      em.close();
    }
  }

  public int getUsuarioCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Usuario> rt = cq.from(Usuario.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
  
  public Usuario realizarLogin(String email, String senha){
    EntityManager em = getEntityManager();
//    EntityManager em = emFactory.createEntityManager();
    Usuario resultado = null;
    Query query = em.createNamedQuery("Usuario.realizarLogin")
            .setParameter("email", email)
            .setParameter("senha", senha);
    try {
      resultado = (Usuario) query.getSingleResult();
    } catch (Exception ex) {
      System.out.println(">> Usuario não encontrado no banco. || "+ex);
    }
    em.close();
    return resultado;
  }
  
}
