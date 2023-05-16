package ra.model.service.cart;

import ra.model.entity.OrderDetail;
import ra.model.service.IService;

import java.util.List;

public interface ICartService extends IService<OrderDetail,Integer> {
List<OrderDetail> findCartByOrderId(Integer orderId);
OrderDetail checkExistProduct(Integer proId,Integer cartId);
}
