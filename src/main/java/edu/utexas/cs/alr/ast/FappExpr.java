package edu.utexas.cs.alr.ast;

import java.util.List;
import java.util.Objects;

public class FappExpr extends Expr {
    private final long id;

    private final List<Expr> exprs;

    public List<Expr> getExprs() {
        return this.exprs;
    }

    public long getId() {
        return id;
    }

    public FappExpr(long id, List<Expr> exprs) {
        if (id <= 0)
            throw new IllegalArgumentException("id must be a positive number");
        this.id = id;
        exprs.forEach(e -> {
            if (!Objects.nonNull(e)) throw new IllegalArgumentException("arguments cannot be null");
        });
        this.exprs = exprs;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FappExpr fExpr = (FappExpr) o;
        if (this.id != fExpr.id) return false;
        return exprs.equals(fExpr.exprs);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, exprs);
    }

    protected void prettyPrint(StringBuilder b, String indent)
    {
        b.append("f");
        b.append(id);
        b.append("(");
        if (exprs.size() > 0) {
            b.append(exprs.get(0));
        }
        if (exprs.size() > 1) {
            for (int i = 1; i < exprs.size(); i++) {
                b.append(", ");
                exprs.get(i).prettyPrint(b, "");
            }
        }
        b.append(")");
    }

    @Override
    public ExprKind getKind() {
        return ExprKind.FAPP;
    }

}
