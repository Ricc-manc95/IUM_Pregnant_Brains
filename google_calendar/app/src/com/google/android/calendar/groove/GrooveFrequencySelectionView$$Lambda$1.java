// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.view.View;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveFrequencySelectionView

final class arg._cls1
    implements android.view.lectionView..Lambda._cls1
{

    private final GrooveFrequencySelectionView arg$1;

    public final void onClick(View view)
    {
        view = arg$1;
        if (view.getContext() instanceof )
        {
            (()view.getContext()).nFrequencyMoreOptionsClicked();
        }
    }

    (GrooveFrequencySelectionView groovefrequencyselectionview)
    {
        arg$1 = groovefrequencyselectionview;
    }
}
