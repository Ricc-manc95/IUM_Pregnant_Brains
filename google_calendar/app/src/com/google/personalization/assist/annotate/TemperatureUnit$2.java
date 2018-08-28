// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


// Referenced classes of package com.google.personalization.assist.annotate:
//            TemperatureUnit

final class 
    implements com.google.protobuf.ier
{

    public final boolean isInRange(int i)
    {
        return TemperatureUnit.forNumber(i) != null;
    }

    ()
    {
    }
}
