// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.v4.app:
//            FragmentManager, Fragment, FragmentTransaction

public abstract class FragmentPagerAdapter extends PagerAdapter
{

    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private final FragmentManager mFragmentManager;

    public FragmentPagerAdapter(FragmentManager fragmentmanager)
    {
        mCurTransaction = null;
        mCurrentPrimaryItem = null;
        mFragmentManager = fragmentmanager;
    }

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        if (mCurTransaction == null)
        {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        mCurTransaction.detach((Fragment)obj);
    }

    public final void finishUpdate(ViewGroup viewgroup)
    {
        if (mCurTransaction != null)
        {
            mCurTransaction.commitNowAllowingStateLoss();
            mCurTransaction = null;
        }
    }

    public abstract Fragment getItem(int i);

    public final Object instantiateItem(ViewGroup viewgroup, int i)
    {
        if (mCurTransaction == null)
        {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        long l = i;
        int j = viewgroup.getId();
        Object obj = (new StringBuilder("android:switcher:")).append(j).append(":").append(l).toString();
        obj = mFragmentManager.findFragmentByTag(((String) (obj)));
        if (obj != null)
        {
            mCurTransaction.attach(((Fragment) (obj)));
            viewgroup = ((ViewGroup) (obj));
        } else
        {
            Fragment fragment = getItem(i);
            FragmentTransaction fragmenttransaction = mCurTransaction;
            i = viewgroup.getId();
            int k = viewgroup.getId();
            fragmenttransaction.add(i, fragment, (new StringBuilder("android:switcher:")).append(k).append(":").append(l).toString());
            viewgroup = fragment;
        }
        if (viewgroup != mCurrentPrimaryItem)
        {
            viewgroup.setMenuVisibility(false);
            viewgroup.setUserVisibleHint(false);
        }
        return viewgroup;
    }

    public final boolean isViewFromObject(View view, Object obj)
    {
        return ((Fragment)obj).mView == view;
    }

    public final Parcelable saveState()
    {
        return null;
    }

    public final void setPrimaryItem(ViewGroup viewgroup, int i, Object obj)
    {
        viewgroup = (Fragment)obj;
        if (viewgroup != mCurrentPrimaryItem)
        {
            if (mCurrentPrimaryItem != null)
            {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (viewgroup != null)
            {
                viewgroup.setMenuVisibility(true);
                viewgroup.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = viewgroup;
        }
    }

    public final void startUpdate(ViewGroup viewgroup)
    {
        if (viewgroup.getId() == -1)
        {
            throw new IllegalStateException((new StringBuilder("ViewPager with adapter ")).append(this).append(" requires a view id").toString());
        } else
        {
            return;
        }
    }
}
