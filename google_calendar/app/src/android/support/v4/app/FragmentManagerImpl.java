// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelStore;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.util.ArraySet;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentManager, Fragment, FragmentHostCallback, BackStackRecord, 
//            FragmentTransition, FragmentManagerNonConfig, SuperNotCalledException, FragmentContainer, 
//            LoaderManagerImpl, LoaderManager, FragmentManagerState, FragmentState, 
//            BackStackState, FragmentTransaction

final class FragmentManagerImpl extends FragmentManager
    implements android.view.LayoutInflater.Factory2
{

    public static boolean DEBUG = false;
    private static final Interpolator DECELERATE_CUBIC = new DecelerateInterpolator(1.5F);
    private static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5F);
    private static Field sAnimationListenerField = null;
    public SparseArray mActive;
    public final ArrayList mAdded = new ArrayList();
    private ArrayList mAvailBackStackIndices;
    public ArrayList mBackStack;
    private ArrayList mBackStackChangeListeners;
    private ArrayList mBackStackIndices;
    public FragmentContainer mContainer;
    private ArrayList mCreatedMenus;
    public int mCurState;
    public boolean mDestroyed;
    private Runnable mExecCommit;
    private boolean mExecutingActions;
    private boolean mHavePendingDeferredStart;
    public FragmentHostCallback mHost;
    private final CopyOnWriteArrayList mLifecycleCallbacks = new CopyOnWriteArrayList();
    private boolean mNeedMenuInvalidate;
    private int mNextFragmentIndex;
    public Fragment mParent;
    private ArrayList mPendingActions;
    private ArrayList mPostponedTransactions;
    public Fragment mPrimaryNav;
    public FragmentManagerNonConfig mSavedNonConfig;
    private SparseArray mStateArray;
    private Bundle mStateBundle;
    public boolean mStateSaved;
    public boolean mStopped;
    private ArrayList mTmpAddedFragments;
    private ArrayList mTmpIsPop;
    private ArrayList mTmpRecords;

    FragmentManagerImpl()
    {
        mNextFragmentIndex = 0;
        mCurState = 0;
        mStateBundle = null;
        mStateArray = null;
        mExecCommit = new _cls1();
    }

    private final void addAddedFragments(ArraySet arrayset)
    {
        if (mCurState > 0)
        {
            int l = Math.min(mCurState, 4);
            int i1 = mAdded.size();
            int i = 0;
            while (i < i1) 
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment.mState < l)
                {
                    int j;
                    int k;
                    if (fragment.mAnimationInfo == null)
                    {
                        j = 0;
                    } else
                    {
                        j = fragment.mAnimationInfo.mNextAnim;
                    }
                    if (fragment.mAnimationInfo == null)
                    {
                        k = 0;
                    } else
                    {
                        k = fragment.mAnimationInfo.mNextTransition;
                    }
                    moveToState(fragment, l, j, k, false);
                    if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded)
                    {
                        arrayset.add(fragment);
                    }
                }
                i++;
            }
        }
    }

    private final void burpActive()
    {
        if (mActive != null)
        {
            for (int i = mActive.size() - 1; i >= 0; i--)
            {
                if (mActive.valueAt(i) == null)
                {
                    mActive.delete(mActive.keyAt(i));
                }
            }

        }
    }

    private final void dispatchOnFragmentActivityCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentActivityCreated(fragment, bundle, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            bundle = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || ((FragmentLifecycleCallbacksHolder) (bundle)).mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentAttached(Fragment fragment, Context context, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentAttached(fragment, context, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            context = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || ((FragmentLifecycleCallbacksHolder) (context)).mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentCreated(fragment, bundle, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            bundle = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || ((FragmentLifecycleCallbacksHolder) (bundle)).mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentDestroyed(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentDestroyed(fragment, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            FragmentLifecycleCallbacksHolder fragmentlifecyclecallbacksholder = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || fragmentlifecyclecallbacksholder.mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentDetached(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentDetached(fragment, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            FragmentLifecycleCallbacksHolder fragmentlifecyclecallbacksholder = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || fragmentlifecyclecallbacksholder.mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentPaused(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentPaused(fragment, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            FragmentLifecycleCallbacksHolder fragmentlifecyclecallbacksholder = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || fragmentlifecyclecallbacksholder.mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentPreAttached(Fragment fragment, Context context, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentPreAttached(fragment, context, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            context = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || ((FragmentLifecycleCallbacksHolder) (context)).mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentPreCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentPreCreated(fragment, bundle, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            bundle = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || ((FragmentLifecycleCallbacksHolder) (bundle)).mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentResumed(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentResumed(fragment, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            FragmentLifecycleCallbacksHolder fragmentlifecyclecallbacksholder = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || fragmentlifecyclecallbacksholder.mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentSaveInstanceState(Fragment fragment, Bundle bundle, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentSaveInstanceState(fragment, bundle, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            bundle = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || ((FragmentLifecycleCallbacksHolder) (bundle)).mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentStarted(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentStarted(fragment, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            FragmentLifecycleCallbacksHolder fragmentlifecyclecallbacksholder = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || fragmentlifecyclecallbacksholder.mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentStopped(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentStopped(fragment, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            FragmentLifecycleCallbacksHolder fragmentlifecyclecallbacksholder = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || fragmentlifecyclecallbacksholder.mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentViewCreated(Fragment fragment, View view, Bundle bundle, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentViewCreated(fragment, view, bundle, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            view = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || ((FragmentLifecycleCallbacksHolder) (view)).mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void dispatchOnFragmentViewDestroyed(Fragment fragment, boolean flag)
    {
        if (mParent != null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mParent.mFragmentManager;
            if (fragmentmanagerimpl instanceof FragmentManagerImpl)
            {
                ((FragmentManagerImpl)fragmentmanagerimpl).dispatchOnFragmentViewDestroyed(fragment, true);
            }
        }
        for (fragment = mLifecycleCallbacks.iterator(); fragment.hasNext();)
        {
            FragmentLifecycleCallbacksHolder fragmentlifecyclecallbacksholder = (FragmentLifecycleCallbacksHolder)fragment.next();
            if (!flag || fragmentlifecyclecallbacksholder.mRecursive)
            {
                throw new NoSuchMethodError();
            }
        }

    }

    private final void ensureExecReady(boolean flag)
    {
        if (mExecutingActions)
        {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (mHost == null)
        {
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        if (Looper.myLooper() != mHost.mHandler.getLooper())
        {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!flag && isStateSaved())
        {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (mTmpRecords == null)
        {
            mTmpRecords = new ArrayList();
            mTmpIsPop = new ArrayList();
        }
        mExecutingActions = true;
        executePostponedTransaction(null, null);
        mExecutingActions = false;
        return;
        Exception exception;
        exception;
        mExecutingActions = false;
        throw exception;
    }

    private final void executeOpsTogether(ArrayList arraylist, ArrayList arraylist1, int i, int j)
    {
        Object obj;
        Object obj1;
        BackStackRecord backstackrecord;
        ArrayList arraylist2;
        BackStackRecord.Op op1;
        int k;
        boolean flag1;
        int i1;
        boolean flag4;
        flag4 = ((BackStackRecord)arraylist.get(i)).mReorderingAllowed;
        if (mTmpAddedFragments == null)
        {
            mTmpAddedFragments = new ArrayList();
        } else
        {
            mTmpAddedFragments.clear();
        }
        mTmpAddedFragments.addAll(mAdded);
        obj = mPrimaryNav;
        i1 = i;
        flag1 = false;
_L20:
        if (i1 >= j) goto _L2; else goto _L1
_L1:
        backstackrecord = (BackStackRecord)arraylist.get(i1);
        if (((Boolean)arraylist1.get(i1)).booleanValue())
        {
            break MISSING_BLOCK_LABEL_551;
        }
        arraylist2 = mTmpAddedFragments;
        k = 0;
_L9:
        obj1 = obj;
        if (k >= backstackrecord.mOps.size())
        {
            break MISSING_BLOCK_LABEL_721;
        }
        op1 = (BackStackRecord.Op)backstackrecord.mOps.get(k);
        op1.cmd;
        JVM INSTR tableswitch 1 8: default 180
    //                   1 199
    //                   2 270
    //                   3 213
    //                   4 180
    //                   5 180
    //                   6 213
    //                   7 199
    //                   8 514;
           goto _L3 _L4 _L5 _L6 _L3 _L3 _L6 _L4 _L7
_L7:
        break MISSING_BLOCK_LABEL_514;
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        break; /* Loop/switch isn't completed */
_L10:
        k++;
        if (true) goto _L9; else goto _L8
_L8:
        arraylist2.add(op1.fragment);
          goto _L10
_L6:
        arraylist2.remove(op1.fragment);
        if (op1.fragment == obj)
        {
            backstackrecord.mOps.add(k, new BackStackRecord.Op(9, op1.fragment));
            obj = null;
            k++;
        }
          goto _L10
_L5:
        Fragment fragment1 = op1.fragment;
        int i2 = fragment1.mContainerId;
        boolean flag = false;
        int k1 = arraylist2.size() - 1;
        while (k1 >= 0) 
        {
            Fragment fragment2 = (Fragment)arraylist2.get(k1);
            if (fragment2.mContainerId == i2)
            {
                if (fragment2 == fragment1)
                {
                    flag = true;
                } else
                {
                    int l1 = k;
                    obj1 = obj;
                    if (fragment2 == obj)
                    {
                        backstackrecord.mOps.add(k, new BackStackRecord.Op(9, fragment2));
                        l1 = k + 1;
                        obj1 = null;
                    }
                    obj = new BackStackRecord.Op(3, fragment2);
                    obj.enterAnim = op1.enterAnim;
                    obj.popEnterAnim = op1.popEnterAnim;
                    obj.exitAnim = op1.exitAnim;
                    obj.popExitAnim = op1.popExitAnim;
                    backstackrecord.mOps.add(l1, obj);
                    arraylist2.remove(fragment2);
                    k = l1 + 1;
                    obj = obj1;
                }
            }
            k1--;
        }
        if (flag)
        {
            backstackrecord.mOps.remove(k);
            k--;
        } else
        {
            op1.cmd = 1;
            arraylist2.add(fragment1);
        }
          goto _L10
        backstackrecord.mOps.add(k, new BackStackRecord.Op(9, ((Fragment) (obj))));
        obj = op1.fragment;
        k++;
          goto _L10
        arraylist2 = mTmpAddedFragments;
        k = 0;
_L18:
        obj1 = obj;
        if (k >= backstackrecord.mOps.size())
        {
            break MISSING_BLOCK_LABEL_721;
        }
        op1 = (BackStackRecord.Op)backstackrecord.mOps.get(k);
        obj1 = obj;
        op1.cmd;
        JVM INSTR tableswitch 1 9: default 652
    //                   1 669
    //                   2 656
    //                   3 687
    //                   4 656
    //                   5 656
    //                   6 687
    //                   7 669
    //                   8 715
    //                   9 705;
           goto _L11 _L12 _L13 _L14 _L13 _L13 _L14 _L12 _L15 _L16
_L15:
        break MISSING_BLOCK_LABEL_715;
_L12:
        break; /* Loop/switch isn't completed */
_L13:
        break; /* Loop/switch isn't completed */
_L11:
        obj1 = obj;
_L19:
        k++;
        obj = obj1;
        if (true) goto _L18; else goto _L17
_L17:
        arraylist2.remove(op1.fragment);
        obj1 = obj;
          goto _L19
_L14:
        arraylist2.add(op1.fragment);
        obj1 = obj;
          goto _L19
_L16:
        obj1 = op1.fragment;
          goto _L19
        obj1 = null;
          goto _L19
        if (flag1 || backstackrecord.mAddToBackStack)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        i1++;
        obj = obj1;
        flag1 = k;
          goto _L20
_L2:
        mTmpAddedFragments.clear();
        if (!flag4)
        {
            FragmentTransition.startTransitions(this, arraylist, arraylist1, i, j, false);
        }
        k = i;
        while (k < j) 
        {
            obj = (BackStackRecord)arraylist.get(k);
            if (((Boolean)arraylist1.get(k)).booleanValue())
            {
                ((BackStackRecord) (obj)).bumpBackStackNesting(-1);
                boolean flag2;
                if (k == j - 1)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                ((BackStackRecord) (obj)).executePopOps(flag2);
            } else
            {
                ((BackStackRecord) (obj)).bumpBackStackNesting(1);
                ((BackStackRecord) (obj)).executeOps();
            }
            k++;
        }
        if (!flag4) goto _L22; else goto _L21
_L21:
        int l;
        obj = new ArraySet();
        addAddedFragments(((ArraySet) (obj)));
        l = j - 1;
        k = j;
_L29:
        if (l < i) goto _L24; else goto _L23
_L23:
        boolean flag3;
        obj1 = (BackStackRecord)arraylist.get(l);
        flag3 = ((Boolean)arraylist1.get(l)).booleanValue();
        i1 = 0;
_L27:
        if (i1 >= ((BackStackRecord) (obj1)).mOps.size())
        {
            break MISSING_BLOCK_LABEL_1107;
        }
        if (!BackStackRecord.isFragmentPostponed((BackStackRecord.Op)((BackStackRecord) (obj1)).mOps.get(i1))) goto _L26; else goto _L25
_L25:
        i1 = 1;
_L28:
        StartEnterTransitionListener startentertransitionlistener;
        if (i1 != 0 && !((BackStackRecord) (obj1)).interactsWith(arraylist, l + 1, j))
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mPostponedTransactions == null)
        {
            mPostponedTransactions = new ArrayList();
        }
        startentertransitionlistener = new StartEnterTransitionListener(((BackStackRecord) (obj1)), flag3);
        mPostponedTransactions.add(startentertransitionlistener);
        for (i1 = 0; i1 < ((BackStackRecord) (obj1)).mOps.size(); i1++)
        {
            BackStackRecord.Op op = (BackStackRecord.Op)((BackStackRecord) (obj1)).mOps.get(i1);
            if (BackStackRecord.isFragmentPostponed(op))
            {
                op.fragment.setOnStartEnterTransitionListener(startentertransitionlistener);
            }
        }

        break MISSING_BLOCK_LABEL_1119;
_L26:
        i1++;
          goto _L27
        i1 = 0;
          goto _L28
        if (flag3)
        {
            ((BackStackRecord) (obj1)).executeOps();
        } else
        {
            ((BackStackRecord) (obj1)).executePopOps(false);
        }
        k--;
        if (l != k)
        {
            arraylist.remove(l);
            arraylist.add(k, obj1);
        }
        addAddedFragments(((ArraySet) (obj)));
        l--;
          goto _L29
_L24:
        int j1 = ((ArraySet) (obj)).size();
        for (l = 0; l < j1; l++)
        {
            Fragment fragment = (Fragment)((ArraySet) (obj)).mArray[l];
            if (!fragment.mAdded)
            {
                View view = fragment.mView;
                fragment.mPostponedAlpha = view.getAlpha();
                view.setAlpha(0.0F);
            }
        }

        l = k;
_L33:
        k = i;
        if (l != i)
        {
            k = i;
            if (flag4)
            {
                FragmentTransition.startTransitions(this, arraylist, arraylist1, i, l, true);
                moveToState(mCurState, true);
                k = i;
            }
        }
_L31:
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (BackStackRecord)arraylist.get(k);
        if (!((Boolean)arraylist1.get(k)).booleanValue() || ((BackStackRecord) (obj)).mIndex < 0)
        {
            break MISSING_BLOCK_LABEL_1391;
        }
        i = ((BackStackRecord) (obj)).mIndex;
        this;
        JVM INSTR monitorenter ;
        mBackStackIndices.set(i, null);
        if (mAvailBackStackIndices == null)
        {
            mAvailBackStackIndices = new ArrayList();
        }
        mAvailBackStackIndices.add(Integer.valueOf(i));
        this;
        JVM INSTR monitorexit ;
        obj.mIndex = -1;
        k++;
        if (true) goto _L31; else goto _L30
        arraylist;
        this;
        JVM INSTR monitorexit ;
        throw arraylist;
_L30:
        if (flag1 && mBackStackChangeListeners != null)
        {
            for (i = 0; i < mBackStackChangeListeners.size(); i++)
            {
                ((FragmentManager.OnBackStackChangedListener)mBackStackChangeListeners.get(i)).onBackStackChanged();
            }

        }
        return;
_L22:
        l = j;
        if (true) goto _L33; else goto _L32
_L32:
    }

    private final void executePostponedTransaction(ArrayList arraylist, ArrayList arraylist1)
    {
        BackStackRecord backstackrecord;
        Object obj;
        int i;
        int j;
        FragmentManagerImpl fragmentmanagerimpl;
        ArrayList arraylist2;
        int k;
        int i1;
        boolean flag;
        if (mPostponedTransactions == null)
        {
            i = 0;
        } else
        {
            i = mPostponedTransactions.size();
        }
        j = 0;
_L2:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_653;
        }
        obj = (StartEnterTransitionListener)mPostponedTransactions.get(j);
        if (arraylist == null || ((StartEnterTransitionListener) (obj)).mIsBack)
        {
            break; /* Loop/switch isn't completed */
        }
        k = arraylist.indexOf(((StartEnterTransitionListener) (obj)).mRecord);
        if (k == -1 || !((Boolean)arraylist1.get(k)).booleanValue())
        {
            break; /* Loop/switch isn't completed */
        }
        fragmentmanagerimpl = ((StartEnterTransitionListener) (obj)).mRecord.mManager;
        backstackrecord = ((StartEnterTransitionListener) (obj)).mRecord;
        flag = ((StartEnterTransitionListener) (obj)).mIsBack;
        if (flag)
        {
            backstackrecord.executePopOps(false);
        } else
        {
            backstackrecord.executeOps();
        }
        obj = new ArrayList(1);
        arraylist2 = new ArrayList(1);
        ((ArrayList) (obj)).add(backstackrecord);
        arraylist2.add(Boolean.valueOf(flag));
        if (fragmentmanagerimpl.mActive != null)
        {
            i1 = fragmentmanagerimpl.mActive.size();
            for (k = 0; k < i1; k++)
            {
                obj = (Fragment)fragmentmanagerimpl.mActive.valueAt(k);
                if (obj == null || ((Fragment) (obj)).mView == null || !((Fragment) (obj)).mIsNewlyAdded || !backstackrecord.interactsWith(((Fragment) (obj)).mContainerId))
                {
                    continue;
                }
                if (((Fragment) (obj)).mPostponedAlpha > 0.0F)
                {
                    ((Fragment) (obj)).mView.setAlpha(((Fragment) (obj)).mPostponedAlpha);
                }
                obj.mPostponedAlpha = -1F;
                obj.mIsNewlyAdded = false;
            }

        }
_L3:
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        int l;
        int j1;
label0:
        {
            FragmentManagerImpl fragmentmanagerimpl1;
            BackStackRecord backstackrecord1;
            ArrayList arraylist3;
            boolean flag1;
            if (((StartEnterTransitionListener) (obj)).mNumPostponed == 0)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            if (l == 0)
            {
                j1 = j;
                l = i;
                if (arraylist == null)
                {
                    break MISSING_BLOCK_LABEL_642;
                }
                j1 = j;
                l = i;
                if (!((StartEnterTransitionListener) (obj)).mRecord.interactsWith(arraylist, 0, arraylist.size()))
                {
                    break MISSING_BLOCK_LABEL_642;
                }
            }
            mPostponedTransactions.remove(j);
            j1 = j - 1;
            l = i - 1;
            if (arraylist == null || ((StartEnterTransitionListener) (obj)).mIsBack)
            {
                break label0;
            }
            i = arraylist.indexOf(((StartEnterTransitionListener) (obj)).mRecord);
            if (i == -1 || !((Boolean)arraylist1.get(i)).booleanValue())
            {
                break label0;
            }
            fragmentmanagerimpl1 = ((StartEnterTransitionListener) (obj)).mRecord.mManager;
            backstackrecord1 = ((StartEnterTransitionListener) (obj)).mRecord;
            flag1 = ((StartEnterTransitionListener) (obj)).mIsBack;
            if (flag1)
            {
                backstackrecord1.executePopOps(false);
            } else
            {
                backstackrecord1.executeOps();
            }
            obj = new ArrayList(1);
            arraylist3 = new ArrayList(1);
            ((ArrayList) (obj)).add(backstackrecord1);
            arraylist3.add(Boolean.valueOf(flag1));
            if (fragmentmanagerimpl1.mActive != null)
            {
                j = fragmentmanagerimpl1.mActive.size();
                for (i = 0; i < j; i++)
                {
                    obj = (Fragment)fragmentmanagerimpl1.mActive.valueAt(i);
                    if (obj == null || ((Fragment) (obj)).mView == null || !((Fragment) (obj)).mIsNewlyAdded || !backstackrecord1.interactsWith(((Fragment) (obj)).mContainerId))
                    {
                        continue;
                    }
                    if (((Fragment) (obj)).mPostponedAlpha > 0.0F)
                    {
                        ((Fragment) (obj)).mView.setAlpha(((Fragment) (obj)).mPostponedAlpha);
                    }
                    obj.mPostponedAlpha = -1F;
                    obj.mIsNewlyAdded = false;
                }

            }
            j = j1;
            i = l;
        }
          goto _L3
        ((StartEnterTransitionListener) (obj)).completeTransaction();
        j = j1;
        i = l;
          goto _L3
    }

    private final void forcePostponedTransactions()
    {
        if (mPostponedTransactions != null)
        {
            for (; !mPostponedTransactions.isEmpty(); ((StartEnterTransitionListener)mPostponedTransactions.remove(0)).completeTransaction()) { }
        }
    }

    private final boolean generateOpsForPendingActions(ArrayList arraylist, ArrayList arraylist1)
    {
        this;
        JVM INSTR monitorenter ;
        if (mPendingActions != null && mPendingActions.size() != 0)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        this;
        JVM INSTR monitorexit ;
        return false;
        int j = mPendingActions.size();
        int i;
        boolean flag;
        i = 0;
        flag = false;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        flag |= ((OpGenerator)mPendingActions.get(i)).generateOps(arraylist, arraylist1);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        mPendingActions.clear();
        mHost.mHandler.removeCallbacks(mExecCommit);
        this;
        JVM INSTR monitorexit ;
        return flag;
        arraylist;
        this;
        JVM INSTR monitorexit ;
        throw arraylist;
    }

    private static android.view.animation.Animation.AnimationListener getAnimationListener(Animation animation)
    {
        try
        {
            if (sAnimationListenerField == null)
            {
                Field field = android/view/animation/Animation.getDeclaredField("mListener");
                sAnimationListenerField = field;
                field.setAccessible(true);
            }
            animation = (android.view.animation.Animation.AnimationListener)sAnimationListenerField.get(animation);
        }
        // Misplaced declaration of an exception variable
        catch (Animation animation)
        {
            Log.e("FragmentManager", "No field with the name mListener is found in Animation class", animation);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch (Animation animation)
        {
            Log.e("FragmentManager", "Cannot access Animation's mListener field", animation);
            return null;
        }
        return animation;
    }

    private final Fragment getFragment(Bundle bundle, String s)
    {
        int i = bundle.getInt(s, -1);
        if (i == -1)
        {
            bundle = null;
        } else
        {
            Fragment fragment = (Fragment)mActive.get(i);
            bundle = fragment;
            if (fragment == null)
            {
                throwException(new IllegalStateException((new StringBuilder("Fragment no longer exists for key ")).append(s).append(": index ").append(i).toString()));
                return fragment;
            }
        }
        return bundle;
    }

    public static void hideFragment(Fragment fragment)
    {
        boolean flag = true;
        if (!fragment.mHidden)
        {
            fragment.mHidden = true;
            if (fragment.mHiddenChanged)
            {
                flag = false;
            }
            fragment.mHiddenChanged = flag;
        }
    }

    private final AnimationOrAnimator loadAnimation(Fragment fragment, int i, boolean flag, int j)
    {
        int k;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        flag2 = false;
        if (fragment.mAnimationInfo == null)
        {
            k = 0;
        } else
        {
            k = fragment.mAnimationInfo.mNextAnim;
        }
        Fragment.onCreateAnimation$514LKI999HGMSP3IDTKM8BRMD5INEBR1DPKMQOBKD5NMSBQ1DPKMQOBKD5NMSEO_0();
        Fragment.onCreateAnimator$514LKI999HGMSP3IDTKM8BR1DPKMQOBKD5NMSBQ1DPKMQOBKDTP3M___0();
        if (k == 0) goto _L2; else goto _L1
_L1:
        flag3 = "anim".equals(mHost.mContext.getResources().getResourceTypeName(k));
        flag1 = flag2;
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        fragment = AnimationUtils.loadAnimation(mHost.mContext, k);
        if (fragment == null)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        fragment = new AnimationOrAnimator(fragment);
        return fragment;
        flag1 = true;
_L12:
        if (flag1) goto _L2; else goto _L3
_L3:
        fragment = AnimatorInflater.loadAnimator(mHost.mContext, k);
        if (fragment == null) goto _L2; else goto _L4
_L4:
        fragment = new AnimationOrAnimator(fragment);
        return fragment;
        fragment;
        throw fragment;
        fragment;
        if (flag3)
        {
            throw fragment;
        }
        fragment = AnimationUtils.loadAnimation(mHost.mContext, k);
        if (fragment != null)
        {
            return new AnimationOrAnimator(fragment);
        }
_L2:
        if (i == 0)
        {
            return null;
        }
        k = -1;
        i;
        JVM INSTR lookupswitch 3: default 216
    //                   4097: 225
    //                   4099: 253
    //                   8194: 239;
           goto _L5 _L6 _L7 _L8
_L5:
        i = k;
_L10:
        if (i < 0)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L6:
        if (flag)
        {
            i = 1;
        } else
        {
            i = 2;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if (flag)
        {
            i = 3;
        } else
        {
            i = 4;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (flag)
        {
            i = 5;
        } else
        {
            i = 6;
        }
        if (true) goto _L10; else goto _L9
_L9:
        switch (i)
        {
        default:
            i = j;
            if (j == 0)
            {
                i = j;
                if (mHost.onHasWindowAnimations())
                {
                    i = mHost.onGetWindowAnimations();
                }
            }
            if (i == 0)
            {
                return null;
            } else
            {
                return null;
            }

        case 1: // '\001'
            return makeOpenCloseAnimation$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D34CHI655662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78JB1DPGMEPBI95MN0R1485N6IRB1EHKMURIFE90MSQBDC5Q6USHR0(1.125F, 1.0F, 0.0F, 1.0F);

        case 2: // '\002'
            return makeOpenCloseAnimation$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D34CHI655662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78JB1DPGMEPBI95MN0R1485N6IRB1EHKMURIFE90MSQBDC5Q6USHR0(1.0F, 0.975F, 1.0F, 0.0F);

        case 3: // '\003'
            return makeOpenCloseAnimation$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D34CHI655662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78JB1DPGMEPBI95MN0R1485N6IRB1EHKMURIFE90MSQBDC5Q6USHR0(0.975F, 1.0F, 0.0F, 1.0F);

        case 4: // '\004'
            return makeOpenCloseAnimation$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D34CHI655662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78JB1DPGMEPBI95MN0R1485N6IRB1EHKMURIFE90MSQBDC5Q6USHR0(1.0F, 1.075F, 1.0F, 0.0F);

        case 5: // '\005'
            return makeFadeAnimation$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D34CAACC5N68SJFD5I2USRLE1O6USJK5TR38BR1E1O2UHJIC5JMQPBEEH6M2RJ1CTIN4IBDE1M28GBED5MM2T39DTN4USI1DPKMQOBKDTP3M___0(0.0F, 1.0F);

        case 6: // '\006'
            return makeFadeAnimation$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D34CAACC5N68SJFD5I2USRLE1O6USJK5TR38BR1E1O2UHJIC5JMQPBEEH6M2RJ1CTIN4IBDE1M28GBED5MM2T39DTN4USI1DPKMQOBKDTP3M___0(1.0F, 0.0F);
        }
        fragment;
        flag1 = flag2;
        if (true) goto _L12; else goto _L11
_L11:
    }

    private static AnimationOrAnimator makeFadeAnimation$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D34CAACC5N68SJFD5I2USRLE1O6USJK5TR38BR1E1O2UHJIC5JMQPBEEH6M2RJ1CTIN4IBDE1M28GBED5MM2T39DTN4USI1DPKMQOBKDTP3M___0(float f, float f1)
    {
        AlphaAnimation alphaanimation = new AlphaAnimation(f, f1);
        alphaanimation.setInterpolator(DECELERATE_CUBIC);
        alphaanimation.setDuration(220L);
        return new AnimationOrAnimator(alphaanimation);
    }

    private static AnimationOrAnimator makeOpenCloseAnimation$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D34CHI655662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78JB1DPGMEPBI95MN0R1485N6IRB1EHKMURIFE90MSQBDC5Q6USHR0(float f, float f1, float f2, float f3)
    {
        AnimationSet animationset = new AnimationSet(false);
        Object obj = new ScaleAnimation(f, f1, f, f1, 1, 0.5F, 1, 0.5F);
        ((ScaleAnimation) (obj)).setInterpolator(DECELERATE_QUINT);
        ((ScaleAnimation) (obj)).setDuration(220L);
        animationset.addAnimation(((Animation) (obj)));
        obj = new AlphaAnimation(f2, f3);
        ((AlphaAnimation) (obj)).setInterpolator(DECELERATE_CUBIC);
        ((AlphaAnimation) (obj)).setDuration(220L);
        animationset.addAnimation(((Animation) (obj)));
        return new AnimationOrAnimator(animationset);
    }

    private static boolean modifiesAlpha(Animator animator)
    {
        if (animator != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if (!(animator instanceof ValueAnimator))
        {
            break; /* Loop/switch isn't completed */
        }
        animator = ((ValueAnimator)animator).getValues();
        int i = 0;
        while (i < animator.length) 
        {
            if ("alpha".equals(animator[i].getPropertyName()))
            {
                return true;
            }
            i++;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (animator instanceof AnimatorSet)
        {
            animator = ((AnimatorSet)animator).getChildAnimations();
            int j = 0;
            while (j < animator.size()) 
            {
                if (modifiesAlpha((Animator)animator.get(j)))
                {
                    return true;
                }
                j++;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    private final boolean popBackStackImmediate(String s, int i, int j)
    {
        boolean flag;
        execPendingActions();
        ensureExecReady(true);
        if (mPrimaryNav != null && s == null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mPrimaryNav.mChildFragmentManager;
            if (fragmentmanagerimpl != null && fragmentmanagerimpl.popBackStackImmediate())
            {
                return true;
            }
        }
        flag = popBackStackState(mTmpRecords, mTmpIsPop, s, -1, j);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        mExecutingActions = true;
        removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
        mExecutingActions = false;
        mTmpIsPop.clear();
        mTmpRecords.clear();
        if (mHavePendingDeferredStart)
        {
            mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
        burpActive();
        return flag;
        s;
        mExecutingActions = false;
        mTmpIsPop.clear();
        mTmpRecords.clear();
        throw s;
    }

    private final void removeRedundantOperationsAndExecute(ArrayList arraylist, ArrayList arraylist1)
    {
        int i = 0;
        if (arraylist != null && !arraylist.isEmpty())
        {
            if (arraylist1 == null || arraylist.size() != arraylist1.size())
            {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            executePostponedTransaction(arraylist, arraylist1);
            int l = arraylist.size();
            int j = 0;
            for (; i < l; i++)
            {
                if (((BackStackRecord)arraylist.get(i)).mReorderingAllowed)
                {
                    continue;
                }
                if (j != i)
                {
                    executeOpsTogether(arraylist, arraylist1, j, i);
                }
                int k = i + 1;
                j = k;
                if (((Boolean)arraylist1.get(i)).booleanValue())
                {
                    do
                    {
                        j = k;
                        if (k >= l)
                        {
                            break;
                        }
                        j = k;
                        if (!((Boolean)arraylist1.get(k)).booleanValue())
                        {
                            break;
                        }
                        j = k;
                        if (((BackStackRecord)arraylist.get(k)).mReorderingAllowed)
                        {
                            break;
                        }
                        k++;
                    } while (true);
                }
                executeOpsTogether(arraylist, arraylist1, i, j);
                i = j;
                k = j - 1;
                j = i;
                i = k;
            }

            if (j != l)
            {
                executeOpsTogether(arraylist, arraylist1, j, l);
                return;
            }
        }
    }

    public static int reverseTransit(int i)
    {
        switch (i)
        {
        default:
            return 0;

        case 4097: 
            return 8194;

        case 8194: 
            return 4097;

        case 4099: 
            return 4099;
        }
    }

    private final void saveFragmentViewState(Fragment fragment)
    {
        if (fragment.mInnerView != null)
        {
            if (mStateArray == null)
            {
                mStateArray = new SparseArray();
            } else
            {
                mStateArray.clear();
            }
            fragment.mInnerView.saveHierarchyState(mStateArray);
            if (mStateArray.size() > 0)
            {
                fragment.mSavedViewState = mStateArray;
                mStateArray = null;
                return;
            }
        }
    }

    private final void saveNonConfig()
    {
        ArrayList arraylist2;
        Object obj1;
        ArrayList arraylist3;
        if (mActive != null)
        {
            int i = 0;
            Object obj = null;
            ArrayList arraylist1 = null;
            ArrayList arraylist = null;
            do
            {
                arraylist3 = ((ArrayList) (obj));
                obj1 = arraylist1;
                arraylist2 = arraylist;
                if (i >= mActive.size())
                {
                    break;
                }
                Fragment fragment = (Fragment)mActive.valueAt(i);
                obj1 = obj;
                arraylist3 = arraylist1;
                ArrayList arraylist4 = arraylist;
                if (fragment != null)
                {
                    arraylist2 = arraylist;
                    if (fragment.mRetainInstance)
                    {
                        arraylist2 = arraylist;
                        if (arraylist == null)
                        {
                            arraylist2 = new ArrayList();
                        }
                        arraylist2.add(fragment);
                        int j;
                        if (fragment.mTarget != null)
                        {
                            j = fragment.mTarget.mIndex;
                        } else
                        {
                            j = -1;
                        }
                        fragment.mTargetIndex = j;
                    }
                    if (fragment.mChildFragmentManager != null)
                    {
                        fragment.mChildFragmentManager.saveNonConfig();
                        obj1 = fragment.mChildFragmentManager.mSavedNonConfig;
                    } else
                    {
                        obj1 = fragment.mChildNonConfig;
                    }
                    arraylist = arraylist1;
                    if (arraylist1 == null)
                    {
                        arraylist = arraylist1;
                        if (obj1 != null)
                        {
                            arraylist1 = new ArrayList(mActive.size());
                            j = 0;
                            do
                            {
                                arraylist = arraylist1;
                                if (j >= i)
                                {
                                    break;
                                }
                                arraylist1.add(null);
                                j++;
                            } while (true);
                        }
                    }
                    if (arraylist != null)
                    {
                        arraylist.add(obj1);
                    }
                    arraylist1 = ((ArrayList) (obj));
                    if (obj == null)
                    {
                        arraylist1 = ((ArrayList) (obj));
                        if (fragment.mViewModelStore != null)
                        {
                            obj = new ArrayList(mActive.size());
                            int k = 0;
                            do
                            {
                                arraylist1 = ((ArrayList) (obj));
                                if (k >= i)
                                {
                                    break;
                                }
                                ((ArrayList) (obj)).add(null);
                                k++;
                            } while (true);
                        }
                    }
                    obj1 = arraylist1;
                    arraylist3 = arraylist;
                    arraylist4 = arraylist2;
                    if (arraylist1 != null)
                    {
                        arraylist1.add(fragment.mViewModelStore);
                        arraylist4 = arraylist2;
                        arraylist3 = arraylist;
                        obj1 = arraylist1;
                    }
                }
                i++;
                arraylist = arraylist4;
                arraylist1 = arraylist3;
                obj = obj1;
            } while (true);
        } else
        {
            arraylist3 = null;
            obj1 = null;
            arraylist2 = null;
        }
        if (arraylist2 == null && obj1 == null && arraylist3 == null)
        {
            mSavedNonConfig = null;
            return;
        } else
        {
            mSavedNonConfig = new FragmentManagerNonConfig(arraylist2, ((List) (obj1)), arraylist3);
            return;
        }
    }

    private static void setHWLayerAnimListenerIfAlpha(View view, AnimationOrAnimator animationoranimator)
    {
        boolean flag1 = false;
        if (view != null && animationoranimator != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        boolean flag = flag1;
        if (view == null) goto _L4; else goto _L3
_L3:
        if (animationoranimator != null) goto _L6; else goto _L5
_L5:
        flag = flag1;
_L4:
        List list;
        int i;
        boolean flag2;
        if (flag)
        {
            if (animationoranimator.animator != null)
            {
                animationoranimator.animator.addListener(new AnimatorOnHWLayerIfNeededListener(view));
                return;
            } else
            {
                android.view.animation.Animation.AnimationListener animationlistener = getAnimationListener(animationoranimator.animation);
                view.setLayerType(2, null);
                animationoranimator.animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(view, animationlistener));
                return;
            }
        }
        if (true) goto _L1; else goto _L6
_L6:
        flag = flag1;
        if (view.getLayerType() != 0) goto _L4; else goto _L7
_L7:
        flag = flag1;
        if (!ViewCompat.hasOverlappingRendering(view)) goto _L4; else goto _L8
_L8:
        if (!(animationoranimator.animation instanceof AlphaAnimation)) goto _L10; else goto _L9
_L9:
        flag2 = true;
_L11:
        flag = flag1;
        if (flag2)
        {
            flag = true;
        }
          goto _L4
_L10:
        if (!(animationoranimator.animation instanceof AnimationSet))
        {
            break MISSING_BLOCK_LABEL_164;
        }
        list = ((AnimationSet)animationoranimator.animation).getAnimations();
        i = 0;
_L12:
label0:
        {
            if (i >= list.size())
            {
                break MISSING_BLOCK_LABEL_158;
            }
            if (!(list.get(i) instanceof AlphaAnimation))
            {
                break label0;
            }
            flag2 = true;
        }
          goto _L11
        i++;
          goto _L12
        flag2 = false;
          goto _L11
        flag2 = modifiesAlpha(animationoranimator.animator);
          goto _L11
    }

    static void setRetaining(FragmentManagerNonConfig fragmentmanagernonconfig)
    {
        if (fragmentmanagernonconfig != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj = fragmentmanagernonconfig.mFragments;
        if (obj != null)
        {
            for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
            {
                ((Fragment)((Iterator) (obj)).next()).mRetaining = true;
            }

        }
        fragmentmanagernonconfig = fragmentmanagernonconfig.mChildNonConfigs;
        if (fragmentmanagernonconfig != null)
        {
            fragmentmanagernonconfig = fragmentmanagernonconfig.iterator();
            while (fragmentmanagernonconfig.hasNext()) 
            {
                setRetaining((FragmentManagerNonConfig)fragmentmanagernonconfig.next());
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public static void showFragment(Fragment fragment)
    {
        boolean flag = false;
        if (fragment.mHidden)
        {
            fragment.mHidden = false;
            if (!fragment.mHiddenChanged)
            {
                flag = true;
            }
            fragment.mHiddenChanged = flag;
        }
    }

    private final void startPendingDeferredFragments()
    {
        if (mActive != null)
        {
            int i = 0;
            while (i < mActive.size()) 
            {
                Fragment fragment = (Fragment)mActive.valueAt(i);
                if (fragment != null)
                {
                    performPendingDeferredStart(fragment);
                }
                i++;
            }
        }
    }

    private final void throwException(RuntimeException runtimeexception)
    {
        Log.e("FragmentManager", runtimeexception.getMessage());
        Log.e("FragmentManager", "Activity state:");
        Object obj = new PrintWriter(new LogWriter("FragmentManager"));
        if (mHost != null)
        {
            try
            {
                mHost.onDump("  ", null, ((PrintWriter) (obj)), new String[0]);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                Log.e("FragmentManager", "Failed dumping state", ((Throwable) (obj)));
            }
        } else
        {
            try
            {
                dump("  ", null, ((PrintWriter) (obj)), new String[0]);
            }
            catch (Exception exception)
            {
                Log.e("FragmentManager", "Failed dumping state", exception);
            }
        }
        throw runtimeexception;
    }

    public final void addFragment(Fragment fragment, boolean flag)
    {
        makeActive(fragment);
        if (!fragment.mDetached)
        {
            if (mAdded.contains(fragment))
            {
                throw new IllegalStateException((new StringBuilder("Fragment already added: ")).append(fragment).toString());
            }
            synchronized (mAdded)
            {
                mAdded.add(fragment);
            }
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mView == null)
            {
                fragment.mHiddenChanged = false;
            }
            if (fragment.mHasMenu && fragment.mMenuVisible)
            {
                mNeedMenuInvalidate = true;
            }
            if (flag)
            {
                moveToState(fragment, mCurState, 0, 0, false);
            }
        }
        return;
        fragment;
        arraylist;
        JVM INSTR monitorexit ;
        throw fragment;
    }

    public final void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if (mBackStackChangeListeners == null)
        {
            mBackStackChangeListeners = new ArrayList();
        }
        mBackStackChangeListeners.add(onbackstackchangedlistener);
    }

    public final int allocBackStackIndex(BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        if (mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        if (mBackStackIndices == null)
        {
            mBackStackIndices = new ArrayList();
        }
        i = mBackStackIndices.size();
        mBackStackIndices.add(backstackrecord);
        this;
        JVM INSTR monitorexit ;
        return i;
        i = ((Integer)mAvailBackStackIndices.remove(mAvailBackStackIndices.size() - 1)).intValue();
        mBackStackIndices.set(i, backstackrecord);
        this;
        JVM INSTR monitorexit ;
        return i;
        backstackrecord;
        this;
        JVM INSTR monitorexit ;
        throw backstackrecord;
    }

    public final void attachController(FragmentHostCallback fragmenthostcallback, FragmentContainer fragmentcontainer, Fragment fragment)
    {
        if (mHost != null)
        {
            throw new IllegalStateException("Already attached");
        } else
        {
            mHost = fragmenthostcallback;
            mContainer = fragmentcontainer;
            mParent = fragment;
            return;
        }
    }

    public final void attachFragment(Fragment fragment)
    {
        if (fragment.mDetached)
        {
            fragment.mDetached = false;
            if (!fragment.mAdded)
            {
                if (mAdded.contains(fragment))
                {
                    throw new IllegalStateException((new StringBuilder("Fragment already added: ")).append(fragment).toString());
                }
                synchronized (mAdded)
                {
                    mAdded.add(fragment);
                }
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible)
                {
                    mNeedMenuInvalidate = true;
                }
            }
        }
        return;
        fragment;
        arraylist;
        JVM INSTR monitorexit ;
        throw fragment;
    }

    public final FragmentTransaction beginTransaction()
    {
        return new BackStackRecord(this);
    }

    final void completeExecute(BackStackRecord backstackrecord, boolean flag, boolean flag1, boolean flag2)
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        if (flag)
        {
            backstackrecord.executePopOps(flag2);
        } else
        {
            backstackrecord.executeOps();
        }
        arraylist = new ArrayList(1);
        arraylist1 = new ArrayList(1);
        arraylist.add(backstackrecord);
        arraylist1.add(Boolean.valueOf(flag));
        if (flag1)
        {
            FragmentTransition.startTransitions(this, arraylist, arraylist1, 0, 1, true);
        }
        if (flag2)
        {
            moveToState(mCurState, true);
        }
        if (mActive != null)
        {
            int j = mActive.size();
            int i = 0;
            while (i < j) 
            {
                Fragment fragment = (Fragment)mActive.valueAt(i);
                if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && backstackrecord.interactsWith(fragment.mContainerId))
                {
                    if (fragment.mPostponedAlpha > 0.0F)
                    {
                        fragment.mView.setAlpha(fragment.mPostponedAlpha);
                    }
                    if (flag2)
                    {
                        fragment.mPostponedAlpha = 0.0F;
                    } else
                    {
                        fragment.mPostponedAlpha = -1F;
                        fragment.mIsNewlyAdded = false;
                    }
                }
                i++;
            }
        }
    }

    public final void detachFragment(Fragment fragment)
    {
        if (!fragment.mDetached)
        {
            fragment.mDetached = true;
            if (fragment.mAdded)
            {
                synchronized (mAdded)
                {
                    mAdded.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible)
                {
                    mNeedMenuInvalidate = true;
                }
                fragment.mAdded = false;
            }
        }
        return;
        fragment;
        arraylist;
        JVM INSTR monitorexit ;
        throw fragment;
    }

    public final void dispatchConfigurationChanged(Configuration configuration)
    {
        for (int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if (fragment == null)
            {
                continue;
            }
            fragment.onConfigurationChanged(configuration);
            if (fragment.mChildFragmentManager != null)
            {
                fragment.mChildFragmentManager.dispatchConfigurationChanged(configuration);
            }
        }

    }

    public final boolean dispatchContextItemSelected(MenuItem menuitem)
    {
        if (mCurState > 0)
        {
            int i = 0;
            while (i < mAdded.size()) 
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null)
                {
                    boolean flag;
                    if (!fragment.mHidden && fragment.mChildFragmentManager != null && fragment.mChildFragmentManager.dispatchContextItemSelected(menuitem))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        return true;
                    }
                }
                i++;
            }
        }
        return false;
    }

    public final boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        boolean flag1 = false;
        if (mCurState <= 0)
        {
            return false;
        }
        ArrayList arraylist = null;
        int j = 0;
        boolean flag2 = false;
        while (j < mAdded.size()) 
        {
            Fragment fragment = (Fragment)mAdded.get(j);
            if (fragment == null)
            {
                continue;
            }
            boolean flag;
            if (!fragment.mHidden)
            {
                ArrayList arraylist1;
                int i;
                if (fragment.mHasMenu && fragment.mMenuVisible)
                {
                    fragment.onCreateOptionsMenu(menu, menuinflater);
                    i = 1;
                } else
                {
                    i = 0;
                }
                flag = i;
                if (fragment.mChildFragmentManager != null)
                {
                    flag = i | fragment.mChildFragmentManager.dispatchCreateOptionsMenu(menu, menuinflater);
                }
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                continue;
            }
            arraylist1 = arraylist;
            if (arraylist == null)
            {
                arraylist1 = new ArrayList();
            }
            arraylist1.add(fragment);
            flag2 = true;
            arraylist = arraylist1;
            j++;
        }
        if (mCreatedMenus != null)
        {
            for (i = ((flag1) ? 1 : 0); i < mCreatedMenus.size(); i++)
            {
                menu = (Fragment)mCreatedMenus.get(i);
                if (arraylist == null || !arraylist.contains(menu))
                {
                    Fragment.onDestroyOptionsMenu();
                }
            }

        }
        mCreatedMenus = arraylist;
        return flag2;
    }

    public final void dispatchLowMemory()
    {
        for (int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if (fragment == null)
            {
                continue;
            }
            fragment.onLowMemory();
            if (fragment.mChildFragmentManager != null)
            {
                fragment.mChildFragmentManager.dispatchLowMemory();
            }
        }

    }

    public final void dispatchMultiWindowModeChanged(boolean flag)
    {
        for (int i = mAdded.size() - 1; i >= 0; i--)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if (fragment != null && fragment.mChildFragmentManager != null)
            {
                fragment.mChildFragmentManager.dispatchMultiWindowModeChanged(flag);
            }
        }

    }

    public final boolean dispatchOptionsItemSelected(MenuItem menuitem)
    {
        if (mCurState > 0) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int i = 0;
_L11:
        Fragment fragment;
        if (i >= mAdded.size())
        {
            continue; /* Loop/switch isn't completed */
        }
        fragment = (Fragment)mAdded.get(i);
        if (fragment == null) goto _L4; else goto _L3
_L3:
        if (fragment.mHidden) goto _L6; else goto _L5
_L5:
        if (!fragment.mHasMenu || !fragment.mMenuVisible || !fragment.onOptionsItemSelected(menuitem)) goto _L8; else goto _L7
_L7:
        boolean flag = true;
_L9:
        if (flag)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L8:
        if (fragment.mChildFragmentManager != null && fragment.mChildFragmentManager.dispatchOptionsItemSelected(menuitem))
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L6:
        flag = false;
        if (true) goto _L9; else goto _L4
_L4:
        i++;
        if (true) goto _L11; else goto _L10
_L10:
        if (true) goto _L1; else goto _L12
_L12:
    }

    public final void dispatchOptionsMenuClosed(Menu menu)
    {
        if (mCurState > 0)
        {
            int i = 0;
            while (i < mAdded.size()) 
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if (fragment != null && !fragment.mHidden && fragment.mChildFragmentManager != null)
                {
                    fragment.mChildFragmentManager.dispatchOptionsMenuClosed(menu);
                }
                i++;
            }
        }
    }

    public final void dispatchPictureInPictureModeChanged(boolean flag)
    {
        for (int i = mAdded.size() - 1; i >= 0; i--)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if (fragment != null && fragment.mChildFragmentManager != null)
            {
                fragment.mChildFragmentManager.dispatchPictureInPictureModeChanged(flag);
            }
        }

    }

    public final boolean dispatchPrepareOptionsMenu(Menu menu)
    {
        if (mCurState <= 0)
        {
            return false;
        }
        int i = 0;
        boolean flag2 = false;
        while (i < mAdded.size()) 
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            boolean flag3 = flag2;
            if (fragment != null)
            {
                boolean flag1;
                if (!fragment.mHidden)
                {
                    boolean flag;
                    if (fragment.mHasMenu && fragment.mMenuVisible)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    flag1 = flag;
                    if (fragment.mChildFragmentManager != null)
                    {
                        flag1 = flag | fragment.mChildFragmentManager.dispatchPrepareOptionsMenu(menu);
                    }
                } else
                {
                    flag1 = false;
                }
                flag3 = flag2;
                if (flag1)
                {
                    flag3 = true;
                }
            }
            i++;
            flag2 = flag3;
        }
        return flag2;
    }

    final void dispatchStateChange(int i)
    {
        mExecutingActions = true;
        moveToState(i, false);
        mExecutingActions = false;
        execPendingActions();
        return;
        Exception exception;
        exception;
        mExecutingActions = false;
        throw exception;
    }

    public final void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        String s1;
        int l;
        int k2;
        s1 = (new StringBuilder()).append(s).append("    ").toString();
        if (mActive != null)
        {
            int j1 = mActive.size();
            if (j1 > 0)
            {
                printwriter.print(s);
                printwriter.print("Active Fragments in ");
                printwriter.print(Integer.toHexString(System.identityHashCode(this)));
                printwriter.println(":");
                for (int i = 0; i < j1; i++)
                {
                    Fragment fragment = (Fragment)mActive.valueAt(i);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(i);
                    printwriter.print(": ");
                    printwriter.println(fragment);
                    if (fragment != null)
                    {
                        fragment.dump(s1, filedescriptor, printwriter, as);
                    }
                }

            }
        }
        int k1 = mAdded.size();
        if (k1 > 0)
        {
            printwriter.print(s);
            printwriter.println("Added Fragments:");
            for (int j = 0; j < k1; j++)
            {
                filedescriptor = (Fragment)mAdded.get(j);
                printwriter.print(s);
                printwriter.print("  #");
                printwriter.print(j);
                printwriter.print(": ");
                printwriter.println(filedescriptor.toString());
            }

        }
        if (mCreatedMenus != null)
        {
            int l1 = mCreatedMenus.size();
            if (l1 > 0)
            {
                printwriter.print(s);
                printwriter.println("Fragments Created Menus:");
                for (int k = 0; k < l1; k++)
                {
                    filedescriptor = (Fragment)mCreatedMenus.get(k);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(k);
                    printwriter.print(": ");
                    printwriter.println(filedescriptor.toString());
                }

            }
        }
        if (mBackStack == null)
        {
            break MISSING_BLOCK_LABEL_1189;
        }
        k2 = mBackStack.size();
        if (k2 <= 0)
        {
            break MISSING_BLOCK_LABEL_1189;
        }
        printwriter.print(s);
        printwriter.println("Back Stack:");
        l = 0;
_L18:
        if (l >= k2) goto _L2; else goto _L1
_L1:
        int i2;
        int l2;
        as = (BackStackRecord)mBackStack.get(l);
        printwriter.print(s);
        printwriter.print("  #");
        printwriter.print(l);
        printwriter.print(": ");
        printwriter.println(as.toString());
        printwriter.print(s1);
        printwriter.print("mName=");
        printwriter.print(((BackStackRecord) (as)).mName);
        printwriter.print(" mIndex=");
        printwriter.print(((BackStackRecord) (as)).mIndex);
        printwriter.print(" mCommitted=");
        printwriter.println(((BackStackRecord) (as)).mCommitted);
        if (((BackStackRecord) (as)).mTransition != 0)
        {
            printwriter.print(s1);
            printwriter.print("mTransition=#");
            printwriter.print(Integer.toHexString(((BackStackRecord) (as)).mTransition));
            printwriter.print(" mTransitionStyle=#");
            printwriter.println(Integer.toHexString(((BackStackRecord) (as)).mTransitionStyle));
        }
        if (((BackStackRecord) (as)).mEnterAnim != 0 || ((BackStackRecord) (as)).mExitAnim != 0)
        {
            printwriter.print(s1);
            printwriter.print("mEnterAnim=#");
            printwriter.print(Integer.toHexString(((BackStackRecord) (as)).mEnterAnim));
            printwriter.print(" mExitAnim=#");
            printwriter.println(Integer.toHexString(((BackStackRecord) (as)).mExitAnim));
        }
        if (((BackStackRecord) (as)).mPopEnterAnim != 0 || ((BackStackRecord) (as)).mPopExitAnim != 0)
        {
            printwriter.print(s1);
            printwriter.print("mPopEnterAnim=#");
            printwriter.print(Integer.toHexString(((BackStackRecord) (as)).mPopEnterAnim));
            printwriter.print(" mPopExitAnim=#");
            printwriter.println(Integer.toHexString(((BackStackRecord) (as)).mPopExitAnim));
        }
        if (((BackStackRecord) (as)).mBreadCrumbTitleRes != 0 || ((BackStackRecord) (as)).mBreadCrumbTitleText != null)
        {
            printwriter.print(s1);
            printwriter.print("mBreadCrumbTitleRes=#");
            printwriter.print(Integer.toHexString(((BackStackRecord) (as)).mBreadCrumbTitleRes));
            printwriter.print(" mBreadCrumbTitleText=");
            printwriter.println(((BackStackRecord) (as)).mBreadCrumbTitleText);
        }
        if (((BackStackRecord) (as)).mBreadCrumbShortTitleRes != 0 || ((BackStackRecord) (as)).mBreadCrumbShortTitleText != null)
        {
            printwriter.print(s1);
            printwriter.print("mBreadCrumbShortTitleRes=#");
            printwriter.print(Integer.toHexString(((BackStackRecord) (as)).mBreadCrumbShortTitleRes));
            printwriter.print(" mBreadCrumbShortTitleText=");
            printwriter.println(((BackStackRecord) (as)).mBreadCrumbShortTitleText);
        }
        if (((BackStackRecord) (as)).mOps.isEmpty())
        {
            continue; /* Loop/switch isn't completed */
        }
        printwriter.print(s1);
        printwriter.println("Operations:");
        (new StringBuilder()).append(s1).append("    ");
        l2 = ((BackStackRecord) (as)).mOps.size();
        i2 = 0;
_L15:
        BackStackRecord.Op op;
        if (i2 >= l2)
        {
            continue; /* Loop/switch isn't completed */
        }
        op = (BackStackRecord.Op)((BackStackRecord) (as)).mOps.get(i2);
        op.cmd;
        JVM INSTR tableswitch 0 9: default 912
    //                   0 1110
    //                   1 1117
    //                   2 1124
    //                   3 1131
    //                   4 1138
    //                   5 1145
    //                   6 1152
    //                   7 1159
    //                   8 1166
    //                   9 1173;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L4:
        break; /* Loop/switch isn't completed */
_L3:
        filedescriptor = (new StringBuilder("cmd=")).append(op.cmd).toString();
_L16:
        printwriter.print(s1);
        printwriter.print("  Op #");
        printwriter.print(i2);
        printwriter.print(": ");
        printwriter.print(filedescriptor);
        printwriter.print(" ");
        printwriter.println(op.fragment);
        if (op.enterAnim != 0 || op.exitAnim != 0)
        {
            printwriter.print(s1);
            printwriter.print("enterAnim=#");
            printwriter.print(Integer.toHexString(op.enterAnim));
            printwriter.print(" exitAnim=#");
            printwriter.println(Integer.toHexString(op.exitAnim));
        }
        if (op.popEnterAnim != 0 || op.popExitAnim != 0)
        {
            printwriter.print(s1);
            printwriter.print("popEnterAnim=#");
            printwriter.print(Integer.toHexString(op.popEnterAnim));
            printwriter.print(" popExitAnim=#");
            printwriter.println(Integer.toHexString(op.popExitAnim));
        }
        i2++;
        if (true) goto _L15; else goto _L14
_L14:
        filedescriptor = "NULL";
          goto _L16
_L5:
        filedescriptor = "ADD";
          goto _L16
_L6:
        filedescriptor = "REPLACE";
          goto _L16
_L7:
        filedescriptor = "REMOVE";
          goto _L16
_L8:
        filedescriptor = "HIDE";
          goto _L16
_L9:
        filedescriptor = "SHOW";
          goto _L16
_L10:
        filedescriptor = "DETACH";
          goto _L16
_L11:
        filedescriptor = "ATTACH";
          goto _L16
_L12:
        filedescriptor = "SET_PRIMARY_NAV";
          goto _L16
_L13:
        filedescriptor = "UNSET_PRIMARY_NAV";
        if (true) goto _L16; else goto _L17
_L17:
        l++;
          goto _L18
_L2:
        this;
        JVM INSTR monitorenter ;
        if (mBackStackIndices == null) goto _L20; else goto _L19
_L19:
        i2 = mBackStackIndices.size();
        if (i2 <= 0) goto _L20; else goto _L21
_L21:
        printwriter.print(s);
        printwriter.println("Back Stack Indices:");
        l = 0;
_L22:
        if (l >= i2)
        {
            break; /* Loop/switch isn't completed */
        }
        filedescriptor = (BackStackRecord)mBackStackIndices.get(l);
        printwriter.print(s);
        printwriter.print("  #");
        printwriter.print(l);
        printwriter.print(": ");
        printwriter.println(filedescriptor);
        l++;
        if (true) goto _L22; else goto _L20
_L20:
        if (mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
        {
            printwriter.print(s);
            printwriter.print("mAvailBackStackIndices: ");
            printwriter.println(Arrays.toString(mAvailBackStackIndices.toArray()));
        }
        this;
        JVM INSTR monitorexit ;
        if (mPendingActions != null)
        {
            int j2 = mPendingActions.size();
            if (j2 > 0)
            {
                printwriter.print(s);
                printwriter.println("Pending Actions:");
                for (int i1 = 0; i1 < j2; i1++)
                {
                    filedescriptor = (OpGenerator)mPendingActions.get(i1);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(i1);
                    printwriter.print(": ");
                    printwriter.println(filedescriptor);
                }

            }
        }
        break MISSING_BLOCK_LABEL_1431;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        printwriter.print(s);
        printwriter.println("FragmentManager misc state:");
        printwriter.print(s);
        printwriter.print("  mHost=");
        printwriter.println(mHost);
        printwriter.print(s);
        printwriter.print("  mContainer=");
        printwriter.println(mContainer);
        if (mParent != null)
        {
            printwriter.print(s);
            printwriter.print("  mParent=");
            printwriter.println(mParent);
        }
        printwriter.print(s);
        printwriter.print("  mCurState=");
        printwriter.print(mCurState);
        printwriter.print(" mStateSaved=");
        printwriter.print(mStateSaved);
        printwriter.print(" mStopped=");
        printwriter.print(mStopped);
        printwriter.print(" mDestroyed=");
        printwriter.println(mDestroyed);
        if (mNeedMenuInvalidate)
        {
            printwriter.print(s);
            printwriter.print("  mNeedMenuInvalidate=");
            printwriter.println(mNeedMenuInvalidate);
        }
        return;
    }

    public final void enqueueAction(OpGenerator opgenerator, boolean flag)
    {
        if (!flag && isStateSaved())
        {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        this;
        JVM INSTR monitorenter ;
        if (!mDestroyed && mHost != null)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        throw new IllegalStateException("Activity has been destroyed");
        opgenerator;
        this;
        JVM INSTR monitorexit ;
        throw opgenerator;
        if (mPendingActions == null)
        {
            mPendingActions = new ArrayList();
        }
        mPendingActions.add(opgenerator);
        scheduleCommit();
        this;
        JVM INSTR monitorexit ;
    }

    public final boolean execPendingActions()
    {
        boolean flag;
        ensureExecReady(true);
        flag = false;
_L2:
        if (!generateOpsForPendingActions(mTmpRecords, mTmpIsPop))
        {
            break; /* Loop/switch isn't completed */
        }
        mExecutingActions = true;
        removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
        mExecutingActions = false;
        mTmpIsPop.clear();
        mTmpRecords.clear();
        flag = true;
        if (true) goto _L2; else goto _L1
        Exception exception;
        exception;
        mExecutingActions = false;
        mTmpIsPop.clear();
        mTmpRecords.clear();
        throw exception;
_L1:
        if (mHavePendingDeferredStart)
        {
            mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
        burpActive();
        return flag;
    }

    public final void execSingleAction(OpGenerator opgenerator, boolean flag)
    {
        if (flag && (mHost == null || mDestroyed))
        {
            return;
        }
        ensureExecReady(flag);
        if (!opgenerator.generateOps(mTmpRecords, mTmpIsPop))
        {
            break MISSING_BLOCK_LABEL_77;
        }
        mExecutingActions = true;
        removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
        mExecutingActions = false;
        mTmpIsPop.clear();
        mTmpRecords.clear();
        if (mHavePendingDeferredStart)
        {
            mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
        burpActive();
        return;
        opgenerator;
        mExecutingActions = false;
        mTmpIsPop.clear();
        mTmpRecords.clear();
        throw opgenerator;
    }

    public final boolean executePendingTransactions()
    {
        boolean flag = execPendingActions();
        forcePostponedTransactions();
        return flag;
    }

    public final Fragment findFragmentById(int i)
    {
        int j = mAdded.size() - 1;
_L10:
        if (j < 0) goto _L2; else goto _L1
_L1:
        Fragment fragment = (Fragment)mAdded.get(j);
        if (fragment == null || fragment.mFragmentId != i) goto _L4; else goto _L3
_L3:
        return fragment;
_L4:
        j--;
        continue; /* Loop/switch isn't completed */
_L2:
        if (mActive == null)
        {
            break MISSING_BLOCK_LABEL_111;
        }
        j = mActive.size() - 1;
_L8:
        if (j < 0) goto _L6; else goto _L5
_L5:
        Fragment fragment1;
        fragment1 = (Fragment)mActive.valueAt(j);
        if (fragment1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        fragment = fragment1;
        if (fragment1.mFragmentId == i) goto _L3; else goto _L7
_L7:
        j--;
          goto _L8
_L6:
        return null;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final Fragment findFragmentByTag(String s)
    {
        if (s == null) goto _L2; else goto _L1
_L1:
        int i = mAdded.size() - 1;
_L11:
        if (i < 0) goto _L2; else goto _L3
_L3:
        Fragment fragment = (Fragment)mAdded.get(i);
        if (fragment == null || !s.equals(fragment.mTag)) goto _L5; else goto _L4
_L4:
        return fragment;
_L5:
        i--;
        continue; /* Loop/switch isn't completed */
_L2:
        if (mActive == null || s == null)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        i = mActive.size() - 1;
_L9:
        if (i < 0) goto _L7; else goto _L6
_L6:
        Fragment fragment1;
        fragment1 = (Fragment)mActive.valueAt(i);
        if (fragment1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        fragment = fragment1;
        if (s.equals(fragment1.mTag)) goto _L4; else goto _L8
_L8:
        i--;
          goto _L9
_L7:
        return null;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public final Fragment findFragmentByWho(String s)
    {
        if (mActive != null && s != null)
        {
            for (int i = mActive.size() - 1; i >= 0; i--)
            {
                Fragment fragment = (Fragment)mActive.valueAt(i);
                if (fragment == null)
                {
                    continue;
                }
                if (!s.equals(fragment.mWho))
                {
                    if (fragment.mChildFragmentManager != null)
                    {
                        fragment = fragment.mChildFragmentManager.findFragmentByWho(s);
                    } else
                    {
                        fragment = null;
                    }
                }
                if (fragment != null)
                {
                    return fragment;
                }
            }

        }
        return null;
    }

    public final FragmentManager.BackStackEntry getBackStackEntryAt(int i)
    {
        return (FragmentManager.BackStackEntry)mBackStack.get(i);
    }

    public final int getBackStackEntryCount()
    {
        if (mBackStack != null)
        {
            return mBackStack.size();
        } else
        {
            return 0;
        }
    }

    public final List getFragments()
    {
        if (mAdded.isEmpty())
        {
            return Collections.EMPTY_LIST;
        }
        List list;
        synchronized (mAdded)
        {
            list = (List)mAdded.clone();
        }
        return list;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final boolean isDestroyed()
    {
        return mDestroyed;
    }

    public final boolean isStateSaved()
    {
        return mStateSaved || mStopped;
    }

    final void makeActive(Fragment fragment)
    {
        if (fragment.mIndex >= 0)
        {
            return;
        }
        int i = mNextFragmentIndex;
        mNextFragmentIndex = i + 1;
        fragment.setIndex(i, mParent);
        if (mActive == null)
        {
            mActive = new SparseArray();
        }
        mActive.put(fragment.mIndex, fragment);
    }

    final void moveFragmentToExpectedState(final Fragment fragment)
    {
        if (fragment != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj;
        ViewGroup viewgroup1;
        int i;
        int j;
        boolean flag;
        j = mCurState;
        i = j;
        int k;
        if (fragment.mRemoving)
        {
            ViewGroup viewgroup;
            if (fragment.mBackStackNesting > 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = Math.min(j, 1);
            } else
            {
                i = Math.min(j, 0);
            }
        }
        if (fragment.mAnimationInfo == null)
        {
            j = 0;
        } else
        {
            j = fragment.mAnimationInfo.mNextTransition;
        }
        if (fragment.mAnimationInfo == null)
        {
            k = 0;
        } else
        {
            k = fragment.mAnimationInfo.mNextTransitionStyle;
        }
        moveToState(fragment, i, j, k, false);
        if (fragment.mView == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        viewgroup1 = fragment.mContainer;
        obj = fragment.mView;
        if (viewgroup1 != null && obj != null) goto _L4; else goto _L3
_L3:
        obj = null;
_L13:
        if (obj != null)
        {
            obj = ((Fragment) (obj)).mView;
            viewgroup = fragment.mContainer;
            i = viewgroup.indexOfChild(((View) (obj)));
            j = viewgroup.indexOfChild(fragment.mView);
            if (j < i)
            {
                viewgroup.removeViewAt(j);
                viewgroup.addView(fragment.mView, i);
            }
        }
        if (fragment.mIsNewlyAdded && fragment.mContainer != null)
        {
            if (fragment.mPostponedAlpha > 0.0F)
            {
                fragment.mView.setAlpha(fragment.mPostponedAlpha);
            }
            fragment.mPostponedAlpha = 0.0F;
            fragment.mIsNewlyAdded = false;
            if (fragment.mAnimationInfo == null)
            {
                i = 0;
            } else
            {
                i = fragment.mAnimationInfo.mNextTransition;
            }
            if (fragment.mAnimationInfo == null)
            {
                j = 0;
            } else
            {
                j = fragment.mAnimationInfo.mNextTransitionStyle;
            }
            obj = loadAnimation(fragment, i, true, j);
            if (obj != null)
            {
                setHWLayerAnimListenerIfAlpha(fragment.mView, ((AnimationOrAnimator) (obj)));
                Fragment fragment1;
                if (((AnimationOrAnimator) (obj)).animation != null)
                {
                    fragment.mView.startAnimation(((AnimationOrAnimator) (obj)).animation);
                } else
                {
                    ((AnimationOrAnimator) (obj)).animator.setTarget(fragment.mView);
                    ((AnimationOrAnimator) (obj)).animator.start();
                }
            }
        }
        if (!fragment.mHiddenChanged) goto _L1; else goto _L5
_L5:
        if (fragment.mView == null) goto _L7; else goto _L6
_L6:
        if (fragment.mAnimationInfo == null)
        {
            i = 0;
        } else
        {
            i = fragment.mAnimationInfo.mNextTransition;
        }
        if (!fragment.mHidden)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (fragment.mAnimationInfo == null)
        {
            j = 0;
        } else
        {
            j = fragment.mAnimationInfo.mNextTransitionStyle;
        }
        obj = loadAnimation(fragment, i, flag, j);
        if (obj == null || ((AnimationOrAnimator) (obj)).animator == null) goto _L9; else goto _L8
_L8:
        ((AnimationOrAnimator) (obj)).animator.setTarget(fragment.mView);
        if (fragment.mHidden)
        {
            if (fragment.mAnimationInfo == null)
            {
                flag = false;
            } else
            {
                flag = fragment.mAnimationInfo.mIsHideReplaced;
            }
            if (flag)
            {
                if (fragment.mAnimationInfo == null)
                {
                    fragment.mAnimationInfo = new Fragment.AnimationInfo();
                }
                fragment.mAnimationInfo.mIsHideReplaced = false;
            } else
            {
                final ViewGroup container = fragment.mContainer;
                final View animatingView = fragment.mView;
                container.startViewTransition(animatingView);
                ((AnimationOrAnimator) (obj)).animator.addListener(new _cls4());
            }
        } else
        {
            fragment.mView.setVisibility(0);
        }
        setHWLayerAnimListenerIfAlpha(fragment.mView, ((AnimationOrAnimator) (obj)));
        ((AnimationOrAnimator) (obj)).animator.start();
_L7:
        if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible)
        {
            mNeedMenuInvalidate = true;
        }
        fragment.mHiddenChanged = false;
        flag = fragment.mHidden;
        Fragment.onHiddenChanged$51D2ILG_0();
        return;
_L4:
        i = mAdded.indexOf(fragment) - 1;
_L14:
        if (i < 0) goto _L11; else goto _L10
_L10:
        fragment1 = (Fragment)mAdded.get(i);
        if (fragment1.mContainer != viewgroup1)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = fragment1;
        if (fragment1.mView != null) goto _L13; else goto _L12
_L12:
        i--;
          goto _L14
_L11:
        obj = null;
          goto _L13
_L9:
        if (obj != null)
        {
            setHWLayerAnimListenerIfAlpha(fragment.mView, ((AnimationOrAnimator) (obj)));
            fragment.mView.startAnimation(((AnimationOrAnimator) (obj)).animation);
            ((AnimationOrAnimator) (obj)).animation.start();
        }
        if (!fragment.mHidden)
        {
            break MISSING_BLOCK_LABEL_808;
        }
        if (fragment.mAnimationInfo == null)
        {
            flag = false;
        } else
        {
            flag = fragment.mAnimationInfo.mIsHideReplaced;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_808;
        }
        i = 8;
_L15:
        fragment.mView.setVisibility(i);
        if (fragment.mAnimationInfo == null)
        {
            flag = false;
        } else
        {
            flag = fragment.mAnimationInfo.mIsHideReplaced;
        }
        if (flag)
        {
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mIsHideReplaced = false;
        }
          goto _L7
        i = 0;
          goto _L15
    }

    final void moveToState(int i, boolean flag)
    {
        if (mHost == null && i != 0)
        {
            throw new IllegalStateException("No activity");
        }
        if (flag || i != mCurState)
        {
            mCurState = i;
            if (mActive != null)
            {
                int j = mAdded.size();
                for (i = 0; i < j; i++)
                {
                    moveFragmentToExpectedState((Fragment)mAdded.get(i));
                }

                j = mActive.size();
                for (i = 0; i < j; i++)
                {
                    Fragment fragment = (Fragment)mActive.valueAt(i);
                    if (fragment != null && (fragment.mRemoving || fragment.mDetached) && !fragment.mIsNewlyAdded)
                    {
                        moveFragmentToExpectedState(fragment);
                    }
                }

                startPendingDeferredFragments();
                if (mNeedMenuInvalidate && mHost != null && mCurState == 5)
                {
                    mHost.onSupportInvalidateOptionsMenu();
                    mNeedMenuInvalidate = false;
                    return;
                }
            }
        }
    }

    final void moveToState(final Fragment fragment, int i, int j, int k, boolean flag)
    {
        int l;
        int i1;
        boolean flag1;
label0:
        {
            flag1 = true;
            if (fragment.mAdded)
            {
                i1 = i;
                if (!fragment.mDetached)
                {
                    break label0;
                }
            }
            i1 = i;
            if (i > 1)
            {
                i1 = 1;
            }
        }
        l = i1;
        if (!fragment.mRemoving) goto _L2; else goto _L1
_L1:
        l = i1;
        if (i1 <= fragment.mState) goto _L2; else goto _L3
_L3:
        if (fragment.mState != 0) goto _L5; else goto _L4
_L4:
        if (fragment.mBackStackNesting > 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L5; else goto _L6
_L6:
        l = 1;
_L2:
        i = l;
        if (fragment.mDeferStart)
        {
            i = l;
            if (fragment.mState < 4)
            {
                i = l;
                if (l > 3)
                {
                    i = 3;
                }
            }
        }
        if (fragment.mState > i) goto _L8; else goto _L7
_L7:
        if (!fragment.mFromLayout || fragment.mInLayout) goto _L10; else goto _L9
_L9:
        return;
_L5:
        l = fragment.mState;
        continue; /* Loop/switch isn't completed */
_L10:
label1:
        {
            Object obj;
            if (fragment.mAnimationInfo == null)
            {
                obj = null;
            } else
            {
                obj = fragment.mAnimationInfo.mAnimatingAway;
            }
            if (obj == null)
            {
                if (fragment.mAnimationInfo == null)
                {
                    obj = null;
                } else
                {
                    obj = fragment.mAnimationInfo.mAnimator;
                }
                if (obj == null)
                {
                    break label1;
                }
            }
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mAnimatingAway = null;
            if (fragment.mAnimationInfo == null)
            {
                fragment.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment.mAnimationInfo.mAnimator = null;
            if (fragment.mAnimationInfo == null)
            {
                j = 0;
            } else
            {
                j = fragment.mAnimationInfo.mStateAfterAnimating;
            }
            moveToState(fragment, j, 0, 0, true);
        }
        k = i;
        l = i;
        i1 = i;
        j = i;
        fragment.mState;
        JVM INSTR tableswitch 0 4: default 296
    //                   0 398
    //                   1 925
    //                   2 1601
    //                   3 1620
    //                   4 1776;
           goto _L11 _L12 _L13 _L14 _L15 _L16
_L12:
        break; /* Loop/switch isn't completed */
_L11:
        l = i;
_L18:
        if (fragment.mState != l)
        {
            Log.w("FragmentManager", (new StringBuilder("moveToState: Fragment state for ")).append(fragment).append(" not updated inline; expected state ").append(l).append(" found ").append(fragment.mState).toString());
            fragment.mState = l;
            return;
        }
        if (true) goto _L9; else goto _L17
_L17:
        k = i;
        if (i > 0)
        {
            k = i;
            FragmentManagerImpl fragmentmanagerimpl;
            if (fragment.mSavedFragmentState != null)
            {
                fragment.mSavedFragmentState.setClassLoader(mHost.mContext.getClassLoader());
                fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                fragment.mTarget = getFragment(fragment.mSavedFragmentState, "android:target_state");
                if (fragment.mTarget != null)
                {
                    fragment.mTargetRequestCode = fragment.mSavedFragmentState.getInt("android:target_req_state", 0);
                }
                if (fragment.mSavedUserVisibleHint != null)
                {
                    fragment.mUserVisibleHint = fragment.mSavedUserVisibleHint.booleanValue();
                    fragment.mSavedUserVisibleHint = null;
                } else
                {
                    fragment.mUserVisibleHint = fragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                }
                k = i;
                if (!fragment.mUserVisibleHint)
                {
                    fragment.mDeferStart = true;
                    k = i;
                    if (i > 3)
                    {
                        k = 3;
                    }
                }
            }
            fragment.mHost = mHost;
            fragment.mParentFragment = mParent;
            if (mParent != null)
            {
                fragmentmanagerimpl = mParent.mChildFragmentManager;
            } else
            {
                fragmentmanagerimpl = mHost.mFragmentManager;
            }
            fragment.mFragmentManager = fragmentmanagerimpl;
            if (fragment.mTarget != null)
            {
                if (mActive.get(fragment.mTarget.mIndex) != fragment.mTarget)
                {
                    throw new IllegalStateException((new StringBuilder("Fragment ")).append(fragment).append(" declared target fragment ").append(fragment.mTarget).append(" that does not belong to this FragmentManager!").toString());
                }
                if (fragment.mTarget.mState <= 0)
                {
                    moveToState(fragment.mTarget, 1, 0, 0, true);
                }
            }
            dispatchOnFragmentPreAttached(fragment, mHost.mContext, false);
            fragment.mCalled = false;
            fragment.onAttach(mHost.mContext);
            if (!fragment.mCalled)
            {
                throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onAttach()").toString());
            }
            if (fragment.mParentFragment == null)
            {
                mHost.onAttachFragment(fragment);
            } else
            {
                Fragment fragment1 = fragment.mParentFragment;
                Fragment.onAttachFragment$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78EP9AO______0();
            }
            dispatchOnFragmentAttached(fragment, mHost.mContext, false);
            ViewGroup viewgroup;
            if (!fragment.mIsCreated)
            {
                dispatchOnFragmentPreCreated(fragment, fragment.mSavedFragmentState, false);
                Bundle bundle = fragment.mSavedFragmentState;
                if (fragment.mChildFragmentManager != null)
                {
                    fragment.mChildFragmentManager.noteStateNotSaved();
                }
                fragment.mState = 1;
                fragment.mCalled = false;
                fragment.onCreate(bundle);
                fragment.mIsCreated = true;
                if (!fragment.mCalled)
                {
                    throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onCreate()").toString());
                }
                fragment.mLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_CREATE));
                dispatchOnFragmentCreated(fragment, fragment.mSavedFragmentState, false);
            } else
            {
                fragment.restoreChildFragmentState(fragment.mSavedFragmentState);
                fragment.mState = 1;
            }
            fragment.mRetaining = false;
        }
_L13:
        if (fragment.mFromLayout && !fragment.mPerformedCreateView)
        {
            fragment.mLayoutInflater = fragment.onGetLayoutInflater(fragment.mSavedFragmentState);
            fragment.performCreateView(fragment.mLayoutInflater, null, fragment.mSavedFragmentState);
            if (fragment.mView != null)
            {
                fragment.mInnerView = fragment.mView;
                fragment.mView.setSaveFromParentEnabled(false);
                if (fragment.mHidden)
                {
                    fragment.mView.setVisibility(8);
                }
                fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                dispatchOnFragmentViewCreated(fragment, fragment.mView, fragment.mSavedFragmentState, false);
            } else
            {
                fragment.mInnerView = null;
            }
        }
        l = k;
        if (k > 1)
        {
            if (!fragment.mFromLayout)
            {
                Object obj1;
                final View viewToAnimate;
                final ViewGroup container;
                Object obj2;
                Object obj3;
                if (fragment.mContainerId != 0)
                {
                    if (fragment.mContainerId == -1)
                    {
                        throwException(new IllegalArgumentException((new StringBuilder("Cannot create fragment ")).append(fragment).append(" for a container view with no id").toString()));
                    }
                    viewgroup = (ViewGroup)mContainer.onFindViewById(fragment.mContainerId);
                    obj1 = viewgroup;
                    if (viewgroup == null)
                    {
                        obj1 = viewgroup;
                        if (!fragment.mRestored)
                        {
                            try
                            {
                                obj1 = fragment.requireContext().getResources().getResourceName(fragment.mContainerId);
                            }
                            // Misplaced declaration of an exception variable
                            catch (Object obj1)
                            {
                                obj1 = "unknown";
                            }
                            throwException(new IllegalArgumentException((new StringBuilder("No view found for id 0x")).append(Integer.toHexString(fragment.mContainerId)).append(" (").append(((String) (obj1))).append(") for fragment ").append(fragment).toString()));
                            obj1 = viewgroup;
                        }
                    }
                } else
                {
                    obj1 = null;
                }
                fragment.mContainer = ((ViewGroup) (obj1));
                fragment.mLayoutInflater = fragment.onGetLayoutInflater(fragment.mSavedFragmentState);
                fragment.performCreateView(fragment.mLayoutInflater, ((ViewGroup) (obj1)), fragment.mSavedFragmentState);
                if (fragment.mView != null)
                {
                    fragment.mInnerView = fragment.mView;
                    fragment.mView.setSaveFromParentEnabled(false);
                    if (obj1 != null)
                    {
                        ((ViewGroup) (obj1)).addView(fragment.mView);
                    }
                    if (fragment.mHidden)
                    {
                        fragment.mView.setVisibility(8);
                    }
                    fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                    dispatchOnFragmentViewCreated(fragment, fragment.mView, fragment.mSavedFragmentState, false);
                    if (fragment.mView.getVisibility() == 0 && fragment.mContainer != null)
                    {
                        flag = flag1;
                    } else
                    {
                        flag = false;
                    }
                    fragment.mIsNewlyAdded = flag;
                } else
                {
                    fragment.mInnerView = null;
                }
            }
            obj1 = fragment.mSavedFragmentState;
            if (fragment.mChildFragmentManager != null)
            {
                fragment.mChildFragmentManager.noteStateNotSaved();
            }
            fragment.mState = 2;
            fragment.mCalled = false;
            fragment.onActivityCreated(((Bundle) (obj1)));
            if (!fragment.mCalled)
            {
                throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onActivityCreated()").toString());
            }
            if (fragment.mChildFragmentManager != null)
            {
                obj1 = fragment.mChildFragmentManager;
                obj1.mStateSaved = false;
                obj1.mStopped = false;
                ((FragmentManagerImpl) (obj1)).dispatchStateChange(2);
            }
            dispatchOnFragmentActivityCreated(fragment, fragment.mSavedFragmentState, false);
            if (fragment.mView != null)
            {
                obj1 = fragment.mSavedFragmentState;
                if (fragment.mSavedViewState != null)
                {
                    fragment.mInnerView.restoreHierarchyState(fragment.mSavedViewState);
                    fragment.mSavedViewState = null;
                }
                fragment.mCalled = false;
                fragment.onViewStateRestored(((Bundle) (obj1)));
                if (!fragment.mCalled)
                {
                    throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onViewStateRestored()").toString());
                }
            }
            fragment.mSavedFragmentState = null;
            l = k;
        }
_L14:
        i1 = l;
        if (l > 2)
        {
            fragment.mState = 3;
            i1 = l;
        }
_L15:
        j = i1;
        if (i1 > 3)
        {
            if (fragment.mChildFragmentManager != null)
            {
                fragment.mChildFragmentManager.noteStateNotSaved();
                fragment.mChildFragmentManager.execPendingActions();
            }
            fragment.mState = 4;
            fragment.mCalled = false;
            fragment.onStart();
            if (!fragment.mCalled)
            {
                throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onStart()").toString());
            }
            if (fragment.mChildFragmentManager != null)
            {
                obj1 = fragment.mChildFragmentManager;
                obj1.mStateSaved = false;
                obj1.mStopped = false;
                ((FragmentManagerImpl) (obj1)).dispatchStateChange(4);
            }
            fragment.mLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_START));
            if (fragment.mView != null)
            {
                fragment.mViewLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_START));
            }
            dispatchOnFragmentStarted(fragment, false);
            j = i1;
        }
_L16:
        l = j;
        if (j > 4)
        {
            if (fragment.mChildFragmentManager != null)
            {
                fragment.mChildFragmentManager.noteStateNotSaved();
                fragment.mChildFragmentManager.execPendingActions();
            }
            fragment.mState = 5;
            fragment.mCalled = false;
            fragment.onResume();
            if (!fragment.mCalled)
            {
                throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onResume()").toString());
            }
            if (fragment.mChildFragmentManager != null)
            {
                obj1 = fragment.mChildFragmentManager;
                obj1.mStateSaved = false;
                obj1.mStopped = false;
                ((FragmentManagerImpl) (obj1)).dispatchStateChange(5);
                fragment.mChildFragmentManager.execPendingActions();
            }
            fragment.mLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_RESUME));
            if (fragment.mView != null)
            {
                fragment.mViewLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_RESUME));
            }
            dispatchOnFragmentResumed(fragment, false);
            fragment.mSavedFragmentState = null;
            fragment.mSavedViewState = null;
            l = j;
        }
          goto _L18
_L8:
        l = i;
        if (fragment.mState <= i) goto _L18; else goto _L19
_L23:
        l = i;
        if (i > 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mDestroyed)
        {
            if (fragment.mAnimationInfo == null)
            {
                obj1 = null;
            } else
            {
                obj1 = fragment.mAnimationInfo.mAnimatingAway;
            }
            if (obj1 != null)
            {
                if (fragment.mAnimationInfo == null)
                {
                    obj1 = null;
                } else
                {
                    obj1 = fragment.mAnimationInfo.mAnimatingAway;
                }
                if (fragment.mAnimationInfo == null)
                {
                    fragment.mAnimationInfo = new Fragment.AnimationInfo();
                }
                fragment.mAnimationInfo.mAnimatingAway = null;
                ((View) (obj1)).clearAnimation();
            } else
            {
                if (fragment.mAnimationInfo == null)
                {
                    obj1 = null;
                } else
                {
                    obj1 = fragment.mAnimationInfo.mAnimator;
                }
                if (obj1 != null)
                {
                    if (fragment.mAnimationInfo == null)
                    {
                        obj1 = null;
                    } else
                    {
                        obj1 = fragment.mAnimationInfo.mAnimator;
                    }
                    if (fragment.mAnimationInfo == null)
                    {
                        fragment.mAnimationInfo = new Fragment.AnimationInfo();
                    }
                    fragment.mAnimationInfo.mAnimator = null;
                    ((Animator) (obj1)).cancel();
                }
            }
        }
        if (fragment.mAnimationInfo == null)
        {
            obj1 = null;
        } else
        {
            obj1 = fragment.mAnimationInfo.mAnimatingAway;
        }
        if (obj1 != null) goto _L21; else goto _L20
_L20:
        if (fragment.mAnimationInfo == null)
        {
            obj1 = null;
        } else
        {
            obj1 = fragment.mAnimationInfo.mAnimator;
        }
        if (obj1 == null) goto _L22; else goto _L21
_L21:
        if (fragment.mAnimationInfo == null)
        {
            fragment.mAnimationInfo = new Fragment.AnimationInfo();
        }
        fragment.mAnimationInfo.mStateAfterAnimating = i;
        l = 1;
        continue; /* Loop/switch isn't completed */
_L19:
        switch (fragment.mState)
        {
        default:
            l = i;
            continue; /* Loop/switch isn't completed */

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_2006;

        case 5: // '\005'
            if (i < 5)
            {
                if (fragment.mView != null)
                {
                    fragment.mViewLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_PAUSE));
                }
                fragment.mLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_PAUSE));
                if (fragment.mChildFragmentManager != null)
                {
                    fragment.mChildFragmentManager.dispatchStateChange(4);
                }
                fragment.mState = 4;
                fragment.mCalled = false;
                fragment.onPause();
                if (!fragment.mCalled)
                {
                    throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onPause()").toString());
                }
                dispatchOnFragmentPaused(fragment, false);
            }
            // fall through

        case 4: // '\004'
            if (i < 4)
            {
                fragment.mLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_STOP));
                if (fragment.mChildFragmentManager != null)
                {
                    obj1 = fragment.mChildFragmentManager;
                    obj1.mStopped = true;
                    ((FragmentManagerImpl) (obj1)).dispatchStateChange(3);
                }
                fragment.mState = 3;
                fragment.mCalled = false;
                fragment.onStop();
                if (!fragment.mCalled)
                {
                    throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onStop()").toString());
                }
                dispatchOnFragmentStopped(fragment, false);
            }
            // fall through

        case 3: // '\003'
            if (i < 3)
            {
                if (fragment.mChildFragmentManager != null)
                {
                    fragment.mChildFragmentManager.dispatchStateChange(2);
                }
                fragment.mState = 2;
            }
            break;

        case 2: // '\002'
            break;
        }
        if (i < 2)
        {
            if (fragment.mView != null && mHost.onShouldSaveFragmentState$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78EP9B8______0() && fragment.mSavedViewState == null)
            {
                saveFragmentViewState(fragment);
            }
            if (fragment.mChildFragmentManager != null)
            {
                fragment.mChildFragmentManager.dispatchStateChange(1);
            }
            fragment.mState = 1;
            fragment.mCalled = false;
            fragment.onDestroyView();
            if (!fragment.mCalled)
            {
                throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onDestroyView()").toString());
            }
            (new LoaderManagerImpl(fragment, ((ViewModelStoreOwner)fragment).getViewModelStore())).markForRedelivery();
            fragment.mPerformedCreateView = false;
            dispatchOnFragmentViewDestroyed(fragment, false);
            if (fragment.mView != null && fragment.mContainer != null)
            {
                fragment.mContainer.endViewTransition(fragment.mView);
                fragment.mView.clearAnimation();
                if (mCurState > 0 && !mDestroyed && fragment.mView.getVisibility() == 0 && fragment.mPostponedAlpha >= 0.0F)
                {
                    obj1 = loadAnimation(fragment, j, false, k);
                } else
                {
                    obj1 = null;
                }
                fragment.mPostponedAlpha = 0.0F;
                if (obj1 != null)
                {
                    viewToAnimate = fragment.mView;
                    container = fragment.mContainer;
                    container.startViewTransition(viewToAnimate);
                    if (fragment.mAnimationInfo == null)
                    {
                        fragment.mAnimationInfo = new Fragment.AnimationInfo();
                    }
                    fragment.mAnimationInfo.mStateAfterAnimating = i;
                    if (((AnimationOrAnimator) (obj1)).animation != null)
                    {
                        obj2 = new EndViewTransitionAnimator(((AnimationOrAnimator) (obj1)).animation, container, viewToAnimate);
                        obj3 = fragment.mView;
                        if (fragment.mAnimationInfo == null)
                        {
                            fragment.mAnimationInfo = new Fragment.AnimationInfo();
                        }
                        fragment.mAnimationInfo.mAnimatingAway = ((View) (obj3));
                        ((Animation) (obj2)).setAnimationListener(new _cls2(fragment));
                        setHWLayerAnimListenerIfAlpha(viewToAnimate, ((AnimationOrAnimator) (obj1)));
                        fragment.mView.startAnimation(((Animation) (obj2)));
                    } else
                    {
                        obj2 = ((AnimationOrAnimator) (obj1)).animator;
                        obj3 = ((AnimationOrAnimator) (obj1)).animator;
                        if (fragment.mAnimationInfo == null)
                        {
                            fragment.mAnimationInfo = new Fragment.AnimationInfo();
                        }
                        fragment.mAnimationInfo.mAnimator = ((Animator) (obj3));
                        ((Animator) (obj2)).addListener(new _cls3());
                        ((Animator) (obj2)).setTarget(fragment.mView);
                        setHWLayerAnimListenerIfAlpha(fragment.mView, ((AnimationOrAnimator) (obj1)));
                        ((Animator) (obj2)).start();
                    }
                }
                fragment.mContainer.removeView(fragment.mView);
            }
            fragment.mContainer = null;
            fragment.mView = null;
            fragment.mViewLifecycleOwner = null;
            fragment.mViewLifecycleOwnerLiveData.setValue(null);
            fragment.mInnerView = null;
            fragment.mInLayout = false;
        }
        if (true) goto _L23; else goto _L22
_L22:
        if (!fragment.mRetaining)
        {
            fragment.mLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_DESTROY));
            if (fragment.mChildFragmentManager != null)
            {
                obj1 = fragment.mChildFragmentManager;
                obj1.mDestroyed = true;
                ((FragmentManagerImpl) (obj1)).execPendingActions();
                ((FragmentManagerImpl) (obj1)).dispatchStateChange(0);
                obj1.mHost = null;
                obj1.mContainer = null;
                obj1.mParent = null;
            }
            fragment.mState = 0;
            fragment.mCalled = false;
            fragment.mIsCreated = false;
            fragment.onDestroy();
            if (!fragment.mCalled)
            {
                throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onDestroy()").toString());
            }
            fragment.mChildFragmentManager = null;
            dispatchOnFragmentDestroyed(fragment, false);
        } else
        {
            fragment.mState = 0;
        }
        fragment.mCalled = false;
        fragment.onDetach();
        fragment.mLayoutInflater = null;
        if (!fragment.mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder("Fragment ")).append(fragment).append(" did not call through to super.onDetach()").toString());
        }
        if (fragment.mChildFragmentManager != null)
        {
            if (!fragment.mRetaining)
            {
                throw new IllegalStateException((new StringBuilder("Child FragmentManager of ")).append(fragment).append(" was not  destroyed and this fragment is not retaining instance").toString());
            }
            obj1 = fragment.mChildFragmentManager;
            obj1.mDestroyed = true;
            ((FragmentManagerImpl) (obj1)).execPendingActions();
            ((FragmentManagerImpl) (obj1)).dispatchStateChange(0);
            obj1.mHost = null;
            obj1.mContainer = null;
            obj1.mParent = null;
            fragment.mChildFragmentManager = null;
        }
        dispatchOnFragmentDetached(fragment, false);
        l = i;
        if (!flag)
        {
            if (!fragment.mRetaining)
            {
                l = i;
                if (fragment.mIndex >= 0)
                {
                    mActive.put(fragment.mIndex, null);
                    fragment.mIndex = -1;
                    fragment.mWho = null;
                    fragment.mAdded = false;
                    fragment.mRemoving = false;
                    fragment.mFromLayout = false;
                    fragment.mInLayout = false;
                    fragment.mRestored = false;
                    fragment.mBackStackNesting = 0;
                    fragment.mFragmentManager = null;
                    fragment.mChildFragmentManager = null;
                    fragment.mHost = null;
                    fragment.mFragmentId = 0;
                    fragment.mContainerId = 0;
                    fragment.mTag = null;
                    fragment.mHidden = false;
                    fragment.mDetached = false;
                    fragment.mRetaining = false;
                    l = i;
                }
            } else
            {
                fragment.mHost = null;
                fragment.mParentFragment = null;
                fragment.mFragmentManager = null;
                l = i;
            }
        }
        if (true) goto _L18; else goto _L24
_L24:
        if (true) goto _L2; else goto _L25
_L25:
    }

    public final void noteStateNotSaved()
    {
        mSavedNonConfig = null;
        mStateSaved = false;
        mStopped = false;
        int j = mAdded.size();
        for (int i = 0; i < j; i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if (fragment != null && fragment.mChildFragmentManager != null)
            {
                fragment.mChildFragmentManager.noteStateNotSaved();
            }
        }

    }

    public final View onCreateView(View view, String s, Context context, AttributeSet attributeset)
    {
        if (!"fragment".equals(s))
        {
            return null;
        }
        String s1 = attributeset.getAttributeValue(null, "class");
        s = context.obtainStyledAttributes(attributeset, FragmentTag.Fragment);
        if (s1 == null)
        {
            s1 = s.getString(0);
        }
        int k = s.getResourceId(1, -1);
        String s2 = s.getString(2);
        s.recycle();
        if (!Fragment.isSupportFragmentClass(mHost.mContext, s1))
        {
            return null;
        }
        int i;
        if (view != null)
        {
            i = view.getId();
        } else
        {
            i = 0;
        }
        if (i == -1 && k == -1 && s2 == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Must specify unique android:id, android:tag, or have a parent with an id for ").append(s1).toString());
        }
        if (k != -1)
        {
            s = findFragmentById(k);
        } else
        {
            s = null;
        }
        view = s;
        if (s == null)
        {
            view = s;
            if (s2 != null)
            {
                view = findFragmentByTag(s2);
            }
        }
        s = view;
        if (view == null)
        {
            s = view;
            if (i != -1)
            {
                s = findFragmentById(i);
            }
        }
        if (s == null)
        {
            view = mContainer.instantiate(context, s1, null);
            view.mFromLayout = true;
            int j;
            if (k != 0)
            {
                j = k;
            } else
            {
                j = i;
            }
            view.mFragmentId = j;
            view.mContainerId = i;
            view.mTag = s2;
            view.mInLayout = true;
            view.mFragmentManager = this;
            view.mHost = mHost;
            view.onInflate$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FELQ6IR1F85Q78SJ9C9QN8PAJCLQ3MJ31DPI74RR9CGNMUSPF89QMSP3CCKTIILG_0(attributeset, ((Fragment) (view)).mSavedFragmentState);
            addFragment(view, true);
        } else
        {
            if (((Fragment) (s)).mInLayout)
            {
                throw new IllegalArgumentException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Duplicate id 0x").append(Integer.toHexString(k)).append(", tag ").append(s2).append(", or parent id 0x").append(Integer.toHexString(i)).append(" with another fragment for ").append(s1).toString());
            }
            s.mInLayout = true;
            s.mHost = mHost;
            if (!((Fragment) (s)).mRetaining)
            {
                s.onInflate$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FELQ6IR1F85Q78SJ9C9QN8PAJCLQ3MJ31DPI74RR9CGNMUSPF89QMSP3CCKTIILG_0(attributeset, ((Fragment) (s)).mSavedFragmentState);
            }
            view = s;
        }
        if (mCurState <= 0 && ((Fragment) (view)).mFromLayout)
        {
            moveToState(view, 1, 0, 0, false);
        } else
        {
            moveToState(view, mCurState, 0, 0, false);
        }
        if (((Fragment) (view)).mView == null)
        {
            throw new IllegalStateException((new StringBuilder("Fragment ")).append(s1).append(" did not create a view.").toString());
        }
        if (k != 0)
        {
            ((Fragment) (view)).mView.setId(k);
        }
        if (((Fragment) (view)).mView.getTag() == null)
        {
            ((Fragment) (view)).mView.setTag(s2);
        }
        return ((Fragment) (view)).mView;
    }

    public final View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        return onCreateView(null, s, context, attributeset);
    }

    public final void performPendingDeferredStart(Fragment fragment)
    {
label0:
        {
            if (fragment.mDeferStart)
            {
                if (!mExecutingActions)
                {
                    break label0;
                }
                mHavePendingDeferredStart = true;
            }
            return;
        }
        fragment.mDeferStart = false;
        moveToState(fragment, mCurState, 0, 0, false);
    }

    public final void popBackStack()
    {
        enqueueAction(new PopBackStackState(null, -1, 0), false);
    }

    public final void popBackStack(int i, int j)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder("Bad id: ")).append(i).toString());
        } else
        {
            enqueueAction(new PopBackStackState(null, i, 1), false);
            return;
        }
    }

    public final boolean popBackStackImmediate()
    {
        if (isStateSaved())
        {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else
        {
            return popBackStackImmediate(null, -1, 0);
        }
    }

    public final boolean popBackStackImmediate(String s, int i)
    {
        if (isStateSaved())
        {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else
        {
            return popBackStackImmediate(s, -1, 1);
        }
    }

    final boolean popBackStackState(ArrayList arraylist, ArrayList arraylist1, String s, int i, int j)
    {
        if (mBackStack == null)
        {
            return false;
        }
        if (s == null && i < 0 && (j & 1) == 0)
        {
            i = mBackStack.size() - 1;
            if (i < 0)
            {
                return false;
            }
            arraylist.add(mBackStack.remove(i));
            arraylist1.add(Boolean.valueOf(true));
        } else
        {
            int k = -1;
            if (s != null || i >= 0)
            {
                int l = mBackStack.size() - 1;
                do
                {
                    if (l < 0)
                    {
                        break;
                    }
                    BackStackRecord backstackrecord = (BackStackRecord)mBackStack.get(l);
                    if (s != null && s.equals(backstackrecord.mName) || i >= 0 && i == backstackrecord.mIndex)
                    {
                        break;
                    }
                    l--;
                } while (true);
                if (l < 0)
                {
                    return false;
                }
                k = l;
                if ((j & 1) != 0)
                {
                    j = l - 1;
                    do
                    {
                        k = j;
                        if (j < 0)
                        {
                            break;
                        }
                        BackStackRecord backstackrecord1 = (BackStackRecord)mBackStack.get(j);
                        if (s == null || !s.equals(backstackrecord1.mName))
                        {
                            k = j;
                            if (i < 0)
                            {
                                break;
                            }
                            k = j;
                            if (i != backstackrecord1.mIndex)
                            {
                                break;
                            }
                        }
                        j--;
                    } while (true);
                }
            }
            if (k == mBackStack.size() - 1)
            {
                return false;
            }
            i = mBackStack.size() - 1;
            while (i > k) 
            {
                arraylist.add(mBackStack.remove(i));
                arraylist1.add(Boolean.valueOf(true));
                i--;
            }
        }
        return true;
    }

    public final void removeFragment(Fragment fragment)
    {
        boolean flag;
        if (fragment.mBackStackNesting > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!fragment.mDetached || flag)
        {
            synchronized (mAdded)
            {
                mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible)
            {
                mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
        return;
        fragment;
        arraylist;
        JVM INSTR monitorexit ;
        throw fragment;
    }

    public final void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if (mBackStackChangeListeners != null)
        {
            mBackStackChangeListeners.remove(onbackstackchangedlistener);
        }
    }

    final void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentmanagernonconfig)
    {
        int j;
        FragmentManagerState fragmentmanagerstate;
        while (parcelable == null || (fragmentmanagerstate = (FragmentManagerState)parcelable).mActive == null) 
        {
            return;
        }
        Object obj;
        if (fragmentmanagernonconfig != null)
        {
            List list = fragmentmanagernonconfig.mFragments;
            obj = fragmentmanagernonconfig.mChildNonConfigs;
            parcelable = fragmentmanagernonconfig.mViewModelStores;
            int i;
            int k;
            if (list != null)
            {
                i = list.size();
            } else
            {
                i = 0;
            }
            for (k = 0; k < i; k++)
            {
                Fragment fragment = (Fragment)list.get(k);
                int i1;
                for (i1 = 0; i1 < fragmentmanagerstate.mActive.length && fragmentmanagerstate.mActive[i1].mIndex != fragment.mIndex; i1++) { }
                if (i1 == fragmentmanagerstate.mActive.length)
                {
                    throwException(new IllegalStateException((new StringBuilder("Could not find active fragment with index ")).append(fragment.mIndex).toString()));
                }
                FragmentState fragmentstate = fragmentmanagerstate.mActive[i1];
                fragmentstate.mInstance = fragment;
                fragment.mSavedViewState = null;
                fragment.mBackStackNesting = 0;
                fragment.mInLayout = false;
                fragment.mAdded = false;
                fragment.mTarget = null;
                if (fragmentstate.mSavedFragmentState != null)
                {
                    fragmentstate.mSavedFragmentState.setClassLoader(mHost.mContext.getClassLoader());
                    fragment.mSavedViewState = fragmentstate.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                    fragment.mSavedFragmentState = fragmentstate.mSavedFragmentState;
                }
            }

        } else
        {
            obj = null;
            parcelable = null;
        }
        mActive = new SparseArray(fragmentmanagerstate.mActive.length);
        j = 0;
        while (j < fragmentmanagerstate.mActive.length) 
        {
            FragmentState fragmentstate1 = fragmentmanagerstate.mActive[j];
            if (fragmentstate1 == null)
            {
                continue;
            }
            Object obj1;
            ViewModelStore viewmodelstore;
            FragmentHostCallback fragmenthostcallback;
            FragmentContainer fragmentcontainer;
            Fragment fragment1;
            int l;
            int j1;
            int k1;
            if (obj != null && j < ((List) (obj)).size())
            {
                obj1 = (FragmentManagerNonConfig)((List) (obj)).get(j);
            } else
            {
                obj1 = null;
            }
            fragmenthostcallback = null;
            viewmodelstore = fragmenthostcallback;
            if (parcelable != null)
            {
                viewmodelstore = fragmenthostcallback;
                if (j < parcelable.size())
                {
                    viewmodelstore = (ViewModelStore)parcelable.get(j);
                }
            }
            fragmenthostcallback = mHost;
            fragmentcontainer = mContainer;
            fragment1 = mParent;
            if (fragmentstate1.mInstance == null)
            {
                Context context = fragmenthostcallback.mContext;
                if (fragmentstate1.mArguments != null)
                {
                    fragmentstate1.mArguments.setClassLoader(context.getClassLoader());
                }
                if (fragmentcontainer != null)
                {
                    fragmentstate1.mInstance = fragmentcontainer.instantiate(context, fragmentstate1.mClassName, fragmentstate1.mArguments);
                } else
                {
                    fragmentstate1.mInstance = Fragment.instantiate(context, fragmentstate1.mClassName, fragmentstate1.mArguments);
                }
                if (fragmentstate1.mSavedFragmentState != null)
                {
                    fragmentstate1.mSavedFragmentState.setClassLoader(context.getClassLoader());
                    fragmentstate1.mInstance.mSavedFragmentState = fragmentstate1.mSavedFragmentState;
                }
                fragmentstate1.mInstance.setIndex(fragmentstate1.mIndex, fragment1);
                fragmentstate1.mInstance.mFromLayout = fragmentstate1.mFromLayout;
                fragmentstate1.mInstance.mRestored = true;
                fragmentstate1.mInstance.mFragmentId = fragmentstate1.mFragmentId;
                fragmentstate1.mInstance.mContainerId = fragmentstate1.mContainerId;
                fragmentstate1.mInstance.mTag = fragmentstate1.mTag;
                fragmentstate1.mInstance.mRetainInstance = fragmentstate1.mRetainInstance;
                fragmentstate1.mInstance.mDetached = fragmentstate1.mDetached;
                fragmentstate1.mInstance.mHidden = fragmentstate1.mHidden;
                fragmentstate1.mInstance.mFragmentManager = fragmenthostcallback.mFragmentManager;
            }
            fragmentstate1.mInstance.mChildNonConfig = ((FragmentManagerNonConfig) (obj1));
            fragmentstate1.mInstance.mViewModelStore = viewmodelstore;
            obj1 = fragmentstate1.mInstance;
            mActive.put(((Fragment) (obj1)).mIndex, obj1);
            fragmentstate1.mInstance = null;
            j++;
        }
        if (fragmentmanagernonconfig != null)
        {
            parcelable = fragmentmanagernonconfig.mFragments;
            if (parcelable != null)
            {
                j = parcelable.size();
            } else
            {
                j = 0;
            }
            for (l = 0; l < j; l++)
            {
                fragmentmanagernonconfig = (Fragment)parcelable.get(l);
                if (((Fragment) (fragmentmanagernonconfig)).mTargetIndex < 0)
                {
                    continue;
                }
                fragmentmanagernonconfig.mTarget = (Fragment)mActive.get(((Fragment) (fragmentmanagernonconfig)).mTargetIndex);
                if (((Fragment) (fragmentmanagernonconfig)).mTarget == null)
                {
                    Log.w("FragmentManager", (new StringBuilder("Re-attaching retained fragment ")).append(fragmentmanagernonconfig).append(" target no longer exists: ").append(((Fragment) (fragmentmanagernonconfig)).mTargetIndex).toString());
                }
            }

        }
        mAdded.clear();
        if (fragmentmanagerstate.mAdded != null)
        {
            for (j = 0; j < fragmentmanagerstate.mAdded.length; j++)
            {
                fragmentmanagernonconfig = (Fragment)mActive.get(fragmentmanagerstate.mAdded[j]);
                if (fragmentmanagernonconfig == null)
                {
                    throwException(new IllegalStateException((new StringBuilder("No instantiated fragment for index #")).append(fragmentmanagerstate.mAdded[j]).toString()));
                }
                fragmentmanagernonconfig.mAdded = true;
                if (mAdded.contains(fragmentmanagernonconfig))
                {
                    throw new IllegalStateException("Already added!");
                }
                synchronized (mAdded)
                {
                    mAdded.add(fragmentmanagernonconfig);
                }
            }

        }
        break MISSING_BLOCK_LABEL_984;
        fragmentmanagernonconfig;
        parcelable;
        JVM INSTR monitorexit ;
        throw fragmentmanagernonconfig;
        if (fragmentmanagerstate.mBackStack == null) goto _L2; else goto _L1
_L1:
        mBackStack = new ArrayList(fragmentmanagerstate.mBackStack.length);
        j = 0;
_L12:
        if (j >= fragmentmanagerstate.mBackStack.length) goto _L4; else goto _L3
_L3:
        parcelable = fragmentmanagerstate.mBackStack[j];
        fragmentmanagernonconfig = new BackStackRecord(this);
        l = 0;
        while (l < ((BackStackState) (parcelable)).mOps.length) 
        {
            obj = new BackStackRecord.Op();
            obj1 = ((BackStackState) (parcelable)).mOps;
            j1 = l + 1;
            obj.cmd = obj1[l];
            obj1 = ((BackStackState) (parcelable)).mOps;
            l = j1 + 1;
            j1 = obj1[j1];
            if (j1 >= 0)
            {
                obj.fragment = (Fragment)mActive.get(j1);
            } else
            {
                obj.fragment = null;
            }
            obj1 = ((BackStackState) (parcelable)).mOps;
            j1 = l + 1;
            obj.enterAnim = obj1[l];
            obj1 = ((BackStackState) (parcelable)).mOps;
            l = j1 + 1;
            obj.exitAnim = obj1[j1];
            obj1 = ((BackStackState) (parcelable)).mOps;
            j1 = l + 1;
            obj.popEnterAnim = obj1[l];
            obj1 = ((BackStackState) (parcelable)).mOps;
            l = j1 + 1;
            obj.popExitAnim = obj1[j1];
            fragmentmanagernonconfig.mEnterAnim = ((BackStackRecord.Op) (obj)).enterAnim;
            fragmentmanagernonconfig.mExitAnim = ((BackStackRecord.Op) (obj)).exitAnim;
            fragmentmanagernonconfig.mPopEnterAnim = ((BackStackRecord.Op) (obj)).popEnterAnim;
            fragmentmanagernonconfig.mPopExitAnim = ((BackStackRecord.Op) (obj)).popExitAnim;
            fragmentmanagernonconfig.addOp(((BackStackRecord.Op) (obj)));
        }
        fragmentmanagernonconfig.mTransition = ((BackStackState) (parcelable)).mTransition;
        fragmentmanagernonconfig.mTransitionStyle = ((BackStackState) (parcelable)).mTransitionStyle;
        fragmentmanagernonconfig.mName = ((BackStackState) (parcelable)).mName;
        fragmentmanagernonconfig.mIndex = ((BackStackState) (parcelable)).mIndex;
        fragmentmanagernonconfig.mAddToBackStack = true;
        fragmentmanagernonconfig.mBreadCrumbTitleRes = ((BackStackState) (parcelable)).mBreadCrumbTitleRes;
        fragmentmanagernonconfig.mBreadCrumbTitleText = ((BackStackState) (parcelable)).mBreadCrumbTitleText;
        fragmentmanagernonconfig.mBreadCrumbShortTitleRes = ((BackStackState) (parcelable)).mBreadCrumbShortTitleRes;
        fragmentmanagernonconfig.mBreadCrumbShortTitleText = ((BackStackState) (parcelable)).mBreadCrumbShortTitleText;
        fragmentmanagernonconfig.mSharedElementSourceNames = ((BackStackState) (parcelable)).mSharedElementSourceNames;
        fragmentmanagernonconfig.mSharedElementTargetNames = ((BackStackState) (parcelable)).mSharedElementTargetNames;
        fragmentmanagernonconfig.mReorderingAllowed = ((BackStackState) (parcelable)).mReorderingAllowed;
        fragmentmanagernonconfig.bumpBackStackNesting(1);
        mBackStack.add(fragmentmanagernonconfig);
        if (((BackStackRecord) (fragmentmanagernonconfig)).mIndex < 0) goto _L6; else goto _L5
_L5:
        k1 = ((BackStackRecord) (fragmentmanagernonconfig)).mIndex;
        this;
        JVM INSTR monitorenter ;
        if (mBackStackIndices == null)
        {
            mBackStackIndices = new ArrayList();
        }
        j1 = mBackStackIndices.size();
        l = j1;
        if (k1 >= j1) goto _L8; else goto _L7
_L7:
        mBackStackIndices.set(k1, fragmentmanagernonconfig);
_L10:
        this;
        JVM INSTR monitorexit ;
          goto _L6
_L8:
        if (l >= k1)
        {
            break; /* Loop/switch isn't completed */
        }
        mBackStackIndices.add(null);
        if (mAvailBackStackIndices == null)
        {
            mAvailBackStackIndices = new ArrayList();
        }
        mAvailBackStackIndices.add(Integer.valueOf(l));
        l++;
        if (true) goto _L8; else goto _L9
_L9:
        mBackStackIndices.add(fragmentmanagernonconfig);
          goto _L10
        parcelable;
        this;
        JVM INSTR monitorexit ;
        throw parcelable;
_L2:
        mBackStack = null;
_L4:
        if (fragmentmanagerstate.mPrimaryNavActiveIndex >= 0)
        {
            mPrimaryNav = (Fragment)mActive.get(fragmentmanagerstate.mPrimaryNavActiveIndex);
        }
        mNextFragmentIndex = fragmentmanagerstate.mNextFragmentIndex;
        return;
_L6:
        j++;
        if (true) goto _L12; else goto _L11
_L11:
    }

    final Parcelable saveAllState()
    {
        BackStackState abackstackstate[];
        boolean flag;
        flag = false;
        abackstackstate = null;
        forcePostponedTransactions();
        int i;
        int k;
        if (mActive == null)
        {
            i = 0;
        } else
        {
            i = mActive.size();
        }
        k = 0;
        while (k < i) 
        {
            Fragment fragment = (Fragment)mActive.valueAt(k);
            if (fragment != null)
            {
                View view;
                if (fragment.mAnimationInfo == null)
                {
                    view = null;
                } else
                {
                    view = fragment.mAnimationInfo.mAnimatingAway;
                }
                if (view != null)
                {
                    Animation animation;
                    int i1;
                    if (fragment.mAnimationInfo == null)
                    {
                        i1 = 0;
                    } else
                    {
                        i1 = fragment.mAnimationInfo.mStateAfterAnimating;
                    }
                    if (fragment.mAnimationInfo == null)
                    {
                        view = null;
                    } else
                    {
                        view = fragment.mAnimationInfo.mAnimatingAway;
                    }
                    animation = view.getAnimation();
                    if (animation != null)
                    {
                        animation.cancel();
                        view.clearAnimation();
                    }
                    if (fragment.mAnimationInfo == null)
                    {
                        fragment.mAnimationInfo = new Fragment.AnimationInfo();
                    }
                    fragment.mAnimationInfo.mAnimatingAway = null;
                    moveToState(fragment, i1, 0, 0, false);
                } else
                {
                    Animator animator;
                    if (fragment.mAnimationInfo == null)
                    {
                        animator = null;
                    } else
                    {
                        animator = fragment.mAnimationInfo.mAnimator;
                    }
                    if (animator != null)
                    {
                        if (fragment.mAnimationInfo == null)
                        {
                            animator = null;
                        } else
                        {
                            animator = fragment.mAnimationInfo.mAnimator;
                        }
                        animator.end();
                    }
                }
            }
            k++;
        }
        execPendingActions();
        mStateSaved = true;
        mSavedNonConfig = null;
        if (mActive != null && mActive.size() > 0) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        FragmentState afragmentstate[];
        int j;
        int l;
        int j1;
        j1 = mActive.size();
        afragmentstate = new FragmentState[j1];
        l = 0;
        j = 0;
_L4:
        if (l >= j1)
        {
            continue; /* Loop/switch isn't completed */
        }
        Fragment fragment1 = (Fragment)mActive.valueAt(l);
        if (fragment1 != null)
        {
            if (fragment1.mIndex < 0)
            {
                throwException(new IllegalStateException((new StringBuilder("Failure saving state: active ")).append(fragment1).append(" has cleared index: ").append(fragment1.mIndex).toString()));
            }
            FragmentState fragmentstate = new FragmentState(fragment1);
            afragmentstate[l] = fragmentstate;
            if (fragment1.mState <= 0 || fragmentstate.mSavedFragmentState != null)
            {
                break; /* Loop/switch isn't completed */
            }
            if (mStateBundle == null)
            {
                mStateBundle = new Bundle();
            }
            Bundle bundle = mStateBundle;
            fragment1.onSaveInstanceState(bundle);
            if (fragment1.mChildFragmentManager != null)
            {
                Parcelable parcelable = fragment1.mChildFragmentManager.saveAllState();
                if (parcelable != null)
                {
                    bundle.putParcelable("android:support:fragments", parcelable);
                }
            }
            dispatchOnFragmentSaveInstanceState(fragment1, mStateBundle, false);
            int ai[];
            Object obj;
            FragmentManagerState fragmentmanagerstate;
            if (!mStateBundle.isEmpty())
            {
                obj = mStateBundle;
                mStateBundle = null;
            } else
            {
                obj = null;
            }
            if (fragment1.mView != null)
            {
                saveFragmentViewState(fragment1);
            }
            bundle = ((Bundle) (obj));
            if (fragment1.mSavedViewState != null)
            {
                bundle = ((Bundle) (obj));
                if (obj == null)
                {
                    bundle = new Bundle();
                }
                bundle.putSparseParcelableArray("android:view_state", fragment1.mSavedViewState);
            }
            obj = bundle;
            if (!fragment1.mUserVisibleHint)
            {
                obj = bundle;
                if (bundle == null)
                {
                    obj = new Bundle();
                }
                ((Bundle) (obj)).putBoolean("android:user_visible_hint", fragment1.mUserVisibleHint);
            }
            fragmentstate.mSavedFragmentState = ((Bundle) (obj));
            if (fragment1.mTarget == null)
            {
                break MISSING_BLOCK_LABEL_780;
            }
            if (fragment1.mTarget.mIndex < 0)
            {
                throwException(new IllegalStateException((new StringBuilder("Failure saving state: ")).append(fragment1).append(" has target not in fragment manager: ").append(fragment1.mTarget).toString()));
            }
            if (fragmentstate.mSavedFragmentState == null)
            {
                fragmentstate.mSavedFragmentState = new Bundle();
            }
            bundle = fragmentstate.mSavedFragmentState;
            obj = fragment1.mTarget;
            if (((Fragment) (obj)).mIndex < 0)
            {
                throwException(new IllegalStateException((new StringBuilder("Fragment ")).append(obj).append(" is not currently in the FragmentManager").toString()));
            }
            bundle.putInt("android:target_state", ((Fragment) (obj)).mIndex);
            if (fragment1.mTargetRequestCode == 0)
            {
                break MISSING_BLOCK_LABEL_780;
            }
            fragmentstate.mSavedFragmentState.putInt("android:target_req_state", fragment1.mTargetRequestCode);
            j = 1;
        }
_L5:
        l++;
        if (true) goto _L4; else goto _L3
_L3:
        fragmentstate.mSavedFragmentState = fragment1.mSavedFragmentState;
        j = 1;
          goto _L5
        if (!j) goto _L1; else goto _L6
_L6:
        l = mAdded.size();
        if (l > 0)
        {
            obj = new int[l];
            j = 0;
            do
            {
                ai = ((int []) (obj));
                if (j >= l)
                {
                    break;
                }
                obj[j] = ((Fragment)mAdded.get(j)).mIndex;
                if (obj[j] < 0)
                {
                    throwException(new IllegalStateException((new StringBuilder("Failure saving state: active ")).append(mAdded.get(j)).append(" has cleared index: ").append(obj[j]).toString()));
                }
                j++;
            } while (true);
        } else
        {
            ai = null;
        }
        obj = abackstackstate;
        if (mBackStack != null)
        {
            l = mBackStack.size();
            obj = abackstackstate;
            if (l > 0)
            {
                abackstackstate = new BackStackState[l];
                j = ((flag) ? 1 : 0);
                do
                {
                    obj = abackstackstate;
                    if (j >= l)
                    {
                        break;
                    }
                    abackstackstate[j] = new BackStackState((BackStackRecord)mBackStack.get(j));
                    j++;
                } while (true);
            }
        }
        fragmentmanagerstate = new FragmentManagerState();
        fragmentmanagerstate.mActive = afragmentstate;
        fragmentmanagerstate.mAdded = ai;
        fragmentmanagerstate.mBackStack = ((BackStackState []) (obj));
        if (mPrimaryNav != null)
        {
            fragmentmanagerstate.mPrimaryNavActiveIndex = mPrimaryNav.mIndex;
        }
        fragmentmanagerstate.mNextFragmentIndex = mNextFragmentIndex;
        saveNonConfig();
        return fragmentmanagerstate;
    }

    final void scheduleCommit()
    {
        boolean flag1 = true;
        this;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (mPostponedTransactions != null && !mPostponedTransactions.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (mPendingActions == null || mPendingActions.size() != 1)
        {
            flag1 = false;
        }
          goto _L1
_L2:
        mHost.mHandler.removeCallbacks(mExecCommit);
        mHost.mHandler.post(mExecCommit);
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L1:
        if (!flag && !flag1) goto _L3; else goto _L2
    }

    public final void setPrimaryNavigationFragment(Fragment fragment)
    {
        if (fragment != null && (mActive.get(fragment.mIndex) != fragment || fragment.mHost != null && fragment.mFragmentManager != this))
        {
            throw new IllegalArgumentException((new StringBuilder("Fragment ")).append(fragment).append(" is not an active fragment of FragmentManager ").append(this).toString());
        } else
        {
            mPrimaryNav = fragment;
            return;
        }
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("FragmentManager{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" in ");
        if (mParent != null)
        {
            DebugUtils.buildShortClassTag(mParent, stringbuilder);
        } else
        {
            DebugUtils.buildShortClassTag(mHost, stringbuilder);
        }
        stringbuilder.append("}}");
        return stringbuilder.toString();
    }

    static 
    {
        new AccelerateInterpolator(2.5F);
        new AccelerateInterpolator(1.5F);
    }

    private class _cls1
        implements Runnable
    {

        private final FragmentManagerImpl this$0;

        public final void run()
        {
            execPendingActions();
        }

        _cls1()
        {
            this$0 = FragmentManagerImpl.this;
            super();
        }
    }


    private class FragmentLifecycleCallbacksHolder
    {

        public final boolean mRecursive;
    }


    private class StartEnterTransitionListener
        implements Fragment.OnStartEnterTransitionListener
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

        StartEnterTransitionListener(BackStackRecord backstackrecord, boolean flag)
        {
            mIsBack = flag;
            mRecord = backstackrecord;
        }
    }


    private class OpGenerator
    {

        public abstract boolean generateOps(ArrayList arraylist, ArrayList arraylist1);
    }


    private class AnimationOrAnimator
    {

        public final Animation animation;
        public final Animator animator;

        AnimationOrAnimator(Animator animator1)
        {
            animation = null;
            animator = animator1;
            if (animator1 == null)
            {
                throw new IllegalStateException("Animator cannot be null");
            } else
            {
                return;
            }
        }

        AnimationOrAnimator(Animation animation1)
        {
            animation = animation1;
            animator = null;
            if (animation1 == null)
            {
                throw new IllegalStateException("Animation cannot be null");
            } else
            {
                return;
            }
        }
    }


    private class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter
    {

        private View mView;

        public final void onAnimationEnd(Animator animator)
        {
            mView.setLayerType(0, null);
            animator.removeListener(this);
        }

        public final void onAnimationStart(Animator animator)
        {
            mView.setLayerType(2, null);
        }

        AnimatorOnHWLayerIfNeededListener(View view)
        {
            mView = view;
        }
    }


    private class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper
    {
        private class AnimationListenerWrapper
            implements android.view.animation.Animation.AnimationListener
        {

            private final android.view.animation.Animation.AnimationListener mWrapped;

            public void onAnimationEnd(Animation animation)
            {
                if (mWrapped != null)
                {
                    mWrapped.onAnimationEnd(animation);
                }
            }

            public void onAnimationRepeat(Animation animation)
            {
                if (mWrapped != null)
                {
                    mWrapped.onAnimationRepeat(animation);
                }
            }

            public void onAnimationStart(Animation animation)
            {
                if (mWrapped != null)
                {
                    mWrapped.onAnimationStart(animation);
                }
            }

            AnimationListenerWrapper(android.view.animation.Animation.AnimationListener animationlistener)
            {
                mWrapped = animationlistener;
            }
        }


        public View mView;

        public final void onAnimationEnd(Animation animation)
        {
            class _cls1
                implements Runnable
            {

                private final AnimateOnHWLayerIfNeededListener this$0;

                public final void run()
                {
                    mView.setLayerType(0, null);
                }

                _cls1()
                {
                    this$0 = AnimateOnHWLayerIfNeededListener.this;
                    super();
                }
            }

            if (ViewCompat.isAttachedToWindow(mView) || android.os.Build.VERSION.SDK_INT >= 24)
            {
                mView.post(new _cls1());
            } else
            {
                mView.setLayerType(0, null);
            }
            super.onAnimationEnd(animation);
        }

        AnimateOnHWLayerIfNeededListener(View view, android.view.animation.Animation.AnimationListener animationlistener)
        {
            super(animationlistener);
            mView = view;
        }
    }


    private class _cls4 extends AnimatorListenerAdapter
    {

        private final View val$animatingView;
        private final ViewGroup val$container;
        private final Fragment val$fragment;

        public final void onAnimationEnd(Animator animator)
        {
            container.endViewTransition(animatingView);
            animator.removeListener(this);
            if (fragment.mView != null)
            {
                fragment.mView.setVisibility(8);
            }
        }

        _cls4()
        {
            container = viewgroup;
            animatingView = view;
            fragment = fragment1;
            super();
        }
    }


    private class EndViewTransitionAnimator extends AnimationSet
        implements Runnable
    {

        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        public final boolean getTransformation(long l, Transformation transformation)
        {
            if (!mEnded) goto _L2; else goto _L1
_L1:
            if (mTransitionEnded) goto _L4; else goto _L3
_L3:
            return true;
_L4:
            return false;
_L2:
            if (!super.getTransformation(l, transformation))
            {
                mEnded = true;
                transformation = mParent;
                OneShotPreDrawListener oneshotpredrawlistener = new OneShotPreDrawListener(transformation, this);
                transformation.getViewTreeObserver().addOnPreDrawListener(oneshotpredrawlistener);
                transformation.addOnAttachStateChangeListener(oneshotpredrawlistener);
                return true;
            }
            if (true) goto _L3; else goto _L5
_L5:
        }

        public final boolean getTransformation(long l, Transformation transformation, float f)
        {
            if (!mEnded) goto _L2; else goto _L1
_L1:
            if (mTransitionEnded) goto _L4; else goto _L3
_L3:
            return true;
_L4:
            return false;
_L2:
            if (!super.getTransformation(l, transformation, f))
            {
                mEnded = true;
                transformation = mParent;
                OneShotPreDrawListener oneshotpredrawlistener = new OneShotPreDrawListener(transformation, this);
                transformation.getViewTreeObserver().addOnPreDrawListener(oneshotpredrawlistener);
                transformation.addOnAttachStateChangeListener(oneshotpredrawlistener);
                return true;
            }
            if (true) goto _L3; else goto _L5
_L5:
        }

        public final void run()
        {
            mParent.endViewTransition(mChild);
            mTransitionEnded = true;
        }

        EndViewTransitionAnimator(Animation animation, ViewGroup viewgroup, View view)
        {
            super(false);
            mParent = viewgroup;
            mChild = view;
            addAnimation(animation);
        }
    }


    private class _cls2 extends AnimationListenerWrapper
    {

        public final FragmentManagerImpl this$0;
        private final ViewGroup val$container;
        public final Fragment val$fragment;

        public final void onAnimationEnd(Animation animation)
        {
            super.onAnimationEnd(animation);
            class _cls1
                implements Runnable
            {

                private final _cls2 this$1;

                public final void run()
                {
                    Object obj = fragment;
                    if (((Fragment) (obj)).mAnimationInfo == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = ((Fragment) (obj)).mAnimationInfo.mAnimatingAway;
                    }
                    if (obj != null)
                    {
                        obj = fragment;
                        if (((Fragment) (obj)).mAnimationInfo == null)
                        {
                            obj.mAnimationInfo = new Fragment.AnimationInfo();
                        }
                        ((Fragment) (obj)).mAnimationInfo.mAnimatingAway = null;
                        obj = _fld0;
                        Fragment fragment1 = fragment;
                        Fragment fragment2 = fragment;
                        int i;
                        if (fragment2.mAnimationInfo == null)
                        {
                            i = 0;
                        } else
                        {
                            i = fragment2.mAnimationInfo.mStateAfterAnimating;
                        }
                        ((FragmentManagerImpl) (obj)).moveToState(fragment1, i, 0, 0, false);
                    }
                }

                _cls1()
                {
                    this$1 = _cls2.this;
                    super();
                }
            }

            container.post(new _cls1());
        }

        _cls2(Fragment fragment1)
        {
            this$0 = FragmentManagerImpl.this;
            container = viewgroup;
            fragment = fragment1;
            super(final_animationlistener);
        }
    }


    private class _cls3 extends AnimatorListenerAdapter
    {

        private final FragmentManagerImpl this$0;
        private final ViewGroup val$container;
        private final Fragment val$fragment;
        private final View val$viewToAnimate;

        public final void onAnimationEnd(Animator animator)
        {
            container.endViewTransition(viewToAnimate);
            animator = fragment;
            Fragment fragment1;
            if (((Fragment) (animator)).mAnimationInfo == null)
            {
                animator = null;
            } else
            {
                animator = ((Fragment) (animator)).mAnimationInfo.mAnimator;
            }
            fragment1 = fragment;
            if (fragment1.mAnimationInfo == null)
            {
                fragment1.mAnimationInfo = new Fragment.AnimationInfo();
            }
            fragment1.mAnimationInfo.mAnimator = null;
            if (animator != null && container.indexOfChild(viewToAnimate) < 0)
            {
                animator = FragmentManagerImpl.this;
                Fragment fragment2 = fragment;
                Fragment fragment3 = fragment;
                int i;
                if (fragment3.mAnimationInfo == null)
                {
                    i = 0;
                } else
                {
                    i = fragment3.mAnimationInfo.mStateAfterAnimating;
                }
                animator.moveToState(fragment2, i, 0, 0, false);
            }
        }

        _cls3()
        {
            this$0 = FragmentManagerImpl.this;
            container = viewgroup;
            viewToAnimate = view;
            fragment = fragment1;
            super();
        }
    }


    private class FragmentTag
    {

        public static final int Fragment[] = {
            0x1010003, 0x10100d0, 0x10100d1
        };

    }


    private class PopBackStackState
        implements OpGenerator
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

        PopBackStackState(String s, int i, int j)
        {
            this$0 = FragmentManagerImpl.this;
            super();
            mId = i;
            mFlags = j;
        }
    }

}
