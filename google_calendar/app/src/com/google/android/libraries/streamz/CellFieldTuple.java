// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;

import java.util.Arrays;

public final class CellFieldTuple
{

    private static final Object NO_FIELDS[];
    public static final CellFieldTuple NO_FIELDS_TUPLE;
    public final Object fieldValues[];
    private final int hashCode;

    public transient CellFieldTuple(Object aobj[])
    {
        fieldValues = aobj;
        hashCode = Arrays.hashCode(aobj);
    }

    public final boolean equals(Object obj)
    {
        return (obj instanceof CellFieldTuple) && hashCode == ((CellFieldTuple)obj).hashCode && Arrays.equals(fieldValues, ((CellFieldTuple)obj).fieldValues);
    }

    public final int hashCode()
    {
        return hashCode;
    }

    public final String toString()
    {
        return Arrays.toString(fieldValues);
    }

    static 
    {
        NO_FIELDS = new Object[0];
        NO_FIELDS_TUPLE = new CellFieldTuple(NO_FIELDS);
    }
}
