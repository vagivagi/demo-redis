package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user/session")
public class UserSessionCompleteController {
    private static Logger log = LoggerFactory.getLogger(UserSessionCompleteController.class);

    @ModelAttribute("sessionId")
    String sessionId(HttpSession session) {
        return session.getId();
    }

    @GetMapping("complete")
    String complete(SessionStatus status) {
        status.setComplete();
        return "complete";
    }

    @GetMapping("discard")
    String discard(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.invalidate();
        return "complete";
    }
}
