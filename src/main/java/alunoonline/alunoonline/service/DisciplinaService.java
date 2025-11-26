package alunoonline.alunoonline.service;

import alunoonline.alunoonline.dtos.CriarDisciplinaDTO;
import alunoonline.alunoonline.model.Aluno;
import alunoonline.alunoonline.model.Disciplina;
import alunoonline.alunoonline.model.Professor;
import alunoonline.alunoonline.repository.DisciplinaRepository;
import alunoonline.alunoonline.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    public void criarDisciplina(CriarDisciplinaDTO disciplina){

        Disciplina novaDisciplina = new Disciplina();
        novaDisciplina.setNome(disciplina.nome());
        novaDisciplina.setCargaHoraria(disciplina.cargaHoraria());

        Professor professor = professorRepository.findById(disciplina.professorDisciplina().id())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        novaDisciplina.setProfessor(professor);

        disciplinaRepository.save(novaDisciplina);
    }

    public List<Disciplina> listarTodasDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarDisciplinaPorId(Long id){
        return disciplinaRepository.findById(id);
    }

    public void deletarDisciplinaPorId(Long id){
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        if (disciplina.isPresent()){
            disciplinaRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Não encontrado a disciplina");
        }
    }

    public void atualizarDisciplinaPorId(Long id, Disciplina disciplina){
        Optional<Disciplina> disciplinaDoBancoDeDados = buscarDisciplinaPorId(id);

        if (disciplinaDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Disciplina não encontrada no Banco de Dados");
        }

        Disciplina disciplinaParaEditar = disciplinaDoBancoDeDados.get();

        disciplinaParaEditar.setNome(disciplina.getNome());
        disciplinaParaEditar.setCargaHoraria(disciplina.getCargaHoraria());

        disciplinaRepository.save(disciplinaParaEditar);
    }

    public List<Disciplina> listarDisciplinasDoProf(Long professorId) {
        return disciplinaRepository.findByProfessorId(professorId);
    }
}
