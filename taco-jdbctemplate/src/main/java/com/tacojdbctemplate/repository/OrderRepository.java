package com.tacojdbctemplate.repository;

import com.tacojdbctemplate.domain.TacoOrder;
import java.util.Optional;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);

    Optional<TacoOrder> findById(Integer id);

}
