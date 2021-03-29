package edu.utexas.cs.alr.ast;

import java.util.List;
import java.util.ArrayList;

public class ArgExpr extends Expr {

    private final List<Expr> exprs;

    public ArgExpr(List<Expr> exprs) {
        this.exprs = new ArrayList<>();
        for (Expr e : exprs) {
            if (e instanceof ArgExpr) {
                this.exprs.addAll(((ArgExpr) e).exprs);
            } else {
                this.exprs.add(e);
            }
        }
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
