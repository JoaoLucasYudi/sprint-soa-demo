package br.com.fiap.sprint.soa_demo.controller;

import br.com.fiap.sprint.soa_demo.dto.RegistrarApostaRequest;
import br.com.fiap.sprint.soa_demo.model.Usuario;
import br.com.fiap.sprint.soa_demo.service.JogoResponsavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogo-responsavel")
public class UsuarioController {

    @Autowired
    private JogoResponsavelService service;

    @GetMapping("/usuarios")
    public List<Usuario> listarTodosUsuarios() {
        return service.listarTodos();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioStatus(@PathVariable Long id) {
        return service.buscarUsuarioPorId(id);
    }

    @PostMapping("/usuarios/{id}/registrar-aposta")
    public ResponseEntity<Usuario> registrarAposta(@PathVariable Long id, @Valid @RequestBody RegistrarApostaRequest request) {
        Usuario usuarioAtualizado = service.registrarAposta(id, request.getValor());
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PostMapping("/usuarios/{id}/autoexclusao")
    public ResponseEntity<Usuario> ativarAutoexclusao(@PathVariable Long id) {
        Usuario usuarioAtualizado = service.ativarAutoexclusao(id);
        return ResponseEntity.ok(usuarioAtualizado);
    }
}
