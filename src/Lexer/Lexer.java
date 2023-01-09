package Lexer;

import java.util.ArrayList;

public class Lexer {
    public ArrayList<Token> tkn = new ArrayList<>();
    public Lexer(String str){
        str = str.replace("(", " ( ");
        str = str.replace(")", " ) ");
        str = str.replace("+", " + ");
        str = str.replace("-", " - ");
        str = str.replace("*", " * ");
        str = str.replace("/", " / ");
        str = str.replace("%", " % ");
        str = str.replace(";", " ; ");
        str = str.replace(":", " : ");
        str = str.replace(",", " , ");
        str = str.replace("{", " { ");
        str = str.replace("}", " } ");
        str = str.replace("<", " < ");
        str = str.replace("<=", " <= ");
        str = str.replace(">", " > ");
        str = str.replace("=>", " => ");
        str = str.replace("==", " == ");
        str = str.replace(":=", " := ");
        str = str.replace("!=", " != ");
        String[] split = str.split(" ");
        split = clean(split);

        for(String s : split){
            if(s != null){
                if(s.equals("("))tkn.add(new Token(s, Token.TokenType.PARENTHESIS));
                else if(s.equals(")"))tkn.add(new Token(s, Token.TokenType.PARENTHESIS));
                else if(s.equals("}"))tkn.add(new Token(s, Token.TokenType.SCOPE));
                else if(s.equals("{"))tkn.add(new Token(s, Token.TokenType.SCOPE));
                else if(s.equals(","))tkn.add(new Token(s, Token.TokenType.SEPARATOR));
                else if(s.equals("+"))tkn.add(new Token(s, Token.TokenType.INT_OPERATOR));
                else if(s.equals("*"))tkn.add(new Token(s, Token.TokenType.INT_OPERATOR));
                else if(s.equals("-"))tkn.add(new Token(s, Token.TokenType.INT_OPERATOR));
                else if(s.equals("/"))tkn.add(new Token(s, Token.TokenType.INT_OPERATOR));
                else if(s.equals("%"))tkn.add(new Token(s, Token.TokenType.INT_OPERATOR));
                else if(s.equals("="))tkn.add(new Token(s, Token.TokenType.ASSIGN));
                else if(s.equals(";"))tkn.add(new Token(s, Token.TokenType.NULL));
                else if(s.equals(":"))tkn.add(new Token(s, Token.TokenType.SEPARATOR));
                else if(s.equals("<"))tkn.add(new Token(s, Token.TokenType.BOOL_INT_OPERATOR));
                else if(s.equals(">"))tkn.add(new Token(s, Token.TokenType.BOOL_INT_OPERATOR));
                else if(s.equals("<="))tkn.add(new Token(s, Token.TokenType.BOOL_INT_OPERATOR));
                else if(s.equals(">="))tkn.add(new Token(s, Token.TokenType.BOOL_INT_OPERATOR));
                else if(s.equals("=="))tkn.add(new Token(s, Token.TokenType.BOOL_OPERATOR));
                else if(s.equals("!="))tkn.add(new Token(s, Token.TokenType.BOOL_OPERATOR));
                else if(s.equals("func"))tkn.add(new Token(s, Token.TokenType.CONTROL));
                else if(s.equals("var"))tkn.add(new Token(s, Token.TokenType.KEYWORD));
                else if(s.equals("const"))tkn.add(new Token(s, Token.TokenType.KEYWORD));
                else if(s.equals("void"))tkn.add(new Token(s, Token.TokenType.VOID));
                else if(s.equals("if"))tkn.add(new Token(s, Token.TokenType.CONTROL));
                else if(s.equals("else"))tkn.add(new Token(s, Token.TokenType.CONTROL));
                else if(s.equals("while"))tkn.add(new Token(s, Token.TokenType.CONTROL));
                else if(s.equals("for"))tkn.add(new Token(s, Token.TokenType.CONTROL));
                else if(s.equals("return"))tkn.add(new Token(s, Token.TokenType.VOID));
                else if(s.equals("switch"))tkn.add(new Token(s, Token.TokenType.CONTROL));
                else if(s.equals("print"))tkn.add(new Token(s, Token.TokenType.KEYWORD));
                else if(isNumeric(s)) tkn.add(new Token(s, Token.TokenType.NUMERIC));
                else if(s.contains("\"")) tkn.add(new Token(s, Token.TokenType.STRING));
                else if(s.contains("[")&&s.contains("]")) tkn.add(new Token(s, Token.TokenType.ARRAY));
                else if(s.equals("true")||s.equals("false")) tkn.add(new Token(s, Token.TokenType.BOOL));
                else tkn.add(new Token(s, Token.TokenType.IDENTIFIER));
            }
        }
    }

    private String[] clean(String[] input) {
        String[] clean = new String[input.length];
        int j = 0;

        for (String s : input) {
            if (!s.equals("")) {
                clean[j] = s;
                ++j;
            }
        }
        return clean;
    }
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

}

