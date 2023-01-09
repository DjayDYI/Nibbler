package Statement;

import Lexer.Token;
import Statement.Statement;

import java.util.ArrayList;

public class PrintStatement extends Statement {

    Token print;
    public PrintStatement(ArrayList<Token> ctx) {
        print = ctx.get(1);
    }

    public void eval()
    {
        if(table.contains(print))
            System.out.println(table.getValue(print));
        else
            System.out.println(print.getVal());
    }
}
