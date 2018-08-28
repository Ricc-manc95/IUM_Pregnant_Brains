// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.io.Closeable;

public abstract class BinaryLog
    implements Closeable
{

    public static final CallOptions.Key CLIENT_CALL_ID_CALLOPTION_KEY;

    static 
    {
        if ("binarylog-calloptions-key" == null)
        {
            throw new NullPointerException(String.valueOf("debugString"));
        } else
        {
            CLIENT_CALL_ID_CALLOPTION_KEY = new CallOptions.Key("binarylog-calloptions-key", null);
        }
    }
}
