// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.support.v4.view.ViewCompat;
import android.view.View;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransaction, FragmentManagerImpl, Fragment, FragmentTransition

final class BackStackRecord extends FragmentTransaction
    implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator
{

    public boolean mAddToBackStack;
    private boolean mAllowAddToBackStack;
    public int mBreadCrumbShortTitleRes;
    public CharSequence mBreadCrumbShortTitleText;
    public int mBreadCrumbTitleRes;
    public CharSequence mBreadCrumbTitleText;
    public boolean mCommitted;
    public int mEnterAnim;
    public int mExitAnim;
    public int mIndex;
    public final FragmentManagerImpl mManager;
    public String mName;
    public ArrayList mOps;
    public int mPopEnterAnim;
    public int mPopExitAnim;
    public boolean mReorderingAllowed;
    public ArrayList mSharedElementSourceNames;
    public ArrayList mSharedElementTargetNames;
    public int mTransition;
    public int mTransitionStyle;

    public BackStackRecord(FragmentManagerImpl fragmentmanagerimpl)
    {
        mOps = new ArrayList();
        mAllowAddToBackStack = true;
        mIndex = -1;
        mReorderingAllowed = false;
        mManager = fragmentmanagerimpl;
    }

    private final int commitInternal(boolean flag)
    {
        if (mCommitted)
        {
            throw new IllegalStateException("commit already called");
        }
        mCommitted = true;
        if (mAddToBackStack)
        {
            mIndex = mManager.allocBackStackIndex(this);
        } else
        {
            mIndex = -1;
        }
        mManager.enqueueAction(this, flag);
        return mIndex;
    }

    private final void doAddOp(int i, Fragment fragment, String s, int j)
    {
        Class class1 = fragment.getClass();
        int k = class1.getModifiers();
        if (class1.isAnonymousClass() || !Modifier.isPublic(k) || class1.isMemberClass() && !Modifier.isStatic(k))
        {
            throw new IllegalStateException((new StringBuilder("Fragment ")).append(class1.getCanonicalName()).append(" must be a public static class to be  properly recreated from instance state.").toString());
        }
        fragment.mFragmentManager = mManager;
        if (s != null)
        {
            if (fragment.mTag != null && !s.equals(fragment.mTag))
            {
                throw new IllegalStateException((new StringBuilder("Can't change tag of fragment ")).append(fragment).append(": was ").append(fragment.mTag).append(" now ").append(s).toString());
            }
            fragment.mTag = s;
        }
        if (i != 0)
        {
            if (i == -1)
            {
                throw new IllegalArgumentException((new StringBuilder("Can't add fragment ")).append(fragment).append(" with tag ").append(s).append(" to container view with no id").toString());
            }
            if (fragment.mFragmentId != 0 && fragment.mFragmentId != i)
            {
                throw new IllegalStateException((new StringBuilder("Can't change container ID of fragment ")).append(fragment).append(": was ").append(fragment.mFragmentId).append(" now ").append(i).toString());
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = i;
        }
        addOp(new Op(j, fragment));
    }

    static boolean isFragmentPostponed(Op op)
    {
        op = op.fragment;
        if (op != null && ((Fragment) (op)).mAdded && ((Fragment) (op)).mView != null && !((Fragment) (op)).mDetached && !((Fragment) (op)).mHidden)
        {
            op = ((Fragment) (op)).mAnimationInfo;
        }
        return false;
    }

    public final FragmentTransaction add(int i, Fragment fragment)
    {
        doAddOp(i, fragment, null, 1);
        return this;
    }

    public final FragmentTransaction add(int i, Fragment fragment, String s)
    {
        doAddOp(i, fragment, s, 1);
        return this;
    }

    public final FragmentTransaction add(Fragment fragment, String s)
    {
        doAddOp(0, fragment, s, 1);
        return this;
    }

    final void addOp(Op op)
    {
        mOps.add(op);
        op.enterAnim = mEnterAnim;
        op.exitAnim = mExitAnim;
        op.popEnterAnim = mPopEnterAnim;
        op.popExitAnim = mPopExitAnim;
    }

    public final FragmentTransaction addSharedElement(View view, String s)
    {
        boolean flag;
        if (FragmentTransition.PLATFORM_IMPL != null || FragmentTransition.SUPPORT_IMPL != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            view = ViewCompat.getTransitionName(view);
            if (view == null)
            {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (mSharedElementSourceNames == null)
            {
                mSharedElementSourceNames = new ArrayList();
                mSharedElementTargetNames = new ArrayList();
            } else
            {
                if (mSharedElementTargetNames.contains(s))
                {
                    throw new IllegalArgumentException((new StringBuilder("A shared element with the target name '")).append(s).append("' has already been added to the transaction.").toString());
                }
                if (mSharedElementSourceNames.contains(view))
                {
                    throw new IllegalArgumentException((new StringBuilder("A shared element with the source name '")).append(view).append(" has already been added to the transaction.").toString());
                }
            }
            mSharedElementSourceNames.add(view);
            mSharedElementTargetNames.add(s);
        }
        return this;
    }

    public final FragmentTransaction addToBackStack(String s)
    {
        if (!mAllowAddToBackStack)
        {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        } else
        {
            mAddToBackStack = true;
            mName = s;
            return this;
        }
    }

    public final FragmentTransaction attach(Fragment fragment)
    {
        addOp(new Op(7, fragment));
        return this;
    }

    final void bumpBackStackNesting(int i)
    {
        if (mAddToBackStack)
        {
            int k = mOps.size();
            int j = 0;
            while (j < k) 
            {
                Object obj = (Op)mOps.get(j);
                if (((Op) (obj)).fragment != null)
                {
                    obj = ((Op) (obj)).fragment;
                    obj.mBackStackNesting = ((Fragment) (obj)).mBackStackNesting + i;
                }
                j++;
            }
        }
    }

    public final int commit()
    {
        return commitInternal(false);
    }

    public final int commitAllowingStateLoss()
    {
        return commitInternal(true);
    }

    public final void commitNow()
    {
        if (mAddToBackStack)
        {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        } else
        {
            mAllowAddToBackStack = false;
            mManager.execSingleAction(this, false);
            return;
        }
    }

    public final void commitNowAllowingStateLoss()
    {
        if (mAddToBackStack)
        {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        } else
        {
            mAllowAddToBackStack = false;
            mManager.execSingleAction(this, true);
            return;
        }
    }

    public final FragmentTransaction detach(Fragment fragment)
    {
        addOp(new Op(6, fragment));
        return this;
    }

    final void executeOps()
    {
        int i;
        int j;
        j = mOps.size();
        i = 0;
_L11:
        Op op;
        Fragment fragment;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_514;
        }
        op = (Op)mOps.get(i);
        fragment = op.fragment;
        if (fragment != null)
        {
            fragment.setNextTransition(mTransition, mTransitionStyle);
        }
        op.cmd;
        JVM INSTR tableswitch 1 9: default 104
    //                   1 132
    //                   2 104
    //                   3 220
    //                   4 276
    //                   5 328
    //                   6 380
    //                   7 436
    //                   8 492
    //                   9 503;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_503;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder("Unknown cmd: ")).append(op.cmd).toString());
_L2:
        int k = op.enterAnim;
        if (fragment.mAnimationInfo != null || k != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = k;
        }
        mManager.addFragment(fragment, false);
_L12:
        if (!mReorderingAllowed && op.cmd != 1 && fragment != null)
        {
            mManager.moveFragmentToExpectedState(fragment);
        }
        i++;
        if (true) goto _L11; else goto _L10
_L10:
        int l = op.exitAnim;
        if (fragment.mAnimationInfo != null || l != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = l;
        }
        mManager.removeFragment(fragment);
          goto _L12
_L4:
        int i1 = op.exitAnim;
        if (fragment.mAnimationInfo != null || i1 != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = i1;
        }
        FragmentManagerImpl.hideFragment(fragment);
          goto _L12
_L5:
        int j1 = op.enterAnim;
        if (fragment.mAnimationInfo != null || j1 != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = j1;
        }
        FragmentManagerImpl.showFragment(fragment);
          goto _L12
_L6:
        int k1 = op.exitAnim;
        if (fragment.mAnimationInfo != null || k1 != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = k1;
        }
        mManager.detachFragment(fragment);
          goto _L12
_L7:
        int l1 = op.enterAnim;
        if (fragment.mAnimationInfo != null || l1 != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = l1;
        }
        mManager.attachFragment(fragment);
          goto _L12
_L8:
        mManager.setPrimaryNavigationFragment(fragment);
          goto _L12
        mManager.setPrimaryNavigationFragment(null);
          goto _L12
        if (!mReorderingAllowed)
        {
            mManager.moveToState(mManager.mCurState, true);
        }
        return;
    }

    final void executePopOps(boolean flag)
    {
        int i = mOps.size() - 1;
_L11:
        Op op;
        Fragment fragment;
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_520;
        }
        op = (Op)mOps.get(i);
        fragment = op.fragment;
        if (fragment != null)
        {
            fragment.setNextTransition(FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
        }
        op.cmd;
        JVM INSTR tableswitch 1 9: default 108
    //                   1 136
    //                   2 108
    //                   3 225
    //                   4 282
    //                   5 334
    //                   6 386
    //                   7 442
    //                   8 498
    //                   9 509;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_509;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder("Unknown cmd: ")).append(op.cmd).toString());
_L2:
        int j = op.popExitAnim;
        if (fragment.mAnimationInfo != null || j != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = j;
        }
        mManager.removeFragment(fragment);
_L12:
        if (!mReorderingAllowed && op.cmd != 3 && fragment != null)
        {
            mManager.moveFragmentToExpectedState(fragment);
        }
        i--;
        if (true) goto _L11; else goto _L10
_L10:
        int k = op.popEnterAnim;
        if (fragment.mAnimationInfo != null || k != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = k;
        }
        mManager.addFragment(fragment, false);
          goto _L12
_L4:
        int l = op.popEnterAnim;
        if (fragment.mAnimationInfo != null || l != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = l;
        }
        FragmentManagerImpl.showFragment(fragment);
          goto _L12
_L5:
        int i1 = op.popExitAnim;
        if (fragment.mAnimationInfo != null || i1 != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = i1;
        }
        FragmentManagerImpl.hideFragment(fragment);
          goto _L12
_L6:
        int j1 = op.popEnterAnim;
        if (fragment.mAnimationInfo != null || j1 != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = j1;
        }
        mManager.attachFragment(fragment);
          goto _L12
_L7:
        int k1 = op.popExitAnim;
        if (fragment.mAnimationInfo != null || k1 != 0)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mNextAnim = k1;
        }
        mManager.detachFragment(fragment);
          goto _L12
_L8:
        mManager.setPrimaryNavigationFragment(null);
          goto _L12
        mManager.setPrimaryNavigationFragment(fragment);
          goto _L12
        if (!mReorderingAllowed && flag)
        {
            mManager.moveToState(mManager.mCurState, true);
        }
        return;
    }

    public final boolean generateOps(ArrayList arraylist, ArrayList arraylist1)
    {
        arraylist.add(this);
        arraylist1.add(Boolean.valueOf(false));
        if (mAddToBackStack)
        {
            arraylist = mManager;
            if (((FragmentManagerImpl) (arraylist)).mBackStack == null)
            {
                arraylist.mBackStack = new ArrayList();
            }
            ((FragmentManagerImpl) (arraylist)).mBackStack.add(this);
        }
        return true;
    }

    public final String getName()
    {
        return mName;
    }

    final boolean interactsWith(int i)
    {
        int l = mOps.size();
        int k;
        for (int j = 0; j < l; j++)
        {
            Op op = (Op)mOps.get(j);
            if (op.fragment != null)
            {
                k = op.fragment.mContainerId;
            } else
            {
                k = 0;
            }
            if (k != 0 && k == i)
            {
                return true;
            }
        }

        return false;
    }

    final boolean interactsWith(ArrayList arraylist, int i, int j)
    {
        if (j == i)
        {
            return false;
        }
        int l1 = mOps.size();
        int l = -1;
        int i1 = 0;
        while (i1 < l1) 
        {
            Op op = (Op)mOps.get(i1);
            int k;
            if (op.fragment != null)
            {
                k = op.fragment.mContainerId;
            } else
            {
                k = 0;
            }
            if (k != 0 && k != l)
            {
                for (l = i; l < j; l++)
                {
                    BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(l);
                    int i2 = backstackrecord.mOps.size();
                    int k1;
                    for (int j1 = 0; j1 < i2; j1++)
                    {
                        Op op1 = (Op)backstackrecord.mOps.get(j1);
                        if (op1.fragment != null)
                        {
                            k1 = op1.fragment.mContainerId;
                        } else
                        {
                            k1 = 0;
                        }
                        if (k1 == k)
                        {
                            return true;
                        }
                    }

                }

            } else
            {
                k = l;
            }
            i1++;
            l = k;
        }
        return false;
    }

    public final FragmentTransaction remove(Fragment fragment)
    {
        addOp(new Op(3, fragment));
        return this;
    }

    public final FragmentTransaction replace(int i, Fragment fragment)
    {
        return replace(i, fragment, null);
    }

    public final FragmentTransaction replace(int i, Fragment fragment, String s)
    {
        if (i == 0)
        {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        } else
        {
            doAddOp(i, fragment, s, 2);
            return this;
        }
    }

    public final FragmentTransaction setCustomAnimations(int i, int j, int k, int l)
    {
        mEnterAnim = i;
        mExitAnim = j;
        mPopEnterAnim = k;
        mPopExitAnim = l;
        return this;
    }

    public final FragmentTransaction setTransition(int i)
    {
        mTransition = 4099;
        return this;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("BackStackEntry{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if (mIndex >= 0)
        {
            stringbuilder.append(" #");
            stringbuilder.append(mIndex);
        }
        if (mName != null)
        {
            stringbuilder.append(" ");
            stringbuilder.append(mName);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private class Op
    {

        public int cmd;
        public int enterAnim;
        public int exitAnim;
        public Fragment fragment;
        public int popEnterAnim;
        public int popExitAnim;

        Op()
        {
        }

        Op(int i, Fragment fragment1)
        {
            cmd = i;
            fragment = fragment1;
        }
    }

}
