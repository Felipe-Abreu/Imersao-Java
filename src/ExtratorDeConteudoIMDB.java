import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoIMDB implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json) {

        // extrair só os dados que inressam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String urlImagem = atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String titulo = atributos.get("title").replaceAll(":", "-");
            double nota = Double.parseDouble(atributos.get("imDbRating"));
            int classificacao = (int) nota;
            var conteudo = new Conteudo(titulo, urlImagem, classificacao);
            conteudos.add(conteudo);
        }

        return conteudos;
    }

}
