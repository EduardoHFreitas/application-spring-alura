package br.com.aula.aplicacao.main;

import br.com.aula.aplicacao.model.*;
import br.com.aula.aplicacao.service.ConsumoAPI;
import br.com.aula.aplicacao.service.ConverterJson;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverterJson converter = new ConverterJson();

    private final String URL_SERIE = "https://www.omdbapi.com/?&apikey=60cfa8";
    private final String URL_CARRO = "https://parallelum.com.br/fipe/api/v1/{0}/marcas/";
    private final String CARRO = "carros";
    private final String MOTO = "motos";
    private final String CAMINHOES = "caminhoes";

    private final String MODELOS = "/modelos/";
    private final String ANOS = "/anos/";

    public void exibirMenuCarro() {
        System.out.println("Digite o tipo de veículo (carros, motos, caminhoes):");
        var tipoVeiculo = leitura.nextLine();

        var url = MessageFormat.format(URL_CARRO, tipoVeiculo);

        var json = consumoAPI.obterDados(url);

        var marcas = converter.converterList(json, DadosFipe.class);

        marcas.forEach(m -> System.out.println("Código/marca: " + m.codigo() + " - " + m.nome()));

        System.out.println("Selecione uma marca por código :");
        var marca = leitura.nextLine();

        json = consumoAPI.obterDados(url + marca + MODELOS);

        var modeloList = converter.converter(json, Modelos.class);

        modeloList.modelos().forEach(m -> System.out.println("Código/modelo: " + m.codigo() + " - " + m.nome()));

        System.out.println("Qual modelo gostaria de pesquisar? ");
        var modelo = leitura.nextLine();

        modeloList.modelos().stream().filter(m -> m.nome().toLowerCase().contains(modelo.toLowerCase())).forEach(System.out::println);

        System.out.println("Digite o código do veículo desejado: ");
        var veiculo = leitura.nextLine();

        var urlAnos = url + marca + MODELOS + veiculo + ANOS;

        json = consumoAPI.obterDados(urlAnos);

        List<DadosFipe> anos = converter.converterList(json, DadosFipe.class);

//        List<Veiculo> veiculos = new ArrayList<>();

        anos.forEach(a -> {
            var jsonAno = consumoAPI.obterDados(urlAnos + a.codigo());
//            veiculos.add(converter.converter(jsonAno, Veiculo.class));
            System.out.println(converter.converter(jsonAno, Veiculo.class));
        });

//        veiculos.forEach(System.out::println);
    }

    public void exibirMyMenuCarro() {
        System.out.println("Digite o tipo de veículo (carros, motos, caminhoes):");
        var tipoVeiculo = leitura.nextLine();

        var url = MessageFormat.format(URL_CARRO, tipoVeiculo);

        var jsonMarcas = consumoAPI.obterDados(url);

        Marcas[] dadosMarcas = converter.converter(jsonMarcas, Marcas[].class);

        List<Marcas> dadosTipoVeiculos = Arrays.asList(dadosMarcas);
        dadosTipoVeiculos.forEach(m -> System.out.println("Código/marca: " + m.getCodigo() + " - " + m.getNome()));

        System.out.println("Selecione uma marca por código :");
        var marca = leitura.nextLine();

        var jsonModelos = consumoAPI.obterDados(url + marca + MODELOS);

        System.out.println(jsonModelos);

        ModeloList dadosModeloList = converter.converter(jsonModelos, ModeloList.class);

        dadosModeloList.getModelos().forEach(m -> System.out.println("Código/modelo: " + m.getCodigo() + " - " + m.getNome()));

        System.out.println("Qual modelo gostaria de pesquisar? ");
        var modelo = leitura.nextLine();

        dadosModeloList.getModelos().stream().filter(m -> m.getNome().toLowerCase().contains(modelo.toLowerCase())).forEach(System.out::println);

        System.out.println("Digite o código do veículo desejado: ");
        var veiculo = leitura.nextLine();

        var urlAnos = url + marca + MODELOS + veiculo + ANOS;

        var jsonAnos = consumoAPI.obterDados(urlAnos);

        Marcas[] anos = converter.converter(jsonAnos, Marcas[].class);

        List<Veiculo> veiculos = new ArrayList<>();

        Arrays.stream(anos).forEach(a -> {
            var jsonAno = consumoAPI.obterDados(urlAnos + a.getCodigo());
            veiculos.add(converter.converter(jsonAno, Veiculo.class));
        });

        veiculos.forEach(System.out::println);
    }

    public void exibeMenuSerie() {
        System.out.println("Digite o nome da serie:");
        var nomeSerie = leitura.nextLine();

        var url = URL_SERIE.concat("&t=").concat(nomeSerie.replace(" ", "+"));

        var json = consumoAPI.obterDados(url);

        DadosSerie serie = converter.converter(json, DadosSerie.class);

        System.out.println(serie);

        List<DadosTemporada> temporadas = new ArrayList<>();

        if (serie != null && serie.temporadas() > 0) {
            for (int i = 1; i <= serie.temporadas(); i++) {
                var jsonTemporada = consumoAPI.obterDados(url + "&season=" + i);

                temporadas.add(converter.converter(jsonTemporada, DadosTemporada.class));
            }
        }

//        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream()).collect(Collectors.toList());

        dadosEpisodios
                .stream()
                .filter(d -> !d.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(10)
                .map(e -> e.titulo().toUpperCase())
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))).collect(Collectors.toList());

        episodios.forEach(System.out::println);

//        System.out.println("Digite o nome do episodio:");
//        var nomeEpisodio = leitura.nextLine();
//
//        Episodio episodio = episodios.stream()
//                .filter(e -> e.getTitulo().toLowerCase().contains(nomeEpisodio.toLowerCase()))
//                .findFirst()
//                .orElse(null);
//
//        if (episodio == null) {
//            System.out.println("Episódio [" + nomeEpisodio + "] não encontrado");
//        } else {
//            System.out.println(episodio);
//        }

        Map<Integer, Double> avaliacaoTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println(avaliacaoTemporada);

        DoubleSummaryStatistics dss = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println(dss);

//        episodios.stream()
//                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
//                .limit(5)
//                .forEach(System.out::println);
//


//        for (int i = 0; i < serie.temporadas(); i++) {
//            List<DadosEpisodio> episodios = temporadas.get(i).episodios();
//
//            for (int j = 0; j < episodios.size(); j++) {
//                System.out.println(episodios.get(j).titulo());
//            }
//        }
    }
}
