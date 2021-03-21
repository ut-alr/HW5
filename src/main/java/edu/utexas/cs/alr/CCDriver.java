package edu.utexas.cs.alr;

import edu.utexas.cs.alr.ast.Expr;
import edu.utexas.cs.alr.util.ExprUtils;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.io.IOException;
import java.util.Set;

public class CCDriver
{
    public static void main(String[] args) throws Exception
    {
        try
        {
            Set<Expr> e = ExprUtils.parseFrom(System.in);
            System.out.println(ExprUtils.checkSAT(e) ? "SAT" : "UNSAT");
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        catch (ParseCancellationException ex)
        {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
