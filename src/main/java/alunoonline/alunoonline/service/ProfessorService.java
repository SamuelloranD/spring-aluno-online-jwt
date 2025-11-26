package alunoonline.alunoonline.service;

import alunoonline.alunoonline.dtos.CriarProfessorDTO;
import alunoonline.alunoonline.model.Aluno;
import alunoonline.alunoonline.model.Professor;
import alunoonline.alunoonline.repository.ProfessorRepository;
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
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void criarProfessor(CriarProfessorDTO professor) {

        Professor novoProfessor = new Professor();
        novoProfessor.setNome(professor.nome());
        novoProfessor.setCpf(professor.cpf());
        novoProfessor.setEmail(professor.email());

        professorRepository.save(novoProfessor);

        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(professor.nome());
        novoUsuario.setSenha(passwordEncoder.encode(professor.senha()));
        novoUsuario.setRole(Role.PROFESSOR);

        usuarioRepository.save(novoUsuario);
    }

    public List<Professor> listarTodosProfessores() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    public void deletarProfessorPorId(Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        if (professor.isPresent()) {
            professorRepository.delete(professor.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado");
        }
    }

    public void atualizarProfessorPorId(Long id, Professor professor) {
        Optional<Professor> professorDoBancoDeDados = buscarProfessorPorId(id);
        if (professorDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Professor não encontrado no banco de dados");
        }

        Professor professorParaEditar = professorDoBancoDeDados.get();

        professorParaEditar.setNome(professor.getNome());
        professorParaEditar.setCpf(professor.getCpf());
        professorParaEditar.setEmail(professor.getEmail());
        professorParaEditar.setId(professor.getId());
        professorRepository.save(professorParaEditar);
    }
}