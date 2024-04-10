
import connection.Connection;
import looca.DataGetter;
import org.springframework.jdbc.core.JdbcTemplate;
public class DbMain {


    public static void main(String[] args) throws InterruptedException {

        //aviso: execute o script do banco no workbench ou no terminal
        //antes de executar o código, a única parte realmente necessária
        //é a tabela "maquina".

        //criando as conexões
        Connection connection = new Connection();
        JdbcTemplate con = connection.getDbConnection();
        DataGetter dataGetter = new DataGetter();

        //recebendo os dados do looca
        Double usoCpu = dataGetter.getCpu().getUso();
        Long usoRam = dataGetter.getMemoria().getEmUso();
        Long totalRam = dataGetter.getMemoria().getTotal();

        String ip = dataGetter
                .getRede()
                .getGrupoDeInterfaces()
                .getInterfaces()
                .stream()
                .filter(p -> !p.getEnderecoIpv4().isEmpty())
                .toList()
                .get(1)
                .getEnderecoIpv4()
                .get(0);

        Long pacotes = dataGetter
                .getRede()
                .getGrupoDeInterfaces()
                .getInterfaces()
                .stream()
                .filter(p -> !p.getEnderecoIpv4().isEmpty())
                .toList()
                .get(1)
                .getPacotesRecebidos();

        //permanência dos dados dentro do banco
        while(true) {
            con.update("""
                insert into registro 
                (uso_cpu, 
                uso_ram, 
                disponivel_ram, 
                ipv4,
                pacotes_recebidos,
                fk_maquina)
                values (?, ?, ?, ?, ?, null)
                """,usoCpu, usoRam, totalRam, ip, pacotes);

            Thread.sleep(1000);

            //a coluna fk_maquina recebe um valor nulo
            //por enquanto, ela seria em teoria hard-codada
            //em cada máquina de acordo com a regra de negócio
            //baseada até então.
        }

    }
}
