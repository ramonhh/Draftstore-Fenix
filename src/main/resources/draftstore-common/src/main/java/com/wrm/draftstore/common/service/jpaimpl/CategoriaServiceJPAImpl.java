/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.common.service.jpaimpl;

import com.wrm.draftstore.common.entidades.Categoria;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author maikon_silva
 */
public class CategoriaServiceJPAImpl implements Serializable{
    
    private EntityManagerFactory emFactory = 
          Persistence.createEntityManagerFactory("com.wrm_draftstore-common_jar_1.0-SNAPSHOTPU");

  public CategoriaServiceJPAImpl() {
  }

  public List<Categoria> listar() {
    EntityManager em = emFactory.createEntityManager();
    Query query = em.createNamedQuery("Categoria.findAll");
    List<Categoria> resultados = query.getResultList();
      for (int i = 0; i < resultados.size(); i++) {
          System.out.println("Categoria e nome: " + resultados.get(i).getNomeCategoria());
      }
    em.close();
    return resultados;
  }
    
}


