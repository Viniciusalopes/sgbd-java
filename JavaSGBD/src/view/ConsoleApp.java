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
package view;

import controller.ControllerBanco;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControllerBanco c = new ControllerBanco();

        try {

            String dirBanco = "/mnt/HD-500/projetos/sgbd-java/JavaSGBD/test";
            String nomeBanco = "Lalala_" + new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());

            if (c.criarBanco(dirBanco, nomeBanco)) {
                System.out.println("Banco '" + nomeBanco + "' criado com sucesso.\n"
                        + "Local do banco: " + dirBanco);
            } else {
                System.out.println("O banco não foi criado.");
            }

            String nomeTabela = "tabelaTeste";
            String[] campos = {"nome", "telefone", "idade"};
            char delimitador = '|';

            if (c.criarTabela(nomeTabela, campos, delimitador)) {
                System.out.println("Tabela '" + nomeTabela + "' criada com sucesso.\n");
            } else {
                System.out.println("A tabela '" + nomeTabela + "' não foi criada.");
            }
        } catch (Exception e) {
            System.out.println("Opa!\n" + ((e.getMessage() == null) ? e : e.getMessage()));
        }
    }
}
