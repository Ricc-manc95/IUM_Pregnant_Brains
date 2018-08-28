// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.OutputStream;

// Referenced classes of package io.grpc.internal:
//            MessageFramer

final class this._cls0 extends OutputStream
{

    private final MessageFramer this$0;

    public final void write(int i)
    {
        write(new byte[] {
            (byte)i
        }, 0, 1);
    }

    public final void write(byte abyte0[], int i, int j)
    {
        writeRaw(abyte0, i, j);
    }

    ()
    {
        this$0 = MessageFramer.this;
        super();
    }
}
