package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;
import Lexer.Tree;

import java.util.ArrayList;

public class ReturnStatement extends AssignationStatement{

    ArrayList<Token> ctx;
    ReturnStatement(ArrayList<Token> ctx){
        super(ctx);
        this.ctx = ctx;
    }

    @Override
    public void eval() {

        if (table.containsFunction(ctx.get(1)))
        {
            functionCall("retvar", 1);
            retfunction("tmpret");

        }
        else if(ctx.size()>3)
        {
            Token left = getOperand(ctx.get(1));
            Token right = getOperand(ctx.get(3));
            retfunction(getResult(left,right,ctx.get(2)));
        }
        else
        {
            retfunction(ctx.get(1).getVal());
        }

    }


    private void retfunction(String value)
    {
        ArrayList<Token> p = new ArrayList<>();
        p.add(new Token("var", Token.TokenType.IDENTIFIER));
        p.add(new Token("retval", Token.TokenType.IDENTIFIER));
        p.add(new Token("=", Token.TokenType.ASSIGN));
        p.add(new Token(value, Token.TokenType.ASSIGN));
        p.add(new Token(";", Token.TokenType.NULL));
        VariableStatement retfunc = new VariableStatement(p);
        retfunc.eval();
    }
}
