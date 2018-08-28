// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.Toolbar;

public class OverflowMenuImpl23 extends Toolbar
    implements android.widget.Toolbar.OnMenuItemClickListener, OverflowMenuCompat.OverflowMenu
{

    private OverflowMenuCompat.OnOverflowItemClickCallback callback;

    public OverflowMenuImpl23(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public final void init(int i)
    {
        inflateMenu(i);
        setOnMenuItemClickListener(this);
    }

    public boolean onMenuItemClick(MenuItem menuitem)
    {
        if (callback != null)
        {
            callback.onOverflowItemClicked(menuitem);
            return true;
        } else
        {
            return false;
        }
    }

    public final void setCallback(OverflowMenuCompat.OnOverflowItemClickCallback onoverflowitemclickcallback)
    {
        callback = onoverflowitemclickcallback;
    }
}
