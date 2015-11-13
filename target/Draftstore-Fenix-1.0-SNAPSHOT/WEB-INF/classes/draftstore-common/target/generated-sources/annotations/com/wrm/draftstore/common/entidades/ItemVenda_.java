package com.wrm.draftstore.common.entidades;

import com.wrm.draftstore.common.entidades.Produto;
import com.wrm.draftstore.common.entidades.Venda;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-12T23:43:54")
@StaticMetamodel(ItemVenda.class)
public class ItemVenda_ { 

    public static volatile SingularAttribute<ItemVenda, BigDecimal> preco;
    public static volatile SingularAttribute<ItemVenda, Integer> idItemVenda;
    public static volatile SingularAttribute<ItemVenda, Venda> fkVenda;
    public static volatile SingularAttribute<ItemVenda, Produto> fkProduto;
    public static volatile SingularAttribute<ItemVenda, Integer> quantidade;
    public static volatile SingularAttribute<ItemVenda, String> nomeProduto;

}