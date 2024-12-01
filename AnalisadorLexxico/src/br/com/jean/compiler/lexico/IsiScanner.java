package br.com.jean.compiler.lexico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import br.com.jean.compiler.exceptions.LexicalException;

public class IsiScanner {
    private char[] content;
    private int status;
    private int position;

    public IsiScanner(String filename) {
        try {
            // Imprime o caminho absoluto do arquivo
            System.out.println("Tentando abrir o arquivo: " + Paths.get(filename).toAbsolutePath());
            
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            String txtContent = new String(bytes, StandardCharsets.UTF_8).trim();
            if (txtContent.isEmpty()) {
                throw new LexicalException("Arquivo vazio!");
            }
            
            System.out.println("DEBUG----");
            System.out.println(txtContent);  // Isso ajudará a verificar se o conteúdo foi lido corretamente.
            content = txtContent.toCharArray();
            position = 0;
        } catch (Exception ex) {
            throw new LexicalException("Erro ao ler o arquivo: " + ex.getMessage());
        }
    }

    public TokenC nextToken() {
        char currentChar;
        TokenC token = new TokenC();
        String term = "";
        status = 0;

        while (true) {
            if (isEndOfFile()) {
                return null;
            }

            currentChar = nextChar();

            switch (status) {
                case 0:
                    if (isChar(currentChar)) {
                        term += currentChar;
                        status = 1;
                    } else if (isNumber(currentChar)) {
                        term += currentChar;
                        status = 3;
                    } else if (isSpace(currentChar)) {
                        status = 0;
                    } else if (currentChar == '"') {
                        status = 5;
                    } else if (isDelimiter(currentChar)) {
                        token.setType(TokenC.TK_DELIMITER);
                        token.setText(String.valueOf(currentChar));
                        return token;
                    } else if (isPunctuation(currentChar)) {
                        token.setType(TokenC.TK_PONCTUATION);
                        token.setText(String.valueOf(currentChar));
                        return token;
                    } else if (isOperator(currentChar)) {
                        char next = peekChar();  // Usando peekChar ao invés de nextChar
                        if (isComparisonOperator(currentChar, next)) {
                            token.setType(TokenC.TK_COMPARISON_OPERATOR);
                            token.setText("" + currentChar + next);
                            position++; // Avança a posição após detectar um operador composto
                            return token;
                        } else if (isLogicalOperator(currentChar, next)) {
                            token.setType(TokenC.TK_LOGICAL_OPERATOR);
                            token.setText("" + currentChar + next);
                            position++; // Avança a posição após detectar um operador lógico
                            return token;
                        } else if (isAssignmentOperator(currentChar, next)) {
                            token.setType(TokenC.TK_ASSIGNMENT_OPERATOR);
                            token.setText("" + currentChar + next);
                            position++; // Avança a posição após detectar um operador de atribuição
                            return token;
                        } else if (isArithmeticOperator(currentChar)) {
                            token.setType(TokenC.TK_ARITHMETIC_OPERATOR);
                            token.setText(String.valueOf(currentChar));
                            return token;
                        } else {
                            token.setType(TokenC.TK_OPERATOR);
                            token.setText(String.valueOf(currentChar));
                            return token;
                        }
                    }
                    break;

                case 1:
                    if (isChar(currentChar) || isNumber(currentChar)) {
                        term += currentChar;
                    } else {
                        back();
                        status = 2;
                    }
                    break;

                case 2:
                    if (isReservedWord(term)) {
                        token.setType(TokenC.TK_RESERVED);
                    } else {
                        token.setType(TokenC.TK_IDENTIFIER);
                    }
                    token.setText(term);
                    return token;

                case 3:
                    if (isNumber(currentChar)) {
                        term += currentChar;
                    } else {
                        back();
                        status = 4;
                    }
                    break;

                case 4:
                    token.setType(TokenC.TK_NUMBER);
                    token.setText(term);
                    return token;

                case 5:
                    if (currentChar != '"') {
                        term += currentChar;
                    } else {
                        token.setType(TokenC.TK_STRING);
                        token.setText(term);
                        return token;
                    }
                    break;
            }
        }
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isOperator(char c) {
        return c == '>' || c == '<' || c == '=' || c == '!' || c == '+' || c == '-' || c == '*' || c == '/' || c == '&';
    }

    private boolean isSpace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private boolean isDelimiter(char c) {
        return c == '(' || c == ')' || c == '{' || c == '}' || c == '[' || c == ']';
    }

    private boolean isPunctuation(char c) {
        return c == ',' || c == ';';
    }

    private boolean isReservedWord(String text) {
        return text.equals("int") || text.equals("float") || text.equals("return") ||
               text.equals("if") || text.equals("else") || text.equals("for") || 
               text.equals("while") || text.equals("break") || text.equals("continue") || 
               text.equals("void") || text.equals("char") || text.equals("double") || 
               text.equals("include") || text.equals("define");
    }

    private char nextChar() {
        return content[position++];
    }

    private char peekChar() {
        if (position < content.length) {
            return content[position];
        }
        return '\0';
    }

    private boolean isEndOfFile() {
        return position == content.length;
    }

    private void back() {
        position--;
    }

    private boolean isComparisonOperator(char current, char next) {
        return (current == '=' && next == '=') || (current == '!' && next == '=') ||
               (current == '<' && next == '=') || (current == '>' && next == '=');
    }

    private boolean isAssignmentOperator(char current, char next) {
        return (current == '+' && next == '=') || (current == '-' && next == '=') ||
               (current == '*' && next == '=') || (current == '/' && next == '=');
    }

    private boolean isArithmeticOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean isLogicalOperator(char current, char next) {
        return (current == '&' && next == '&') || (current == '|' && next == '|');
    }
}
