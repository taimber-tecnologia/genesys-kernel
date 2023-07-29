package br.com.salomaotech.sistema.jpa;

import java.sql.Types;
import org.hibernate.dialect.DerbyTenSevenDialect;

public class FixedDerbyDialect extends DerbyTenSevenDialect {

    /* corrige o problema do dialeto do Derby em relação ao clob */
    public FixedDerbyDialect() {

        super();
        registerColumnType(Types.CLOB, "clob");

    }

}
