/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.jpaControllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.wrm.draftstore.common.entidades.TipoPagamento;
import com.wrm.draftstore.common.entidades.Endereco;
import com.wrm.draftstore.common.entidades.Cartao;
import com.wrm.draftstore.common.entidades.Carrinho;
import com.wrm.draftstore.common.entidades.CarrinhoVenda;
import com.wrm.draftstore.common.jpaControllers.exceptions.IllegalOrphanException;
import com.wrm.draftstore.common.jpaControllers.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Wilson A. Oliveira
 */
public class CarrinhoVendaJpaController implements Serializable {

    public CarrinhoVendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CarrinhoVenda carrinhoVenda) {
        if (carrinhoVenda.getCarrinhoCollection() == null) {
            carrinhoVenda.setCarrinhoCollection(new ArrayList<Carrinho>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoPagamento fkTipoPagamento = carrinhoVenda.getFkTipoPagamento();
            if (fkTipoPagamento != null) {
                fkTipoPagamento = em.getReference(fkTipoPagamento.getClass(), fkTipoPagamento.getIdTipoPagamento());
                carrinhoVenda.setFkTipoPagamento(fkTipoPagamento);
            }
            Endereco fkEndereco = carrinhoVenda.getFkEndereco();
            if (fkEndereco != null) {
                fkEndereco = em.getReference(fkEndereco.getClass(), fkEndereco.getIdEndereco());
                carrinhoVenda.setFkEndereco(fkEndereco);
            }
            Cartao fkCartao = carrinhoVenda.getFkCartao();
            if (fkCartao != null) {
                fkCartao = em.getReference(fkCartao.getClass(), fkCartao.getIdCartao());
                carrinhoVenda.setFkCartao(fkCartao);
            }
            Collection<Carrinho> attachedCarrinhoCollection = new ArrayList<Carrinho>();
            for (Carrinho carrinhoCollectionCarrinhoToAttach : carrinhoVenda.getCarrinhoCollection()) {
                carrinhoCollectionCarrinhoToAttach = em.getReference(carrinhoCollectionCarrinhoToAttach.getClass(), carrinhoCollectionCarrinhoToAttach.getIdCarrinho());
                attachedCarrinhoCollection.add(carrinhoCollectionCarrinhoToAttach);
            }
            carrinhoVenda.setCarrinhoCollection(attachedCarrinhoCollection);
            em.persist(carrinhoVenda);
            if (fkTipoPagamento != null) {
                fkTipoPagamento.getCarrinhoVendaCollection().add(carrinhoVenda);
                fkTipoPagamento = em.merge(fkTipoPagamento);
            }
            if (fkEndereco != null) {
                fkEndereco.getCarrinhoVendaCollection().add(carrinhoVenda);
                fkEndereco = em.merge(fkEndereco);
            }
            if (fkCartao != null) {
                fkCartao.getCarrinhoVendaCollection().add(carrinhoVenda);
                fkCartao = em.merge(fkCartao);
            }
            for (Carrinho carrinhoCollectionCarrinho : carrinhoVenda.getCarrinhoCollection()) {
                CarrinhoVenda oldFkCarrinhoVendaOfCarrinhoCollectionCarrinho = carrinhoCollectionCarrinho.getFkCarrinhoVenda();
                carrinhoCollectionCarrinho.setFkCarrinhoVenda(carrinhoVenda);
                carrinhoCollectionCarrinho = em.merge(carrinhoCollectionCarrinho);
                if (oldFkCarrinhoVendaOfCarrinhoCollectionCarrinho != null) {
                    oldFkCarrinhoVendaOfCarrinhoCollectionCarrinho.getCarrinhoCollection().remove(carrinhoCollectionCarrinho);
                    oldFkCarrinhoVendaOfCarrinhoCollectionCarrinho = em.merge(oldFkCarrinhoVendaOfCarrinhoCollectionCarrinho);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CarrinhoVenda carrinhoVenda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CarrinhoVenda persistentCarrinhoVenda = em.find(CarrinhoVenda.class, carrinhoVenda.getIdVenda());
            TipoPagamento fkTipoPagamentoOld = persistentCarrinhoVenda.getFkTipoPagamento();
            TipoPagamento fkTipoPagamentoNew = carrinhoVenda.getFkTipoPagamento();
            Endereco fkEnderecoOld = persistentCarrinhoVenda.getFkEndereco();
            Endereco fkEnderecoNew = carrinhoVenda.getFkEndereco();
            Cartao fkCartaoOld = persistentCarrinhoVenda.getFkCartao();
            Cartao fkCartaoNew = carrinhoVenda.getFkCartao();
            Collection<Carrinho> carrinhoCollectionOld = persistentCarrinhoVenda.getCarrinhoCollection();
            Collection<Carrinho> carrinhoCollectionNew = carrinhoVenda.getCarrinhoCollection();
            List<String> illegalOrphanMessages = null;
            for (Carrinho carrinhoCollectionOldCarrinho : carrinhoCollectionOld) {
                if (!carrinhoCollectionNew.contains(carrinhoCollectionOldCarrinho)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Carrinho " + carrinhoCollectionOldCarrinho + " since its fkCarrinhoVenda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkTipoPagamentoNew != null) {
                fkTipoPagamentoNew = em.getReference(fkTipoPagamentoNew.getClass(), fkTipoPagamentoNew.getIdTipoPagamento());
                carrinhoVenda.setFkTipoPagamento(fkTipoPagamentoNew);
            }
            if (fkEnderecoNew != null) {
                fkEnderecoNew = em.getReference(fkEnderecoNew.getClass(), fkEnderecoNew.getIdEndereco());
                carrinhoVenda.setFkEndereco(fkEnderecoNew);
            }
            if (fkCartaoNew != null) {
                fkCartaoNew = em.getReference(fkCartaoNew.getClass(), fkCartaoNew.getIdCartao());
                carrinhoVenda.setFkCartao(fkCartaoNew);
            }
            Collection<Carrinho> attachedCarrinhoCollectionNew = new ArrayList<Carrinho>();
            for (Carrinho carrinhoCollectionNewCarrinhoToAttach : carrinhoCollectionNew) {
                carrinhoCollectionNewCarrinhoToAttach = em.getReference(carrinhoCollectionNewCarrinhoToAttach.getClass(), carrinhoCollectionNewCarrinhoToAttach.getIdCarrinho());
                attachedCarrinhoCollectionNew.add(carrinhoCollectionNewCarrinhoToAttach);
            }
            carrinhoCollectionNew = attachedCarrinhoCollectionNew;
            carrinhoVenda.setCarrinhoCollection(carrinhoCollectionNew);
            carrinhoVenda = em.merge(carrinhoVenda);
            if (fkTipoPagamentoOld != null && !fkTipoPagamentoOld.equals(fkTipoPagamentoNew)) {
                fkTipoPagamentoOld.getCarrinhoVendaCollection().remove(carrinhoVenda);
                fkTipoPagamentoOld = em.merge(fkTipoPagamentoOld);
            }
            if (fkTipoPagamentoNew != null && !fkTipoPagamentoNew.equals(fkTipoPagamentoOld)) {
                fkTipoPagamentoNew.getCarrinhoVendaCollection().add(carrinhoVenda);
                fkTipoPagamentoNew = em.merge(fkTipoPagamentoNew);
            }
            if (fkEnderecoOld != null && !fkEnderecoOld.equals(fkEnderecoNew)) {
                fkEnderecoOld.getCarrinhoVendaCollection().remove(carrinhoVenda);
                fkEnderecoOld = em.merge(fkEnderecoOld);
            }
            if (fkEnderecoNew != null && !fkEnderecoNew.equals(fkEnderecoOld)) {
                fkEnderecoNew.getCarrinhoVendaCollection().add(carrinhoVenda);
                fkEnderecoNew = em.merge(fkEnderecoNew);
            }
            if (fkCartaoOld != null && !fkCartaoOld.equals(fkCartaoNew)) {
                fkCartaoOld.getCarrinhoVendaCollection().remove(carrinhoVenda);
                fkCartaoOld = em.merge(fkCartaoOld);
            }
            if (fkCartaoNew != null && !fkCartaoNew.equals(fkCartaoOld)) {
                fkCartaoNew.getCarrinhoVendaCollection().add(carrinhoVenda);
                fkCartaoNew = em.merge(fkCartaoNew);
            }
            for (Carrinho carrinhoCollectionNewCarrinho : carrinhoCollectionNew) {
                if (!carrinhoCollectionOld.contains(carrinhoCollectionNewCarrinho)) {
                    CarrinhoVenda oldFkCarrinhoVendaOfCarrinhoCollectionNewCarrinho = carrinhoCollectionNewCarrinho.getFkCarrinhoVenda();
                    carrinhoCollectionNewCarrinho.setFkCarrinhoVenda(carrinhoVenda);
                    carrinhoCollectionNewCarrinho = em.merge(carrinhoCollectionNewCarrinho);
                    if (oldFkCarrinhoVendaOfCarrinhoCollectionNewCarrinho != null && !oldFkCarrinhoVendaOfCarrinhoCollectionNewCarrinho.equals(carrinhoVenda)) {
                        oldFkCarrinhoVendaOfCarrinhoCollectionNewCarrinho.getCarrinhoCollection().remove(carrinhoCollectionNewCarrinho);
                        oldFkCarrinhoVendaOfCarrinhoCollectionNewCarrinho = em.merge(oldFkCarrinhoVendaOfCarrinhoCollectionNewCarrinho);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carrinhoVenda.getIdVenda();
                if (findCarrinhoVenda(id) == null) {
                    throw new NonexistentEntityException("The carrinhoVenda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CarrinhoVenda carrinhoVenda;
            try {
                carrinhoVenda = em.getReference(CarrinhoVenda.class, id);
                carrinhoVenda.getIdVenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carrinhoVenda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Carrinho> carrinhoCollectionOrphanCheck = carrinhoVenda.getCarrinhoCollection();
            for (Carrinho carrinhoCollectionOrphanCheckCarrinho : carrinhoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CarrinhoVenda (" + carrinhoVenda + ") cannot be destroyed since the Carrinho " + carrinhoCollectionOrphanCheckCarrinho + " in its carrinhoCollection field has a non-nullable fkCarrinhoVenda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoPagamento fkTipoPagamento = carrinhoVenda.getFkTipoPagamento();
            if (fkTipoPagamento != null) {
                fkTipoPagamento.getCarrinhoVendaCollection().remove(carrinhoVenda);
                fkTipoPagamento = em.merge(fkTipoPagamento);
            }
            Endereco fkEndereco = carrinhoVenda.getFkEndereco();
            if (fkEndereco != null) {
                fkEndereco.getCarrinhoVendaCollection().remove(carrinhoVenda);
                fkEndereco = em.merge(fkEndereco);
            }
            Cartao fkCartao = carrinhoVenda.getFkCartao();
            if (fkCartao != null) {
                fkCartao.getCarrinhoVendaCollection().remove(carrinhoVenda);
                fkCartao = em.merge(fkCartao);
            }
            em.remove(carrinhoVenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CarrinhoVenda> findCarrinhoVendaEntities() {
        return findCarrinhoVendaEntities(true, -1, -1);
    }

    public List<CarrinhoVenda> findCarrinhoVendaEntities(int maxResults, int firstResult) {
        return findCarrinhoVendaEntities(false, maxResults, firstResult);
    }

    private List<CarrinhoVenda> findCarrinhoVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CarrinhoVenda.class));
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

    public CarrinhoVenda findCarrinhoVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CarrinhoVenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarrinhoVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CarrinhoVenda> rt = cq.from(CarrinhoVenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
