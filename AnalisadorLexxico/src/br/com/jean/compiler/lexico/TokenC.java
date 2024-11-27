package br.com.jean.compiler.lexico;

// Classe que representa um token no contexto de um analisador léxico para C.
public class TokenC {
    // Constantes para representar diferentes tipos de tokens.
    public static final int TK_IDENTIFIER = 0; // Identificador (ex.: nomes de variáveis)
    public static final int TK_NUMBER = 1; // Número (ex.: literais numéricos)
    public static final int TK_OPERATOR = 2; // Operador genérico (ex.: +, -, *, /)
    public static final int TK_PONCTUATION = 3; // Pontuação (ex.: vírgulas, pontos, etc.)
    public static final int TK_RESERVED = 5; // Palavras reservadas da linguagem C.
    public static final int TK_STRING = 6; // Literais de string (ex.: "texto")
    public static final int TK_DELIMITER = 7; // Delimitadores (ex.: {, }, (, ), [, ])
    public static final int TK_ARITHMETIC_OPERATOR = 8; // Operador Aritmético (ex.: +, -, *, /)
    public static final int TK_COMPARISON_OPERATOR = 9; // Operador de Comparação (ex.: ==, !=, <, >, <=, >=)
    public static final int TK_LOGICAL_OPERATOR = 10; // Operador Lógico (ex.: &&, ||, !)
    public static final int TK_ASSIGNMENT_OPERATOR = 11; // Operador de Atribuição (ex.: =, +=, -=)
    public static final int TK_INCREMENT_OPERATOR = 12; // Operador de Incremento (ex.: ++)
    public static final int TK_DECREMENT_OPERATOR = 13; // Operador de Decremento (ex.: --)

    // Atributos que armazenam o tipo do token e o texto associado a ele.
    private int type; // Representa o tipo do token usando as constantes acima.
    private String text; // O texto do token (ex.: "var", "42", "+").

    // Construtor que inicializa um token com o tipo e o texto fornecidos.
    public TokenC(int type, String text) {
        super(); // Chamada ao construtor da classe base (opcional, neste caso).
        this.type = type; // Define o tipo do token.
        this.text = text; // Define o texto do token.
    }

    // Construtor padrão (sem parâmetros).
    public TokenC() {
        super(); // Chamada ao construtor da classe base.
    }

    // Métodos getter e setter para o atributo `type`.
    public int getType() { // Retorna o tipo do token.
        return type;
    }

    public void setType(int type) { // Define o tipo do token.
        this.type = type;
    }

    // Métodos getter e setter para o atributo `text`.
    public String getText() { // Retorna o texto do token.
        return text;
    }

    public void setText(String text) { // Define o texto do token.
        this.text = text;
    }

    @Override
    public String toString() {
        return "TokenC [type=" + type + ", text='" + text + "']";
    }
}
