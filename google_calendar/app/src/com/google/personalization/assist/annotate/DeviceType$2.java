// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


// Referenced classes of package com.google.personalization.assist.annotate:
//            DeviceType

final class 
    implements com.google.protobuf.Verifier
{

    public final boolean isInRange(int i)
    {
        return DeviceType.forNumber(i) != null;
    }

    ()
    {
    }
}
