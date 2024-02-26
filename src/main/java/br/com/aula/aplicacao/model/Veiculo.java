package br.com.aula.aplicacao.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Veiculo {

    @JsonAlias("TipoVeiculo")
    private Integer tipoVeiculo;

    @JsonAlias("Valor")
    private String valor;

    @JsonAlias("Marca")
    private String marca;

    @JsonAlias("Modelo")
    private String modelo;

    @JsonAlias("AnoModelo")
    private Integer ano;

    @JsonAlias("Combustivel")
    private String combustivel;

    @JsonAlias("CodigoFipe")
    private String codigoFipe;

    @JsonAlias("MesReferencia")
    private String mesReferencia;

    @JsonAlias("SiglaCombustivel")
    private String siglaCombustivel;

    public Integer getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(Integer tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        this.codigoFipe = codigoFipe;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getSiglaCombustivel() {
        return siglaCombustivel;
    }

    public void setSiglaCombustivel(String siglaCombustivel) {
        this.siglaCombustivel = siglaCombustivel;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " - " + combustivel + " - " + ano + " - valor: " + valor + " - referencia: " + mesReferencia;
    }
}
