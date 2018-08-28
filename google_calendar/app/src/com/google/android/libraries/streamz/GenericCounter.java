// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;


// Referenced classes of package com.google.android.libraries.streamz:
//            GenericMetric, CounterCellValue, MetricConfigProvider, Field, 
//            CellValue

class GenericCounter extends GenericMetric
{

    transient GenericCounter(String s, MetricConfigProvider metricconfigprovider, Field afield[])
    {
        super(s, metricconfigprovider, afield);
    }

    final CellValue newCellValue()
    {
        return new CounterCellValue();
    }
}
