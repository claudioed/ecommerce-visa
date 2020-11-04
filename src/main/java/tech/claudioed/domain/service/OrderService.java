package tech.claudioed.domain.service;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import tech.claudioed.domain.Order;
import tech.claudioed.domain.resource.data.OrderRequest;

@ApplicationScoped
public class OrderService {

  final TransactionEmitter transactionEmitter;

  @Inject
  public OrderService(TransactionEmitter transactionEmitter) {
    this.transactionEmitter = transactionEmitter;
  }

  @Transactional
  public Order newOrder(OrderRequest request) {
    var order = request.toOrder();
    order.persist();
    this.transactionEmitter.emit(order);
    return order;
  }

  public List<Order> orders() {
    return Order.listAll();
  }

  public Optional<Order> order(Long id) {
    Order order = Order.findById(id);
    return Optional.ofNullable(order);
  }

}
