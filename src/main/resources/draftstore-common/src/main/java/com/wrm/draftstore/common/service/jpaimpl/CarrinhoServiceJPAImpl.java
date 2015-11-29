/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service.jpaimpl;

import com.wrm.draftstore.common.entidades.Carrinho;
import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.service.CarrinhoService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ramonhonorio
 */
public class CarrinhoServiceJPAImpl implements CarrinhoService {

    private EntityManagerFactory emFactory
            = Persistence.createEntityManagerFactory("com.wrm_draftstore-common_jar_1.0-SNAPSHOTPU");

    public CarrinhoServiceJPAImpl() {
    }

    @Override
    public Carrinho obter(Long idCarrinho) {
        EntityManager em = emFactory.createEntityManager();
        Query query = em.createNamedQuery("Carrinho.findByIdCarrinho")
                .setParameter("idCarrinho", idCarrinho);
        Carrinho c = (Carrinho) query.getSingleResult();
        em.close();
        return c;
    }

    @Override
    public void incluir(Carrinho c) {
        EntityManager em = emFactory.createEntityManager();
        EntityTransaction transacao = em.getTransaction();
        try {
            transacao.begin();
            em.persist(c);
            transacao.commit();
        } catch (Exception e) {
            transacao.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Carrinho> listarDoUsuario(Usuario u) {
        EntityManager em = emFactory.createEntityManager();
        Query query = em.createNamedQuery("Carrinho.findByUser")
                .setParameter("idUsuario", u.getIdUsuario());
        List<Carrinho> resultados = query.getResultList();
        em.close();
        return resultados;
    }

}
