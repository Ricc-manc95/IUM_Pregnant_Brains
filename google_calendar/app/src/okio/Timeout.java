// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.io.IOException;
import java.io.InterruptedIOException;

public class Timeout
{

    public Timeout()
    {
    }

    public void throwIfReached()
        throws IOException
    {
        if (Thread.interrupted())
        {
            throw new InterruptedIOException("thread interrupted");
        } else
        {
            return;
        }
    }

    static 
    {
        new _cls1();
    }

    private class _cls1 extends Timeout
    {

        public final void throwIfReached()
            throws IOException
        {
        }

        _cls1()
        {
        }
    }

}
