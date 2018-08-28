// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            LiveData

final class this._cls0
    implements Runnable
{

    private final LiveData this$0;

    public final void run()
    {
        Object obj1;
        synchronized (mDataLock)
        {
            obj1 = mPendingData;
            mPendingData = LiveData.NOT_SET;
        }
        setValue(obj1);
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    ()
    {
        this$0 = LiveData.this;
        super();
    }
}
