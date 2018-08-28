// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.provider;


public final class Selection
{

    public final int argsCount;
    public final String filterString;

    public Selection(String s, int i)
    {
        filterString = s;
        argsCount = i;
    }

    public static Builder nTimesOr(int i, Selection selection)
    {
        boolean flag1 = true;
        boolean flag;
        if (i > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        Builder builder = new Builder();
        builder.argsCount = selection.argsCount;
        builder.filterString.append("(");
        builder.filterString.append(selection.filterString);
        builder.filterString.append(")");
        for (int j = ((flag1) ? 1 : 0); j < i; j++)
        {
            builder.argsCount = builder.argsCount + selection.argsCount;
            builder.filterString.append(" OR (");
            builder.filterString.append(selection.filterString);
            builder.filterString.append(")");
        }

        return builder;
    }

    public static Builder.ColumnExpression where(String s)
    {
        Builder builder = new Builder();
        builder.filterString.append(s);
        class Builder.ColumnExpression
        {

            public final Builder this$0;

            public Builder.ColumnExpression()
            {
                this$0 = Builder.this;
                super();
            }
        }

        return builder. new Builder.ColumnExpression();
    }

    public static Builder where(Selection selection)
    {
        Builder builder = new Builder();
        builder.argsCount = selection.argsCount;
        builder.filterString.append("(");
        builder.filterString.append(selection.filterString);
        builder.filterString.append(")");
        return builder;
    }

    static 
    {
        new Selection(null, 0);
    }

    private class Builder
    {

        public int argsCount;
        public final StringBuilder filterString = new StringBuilder();

        Builder()
        {
            argsCount = 0;
        }
    }

}
