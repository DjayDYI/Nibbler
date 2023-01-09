package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;
import Statement.Statement;

import java.util.ArrayList;

public class WhileStatement extends Statement {
    private Condition condition;
    private ArrayList<Statement> instrue= new ArrayList<>();
    public WhileStatement(ArrayList<Token> ctx) {
        ctx.remove(0);
        TokenStreamer s = new TokenStreamer(ctx);
        condition = getCond(s);
        while(s.hasNext())
        {
            s.getNext();
            if(s.hasNext())
                instrue.add(Statement.create(s.getUntilLevel(";")));
        }
    }

    @Override
    public void eval() {
        if (condition.eval().equals("true"))
        {
            for(Statement s:instrue)
            {
                s.eval();
            }
            eval();
        }
    }
}

