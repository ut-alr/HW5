package edu.utexas.cs.alr.ast;

import java.util.Objects;

public class VarExpr extends Expr
{
    private final long id;

    VarExpr(long id)
    {
        if (id <= 0)
            throw new IllegalArgumentException("id must be a positive number");
        this.id = id;
    }

    public long getId()
    {
        return id;
    }

    @Override
    public ExprKind getKind()
    {
        return ExprKind.VAR;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarExpr varExpr = (VarExpr) o;
        return id == varExpr.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    protected void prettyPrint(StringBuilder b, String indent)
    {
        b.append(indent + "x").append(id);
    }
}
