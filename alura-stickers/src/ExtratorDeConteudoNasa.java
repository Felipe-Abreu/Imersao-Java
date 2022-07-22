import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoNasa implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json) {

        // extrair só os dados que inressam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();
        
        //popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("url");
            var conteudo = new Conteudo(titulo, urlImagem, 0);
            conteudos.add(conteudo);
        }
        
        return conteudos;
    }

}
