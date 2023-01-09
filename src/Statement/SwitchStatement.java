package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;

import java.util.ArrayList;

public class SwitchStatement extends Statement {

    private ArrayList<Token> condition;
    private ArrayList<CaseStatement> instrcase= new ArrayList<>();

    SwitchStatement(ArrayList<Token> ctx){
        switchCase.setCurrent(this);
        ctx.remove(0);
        TokenStreamer s = new TokenStreamer(ctx);
        condition = getParam(s);
        while(s.hasNext())
        {
            Statement.create(s.getUntilLevel(";"));
        }
    }

    @Override
    public void eval() {
        for(CaseStatement cs:instrcase)
        {
            if(cs.valcase.getVal().equals(fromTable(condition))){
                cs.eval();
            }
        }
    }

    private String fromTable(ArrayList<Token> cond){
        if(table.contains(cond.get(0))){
            return table.getValue(cond.get(0));
        }
        return cond.get(0).getVal();
    }

    public void addCase(CaseStatement caseStatement) {
        instrcase.add(caseStatement);
    }
}
