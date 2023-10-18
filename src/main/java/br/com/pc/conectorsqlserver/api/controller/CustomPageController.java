package br.com.pc.conectorsqlserver.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomPageController {

    @GetMapping("/home")
    public String customPage() {
        return "custom-page.html"; // Nome da sua página HTML
    }

    @GetMapping("/")
    public String redirect(){
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/licenca")
    public String terms() {
        return "terms.html"; // Nome do seu arquivo HTML
    }

    @GetMapping("/terms-of-service")
    public String termsOfService() {
        return "terms-of-service.html"; // Nome do seu arquivo HTML
    }

}
