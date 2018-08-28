// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.widget.Toast;
import com.google.android.calendar.search.SearchResultsAdapter;
import com.google.common.util.concurrent.FutureCallback;
import java.util.List;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventListController, ICalEventListFragment

final class val.eventAdapter
    implements FutureCallback
{

    private final ICalEventListFragment this$0;
    private final SearchResultsAdapter val$eventAdapter;

    public final void onFailure(Throwable throwable)
    {
        Object obj = null;
        throwable = ICalEventListFragment.this;
        boolean flag;
        if (((Fragment) (throwable)).mHost != null && ((Fragment) (throwable)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            throwable = ICalEventListFragment.this;
            if (((Fragment) (throwable)).mHost == null)
            {
                throwable = null;
            } else
            {
                throwable = (FragmentActivity)((Fragment) (throwable)).mHost.mActivity;
            }
            Toast.makeText(throwable, 0x7f1302f9, 1).show();
            throwable = ICalEventListFragment.this;
            if (((Fragment) (throwable)).mHost == null)
            {
                throwable = obj;
            } else
            {
                throwable = (FragmentActivity)((Fragment) (throwable)).mHost.mActivity;
            }
            throwable.finish();
        }
    }

    public final void onSuccess(Object obj)
    {
        List list = (List)obj;
        ICalEventListFragment icaleventlistfragment = ICalEventListFragment.this;
        obj = ICalEventListFragment.this;
        if (((Fragment) (obj)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        icaleventlistfragment.controller = new ICalEventListController(((android.content.Context) (obj)), list, val$eventAdapter, eventClient, new <init>(ICalEventListFragment.this), new <init>(ICalEventListFragment.this));
    }

    ()
    {
        this$0 = final_icaleventlistfragment;
        val$eventAdapter = SearchResultsAdapter.this;
        super();
    }
}
