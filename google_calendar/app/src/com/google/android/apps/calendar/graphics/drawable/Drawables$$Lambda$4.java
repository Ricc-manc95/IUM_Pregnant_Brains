// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.android.apps.calendar.graphics.Layoutable;

public final class arg._cls5
    implements Layoutable
{

    private final Drawable arg$1;
    private final int arg$2;
    private final int arg$3;
    private final int arg$4;
    private final int arg$5;

    public final void layout(int i, int j, int k, int l)
    {
        arg$1.setBounds(arg$2 + i, arg$3 + j, k - arg$4, l - arg$5);
    }

    public final void layout(Rect rect)
    {
        layout(rect.left, rect.top, rect.right, rect.bottom);
    }

    public (Drawable drawable, int i, int j, int k, int l)
    {
        arg$1 = drawable;
        arg$2 = i;
        arg$3 = j;
        arg$4 = k;
        arg$5 = l;
    }
}
