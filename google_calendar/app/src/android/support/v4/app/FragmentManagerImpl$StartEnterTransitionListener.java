// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            BackStackRecord, FragmentManagerImpl, Fragment

final class mRecord
    implements mRecord
{

    public final boolean mIsBack;
    public int mNumPostponed;
    public final BackStackRecord mRecord;

    public final void completeTransaction()
    {
        boolean flag1 = false;
        FragmentManagerImpl fragmentmanagerimpl;
        boolean flag;
        int j;
        if (mNumPostponed > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        fragmentmanagerimpl = mRecord.mManager;
        j = fragmentmanagerimpl.mAdded.size();
        for (int i = 0; i < j; i++)
        {
            Object obj = (Fragment)fragmentmanagerimpl.mAdded.get(i);
            ((Fragment) (obj)).setOnStartEnterTransitionListener(null);
            if (flag)
            {
                obj = ((Fragment) (obj)).mAnimationInfo;
            }
        }

        fragmentmanagerimpl = mRecord.mManager;
        BackStackRecord backstackrecord = mRecord;
        boolean flag2 = mIsBack;
        if (!flag)
        {
            flag1 = true;
        }
        fragmentmanagerimpl.completeExecute(backstackrecord, flag2, flag1, true);
    }

    public final void startListening()
    {
        mNumPostponed = mNumPostponed + 1;
    }

    (BackStackRecord backstackrecord, boolean flag)
    {
        mIsBack = flag;
        mRecord = backstackrecord;
    }
}
