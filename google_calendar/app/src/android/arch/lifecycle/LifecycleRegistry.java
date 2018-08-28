// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import android.arch.core.internal.SafeIterableMap;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

// Referenced classes of package android.arch.lifecycle:
//            Lifecycle, LifecycleOwner, LifecycleObserver

public final class LifecycleRegistry extends Lifecycle
{

    private int mAddingObserverCounter;
    private boolean mHandlingEvent;
    private final WeakReference mLifecycleOwner;
    private boolean mNewEventOccurred;
    private FastSafeIterableMap mObserverMap;
    private ArrayList mParentStates;
    private Lifecycle.State mState;

    public LifecycleRegistry(LifecycleOwner lifecycleowner)
    {
        mObserverMap = new FastSafeIterableMap();
        mAddingObserverCounter = 0;
        mHandlingEvent = false;
        mNewEventOccurred = false;
        mParentStates = new ArrayList();
        mLifecycleOwner = new WeakReference(lifecycleowner);
        mState = Lifecycle.State.INITIALIZED;
    }

    private final Lifecycle.State calculateTargetState(LifecycleObserver lifecycleobserver)
    {
        Object obj = mObserverMap;
        Lifecycle.State state;
        if (((FastSafeIterableMap) (obj)).mHashMap.containsKey(lifecycleobserver))
        {
            lifecycleobserver = ((android.arch.core.internal.SafeIterableMap.Entry)((FastSafeIterableMap) (obj)).mHashMap.get(lifecycleobserver)).mPrevious;
        } else
        {
            lifecycleobserver = null;
        }
        if (lifecycleobserver != null)
        {
            lifecycleobserver = ((ObserverWithState)lifecycleobserver.getValue()).mState;
        } else
        {
            lifecycleobserver = null;
        }
        if (!mParentStates.isEmpty())
        {
            obj = (Lifecycle.State)mParentStates.get(mParentStates.size() - 1);
        } else
        {
            obj = null;
        }
        state = mState;
        if (lifecycleobserver == null || lifecycleobserver.compareTo(state) >= 0)
        {
            lifecycleobserver = state;
        }
        if (obj != null && ((Lifecycle.State) (obj)).compareTo(lifecycleobserver) < 0)
        {
            return ((Lifecycle.State) (obj));
        } else
        {
            return lifecycleobserver;
        }
    }

    public static Lifecycle.State getStateAfter(Lifecycle.Event event)
    {
        switch (event.ordinal())
        {
        default:
            throw new IllegalArgumentException((new StringBuilder("Unexpected event value ")).append(event).toString());

        case 0: // '\0'
        case 4: // '\004'
            return Lifecycle.State.CREATED;

        case 1: // '\001'
        case 3: // '\003'
            return Lifecycle.State.STARTED;

        case 2: // '\002'
            return Lifecycle.State.RESUMED;

        case 5: // '\005'
            return Lifecycle.State.DESTROYED;
        }
    }

    private final void sync()
    {
        LifecycleOwner lifecycleowner;
        lifecycleowner = (LifecycleOwner)mLifecycleOwner.get();
        if (lifecycleowner == null)
        {
            Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
            return;
        }
_L13:
        Object obj2;
        Object obj;
        android.arch.core.internal.SafeIterableMap.DescendingIterator descendingiterator;
        java.util.Map.Entry entry1;
        Object obj3;
        boolean flag;
        if (((SafeIterableMap) (mObserverMap)).mSize == 0)
        {
            flag = true;
        } else
        {
            Lifecycle.State state = ((ObserverWithState)((SafeIterableMap) (mObserverMap)).mStart.getValue()).mState;
            Lifecycle.State state1 = ((ObserverWithState)((SafeIterableMap) (mObserverMap)).mEnd.getValue()).mState;
            if (state == state1 && mState == state1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_609;
        }
        mNewEventOccurred = false;
        if (mState.compareTo(((ObserverWithState)((SafeIterableMap) (mObserverMap)).mStart.getValue()).mState) >= 0)
        {
            break MISSING_BLOCK_LABEL_404;
        }
        obj = mObserverMap;
        descendingiterator = new android.arch.core.internal.SafeIterableMap.DescendingIterator(((SafeIterableMap) (obj)).mEnd, ((SafeIterableMap) (obj)).mStart);
        ((SafeIterableMap) (obj)).mIterators.put(descendingiterator, Boolean.valueOf(false));
_L2:
        if (!descendingiterator.hasNext() || mNewEventOccurred)
        {
            break MISSING_BLOCK_LABEL_404;
        }
        entry1 = (java.util.Map.Entry)descendingiterator.next();
        obj2 = (ObserverWithState)entry1.getValue();
_L10:
        if (((ObserverWithState) (obj2)).mState.compareTo(mState) <= 0 || mNewEventOccurred) goto _L2; else goto _L1
_L1:
        obj = mObserverMap;
        obj3 = entry1.getKey();
        if (!((FastSafeIterableMap) (obj)).mHashMap.containsKey(obj3)) goto _L2; else goto _L3
_L3:
        obj = ((ObserverWithState) (obj2)).mState;
        ((Lifecycle.State) (obj)).ordinal();
        JVM INSTR tableswitch 0 4: default 240
    //                   0 396
    //                   1 327
    //                   2 335
    //                   3 382
    //                   4 389;
           goto _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_389;
_L5:
        break MISSING_BLOCK_LABEL_396;
_L4:
        throw new IllegalArgumentException((new StringBuilder("Unexpected state value ")).append(obj).toString());
_L6:
        throw new IllegalArgumentException();
_L7:
        Object obj1 = Lifecycle.Event.ON_DESTROY;
_L11:
        Lifecycle.State state3 = getStateAfter(((Lifecycle.Event) (obj1)));
        mParentStates.add(state3);
        ((ObserverWithState) (obj2)).dispatchEvent(lifecycleowner, ((Lifecycle.Event) (obj1)));
        mParentStates.remove(mParentStates.size() - 1);
          goto _L10
_L8:
        obj1 = Lifecycle.Event.ON_STOP;
          goto _L11
        obj1 = Lifecycle.Event.ON_PAUSE;
          goto _L11
        throw new IllegalArgumentException();
        obj1 = ((SafeIterableMap) (mObserverMap)).mEnd;
        if (mNewEventOccurred || obj1 == null || mState.compareTo(((ObserverWithState)((java.util.Map.Entry) (obj1)).getValue()).mState) <= 0) goto _L13; else goto _L12
_L12:
        FastSafeIterableMap fastsafeiterablemap = mObserverMap;
        obj1 = new android.arch.core.internal.SafeIterableMap.IteratorWithAdditions(fastsafeiterablemap);
        ((SafeIterableMap) (fastsafeiterablemap)).mIterators.put(obj1, Boolean.valueOf(false));
_L16:
        if (!((Iterator) (obj1)).hasNext() || mNewEventOccurred) goto _L13; else goto _L14
_L14:
        java.util.Map.Entry entry;
        ObserverWithState observerwithstate;
        entry = (java.util.Map.Entry)((Iterator) (obj1)).next();
        observerwithstate = (ObserverWithState)entry.getValue();
_L18:
        if (observerwithstate.mState.compareTo(mState) >= 0 || mNewEventOccurred) goto _L16; else goto _L15
_L15:
        Object obj4;
        obj2 = mObserverMap;
        obj4 = entry.getKey();
        if (!((FastSafeIterableMap) (obj2)).mHashMap.containsKey(obj4)) goto _L16; else goto _L17
_L17:
        Lifecycle.State state2 = observerwithstate.mState;
        mParentStates.add(state2);
        observerwithstate.dispatchEvent(lifecycleowner, upEvent(observerwithstate.mState));
        mParentStates.remove(mParentStates.size() - 1);
          goto _L18
        mNewEventOccurred = false;
        return;
    }

    private static Lifecycle.Event upEvent(Lifecycle.State state)
    {
        switch (state.ordinal())
        {
        default:
            throw new IllegalArgumentException((new StringBuilder("Unexpected state value ")).append(state).toString());

        case 0: // '\0'
        case 1: // '\001'
            return Lifecycle.Event.ON_CREATE;

        case 2: // '\002'
            return Lifecycle.Event.ON_START;

        case 3: // '\003'
            return Lifecycle.Event.ON_RESUME;

        case 4: // '\004'
            throw new IllegalArgumentException();
        }
    }

    public final void addObserver(LifecycleObserver lifecycleobserver)
    {
        Lifecycle.State state;
        ObserverWithState observerwithstate;
        if (mState == Lifecycle.State.DESTROYED)
        {
            state = Lifecycle.State.DESTROYED;
        } else
        {
            state = Lifecycle.State.INITIALIZED;
        }
        observerwithstate = new ObserverWithState(lifecycleobserver, state);
        LifecycleOwner lifecycleowner;
        if ((ObserverWithState)mObserverMap.putIfAbsent(lifecycleobserver, observerwithstate) == null)
        {
            if ((lifecycleowner = (LifecycleOwner)mLifecycleOwner.get()) != null)
            {
                Lifecycle.State state1;
                boolean flag;
                if (mAddingObserverCounter != 0 || mHandlingEvent)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                state1 = calculateTargetState(lifecycleobserver);
                mAddingObserverCounter = mAddingObserverCounter + 1;
                for (; observerwithstate.mState.compareTo(state1) < 0 && mObserverMap.mHashMap.containsKey(lifecycleobserver); state1 = calculateTargetState(lifecycleobserver))
                {
                    state1 = observerwithstate.mState;
                    mParentStates.add(state1);
                    observerwithstate.dispatchEvent(lifecycleowner, upEvent(observerwithstate.mState));
                    mParentStates.remove(mParentStates.size() - 1);
                }

                if (!flag)
                {
                    sync();
                }
                mAddingObserverCounter = mAddingObserverCounter - 1;
                return;
            }
        }
    }

    public final Lifecycle.State getCurrentState()
    {
        return mState;
    }

    public final void moveToState(Lifecycle.State state)
    {
        if (mState == state)
        {
            return;
        }
        mState = state;
        if (mHandlingEvent || mAddingObserverCounter != 0)
        {
            mNewEventOccurred = true;
            return;
        } else
        {
            mHandlingEvent = true;
            sync();
            mHandlingEvent = false;
            return;
        }
    }

    public final void removeObserver(LifecycleObserver lifecycleobserver)
    {
        mObserverMap.remove(lifecycleobserver);
    }

    private class ObserverWithState
    {

        private GenericLifecycleObserver mLifecycleObserver;
        public Lifecycle.State mState;

        final void dispatchEvent(LifecycleOwner lifecycleowner, Lifecycle.Event event)
        {
            Lifecycle.State state1 = LifecycleRegistry.getStateAfter(event);
            Lifecycle.State state2 = mState;
            Lifecycle.State state = state2;
            if (state1 != null)
            {
                state = state2;
                if (state1.compareTo(state2) < 0)
                {
                    state = state1;
                }
            }
            mState = state;
            mLifecycleObserver.onStateChanged(lifecycleowner, event);
            mState = state1;
        }

        ObserverWithState(LifecycleObserver lifecycleobserver, Lifecycle.State state)
        {
            mLifecycleObserver = Lifecycling.getCallback(lifecycleobserver);
            mState = state;
        }
    }

}
