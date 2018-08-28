// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import android.view.MenuItem;

// Referenced classes of package com.google.android.calendar.newapi.overflow:
//            OverflowMenuController

public final class ReminderOverflowMenuController extends OverflowMenuController
{

    public ReminderOverflowMenuController(Callback callback)
    {
        super(callback);
    }

    public final int getMenuResourceId()
    {
        return 0x7f15000a;
    }

    protected final void onMenuItemClicked(MenuItem menuitem, Object obj)
    {
        ((Callback)obj).onDeleteClicked();
    }

    protected final volatile void onModelChanged(OverflowMenuCompat.OverflowMenu overflowmenu, Object obj)
    {
    }

    private class Callback
    {

        public abstract void onDeleteClicked();
    }

}
