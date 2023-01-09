package Lexer;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.init(args[0]);
    }
    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
    public void init(String name) throws Exception {
        //Lexer l = new Lexer("var x = 5;if(x==5){print y; print z;if(2<4){print x;print v;if(1==1){print t;};};}else{print a;print c;if(2<4){print d;print f;};};");
        //Lexer l = new Lexer("var x = 1;while(x<3){  if(x<5){print a; x = x + 1;print x;}else{print 2;}; };");
        //Lexer l = new Lexer("var x = 3;for(i = x, i < 7 ,i = i + 1){ if(x<i){print a;}else{print 2;}; };");
        //Lexer l = new Lexer("var x = 2;switch(x){case 1:print 1;case 2:print 2;case 3:print 3;};");
        //Lexer l = new Lexer("var x = 2; var y = x+3; y = 3;");
        //Lexer l = new Lexer("func carre(x){var d = x * x ; return d;}; func rec(x){if(x==0){return 0;}else{return rec(0);};}; var e = rec(2);");
        //Lexer l = new Lexer("var x[] = {1,2,3}; var c = x[1]; x[1] = 5;");
        //Lexer l = new Lexer("var x = true; if(x){print a;};");
        //Lexer l = new Lexer("var x = asdf; print x;");
        //Lexer l = new Lexer("var x = 5.5; x = x+1.2;print x; x = 2; x = x+2;print x;");
        String text = readFileAsString(name);
        Lexer l = new Lexer(text);
        TokenStreamer s = new TokenStreamer(l);
        Parser p = new Parser(s);
    }


}