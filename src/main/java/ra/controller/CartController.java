package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.OrderDetail;
import ra.model.entity.Product;
import ra.model.entity.UserLogin;
import ra.model.service.cart.CartServiceImp;
import ra.model.service.product.ProductServiceImp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("cartController")
public class CartController {
    CartServiceImp cartService = new CartServiceImp();
    ProductServiceImp productService = new ProductServiceImp();
    @GetMapping("")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserLogin user = (UserLogin) session.getAttribute("userLogin");
        List<OrderDetail> list = cartService.findCartByOrderId(user.getCartId());
        model.addAttribute("listcart",list);
        return "cart";
    }
    @GetMapping("/add-to-cart/{productid}")
    public String addCart(@PathVariable("productid") String id,HttpServletRequest request ){
        HttpSession session = request.getSession();
        UserLogin user = (UserLogin) session.getAttribute("userLogin");
        OrderDetail o = cartService.checkExistProduct(Integer.valueOf(id),user.getCartId());
        if (o!=null){
            // đã tồn tại
            o.setQuantity(o.getQuantity()+1);
            cartService.update(o);
        }else {
            Product product = productService.findById(Integer.valueOf(id));
            OrderDetail orderDetail =new OrderDetail(0,user.getCartId(), product.getProductId(), product.getProductPrice(),1);
            cartService.save(orderDetail);
        }
            return "redirect:/cartController/";

    }
    @GetMapping("/update")
    public String update(@RequestParam("odId") String id,@RequestParam("quantity") String quantity){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(Integer.parseInt(id));
        orderDetail.setQuantity(Integer.parseInt(quantity));
        cartService.update(orderDetail);
        return "redirect:/cartController";
    }
    @GetMapping("/delete/{idDel}")
    public String delete(@PathVariable("idDel") String idDel){
        cartService.delete(Integer.valueOf(idDel));
        return "redirect:/cartController";
    }
}
