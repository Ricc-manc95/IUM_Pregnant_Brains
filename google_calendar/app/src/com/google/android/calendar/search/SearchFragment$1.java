// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.widget.RecyclerView;
import com.google.android.calendar.Utils;

// Referenced classes of package com.google.android.calendar.search:
//            SearchFragment

final class this._cls0
    implements Runnable
{

    private final SearchFragment this$0;

    public final void run()
    {
        Object obj = SearchFragment.this;
        if (((Fragment) (obj)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        Utils.getTimeZoneId(((android.content.Context) (obj)), homeTimeUpdater);
        if (recyclerView != null)
        {
            recyclerView.mAdapter.mObservable.notifyChanged();
        }
    }

    servable()
    {
        this$0 = SearchFragment.this;
        super();
    }
}
