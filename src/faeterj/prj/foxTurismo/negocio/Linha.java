package faeterj.prj.foxTurismo.negocio;

import java.time.LocalTime;

public class Linha {

    private int id;
    private String origem;
    private String destino;
    private LocalTime horaPartida;
    private LocalTime horaEmbarque;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalTime getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(LocalTime horaPartida) {
        this.horaPartida = horaPartida;
    }

    public LocalTime getHoraEmbarque() {
        return horaEmbarque;
    }

    public void setHoraEmbarque(LocalTime horaEmbarque) {
        this.horaEmbarque = horaEmbarque;
    }
}
