// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import java.util.List;

public final class FragmentManagerNonConfig
{

    public final List mChildNonConfigs;
    public final List mFragments;
    public final List mViewModelStores;

    FragmentManagerNonConfig(List list, List list1, List list2)
    {
        mFragments = list;
        mChildNonConfigs = list1;
        mViewModelStores = list2;
    }
}
