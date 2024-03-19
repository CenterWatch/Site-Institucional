public class Navegacao {
    UsuarioHandler usuario = new UsuarioHandler();
    void menu(Integer op) {
        Boolean continuar = true;
        do {
            switch (op) {
                case 1:
                        usuario.entrar();
                    break;

                case 2:
                    Boolean usuarioCadastrou;
                    do {
                        usuarioCadastrou = usuario.cadastrar();
                        System.out.println("Redirecionando para tela de login...\n");
                        // Ao finalizar o cadastro, será redirecionado para a opção de login
                        if (usuarioCadastrou)
                            menu(1);
                    } while (!usuarioCadastrou);
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
