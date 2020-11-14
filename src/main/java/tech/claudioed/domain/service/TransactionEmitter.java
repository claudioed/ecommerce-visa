package tech.claudioed.domain.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
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

  @Inject
  public TransactionEmitter(@RestClient TransactionCloudEventsSender transactionCloudEventsSender) {
    this.transactionCloudEventsSender = transactionCloudEventsSender;
  }

  public void emit(Order order) {
    var transaction = order.transaction();
    this.transactionCloudEventsSender.send(this.ceType,this.ceSource,this.ceSpecVersion,String.valueOf(order.id),this.ceContentType,transaction);
  }

}
