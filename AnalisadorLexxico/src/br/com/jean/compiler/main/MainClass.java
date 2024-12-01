package br.com.jean.compiler.main;

import br.com.jean.compiler.exceptions.LexicalException;
import br.com.jean.compiler.lexico.IsiScanner;
import br.com.jean.compiler.lexico.TokenC;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainClass {
    public static void main(String[] args) {
        try {
            // Criação do objeto IsiScanner para ler o arquivo "input.c"
            IsiScanner sc = new IsiScanner("input.jian");
            TokenC token = null;
            Map<String, Integer> symbolTable = new LinkedHashMap<>();

            // Listagem dos tokens
            System.out.println("LISTA DOS TOKENS");
            System.out.println("----------------------------------------------------------");
            System.out.println("| Tipo de Token                | Valor do Token         |");
            System.out.println("----------------------------------------------------------");

            do {
                try {
                    token = sc.nextToken();  // Obtém o próximo token
                    if (token != null) {
                        // Imprime o token
                        System.out.printf("| %-27s | %-22s |\n", getTokenTypeString(token.getType()), token.getText());

                        // Adiciona o token à tabela de símbolos
                        symbolTable.put(token.getText(), symbolTable.getOrDefault(token.getText(), 0) + 1);
                    }
                } catch (LexicalException e) {
                    System.out.println("Erro léxico encontrado: " + e.getMessage());
                }
            } while (token != null);

            System.out.println("----------------------------------------------------------");

            // Exibe a tabela de símbolos
            System.out.println("\nTABELA DE SÍMBOLOS");
            System.out.println("----------------------------------------------------------");
            System.out.println("| Símbolo                     | Quantidade             |");
            System.out.println("----------------------------------------------------------");
            for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
                System.out.printf("| %-27s | %-22d |\n", entry.getKey(), entry.getValue());
            }
            System.out.println("----------------------------------------------------------");

        } catch (Exception ex) {
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
