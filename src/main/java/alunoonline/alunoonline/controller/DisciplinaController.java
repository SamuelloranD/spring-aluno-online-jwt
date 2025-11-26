package alunoonline.alunoonline.controller;

import alunoonline.alunoonline.dtos.CriarDisciplinaDTO;
import alunoonline.alunoonline.model.Disciplina;
import alunoonline.alunoonline.model.Professor;
import alunoonline.alunoonline.service.DisciplinaService;
import alunoonline.alunoonline.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinascasa")
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarDisciplina(@RequestBody CriarDisciplinaDTO disciplina){
        disciplinaService.criarDisciplina(disciplina);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Disciplina> listarTodasDisciplinas(){
        return disciplinaService.listarTodasDisciplinas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Disciplina> buscarDisciplinaPorId(@PathVariable Long id){
        return disciplinaService.buscarDisciplinaPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarDisciplinaPorId(@PathVariable long id){
        disciplinaService.deletarDisciplinaPorId(id);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarDisciplinaPorId(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        disciplinaService.atualizarDisciplinaPorId(id, disciplina);
    }

    @GetMapping("/professor/{professorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Disciplina> listarDisciplinasDoProf (@PathVariable Long professorId) {
        return disciplinaService.listarDisciplinasDoProf(professorId);
    }

}
