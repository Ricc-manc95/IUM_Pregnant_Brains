// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            LifecycleObserver, LifecycleOwner

public interface FullLifecycleObserver
    extends LifecycleObserver
{

    public abstract void onCreate(LifecycleOwner lifecycleowner);

    public abstract void onDestroy$51662RJ4E9NMIP1FC5P66Q1FDHKMCPB3F5HMOP9F9HKMCPB3F5HMOPAFETN6ASHR55B0____0();

    public abstract void onPause(LifecycleOwner lifecycleowner);

    public abstract void onResume(LifecycleOwner lifecycleowner);

    public abstract void onStart(LifecycleOwner lifecycleowner);

    public abstract void onStop(LifecycleOwner lifecycleowner);
}
