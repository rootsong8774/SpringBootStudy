package com.tacojdbctemplate.repository;

import com.tacojdbctemplate.domain.TacoOrder;
import java.sql.Types;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("insert into Taco_Order"
            + "(delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number,"
            + "cc_expiration, cc_cvv, placed_at) values (?,?,?,?,?,?,?,?,?)", Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
            );
        pscf.setReturnGeneratedKeys(true);
        return null;
    }

    @Override
    public Optional<TacoOrder> findById(Integer id) {
        return Optional.empty();
    }
}
