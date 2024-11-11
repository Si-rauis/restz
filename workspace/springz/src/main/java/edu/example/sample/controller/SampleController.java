package edu.example.sample.controller;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
    @GetMapping("/display-sample")
    public String displaySample(Model model,
                                Authentication auth,
                                @AuthenticationPrincipal UserDetails user) {
        System.out.println("user : " + auth.getName());
        System.out.println("principal : " + auth.getPrincipal());
        auth.getAuthorities().forEach(System.out::println);

        System.out.println("------ UserDetails ------");
        System.out.println("userDetails : " + user);

        System.out.println("----- SecurityContextHolder -----");
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication : " + authentication);

        //뷰로 보낼 데이터는 Model 객체의 속성에 저장
        model.addAttribute("fullName", "Hanna");
                                        //키                     값
        return "sample";                //뷰 이름
    }   //뷰 템플릿 파일 경로는 ViewResolver가 스프링 부트 자동 설정을 따름
}       // src/resources/templates/뷰이름.html
        //[pre
