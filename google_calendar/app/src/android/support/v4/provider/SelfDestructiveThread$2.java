// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import android.os.Handler;
import java.util.concurrent.Callable;

// Referenced classes of package android.support.v4.provider:
//            SelfDestructiveThread

final class val.reply
    implements Runnable
{

    private final Callable val$callable;
    private final Handler val$callingHandler;
    public final plyCallback val$reply;

    public final void run()
    {
        class _cls1
            implements Runnable
        {

            private final SelfDestructiveThread._cls2 this$1;
            private final Object val$result;

            public final void run()
            {
                reply.onReply(result);
            }

            _cls1()
            {
                this$1 = SelfDestructiveThread._cls2.this;
                result = obj;
                super();
            }
        }

        final Object result;
        try
        {
            result = val$callable.call();
        }
        catch (Exception exception)
        {
            exception = null;
        }
        val$callingHandler.post(new _cls1());
    }

    plyCallback(plyCallback plycallback)
    {
        val$callable = callable1;
        val$callingHandler = handler;
        val$reply = plycallback;
        super();
    }
}
