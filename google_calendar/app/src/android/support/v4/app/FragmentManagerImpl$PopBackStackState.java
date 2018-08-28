// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentManagerImpl, Fragment, FragmentManager

final class mFlags
    implements mFlags
{

    private final int mFlags;
    private final int mId;
    private final String mName = null;
    private final FragmentManagerImpl this$0;

    public final boolean generateOps(ArrayList arraylist, ArrayList arraylist1)
    {
        if (mPrimaryNav != null && mId < 0 && mName == null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mPrimaryNav.mChildFragmentManager;
            if (fragmentmanagerimpl != null && fragmentmanagerimpl.popBackStackImmediate())
            {
                return false;
            }
        }
        return popBackStackState(arraylist, arraylist1, mName, mId, mFlags);
    }

    (String s, int i, int j)
    {
        this$0 = FragmentManagerImpl.this;
        super();
        mId = i;
        mFlags = j;
    }
}
