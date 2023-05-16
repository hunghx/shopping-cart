package ra.model.service.cart;

import ra.model.entity.OrderDetail;
import ra.model.entity.Product;
import ra.model.util.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImp implements ICartService {
    @Override
    public List<OrderDetail> findAll() {
        return null;
    }

    @Override
    public List<OrderDetail> findCartByOrderId(Integer orderId) {
        List<OrderDetail> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindListOrderDetail(?)}");
            callSt.setInt(1, orderId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setProductId(rs.getInt("product_id"));
                orderDetail.setPrice(rs.getFloat("product_price"));
                orderDetail.setProductName(rs.getString("name"));
                orderDetail.setDescription(rs.getString("description"));
                orderDetail.setImageUrl(rs.getString("image_url"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                list.add(orderDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean save(OrderDetail orderDetail) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_CreateOrderDetail(?,?,?,?)}");
            callSt.setInt(1, orderDetail.getOrderId());
            callSt.setInt(2, orderDetail.getProductId());
            callSt.setFloat(3, orderDetail.getPrice());
            callSt.setInt(4, orderDetail.getQuantity());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean update(OrderDetail orderDetail) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_ChangeQuantity(?,?)}");
            callSt.setInt(1, orderDetail.getId());
            callSt.setInt(2, orderDetail.getQuantity());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public OrderDetail findById(Integer integer) {
        return null;
    }

    @Override
    public OrderDetail checkExistProduct(Integer proId, Integer cartId) {
        List<OrderDetail> list = findCartByOrderId(cartId);
        for (OrderDetail o : list) {
            if (o.getProductId() == proId) {
                return o;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_DeleteOrderDetail(?)}");
            callSt.setInt(1, integer);
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
