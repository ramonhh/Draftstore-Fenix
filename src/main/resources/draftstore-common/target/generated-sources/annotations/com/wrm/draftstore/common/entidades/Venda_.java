package com.wrm.draftstore.common.entidades;

import com.wrm.draftstore.common.entidades.Funcionario;
import com.wrm.draftstore.common.entidades.ItemVenda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-17T22:33:32")
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, String> nomeUsuario;
    public static volatile CollectionAttribute<Venda, ItemVenda> itemVendaCollection;
    public static volatile SingularAttribute<Venda, Funcionario> fkFuncionario;
    public static volatile SingularAttribute<Venda, Date> dataCriacao;
    public static volatile SingularAttribute<Venda, Integer> idVenda;

}