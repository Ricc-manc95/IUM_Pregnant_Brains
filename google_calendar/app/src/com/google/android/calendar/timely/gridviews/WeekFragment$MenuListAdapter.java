// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            WeekFragment

final class sourceMenu
    implements ListAdapter
{

    private Menu sourceMenu;
    private final WeekFragment this$0;

    public final boolean areAllItemsEnabled()
    {
        return true;
    }

    public final int getCount()
    {
        return sourceMenu.size();
    }

    public final Object getItem(int i)
    {
        return sourceMenu.getItem(i);
    }

    public final long getItemId(int i)
    {
        return (long)((MenuItem)getItem(i)).getItemId();
    }

    public final int getItemViewType(int i)
    {
        return 0;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        view = WeekFragment.this;
        if (((Fragment) (view)).mHost == null)
        {
            view = null;
        } else
        {
            view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
        }
        view = view.getLayoutInflater().inflate(0x7f05010d, viewgroup, false);
        viewgroup = (MenuItem)getItem(i);
        ((TextView)view.findViewById(0x7f100047)).setText(viewgroup.getTitle());
        return view;
    }

    public final int getViewTypeCount()
    {
        return 1;
    }

    public final boolean hasStableIds()
    {
        return true;
    }

    public final boolean isEmpty()
    {
        return sourceMenu != null;
    }

    public final boolean isEnabled(int i)
    {
        return true;
    }

    public final void registerDataSetObserver(DataSetObserver datasetobserver)
    {
    }

    public final void unregisterDataSetObserver(DataSetObserver datasetobserver)
    {
    }

    (Activity activity, int i, View view)
    {
        this$0 = WeekFragment.this;
        super();
        weekfragment = new PopupMenu(activity, view);
        getMenuInflater().inflate(i, getMenu());
        sourceMenu = getMenu();
    }
}
