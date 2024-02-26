package br.com.aula.aplicacao.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {

    private String titulo;
    private Integer numero;
    private Integer temporada;
    private String duracao;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(Integer temporada, DadosEpisodio dados) {
        this.titulo = dados.titulo();
        this.temporada = temporada;
        this.numero = dados.numero();
        this.duracao = dados.duracao();
        try {
            this.dataLancamento = LocalDate.parse(dados.dataLancamento());
        } catch (DateTimeParseException ex) {
            this.dataLancamento = null;
        }
        try {
            this.avaliacao = Double.valueOf(dados.avaliacao());
        } catch (NumberFormatException ex) {
            this.avaliacao = 0.0;
        }
    }

    public Episodio(){}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                ", numero=" + numero +
                ", temporada=" + temporada +
                ", duracao='" + duracao + '\'' +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento;
    }
}
