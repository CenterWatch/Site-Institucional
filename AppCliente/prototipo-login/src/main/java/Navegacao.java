import java.util.Scanner;

public class Navegacao {
    Usuario usuario = new Usuario();
    void menu(Integer op) {
        Boolean continuar = true;
        do {
            System.out.println();
            switch (op) {
                case 1:
                    Boolean usuarioEntrou;
                    do {
                        usuarioEntrou = usuario.entrar();
                        System.out.println(usuarioEntrou);

                        continuar = !usuarioEntrou;
                    } while (!usuarioEntrou);
                    break;
                case 2:
                    Boolean usuarioCadastrou;
                    do {
                        usuarioCadastrou = usuario.cadastrar();

                        if (usuarioCadastrou)
                            menu(1);
                        continuar = !usuarioCadastrou;
                    } while (!usuarioCadastrou);
                    break;
                case 3:
                    System.out.println("Até mais!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println();
        } while (continuar);
    }
}
