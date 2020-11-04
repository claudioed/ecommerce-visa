package tech.claudioed.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import tech.claudioed.domain.service.data.Transaction;

@Data
@Entity
@Table(name = "orders")
public class Order extends PanacheEntity {

  private LocalDateTime at;

  @ElementCollection
  @CollectionTable(name = "order_items")
  private Set<OrderItem> items;

  private String customerId;

  private String token;

  Order() {
    //do nothing
  }

  public Order(Set<OrderItem> items, String customerId,String token) {
    this.at = LocalDateTime.now();
    this.items = items;
    this.customerId = customerId;
    this.token = token;
  }

  public LocalDateTime getAt() {
    return at;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getToken() {
    return token;
  }

  private BigDecimal total() {
    return BigDecimal.valueOf(this.items.stream().mapToDouble(element -> element.value().doubleValue()).sum());
  }

  public Transaction transaction() {
    Transaction transaction = Transaction.builder().deviceType("WEB-ECOMMERCE-VISA")
        .value(total().toString()).type("CARD").subType("VISA").toAccount("000002453")
        .fromAccount(this.token).build();
    return transaction;
  }

}

