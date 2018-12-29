import java.io.InputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class ScheduleRunner {
    public static void main( String[] args) throws Exception 
    {
    	//Reading the DSL script
        InputStream is = ClassLoader.getSystemResourceAsStream("resources/input.gr");
        
        //Loading the DSL script into the ANTLR stream.
        CharStream input = new ANTLRInputStream(is);
        
	    //ANTLRInputStream input = new ANTLRInputStream( System.in);
	
	    SchedulingLexer lexer = new SchedulingLexer(input);
	
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	
	    SchedulingParser parser = new SchedulingParser(tokens);
	    parser.addParseListener(new CustomSchedulingBaseListener());
	    //System.out.println(parser);
	    ParseTree tree = parser.command(); // begin parsing at rule 'r'
	    //System.out.println(tree.toStringTree(parser)); // print LISP-style tree
	    
    }
}
