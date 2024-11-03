package com.diel.tarefas.controller;

import com.diel.tarefas.domain.usuario.AutenticacaoInputDTO;
import com.diel.tarefas.domain.usuario.AutenticacaoService;
import com.diel.tarefas.domain.usuario.RegistrarInputDTO;
import com.diel.tarefas.domain.usuario.Usuario;
import com.diel.tarefas.infra.security.DadosTokenJWT;
import com.diel.tarefas.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoInputDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.usuario(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid RegistrarInputDTO dados) {
        var dadosUsuarioRegistrado = autenticacaoService.registrarUsuario(dados);
        return ResponseEntity.ok(dadosUsuarioRegistrado);
    }
}
