/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.jpaControllers;

import com.wrm.draftstore.common.entidades.Carrinho;
import com.wrm.draftstore.common.entidades.Cartao;
import com.wrm.draftstore.common.entidades.Endereco;
import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.entidades.exceptions.IllegalOrphanException;
import com.wrm.draftstore.common.entidades.exceptions.NonexistentEntityException;
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
        if (usuario.getCarrinhoCollection() == null) {
            usuario.setCarrinhoCollection(new ArrayList<Carrinho>());
        }
        if (usuario.getCartaoCollection() == null) {
            usuario.setCartaoCollection(new ArrayList<Cartao>());
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
            Collection<Carrinho> attachedCarrinhoCollection = new ArrayList<Carrinho>();
            for (Carrinho carrinhoCollectionCarrinhoToAttach : usuario.getCarrinhoCollection()) {
                carrinhoCollectionCarrinhoToAttach = em.getReference(carrinhoCollectionCarrinhoToAttach.getClass(), carrinhoCollectionCarrinhoToAttach.getIdCarrinho());
                attachedCarrinhoCollection.add(carrinhoCollectionCarrinhoToAttach);
            }
            usuario.setCarrinhoCollection(attachedCarrinhoCollection);
            Collection<Cartao> attachedCartaoCollection = new ArrayList<Cartao>();
            for (Cartao cartaoCollectionCartaoToAttach : usuario.getCartaoCollection()) {
                cartaoCollectionCartaoToAttach = em.getReference(cartaoCollectionCartaoToAttach.getClass(), cartaoCollectionCartaoToAttach.getIdCartao());
                attachedCartaoCollection.add(cartaoCollectionCartaoToAttach);
            }
            usuario.setCartaoCollection(attachedCartaoCollection);
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
            for (Carrinho carrinhoCollectionCarrinho : usuario.getCarrinhoCollection()) {
                Usuario oldFkUsuarioOfCarrinhoCollectionCarrinho = carrinhoCollectionCarrinho.getFkUsuario();
                carrinhoCollectionCarrinho.setFkUsuario(usuario);
                carrinhoCollectionCarrinho = em.merge(carrinhoCollectionCarrinho);
                if (oldFkUsuarioOfCarrinhoCollectionCarrinho != null) {
                    oldFkUsuarioOfCarrinhoCollectionCarrinho.getCarrinhoCollection().remove(carrinhoCollectionCarrinho);
                    oldFkUsuarioOfCarrinhoCollectionCarrinho = em.merge(oldFkUsuarioOfCarrinhoCollectionCarrinho);
                }
            }
            for (Cartao cartaoCollectionCartao : usuario.getCartaoCollection()) {
                Usuario oldFkUsuarioOfCartaoCollectionCartao = cartaoCollectionCartao.getFkUsuario();
                cartaoCollectionCartao.setFkUsuario(usuario);
                cartaoCollectionCartao = em.merge(cartaoCollectionCartao);
                if (oldFkUsuarioOfCartaoCollectionCartao != null) {
                    oldFkUsuarioOfCartaoCollectionCartao.getCartaoCollection().remove(cartaoCollectionCartao);
                    oldFkUsuarioOfCartaoCollectionCartao = em.merge(oldFkUsuarioOfCartaoCollectionCartao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Collection<Endereco> enderecoCollectionOld = persistentUsuario.getEnderecoCollection();
            Collection<Endereco> enderecoCollectionNew = usuario.getEnderecoCollection();
            Collection<Carrinho> carrinhoCollectionOld = persistentUsuario.getCarrinhoCollection();
            Collection<Carrinho> carrinhoCollectionNew = usuario.getCarrinhoCollection();
            Collection<Cartao> cartaoCollectionOld = persistentUsuario.getCartaoCollection();
            Collection<Cartao> cartaoCollectionNew = usuario.getCartaoCollection();
            List<String> illegalOrphanMessages = null;
            for (Carrinho carrinhoCollectionOldCarrinho : carrinhoCollectionOld) {
                if (!carrinhoCollectionNew.contains(carrinhoCollectionOldCarrinho)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Carrinho " + carrinhoCollectionOldCarrinho + " since its fkUsuario field is not nullable.");
                }
            }
            for (Cartao cartaoCollectionOldCartao : cartaoCollectionOld) {
                if (!cartaoCollectionNew.contains(cartaoCollectionOldCartao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cartao " + cartaoCollectionOldCartao + " since its fkUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Endereco> attachedEnderecoCollectionNew = new ArrayList<Endereco>();
            for (Endereco enderecoCollectionNewEnderecoToAttach : enderecoCollectionNew) {
                enderecoCollectionNewEnderecoToAttach = em.getReference(enderecoCollectionNewEnderecoToAttach.getClass(), enderecoCollectionNewEnderecoToAttach.getIdEndereco());
                attachedEnderecoCollectionNew.add(enderecoCollectionNewEnderecoToAttach);
            }
            enderecoCollectionNew = attachedEnderecoCollectionNew;
            usuario.setEnderecoCollection(enderecoCollectionNew);
            Collection<Carrinho> attachedCarrinhoCollectionNew = new ArrayList<Carrinho>();
            for (Carrinho carrinhoCollectionNewCarrinhoToAttach : carrinhoCollectionNew) {
                carrinhoCollectionNewCarrinhoToAttach = em.getReference(carrinhoCollectionNewCarrinhoToAttach.getClass(), carrinhoCollectionNewCarrinhoToAttach.getIdCarrinho());
                attachedCarrinhoCollectionNew.add(carrinhoCollectionNewCarrinhoToAttach);
            }
            carrinhoCollectionNew = attachedCarrinhoCollectionNew;
            usuario.setCarrinhoCollection(carrinhoCollectionNew);
            Collection<Cartao> attachedCartaoCollectionNew = new ArrayList<Cartao>();
            for (Cartao cartaoCollectionNewCartaoToAttach : cartaoCollectionNew) {
                cartaoCollectionNewCartaoToAttach = em.getReference(cartaoCollectionNewCartaoToAttach.getClass(), cartaoCollectionNewCartaoToAttach.getIdCartao());
                attachedCartaoCollectionNew.add(cartaoCollectionNewCartaoToAttach);
            }
            cartaoCollectionNew = attachedCartaoCollectionNew;
            usuario.setCartaoCollection(cartaoCollectionNew);
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
            for (Carrinho carrinhoCollectionNewCarrinho : carrinhoCollectionNew) {
                if (!carrinhoCollectionOld.contains(carrinhoCollectionNewCarrinho)) {
                    Usuario oldFkUsuarioOfCarrinhoCollectionNewCarrinho = carrinhoCollectionNewCarrinho.getFkUsuario();
                    carrinhoCollectionNewCarrinho.setFkUsuario(usuario);
                    carrinhoCollectionNewCarrinho = em.merge(carrinhoCollectionNewCarrinho);
                    if (oldFkUsuarioOfCarrinhoCollectionNewCarrinho != null && !oldFkUsuarioOfCarrinhoCollectionNewCarrinho.equals(usuario)) {
                        oldFkUsuarioOfCarrinhoCollectionNewCarrinho.getCarrinhoCollection().remove(carrinhoCollectionNewCarrinho);
                        oldFkUsuarioOfCarrinhoCollectionNewCarrinho = em.merge(oldFkUsuarioOfCarrinhoCollectionNewCarrinho);
                    }
                }
            }
            for (Cartao cartaoCollectionNewCartao : cartaoCollectionNew) {
                if (!cartaoCollectionOld.contains(cartaoCollectionNewCartao)) {
                    Usuario oldFkUsuarioOfCartaoCollectionNewCartao = cartaoCollectionNewCartao.getFkUsuario();
                    cartaoCollectionNewCartao.setFkUsuario(usuario);
                    cartaoCollectionNewCartao = em.merge(cartaoCollectionNewCartao);
                    if (oldFkUsuarioOfCartaoCollectionNewCartao != null && !oldFkUsuarioOfCartaoCollectionNewCartao.equals(usuario)) {
                        oldFkUsuarioOfCartaoCollectionNewCartao.getCartaoCollection().remove(cartaoCollectionNewCartao);
                        oldFkUsuarioOfCartaoCollectionNewCartao = em.merge(oldFkUsuarioOfCartaoCollectionNewCartao);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Carrinho> carrinhoCollectionOrphanCheck = usuario.getCarrinhoCollection();
            for (Carrinho carrinhoCollectionOrphanCheckCarrinho : carrinhoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Carrinho " + carrinhoCollectionOrphanCheckCarrinho + " in its carrinhoCollection field has a non-nullable fkUsuario field.");
            }
            Collection<Cartao> cartaoCollectionOrphanCheck = usuario.getCartaoCollection();
            for (Cartao cartaoCollectionOrphanCheckCartao : cartaoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Cartao " + cartaoCollectionOrphanCheckCartao + " in its cartaoCollection field has a non-nullable fkUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
    
    public Usuario realizarLogin(String email, String senha) {
        EntityManager em = getEntityManager();
        //    EntityManager em = emFactory.createEntityManager();
        Usuario resultado = null;
        Query query = em.createNamedQuery("Usuario.realizarLogin")
                .setParameter("email", email)
                .setParameter("senha", senha);
        try {
            resultado = (Usuario) query.getSingleResult();
        } catch (Exception ex) {
            System.out.println(">> Usuario não encontrado no banco. || " + ex);
        }
        em.close();
        return resultado;
    }
    
}
