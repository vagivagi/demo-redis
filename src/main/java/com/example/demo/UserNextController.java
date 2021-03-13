package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("userForm")
@RequestMapping("user/next")
public class UserNextController {
  @ModelAttribute("sessionId")
  String sessionId(HttpSession session){
    return session.getId();
  }

  @GetMapping("complete")
  String nextComplete(SessionStatus sessionStatus) {
    sessionStatus.setComplete();
    return "nextComplete";
  }
  @GetMapping("discard")
  String nextDiscard(HttpSession session) {
    session.invalidate();
    return "nextComplete";
  }
}
