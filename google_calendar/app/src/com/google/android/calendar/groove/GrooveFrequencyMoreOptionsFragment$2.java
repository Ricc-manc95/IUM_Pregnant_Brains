// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveFrequencyMoreOptionsFragment

final class this._cls0
    implements android.view.MoreOptionsFragment._cls2
{

    private final GrooveFrequencyMoreOptionsFragment this$0;

    public final void onClick(View view)
    {
        mFragmentManager.popBackStack();
    }

    ()
    {
        this$0 = GrooveFrequencyMoreOptionsFragment.this;
        super();
    }
}