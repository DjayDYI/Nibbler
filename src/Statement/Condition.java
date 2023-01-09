package Statement;

import Lexer.Token;
import Lexer.Tree;
import Statement.SymbolTable.TokenTable;

import java.util.ArrayList;

public class Condition implements TokenTable {
    ArrayList<Token> cond;
    Tree ast;
    Condition(ArrayList<Token> cond){
        this.cond = cond;
        this.ast = new Tree(cond);
    }
    public String eval(){
        ArrayList<Token> condeval = new ArrayList<>();

        for(Token t:this.cond)
        {
            if(table.contains(t))
            {
                condeval.add(new Token(table.getValue(t), Token.TokenType.IDENTIFIER));
            }
            else
            {
                condeval.add(t);
            }
        }

        if(condeval.size()>2) {
            Token swap = condeval.get(0);
            condeval.remove(0);
            condeval.add(1, swap);
        }
        this.ast = new Tree(condeval);
        return ast.parse();
    }

    String getInit(){
        if(table.contains(cond.get(2)))
            return table.getValue(cond.get(2));
        else
            return cond.get(2).getVal();
    }
    String getCondUpper(){
        if(table.contains(cond.get(6)))
            return table.getValue(cond.get(6));
        else
            return cond.get(6).getVal();
    }
    String getCondLower(){
        if(table.contains(cond.get(4)))
            return table.getValue(cond.get(4));
        else
            return cond.get(4).getVal();
    }

    String getOperator(){
        return cond.get(5).getVal();
    }

    String getIncrement(){
        if(table.contains(cond.get(12)))
            return table.getValue(cond.get(12));
        else
            return cond.get(12).getVal();
    }


}
