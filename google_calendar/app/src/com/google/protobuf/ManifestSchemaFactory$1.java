// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            MessageInfoFactory, MessageInfo

final class 
    implements MessageInfoFactory
{

    public final boolean isSupported(Class class1)
    {
        return false;
    }

    public final MessageInfo messageInfoFor(Class class1)
    {
        throw new IllegalStateException("This should never be called.");
    }

    ()
    {
    }
}
