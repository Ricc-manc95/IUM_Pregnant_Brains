// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import com.google.android.apps.calendar.util.function.IntUnaryOperator;

public final class arg._cls2
    implements IntUnaryOperator
{

    private final int arg$1;
    private final int arg$2;

    public final int apply(int i)
    {
        return arg$1 + i + arg$2;
    }

    public (int i, int j)
    {
        arg$1 = i;
        arg$2 = j;
    }
}
