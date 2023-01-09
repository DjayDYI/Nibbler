package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;

import java.util.ArrayList;

public class ElseStatement extends Statement {
    private ArrayList<Statement> instrue= new ArrayList<>();
    public ElseStatement(ArrayList<Token> ctx) {
        ctx.remove(0);
        TokenStreamer s = new TokenStreamer(ctx);
        while(s.hasNext())
        {
            s.getNext();
            if(s.hasNext())
                instrue.add(Statement.create(s.getUntilLevel(";")));
        }
        current.getCurrent().setInstrFalse(instrue);
    }

    @Override
    public void eval() {

    }
}
