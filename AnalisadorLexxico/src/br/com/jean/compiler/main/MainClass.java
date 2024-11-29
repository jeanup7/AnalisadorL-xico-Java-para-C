package br.com.jean.compiler.main;

import br.com.jean.compiler.exceptions.LexicalException;
import br.com.jean.compiler.lexico.IsiScanner;
import br.com.jean.compiler.lexico.TokenC;

public class MainClass {
    public static void main(String[] args) {
        try {
            // Criação do objeto IsiScanner para ler o arquivo "input.jian"
            IsiScanner sc = new IsiScanner("input.jian");
            TokenC token = null;
            
            // Imprimir cabeçalho da tabela
            System.out.println("----------------------------------------------------------");
            System.out.println("| Tipo de Token                | Valor do Token         |");
            System.out.println("----------------------------------------------------------");
            
            // Laço para pegar todos os tokens até o final do arquivo
            do {
                token = sc.nextToken();  // Obtém o próximo token
                if (token != null) {
                    // Ajustar a largura das colunas para alinhar bem os tokens e seus valores
                    System.out.printf("| %-27s | %-22s |\n", getTokenTypeString(token.getType()), token.getText());
                }
            } while (token != null);
            
            // Imprimir rodapé da tabela
            System.out.println("----------------------------------------------------------");
            
        } catch(LexicalException ex) {
            // Caso ocorra um erro léxico (ex: erro na análise)
            System.out.println("Lexical ERROR: " + ex.getMessage());
            
        } catch (Exception ex) {
            // Caso ocorra qualquer outro erro
            System.out.println("Generic ERROR: " + ex.getMessage());
        }
    }
    
    // Método auxiliar para obter o tipo do token como string
    private static String getTokenTypeString(int type) {
        switch (type) {
            case TokenC.TK_IDENTIFIER:
                return "IDENTIFICADOR";
            case TokenC.TK_NUMBER:
                return "NÚMERO";
            case TokenC.TK_OPERATOR:
                return "OPERADOR";
            case TokenC.TK_PONCTUATION:
                return "PONTUAÇÃO";
            case TokenC.TK_RESERVED:
                return "PALAVRA RESERVADA";
            case TokenC.TK_STRING:
                return "STRING";
            case TokenC.TK_DELIMITER:
                return "DELIMITADOR";
            case TokenC.TK_ARITHMETIC_OPERATOR:
                return "OPERADOR ARITMÉTICO";
            case TokenC.TK_COMPARISON_OPERATOR:
                return "OPERADOR DE COMPARAÇÃO";
            case TokenC.TK_LOGICAL_OPERATOR:
                return "OPERADOR LÓGICO";
            case TokenC.TK_ASSIGNMENT_OPERATOR:
                return "OPERADOR DE ATRIBUIÇÃO";
            case TokenC.TK_INCREMENT_OPERATOR:
                return "OPERADOR DE INCREMENTO";
            case TokenC.TK_DECREMENT_OPERATOR:
                return "OPERADOR DE DECREMENTO";
            default:
                return "DESCONHECIDO";
        }
    }
}