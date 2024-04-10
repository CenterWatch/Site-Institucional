import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.rede.RedeInterfaceGroup;
import connection.Connection;
import looca.DataGetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DbMain {


    public static void main(String[] args) throws InterruptedException {

        Connection connection = new Connection();
        JdbcTemplate con = connection.getDbConnection();
        DataGetter dataGetter = new DataGetter();

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

        while(true) {
            con.update("""
                insert into registro 
                (uso_cpu, 
                uso_ram, 
                disponivel_ram, 
                ipv4,
                pacotes_recebidos,
                fk_maquina)
                values (?, ?, ?, ?, ?, 12345)
                """,usoCpu, usoRam, totalRam, ip, pacotes);

            Thread.sleep(1000);
        }







//        Processador cpu = dataGetter.getCpu();
//
//        //forma de cpu dentro do banco, precisa ser refatorada
////        System.out.printf("%.2f",cpu.getUso());
//
//        Memoria memoria = dataGetter.getMemoria();
////        System.out.println(memoria);
////        System.out.println(memoria.getTotal());
////        System.out.println(memoria.getEmUso());
//
//        Rede rede = dataGetter.getRede();
//        List<RedeInterface> interfaceGroup = rede.getGrupoDeInterfaces().getInterfaces();
//
//        List<RedeInterface> ips = interfaceGroup.stream().filter(i -> !i.getEnderecoIpv4().isEmpty()).toList();
//
//        //o que vai ser inserido no banco
//        System.out.println(ips.get(1).getEnderecoIpv4());
//        System.out.println(ips.get(1).getPacotesRecebidos());
//
//        Processador pro = dataGetter.getCpu();
//        System.out.println(memoria.getTotal());
//
//        Looca looca = new Looca();
//        DiscoGrupo discoGrupo = looca.getGrupoDeDiscos();
//        List<Disco> discos = discoGrupo.getDiscos();
//
//        System.out.println(discos);



    }
}
