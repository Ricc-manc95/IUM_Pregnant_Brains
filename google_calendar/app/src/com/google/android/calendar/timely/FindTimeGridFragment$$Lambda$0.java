// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.calendar.sharedprefs.SharedPrefs;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridFragment

final class arg._cls1
    implements com.google.android.calendar.utils.viewpager.ageChangeListener
{

    private final FindTimeGridFragment arg$1;

    public final void onPageSelected(int i, boolean flag)
    {
        Object obj = arg$1;
        ((FindTimeGridFragment) (obj)).updateSelectedSuggestion(i);
        ((FindTimeGridFragment) (obj)).updateGrid(flag);
        if (((FindTimeGridFragment) (obj)).listener != null)
        {
            ((FindTimeGridFragment) (obj)).listener.uggestionSwiped(((FindTimeGridFragment) (obj)).currentSuggestion, i);
        }
        if (flag)
        {
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            SharedPrefs.setSharedPreference(((android.content.Context) (obj)), "com.google.android.calendar.findtime.grid.was_slab_swiped", true);
        }
    }

    rAwareOnPageChangeListener(FindTimeGridFragment findtimegridfragment)
    {
        arg$1 = findtimegridfragment;
    }
}
