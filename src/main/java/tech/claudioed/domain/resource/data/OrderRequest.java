package tech.claudioed.domain.resource.data;

import java.util.Set;
import lombok.Data;
import tech.claudioed.domain.Order;
import tech.claudioed.domain.OrderItem;

@Data
public class OrderRequest{

  private Set<OrderItem> items;

  private String customerId;

  private String token;

  public Order toOrder() {
    return new Order(this.items,this.customerId,this.token);
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

}
