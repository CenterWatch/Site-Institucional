package looca;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;

public class DataGetter {
    private Looca looca;

    private Memoria memoria;
    private Processador cpu;
    private Rede rede;

    public DataGetter() {
        this.looca = new Looca();
        this.memoria = looca.getMemoria();
        this.cpu = looca.getProcessador();
        this.rede = looca.getRede();
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public Processador getCpu() {
        return cpu;
    }

    public Rede getRede() {
        return rede;
    }
}
