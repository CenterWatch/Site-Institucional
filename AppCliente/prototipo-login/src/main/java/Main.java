import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Header header = new Header();
        header.showHeader();

        Usuario usuario = new Usuario();

        usuario.cadastrar("root", "password");

        Scanner leitor = new Scanner(System.in);
        Scanner leitor2 = new Scanner(System.in);

        System.out.println("Bem-vindo!\n\nSelecione uma das seguintes opções:");
        System.out.println("1. Entrar");
        System.out.println("2. Cadastrar");
        System.out.println("3. Sair");

        Integer opt = leitor.nextInt();

        String user, pwd;

        switch (opt) {
            case 1:
                System.out.printf("Usuário: ");
                user = leitor2.next();
                System.out.printf("Senha: ");
                pwd = leitor2.next();

                if (usuario.verificarLogin(user, pwd)) {
                    usuario.entrar(user, pwd);
                }
                break;
            case 2:
                System.out.printf("Usuário: ");
                user = leitor2.next();
                System.out.printf("Senha: ");
                pwd = leitor2.next();

                usuario.cadastrar(user, pwd);
                break;
            case 3:
                break;
        }

        System.out.println();
    }
}
