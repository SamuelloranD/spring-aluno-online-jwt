package alunoonline.alunoonline.dtos;

import alunoonline.alunoonline.model.Professor;

public record CriarDisciplinaDTO(
        String nome,
        Integer cargaHoraria,
        ProfessorDisciplina professorDisciplina
) {
}
