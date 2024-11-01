package com.diel.tarefas.domain.usuario;

import com.diel.tarefas.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByNomeUsuario(username);
    }

    public DadosUsuarioRegistrado registrarUsuario(RegistrarInputDTO dados) {
        if (repository.existsByNomeUsuario(dados.usuario())) {
            throw new ValidacaoException("Usuário já cadastrado!");
        }

        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        Usuario novoUsuario = new Usuario(dados.usuario(), senhaCriptografada);
        repository.save(novoUsuario);

        return new DadosUsuarioRegistrado(novoUsuario.getId(), novoUsuario.getNomeUsuario());
    }
 }
