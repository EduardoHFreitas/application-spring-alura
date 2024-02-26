package br.com.aula.aplicacao.service;

import java.util.List;

public interface IConverter {

    <T> T converter(String json, Class<T> classe);

    <T> List<T> converterList(String json, Class<T> classe);

}
