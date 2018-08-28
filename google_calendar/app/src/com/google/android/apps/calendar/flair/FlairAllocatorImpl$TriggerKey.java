// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.flair;

import java.util.Arrays;

final class wordsAsString
{

    private final int hashCode;
    private final String wordsAsString;

    public final boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof wordsAsString))
        {
            return false;
        } else
        {
            return wordsAsString.equals(((wordsAsString)obj).wordsAsString);
        }
    }

    public final int hashCode()
    {
        return hashCode;
    }

    public final String toString()
    {
        return wordsAsString;
    }

    public (StringBuilder stringbuilder)
    {
        wordsAsString = stringbuilder.toString();
        hashCode = wordsAsString.hashCode();
    }

    public wordsAsString(String as[])
    {
        wordsAsString = Arrays.toString(as);
        hashCode = wordsAsString.hashCode();
    }
}
