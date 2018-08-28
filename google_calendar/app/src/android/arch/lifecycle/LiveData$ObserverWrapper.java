// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            LiveData, Observer, LifecycleOwner

abstract class mObserver
{

    public boolean mActive;
    public int mLastVersion;
    public final Observer mObserver;
    private final LiveData this$0;

    final void activeStateChanged(boolean flag)
    {
        int i = 1;
        if (flag != mActive)
        {
            mActive = flag;
            LiveData livedata;
            boolean flag1;
            int j;
            if (mActiveCount == 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            livedata = LiveData.this;
            j = livedata.mActiveCount;
            if (!mActive)
            {
                i = -1;
            }
            livedata.mActiveCount = i + j;
            if (flag1 && mActive)
            {
                onActive();
            }
            if (mActiveCount == 0 && !mActive)
            {
                onInactive();
            }
            if (mActive)
            {
                dispatchingValue(this);
                return;
            }
        }
    }

    void detachObserver()
    {
    }

    boolean isAttachedTo(LifecycleOwner lifecycleowner)
    {
        return false;
    }

    abstract boolean shouldBeActive();

    (Observer observer)
    {
        this$0 = LiveData.this;
        super();
        mLastVersion = -1;
        mObserver = observer;
    }
}
