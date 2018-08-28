// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            LifecycleObserver, LifecycleOwner

public interface GenericLifecycleObserver
    extends LifecycleObserver
{

    public abstract void onStateChanged(LifecycleOwner lifecycleowner, Lifecycle.Event event);
}
