// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;
import android.widget.ListPopupWindow;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            WeekFragment

final class this._cls0
    implements android.view.tener
{

    public final WeekFragment this$0;

    public final void onClick(View view)
    {
        Object obj1 = null;
        Object obj = WeekFragment.this;
        final ListPopupWindow popup;
        WeekFragment weekfragment;
        if (((Fragment) (obj)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        popup = new ListPopupWindow(((Context) (obj)));
        popup.setAnchorView(view);
        popup.setModal(true);
        popup.setWidth((int)requireContext().getResources().getDimension(0x7f0e030c));
        weekfragment = WeekFragment.this;
        if (weekfragment.overflowAdapter == null)
        {
            class _cls1
                implements android.widget.AdapterView.OnItemClickListener
            {

                private final WeekFragment._cls1 this$1;
                private final ListPopupWindow val$popup;

                public final void onItemClick(AdapterView adapterview, View view1, int i, long l)
                {
                    adapterview = this$0;
                    if (((Fragment) (adapterview)).mHost == null)
                    {
                        adapterview = null;
                    } else
                    {
                        adapterview = (FragmentActivity)((Fragment) (adapterview)).mHost.mActivity;
                    }
                    adapterview.onOptionsItemSelected((MenuItem)overflowAdapter.getItem(i));
                    popup.dismiss();
                }

            _cls1()
            {
                this$1 = WeekFragment._cls1.this;
                popup = listpopupwindow;
                super();
            }
            }

            if (((Fragment) (weekfragment)).mHost == null)
            {
                obj = obj1;
            } else
            {
                obj = (FragmentActivity)((Fragment) (weekfragment)).mHost.mActivity;
            }
            weekfragment.overflowAdapter = new nuListAdapter(weekfragment, ((android.app.Activity) (obj)), 0x7f150009, weekfragment.overflowButton);
        }
        popup.setAdapter(overflowAdapter);
        popup.setOnItemClickListener(new _cls1());
        view.setOnTouchListener(popup.createDragToOpenListener(view));
        popup.show();
    }

    _cls1()
    {
        this$0 = WeekFragment.this;
        super();
    }
}
