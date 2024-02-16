package br.com.aula.aplicacao;

import br.com.aula.aplicacao.model.Serie;
import br.com.aula.aplicacao.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AplicacaoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AplicacaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=60cfa8");
		System.out.println(json);

		ConverterSerie converter = new ConverterSerie();
		Serie serie = converter.obterDados(json, Serie.class);

		System.out.println(serie);
	}
}
