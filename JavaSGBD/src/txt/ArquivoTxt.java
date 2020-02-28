package txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoTxt {

    // FONTE: https://www.devmedia.com.br/classes-de-entrada-e-saida-de-dados-em-java/26029
    /**
     * Leitor de arquivos de texto
     *
     * @param arquivo Caminho completo do arquivo
     * @return Lista com as linhas do arquvo
     * @throws Exception
     */
    public static List<String> lerArquivo(String arquivo) throws Exception {
        List<String> retorno = new ArrayList<>();

        // EFETUANDO A LEITURA
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        String linha;

        //ENQUANTO EXISTEM LINHAS CONTINUA ADICIONANDO
        while ((linha = br.readLine()) != null) {
            retorno.add(linha);
        }
        br.close();

        return retorno;
    }

    /**
     * Buscador de linha com palavra(s) chave(s)
     *
     * @param arquivo Caminho completo do arquivo
     * @param textoProcurado
     * @return Linha contendo o texto procurado
     * @throws Exception
     */
    public static String lerLinha(String arquivo, String textoProcurado) throws Exception {
        String retorno = "";

        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        String linha;

        while ((linha = br.readLine()) != null) {
            if (linha.equals(textoProcurado)) {
                retorno = linha;
                break;
            }
        }
        return retorno;
    }

    /**
     * Escritor de arquivos de texto
     *
     * @param arquivo Caminho completo do arquivo
     * @param texto Texto a ser adicionado ao arquivo
     * @throws Exception
     */
    public static void escreverNoArquivo(String arquivo, String texto) throws Exception {

        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(texto);
        bw.newLine();
        bw.flush();
        bw.close();

    }

    /**
     * Substitui uma linha no arquivo de texto
     *
     * @param arquivo Caminho completo do arquivo
     * @param textoProcurado
     * @param novoTexto
     * @throws Exception
     */
    public static void substituirLinha(String arquivo, String textoProcurado, String novoTexto) throws Exception {
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        List<String> linhas = lerArquivo(arquivo);

        for (String linha : linhas) {
            bw.write((linha.equals(textoProcurado)) ? novoTexto : linha);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    /**
     * Exclui uma linha de um arquivo de texto
     *
     * @param arquivo Caminho completo do arquivo
     * @param texto Linha a ser excluída
     * @throws IOException
     */
    public static void excluirLinha(String arquivo, String texto) throws Exception {
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        List<String> linhas = lerArquivo(arquivo);

        for (String linha : linhas) {
            if (!linha.equals(texto)) {
                bw.write(linha);
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }

    /**
     * Verifica a existência de uma linha no arquivo
     *
     * @param path Caminho do arquivo
     * @param texto Linha pesquisada
     * @return true = Existe
     * @throws Exception
     */
    public static boolean linhaExiste(String arquivo, String texto) throws Exception {
        if (lerLinha(arquivo, texto).equals(texto)) {
            return true;
        }
        return false;
    }
}
