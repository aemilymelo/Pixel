package br.edu.utfpr.api.service;
import br.edu.utfpr.api.DTO.AuthRequest;
import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public String login(AuthRequest request) throws RuntimeException {
        Pessoa user = pessoaService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    public void register(AuthRequest request) {
        if (pessoaService.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        Pessoa user = new Pessoa();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        pessoaService.save(user);
    }
    public String passwordEncoder(String password) {
        return passwordEncoder.encode(password);
    }
}