package Statement;

import Lexer.Token;

import java.util.ArrayList;

public class EmptyStatement extends Statement {

    public EmptyStatement(ArrayList<Token> t){}

    @Override
    public void eval() {

    }
}
