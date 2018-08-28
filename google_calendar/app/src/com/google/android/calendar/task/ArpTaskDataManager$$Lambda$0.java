// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskDataManager

final class arg._cls1
    implements Consumer
{

    private final ArpTaskDataManager arg$1;

    public final void accept(Object obj)
    {
        arg$1.loadAccounts(false);
    }

    (ArpTaskDataManager arptaskdatamanager)
    {
        arg$1 = arptaskdatamanager;
    }
}
