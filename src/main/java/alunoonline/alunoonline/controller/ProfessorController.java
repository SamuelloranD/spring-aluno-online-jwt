package alunoonline.alunoonline.controller;

import alunoonline.alunoonline.dtos.CriarProfessorDTO;
import alunoonline.alunoonline.model.Aluno;
import alunoonline.alunoonline.model.Professor;
import alunoonline.alunoonline.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professorescasa")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarProfessor(@RequestBody CriarProfessorDTO professor) {
        professorService.criarProfessor(professor);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Professor> listarTodosProfessores() {
        return professorService.listarTodosProfessores();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Professor> buscarProfessorPorId(@PathVariable Long id) {
        return professorService.buscarProfessorPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProfessorPorId(@PathVariable Long id) {
        professorService.deletarProfessorPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProfessorPorId(@PathVariable Long id,
                                    @RequestBody Professor professor) {
        professorService.atualizarProfessorPorId(id, professor);
    }
}