package br.com.aula.aplicacao.main;

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
		Principal principal = new Principal();
		principal.exibirMenuCarro();
	}
}
