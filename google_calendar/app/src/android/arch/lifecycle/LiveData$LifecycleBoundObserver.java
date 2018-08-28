// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            GenericLifecycleObserver, LifecycleOwner, Lifecycle, LiveData, 
//            Observer

final class mOwner extends mOwner
    implements GenericLifecycleObserver
{

    private final LifecycleOwner mOwner;
    private final LiveData this$0;

    final void detachObserver()
    {
        mOwner.getLifecycle().removeObserver(this);
    }

    final boolean isAttachedTo(LifecycleOwner lifecycleowner)
    {
        return mOwner == lifecycleowner;
    }

    public final void onStateChanged(LifecycleOwner lifecycleowner, mOwner mowner)
    {
        if (mOwner.getLifecycle().getCurrentState() == mOwner)
        {
            removeObserver(mObserver);
            return;
        } else
        {
            tateChanged(eActive());
            return;
        }
    }

    final boolean shouldBeActive()
    {
        return mOwner.getLifecycle().getCurrentState().mOwner(mOwner) >= 0;
    }

    (LifecycleOwner lifecycleowner, Observer observer)
    {
        this$0 = LiveData.this;
        super(LiveData.this, observer);
        mOwner = lifecycleowner;
    }
}
