package tech.claudioed.domain.service;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import tech.claudioed.domain.Order;

@ApplicationScoped
public class TransactionEmitter {

  final TransactionCloudEventsSender transactionCloudEventsSender;

  @ConfigProperty(name = "ce.type")
  String ceType;

  @ConfigProperty(name = "ce.source")
  String ceSource;

  @ConfigProperty(name = "ce.spec.version")
  String ceSpecVersion;

  @ConfigProperty(name = "ce.content.type")
  String ceContentType;

  public TransactionEmitter(TransactionCloudEventsSender transactionCloudEventsSender) {
    this.transactionCloudEventsSender = transactionCloudEventsSender;
  }

  public void emit(Order order) {
    var transaction = order.transaction();
    this.transactionCloudEventsSender.send(this.ceType,this.ceSource,this.ceSpecVersion,String.valueOf(order.id),this.ceContentType,transaction);
  }

}
