package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.service.user.IUserService;
import ra.model.service.user.UserServiceImp;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/userController")
public class UserController {
    private IUserService userService = new UserServiceImp();
    @GetMapping("/form-login")
    public String formLogin(){
        return "form-login";
    }
    @GetMapping("/form-register")
    public String formRegister(){
        return "form-register";
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request) {
        if (username.trim().equals("")||password.trim().equals("")){
            model.addAttribute("login","Not Required");
            return "form-login";
        }
        UserLogin user = userService.login(new User(username, password));
        if (user == null){
            model.addAttribute("login","Username or password incorrect");
            return "form-login";
        }else {
           request.getSession().setAttribute("userLogin",user);
           if (user.getRole()==1){
               // đây là admin
               return "admin";
           }else if (user.getRole()==0) {
               // đây là người dùng
               return "redirect:/";
           }else {
               return "home";
           }
        }


    }
    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        if (username.trim().equals("")){
            model.addAttribute("username","Not Required");
            return "form-register";
        }else if (userService.checkExistsUsername(username)){
            model.addAttribute("username","Username is Exist");
            return "form-register";
        }
        if (password.trim().equals("")) {
            model.addAttribute("password","Not Required");
            return "form-register";
        }
            userService.save(new User(username,password));
            return "redirect:form-login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("userlogin");
        return "home";
    }
}
