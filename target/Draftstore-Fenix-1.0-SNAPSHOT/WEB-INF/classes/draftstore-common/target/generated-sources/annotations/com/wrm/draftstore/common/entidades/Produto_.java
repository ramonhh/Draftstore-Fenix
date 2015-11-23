package com.wrm.draftstore.common.entidades;

import com.wrm.draftstore.common.entidades.Categoria;
import com.wrm.draftstore.common.entidades.Fornecedor;
import com.wrm.draftstore.common.entidades.Funcionario;
import com.wrm.draftstore.common.entidades.ItemVenda;
import com.wrm.draftstore.common.entidades.Subcategoria;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-17T21:03:30")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile SingularAttribute<Produto, BigDecimal> precoPromo;
    public static volatile SingularAttribute<Produto, BigDecimal> precoVenda;
    public static volatile SingularAttribute<Produto, String> caminhoImagem;
    public static volatile SingularAttribute<Produto, Fornecedor> fkFornecedor;
    public static volatile SingularAttribute<Produto, Funcionario> fkFuncionario;
    public static volatile SingularAttribute<Produto, Subcategoria> fkSubcategoria;
    public static volatile SingularAttribute<Produto, String> descricaoImagem;
    public static volatile SingularAttribute<Produto, String> modelo;
    public static volatile SingularAttribute<Produto, String> descricao;
    public static volatile SingularAttribute<Produto, BigDecimal> percentualLucro;
    public static volatile SingularAttribute<Produto, String> marca;
    public static volatile SingularAttribute<Produto, String> nomeUsuario;
    public static volatile SingularAttribute<Produto, Integer> idProduto;
    public static volatile SingularAttribute<Produto, String> nomeFornecedor;
    public static volatile CollectionAttribute<Produto, ItemVenda> itemVendaCollection;
    public static volatile SingularAttribute<Produto, Date> dataEventoIni;
    public static volatile SingularAttribute<Produto, BigDecimal> custo;
    public static volatile SingularAttribute<Produto, Categoria> fkCategoria;
    public static volatile SingularAttribute<Produto, Date> dataEventoFim;
    public static volatile SingularAttribute<Produto, Integer> quantidade;
    public static volatile SingularAttribute<Produto, Date> dataCriacao;

}