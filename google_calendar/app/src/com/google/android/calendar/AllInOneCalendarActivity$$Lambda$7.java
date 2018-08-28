// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.support.v4.widget.DrawerLayout;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

final class arg._cls1
    implements Runnable
{

    private final AllInOneCalendarActivity arg$1;

    public final void run()
    {
        AllInOneCalendarActivity allinonecalendaractivity = arg$1;
        DrawerLayout drawerlayout = allinonecalendaractivity.drawerLayout;
        android.view.View view = drawerlayout.findDrawerWithGravity(3);
        boolean flag;
        if (view != null)
        {
            flag = drawerlayout.isDrawerOpen(view);
        } else
        {
            flag = false;
        }
        if (flag)
        {
            allinonecalendaractivity.drawerLayout.closeDrawer(3);
            return;
        } else
        {
            allinonecalendaractivity.drawerLayout.openDrawer(3);
            return;
        }
    }

    (AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
