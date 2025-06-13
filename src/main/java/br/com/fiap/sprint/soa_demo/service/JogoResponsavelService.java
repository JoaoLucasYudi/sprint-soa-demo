package br.com.fiap.sprint.soa_demo.service;

import br.com.fiap.sprint.soa_demo.exception.LimiteExcedidoException;
import br.com.fiap.sprint.soa_demo.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint.soa_demo.exception.UsuarioAutoExcluidoException;
import br.com.fiap.sprint.soa_demo.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JogoResponsavelService {

    // Usando um mapa para simular um banco de dados (thread-safe)
    private final Map<Long, Usuario> usuarios = new ConcurrentHashMap<>();

    public JogoResponsavelService() {
        // Dados iniciais para teste
        usuarios.put(1L, new Usuario(1L, "João Silva", 500.00, 0.0, Usuario.Status.ATIVO));
        usuarios.put(2L, new Usuario(2L, "Maria Souza", 200.00, 180.0, Usuario.Status.ATIVO));
    }

    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            throw new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado.");
        }
        return usuario;
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }

    public Usuario registrarAposta(Long usuarioId, double valorAposta) {
        Usuario usuario = buscarUsuarioPorId(usuarioId);

        // 1. Verifica se o usuário está autoexcluído
        if (usuario.getStatus() == Usuario.Status.AUTOEXCLUIDO) {
            throw new UsuarioAutoExcluidoException("Ação bloqueada. Usuário " + usuarioId + " está em período de autoexclusão.");
        }

        // 2. Verifica se a nova aposta excede o limite semanal
        if ((usuario.getGastoAtualSemanal() + valorAposta) > usuario.getLimiteGastoSemanal()) {
            throw new LimiteExcedidoException("Aposta recusada. Limite de gasto semanal (R$" + usuario.getLimiteGastoSemanal() + ") seria excedido.");
        }

        // 3. Se tudo estiver OK, registra o gasto
        usuario.setGastoAtualSemanal(usuario.getGastoAtualSemanal() + valorAposta);
        return usuario;
    }

    public Usuario ativarAutoexclusao(Long usuarioId) {
        Usuario usuario = buscarUsuarioPorId(usuarioId);
        usuario.setStatus(Usuario.Status.AUTOEXCLUIDO);
        return usuario;
    }
}