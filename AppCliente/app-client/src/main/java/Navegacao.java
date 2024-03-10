import java.util.Scanner;

public class Navegacao {
    Usuario usuario = new Usuario();
    void menu(Integer op) {
        Boolean continuar = true;
        do {
            switch (op) {
                case 1:
                    Boolean usuarioEntrou;
                    do {
                        usuarioEntrou = usuario.entrar();

                        continuar = !usuarioEntrou;
                    } while (continuar);
                    break;

                case 2:
                    Boolean usuarioCadastrou;
                    do {
                        usuarioCadastrou = usuario.cadastrar();

                        // Ao finalizar o cadastro, será redirecionado para a opção de login
                        if (usuarioCadastrou)
                            menu(1);

                        continuar = !usuarioCadastrou;
                    } while (continuar);
                    break;

                case 0:
                    System.out.println("Até mais!");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    continuar = false;
            }
        } while (continuar);
    }
}
