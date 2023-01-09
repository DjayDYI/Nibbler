package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;
import Statement.FlowTable.FlowTable;
import Statement.SymbolTable.TokenTable;

import java.util.ArrayList;

public abstract class Statement implements FlowTable, TokenTable {

    public static Statement create(ArrayList<Token> ctx)
    {
        if(ctx.size()==1)                               return new EmptyStatement(ctx);
        else if(ctx.get(0).getVal().equals("if"))       return new IfStatement(ctx);
        else if(ctx.get(0).getVal().equals("else"))     return new ElseStatement(ctx);
        else if(ctx.get(0).getVal().equals("while"))    return new WhileStatement(ctx);
        else if(ctx.get(0).getVal().equals("switch"))   return new SwitchStatement(ctx);
        else if(ctx.get(0).getVal().equals("case"))     return new CaseStatement(ctx);
        else if(ctx.get(0).getVal().equals("for"))      return new ForStatement(ctx);
        else if(ctx.get(0).getVal().equals("var"))      return new VariableStatement(ctx);
        else if(ctx.get(0).getVal().equals("func"))     return new FunctionStatement(ctx);
        else if(ctx.get(0).getVal().equals("return"))   return new ReturnStatement(ctx);
        else if(ctx.get(0).getVal().equals("print"))    return new PrintStatement(ctx);
        else if(ctx.get(1).getVal().equals("="))        return new ReassignStatement(ctx);
        else                                            return new ExpressionStatement(ctx);
    }

    protected static Condition getCond(TokenStreamer ctx)
    {
        ArrayList<Token> cond = ctx.getUntil(")");
        return new Condition(cond);
    }

    protected static ArrayList<Token> getParam(TokenStreamer ctx)
    {
        return ctx.getUntil(")");
    }

    public abstract void eval();
}
