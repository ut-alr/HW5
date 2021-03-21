package edu.utexas.cs.alr.ast;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ExprFactory
{
    private static final ConcurrentHashMap<Expr, Expr> cache = new ConcurrentHashMap<>();

    public static VarExpr mkVAR(long id)
    {
        VarExpr v = new VarExpr(id);
        cache.putIfAbsent(v, v);
        return (VarExpr) cache.get(v);
    }

    public static FappExpr mkFAPP(long id, List<Expr> args)
    {
        FappExpr f = new FappExpr(id, args);
        cache.putIfAbsent(f, f);
        return (FappExpr) cache.get(f);
    }

    public static ArgExpr mkArgs(List<Expr> args)
    {
        ArgExpr arg = new ArgExpr(args);
        cache.putIfAbsent(arg, arg);
        return (ArgExpr) cache.get(arg);
    }

    public static EqExpr mkEq(Expr left, Expr right)
    {
        EqExpr eq = new EqExpr(left, right);
        cache.putIfAbsent(eq, eq);
        return (EqExpr) cache.get(eq);
    }

    public static NeqExpr mkNeq(Expr left, Expr right)
    {
        NeqExpr eq = new NeqExpr(left, right);
        cache.putIfAbsent(eq, eq);
        return (NeqExpr) cache.get(eq);
    }
}
