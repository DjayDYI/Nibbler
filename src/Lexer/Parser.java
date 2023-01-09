package Lexer;

import Lexer.Token;
import Lexer.TokenStreamer;
import Statement.Statement;

import java.util.ArrayList;

public class Parser {

    Parser(TokenStreamer s)
    {
        ArrayList<Statement> main = new ArrayList<>();
        while (s.hasNext())
        {
            ArrayList<Token> token = s.getUntilSameLevel(";");
            main.add(Statement.create(token));
            if(s.hasNext())
                s.next();
        }
        for(Statement s2: main)
            s2.eval();
    }

}
