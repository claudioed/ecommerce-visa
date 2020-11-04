package tech.claudioed.domain.resource;

import java.net.URI;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import tech.claudioed.domain.Order;
import tech.claudioed.domain.resource.data.OrderRequest;
import tech.claudioed.domain.service.OrderService;

@Path("/api/orders")
public class OrderResource {


  final OrderService orderService;

  @Inject
  public OrderResource(OrderService orderService) {
    this.orderService = orderService;
  }

  @POST
  public Response newOrder(OrderRequest request){
    var order = this.orderService.newOrder(request);
    return Response.created(URI.create("/api/orders/" + order.id)).entity(order).build();
  }

  @GET
  @Path("/{id}")
  public Response order(@PathParam("id") Long id){
    return Response.ok().entity(this.orderService.order(id)).build();
  }

  @GET
  public Response orders(){
    return Response.ok().entity(this.orderService.orders()).build();
  }


}
