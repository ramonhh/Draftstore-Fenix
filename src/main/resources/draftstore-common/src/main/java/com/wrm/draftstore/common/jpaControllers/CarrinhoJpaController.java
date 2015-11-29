/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.jpaControllers;

import com.wrm.draftstore.common.entidades.Carrinho;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.entidades.Produto;
import com.wrm.draftstore.common.entidades.CarrinhoVenda;
import com.wrm.draftstore.common.jpaControllers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Wilson A. Oliveira
 */
public class CarrinhoJpaController implements Serializable {

    public CarrinhoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carrinho carrinho) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario fkUsuario = carrinho.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario = em.getReference(fkUsuario.getClass(), fkUsuario.getIdUsuario());
                carrinho.setFkUsuario(fkUsuario);
            }
            Produto fkProduto = carrinho.getFkProduto();
            if (fkProduto != null) {
                fkProduto = em.getReference(fkProduto.getClass(), fkProduto.getIdProduto());
                carrinho.setFkProduto(fkProduto);
            }
            CarrinhoVenda fkCarrinhoVenda = carrinho.getFkCarrinhoVenda();
            if (fkCarrinhoVenda != null) {
                fkCarrinhoVenda = em.getReference(fkCarrinhoVenda.getClass(), fkCarrinhoVenda.getIdVenda());
                carrinho.setFkCarrinhoVenda(fkCarrinhoVenda);
            }
            em.persist(carrinho);
            if (fkUsuario != null) {
                fkUsuario.getCarrinhoCollection().add(carrinho);
                fkUsuario = em.merge(fkUsuario);
            }
            if (fkProduto != null) {
                fkProduto.getCarrinhoCollection().add(carrinho);
                fkProduto = em.merge(fkProduto);
            }
            if (fkCarrinhoVenda != null) {
                fkCarrinhoVenda.getCarrinhoCollection().add(carrinho);
                fkCarrinhoVenda = em.merge(fkCarrinhoVenda);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carrinho carrinho) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrinho persistentCarrinho = em.find(Carrinho.class, carrinho.getIdCarrinho());
            Usuario fkUsuarioOld = persistentCarrinho.getFkUsuario();
            Usuario fkUsuarioNew = carrinho.getFkUsuario();
            Produto fkProdutoOld = persistentCarrinho.getFkProduto();
            Produto fkProdutoNew = carrinho.getFkProduto();
            CarrinhoVenda fkCarrinhoVendaOld = persistentCarrinho.getFkCarrinhoVenda();
            CarrinhoVenda fkCarrinhoVendaNew = carrinho.getFkCarrinhoVenda();
            if (fkUsuarioNew != null) {
                fkUsuarioNew = em.getReference(fkUsuarioNew.getClass(), fkUsuarioNew.getIdUsuario());
                carrinho.setFkUsuario(fkUsuarioNew);
            }
            if (fkProdutoNew != null) {
                fkProdutoNew = em.getReference(fkProdutoNew.getClass(), fkProdutoNew.getIdProduto());
                carrinho.setFkProduto(fkProdutoNew);
            }
            if (fkCarrinhoVendaNew != null) {
                fkCarrinhoVendaNew = em.getReference(fkCarrinhoVendaNew.getClass(), fkCarrinhoVendaNew.getIdVenda());
                carrinho.setFkCarrinhoVenda(fkCarrinhoVendaNew);
            }
            carrinho = em.merge(carrinho);
            if (fkUsuarioOld != null && !fkUsuarioOld.equals(fkUsuarioNew)) {
                fkUsuarioOld.getCarrinhoCollection().remove(carrinho);
                fkUsuarioOld = em.merge(fkUsuarioOld);
            }
            if (fkUsuarioNew != null && !fkUsuarioNew.equals(fkUsuarioOld)) {
                fkUsuarioNew.getCarrinhoCollection().add(carrinho);
                fkUsuarioNew = em.merge(fkUsuarioNew);
            }
            if (fkProdutoOld != null && !fkProdutoOld.equals(fkProdutoNew)) {
                fkProdutoOld.getCarrinhoCollection().remove(carrinho);
                fkProdutoOld = em.merge(fkProdutoOld);
            }
            if (fkProdutoNew != null && !fkProdutoNew.equals(fkProdutoOld)) {
                fkProdutoNew.getCarrinhoCollection().add(carrinho);
                fkProdutoNew = em.merge(fkProdutoNew);
            }
            if (fkCarrinhoVendaOld != null && !fkCarrinhoVendaOld.equals(fkCarrinhoVendaNew)) {
                fkCarrinhoVendaOld.getCarrinhoCollection().remove(carrinho);
                fkCarrinhoVendaOld = em.merge(fkCarrinhoVendaOld);
            }
            if (fkCarrinhoVendaNew != null && !fkCarrinhoVendaNew.equals(fkCarrinhoVendaOld)) {
                fkCarrinhoVendaNew.getCarrinhoCollection().add(carrinho);
                fkCarrinhoVendaNew = em.merge(fkCarrinhoVendaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carrinho.getIdCarrinho();
                if (findCarrinho(id) == null) {
                    throw new NonexistentEntityException("The carrinho with id " + id + " no longer exists.");
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
            Carrinho carrinho;
            try {
                carrinho = em.getReference(Carrinho.class, id);
                carrinho.getIdCarrinho();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carrinho with id " + id + " no longer exists.", enfe);
            }
            Usuario fkUsuario = carrinho.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario.getCarrinhoCollection().remove(carrinho);
                fkUsuario = em.merge(fkUsuario);
            }
            Produto fkProduto = carrinho.getFkProduto();
            if (fkProduto != null) {
                fkProduto.getCarrinhoCollection().remove(carrinho);
                fkProduto = em.merge(fkProduto);
            }
            CarrinhoVenda fkCarrinhoVenda = carrinho.getFkCarrinhoVenda();
            if (fkCarrinhoVenda != null) {
                fkCarrinhoVenda.getCarrinhoCollection().remove(carrinho);
                fkCarrinhoVenda = em.merge(fkCarrinhoVenda);
            }
            em.remove(carrinho);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carrinho> findCarrinhoEntities() {
        return findCarrinhoEntities(true, -1, -1);
    }

    public List<Carrinho> findCarrinhoEntities(int maxResults, int firstResult) {
        return findCarrinhoEntities(false, maxResults, firstResult);
    }

    private List<Carrinho> findCarrinhoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carrinho.class));
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

    public Carrinho findCarrinho(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrinho.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarrinhoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrinho> rt = cq.from(Carrinho.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
