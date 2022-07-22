import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App extends Configuracao {

  public static void main(String[] args) throws Exception {

    String url = null;

    // escolher entre o Top Filmes ou Top Série do IMDB
    // try (
    // Scanner escolha = new Scanner(System.in)) {
    //   System.out.printf("\u001b[1mDigite 1 para Top Filmes ou 2 para Top Séries: \u001b[0m");
    //   int filmeOuSerie = escolha.nextInt();
    //   if (filmeOuSerie == 1) {
    //     url = "https://imdb-api.com/en/API/Top250Movies/" + getApiKeyImdb();
    //   } else if (filmeOuSerie == 2) {
    //     url = "https://imdb-api.com/en/API/Top250TVs/" + getApiKeyImdb();
    //   } else {
    //     System.out.println("Opção Inválida!");
    //   }
    // }
    // ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

    // Extração da API da Nasa
    // url = "https://api.nasa.gov/planetary/apod?api_key=" + getApiKeyNasa()
    // + "&start_date=2022-07-18&end_date=2022-07-20";
    // ExtratorDeConteudo extrator = new ExtratorDeConteudoNasa();

    url = "http://localhost:8080/linguagens";
    ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

    var http = new ClienteHttp();
    String json = http.buscaDados(url);

    // exibir e manipular os dados
    List<Conteudo> conteudos = extrator.extraiConteudos(json);

    var geradora = new GeradoraDeFigurinhas();

    for (int i = 0; i < conteudos.size(); i++) {
      Conteudo conteudo = conteudos.get(i);

      InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
      String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

      geradora.gera(inputStream, nomeArquivo);

      System.out.println("\u001b[1m\u001b[35m" + conteudo.getTitulo() + "\u001b[0m");

      if (conteudo.getClassificacao() != 0) {

        System.out.println("\u001b[4m\u001b[34m" + conteudo.getUrlImagem() + "\u001b[0m");
        System.out.println("\u001b[37m \u001b[43m Classificação IMDB:" + conteudo.getClassificacao() + "  \u001b[0m");
        
        var classificacao = new GeraClassificacao();
        classificacao.geraClassificacao(conteudo.getClassificacao());

      }
    }
  }
}