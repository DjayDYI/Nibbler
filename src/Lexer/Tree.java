package Lexer;

import Lexer.Token;
import Statement.SymbolTable.TokenTable;

import java.util.ArrayList;

public class Tree implements TokenTable {
    class Node{
        Node Left;
        Node Right;
        String Key;

        Node(String key){
            Key = key;
        }
    }
    Node root;

    public Tree(ArrayList<Token> list){
        if(list.size()>2) {
            Token tkn = list.get(0);
            list.remove(0);
            list.add(1, tkn);
        }
        String str = "";
        for(Token t:list)
        {
            str += t.getVal()+" ";
        }
        init(str);
    }
    public Tree(String content) {
        init(content);
    }

    private void init(String content){
        for (String ctx : content.split(" ")) {
            if (root == null) {
                root = new Node(ctx);
            } else if (root.Left == null) {
                root.Left = new Node(ctx);
            } else if (root.Right == null) {
                root.Right = new Node(ctx);
            } else {
                Node n = new Node(ctx);
                n.Left = root;
                root = n;
            }
        }
    }

    String eval(Node root) {
        if (root != null) {
            String l = eval(root.Right);
            String r = eval(root.Left);

            if (root.Key.equals(">"))
                if (Integer.parseInt(r) > Integer.parseInt(l)) return "true";
                else return "false";
            if (root.Key.equals(">="))
                if (Integer.parseInt(r) >= Integer.parseInt(l)) return "true";
                else return "false";
            if (root.Key.equals("<"))
                if (Integer.parseInt(r) < Integer.parseInt(l)) return "true";
                else return "false";
            if (root.Key.equals("<="))
                if (Integer.parseInt(r) <= Integer.parseInt(l)) return "true";
                else return "false";
            if (root.Key.equals("=="))
                if (Integer.parseInt(r) == Integer.parseInt(l)) return "true";
                else return "false";
            if (root.Key.equals("!="))
                if (Integer.parseInt(r) != Integer.parseInt(l)) return "true";
                else return "false";
            if (root.Key.equals("||"))
                if (r.equals("true") || l.equals("true")) return "true";
                else return "false";
            if (root.Key.equals("&&"))
                if (r.equals("true") && l.equals("true")) return "true";
                else return "false";

            if (root.Key.equals("+"))
                return String.valueOf(Float.parseFloat(l) + Float.parseFloat(r));
            if (root.Key.equals("-"))
                return String.valueOf(Float.parseFloat(l) - Float.parseFloat(r));
            if (root.Key.equals("*"))
                return String.valueOf(Float.parseFloat(l) * Float.parseFloat(r));
            if (root.Key.equals("/"))
                return String.valueOf(Float.parseFloat(l) / Float.parseFloat(r));
            if (root.Key.equals("%"))
                return String.valueOf(Float.parseFloat(l) % Float.parseFloat(r));
            if (root.Key.equals("^"))
                return String.valueOf(Integer.parseInt(l) ^ Integer.parseInt(r));
            if (root.Key.equals("&"))
                return String.valueOf(Integer.parseInt(l) & Integer.parseInt(r));
            if (root.Key.equals("|"))
                return String.valueOf(Integer.parseInt(l) | Integer.parseInt(r));

            return root.Key;
        }
        return null;
    }

    public String parse() {
        return eval(this.root);
    }
}
