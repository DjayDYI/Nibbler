package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;

import java.util.ArrayList;

public class IfStatement extends Statement {


    private Condition condition;
    private ArrayList<Statement> instrue= new ArrayList<>();
    private ArrayList<Statement> instrfalse= new ArrayList<>();
    private boolean hasElse = false;
    private boolean foundelse = false;
    public IfStatement(ArrayList<Token> ctx){
        current.setCurrent(this);
        ctx.remove(0);
        TokenStreamer s = new TokenStreamer(ctx);
        condition = getCond(s);
        hasElse = s.contains("else");
        s.getNext();
        while(s.hasNext())
        {
            if(hasElse)
            {
                if(s.get().getVal().equals("else")) {
                    foundelse = true;
                    s.next();
                }

                ArrayList<Token> t = s.getUntilLevel(";");
                if(!foundelse){
                    instrue.add(Statement.create(t));
                } else {
                    instrfalse.add(Statement.create(t));
                }
            }
            else
            {
                instrue.add(Statement.create(s.getUntilLevel(";")));
            }
        }
    }
    public void eval(){
        if(condition.eval().equals("true"))
        {
            for(Statement s:instrue)
            {
                s.eval();
            }
        }
        else
        {
            for(Statement s:instrfalse)
            {
                s.eval();
            }
        }
    }

    public void setInstrFalse(ArrayList<Statement> instrue) {
        this.instrfalse = instrue;
    }
}
