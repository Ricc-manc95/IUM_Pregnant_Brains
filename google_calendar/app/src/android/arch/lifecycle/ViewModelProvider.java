// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            ViewModelStore

public final class ViewModelProvider
{

    public final Factory mFactory;
    public final ViewModelStore mViewModelStore;

    public ViewModelProvider(ViewModelStore viewmodelstore, Factory factory)
    {
        mFactory = factory;
        mViewModelStore = viewmodelstore;
    }
}
