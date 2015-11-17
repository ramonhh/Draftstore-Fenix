package com.wrm.draftstore.common.entidades;

import com.wrm.draftstore.common.entidades.Fornecedor;
import com.wrm.draftstore.common.entidades.Log;
import com.wrm.draftstore.common.entidades.Papel;
import com.wrm.draftstore.common.entidades.Produto;
import com.wrm.draftstore.common.entidades.Venda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-13T20:41:58")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile SingularAttribute<Funcionario, String> telefone;
    public static volatile SingularAttribute<Funcionario, Boolean> ativo;
    public static volatile SingularAttribute<Funcionario, Papel> fkPapel;
    public static volatile CollectionAttribute<Funcionario, Fornecedor> fornecedorCollection;
    public static volatile SingularAttribute<Funcionario, String> nome;
    public static volatile CollectionAttribute<Funcionario, Produto> produtoCollection;
    public static volatile SingularAttribute<Funcionario, Integer> idFuncionario;
    public static volatile SingularAttribute<Funcionario, String> senha;
    public static volatile SingularAttribute<Funcionario, String> rg;
    public static volatile SingularAttribute<Funcionario, String> cpf;
    public static volatile SingularAttribute<Funcionario, String> celular;
    public static volatile CollectionAttribute<Funcionario, Log> logCollection;
    public static volatile SingularAttribute<Funcionario, Date> dataNascimento;
    public static volatile SingularAttribute<Funcionario, String> sexo;
    public static volatile SingularAttribute<Funcionario, Date> dataCriacao;
    public static volatile SingularAttribute<Funcionario, String> email;
    public static volatile CollectionAttribute<Funcionario, Venda> vendaCollection;

}