// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            GenericLifecycleObserver, MethodCallsLogger, GeneratedAdapter, LifecycleOwner

public final class CompositeGeneratedAdaptersObserver
    implements GenericLifecycleObserver
{

    private final GeneratedAdapter mGeneratedAdapters[];

    CompositeGeneratedAdaptersObserver(GeneratedAdapter ageneratedadapter[])
    {
        mGeneratedAdapters = ageneratedadapter;
    }

    public final void onStateChanged(LifecycleOwner lifecycleowner, Lifecycle.Event event)
    {
        boolean flag = false;
        new MethodCallsLogger();
        lifecycleowner = mGeneratedAdapters;
        int k = lifecycleowner.length;
        for (int i = 0; i < k; i++)
        {
            lifecycleowner[i].callMethods$51662RJ4E9NMIP1FC5P66Q1FDHKMCPB3F5HMOP9F9HKMCPB3F5HMOPAFETN6ASHR9HGMSP3IDTKM8BR1E9HMGBRCD5J6AORPCDM6ABQCD5J6AORPCDM6A925EPIMST1RB9662RJ4E9NMIP1FC5P66Q1FDHKMCPB3F5HMOP9F9LIN8Q3FCH1M2R3CED66UPR7CLP3MAAM0();
        }

        lifecycleowner = mGeneratedAdapters;
        k = lifecycleowner.length;
        for (int j = ((flag) ? 1 : 0); j < k; j++)
        {
            lifecycleowner[j].callMethods$51662RJ4E9NMIP1FC5P66Q1FDHKMCPB3F5HMOP9F9HKMCPB3F5HMOPAFETN6ASHR9HGMSP3IDTKM8BR1E9HMGBRCD5J6AORPCDM6ABQCD5J6AORPCDM6A925EPIMST1RB9662RJ4E9NMIP1FC5P66Q1FDHKMCPB3F5HMOP9F9LIN8Q3FCH1M2R3CED66UPR7CLP3MAAM0();
        }

    }
}
