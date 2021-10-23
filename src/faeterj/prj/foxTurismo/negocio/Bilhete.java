package faeterj.prj.foxTurismo.negocio;

import java.time.LocalDateTime;

public class Bilhete {

    private String codigo;
    private String assento;
    private Passageiro passageiro;
    private Linha linha;
    private LocalDateTime horaMarcadaAssento;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public LocalDateTime getHoraMarcadaAssento() {
        return horaMarcadaAssento;
    }

    public void setHoraMarcadaAssento(LocalDateTime horaMarcadaAssento) {
        this.horaMarcadaAssento = horaMarcadaAssento;
    }
}
