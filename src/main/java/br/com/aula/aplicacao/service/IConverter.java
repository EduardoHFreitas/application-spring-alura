package br.com.aula.aplicacao.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConverter {

    <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException;
}
