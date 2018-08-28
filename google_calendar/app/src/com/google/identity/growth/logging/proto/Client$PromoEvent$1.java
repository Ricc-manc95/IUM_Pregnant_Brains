// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.logging.proto;


final class nditionsReason
    implements com.google.protobuf..Converter
{

    public final Object convert(Object obj)
    {
        nditionsReason nditionsreason = nditionsReason.forNumber(((Integer)obj).intValue());
        obj = nditionsreason;
        if (nditionsreason == null)
        {
            obj = nditionsReason.CONDITION_UNKNOWN;
        }
        return obj;
    }

    nditionsReason()
    {
    }
}
