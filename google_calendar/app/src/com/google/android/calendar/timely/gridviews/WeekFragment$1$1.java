// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListPopupWindow;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            WeekFragment

final class val.popup
    implements android.widget.mClickListener
{

    private final val.popup this$1;
    private final ListPopupWindow val$popup;

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        adapterview = _fld0;
        if (((Fragment) (adapterview)).mHost == null)
        {
            adapterview = null;
        } else
        {
            adapterview = (FragmentActivity)((Fragment) (adapterview)).mHost.mActivity;
        }
        adapterview.onOptionsItemSelected((MenuItem)overflowAdapter.getItem(i));
        val$popup.dismiss();
    }

    ListAdapter()
    {
        this$1 = final_listadapter;
        val$popup = ListPopupWindow.this;
        super();
    }
}
