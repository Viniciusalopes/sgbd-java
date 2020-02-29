/*
 * The MIT License
 *
 * Copyright 2020 vovo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package controller;

import java.io.File;
import model.Banco;
import model.Config;
import txt.ArquivoTxt;

public class ControllerBanco {

    private Banco bd;
    private Config cfg;
    private String arquivoIni;
    private String arquivoTabela;

    public boolean criarBanco(String dirBanco, String nome) throws Exception {
        cfg = new Config();

        // dirBanco/nome.ini
        arquivoIni = dirBanco + cfg.getBarra() + nome + cfg.getBarra() + nome + ".ini";
        if (new File(arquivoIni).exists()) {
            throw new Exception("Banco já existe!");
        }

        bd = new Banco(cfg, dirBanco, nome);
        if (!bd.getDirBanco().exists()) {
            bd.getDirBanco().mkdirs();
        }

        String texto = "; Configurações do banco de dados '" + nome + "'\n"
                + "\n"
                + "[Banco]\n"
                + "dirBanco = " + dirBanco + "\n"
                + "nome = " + nome + "\n";

        ArquivoTxt.escreverNovoArquivo(arquivoIni, texto);
        return true;
    }

    public boolean criarTabela(String nome, String[] campos, char delimitador) throws Exception {
        arquivoTabela = bd.getDirBanco().getCanonicalFile().toString() + cfg.getBarra() + nome + ".bd";
        if (new File(arquivoTabela).exists()) {
            throw new Exception("Tabela já existe!");
        }

        // Tabela
        String texto = "ID|";

        for (String campo : campos) {
            texto += campo + delimitador;
        }
        texto = texto.substring(0, texto.length() - 1) + "\n"; // retira o último pipe
        ArquivoTxt.escreverNovoArquivo(arquivoTabela, texto);

        // nome.ini
        String textoIni = "\n"
                + "[Tabela " + nome + "]\n"
                + "tabela.nome = " + nome + "\n"
                + "tabela.delimitador = " + delimitador + "\n"
                + "tabela.campos = ID, ";

        for (String campo : campos) {
            textoIni += campo + ", ";
        }
        textoIni = textoIni.substring(0, textoIni.length() - 1) + "\n";

        ArquivoTxt.incluirTextoNoArquivo(arquivoIni, textoIni);
        return true;
    }

}
