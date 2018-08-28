// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            GenericLifecycleObserver, FullLifecycleObserver, LifecycleOwner

final class FullLifecycleObserverAdapter
    implements GenericLifecycleObserver
{

    private final FullLifecycleObserver mObserver;

    FullLifecycleObserverAdapter(FullLifecycleObserver fulllifecycleobserver)
    {
        mObserver = fulllifecycleobserver;
    }

    public final void onStateChanged(LifecycleOwner lifecycleowner, Lifecycle.Event event)
    {
        switch (event.ordinal())
        {
        default:
            return;

        case 0: // '\0'
            mObserver.onCreate(lifecycleowner);
            return;

        case 1: // '\001'
            mObserver.onStart(lifecycleowner);
            return;

        case 2: // '\002'
            mObserver.onResume(lifecycleowner);
            return;

        case 3: // '\003'
            mObserver.onPause(lifecycleowner);
            return;

        case 4: // '\004'
            mObserver.onStop(lifecycleowner);
            return;

        case 5: // '\005'
            mObserver.onDestroy$51662RJ4E9NMIP1FC5P66Q1FDHKMCPB3F5HMOP9F9HKMCPB3F5HMOPAFETN6ASHR55B0____0();
            return;

        case 6: // '\006'
            throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
    }
}
