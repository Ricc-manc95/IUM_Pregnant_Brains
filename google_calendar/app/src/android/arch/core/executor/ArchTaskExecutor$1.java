// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.core.executor;

import java.util.concurrent.Executor;

// Referenced classes of package android.arch.core.executor:
//            ArchTaskExecutor, TaskExecutor

final class 
    implements Executor
{

    public final void execute(Runnable runnable)
    {
        ArchTaskExecutor.getInstance().postToMainThread(runnable);
    }

    ()
    {
    }
}
