package com.wrm.draftstore.common.entidades;

import com.wrm.draftstore.common.entidades.Produto;
import com.wrm.draftstore.common.entidades.Subcategoria;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-16T20:35:30")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, Integer> idCategoria;
    public static volatile CollectionAttribute<Categoria, Subcategoria> subcategoriaCollection;
    public static volatile SingularAttribute<Categoria, String> nomeCategoria;
    public static volatile CollectionAttribute<Categoria, Produto> produtoCollection;

}