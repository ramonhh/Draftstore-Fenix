/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service.jpaimpl;

import com.wrm.draftstore.common.entidades.CarrinhoVenda;
import com.wrm.draftstore.common.service.CarrinhoVendaService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author ramonhonorio
 */
public class CarrinhoVendaServiceJPAImpl implements CarrinhoVendaService {

    private EntityManagerFactory emFactory
            = Persistence.createEntityManagerFactory("com.wrm_draftstore-common_jar_1.0-SNAPSHOTPU");

    public CarrinhoVendaServiceJPAImpl() {
    }

    @Override
    public CarrinhoVenda incluir(CarrinhoVenda cv) {
        EntityManager em = emFactory.createEntityManager();
        EntityTransaction transacao = em.getTransaction();
        try {
            transacao.begin();
            em.persist(cv);
            em.flush();
            transacao.commit();
            return cv;
        } catch (Exception e) {
            transacao.rollback();
            return null;
        } finally {
            em.close();
        }
    }
}
