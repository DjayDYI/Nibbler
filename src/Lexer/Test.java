package Lexer;

import Lexer.Lexer;

public class Test {

    void test_if() {
        Lexer l = new Lexer("if(5==5){print y; print z;if(2<4){print x;print v;if(1==1){print t;};};}else{print a;print c;if(2<4){print d;print f;};};");
        TokenStreamer s = new TokenStreamer(l);
        Parser p = new Parser(s);
    }
}
