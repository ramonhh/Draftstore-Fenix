package com.wrm.draftstore.common.entidades;

import com.wrm.draftstore.common.entidades.Funcionario;
import com.wrm.draftstore.common.entidades.Produto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-16T20:35:30")
@StaticMetamodel(Fornecedor.class)
public class Fornecedor_ { 

    public static volatile SingularAttribute<Fornecedor, String> site;
    public static volatile SingularAttribute<Fornecedor, Integer> idFornecedor;
    public static volatile SingularAttribute<Fornecedor, String> razaoSocial;
    public static volatile CollectionAttribute<Fornecedor, Produto> produtoCollection;
    public static volatile SingularAttribute<Fornecedor, String> cnpj;
    public static volatile SingularAttribute<Fornecedor, String> nomeUsuario;
    public static volatile SingularAttribute<Fornecedor, Integer> numero;
    public static volatile SingularAttribute<Fornecedor, String> bairro;
    public static volatile SingularAttribute<Fornecedor, String> cidade;
    public static volatile SingularAttribute<Fornecedor, String> cep;
    public static volatile SingularAttribute<Fornecedor, String> estado;
    public static volatile SingularAttribute<Fornecedor, String> email;
    public static volatile SingularAttribute<Fornecedor, String> telefone;
    public static volatile SingularAttribute<Fornecedor, Date> dataCriacao;
    public static volatile SingularAttribute<Fornecedor, Funcionario> fkFuncionario;
    public static volatile SingularAttribute<Fornecedor, String> endereco;

}