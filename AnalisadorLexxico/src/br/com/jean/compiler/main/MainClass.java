package br.com.jean.compiler.main;

import br.com.jean.compiler.exceptions.LexicalException;
import br.com.jean.compiler.lexico.IsiScanner;
import br.com.jean.compiler.lexico.TokenC;

public class MainClass {
    public static void main(String[] args) {
        try {
            // Criação do objeto IsiScanner para ler o arquivo "input.isi"
            IsiScanner sc = new IsiScanner("input.isi"); 
            TokenC token = null;
            
            // Laço para pegar todos os tokens até o final do arquivo
            do {
                token = sc.nextToken();  // Obtém o próximo token
                if (token != null) {
                    System.out.println(token); // Imprime o token (toString() será chamado automaticamente)
                }
            } while (token != null);
            
        } catch(LexicalException ex) {
            // Caso ocorra um erro léxico (ex: erro na análise)
            System.out.println("Lexical ERROR: " + ex.getMessage());
            
        } catch (Exception ex) {
            // Caso ocorra qualquer outro erro
            System.out.println("Generic ERROR: " + ex.getMessage());
        }
    }
}
