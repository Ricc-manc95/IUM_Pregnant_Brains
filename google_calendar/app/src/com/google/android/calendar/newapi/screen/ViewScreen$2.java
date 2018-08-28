// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.res.Resources;
import android.view.View;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreen

final class this._cls0
    implements android.view.ChangeListener
{

    private final ViewScreen this$0;

    public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1)
    {
        view = ViewScreen.this;
        i = l - j;
        View view1 = view.findViewById(0x7f1002ad);
        j = view.getResources().getDimensionPixelOffset(0x7f0e0266);
        k = view.getResources().getDimensionPixelOffset(0x7f0e0265);
        ((android.view.ginLayoutParams)view1.getLayoutParams()).topMargin = (int)((float)i - (float)(j + (k << 1)) / 2.0F);
        ((android.view.ginLayoutParams)view.findViewById(0x7f1002a8).getLayoutParams()).topMargin = i;
    }

    ()
    {
        this$0 = ViewScreen.this;
        super();
    }
}
