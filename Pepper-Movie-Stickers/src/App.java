import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class App {

    public static void main (String[] args) throws Exception {

        //fazer uma conexão HTTP e buscar os top 250 filmes

        //String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMBD();

        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-29&end_date=2022-07-31";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url); //json porque sabemos que é um json, mas poderia ser diferente

        //exibir e manipular os dados como desejado
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var generator = new StickerGenerator();

        for (int i = 0; i <3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            generator.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

    }

}
