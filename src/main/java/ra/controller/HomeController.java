package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.entity.Product;
import ra.model.service.product.ProductServiceImp;

import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {
    private ProductServiceImp productService = new ProductServiceImp();
    @GetMapping("/")
    public String index(Model model) {
        List<Product> list = productService.findAll();
        model.addAttribute("listProduct",list);
        return "home";
    }
}
