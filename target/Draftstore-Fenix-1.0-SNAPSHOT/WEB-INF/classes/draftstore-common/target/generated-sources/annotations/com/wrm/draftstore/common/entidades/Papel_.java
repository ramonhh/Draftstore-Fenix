package com.wrm.draftstore.common.entidades;

import com.wrm.draftstore.common.entidades.Funcionario;
import com.wrm.draftstore.common.entidades.Pagina;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-12T23:43:54")
@StaticMetamodel(Papel.class)
public class Papel_ { 

    public static volatile SingularAttribute<Papel, String> nomePapel;
    public static volatile SingularAttribute<Papel, Integer> idPapel;
    public static volatile CollectionAttribute<Papel, Funcionario> funcionarioCollection;
    public static volatile CollectionAttribute<Papel, Pagina> paginaCollection;

}