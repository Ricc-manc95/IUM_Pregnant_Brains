// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.util;

import java.util.regex.Matcher;

final class word
    implements Comparable
{

    public final int end;
    public final int start;
    public final String word;

    public final int compareTo(Object obj)
    {
        obj = (word)obj;
        return Integer.compare(start, ((start) (obj)).start);
    }

    (Matcher matcher)
    {
        start = matcher.start();
        end = matcher.end();
        word = matcher.group();
    }
}
