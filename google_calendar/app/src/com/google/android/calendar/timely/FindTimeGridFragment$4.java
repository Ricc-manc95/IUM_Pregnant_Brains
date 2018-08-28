// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.view.View;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridFragment

final class this._cls0
    implements Runnable
{

    private final FindTimeGridFragment this$0;

    public final void run()
    {
        FindTimeGridFragment findtimegridfragment = FindTimeGridFragment.this;
        boolean flag;
        if (((Fragment) (findtimegridfragment)).mHost != null && ((Fragment) (findtimegridfragment)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return;
        } else
        {
            mView.announceForAccessibility(requireContext().getResources().getString(0x7f130470));
            return;
        }
    }

    ()
    {
        this$0 = FindTimeGridFragment.this;
        super();
    }
}
