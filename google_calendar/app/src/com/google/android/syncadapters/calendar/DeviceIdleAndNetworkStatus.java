// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;


final class DeviceIdleAndNetworkStatus
{

    public final Boolean hasNetwork;
    public final Boolean isDeviceIdle;
    public final Boolean isDeviceIdleLight;

    public final String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = new com.google.common.base.MoreObjects.ToStringHelper("DIANStatus");
        Boolean boolean1 = hasNetwork;
        com.google.common.base.MoreObjects.ToStringHelper.ValueHolder valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = boolean1;
        if ("hasNetwork" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"hasNetwork";
        boolean1 = isDeviceIdle;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = boolean1;
        if ("isDeviceIdle" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"isDeviceIdle";
        boolean1 = isDeviceIdleLight;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = boolean1;
        if ("isDeviceIdleLight" == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)"isDeviceIdleLight";
            tostringhelper.omitNullValues = true;
            return tostringhelper.toString();
        }
    }
}
