package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;

import java.util.ArrayList;

public class ExpressionStatement extends Statement {
    ArrayList<Token> c;
    ArrayList<Token> arguments;
    public ExpressionStatement(ArrayList<Token> ctx) {
        c = ctx;
        TokenStreamer s = new TokenStreamer(ctx);
        if(table.containsFunction(c.get(0)))
            arguments = getParam(s);
    }

    @Override
    public void eval() {
        if(table.containsFunction(c.get(0)))
        {
            table.getFunction(c.get(0)).overloadParam(arguments);
            table.getFunction(c.get(0)).JustInTimeEval();
        }
    }
}
