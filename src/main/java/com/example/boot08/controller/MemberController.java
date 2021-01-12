package com.example.boot08.controller;

import com.example.boot08.domain.Member;
import com.example.boot08.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Log
@Controller
@RequestMapping("/member/")
public class MemberController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository repo;

    @GetMapping("/join")
    public void join() {

    }

    @Transactional
    @PostMapping("/join")
    public String joinPost(@ModelAttribute("member") Member member) {
        log.info("MEMBER : " + member);

        String encryptPw = passwordEncoder.encode(member.getUpw());
        log.info("encryt : " + encryptPw);

        member.setUpw(encryptPw);

        repo.save(member);

        return "/member/joinResult";
    }
}
