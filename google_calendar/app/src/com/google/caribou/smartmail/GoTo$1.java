// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.caribou.smartmail;


final class urce
    implements com.google.protobuf.l.ListAdapter.Converter
{

    public final Object convert(Object obj)
    {
        urce urce = com.google.caribou.smartmail.urce.forNumber(((Integer)obj).intValue());
        obj = urce;
        if (urce == null)
        {
            obj = urce.UNKNOWN_SOURCE;
        }
        return obj;
    }

    urce()
    {
    }
}
