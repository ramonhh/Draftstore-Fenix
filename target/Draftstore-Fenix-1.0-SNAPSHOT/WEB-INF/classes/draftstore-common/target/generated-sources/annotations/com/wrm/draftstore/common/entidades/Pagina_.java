package com.wrm.draftstore.common.entidades;

import com.wrm.draftstore.common.entidades.Papel;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-12T23:43:54")
@StaticMetamodel(Pagina.class)
public class Pagina_ { 

    public static volatile SingularAttribute<Pagina, Integer> idPagina;
    public static volatile SingularAttribute<Pagina, String> nomePagina;
    public static volatile SingularAttribute<Pagina, Papel> fkPapel;

}