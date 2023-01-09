package Statement;

import Lexer.Token;
import Lexer.TokenStreamer;
import Lexer.Tree;
import Statement.Statement;

import java.util.ArrayList;

public class ForStatement extends Statement {

    private Condition condition;
    private ArrayList<Statement> instrue= new ArrayList<>();
    public ForStatement(ArrayList<Token> ctx) {
        ctx.remove(0);
        TokenStreamer s = new TokenStreamer(ctx);
        condition = getCond(s);
        while(s.hasNext())
        {
            s.getNext();
            if(s.hasNext())
                instrue.add(Statement.create(s.getUntilLevel(";")));
        }
    }

    private ArrayList<Token> getVariableInitial()
    {
        ArrayList<Token> ret = new ArrayList<>();
        ret.add(new Token("var", Token.TokenType.KEYWORD));
        ret.add(new Token("i", Token.TokenType.KEYWORD));
        ret.add(new Token("=", Token.TokenType.ASSIGN));
        ret.add(new Token(condition.getInit(), Token.TokenType.ASSIGN));
        return ret;
    }

    private ArrayList<Token> getReassign()
    {
        ArrayList<Token> ret = new ArrayList<>();
        ret.add(new Token("i", Token.TokenType.KEYWORD));
        ret.add(new Token("=", Token.TokenType.ASSIGN));
        ret.add(new Token("i", Token.TokenType.KEYWORD));
        ret.add(new Token("+", Token.TokenType.INT_OPERATOR));
        ret.add(new Token(condition.getIncrement(), Token.TokenType.KEYWORD));
        return ret;
    }

    private ArrayList<Token> getCondition()
    {
        ArrayList<Token> ret = new ArrayList<>();
        ret.add(new Token(condition.getCondLower(), Token.TokenType.KEYWORD));
        ret.add(new Token(condition.getOperator(), Token.TokenType.KEYWORD));
        ret.add(new Token(condition.getCondUpper(), Token.TokenType.KEYWORD));
        return ret;
    }

    @Override
    public void eval() {
        VariableStatement statement = new VariableStatement(getVariableInitial());
        statement.eval();
        forLoop();

    }

    public void forLoop()
    {
        Tree ast = new Tree(getCondition());
        if(ast.parse().equals("true")){
            for(Statement s:instrue)
            {
                s.eval();
            }
            ReassignStatement reassignStatement = new ReassignStatement(getReassign());
            reassignStatement.eval();
            forLoop();
        }
    }
}
