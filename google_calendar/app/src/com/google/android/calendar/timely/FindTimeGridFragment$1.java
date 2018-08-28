// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridFragment

final class this._cls0
    implements android.view.indTimeGridFragment._cls1
{

    private final FindTimeGridFragment this$0;

    public final void onClick(View view)
    {
        if (listener != null)
        {
            listener.onGridCancelled();
        }
    }

    ()
    {
        this$0 = FindTimeGridFragment.this;
        super();
    }
}
