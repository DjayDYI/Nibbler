package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;
import Lexer.Tree;

import java.util.ArrayList;


public class AssignationStatement extends Statement{

    ArrayList<Token> ctx;

    public AssignationStatement(ArrayList<Token>ctx) {
        this.ctx = ctx;
    }

    public void functionCall(String name,int pos){
        TokenStreamer s = new TokenStreamer(ctx);
        s.nextUntil();
        table.getFunction(ctx.get(pos)).overloadParam(getParam(s));
        String res = table.getFunction(ctx.get(pos)).JustInTimeEval();
        table.addVariable(name, res);
    }

    public String getResult(Token left, Token right, Token op){
        ArrayList<Token> eval = new ArrayList<>();
        eval.add(left);
        eval.add(op);
        eval.add(right);

        Tree ast = new Tree(eval);
        String res = ast.parse();
        return res;
    }
    ArrayList<Token> getTokens(String left, String right, Token oper) {
        ArrayList<Token> ret = new ArrayList<>();
        Token l = new Token(left, Token.TokenType.IDENTIFIER);
        Token r = new Token(right, Token.TokenType.IDENTIFIER);
        ret.add(l);
        ret.add(oper);
        ret.add(r);
        return ret;
    }
    public void expression(String name) {
        Token left = getOperand(ctx.get(2));
        Token right = getOperand(ctx.get(4));
        String res = getResult(left, right, ctx.get(3));
        table.addVariable(name, res);
    }
    public Token getOperand(Token c){
        if(table.contains(c)){
            return new Token(table.getValue(c), Token.TokenType.IDENTIFIER);
        }else{
            return new Token(c.getVal(), Token.TokenType.IDENTIFIER);

        }
    }


    @Override
    public void eval() {

    }
}
