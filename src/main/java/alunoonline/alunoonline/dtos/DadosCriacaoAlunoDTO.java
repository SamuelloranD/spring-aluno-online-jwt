package alunoonline.alunoonline.dtos;

// Usar um record é a forma mais moderna e simples
public record DadosCriacaoAlunoDTO(
        String nome,
        String cpf,
        String email,
        String senha // <-- AQUI ESTÁ A NOVIDADE!
) {
}