package edu.utexas.cs.alr.ast;

import java.util.List;

public class ArgExpr extends Expr {

    private final List<Expr> exprs;

    public ArgExpr(List<Expr> exprs) {
        this.exprs = exprs;
    }

    public List<Expr> getExprs() {
        return this.exprs;
    }

    @Override
    protected void prettyPrint(StringBuilder b, String indent) {
    }

    @Override
    public ExprKind getKind() {
        return null;
    }
}
