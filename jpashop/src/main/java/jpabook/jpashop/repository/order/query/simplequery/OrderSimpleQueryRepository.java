package jpabook.jpashop.repository.order.query.simplequery;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 복잡한 쿼리나 구체적인 쿼리를 다루어야할 때를 위한 repository (유지, 보수성이 낮은 repository)
 */
@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager entityManager;

    public List<OrderSimpleQueryDTO> findOrderDtos() {
        return entityManager.createQuery(
                "select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDTO(o.id, m.name, o.orderDate, o.status, d.address) "
                    + " from Order o "
                    + " join o.member m"
                    + " join o.delivery d", OrderSimpleQueryDTO.class)
            .getResultList();
    }
}
