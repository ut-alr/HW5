package edu.utexas.cs.alr.ast;

import java.util.Objects;

public class EqExpr extends Expr {
    Expr leftExpr;
    Expr rightExpr;

    EqExpr(Expr left, Expr right)
    {
        if (!Objects.nonNull(left))
            throw new IllegalArgumentException("Expr left cannot be null");
        if (!Objects.nonNull(right))
            throw new IllegalArgumentException("Expr right cannot be null");

        this.leftExpr = left;
        this.rightExpr = right;
    }

    public Expr getLeft()
    {
        return leftExpr;
    }

    public Expr getRight()
    {
        return rightExpr;
    }

    @Override
    public ExprKind getKind()
    {
        return ExprKind.EQ;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqExpr eqExpr = (EqExpr) o;
        return (leftExpr.equals(eqExpr.leftExpr) && rightExpr.equals(eqExpr.rightExpr));
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(leftExpr, rightExpr);
    }

    protected void prettyPrint(StringBuilder b, String indent)
    {
        b.append("(");
        leftExpr.prettyPrint(b, "");
        b.append(" = ");
        rightExpr.prettyPrint(b, "");
        b.append(")");
    }
}
