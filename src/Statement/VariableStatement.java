package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;
import Lexer.Tree;
import Statement.Statement;

import java.util.ArrayList;

public class VariableStatement extends AssignationStatement {

    private ArrayList<Token> ctx;
    Token name;
    boolean isarray = false;
    public VariableStatement(ArrayList<Token> ctx) {
        super(ctx);
        ctx.remove(0);// var
        this.ctx = ctx;
        this.name = ctx.get(0);
        isarray = this.name.getVal().contains("[");
    }

    @Override
    public void eval() {
        if(isarray)
        {
            String name = this.name.getVal().replace("[]","");
            table.addVariable(name, ctx);
        }
        else if(table.containsFunction(ctx.get(2)))
        {
            functionCall(this.name.getVal(),2);
        }
        else if(ctx.size() > 4)
        {
            expression(this.name.getVal());
        }
        else
        {
            if(table.contains(ctx.get(2)))
                table.addVariable(this.name,table.getValue(ctx.get(2)));
            else
                table.addVariable(this.name,ctx.get(2));
        }
    }
}
