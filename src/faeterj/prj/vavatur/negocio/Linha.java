package faeterj.prj.vavatur.negocio;

import java.time.LocalDate;

public class Linha {

    private int id;
    private String origem;
    private String destino;
    private LocalDate horaPartida;
    private LocalDate horaEmbarque;

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

    public LocalDate getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(LocalDate horaPartida) {
        this.horaPartida = horaPartida;
    }

    public LocalDate getHoraEmbarque() {
        return horaEmbarque;
    }

    public void setHoraEmbarque(LocalDate horaEmbarque) {
        this.horaEmbarque = horaEmbarque;
    }
}
