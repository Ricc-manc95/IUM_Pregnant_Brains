// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.core.executor.TaskExecutor;
import android.arch.core.internal.SafeIterableMap;
import java.util.Iterator;
import java.util.WeakHashMap;

// Referenced classes of package android.arch.lifecycle:
//            Observer, LifecycleOwner, Lifecycle

public class LiveData
{

    public static final Object NOT_SET = new Object();
    public int mActiveCount;
    public volatile Object mData;
    public final Object mDataLock = new Object();
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private SafeIterableMap mObservers;
    public volatile Object mPendingData;
    private final Runnable mPostValueRunnable = new _cls1();
    private int mVersion;

    public LiveData()
    {
        mObservers = new SafeIterableMap();
        mActiveCount = 0;
        mData = NOT_SET;
        mPendingData = NOT_SET;
        mVersion = -1;
    }

    private static void assertMainThread(String s)
    {
        if (!ArchTaskExecutor.getInstance().isMainThread())
        {
            throw new IllegalStateException((new StringBuilder("Cannot invoke ")).append(s).append(" on a background thread").toString());
        } else
        {
            return;
        }
    }

    private final void considerNotify(ObserverWrapper observerwrapper)
    {
        if (observerwrapper.mActive)
        {
            if (!observerwrapper.shouldBeActive())
            {
                observerwrapper.activeStateChanged(false);
                return;
            }
            if (observerwrapper.mLastVersion < mVersion)
            {
                observerwrapper.mLastVersion = mVersion;
                observerwrapper.mObserver.onChanged(mData);
                return;
            }
        }
    }

    final void dispatchingValue(ObserverWrapper observerwrapper)
    {
        if (mDispatchingValue)
        {
            mDispatchInvalidated = true;
            return;
        }
        mDispatchingValue = true;
_L2:
        Object obj;
        mDispatchInvalidated = false;
        if (observerwrapper == null)
        {
            break; /* Loop/switch isn't completed */
        }
        considerNotify(observerwrapper);
        obj = null;
_L4:
        observerwrapper = ((ObserverWrapper) (obj));
        if (!mDispatchInvalidated)
        {
            mDispatchingValue = false;
            return;
        }
        if (true) goto _L2; else goto _L1
_L1:
        android.arch.core.internal.SafeIterableMap.IteratorWithAdditions iteratorwithadditions;
        obj = mObservers;
        iteratorwithadditions = new android.arch.core.internal.SafeIterableMap.IteratorWithAdditions(((SafeIterableMap) (obj)));
        ((SafeIterableMap) (obj)).mIterators.put(iteratorwithadditions, Boolean.valueOf(false));
_L6:
        obj = observerwrapper;
        if (!iteratorwithadditions.hasNext()) goto _L4; else goto _L3
_L3:
        considerNotify((ObserverWrapper)((java.util.Map.Entry)iteratorwithadditions.next()).getValue());
        if (!mDispatchInvalidated) goto _L6; else goto _L5
_L5:
        obj = observerwrapper;
          goto _L4
    }

    public final void observe(LifecycleOwner lifecycleowner, Observer observer)
    {
        if (lifecycleowner.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED)
        {
            LifecycleBoundObserver lifecycleboundobserver = new LifecycleBoundObserver(lifecycleowner, observer);
            observer = (ObserverWrapper)mObservers.putIfAbsent(observer, lifecycleboundobserver);
            if (observer != null && !observer.isAttachedTo(lifecycleowner))
            {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            }
            if (observer == null)
            {
                lifecycleowner.getLifecycle().addObserver(lifecycleboundobserver);
                return;
            }
        }
    }

    public void onActive()
    {
    }

    public void onInactive()
    {
    }

    public void postValue(Object obj)
    {
        Object obj1 = mDataLock;
        obj1;
        JVM INSTR monitorenter ;
        boolean flag;
        if (mPendingData == NOT_SET)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mPendingData = obj;
        obj1;
        JVM INSTR monitorexit ;
        if (!flag)
        {
            return;
        } else
        {
            ArchTaskExecutor.getInstance().postToMainThread(mPostValueRunnable);
            return;
        }
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public void removeObserver(Observer observer)
    {
        assertMainThread("removeObserver");
        observer = (ObserverWrapper)mObservers.remove(observer);
        if (observer == null)
        {
            return;
        } else
        {
            observer.detachObserver();
            observer.activeStateChanged(false);
            return;
        }
    }

    public void setValue(Object obj)
    {
        assertMainThread("setValue");
        mVersion = mVersion + 1;
        mData = obj;
        dispatchingValue(null);
    }


    private class _cls1
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

        _cls1()
        {
            this$0 = LiveData.this;
            super();
        }
    }


    private class ObserverWrapper
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

        ObserverWrapper(Observer observer)
        {
            this$0 = LiveData.this;
            super();
            mLastVersion = -1;
            mObserver = observer;
        }
    }


    private class LifecycleBoundObserver extends ObserverWrapper
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

        public final void onStateChanged(LifecycleOwner lifecycleowner, Lifecycle.Event event)
        {
            if (mOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED)
            {
                removeObserver(mObserver);
                return;
            } else
            {
                activeStateChanged(shouldBeActive());
                return;
            }
        }

        final boolean shouldBeActive()
        {
            return mOwner.getLifecycle().getCurrentState().compareTo(Lifecycle.State.STARTED) >= 0;
        }

        LifecycleBoundObserver(LifecycleOwner lifecycleowner, Observer observer)
        {
            this$0 = LiveData.this;
            super(observer);
            mOwner = lifecycleowner;
        }
    }

}
