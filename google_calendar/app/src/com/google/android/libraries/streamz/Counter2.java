// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;


// Referenced classes of package com.google.android.libraries.streamz:
//            GenericCounter, Field, MetricConfigProvider

public final class Counter2 extends GenericCounter
{

    public Counter2(String s, MetricConfigProvider metricconfigprovider, Field field, Field field1)
    {
        super(s, metricconfigprovider, new Field[] {
            field, field1
        });
    }
}
