// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.view.View;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveFrequencySelectionView

final class arg._cls3
    implements android.view.lectionView..Lambda._cls0
{

    private final GrooveFrequencySelectionView arg$1;
    private final int arg$2;
    private final int arg$3;

    public final void onClick(View view)
    {
        view = arg$1;
        int i = arg$2;
        int j = arg$3;
        if (view.getContext() instanceof )
        {
            (()view.getContext()).nFrequencySelectionComplete(i, j);
        }
    }

    (GrooveFrequencySelectionView groovefrequencyselectionview, int i, int j)
    {
        arg$1 = groovefrequencyselectionview;
        arg$2 = i;
        arg$3 = j;
    }
}
