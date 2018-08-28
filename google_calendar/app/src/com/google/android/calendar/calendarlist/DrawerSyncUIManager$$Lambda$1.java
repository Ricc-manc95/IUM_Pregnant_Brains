// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;


// Referenced classes of package com.google.android.calendar.calendarlist:
//            SelectCalendarsAdapter, DrawerListAdapter

final class arg._cls1
    implements Runnable
{

    private final DrawerListAdapter arg$1;

    public final void run()
    {
        arg$1.updateItemList();
    }

    (DrawerListAdapter drawerlistadapter)
    {
        arg$1 = drawerlistadapter;
    }
}
