package siit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
//    @GetMapping
    protected ModelAndView displayLoginForm() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("/create")
    protected ModelAndView createAccount(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/create-user");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView performLogin(HttpSession session, @RequestParam("user") String userName, @RequestParam String password) {
        ModelAndView mav = new ModelAndView();

        if (userName.equals(password)) {
            //logare cu succes
//            mav.addObject("logged_user", userName);
            session.setAttribute("logged_user", userName);
            mav.setViewName("redirect:/customers");
        } else {
            String errorMessage = "User and password do not match!";
            mav.addObject("error", errorMessage);
            mav.setViewName("login");
        }
        return mav;


    }
}
