package com.diel.tarefas.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByNomeUsuario(String usuario);

    UserDetails findByNomeUsuario(String nomeUsuario);
}
