// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;

import com.google.identity.boq.growth.common.proto.IosPermissionType;

final class 
    implements com.google.protobuf.ionRequest._cls1
{

    public final Object convert(Object obj)
    {
        IosPermissionType iospermissiontype = IosPermissionType.forNumber(((Integer)obj).intValue());
        obj = iospermissiontype;
        if (iospermissiontype == null)
        {
            obj = IosPermissionType.INVALID_PERMISSION_TYPE;
        }
        return obj;
    }

    ()
    {
    }
}
