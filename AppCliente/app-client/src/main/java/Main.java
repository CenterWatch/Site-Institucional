import java.util.Scanner;

public class Main {
    static Navegacao navegacao = new Navegacao();
    public static void main(String[] args) {
        Header header = new Header();
        header.showHeader();

        inicio();
    }

    static void inicio() {
        System.out.println("Bem-vindo!\n\nSelecione uma das seguintes opções:");
        Scanner leitor = new Scanner(System.in);

        System.out.println("1. Entrar");
        System.out.println("2. Cadastrar");
        System.out.println("0. Sair");
        System.out.println();

        navegacao.menu(leitor.nextInt());
    }
}
