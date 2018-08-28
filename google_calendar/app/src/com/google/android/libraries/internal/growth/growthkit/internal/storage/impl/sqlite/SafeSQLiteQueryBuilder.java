// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import java.util.ArrayList;

public final class SafeSQLiteQueryBuilder
{

    public final ArrayList args = new ArrayList();
    public final StringBuilder builder = new StringBuilder();

    public SafeSQLiteQueryBuilder()
    {
    }

    public final SafeSQLStatement build()
    {
        return new SafeSQLStatement(builder.toString(), args.toArray(new Object[args.size()]));
    }

    private class SafeSQLStatement
    {

        public final Object args[];
        public final String query;

        SafeSQLStatement(String s, Object aobj[])
        {
            query = s;
            args = aobj;
        }
    }

}
