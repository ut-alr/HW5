package edu.utexas.cs.alr.util;

import edu.utexas.cs.alr.ast.*;
import edu.utexas.cs.alr.parser.ExprBaseListener;
import edu.utexas.cs.alr.parser.ExprLexer;
import edu.utexas.cs.alr.parser.ExprParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static edu.utexas.cs.alr.ast.ExprFactory.*;

public class ExprUtils
{
    public static void validate(Set<Expr> exprs) {
        Map<Long, Integer> func2arg = new HashMap<>();
        for (Expr e : exprs) {
            check(e, func2arg);
        }
    }

    private static void check(Expr e, Map<Long, Integer> func2argcnt) {
        if (e instanceof EqExpr) {
            Expr left = ((EqExpr) e).getLeft();
            Expr right = ((EqExpr) e).getRight();
            if (left instanceof FappExpr) {
                check(left, func2argcnt);
            }
            if (right instanceof FappExpr) {
                check(right, func2argcnt);
            }
        } else if (e instanceof NeqExpr) {
            Expr left = ((NeqExpr) e).getLeft();
            Expr right = ((NeqExpr) e).getRight();
            if (left instanceof FappExpr) {
                check(left, func2argcnt);
            }
            if (right instanceof FappExpr) {
                check(right, func2argcnt);
            }
        } else if (e instanceof FappExpr) {
            FappExpr fExpr = (FappExpr) e;
            Integer argCnt = func2argcnt.get(fExpr.getId());
            if (argCnt != null && argCnt != fExpr.getExprs().size()) {
                throw new IllegalStateException("Function argument mismatch: "
                        + "f"+fExpr.getId() + " " + argCnt
                        + " != " + fExpr.getExprs().size());
            }
            func2argcnt.put(fExpr.getId(), fExpr.getExprs().size());
            for (Expr arg : fExpr.getExprs()) {
                check(arg, func2argcnt);
            }
        }
    }


    public static boolean checkSAT(Set<Expr> exprs) {
        throw new UnsupportedOperationException("implement this");

    }

    public static boolean checkSATInjective(Set<Expr> exprs) {
        throw new UnsupportedOperationException("implement this");
    }

    public static Set<Expr> parseFrom(InputStream inStream) throws IOException
    {
        ExprLexer lexer = new ExprLexer(CharStreams.fromStream(inStream));
        BufferedTokenStream tokenStream = new BufferedTokenStream(lexer);
        ExprParser parser = new ExprParser(tokenStream);

        parser.addErrorListener(ThrowingErrorListener.INSTANCE);
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        ExprParser.FormulaContext parseTree = parser.formula();
        ASTListener astListener = new ASTListener();
        ParseTreeWalker.DEFAULT.walk(astListener, parseTree);
        validate(astListener.conjuncts);
        return astListener.conjuncts;
    }

}

class ASTListener extends ExprBaseListener
{
    Stack<Expr> pendingExpr = new Stack<>();
    Set<Expr> conjuncts = new HashSet<>();

    @Override
    public void exitObj(ExprParser.ObjContext ctx) {
        if (ctx.FUNC() != null) {
            ArgExpr args = mkArgs(new ArrayList<>());
            Expr arg = pendingExpr.pop();
            // check if it is a single argument
            if (!(arg instanceof ArgExpr)) {
                args.getExprs().add(arg);
            } else {
                args = (ArgExpr) arg;
            }
            long funcId = Long.parseLong(ctx.FUNC().toString().substring(1));
            FappExpr fapp = mkFAPP(funcId, args.getExprs());
            pendingExpr.push(fapp);
        } else if (ctx.VAR() != null) {
            long id = Long.parseLong(ctx.VAR().toString().substring(1));
            VarExpr var = mkVAR(id);
            pendingExpr.push(var);
        }
    }

    @Override
    public void exitObjlist(ExprParser.ObjlistContext ctx) {
        int i = ctx.children.size()-1;
        List<Expr> exprs = new ArrayList<>();
        while(i > 0) {
            Expr popped = pendingExpr.pop();
            exprs.add(popped);
            i--;
        }
        Collections.reverse(exprs);
        if (exprs.size() > 0) {
            ArgExpr arg = mkArgs(exprs);
            pendingExpr.push(arg);
        }
    }

    @Override
    public void exitLequals(ExprParser.LequalsContext ctx) {
        Expr right = pendingExpr.pop(), left = pendingExpr.pop();
        EqExpr eqExpr = mkEq(left, right);

        conjuncts.add(eqExpr);
    }

    @Override
    public void exitLnequals(ExprParser.LnequalsContext ctx) {
        Expr right = pendingExpr.pop(), left = pendingExpr.pop();
        NeqExpr neqExpr = mkNeq(left, right);

        conjuncts.add(neqExpr);
    }
}

class ThrowingErrorListener extends BaseErrorListener
{

    public static final ThrowingErrorListener INSTANCE = new ThrowingErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
            throws ParseCancellationException
    {
        throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
    }
}



