import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickerGenerator {
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        //leitura da imagem
        //BufferedImage imagemOriginal = ImageIO.read(new File("entrada/filme-maior.jpg"));

        //melhorando a abstração
        //InputStream inputStream = new FileInputStream(new File("entrada/filme-maior.jpg"));
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //criar uma nova imagem em memoria com transparencia
        int largura;
        largura = imagemOriginal.getWidth();
        int altura;
        altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura,BufferedImage.TRANSLUCENT);

        //copiar a imagem original para a nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        graphics.drawString("FILMÃO", 210, novaAltura - 100);

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

    /*  classe MAIN para testes iniciais
    public static void main(String[] args) throws Exception {
        File saida = new File("C:\\Users\\tatip\\Documents\\Estudos\\ALURA\\Imersões Alura\\Imersão Java Alura_jul 2022\\Pepper-Movie-Stickers\\saida");
        if (saida.mkdir()) {
            System.out.println("Saida criada");
        } else {
            System.out.println("Saida não criada");
        }
        var generator = new StickerGenerator();
        generator.cria();
    }*/

}
