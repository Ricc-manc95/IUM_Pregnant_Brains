// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelStore;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.ContainerHelpers;
import android.support.v4.util.SparseArrayCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package android.support.v4.app:
//            SupportActivity, FragmentController, FragmentManager, Fragment, 
//            FragmentHostCallback, FragmentManagerImpl, LoaderManagerImpl, LoaderManager, 
//            ActivityCompat

public class FragmentActivity extends SupportActivity
    implements ViewModelStoreOwner, ActivityCompat.OnRequestPermissionsResultCallback, ActivityCompat.RequestPermissionsRequestCodeValidator
{

    private boolean mCreated;
    public final FragmentController mFragments = new FragmentController(new HostCallbacks());
    public final Handler mHandler = new _cls1();
    private int mNextCandidateRequestIndex;
    private SparseArrayCompat mPendingFragmentActivityResults;
    private boolean mReallyStopped;
    public boolean mRequestedPermissionsFromFragment;
    private boolean mResumed;
    private boolean mRetaining;
    public boolean mStartedActivityFromFragment;
    public boolean mStopped;
    private ViewModelStore mViewModelStore;

    public FragmentActivity()
    {
        mStopped = true;
        mReallyStopped = true;
    }

    private static boolean markState(FragmentManager fragmentmanager, android.arch.lifecycle.Lifecycle.State state)
    {
        fragmentmanager = fragmentmanager.getFragments().iterator();
        boolean flag1 = false;
        do
        {
            if (!fragmentmanager.hasNext())
            {
                break;
            }
            Object obj = (Fragment)fragmentmanager.next();
            if (obj != null)
            {
                boolean flag;
                if (((Fragment) (obj)).mLifecycleRegistry.getCurrentState().compareTo(android.arch.lifecycle.Lifecycle.State.STARTED) >= 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    ((Fragment) (obj)).mLifecycleRegistry.moveToState(state);
                    flag1 = true;
                }
                obj = ((Fragment) (obj)).mChildFragmentManager;
                if (obj != null)
                {
                    flag1 = markState(((FragmentManager) (obj)), state) | flag1;
                }
            }
        } while (true);
        return flag1;
    }

    final int allocateRequestIndex(Fragment fragment)
    {
        SparseArrayCompat sparsearraycompat = mPendingFragmentActivityResults;
        if (sparsearraycompat.mGarbage)
        {
            sparsearraycompat.gc();
        }
        if (sparsearraycompat.mSize >= 65534)
        {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
        do
        {
            SparseArrayCompat sparsearraycompat1 = mPendingFragmentActivityResults;
            int i = mNextCandidateRequestIndex;
            if (sparsearraycompat1.mGarbage)
            {
                sparsearraycompat1.gc();
            }
            if (ContainerHelpers.binarySearch(sparsearraycompat1.mKeys, sparsearraycompat1.mSize, i) >= 0)
            {
                mNextCandidateRequestIndex = (mNextCandidateRequestIndex + 1) % 65534;
            } else
            {
                int j = mNextCandidateRequestIndex;
                mPendingFragmentActivityResults.put(j, fragment.mWho);
                mNextCandidateRequestIndex = (mNextCandidateRequestIndex + 1) % 65534;
                return j;
            }
        } while (true);
    }

    final void doReallyStop(boolean flag)
    {
        if (!mReallyStopped)
        {
            mReallyStopped = true;
            mRetaining = flag;
            mHandler.removeMessages(1);
            mFragments.mHost.mFragmentManager.dispatchStateChange(2);
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        super.dump(s, filedescriptor, printwriter, as);
        printwriter.print(s);
        printwriter.print("Local FragmentActivity ");
        printwriter.print(Integer.toHexString(System.identityHashCode(this)));
        printwriter.println(" State:");
        String s1 = (new StringBuilder()).append(s).append("  ").toString();
        printwriter.print(s1);
        printwriter.print("mCreated=");
        printwriter.print(mCreated);
        printwriter.print(" mResumed=");
        printwriter.print(mResumed);
        printwriter.print(" mStopped=");
        printwriter.print(mStopped);
        printwriter.print(" mReallyStopped=");
        printwriter.println(mReallyStopped);
        (new LoaderManagerImpl(this, ((ViewModelStoreOwner)this).getViewModelStore())).dump(s1, filedescriptor, printwriter, as);
        mFragments.mHost.mFragmentManager.dump(s, filedescriptor, printwriter, as);
    }

    public final ViewModelStore getViewModelStore()
    {
        if (getApplication() == null)
        {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (mViewModelStore == null)
        {
            mViewModelStore = new ViewModelStore();
        }
        return mViewModelStore;
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        int k;
        mFragments.mHost.mFragmentManager.noteStateNotSaved();
        k = i >> 16;
        if (k == 0) goto _L2; else goto _L1
_L1:
        Object obj;
        k--;
        obj = mPendingFragmentActivityResults;
        int l = ContainerHelpers.binarySearch(((SparseArrayCompat) (obj)).mKeys, ((SparseArrayCompat) (obj)).mSize, k);
        SparseArrayCompat sparsearraycompat;
        if (l < 0 || ((SparseArrayCompat) (obj)).mValues[l] == SparseArrayCompat.DELETED)
        {
            obj = null;
        } else
        {
            obj = ((SparseArrayCompat) (obj)).mValues[l];
        }
        obj = (String)obj;
        sparsearraycompat = mPendingFragmentActivityResults;
        k = ContainerHelpers.binarySearch(sparsearraycompat.mKeys, sparsearraycompat.mSize, k);
        if (k >= 0 && sparsearraycompat.mValues[k] != SparseArrayCompat.DELETED)
        {
            sparsearraycompat.mValues[k] = SparseArrayCompat.DELETED;
            sparsearraycompat.mGarbage = true;
        }
        if (obj != null) goto _L4; else goto _L3
_L3:
        Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
_L6:
        return;
_L4:
        Fragment fragment = mFragments.mHost.mFragmentManager.findFragmentByWho(((String) (obj)));
        if (fragment == null)
        {
            Log.w("FragmentActivity", (new StringBuilder("Activity result no fragment exists for who: ")).append(((String) (obj))).toString());
            return;
        } else
        {
            fragment.onActivityResult(0xffff & i, j, intent);
            return;
        }
_L2:
        ActivityCompat.PermissionCompatDelegate permissioncompatdelegate = ActivityCompat.getPermissionCompatDelegate();
        if (permissioncompatdelegate == null || !permissioncompatdelegate.onActivityResult$51662RJ4E9NMIP1FC5O70BQ1CDQ6ITJ9EHSJMIA99HGMSP3IDTKM8BR3DTN78PBEEGNKIRJKCLN78EP9B8______0())
        {
            super.onActivityResult(i, j, intent);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void onAttachFragment(Fragment fragment)
    {
    }

    public void onBackPressed()
    {
        FragmentManagerImpl fragmentmanagerimpl = mFragments.mHost.mFragmentManager;
        for (boolean flag = fragmentmanagerimpl.isStateSaved(); flag && android.os.Build.VERSION.SDK_INT <= 25 || !flag && fragmentmanagerimpl.popBackStackImmediate();)
        {
            return;
        }

        super.onBackPressed();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mFragments.mHost.mFragmentManager.noteStateNotSaved();
        mFragments.mHost.mFragmentManager.dispatchConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle)
    {
        Object obj = mFragments;
        ((FragmentController) (obj)).mHost.mFragmentManager.attachController(((FragmentController) (obj)).mHost, ((FragmentController) (obj)).mHost, null);
        super.onCreate(bundle);
        obj = (NonConfigurationInstances)getLastNonConfigurationInstance();
        if (obj != null)
        {
            mViewModelStore = ((NonConfigurationInstances) (obj)).viewModelStore;
        }
        if (bundle != null)
        {
            android.os.Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            FragmentController fragmentcontroller = mFragments;
            if (obj != null)
            {
                obj = ((NonConfigurationInstances) (obj)).fragments;
            } else
            {
                obj = null;
            }
            fragmentcontroller.mHost.mFragmentManager.restoreAllState(parcelable, ((FragmentManagerNonConfig) (obj)));
            if (bundle.containsKey("android:support:next_request_index"))
            {
                mNextCandidateRequestIndex = bundle.getInt("android:support:next_request_index");
                obj = bundle.getIntArray("android:support:request_indicies");
                bundle = bundle.getStringArray("android:support:request_fragment_who");
                if (obj == null || bundle == null || obj.length != bundle.length)
                {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                } else
                {
                    mPendingFragmentActivityResults = new SparseArrayCompat(obj.length);
                    int i = 0;
                    while (i < obj.length) 
                    {
                        mPendingFragmentActivityResults.put(obj[i], bundle[i]);
                        i++;
                    }
                }
            }
        }
        if (mPendingFragmentActivityResults == null)
        {
            mPendingFragmentActivityResults = new SparseArrayCompat();
            mNextCandidateRequestIndex = 0;
        }
        bundle = mFragments.mHost.mFragmentManager;
        bundle.mStateSaved = false;
        bundle.mStopped = false;
        bundle.dispatchStateChange(1);
    }

    public boolean onCreatePanelMenu(int i, Menu menu)
    {
        if (i == 0)
        {
            boolean flag = super.onCreatePanelMenu(i, menu);
            FragmentController fragmentcontroller = mFragments;
            android.view.MenuInflater menuinflater = getMenuInflater();
            return flag | fragmentcontroller.mHost.mFragmentManager.dispatchCreateOptionsMenu(menu, menuinflater);
        } else
        {
            return super.onCreatePanelMenu(i, menu);
        }
    }

    public View onCreateView(View view, String s, Context context, AttributeSet attributeset)
    {
        View view2 = mFragments.mHost.mFragmentManager.onCreateView(view, s, context, attributeset);
        View view1 = view2;
        if (view2 == null)
        {
            view1 = super.onCreateView(view, s, context, attributeset);
        }
        return view1;
    }

    public View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        View view1 = mFragments.mHost.mFragmentManager.onCreateView(null, s, context, attributeset);
        View view = view1;
        if (view1 == null)
        {
            view = super.onCreateView(s, context, attributeset);
        }
        return view;
    }

    public void onDestroy()
    {
        super.onDestroy();
        if (!mReallyStopped)
        {
            mReallyStopped = true;
            mRetaining = false;
            mHandler.removeMessages(1);
            mFragments.mHost.mFragmentManager.dispatchStateChange(2);
        }
        if (mViewModelStore != null && !mRetaining)
        {
            mViewModelStore.clear();
        }
        FragmentManagerImpl fragmentmanagerimpl = mFragments.mHost.mFragmentManager;
        fragmentmanagerimpl.mDestroyed = true;
        fragmentmanagerimpl.execPendingActions();
        fragmentmanagerimpl.dispatchStateChange(0);
        fragmentmanagerimpl.mHost = null;
        fragmentmanagerimpl.mContainer = null;
        fragmentmanagerimpl.mParent = null;
    }

    public void onLowMemory()
    {
        super.onLowMemory();
        mFragments.mHost.mFragmentManager.dispatchLowMemory();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        if (super.onMenuItemSelected(i, menuitem))
        {
            return true;
        }
        switch (i)
        {
        default:
            return false;

        case 0: // '\0'
            return mFragments.mHost.mFragmentManager.dispatchOptionsItemSelected(menuitem);

        case 6: // '\006'
            return mFragments.mHost.mFragmentManager.dispatchContextItemSelected(menuitem);
        }
    }

    public void onMultiWindowModeChanged(boolean flag)
    {
        mFragments.mHost.mFragmentManager.dispatchMultiWindowModeChanged(flag);
    }

    public void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        mFragments.mHost.mFragmentManager.noteStateNotSaved();
    }

    public void onPanelClosed(int i, Menu menu)
    {
        i;
        JVM INSTR tableswitch 0 0: default 20
    //                   0 27;
           goto _L1 _L2
_L1:
        super.onPanelClosed(i, menu);
        return;
_L2:
        mFragments.mHost.mFragmentManager.dispatchOptionsMenuClosed(menu);
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void onPause()
    {
        super.onPause();
        mResumed = false;
        if (mHandler.hasMessages(2))
        {
            mHandler.removeMessages(2);
            FragmentManagerImpl fragmentmanagerimpl = mFragments.mHost.mFragmentManager;
            fragmentmanagerimpl.mStateSaved = false;
            fragmentmanagerimpl.mStopped = false;
            fragmentmanagerimpl.dispatchStateChange(5);
        }
        mFragments.mHost.mFragmentManager.dispatchStateChange(4);
    }

    public void onPictureInPictureModeChanged(boolean flag)
    {
        mFragments.mHost.mFragmentManager.dispatchPictureInPictureModeChanged(flag);
    }

    public void onPostResume()
    {
        super.onPostResume();
        mHandler.removeMessages(2);
        FragmentManagerImpl fragmentmanagerimpl = mFragments.mHost.mFragmentManager;
        fragmentmanagerimpl.mStateSaved = false;
        fragmentmanagerimpl.mStopped = false;
        fragmentmanagerimpl.dispatchStateChange(5);
        mFragments.mHost.mFragmentManager.execPendingActions();
    }

    public boolean onPreparePanel(int i, View view, Menu menu)
    {
        if (i == 0 && menu != null)
        {
            return super.onPreparePanel(0, view, menu) | mFragments.mHost.mFragmentManager.dispatchPrepareOptionsMenu(menu);
        } else
        {
            return super.onPreparePanel(i, view, menu);
        }
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
        Object obj;
label0:
        {
            mFragments.mHost.mFragmentManager.noteStateNotSaved();
            int j = i >> 16 & 0xffff;
            if (j != 0)
            {
                j--;
                obj = mPendingFragmentActivityResults;
                int k = ContainerHelpers.binarySearch(((SparseArrayCompat) (obj)).mKeys, ((SparseArrayCompat) (obj)).mSize, j);
                SparseArrayCompat sparsearraycompat;
                if (k < 0 || ((SparseArrayCompat) (obj)).mValues[k] == SparseArrayCompat.DELETED)
                {
                    obj = null;
                } else
                {
                    obj = ((SparseArrayCompat) (obj)).mValues[k];
                }
                obj = (String)obj;
                sparsearraycompat = mPendingFragmentActivityResults;
                j = ContainerHelpers.binarySearch(sparsearraycompat.mKeys, sparsearraycompat.mSize, j);
                if (j >= 0 && sparsearraycompat.mValues[j] != SparseArrayCompat.DELETED)
                {
                    sparsearraycompat.mValues[j] = SparseArrayCompat.DELETED;
                    sparsearraycompat.mGarbage = true;
                }
                if (obj != null)
                {
                    break label0;
                }
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            }
            return;
        }
        Fragment fragment = mFragments.mHost.mFragmentManager.findFragmentByWho(((String) (obj)));
        if (fragment == null)
        {
            Log.w("FragmentActivity", (new StringBuilder("Activity result no fragment exists for who: ")).append(((String) (obj))).toString());
            return;
        } else
        {
            fragment.onRequestPermissionsResult(i & 0xffff, as, ai);
            return;
        }
    }

    public void onResume()
    {
        super.onResume();
        mHandler.sendEmptyMessage(2);
        mResumed = true;
        mFragments.mHost.mFragmentManager.execPendingActions();
    }

    public Object onRetainCustomNonConfigurationInstance()
    {
        return null;
    }

    public final Object onRetainNonConfigurationInstance()
    {
        if (mStopped && !mReallyStopped)
        {
            mReallyStopped = true;
            mRetaining = true;
            mHandler.removeMessages(1);
            mFragments.mHost.mFragmentManager.dispatchStateChange(2);
        }
        Object obj = onRetainCustomNonConfigurationInstance();
        Object obj1 = mFragments.mHost.mFragmentManager;
        FragmentManagerImpl.setRetaining(((FragmentManagerImpl) (obj1)).mSavedNonConfig);
        obj1 = ((FragmentManagerImpl) (obj1)).mSavedNonConfig;
        if (obj1 == null && mViewModelStore == null && obj == null)
        {
            return null;
        } else
        {
            NonConfigurationInstances nonconfigurationinstances = new NonConfigurationInstances();
            nonconfigurationinstances.custom = obj;
            nonconfigurationinstances.viewModelStore = mViewModelStore;
            nonconfigurationinstances.fragments = ((FragmentManagerNonConfig) (obj1));
            return nonconfigurationinstances;
        }
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        while (markState(mFragments.mHost.mFragmentManager, android.arch.lifecycle.Lifecycle.State.CREATED)) ;
        Object obj = mFragments.mHost.mFragmentManager.saveAllState();
        if (obj != null)
        {
            bundle.putParcelable("android:support:fragments", ((android.os.Parcelable) (obj)));
        }
        obj = mPendingFragmentActivityResults;
        if (((SparseArrayCompat) (obj)).mGarbage)
        {
            ((SparseArrayCompat) (obj)).gc();
        }
        if (((SparseArrayCompat) (obj)).mSize > 0)
        {
            bundle.putInt("android:support:next_request_index", mNextCandidateRequestIndex);
            SparseArrayCompat sparsearraycompat = mPendingFragmentActivityResults;
            if (sparsearraycompat.mGarbage)
            {
                sparsearraycompat.gc();
            }
            int ai[] = new int[sparsearraycompat.mSize];
            SparseArrayCompat sparsearraycompat1 = mPendingFragmentActivityResults;
            if (sparsearraycompat1.mGarbage)
            {
                sparsearraycompat1.gc();
            }
            String as[] = new String[sparsearraycompat1.mSize];
            int i = 0;
            do
            {
                SparseArrayCompat sparsearraycompat2 = mPendingFragmentActivityResults;
                if (sparsearraycompat2.mGarbage)
                {
                    sparsearraycompat2.gc();
                }
                if (i >= sparsearraycompat2.mSize)
                {
                    break;
                }
                sparsearraycompat2 = mPendingFragmentActivityResults;
                if (sparsearraycompat2.mGarbage)
                {
                    sparsearraycompat2.gc();
                }
                ai[i] = sparsearraycompat2.mKeys[i];
                sparsearraycompat2 = mPendingFragmentActivityResults;
                if (sparsearraycompat2.mGarbage)
                {
                    sparsearraycompat2.gc();
                }
                as[i] = (String)sparsearraycompat2.mValues[i];
                i++;
            } while (true);
            bundle.putIntArray("android:support:request_indicies", ai);
            bundle.putStringArray("android:support:request_fragment_who", as);
        }
    }

    public void onStart()
    {
        super.onStart();
        mStopped = false;
        mReallyStopped = false;
        mHandler.removeMessages(1);
        if (!mCreated)
        {
            mCreated = true;
            FragmentManagerImpl fragmentmanagerimpl = mFragments.mHost.mFragmentManager;
            fragmentmanagerimpl.mStateSaved = false;
            fragmentmanagerimpl.mStopped = false;
            fragmentmanagerimpl.dispatchStateChange(2);
        }
        mFragments.mHost.mFragmentManager.noteStateNotSaved();
        mFragments.mHost.mFragmentManager.execPendingActions();
        FragmentManagerImpl fragmentmanagerimpl1 = mFragments.mHost.mFragmentManager;
        fragmentmanagerimpl1.mStateSaved = false;
        fragmentmanagerimpl1.mStopped = false;
        fragmentmanagerimpl1.dispatchStateChange(4);
    }

    public void onStateNotSaved()
    {
        mFragments.mHost.mFragmentManager.noteStateNotSaved();
    }

    public void onStop()
    {
        super.onStop();
        mStopped = true;
        while (markState(mFragments.mHost.mFragmentManager, android.arch.lifecycle.Lifecycle.State.CREATED)) ;
        mHandler.sendEmptyMessage(1);
        FragmentManagerImpl fragmentmanagerimpl = mFragments.mHost.mFragmentManager;
        fragmentmanagerimpl.mStopped = true;
        fragmentmanagerimpl.dispatchStateChange(3);
    }

    public void startActivityForResult(Intent intent, int i)
    {
        if (!mStartedActivityFromFragment && i != -1 && (0xffff0000 & i) != 0)
        {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        } else
        {
            super.startActivityForResult(intent, i);
            return;
        }
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle)
    {
        if (!mStartedActivityFromFragment && i != -1 && (0xffff0000 & i) != 0)
        {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        } else
        {
            super.startActivityForResult(intent, i, bundle);
            return;
        }
    }

    public void startIntentSenderForResult(IntentSender intentsender, int i, Intent intent, int j, int k, int l)
        throws android.content.IntentSender.SendIntentException
    {
        if (i != -1 && (0xffff0000 & i) != 0)
        {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        } else
        {
            super.startIntentSenderForResult(intentsender, i, intent, j, k, l);
            return;
        }
    }

    public void startIntentSenderForResult(IntentSender intentsender, int i, Intent intent, int j, int k, int l, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        if (i != -1 && (0xffff0000 & i) != 0)
        {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        } else
        {
            super.startIntentSenderForResult(intentsender, i, intent, j, k, l, bundle);
            return;
        }
    }

    public void supportInvalidateOptionsMenu()
    {
        invalidateOptionsMenu();
    }

    public final void validateRequestPermissionsRequestCode(int i)
    {
        if (!mRequestedPermissionsFromFragment && i != -1 && (0xffff0000 & i) != 0)
        {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        } else
        {
            return;
        }
    }

    private class _cls1 extends Handler
    {

        private final FragmentActivity this$0;

        public final void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 34
        //                       2 53;
               goto _L1 _L2 _L3
_L1:
            super.handleMessage(message);
_L5:
            return;
_L2:
            if (!mStopped) goto _L5; else goto _L4
_L4:
            doReallyStop(false);
            return;
_L3:
            message = mFragments.mHost.mFragmentManager;
            message.mStateSaved = false;
            message.mStopped = false;
            message.dispatchStateChange(5);
            mFragments.mHost.mFragmentManager.execPendingActions();
            return;
        }

        _cls1()
        {
            this$0 = FragmentActivity.this;
            super();
        }
    }


    private class HostCallbacks extends FragmentHostCallback
    {

        private final FragmentActivity this$0;

        public final void onAttachFragment(Fragment fragment)
        {
            FragmentActivity.this.onAttachFragment(fragment);
        }

        public final void onDump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            dump(s, null, printwriter, as);
        }

        public final View onFindViewById(int i)
        {
            return findViewById(i);
        }

        public final LayoutInflater onGetLayoutInflater()
        {
            return getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        public final int onGetWindowAnimations()
        {
            Window window = getWindow();
            if (window == null)
            {
                return 0;
            } else
            {
                return window.getAttributes().windowAnimations;
            }
        }

        public final boolean onHasView()
        {
            Window window = getWindow();
            return window != null && window.peekDecorView() != null;
        }

        public final boolean onHasWindowAnimations()
        {
            return getWindow() != null;
        }

        public final void onRequestPermissionsFromFragment(Fragment fragment, String as[], int i)
        {
            FragmentActivity fragmentactivity;
            fragmentactivity = FragmentActivity.this;
            if (i == -1)
            {
                ActivityCompat.requestPermissions(fragmentactivity, as, i);
                return;
            }
            if ((0xffff0000 & i) != 0)
            {
                throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
            }
            fragmentactivity.mRequestedPermissionsFromFragment = true;
            ActivityCompat.requestPermissions(fragmentactivity, as, (fragmentactivity.allocateRequestIndex(fragment) + 1 << 16) + (0xffff & i));
            fragmentactivity.mRequestedPermissionsFromFragment = false;
            return;
            fragment;
            fragmentactivity.mRequestedPermissionsFromFragment = false;
            throw fragment;
        }

        public final boolean onShouldSaveFragmentState$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78EP9B8______0()
        {
            return !isFinishing();
        }

        public final boolean onShouldShowRequestPermissionRationale(String s)
        {
            return ActivityCompat.shouldShowRequestPermissionRationale(FragmentActivity.this, s);
        }

        public final void onStartActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle)
        {
            FragmentActivity fragmentactivity;
            fragmentactivity = FragmentActivity.this;
            fragmentactivity.mStartedActivityFromFragment = true;
            if (i != -1)
            {
                break MISSING_BLOCK_LABEL_33;
            }
            ActivityCompat.startActivityForResult(fragmentactivity, intent, -1, bundle);
            fragmentactivity.mStartedActivityFromFragment = false;
            return;
            if ((0xffff0000 & i) == 0)
            {
                break MISSING_BLOCK_LABEL_59;
            }
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
            fragment;
            fragmentactivity.mStartedActivityFromFragment = false;
            throw fragment;
            ActivityCompat.startActivityForResult(fragmentactivity, intent, (fragmentactivity.allocateRequestIndex(fragment) + 1 << 16) + (0xffff & i), bundle);
            fragmentactivity.mStartedActivityFromFragment = false;
            return;
        }

        public final void onSupportInvalidateOptionsMenu()
        {
            supportInvalidateOptionsMenu();
        }

        public HostCallbacks()
        {
            this$0 = FragmentActivity.this;
            super(FragmentActivity.this);
        }
    }


    private class NonConfigurationInstances
    {

        public Object custom;
        public FragmentManagerNonConfig fragments;
        public ViewModelStore viewModelStore;

        NonConfigurationInstances()
        {
        }
    }

}
