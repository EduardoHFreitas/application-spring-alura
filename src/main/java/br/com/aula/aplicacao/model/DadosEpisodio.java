package br.com.aula.aplicacao.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("Episode") Integer numero,
                            @JsonAlias("Season") String temporada,
                            @JsonAlias("Runtime") String duracao,
                            @JsonAlias("imdbRating") String avaliacao,
                            @JsonAlias("Released")String dataLancamento) {
}
