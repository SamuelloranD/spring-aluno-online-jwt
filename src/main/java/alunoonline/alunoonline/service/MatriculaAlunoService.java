package alunoonline.alunoonline.service;

import alunoonline.alunoonline.dtos.AtualizarNotasRequestDTO;
import alunoonline.alunoonline.dtos.DisciplinasAlunoResponseDTO;
import alunoonline.alunoonline.dtos.HistoricoAlunoResponseDTO;
import alunoonline.alunoonline.dtos.MatricularAlunoDTO;
import alunoonline.alunoonline.enums.MatriculaAlunoStatusEnum;
import alunoonline.alunoonline.model.MatriculaAluno;
import alunoonline.alunoonline.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaAlunoService {

    private static final Double MEDIA_PARA_APROVACAO = 7.0;
    private static final Integer QTD_NOTAS = 2;

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void criarMatricula(MatricularAlunoDTO matriculaAluno) {

        MatriculaAluno novaMatriculaAluno = new MatriculaAluno();
        novaMatriculaAluno.setAluno(matriculaAluno.aluno());
        novaMatriculaAluno.setDisciplina(matriculaAluno.disciplina());
        novaMatriculaAluno.setNota1(matriculaAluno.nota1());
        novaMatriculaAluno.setNota2(matriculaAluno.nota2());

        matriculaAlunoRepository.save(novaMatriculaAluno);
    }

    public void trancarMatricula(Long matriculaAlunoId) {

        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(matriculaAlunoId)
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matrícula Aluno não encontrada"));


        if (matriculaAluno.getStatus()
                .equals(MatriculaAlunoStatusEnum.MATRICULADO)) {
            matriculaAluno.setStatus(MatriculaAlunoStatusEnum.TRANCADO);
            matriculaAlunoRepository.save(matriculaAluno);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só é possível trancar com o status MATRICULADO");
        }
    }

    public void atualizarNotas(Long matriculaAlunoId, AtualizarNotasRequestDTO atualizarNotasRequestDTO) {
        MatriculaAluno matriculaAluno = buscarMatriculaOuLancarExcecao(matriculaAlunoId);

        if (atualizarNotasRequestDTO.getNota1() != null) {
            matriculaAluno.setNota1(atualizarNotasRequestDTO.getNota1());
        }
        if (atualizarNotasRequestDTO.getNota2() != null) {
            matriculaAluno.setNota2(atualizarNotasRequestDTO.getNota2());
        }

        calcularMediaEModificarStatus(matriculaAluno);
        matriculaAlunoRepository.save(matriculaAluno);

    }

    public HistoricoAlunoResponseDTO emitirHistorico(Long alunoid) {
        List<MatriculaAluno> matriculaAlunos = matriculaAlunoRepository.findByAlunoId(alunoid);
        if (matriculaAlunos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Esse aluno não possui matrículas");
        }
        HistoricoAlunoResponseDTO historicoAluno = new HistoricoAlunoResponseDTO();
        historicoAluno.setNomeAluno(matriculaAlunos.get(0).getAluno().getNome());
        historicoAluno.setEmailAluno(matriculaAlunos.get(0).getAluno().getEmail());
        historicoAluno.setCpfAluno(matriculaAlunos.get(0).getAluno().getCpf());

        List<DisciplinasAlunoResponseDTO> disciplinas = matriculaAlunos.stream()
                .map(this::mapearParaDisciplinasAlunoResponseDTO)
                .collect(Collectors.toList());
        historicoAluno.setDisciplinas(disciplinas);
        return historicoAluno;
    }

    private MatriculaAluno buscarMatriculaOuLancarExcecao(Long matriculaAlunoId) {
        return matriculaAlunoRepository.findById(matriculaAlunoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula do Aluno não encontrada"));
    }

    private void calcularMediaEModificarStatus(MatriculaAluno matriculaAluno) {
        Double nota1 = matriculaAluno.getNota1();
        Double nota2 = matriculaAluno.getNota2();

        if (nota1 != null && nota2 != null) {
            Double media = (nota1 + nota2) / QTD_NOTAS;
            matriculaAluno.setStatus(media >= MEDIA_PARA_APROVACAO ? MatriculaAlunoStatusEnum.APROVADO : MatriculaAlunoStatusEnum.REPROVADO);
        }
    }

    private DisciplinasAlunoResponseDTO mapearParaDisciplinasAlunoResponseDTO(MatriculaAluno matriculaAluno) {
        DisciplinasAlunoResponseDTO response = new DisciplinasAlunoResponseDTO();
        response.setNomeDisciplina(matriculaAluno.getDisciplina().getNome());
        response.setNomeProfessor(matriculaAluno.getDisciplina().getProfessor().getNome());
        response.setNota1((matriculaAluno.getNota1()));
        response.setNota2((matriculaAluno.getNota2()));
        response.setMedia(calcularMedia(matriculaAluno.getNota1(), matriculaAluno.getNota2()));
        response.setStatus(matriculaAluno.getStatus());

        return response;
    }

    private Double calcularMedia(Double nota1, Double nota2) {
        return (nota1 != null && nota2 != null) ? (nota1 + nota2) / QTD_NOTAS : null;
    }
}