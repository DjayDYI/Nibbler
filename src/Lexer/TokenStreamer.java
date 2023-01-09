package Lexer;

import Lexer.Lexer;

import java.util.ArrayList;

public class TokenStreamer {

    ArrayList<Token> stream = new ArrayList<>();
    int i=0;
    public TokenStreamer(Lexer l)
    {
        stream = l.tkn;
    }
    public TokenStreamer(ArrayList<Token> l)
    {
        stream = l;
    }

    public boolean hasNext()
    {
        return i < stream.size()-1;
    }

    public Token get() {
        return stream.get(i);
    }

    public Token getNext() {
        return stream.get(i+1);
    }
    public Token next() {
        return stream.get(++i);
    }
    public Token getLast() {
        return stream.get(--i);
    }

    public void nextUntil() {
        while(!stream.get(i).getVal().equals("(")){i++;}
    }

    public ArrayList<Token> getUntil(String c)
    {
        ArrayList<Token> ret = new ArrayList<>();
        i++;
        while(!stream.get(i).getVal().equals(c))
        {
            ret.add(stream.get(i++));
        }
        i++;
        return ret;
    }

    public ArrayList<Token> getUntilWihoutSeparator(String c)
    {
        ArrayList<Token> ret = new ArrayList<>();
        int i = 0;
        while(!stream.get(i).getVal().equals(c))
        {
            if(!stream.get(i).getType().equals(Token.TokenType.SEPARATOR))
                ret.add(stream.get(i));
            i++;
        }
        return ret;
    }


    public ArrayList<Token> getUntilLevel(String t) {
        ArrayList<Token> ret = new ArrayList<>();
        int lvl = 0;
        while(!stream.get(i).getVal().equals(t) || lvl != 0){
            if(stream.get(i).getVal().equals("{")) lvl++;
            if(stream.get(i).getVal().equals("}")) lvl--;
            ret.add(stream.get(i++));
        }
        ret.add(stream.get(i++));
        return ret;
    }


    public ArrayList<Token> getUntilSameLevel(String t){
        ArrayList<Token> ret = new ArrayList<>();
        int lvl = 0;
        do {
            if(stream.get(i).getVal().equals("{")) lvl++;
            if(stream.get(i).getVal().equals("}")) lvl--;

            if(!stream.get(i).getType().equals(Token.TokenType.SCOPE))
                ret.add(stream.get(i));
            i++;
        } while( lvl != 0 || !stream.get(i).getVal().equals(t));
        ret.add(stream.get(i));
        return ret;
    }

    public static boolean hasWord(ArrayList<Token> list, String word)
    {
        return list.stream().anyMatch(o -> o.getVal().equals(word));
    }


    public boolean contains(String anElse) {
        int i = 0;
        int lvl=0;
        while(i<stream.size()-1)
        {
            if(stream.get(i).getVal().equals("{"))lvl++;
            if(stream.get(i).getVal().equals("}"))lvl--;
            if(stream.get(i++).getVal().equals(anElse) && lvl == 0){
                return true;
            }
        }
        return false;
    }
}
