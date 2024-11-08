package com.diel.tarefas.infra.auth;

import com.diel.tarefas.domain.usuario.Usuario;
import com.diel.tarefas.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHelper {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Esse método é para retornar o usuário autenticado
    public Usuario getAuthenticatedUser() {

        String username = getAuthenticatedUsername();

        return usuarioRepository.findByNomeUsuario(username);
    }

    private String getAuthenticatedUsername() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
