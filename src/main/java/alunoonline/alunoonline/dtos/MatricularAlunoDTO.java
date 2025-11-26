package alunoonline.alunoonline.dtos;

import alunoonline.alunoonline.enums.MatriculaAlunoStatusEnum;
import alunoonline.alunoonline.model.Aluno;
import alunoonline.alunoonline.model.Disciplina;

public record MatricularAlunoDTO(
        Aluno aluno,
        Disciplina disciplina,
        Double nota1,
        Double nota2,
        MatriculaAlunoStatusEnum status
) {
}
