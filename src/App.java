import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App extends Configuracao {

  public static void main(String[] args) throws Exception {

    // escolher entre o Top Filmes ou Top Série
    Scanner escolha = new Scanner(System.in);
    System.out.printf("\u001b[1mDigite 1 para Top Filmes ou 2 para Top Séries: \u001b[0m");
    int filmeOuSerie = escolha.nextInt();
    String url = null;
    if (filmeOuSerie == 1) {
      url = "https://imdb-api.com/en/API/Top250Movies/" + getApiKey();
    } else if (filmeOuSerie == 2) {
      url = "https://imdb-api.com/en/API/Top250TVs/" + getApiKey();
    } else {
      System.out.println("Opção Inválida!");
    }

    // fazer uma conexão HTTP e buscar os top 250 filmes
    URI endereco = URI.create(url);
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder(endereco).GET().build();
    HttpResponse < String > response = client.send(request, BodyHandlers.ofString());
    String body = response.body();

    // extrair só os dados que inressam (título, poster, classificação)
    JsonParser parser = new JsonParser();
    List < Map < String, String >> listaDeFilmes = parser.parse(body);

    //exibir e manipular os dados
    for (Map < String, String > filme: listaDeFilmes) {
      System.out.println("\u001b[1m\u001b[35m" + filme.get("title") + "\u001b[0m");
      System.out.println("\u001b[4m\u001b[34m" + filme.get("image") + "\u001b[0m");
      System.out.println("\u001b[37m \u001b[43m Classificação IMDB:" + filme.get("imDbRating") + "  \u001b[0m");

      double nota = Double.parseDouble(filme.get("imDbRating"));
      int classificacao = (int) nota;
      for (int i = 1; i < 10; i++) {
        if (i > classificacao) {
          break;
        } else {
          System.out.print("\u001b[40m\u2B50\u001b[0m");
        }
      }
      System.out.println();

      System.out.printf("\u001b[1mAvalie de 0 a 10: \u001b[0m");
      Scanner avaliacao = new Scanner(System.in);
      int av = avaliacao.nextInt();
      System.out.println("\u001b[1m\u001b[30m \u001b[44m Avaliação do usuário: \u001b[0m");
      for (int i = 1; i < 10; i++) {
        if (i > av) {
          break;
        } else {
          System.out.print("\u001b[40m\u2B50\u001b[0m");
        }
      }
      System.out.println();
    }
  }
}