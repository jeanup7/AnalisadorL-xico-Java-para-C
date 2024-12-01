package br.com.jean.compiler.lexico;

/**
 * Classe que representa um token no contexto de um analisador léxico para C.
 */
public class TokenC {

    // Constantes para representar diferentes tipos de tokens.
    public static final int TK_IDENTIFIER = 0; // Identificador (ex.: nomes de variáveis)
    public static final int TK_NUMBER = 1; // Número (ex.: literais numéricos)
    public static final int TK_OPERATOR = 2; // Operador genérico (ex.: +, -, *, /)
    public static final int TK_PONCTUATION = 3; // Pontuação (ex.: vírgulas, pontos, etc.)
    public static final int TK_RESERVED = 5; // Palavras reservadas da linguagem C
    public static final int TK_STRING = 6; // Literais de string (ex.: "texto")
    public static final int TK_DELIMITER = 7; // Delimitadores (ex.: {, }, (, ), [, ])
    public static final int TK_ARITHMETIC_OPERATOR = 8; // Operador aritmético (ex.: +, -, *, /)
    public static final int TK_COMPARISON_OPERATOR = 9; // Operador de comparação (ex.: ==, !=, <, >, <=, >=)
    public static final int TK_LOGICAL_OPERATOR = 10; // Operador lógico (ex.: &&, ||, !)
    public static final int TK_ASSIGNMENT_OPERATOR = 11; // Operador de atribuição (ex.: =, +=, -=)
    public static final int TK_INCREMENT_OPERATOR = 12; // Operador de incremento (ex.: ++)
    public static final int TK_DECREMENT_OPERATOR = 13; // Operador de decremento (ex.: --)

    // Atributos do token.
    private int type; // Representa o tipo do token (usando as constantes acima)
    private String text; // O texto associado ao token (ex.: "var", "42", "+").

    /**
     * Construtor que inicializa o token com o tipo e o texto fornecidos.
     *
     * @param type O tipo do token.
     * @param text O texto associado ao token.
     */
    public TokenC(int type, String text) {
        this.type = type;
        this.text = text;
    }

    /**
     * Construtor padrão (inicializa um token vazio).
     */
    public TokenC() {
        // Inicializa um token vazio.
    }

    /**
     * Retorna o tipo do token.
     *
     * @return Tipo do token.
     */
    public int getType() {
        return type;
    }

    /**
     * Define o tipo do token.
     *
     * @param type O tipo do token a ser definido.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Retorna o texto associado ao token.
     *
     * @return Texto do token.
     */
    public String getText() {
        return text;
    }

    /**
     * Define o texto associado ao token.
     *
     * @param text O texto a ser definido.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Representação do token como string.
     *
     * @return Uma string representando o token.
     */
    @Override
    public String toString() {
        return "TokenC [type=" + type + ", text='" + text + "']";
    }
}
