package ra.model.service.product;

import ra.model.entity.Product;
import ra.model.entity.Student;
import ra.model.util.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImp implements IProductService{

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt= conn.prepareCall("{call PROC_FindAllProduct()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p =new Product();
                p.setProductId(rs.getInt("id"));
                p.setProductName(rs.getString("name"));
                p.setImageUrl(rs.getString("image_url"));
                p.setProductPrice(rs.getFloat("price"));
                p.setProductQuantity(rs.getInt("quantity"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getBoolean("status"));
                list.add(p);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                ConnectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean save(Product product) {
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public Product findById(Integer integer) {
        Connection conn = null;
        Product product = null;
        try {
            conn= ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindProductById(?)}");
            callSt.setInt(1,integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                product= new Product();
                product.setProductId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setImageUrl(rs.getString("image_url"));
                product.setProductPrice(rs.getFloat("price"));
                product.setProductQuantity(rs.getInt("quantity"));
                product.setDescription(rs.getString("description"));
                product.setStatus(rs.getBoolean("status"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}
