// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.bucket;

import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timebox.bucket:
//            TypeBucketer

public interface 
{

    public abstract TypeBucketer getBucketer(TimeRangeEntry timerangeentry);

    public abstract List getBucketers();
}
