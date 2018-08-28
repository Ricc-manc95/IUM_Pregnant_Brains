// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.view.View;

// Referenced classes of package com.google.android.calendar:
//            SearchActivity

final class arg._cls1
    implements android.view.wListener
{

    private final View arg$1;

    public final boolean onPreDraw()
    {
        return SearchActivity.lambda$onCreateOptionsMenu$2$SearchActivity(arg$1);
    }

    (View view)
    {
        arg$1 = view;
    }
}
