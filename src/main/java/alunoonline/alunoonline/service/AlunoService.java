package alunoonline.alunoonline.service;

import alunoonline.alunoonline.dtos.DadosCriacaoAlunoDTO;
import alunoonline.alunoonline.model.Aluno;
import alunoonline.alunoonline.repository.AlunoRepository;
import alunoonline.alunoonline.usuario.Role;
import alunoonline.alunoonline.usuario.Usuario;
import alunoonline.alunoonline.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void criarAluno(DadosCriacaoAlunoDTO dados) {

        Aluno novoAluno = new Aluno();
        novoAluno.setNome(dados.nome());
        novoAluno.setCpf(dados.cpf());
        novoAluno.setEmail(dados.email());

        alunoRepository.save(novoAluno);

        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(dados.email());
        novoUsuario.setSenha(passwordEncoder.encode(dados.senha()));
        novoUsuario.setRole(Role.ALUNO);

        usuarioRepository.save(novoUsuario);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            alunoRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
    }

    public void atualizarAlunoPorId(Long id, Aluno aluno) {
        Optional<Aluno> alunoDoBancoDeDados = buscarAlunoPorId(id);
        if (alunoDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Aluno não encontrado no banco de dados");
        }

        Aluno alunoParaEditar = alunoDoBancoDeDados.get();

        alunoParaEditar.setNome(aluno.getNome());
        alunoParaEditar.setCpf(aluno.getCpf());
        alunoParaEditar.setEmail(aluno.getEmail());
        alunoParaEditar.setId(aluno.getId());
        alunoRepository.save(alunoParaEditar);
    }
}