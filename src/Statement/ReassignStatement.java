package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;
import Lexer.Tree;
import Statement.Statement;

import java.util.ArrayList;

public class ReassignStatement extends AssignationStatement {

    private ArrayList<Token> ctx;
    public ReassignStatement(ArrayList<Token> ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    private String valueFromTable(Token t){
        if(table.contains(t)){
            return table.getValue(t);
        }
        return t.getVal();
    }
    private ArrayList<Token> fetch(String left, String right, Token oper){
        return getTokens(left, right, oper);
    }

    @Override
    public void eval() {
        if(ctx.get(0).getVal().contains("[")){
            String idx = ctx.get(0).getVal().substring(ctx.get(0).getVal().indexOf("[")+1, ctx.get(0).getVal().indexOf("]"));
            table.reassign(ctx.get(0),ctx.get(2),Integer.parseInt(idx));
        }
        else if(ctx.size() < 5) {
            if(ctx.get(2).getVal().contains("["))
            {
                String name = ctx.get(2).getVal().substring(0,ctx.get(2).getVal().indexOf("["));
                String index = ctx.get(2).getVal().substring(ctx.get(2).getVal().indexOf("[")+1, ctx.get(2).getVal().indexOf("]"));
                String val = table.getValue(name, index);
                table.reassign(name, val);
            }
            else
                table.reassign(ctx.get(0), ctx.get(2));
        }else{
            String left = valueFromTable(ctx.get(2));
            String right = valueFromTable(ctx.get(4));
            ArrayList<Token> overload = fetch(left,right,ctx.get(3));
            Tree ast = new Tree(overload);
            String result = ast.parse();
            table.reassign(ctx.get(0),result);
        }
    }
}
