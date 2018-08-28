// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            Status

public final class InternalStatus
{

    public static final Metadata.Key CODE_KEY;
    public static final Metadata.Key MESSAGE_KEY;

    static 
    {
        MESSAGE_KEY = Status.MESSAGE_KEY;
        CODE_KEY = Status.CODE_KEY;
    }
}
