package siit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

@Controller("/logout")
public class LogoutController{
    @RequestMapping("/logout")
    protected ModelAndView logout(HttpSession httpSession){
        ModelAndView mav = new ModelAndView();
        httpSession.invalidate();
        mav.setViewName("redirect:/login");
        return mav;
    }
}
