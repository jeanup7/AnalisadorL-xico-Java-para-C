package br.com.jean.compiler.main;

import br.com.jean.compiler.exceptions.LexicalException;
import br.com.jean.compiler.lexico.IsiScanner;
import br.com.jean.compiler.lexico.TokenC;

public class AnalisadorLexxico {

    public static void main(String[] args) {
        try {
            // Criando o objeto IsiScanner para ler o arquivo "input.jian"
            IsiScanner scanner = new IsiScanner("input.jian");

            // Usando um laço para pegar todos os tokens até o final do arquivo
            TokenC token = null;
            while ((token = scanner.nextToken()) != null) {
                // Aqui podemos exibir os tokens ou processá-los de outra forma
                System.out.println(token); // Imprime o token (toString() será chamado automaticamente)
            }

        } catch (LexicalException lexException) {
            // Se ocorrer um erro léxico (erro durante a análise do texto)
            System.out.println("Lexical ERROR: " + lexException.getMessage());
        } catch (Exception ex) {
            // Qualquer outro tipo de erro genérico
            System.out.println("Generic ERROR: " + ex.getMessage());
        }
    }
}

