package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CompleteController {
    @GetMapping("complete")
    String complete(HttpSession session) {
        session.invalidate();
        return "complete";
    }
}
