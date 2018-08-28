// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import android.content.Context;
import android.view.MenuItem;

public abstract class OverflowMenuController
    implements OverflowMenuCompat.OnOverflowItemClickCallback
{

    private Object callback;
    public Context context;
    public Object model;
    public OverflowMenuCompat.OverflowMenu overflowMenu;

    public OverflowMenuController(Object obj)
    {
        callback = obj;
    }

    public abstract int getMenuResourceId();

    protected void onMenuItemClicked(MenuItem menuitem, Object obj)
    {
    }

    public abstract void onModelChanged(OverflowMenuCompat.OverflowMenu overflowmenu, Object obj);

    public final void onOverflowItemClicked(MenuItem menuitem)
    {
        if (callback != null)
        {
            onMenuItemClicked(menuitem, callback);
        }
    }
}
