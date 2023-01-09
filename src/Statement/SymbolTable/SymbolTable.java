package Statement.SymbolTable;

import Lexer.Token;
import Lexer.TokenStreamer;
import Statement.FunctionStatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private Map<String,Info> table = new HashMap<>();
    private Map<String,FunctionStatement> function = new HashMap<>();


    public void addVariable(String name, ArrayList<Token> value)
    {
        TokenStreamer S = new TokenStreamer(value);
        ArrayList<Token> val= S.getUntilWihoutSeparator(";");
        val.remove(0);val.remove(0);
        table.put(name, new Info(val));
    }

    public void addVariable(Token name, Token value)
    {
        table.put(name.getVal(), new Info(value.getVal()));
    }

    public void addVariable(Token name, String value)
    {
        table.put(name.getVal(), new Info(value));
    }

    public void addVariable(String name, String res)
    {
        table.put(name, new Info(res));
    }

    public void reassign(Token name, Token value, int idx)
    {
        String n=name.getVal().substring(0, name.getVal().indexOf("["));
        table.get(n).updateValue(value.getVal(), idx);
    }

    public void reassign(Token name, Token value)
    {
        table.get(name.getVal()).updateValue(value.getVal());
    }
    public void reassign(String name, String val) {
        table.get(name).updateValue(val);
    }
    public void reassign(Token name, String value)
    {
        table.get(name.getVal()).updateValue(value);
    }

    public boolean contains(Token t)
    {
        return table.containsKey(t.getVal());
    }
    public String getValue(String name, String index)
    {
        return table.get(name).getValue(Integer.parseInt(index));
    }
    public String getValue(Token t)
    {
        return table.get(t.getVal()).getValue();
    }

    public void addFunction(Token name, FunctionStatement functionStatement)
    {
        function.put(name.getVal(),functionStatement);
    }
    public boolean containsFunction(Token name)
    {
        return function.containsKey(name.getVal());
    }

    public FunctionStatement getFunction(Token name)
    {
        return function.get(name.getVal());
    }



}
