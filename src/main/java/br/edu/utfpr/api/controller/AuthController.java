package br.edu.utfpr.api.controller;
import br.edu.utfpr.api.dto.AuthRequest;
import br.edu.utfpr.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value="/login", consumes = {"application/json", "application/*+json"})
    public String login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        authService.register(request);
        return "Usuário registrado com sucesso";
    }
}
