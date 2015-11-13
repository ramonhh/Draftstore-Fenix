package com.wrm.draftstore.common.entidades;

import com.wrm.draftstore.common.entidades.Funcionario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-12T23:43:54")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, String> nomeUsuario;
    public static volatile SingularAttribute<Log, Funcionario> fkFuncionario;
    public static volatile SingularAttribute<Log, String> descricaoLog;
    public static volatile SingularAttribute<Log, Date> dataCriacao;
    public static volatile SingularAttribute<Log, Integer> idLog;

}