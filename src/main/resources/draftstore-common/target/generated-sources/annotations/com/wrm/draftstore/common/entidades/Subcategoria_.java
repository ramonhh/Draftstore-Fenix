package com.wrm.draftstore.common.entidades;

import com.wrm.draftstore.common.entidades.Categoria;
import com.wrm.draftstore.common.entidades.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-17T22:33:32")
@StaticMetamodel(Subcategoria.class)
public class Subcategoria_ { 

    public static volatile SingularAttribute<Subcategoria, Categoria> fkCategoria;
    public static volatile SingularAttribute<Subcategoria, Integer> idSubcategoria;
    public static volatile CollectionAttribute<Subcategoria, Produto> produtoCollection;
    public static volatile SingularAttribute<Subcategoria, String> nomeSubcategoria;

}