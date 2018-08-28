// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.content.Context;
import android.view.ViewGroup;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            BottomBar

public abstract class BottomBarController
    implements BottomBar.OnCommandBarActionClickListener
{

    public Object callback;
    public BottomBar commandBar;
    public Object model;

    public BottomBarController(Object obj)
    {
        callback = obj;
    }

    public abstract int getActionsLayout();

    public abstract int[] getPrimaryActionIds();

    public abstract BottomBar inflateCommandBar(Context context, ViewGroup viewgroup);

    public final void onCommandBarActionClick(int i)
    {
        if (callback != null)
        {
            onCommandBarActionClick(callback, i);
        }
    }

    public abstract void onCommandBarActionClick(Object obj, int i);

    public abstract void onModelChanged(Object obj);

    public abstract void setupCommandBar(BottomBar bottombar);
}
