package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("userForm")
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @ModelAttribute("sessionId")
    String sessionId(HttpSession session) {
        return session.getId();
    }

    @GetMapping("input")
    String inputForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String requestSessionId = request.getRequestedSessionId();
        if (session == null) {
            log.info("session is null");
        } else if (requestSessionId == null) {
            log.info("request session id is null");
        } else if (!request.isRequestedSessionIdValid()) {
            log.info("request session id={} is invalid", requestSessionId);
        } else if (!requestSessionId.equals(session.getId())) {
            log.info("request session id={} and session id={} are invalid", requestSessionId, session.getId());
        } else {
            log.info("session is not timeout. request session id={}, session id={}", requestSessionId, session.getId());
        }
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

    @GetMapping("complete")
    String setComplete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "detail";
    }

    @GetMapping("discard")
    String discard(HttpSession session) {
        session.invalidate();
        return "detail";
    }

    @ExceptionHandler(HttpSessionRequiredException.class)
    @ResponseBody
    String noSession(HttpSessionRequiredException e, HttpSession session) {
        log.error("セッションエラー発生", e);
        return "セッションオブジェクトが取得できませんでした。セッションID: " + session.getId();
    }
}
