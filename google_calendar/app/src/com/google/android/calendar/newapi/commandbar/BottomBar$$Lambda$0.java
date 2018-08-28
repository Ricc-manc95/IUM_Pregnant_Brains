// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.view.View;

public final class arg._cls1
    implements android.view.istener
{

    private final anged arg$1;

    public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        view = arg$1;
        if (l - j != l1 - j1)
        {
            view.onHeightChanged();
        }
    }

    public anged(anged anged)
    {
        arg$1 = anged;
    }
}
