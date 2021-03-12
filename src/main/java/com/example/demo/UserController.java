package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("userForm")
@RequestMapping("user")
public class UserController {

  @GetMapping("input")
  String inputForm(Model model) {
    UserForm user = new UserForm();
    model.addAttribute("userForm", user);
    return "form";
  }

  @PostMapping("input")
  String input(@ModelAttribute("userForm") UserForm userForm, Model model) {
    return "redirect:/user/detail";
  }

  @GetMapping("detail")
  String detail(@ModelAttribute("userForm") UserForm user, Model model) {
    model.addAttribute("userForm", user);
    return "detail";
  }

  @GetMapping("delete")
  String delete(SessionStatus sessionStatus, HttpSession session) {
    sessionStatus.setComplete();
    return "detail";
  }
}
