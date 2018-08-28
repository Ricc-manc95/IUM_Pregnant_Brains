// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;


// Referenced classes of package com.google.android.libraries.streamz:
//            GenericCounter, Field, MetricConfigProvider

public final class Counter3 extends GenericCounter
{

    public Counter3(String s, MetricConfigProvider metricconfigprovider, Field field, Field field1, Field field2)
    {
        super(s, metricconfigprovider, new Field[] {
            field, field1, field2
        });
    }
}
