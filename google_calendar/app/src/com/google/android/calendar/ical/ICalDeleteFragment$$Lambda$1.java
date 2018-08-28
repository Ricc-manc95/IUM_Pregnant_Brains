// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalDeleteFragment

final class arg._cls1
    implements android.content.er
{

    private final ICalDeleteFragment arg$1;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface = arg$1;
        if (((Fragment) (dialoginterface)).mHost == null)
        {
            dialoginterface = null;
        } else
        {
            dialoginterface = (FragmentActivity)((Fragment) (dialoginterface)).mHost.mActivity;
        }
        dialoginterface.finish();
    }

    (ICalDeleteFragment icaldeletefragment)
    {
        arg$1 = icaldeletefragment;
    }
}
