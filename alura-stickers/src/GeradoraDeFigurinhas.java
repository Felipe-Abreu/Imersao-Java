import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    private int width;

    public void gera(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, null, 0, 0);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.CYAN);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", largura - 500, novaAltura - 100);

        // escrever a nova imagem em um arquivo

        File salvaImagem = new File(nomeArquivo);
        if (!salvaImagem.getParentFile().exists()) {
            salvaImagem.getParentFile().mkdirs();
        }
        if (!salvaImagem.exists()) {
            ImageIO.write(novaImagem, "png", salvaImagem);
        }

    }
}
