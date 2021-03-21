package edu.utexas.cs.alr.ast;

public abstract class Expr
{
    public enum ExprKind
    {
        VAR,
        EQ,
        NEQ,
        FAPP,
    }

    protected abstract void prettyPrint(StringBuilder b, String indent);

    public abstract ExprKind getKind();

    @Override
    public String toString()
    {
        StringBuilder b = new StringBuilder();
        prettyPrint(b, "");
        return b.toString();
    }
}
