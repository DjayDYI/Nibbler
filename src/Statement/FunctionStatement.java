package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;
import Statement.Statement;

import java.util.ArrayList;

public class FunctionStatement extends Statement {

    private Token name;
    private ArrayList<Token> param;
    private ArrayList<Token> overload = new ArrayList<>();
    private ArrayList<Statement> context = new ArrayList<>();
    private ReturnStatement ret;
    public FunctionStatement(ArrayList<Token> ctx) {
        ctx.remove(0);
        this.name = ctx.get(0);
        TokenStreamer s = new TokenStreamer(ctx);
        s.next();
        this.param = getParam(s);

        while(s.hasNext())
        {
            s.getNext();
            if(s.hasNext())
            {
                Statement stmt = Statement.create(s.getUntilLevel(";"));
                if(stmt instanceof ReturnStatement)
                {
                    ret = (ReturnStatement) stmt;
                }
                else
                {
                    context.add(stmt);
                }

            }

        }

        if(!table.containsFunction(this.name)){
            table.addFunction(this.name, this);
        }
    }

    @Override
    public void eval() {}

    public String JustInTimeEval()
    {
        for(int i=0;i<param.size();i++){
            VariableStatement stmt = new VariableStatement(declareParameter(i));
            stmt.eval();
        }

        for(Statement s:context) {
            s.eval();
        }

        if(ret != null) {
            ret.eval();
            return table.getValue(new Token("retval", Token.TokenType.IDENTIFIER));
        }else{
            return "void";
        }

    }

    private ArrayList<Token> declareParameter(int i){
        ArrayList<Token> p = new ArrayList<>();
        p.add(new Token("var", Token.TokenType.IDENTIFIER));
        p.add(new Token(param.get(i).getVal(), Token.TokenType.IDENTIFIER));
        p.add(new Token("=", Token.TokenType.ASSIGN));
        p.add(new Token(overload.get(i).getVal(), Token.TokenType.ASSIGN));
        p.add(new Token(";", Token.TokenType.NULL));
        return p;
    }

    public void overloadParam(ArrayList<Token> arguments) {
        overload = arguments;
    }
}
