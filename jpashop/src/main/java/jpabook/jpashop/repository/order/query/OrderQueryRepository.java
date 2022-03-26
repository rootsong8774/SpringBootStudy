package jpabook.jpashop.repository.order.query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager entityManager;


    public List<OrderQueryDTO> findOrderQueryDTOS() {

        List<OrderQueryDTO> result = findOrders();
        result.forEach(o -> {
            List<OrderItemQueryDTO> orderItems = findOrderItem(o.getOrderId());
            o.setOrderItems(orderItems);
        });

        return result;
    }

    private List<OrderItemQueryDTO> findOrderItem(Long orderId) {
        return entityManager.createQuery(
            "select new jpabook.jpashop.repository.order.query.OrderItemQueryDTO(oi.order.id, i.name, oi.orderPrice, oi.count)"
                + " from OrderItem oi"
                + " join oi.item i"
                + " where oi.order.id = :orderId" , OrderItemQueryDTO.class
        ).setParameter("orderId", orderId).getResultList();
    }

    private List<OrderQueryDTO> findOrders() {
        return entityManager.createQuery(
            "select new jpabook.jpashop.repository.order.query.OrderQueryDTO(o.id, m.name, o.orderDate, o.status, d.address) from Order o "
                + " join o.member m "
                + " join o.delivery d", OrderQueryDTO.class
        ).getResultList();
    }

    public List<OrderQueryDTO> findAllByDTO_optimization() {
        List<OrderQueryDTO> result = findOrders();

        List<Long> orderIds = toOrderIds(result);

        Map<Long, List<OrderItemQueryDTO>> orderItemMap = findOrderItemMap(
            orderIds);

        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));
        return result;
    }

    private Map<Long, List<OrderItemQueryDTO>> findOrderItemMap(List<Long> orderIds) {
        List<OrderItemQueryDTO> orderItems = entityManager.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderItemQueryDTO(oi.order.id, i.name, oi.orderPrice, oi.count)"
                    + " from OrderItem oi"
                    + " join oi.item i"
                    + " where oi.order.id in :orderIds"
                , OrderItemQueryDTO.class)
            .setParameter("orderIds", orderIds)
            .getResultList();

        return orderItems.stream()
            .collect(Collectors.groupingBy(OrderItemQueryDTO::getOrderId));
    }

    private List<Long> toOrderIds(List<OrderQueryDTO> result) {
        return result.stream().map(OrderQueryDTO::getOrderId).collect(Collectors.toList());
    }

    public List<OrderFlatDTO> findAllByDTO_flat() {
        return entityManager.createQuery(
            "select new "
                + " jpabook.jpashop.repository.order.query.OrderFlatDTO(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count)"
                + " from Order o "
                + " join o.member m "
                + " join o.delivery d "
                + " join o.orderItems oi "
                + " join oi.item i", OrderFlatDTO.class
        ).getResultList();
    }
}
