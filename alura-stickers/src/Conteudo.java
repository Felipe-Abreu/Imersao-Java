public class Conteudo {
    
    private final String titulo;
    private final String urlImagem;
    private final int classificacao;

    public Conteudo(String titulo, String urlImagem, int classificacao) {
        this.titulo = titulo;
        this.urlImagem = urlImagem;
        this.classificacao = classificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public int getClassificacao() {
        return classificacao;
    }
    
}
