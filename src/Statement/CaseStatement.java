package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;

import java.util.ArrayList;

public class CaseStatement extends Statement {

    Token valcase;
    Statement stmtcase;
    public CaseStatement(ArrayList<Token> ctx)
    {
        ctx.remove(0);
        valcase = ctx.get(0);
        TokenStreamer s = new TokenStreamer(ctx);
        s.next();s.next();
        stmtcase = Statement.create(s.getUntilLevel(";"));
        switchCase.getCurrent().addCase(this);
    }

    @Override
    public void eval() {
        stmtcase.eval();
    }
}
