package Statement.SymbolTable;

import Lexer.Token;

import java.util.ArrayList;

public class Info {

    private ArrayList<String> value = new ArrayList<>();
    public Info(ArrayList<Token> val) {
        for(Token t:val)
            value.add(t.getVal());
    }

    public Info(Token val) {
        value.add(val.getVal());
    }

    public Info(String val) {
        value.add(val);
    }

    public void updateValue(String val) {
        value.clear();
        value.add(val);
    }

    public void updateValue(String val, int idx) {
        value.remove(idx);
        value.add(idx,val);
    }

    public String getValue() {
        return value.get(0);
    }

    public String getValue(int index) {
        return value.get(index);
    }
}
