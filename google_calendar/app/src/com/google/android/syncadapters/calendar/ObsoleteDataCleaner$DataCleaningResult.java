// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;


// Referenced classes of package com.google.android.syncadapters.calendar:
//            ObsoleteDataCleaner

public static final class habitNotificationsDeleted
{

    public static final habitNotificationsDeleted NO_FEATURE_RESULT = new <init>(-1, -1, 0, 0);
    public static final <init> NO_PERMISSIONS_RESULT = new <init>(-2, -2, 0, 0);
    public final int eventsDeleted;
    public final int fromStage;
    public final int habitNotificationsDeleted;
    public final int toStage;


    (int i, int j, int k, int l)
    {
        fromStage = i;
        toStage = j;
        eventsDeleted = k;
        habitNotificationsDeleted = l;
    }
}
