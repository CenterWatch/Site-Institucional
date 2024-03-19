public class Usuario {
    String nome;
    String sobrenome;
    String telefone;
    String email;
    String senha;
    String cpf;
    String cargo;

    public void exibirPerfil() {
        System.out.printf("""
                Nome: %s
                Sobrenome: %s
                Telefone: %s
                Email: %s
                CPF: %s
                Cargo: %s
                """.formatted(nome, sobrenome, telefone, email, cpf, cargo)
        );
    }

    public Usuario(String nome, String sobrenome, String telefone, String email, String senha, String cpf, String cargo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.cargo = cargo;
    }
}
