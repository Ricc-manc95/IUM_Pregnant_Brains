// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelStore;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// Referenced classes of package android.support.v4.app:
//            FragmentManager, FragmentManagerImpl, LoaderManagerImpl, LoaderManager, 
//            FragmentHostCallback, FragmentActivity, FragmentManagerNonConfig, SharedElementCallback

public class Fragment
    implements LifecycleOwner, ViewModelStoreOwner, ComponentCallbacks, android.view.View.OnCreateContextMenuListener
{
    public static final class AnimationInfo
    {

        public View mAnimatingAway;
        public Animator mAnimator;
        public Object mEnterTransition;
        public SharedElementCallback mEnterTransitionCallback;
        public Object mExitTransition;
        public SharedElementCallback mExitTransitionCallback;
        public boolean mIsHideReplaced;
        public int mNextAnim;
        public int mNextTransition;
        public int mNextTransitionStyle;
        public Object mReenterTransition;
        public Object mReturnTransition;
        public Object mSharedElementEnterTransition;
        public Object mSharedElementReturnTransition;
        public OnStartEnterTransitionListener mStartEnterTransitionListener;
        public int mStateAfterAnimating;

        public AnimationInfo()
        {
            mEnterTransition = null;
            mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
            mExitTransition = null;
            mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
            mSharedElementEnterTransition = null;
            mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
            mEnterTransitionCallback = null;
            mExitTransitionCallback = null;
        }
    }

    public static final class InstantiationException extends RuntimeException
    {

        public InstantiationException(String s, Exception exception)
        {
            super(s, exception);
        }
    }

    static interface OnStartEnterTransitionListener
    {

        public abstract void startListening();
    }


    public static final Object USE_DEFAULT_TRANSITION = new Object();
    private static final SimpleArrayMap sClassMap = new SimpleArrayMap();
    public boolean mAdded;
    public AnimationInfo mAnimationInfo;
    public Bundle mArguments;
    public int mBackStackNesting;
    public boolean mCalled;
    public FragmentManagerImpl mChildFragmentManager;
    public FragmentManagerNonConfig mChildNonConfig;
    public ViewGroup mContainer;
    public int mContainerId;
    public boolean mDeferStart;
    public boolean mDetached;
    public int mFragmentId;
    public FragmentManagerImpl mFragmentManager;
    public boolean mFromLayout;
    public boolean mHasMenu;
    public boolean mHidden;
    public boolean mHiddenChanged;
    public FragmentHostCallback mHost;
    public boolean mInLayout;
    public int mIndex;
    public View mInnerView;
    public boolean mIsCreated;
    public boolean mIsNewlyAdded;
    public LayoutInflater mLayoutInflater;
    public LifecycleRegistry mLifecycleRegistry;
    public boolean mMenuVisible;
    public Fragment mParentFragment;
    public boolean mPerformedCreateView;
    public float mPostponedAlpha;
    public boolean mRemoving;
    public boolean mRestored;
    public boolean mRetainInstance;
    public boolean mRetaining;
    public Bundle mSavedFragmentState;
    public Boolean mSavedUserVisibleHint;
    public SparseArray mSavedViewState;
    public int mState;
    public String mTag;
    public Fragment mTarget;
    public int mTargetIndex;
    public int mTargetRequestCode;
    public boolean mUserVisibleHint;
    public View mView;
    public LifecycleOwner mViewLifecycleOwner;
    public MutableLiveData mViewLifecycleOwnerLiveData;
    public LifecycleRegistry mViewLifecycleRegistry;
    public ViewModelStore mViewModelStore;
    public String mWho;

    public Fragment()
    {
        mState = 0;
        mIndex = -1;
        mTargetIndex = -1;
        mMenuVisible = true;
        mUserVisibleHint = true;
        mLifecycleRegistry = new LifecycleRegistry(this);
        mViewLifecycleOwnerLiveData = new MutableLiveData();
    }

    public static Fragment instantiate(Context context, String s)
    {
        return instantiate(context, s, null);
    }

    public static Fragment instantiate(Context context, String s, Bundle bundle)
    {
        boolean flag = false;
        Class class1;
        Class class2;
        try
        {
            class2 = (Class)sClassMap.get(s);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new InstantiationException((new StringBuilder("Unable to instantiate fragment ")).append(s).append(": make sure class name exists, is public, and has an empty constructor that is public").toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new InstantiationException((new StringBuilder("Unable to instantiate fragment ")).append(s).append(": make sure class name exists, is public, and has an empty constructor that is public").toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new InstantiationException((new StringBuilder("Unable to instantiate fragment ")).append(s).append(": make sure class name exists, is public, and has an empty constructor that is public").toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new InstantiationException((new StringBuilder("Unable to instantiate fragment ")).append(s).append(": could not find Fragment constructor").toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new InstantiationException((new StringBuilder("Unable to instantiate fragment ")).append(s).append(": calling Fragment constructor caused an exception").toString(), context);
        }
        class1 = class2;
        if (class2 != null)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        class1 = context.getClassLoader().loadClass(s);
        sClassMap.put(s, class1);
        context = (Fragment)class1.getConstructor(new Class[0]).newInstance(new Object[0]);
        if (bundle == null)
        {
            break MISSING_BLOCK_LABEL_152;
        }
        bundle.setClassLoader(context.getClass().getClassLoader());
        if (((Fragment) (context)).mIndex < 0)
        {
            break MISSING_BLOCK_LABEL_147;
        }
        if (((Fragment) (context)).mFragmentManager != null)
        {
            break MISSING_BLOCK_LABEL_135;
        }
_L1:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_147;
        }
        throw new IllegalStateException("Fragment already active and state has been saved");
        flag = ((Fragment) (context)).mFragmentManager.isStateSaved();
          goto _L1
        context.mArguments = bundle;
        return context;
    }

    private final void instantiateChildFragmentManager()
    {
        if (mHost == null)
        {
            throw new IllegalStateException("Fragment has not been attached yet.");
        } else
        {
            mChildFragmentManager = new FragmentManagerImpl();
            mChildFragmentManager.attachController(mHost, new _cls2(), this);
            return;
        }
    }

    static boolean isSupportFragmentClass(Context context, String s)
    {
        Class class1;
        Class class2;
        boolean flag;
        try
        {
            class2 = (Class)sClassMap.get(s);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return false;
        }
        class1 = class2;
        if (class2 != null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        class1 = context.getClassLoader().loadClass(s);
        sClassMap.put(s, class1);
        flag = android/support/v4/app/Fragment.isAssignableFrom(class1);
        return flag;
    }

    public static void onAttachFragment$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78EP9AO______0()
    {
    }

    public static Animation onCreateAnimation$514LKI999HGMSP3IDTKM8BRMD5INEBR1DPKMQOBKD5NMSBQ1DPKMQOBKD5NMSEO_0()
    {
        return null;
    }

    public static Animator onCreateAnimator$514LKI999HGMSP3IDTKM8BR1DPKMQOBKD5NMSBQ1DPKMQOBKDTP3M___0()
    {
        return null;
    }

    public static void onDestroyOptionsMenu()
    {
    }

    public static void onHiddenChanged$51D2ILG_0()
    {
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        boolean flag = false;
        printwriter.print(s);
        printwriter.print("mFragmentId=#");
        printwriter.print(Integer.toHexString(mFragmentId));
        printwriter.print(" mContainerId=#");
        printwriter.print(Integer.toHexString(mContainerId));
        printwriter.print(" mTag=");
        printwriter.println(mTag);
        printwriter.print(s);
        printwriter.print("mState=");
        printwriter.print(mState);
        printwriter.print(" mIndex=");
        printwriter.print(mIndex);
        printwriter.print(" mWho=");
        printwriter.print(mWho);
        printwriter.print(" mBackStackNesting=");
        printwriter.println(mBackStackNesting);
        printwriter.print(s);
        printwriter.print("mAdded=");
        printwriter.print(mAdded);
        printwriter.print(" mRemoving=");
        printwriter.print(mRemoving);
        printwriter.print(" mFromLayout=");
        printwriter.print(mFromLayout);
        printwriter.print(" mInLayout=");
        printwriter.println(mInLayout);
        printwriter.print(s);
        printwriter.print("mHidden=");
        printwriter.print(mHidden);
        printwriter.print(" mDetached=");
        printwriter.print(mDetached);
        printwriter.print(" mMenuVisible=");
        printwriter.print(mMenuVisible);
        printwriter.print(" mHasMenu=");
        printwriter.println(mHasMenu);
        printwriter.print(s);
        printwriter.print("mRetainInstance=");
        printwriter.print(mRetainInstance);
        printwriter.print(" mRetaining=");
        printwriter.print(mRetaining);
        printwriter.print(" mUserVisibleHint=");
        printwriter.println(mUserVisibleHint);
        if (mFragmentManager != null)
        {
            printwriter.print(s);
            printwriter.print("mFragmentManager=");
            printwriter.println(mFragmentManager);
        }
        if (mHost != null)
        {
            printwriter.print(s);
            printwriter.print("mHost=");
            printwriter.println(mHost);
        }
        if (mParentFragment != null)
        {
            printwriter.print(s);
            printwriter.print("mParentFragment=");
            printwriter.println(mParentFragment);
        }
        if (mArguments != null)
        {
            printwriter.print(s);
            printwriter.print("mArguments=");
            printwriter.println(mArguments);
        }
        if (mSavedFragmentState != null)
        {
            printwriter.print(s);
            printwriter.print("mSavedFragmentState=");
            printwriter.println(mSavedFragmentState);
        }
        if (mSavedViewState != null)
        {
            printwriter.print(s);
            printwriter.print("mSavedViewState=");
            printwriter.println(mSavedViewState);
        }
        if (mTarget != null)
        {
            printwriter.print(s);
            printwriter.print("mTarget=");
            printwriter.print(mTarget);
            printwriter.print(" mTargetRequestCode=");
            printwriter.println(mTargetRequestCode);
        }
        View view;
        int i;
        if (mAnimationInfo == null)
        {
            i = 0;
        } else
        {
            i = mAnimationInfo.mNextAnim;
        }
        if (i != 0)
        {
            printwriter.print(s);
            printwriter.print("mNextAnim=");
            if (mAnimationInfo == null)
            {
                i = 0;
            } else
            {
                i = mAnimationInfo.mNextAnim;
            }
            printwriter.println(i);
        }
        if (mContainer != null)
        {
            printwriter.print(s);
            printwriter.print("mContainer=");
            printwriter.println(mContainer);
        }
        if (mView != null)
        {
            printwriter.print(s);
            printwriter.print("mView=");
            printwriter.println(mView);
        }
        if (mInnerView != null)
        {
            printwriter.print(s);
            printwriter.print("mInnerView=");
            printwriter.println(mView);
        }
        if (mAnimationInfo == null)
        {
            view = null;
        } else
        {
            view = mAnimationInfo.mAnimatingAway;
        }
        if (view != null)
        {
            printwriter.print(s);
            printwriter.print("mAnimatingAway=");
            if (mAnimationInfo == null)
            {
                view = null;
            } else
            {
                view = mAnimationInfo.mAnimatingAway;
            }
            printwriter.println(view);
            printwriter.print(s);
            printwriter.print("mStateAfterAnimating=");
            if (mAnimationInfo == null)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = mAnimationInfo.mStateAfterAnimating;
            }
            printwriter.println(i);
        }
        (new LoaderManagerImpl(this, ((ViewModelStoreOwner)this).getViewModelStore())).dump(s, filedescriptor, printwriter, as);
        if (mChildFragmentManager != null)
        {
            printwriter.print(s);
            printwriter.println((new StringBuilder("Child ")).append(mChildFragmentManager).append(":").toString());
            mChildFragmentManager.dump((new StringBuilder()).append(s).append("  ").toString(), filedescriptor, printwriter, as);
        }
    }

    public final Bundle getArguments()
    {
        return mArguments;
    }

    public final FragmentManager getChildFragmentManager()
    {
        if (mChildFragmentManager != null) goto _L2; else goto _L1
_L1:
        instantiateChildFragmentManager();
        if (mState < 5) goto _L4; else goto _L3
_L3:
        FragmentManagerImpl fragmentmanagerimpl = mChildFragmentManager;
        fragmentmanagerimpl.mStateSaved = false;
        fragmentmanagerimpl.mStopped = false;
        fragmentmanagerimpl.dispatchStateChange(5);
_L2:
        return mChildFragmentManager;
_L4:
        if (mState >= 4)
        {
            FragmentManagerImpl fragmentmanagerimpl1 = mChildFragmentManager;
            fragmentmanagerimpl1.mStateSaved = false;
            fragmentmanagerimpl1.mStopped = false;
            fragmentmanagerimpl1.dispatchStateChange(4);
        } else
        if (mState >= 2)
        {
            FragmentManagerImpl fragmentmanagerimpl2 = mChildFragmentManager;
            fragmentmanagerimpl2.mStateSaved = false;
            fragmentmanagerimpl2.mStopped = false;
            fragmentmanagerimpl2.dispatchStateChange(2);
        } else
        if (mState > 0)
        {
            FragmentManagerImpl fragmentmanagerimpl3 = mChildFragmentManager;
            fragmentmanagerimpl3.mStateSaved = false;
            fragmentmanagerimpl3.mStopped = false;
            fragmentmanagerimpl3.dispatchStateChange(1);
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public Context getContext()
    {
        if (mHost == null)
        {
            return null;
        } else
        {
            return mHost.mContext;
        }
    }

    public final Lifecycle getLifecycle()
    {
        return mLifecycleRegistry;
    }

    public final Object getReenterTransition()
    {
        if (mAnimationInfo != null)
        {
            if (mAnimationInfo.mReenterTransition == USE_DEFAULT_TRANSITION)
            {
                if (mAnimationInfo != null)
                {
                    return mAnimationInfo.mExitTransition;
                }
            } else
            {
                return mAnimationInfo.mReenterTransition;
            }
        }
        return null;
    }

    public final Object getReturnTransition()
    {
        if (mAnimationInfo != null)
        {
            if (mAnimationInfo.mReturnTransition == USE_DEFAULT_TRANSITION)
            {
                if (mAnimationInfo != null)
                {
                    return mAnimationInfo.mEnterTransition;
                }
            } else
            {
                return mAnimationInfo.mReturnTransition;
            }
        }
        return null;
    }

    public final Object getSharedElementReturnTransition()
    {
        if (mAnimationInfo != null)
        {
            if (mAnimationInfo.mSharedElementReturnTransition == USE_DEFAULT_TRANSITION)
            {
                if (mAnimationInfo != null)
                {
                    return mAnimationInfo.mSharedElementEnterTransition;
                }
            } else
            {
                return mAnimationInfo.mSharedElementReturnTransition;
            }
        }
        return null;
    }

    public final ViewModelStore getViewModelStore()
    {
        if (getContext() == null)
        {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (mViewModelStore == null)
        {
            mViewModelStore = new ViewModelStore();
        }
        return mViewModelStore;
    }

    public final boolean isVisible()
    {
        boolean flag;
        if (mHost != null && mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && !mHidden && mView != null && mView.getWindowToken() != null && mView.getVisibility() == 0;
    }

    public void onActivityCreated(Bundle bundle)
    {
        mCalled = true;
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
    }

    public void onAttach(Activity activity)
    {
        mCalled = true;
    }

    public void onAttach(Context context)
    {
        mCalled = true;
        if (mHost == null)
        {
            context = null;
        } else
        {
            context = mHost.mActivity;
        }
        if (context != null)
        {
            mCalled = false;
            onAttach(((Activity) (context)));
        }
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        mCalled = true;
    }

    public void onCreate(Bundle bundle)
    {
        mCalled = true;
        restoreChildFragmentState(bundle);
        if (mChildFragmentManager != null)
        {
            boolean flag;
            if (mChildFragmentManager.mCurState > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                bundle = mChildFragmentManager;
                bundle.mStateSaved = false;
                bundle.mStopped = false;
                bundle.dispatchStateChange(1);
            }
        }
    }

    public void onCreateContextMenu(ContextMenu contextmenu, View view, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
        FragmentActivity fragmentactivity;
        if (mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)mHost.mActivity;
        }
        fragmentactivity.onCreateContextMenu(contextmenu, view, contextmenuinfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return null;
    }

    public void onDestroy()
    {
        mCalled = true;
        if (mViewModelStore != null && !mHost.mFragmentManager.mStateSaved)
        {
            mViewModelStore.clear();
        }
    }

    public void onDestroyView()
    {
        mCalled = true;
        if (mView != null)
        {
            mViewLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_DESTROY));
        }
    }

    public void onDetach()
    {
        mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle)
    {
        if (mHost == null)
        {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        } else
        {
            bundle = mHost.onGetLayoutInflater();
            getChildFragmentManager();
            bundle.setFactory2(mChildFragmentManager);
            return bundle;
        }
    }

    public final void onInflate$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FELQ6IR1F85Q78SJ9C9QN8PAJCLQ3MJ31DPI74RR9CGNMUSPF89QMSP3CCKTIILG_0(AttributeSet attributeset, Bundle bundle)
    {
        mCalled = true;
        if (mHost == null)
        {
            attributeset = null;
        } else
        {
            attributeset = mHost.mActivity;
        }
        if (attributeset != null)
        {
            mCalled = false;
            mCalled = true;
        }
    }

    public void onLowMemory()
    {
        mCalled = true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onPause()
    {
        mCalled = true;
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
    }

    public void onResume()
    {
        mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle)
    {
    }

    public void onStart()
    {
        mCalled = true;
    }

    public void onStop()
    {
        mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle)
    {
    }

    public void onViewStateRestored(Bundle bundle)
    {
        mCalled = true;
        if (mView != null)
        {
            mViewLifecycleRegistry.moveToState(LifecycleRegistry.getStateAfter(android.arch.lifecycle.Lifecycle.Event.ON_CREATE));
        }
    }

    final void performCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        if (mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
        }
        mPerformedCreateView = true;
        mViewLifecycleOwner = new _cls3();
        mViewLifecycleRegistry = null;
        mView = onCreateView(layoutinflater, viewgroup, bundle);
        if (mView != null)
        {
            mViewLifecycleOwner.getLifecycle();
            mViewLifecycleOwnerLiveData.setValue(mViewLifecycleOwner);
            return;
        }
        if (mViewLifecycleRegistry != null)
        {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        } else
        {
            mViewLifecycleOwner = null;
            return;
        }
    }

    public final void requestPermissions(String as[], int i)
    {
        if (mHost == null)
        {
            throw new IllegalStateException((new StringBuilder("Fragment ")).append(this).append(" not attached to Activity").toString());
        } else
        {
            mHost.onRequestPermissionsFromFragment(this, as, i);
            return;
        }
    }

    public final Context requireContext()
    {
        Context context = getContext();
        if (context == null)
        {
            throw new IllegalStateException((new StringBuilder("Fragment ")).append(this).append(" not attached to a context.").toString());
        } else
        {
            return context;
        }
    }

    final void restoreChildFragmentState(Bundle bundle)
    {
        if (bundle != null)
        {
            bundle = bundle.getParcelable("android:support:fragments");
            if (bundle != null)
            {
                if (mChildFragmentManager == null)
                {
                    instantiateChildFragmentManager();
                }
                mChildFragmentManager.restoreAllState(bundle, mChildNonConfig);
                mChildNonConfig = null;
                bundle = mChildFragmentManager;
                bundle.mStateSaved = false;
                bundle.mStopped = false;
                bundle.dispatchStateChange(1);
            }
        }
    }

    public final void setArguments(Bundle bundle)
    {
        if (mIndex >= 0)
        {
            boolean flag;
            if (mFragmentManager == null)
            {
                flag = false;
            } else
            {
                flag = mFragmentManager.isStateSaved();
            }
            if (flag)
            {
                throw new IllegalStateException("Fragment already active and state has been saved");
            }
        }
        mArguments = bundle;
    }

    public final void setEnterTransition(Object obj)
    {
        if (mAnimationInfo == null)
        {
            mAnimationInfo = new AnimationInfo();
        }
        mAnimationInfo.mEnterTransition = obj;
    }

    public final void setExitTransition(Object obj)
    {
        if (mAnimationInfo == null)
        {
            mAnimationInfo = new AnimationInfo();
        }
        mAnimationInfo.mExitTransition = obj;
    }

    public final void setHasOptionsMenu(boolean flag)
    {
        boolean flag1 = true;
        if (!mHasMenu)
        {
            mHasMenu = true;
            if (mHost == null || !mAdded)
            {
                flag1 = false;
            }
            if (flag1 && !mHidden)
            {
                mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    final void setIndex(int i, Fragment fragment)
    {
        mIndex = i;
        if (fragment != null)
        {
            mWho = (new StringBuilder()).append(fragment.mWho).append(":").append(mIndex).toString();
            return;
        } else
        {
            mWho = (new StringBuilder("android:fragment:")).append(mIndex).toString();
            return;
        }
    }

    public final void setMenuVisibility(boolean flag)
    {
        if (mMenuVisible != flag)
        {
            mMenuVisible = flag;
            if (mHasMenu)
            {
                boolean flag1;
                if (mHost != null && mAdded)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1 && !mHidden)
                {
                    mHost.onSupportInvalidateOptionsMenu();
                }
            }
        }
    }

    final void setNextTransition(int i, int j)
    {
        if (mAnimationInfo == null && i == 0 && j == 0)
        {
            return;
        }
        if (mAnimationInfo == null)
        {
            mAnimationInfo = new AnimationInfo();
        }
        AnimationInfo animationinfo = mAnimationInfo;
        mAnimationInfo.mNextTransition = i;
        mAnimationInfo.mNextTransitionStyle = j;
    }

    final void setOnStartEnterTransitionListener(OnStartEnterTransitionListener onstartentertransitionlistener)
    {
        if (mAnimationInfo == null)
        {
            mAnimationInfo = new AnimationInfo();
        }
        AnimationInfo animationinfo = mAnimationInfo;
        if (onstartentertransitionlistener != mAnimationInfo.mStartEnterTransitionListener)
        {
            if (onstartentertransitionlistener != null && mAnimationInfo.mStartEnterTransitionListener != null)
            {
                throw new IllegalStateException((new StringBuilder("Trying to set a replacement startPostponedEnterTransition on ")).append(this).toString());
            }
            if (onstartentertransitionlistener != null)
            {
                onstartentertransitionlistener.startListening();
                return;
            }
        }
    }

    public final void setReenterTransition(Object obj)
    {
        if (mAnimationInfo == null)
        {
            mAnimationInfo = new AnimationInfo();
        }
        mAnimationInfo.mReenterTransition = obj;
    }

    public final void setReturnTransition(Object obj)
    {
        if (mAnimationInfo == null)
        {
            mAnimationInfo = new AnimationInfo();
        }
        mAnimationInfo.mReturnTransition = obj;
    }

    public final void setTargetFragment(Fragment fragment, int i)
    {
        FragmentManagerImpl fragmentmanagerimpl1 = mFragmentManager;
        FragmentManagerImpl fragmentmanagerimpl;
        if (fragment != null)
        {
            fragmentmanagerimpl = fragment.mFragmentManager;
        } else
        {
            fragmentmanagerimpl = null;
        }
        if (fragmentmanagerimpl1 != null && fragmentmanagerimpl != null && fragmentmanagerimpl1 != fragmentmanagerimpl)
        {
            throw new IllegalArgumentException((new StringBuilder("Fragment ")).append(fragment).append(" must share the same FragmentManager to be set as a target fragment").toString());
        }
        for (Fragment fragment1 = fragment; fragment1 != null; fragment1 = fragment1.mTarget)
        {
            if (fragment1 == this)
            {
                throw new IllegalArgumentException((new StringBuilder("Setting ")).append(fragment).append(" as the target of ").append(this).append(" would create a target cycle").toString());
            }
        }

        mTarget = fragment;
        mTargetRequestCode = i;
    }

    public final void setUserVisibleHint(boolean flag)
    {
        boolean flag2 = true;
        if (!mUserVisibleHint && flag && mState < 4 && mFragmentManager != null)
        {
            boolean flag1;
            if (mHost != null && mAdded)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 && mIsCreated)
            {
                mFragmentManager.performPendingDeferredStart(this);
            }
        }
        mUserVisibleHint = flag;
        if (mState >= 4 || flag)
        {
            flag2 = false;
        }
        mDeferStart = flag2;
        if (mSavedFragmentState != null)
        {
            mSavedUserVisibleHint = Boolean.valueOf(flag);
        }
    }

    public void startActivityForResult(Intent intent, int i)
    {
        if (mHost == null)
        {
            throw new IllegalStateException((new StringBuilder("Fragment ")).append(this).append(" not attached to Activity").toString());
        } else
        {
            mHost.onStartActivityFromFragment(this, intent, i, null);
            return;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        DebugUtils.buildShortClassTag(this, stringbuilder);
        if (mIndex >= 0)
        {
            stringbuilder.append(" #");
            stringbuilder.append(mIndex);
        }
        if (mFragmentId != 0)
        {
            stringbuilder.append(" id=0x");
            stringbuilder.append(Integer.toHexString(mFragmentId));
        }
        if (mTag != null)
        {
            stringbuilder.append(" ");
            stringbuilder.append(mTag);
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }


    private class _cls2 extends FragmentContainer
    {

        private final Fragment this$0;

        public final Fragment instantiate(Context context, String s, Bundle bundle)
        {
            return mHost.instantiate(context, s, bundle);
        }

        public final View onFindViewById(int i)
        {
            if (mView == null)
            {
                throw new IllegalStateException("Fragment does not have a view");
            } else
            {
                return mView.findViewById(i);
            }
        }

        public final boolean onHasView()
        {
            return mView != null;
        }

        _cls2()
        {
            this$0 = Fragment.this;
            super();
        }
    }


    private class _cls3
        implements LifecycleOwner
    {

        private final Fragment this$0;

        public final Lifecycle getLifecycle()
        {
            if (mViewLifecycleRegistry == null)
            {
                mViewLifecycleRegistry = new LifecycleRegistry(mViewLifecycleOwner);
            }
            return mViewLifecycleRegistry;
        }

        _cls3()
        {
            this$0 = Fragment.this;
            super();
        }
    }

}
