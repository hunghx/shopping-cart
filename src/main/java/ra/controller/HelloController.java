package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("helloController")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "home";
    }
    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }
}
