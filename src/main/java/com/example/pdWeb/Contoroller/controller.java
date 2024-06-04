package com.example.pdWeb.Contoroller;

import com.example.pdWeb.Company.ICompanyService;
import com.example.pdWeb.User.IUserService;
import com.example.pdWeb.User.LoginForm;
import com.example.pdWeb.User.CreateForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class controller {
    @Autowired
    HttpSession session;
    @Autowired
    IUserService userService;
    @Autowired
    ICompanyService companyService;

    @GetMapping("/index")
    public String index(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "index";
    }

    @PostMapping("/index")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {

        // バリデーション
        if(bindingResult.hasErrors()) {
            return "index";
        }else {
            var user = userService.findByUser(loginForm);
            System.out.println(user);
            if(user == null) {
                model.addAttribute("errorMsg","IDまたはパスワードが不正です");
                return "index";
            }else{
                session.setAttribute("user",user);
                return "redirect:/menu";
            }
        }
    }
    @GetMapping("/menu")
    public String menu(){
        if(session.getAttribute("user") == null){
            return "redirect:/index";
        }else{
            System.out.println(companyService.findAll());
            return "menu";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/createUser")
    public String gCreateUser(@ModelAttribute("createForm") CreateForm createForm,Model model){
        model.addAttribute("companies",companyService.findAll());
        return "createUser";
    }

    @PostMapping("/createUser")
    public String pCreateUser(@Validated @ModelAttribute("createForm") CreateForm createForm,BindingResult bindingResult,Model model){
        System.out.println(createForm);
        if(bindingResult.hasErrors()){
            model.addAttribute("companies",companyService.findAll());
            return "createUser";
        }else{
            if(!createForm.getPassword().equals(createForm.getSubPassword())){
                model.addAttribute("passErrorMsg","確認用パスワードが違います");
                model.addAttribute("companies",companyService.findAll());
                return "createUser";
            }
            try {
                userService.insert(createForm);

                LoginForm loginForm = new LoginForm();
                loginForm.setLoginId(createForm.getLoginId());
                loginForm.setPassword(createForm.getPassword());

                var user = userService.findByUser(loginForm);
                System.out.println(user);
                if(user == null){
                    throw new RuntimeException();
                }session.setAttribute("user",user);
                return"redirect:/menu";

            }catch (RuntimeException e){
                model.addAttribute("errorMsg","ログインIDが存在します");
                model.addAttribute("companies",companyService.findAll());
                return "createUser";
            }
        }
    }
}
