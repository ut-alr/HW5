package edu.utexas.cs.alr.ast;
import java.util.Objects;

public class NeqExpr extends Expr {
    Expr leftExpr;
    Expr rightExpr;

    NeqExpr(Expr left, Expr right)
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeqExpr neqExpr = (NeqExpr) o;
        return (leftExpr.equals(neqExpr.leftExpr) && rightExpr.equals(neqExpr.rightExpr));
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
        b.append(" != ");
        rightExpr.prettyPrint(b, "");
        b.append(")");
    }

    @Override
    public ExprKind getKind() {
        return ExprKind.NEQ;
    }
}

