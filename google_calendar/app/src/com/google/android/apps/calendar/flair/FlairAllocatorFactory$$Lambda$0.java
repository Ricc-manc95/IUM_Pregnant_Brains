// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.flair;


// Referenced classes of package com.google.android.apps.calendar.flair:
//            FlairAllocatorFactory

final class 
    implements Runnable
{

    public static final Runnable $instance = new <init>();

    public final void run()
    {
        FlairAllocatorFactory.getAllocator();
    }


    private ()
    {
    }
}
