package alunoonline.alunoonline.controller;

import alunoonline.alunoonline.dtos.AtualizarNotasRequestDTO;
import alunoonline.alunoonline.dtos.HistoricoAlunoResponseDTO;
import alunoonline.alunoonline.dtos.MatricularAlunoDTO;
import alunoonline.alunoonline.model.MatriculaAluno;
import alunoonline.alunoonline.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculascasa")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService matriculaAlunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarMatricula(@RequestBody MatricularAlunoDTO matriculaAluno) {
        matriculaAlunoService.criarMatricula(matriculaAluno);
    }

    @PatchMapping("/trancar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void trancarMatricula
            (@PathVariable Long id) {
        matriculaAlunoService.trancarMatricula(id);
    }

    @PatchMapping("/atualizar-notas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarNotas(@PathVariable Long id,
                               @RequestBody AtualizarNotasRequestDTO atualizarNotasRequestDTO) {
        matriculaAlunoService.atualizarNotas(id, atualizarNotasRequestDTO);
    }

    @GetMapping("/emitir-historico/{alunoId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('ALUNO') and @securityService.alunoPodeConsultarHistorico(authentication, #alunoId))")
    public HistoricoAlunoResponseDTO emitirHistorico(@PathVariable Long alunoId) {
        return matriculaAlunoService.emitirHistorico(alunoId);
    }
}
