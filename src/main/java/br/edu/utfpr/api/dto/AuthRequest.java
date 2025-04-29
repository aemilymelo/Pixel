package br.edu.utfpr.api.dto;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class AuthRequest {
    private String email;
    private String username;
    private String password;


    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}