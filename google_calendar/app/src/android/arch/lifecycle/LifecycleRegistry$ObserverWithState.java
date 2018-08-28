// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            Lifecycling, LifecycleRegistry, GenericLifecycleObserver, LifecycleObserver, 
//            LifecycleOwner

final class mState
{

    private GenericLifecycleObserver mLifecycleObserver;
    public mState mState;

    final void dispatchEvent(LifecycleOwner lifecycleowner, mState mstate)
    {
        mState mstate2 = LifecycleRegistry.getStateAfter(mstate);
        mState mstate3 = mState;
        mState mstate1 = mstate3;
        if (mstate2 != null)
        {
            mstate1 = mstate3;
            if (mstate2.mState(mstate3) < 0)
            {
                mstate1 = mstate2;
            }
        }
        mState = mstate1;
        mLifecycleObserver.onStateChanged(lifecycleowner, mstate);
        mState = mstate2;
    }

    (LifecycleObserver lifecycleobserver,  )
    {
        mLifecycleObserver = Lifecycling.getCallback(lifecycleobserver);
        mState = ;
    }
}
