// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            LifecycleOwner, LifecycleRegistry

public interface LifecycleRegistryOwner
    extends LifecycleOwner
{

    public abstract LifecycleRegistry getLifecycle();
}
