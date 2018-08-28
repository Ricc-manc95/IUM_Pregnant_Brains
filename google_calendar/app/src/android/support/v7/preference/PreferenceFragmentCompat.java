// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.preference.internal.AbstractMultiSelectListPreference;
import android.support.v7.widget.AdapterHelper;
import android.support.v7.widget.ChildHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.preference:
//            PreferenceManager, PreferenceInflater, PreferenceScreen, Preference, 
//            PreferenceGroup, PreferenceGroupAdapter, PreferenceRecyclerViewAccessibilityDelegate, EditTextPreference, 
//            EditTextPreferenceDialogFragmentCompat, ListPreference, ListPreferenceDialogFragmentCompat, MultiSelectListPreferenceDialogFragmentCompat

public abstract class PreferenceFragmentCompat extends Fragment
    implements DialogPreference.TargetFragment, PreferenceManager.OnDisplayPreferenceDialogListener, PreferenceManager.OnNavigateToScreenListener, PreferenceManager.OnPreferenceTreeClickListener
{

    private final DividerDecoration mDividerDecoration = new DividerDecoration();
    private Handler mHandler;
    private boolean mHavePrefs;
    private boolean mInitDone;
    private int mLayoutResId;
    public RecyclerView mList;
    public PreferenceManager mPreferenceManager;
    private final Runnable mRequestFocus = new _cls2();
    private Context mStyledContext;

    public PreferenceFragmentCompat()
    {
        mLayoutResId = 0x7f050118;
        mHandler = new _cls1();
    }

    public final void addPreferencesFromResource(int i)
    {
        boolean flag = false;
        if (mPreferenceManager == null)
        {
            throw new RuntimeException("This should be called after super.onCreate.");
        }
        PreferenceManager preferencemanager = mPreferenceManager;
        Object obj = mStyledContext;
        PreferenceScreen preferencescreen = mPreferenceManager.mPreferenceScreen;
        preferencemanager.mNoCommit = true;
        obj = (PreferenceScreen)(new PreferenceInflater(((Context) (obj)), preferencemanager)).inflate(i, preferencescreen);
        ((Preference) (obj)).onAttachedToHierarchy(preferencemanager);
        if (preferencemanager.mEditor != null)
        {
            preferencemanager.mEditor.apply();
        }
        preferencemanager.mNoCommit = false;
        preferencemanager = mPreferenceManager;
        i = ((flag) ? 1 : 0);
        if (obj != preferencemanager.mPreferenceScreen)
        {
            if (preferencemanager.mPreferenceScreen != null)
            {
                preferencemanager.mPreferenceScreen.onDetached();
            }
            preferencemanager.mPreferenceScreen = ((PreferenceScreen) (obj));
            i = 1;
        }
        if (i != 0 && obj != null)
        {
            mHavePrefs = true;
            if (mInitDone && !mHandler.hasMessages(1))
            {
                mHandler.obtainMessage(1).sendToTarget();
            }
        }
    }

    final void bindPreferences()
    {
        boolean flag1 = false;
        PreferenceScreen preferencescreen = mPreferenceManager.mPreferenceScreen;
        if (preferencescreen != null)
        {
            RecyclerView recyclerview = mList;
            Object obj = onCreateAdapter(preferencescreen);
            if (recyclerview.mLayoutFrozen)
            {
                recyclerview.assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
                recyclerview.mLayoutFrozen = false;
                if (recyclerview.mLayoutWasDefered && recyclerview.mLayout != null && recyclerview.mAdapter != null)
                {
                    recyclerview.requestLayout();
                }
                recyclerview.mLayoutWasDefered = false;
            }
            if (recyclerview.mAdapter != null)
            {
                android.support.v7.widget.RecyclerView.Adapter adapter = recyclerview.mAdapter;
                android.support.v7.widget.RecyclerView.RecyclerViewDataObserver recyclerviewdataobserver = recyclerview.mObserver;
                adapter.mObservable.unregisterObserver(recyclerviewdataobserver);
                recyclerview.mAdapter.onDetachedFromRecyclerView(recyclerview);
            }
            recyclerview.removeAndRecycleViews();
            Object obj1 = recyclerview.mAdapterHelper;
            ((AdapterHelper) (obj1)).recycleUpdateOpsAndClearList(((AdapterHelper) (obj1)).mPendingUpdates);
            ((AdapterHelper) (obj1)).recycleUpdateOpsAndClearList(((AdapterHelper) (obj1)).mPostponedList);
            obj1.mExistingUpdateTypes = 0;
            obj1 = recyclerview.mAdapter;
            recyclerview.mAdapter = ((android.support.v7.widget.RecyclerView.Adapter) (obj));
            if (obj != null)
            {
                android.support.v7.widget.RecyclerView.RecyclerViewDataObserver recyclerviewdataobserver1 = recyclerview.mObserver;
                ((android.support.v7.widget.RecyclerView.Adapter) (obj)).mObservable.registerObserver(recyclerviewdataobserver1);
                ((android.support.v7.widget.RecyclerView.Adapter) (obj)).onAttachedToRecyclerView(recyclerview);
            }
            Object obj2 = recyclerview.mRecycler;
            obj = recyclerview.mAdapter;
            ((android.support.v7.widget.RecyclerView.Recycler) (obj2)).mAttachedScrap.clear();
            ((android.support.v7.widget.RecyclerView.Recycler) (obj2)).recycleAndClearCachedViews();
            if (((android.support.v7.widget.RecyclerView.Recycler) (obj2)).mRecyclerPool == null)
            {
                obj2.mRecyclerPool = new android.support.v7.widget.RecyclerView.RecycledViewPool();
            }
            obj2 = ((android.support.v7.widget.RecyclerView.Recycler) (obj2)).mRecyclerPool;
            if (obj1 != null)
            {
                obj2.mAttachCount = ((android.support.v7.widget.RecyclerView.RecycledViewPool) (obj2)).mAttachCount - 1;
            }
            if (((android.support.v7.widget.RecyclerView.RecycledViewPool) (obj2)).mAttachCount == 0)
            {
                for (int i = 0; i < ((android.support.v7.widget.RecyclerView.RecycledViewPool) (obj2)).mScrap.size(); i++)
                {
                    ((android.support.v7.widget.RecyclerView.RecycledViewPool.ScrapData)((android.support.v7.widget.RecyclerView.RecycledViewPool) (obj2)).mScrap.valueAt(i)).mScrapHeap.clear();
                }

            }
            if (obj != null)
            {
                obj2.mAttachCount = ((android.support.v7.widget.RecyclerView.RecycledViewPool) (obj2)).mAttachCount + 1;
            }
            recyclerview.mState.mStructureChanged = true;
            recyclerview.mDispatchItemsChangedEvent = recyclerview.mDispatchItemsChangedEvent | false;
            recyclerview.mDataSetHasChangedAfterLayout = true;
            int i1 = recyclerview.mChildHelper.mCallback.getChildCount();
            int j = 0;
            while (j < i1) 
            {
                obj = RecyclerView.getChildViewHolderInt(recyclerview.mChildHelper.mCallback.getChildAt(j));
                if (obj == null)
                {
                    continue;
                }
                boolean flag;
                if ((((android.support.v7.widget.RecyclerView.ViewHolder) (obj)).mFlags & 0x80) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    obj.mFlags = ((android.support.v7.widget.RecyclerView.ViewHolder) (obj)).mFlags | 6;
                }
                j++;
            }
            recyclerview.markItemDecorInsetsDirty();
            obj = recyclerview.mRecycler;
            int l = ((android.support.v7.widget.RecyclerView.Recycler) (obj)).mCachedViews.size();
            for (int k = ((flag1) ? 1 : 0); k < l; k++)
            {
                android.support.v7.widget.RecyclerView.ViewHolder viewholder = (android.support.v7.widget.RecyclerView.ViewHolder)((android.support.v7.widget.RecyclerView.Recycler) (obj)).mCachedViews.get(k);
                if (viewholder != null)
                {
                    viewholder.mFlags = viewholder.mFlags | 6;
                    viewholder.addChangePayload(null);
                }
            }

            if (((android.support.v7.widget.RecyclerView.Recycler) (obj)).this$0.mAdapter == null || !((android.support.v7.widget.RecyclerView.Recycler) (obj)).this$0.mAdapter.mHasStableIds)
            {
                ((android.support.v7.widget.RecyclerView.Recycler) (obj)).recycleAndClearCachedViews();
            }
            recyclerview.requestLayout();
            preferencescreen.onAttached();
        }
    }

    public final Preference findPreference(CharSequence charsequence)
    {
        PreferenceManager preferencemanager;
        if (mPreferenceManager != null)
        {
            if ((preferencemanager = mPreferenceManager).mPreferenceScreen != null)
            {
                return preferencemanager.mPreferenceScreen.findPreference(charsequence);
            }
        }
        return null;
    }

    public final void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        if (bundle != null)
        {
            bundle = bundle.getBundle("android:preferences");
            if (bundle != null)
            {
                PreferenceScreen preferencescreen = mPreferenceManager.mPreferenceScreen;
                if (preferencescreen != null)
                {
                    preferencescreen.dispatchRestoreInstanceState(bundle);
                }
            }
        }
    }

    public final void onCreate(Bundle bundle)
    {
        Object obj = null;
        super.onCreate(bundle);
        TypedValue typedvalue = new TypedValue();
        int i;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        bundle.getTheme().resolveAttribute(0x7f010222, typedvalue, true);
        i = typedvalue.resourceId;
        if (i == 0)
        {
            throw new IllegalStateException("Must specify preferenceTheme in theme");
        }
        if (super.mHost == null)
        {
            bundle = obj;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        mStyledContext = new ContextThemeWrapper(bundle, i);
        mPreferenceManager = new PreferenceManager(mStyledContext);
        mPreferenceManager.mOnNavigateToScreenListener = this;
        if (getArguments() != null)
        {
            getArguments().getString("android.support.v7.preference.PreferenceFragmentCompat.PREFERENCE_ROOT");
        }
        onCreatePreferences$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0();
    }

    public android.support.v7.widget.RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferencescreen)
    {
        return new PreferenceGroupAdapter(preferencescreen);
    }

    public abstract void onCreatePreferences$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0();

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Object obj = null;
        bundle = mStyledContext.obtainStyledAttributes(null, R.styleable.PreferenceFragmentCompat, 0x7f010226, 0);
        mLayoutResId = bundle.getResourceId(R.styleable.PreferenceFragmentCompat_android_layout, mLayoutResId);
        Drawable drawable = bundle.getDrawable(R.styleable.PreferenceFragmentCompat_android_divider);
        int i = bundle.getDimensionPixelSize(R.styleable.PreferenceFragmentCompat_android_dividerHeight, -1);
        boolean flag = bundle.getBoolean(R.styleable.PreferenceFragmentCompat_allowDividerAfterLastItem, true);
        bundle.recycle();
        Object obj1 = new TypedValue();
        int j;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        bundle.getTheme().resolveAttribute(0x7f010222, ((TypedValue) (obj1)), true);
        j = ((TypedValue) (obj1)).resourceId;
        layoutinflater = layoutinflater.cloneInContext(new ContextThemeWrapper(layoutinflater.getContext(), j));
        viewgroup = layoutinflater.inflate(mLayoutResId, viewgroup, false);
        bundle = viewgroup.findViewById(0x102003f);
        if (!(bundle instanceof ViewGroup))
        {
            throw new RuntimeException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
        }
        bundle = (ViewGroup)bundle;
        obj1 = (RecyclerView)layoutinflater.inflate(0x7f05011a, bundle, false);
        if (super.mHost == null)
        {
            layoutinflater = obj;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        ((RecyclerView) (obj1)).setLayoutManager(new LinearLayoutManager(layoutinflater));
        obj1.mAccessibilityDelegate = new PreferenceRecyclerViewAccessibilityDelegate(((RecyclerView) (obj1)));
        ViewCompat.setAccessibilityDelegate(((View) (obj1)), ((RecyclerView) (obj1)).mAccessibilityDelegate);
        if (obj1 == null)
        {
            throw new RuntimeException("Could not create RecyclerView");
        }
        mList = ((RecyclerView) (obj1));
        layoutinflater = mDividerDecoration;
        if (((RecyclerView) (obj1)).mLayout != null)
        {
            ((RecyclerView) (obj1)).mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (((RecyclerView) (obj1)).mItemDecorations.isEmpty())
        {
            ((RecyclerView) (obj1)).setWillNotDraw(false);
        }
        ((RecyclerView) (obj1)).mItemDecorations.add(layoutinflater);
        ((RecyclerView) (obj1)).markItemDecorInsetsDirty();
        ((RecyclerView) (obj1)).requestLayout();
        layoutinflater = mDividerDecoration;
        if (drawable != null)
        {
            layoutinflater.mDividerHeight = drawable.getIntrinsicHeight();
        } else
        {
            layoutinflater.mDividerHeight = 0;
        }
        layoutinflater.mDivider = drawable;
        ((DividerDecoration) (layoutinflater))._fld0.mList.invalidateItemDecorations();
        if (i != -1)
        {
            layoutinflater = mDividerDecoration;
            layoutinflater.mDividerHeight = i;
            ((DividerDecoration) (layoutinflater))._fld0.mList.invalidateItemDecorations();
        }
        mDividerDecoration.mAllowDividerAfterLastItem = flag;
        bundle.addView(mList);
        mHandler.post(mRequestFocus);
        return viewgroup;
    }

    public final void onDestroyView()
    {
        mHandler.removeCallbacks(mRequestFocus);
        mHandler.removeMessages(1);
        if (mHavePrefs)
        {
            PreferenceScreen preferencescreen = mPreferenceManager.mPreferenceScreen;
            if (preferencescreen != null)
            {
                preferencescreen.onDetached();
            }
        }
        mList = null;
        super.onDestroyView();
    }

    public void onDisplayPreferenceDialog(Preference preference)
    {
        FragmentActivity fragmentactivity;
        boolean flag;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity instanceof OnPreferenceDisplayDialogCallback)
        {
            if (super.mHost == null)
            {
                fragmentactivity = null;
            } else
            {
                fragmentactivity = (FragmentActivity)super.mHost.mActivity;
            }
            flag = ((OnPreferenceDisplayDialogCallback)fragmentactivity)._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIKCSJ1CTMMARJK8DNMQS31EGTKOOBECHP6UQB45TPNAS3GDTP78BRM6SNN0SJ5CPIN4PBECDIIUK3ICLJ6ASJ5DPHMAEP9B8______0();
        } else
        {
            flag = false;
        }
        while (flag || super.mFragmentManager.findFragmentByTag("android.support.v14.preference.PreferenceFragment.DIALOG") != null) 
        {
            return;
        }
        if (preference instanceof EditTextPreference)
        {
            String s = preference.mKey;
            preference = new EditTextPreferenceDialogFragmentCompat();
            Bundle bundle = new Bundle(1);
            bundle.putString("key", s);
            preference.setArguments(bundle);
        } else
        if (preference instanceof ListPreference)
        {
            String s1 = preference.mKey;
            preference = new ListPreferenceDialogFragmentCompat();
            Bundle bundle1 = new Bundle(1);
            bundle1.putString("key", s1);
            preference.setArguments(bundle1);
        } else
        if (preference instanceof AbstractMultiSelectListPreference)
        {
            String s2 = preference.mKey;
            preference = new MultiSelectListPreferenceDialogFragmentCompat();
            Bundle bundle2 = new Bundle(1);
            bundle2.putString("key", s2);
            preference.setArguments(bundle2);
        } else
        {
            throw new IllegalArgumentException("Tried to display dialog for unknown preference type. Did you forget to override onDisplayPreferenceDialog()?");
        }
        preference.setTargetFragment(this, 0);
        preference.show(super.mFragmentManager, "android.support.v14.preference.PreferenceFragment.DIALOG");
    }

    public final void onNavigateToScreen(PreferenceScreen preferencescreen)
    {
        if (super.mHost == null)
        {
            preferencescreen = null;
        } else
        {
            preferencescreen = (FragmentActivity)super.mHost.mActivity;
        }
        if (preferencescreen instanceof OnPreferenceStartScreenCallback)
        {
            if (super.mHost == null)
            {
                preferencescreen = null;
            } else
            {
                preferencescreen = (FragmentActivity)super.mHost.mActivity;
            }
            ((OnPreferenceStartScreenCallback)preferencescreen)._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIKCSJ1CTMMARJK8DNMQS31EGTKOOBECHP6UQB45TPNAS3GDTP78BRM6SNN0SJ5CPIN4PBECDIIUK3ICLJ6ASJ5DPHMAKR3E9IMARHR55D0____0();
        }
    }

    public final boolean onPreferenceTreeClick(Preference preference)
    {
        if (preference.mFragment != null)
        {
            if (super.mHost == null)
            {
                preference = null;
            } else
            {
                preference = (FragmentActivity)super.mHost.mActivity;
            }
            if (preference instanceof OnPreferenceStartFragmentCallback)
            {
                if (super.mHost == null)
                {
                    preference = null;
                } else
                {
                    preference = (FragmentActivity)super.mHost.mActivity;
                }
                return ((OnPreferenceStartFragmentCallback)preference)._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIKCSJ1CTMMARJK8DNMQS31EGTKOOBECHP6UQB45TPNAS3GDTP78BRM6SNN0SJ5CPIN4PBECDIIUK3ICLJ6ASJ5DPHMAEP9B8______0();
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        PreferenceScreen preferencescreen = mPreferenceManager.mPreferenceScreen;
        if (preferencescreen != null)
        {
            Bundle bundle1 = new Bundle();
            preferencescreen.dispatchSaveInstanceState(bundle1);
            bundle.putBundle("android:preferences", bundle1);
        }
    }

    public void onStart()
    {
        super.onStart();
        mPreferenceManager.mOnPreferenceTreeClickListener = this;
        mPreferenceManager.mOnDisplayPreferenceDialogListener = this;
    }

    public final void onStop()
    {
        super.onStop();
        mPreferenceManager.mOnPreferenceTreeClickListener = null;
        mPreferenceManager.mOnDisplayPreferenceDialogListener = null;
    }

    public final void onViewCreated(View view, Bundle bundle)
    {
        boolean flag1 = false;
        super.onViewCreated(view, bundle);
        if (mHavePrefs)
        {
            view = mPreferenceManager.mPreferenceScreen;
            if (view != null)
            {
                bundle = mList;
                Object obj = onCreateAdapter(view);
                if (((RecyclerView) (bundle)).mLayoutFrozen)
                {
                    bundle.assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
                    bundle.mLayoutFrozen = false;
                    if (((RecyclerView) (bundle)).mLayoutWasDefered && ((RecyclerView) (bundle)).mLayout != null && ((RecyclerView) (bundle)).mAdapter != null)
                    {
                        bundle.requestLayout();
                    }
                    bundle.mLayoutWasDefered = false;
                }
                if (((RecyclerView) (bundle)).mAdapter != null)
                {
                    android.support.v7.widget.RecyclerView.Adapter adapter = ((RecyclerView) (bundle)).mAdapter;
                    android.support.v7.widget.RecyclerView.RecyclerViewDataObserver recyclerviewdataobserver = ((RecyclerView) (bundle)).mObserver;
                    adapter.mObservable.unregisterObserver(recyclerviewdataobserver);
                    ((RecyclerView) (bundle)).mAdapter.onDetachedFromRecyclerView(bundle);
                }
                bundle.removeAndRecycleViews();
                Object obj1 = ((RecyclerView) (bundle)).mAdapterHelper;
                ((AdapterHelper) (obj1)).recycleUpdateOpsAndClearList(((AdapterHelper) (obj1)).mPendingUpdates);
                ((AdapterHelper) (obj1)).recycleUpdateOpsAndClearList(((AdapterHelper) (obj1)).mPostponedList);
                obj1.mExistingUpdateTypes = 0;
                obj1 = ((RecyclerView) (bundle)).mAdapter;
                bundle.mAdapter = ((android.support.v7.widget.RecyclerView.Adapter) (obj));
                if (obj != null)
                {
                    android.support.v7.widget.RecyclerView.RecyclerViewDataObserver recyclerviewdataobserver1 = ((RecyclerView) (bundle)).mObserver;
                    ((android.support.v7.widget.RecyclerView.Adapter) (obj)).mObservable.registerObserver(recyclerviewdataobserver1);
                    ((android.support.v7.widget.RecyclerView.Adapter) (obj)).onAttachedToRecyclerView(bundle);
                }
                Object obj2 = ((RecyclerView) (bundle)).mRecycler;
                obj = ((RecyclerView) (bundle)).mAdapter;
                ((android.support.v7.widget.RecyclerView.Recycler) (obj2)).mAttachedScrap.clear();
                ((android.support.v7.widget.RecyclerView.Recycler) (obj2)).recycleAndClearCachedViews();
                if (((android.support.v7.widget.RecyclerView.Recycler) (obj2)).mRecyclerPool == null)
                {
                    obj2.mRecyclerPool = new android.support.v7.widget.RecyclerView.RecycledViewPool();
                }
                obj2 = ((android.support.v7.widget.RecyclerView.Recycler) (obj2)).mRecyclerPool;
                if (obj1 != null)
                {
                    obj2.mAttachCount = ((android.support.v7.widget.RecyclerView.RecycledViewPool) (obj2)).mAttachCount - 1;
                }
                if (((android.support.v7.widget.RecyclerView.RecycledViewPool) (obj2)).mAttachCount == 0)
                {
                    for (int i = 0; i < ((android.support.v7.widget.RecyclerView.RecycledViewPool) (obj2)).mScrap.size(); i++)
                    {
                        ((android.support.v7.widget.RecyclerView.RecycledViewPool.ScrapData)((android.support.v7.widget.RecyclerView.RecycledViewPool) (obj2)).mScrap.valueAt(i)).mScrapHeap.clear();
                    }

                }
                if (obj != null)
                {
                    obj2.mAttachCount = ((android.support.v7.widget.RecyclerView.RecycledViewPool) (obj2)).mAttachCount + 1;
                }
                ((RecyclerView) (bundle)).mState.mStructureChanged = true;
                bundle.mDispatchItemsChangedEvent = ((RecyclerView) (bundle)).mDispatchItemsChangedEvent | false;
                bundle.mDataSetHasChangedAfterLayout = true;
                int i1 = ((RecyclerView) (bundle)).mChildHelper.mCallback.getChildCount();
                int j = 0;
                while (j < i1) 
                {
                    obj = RecyclerView.getChildViewHolderInt(((RecyclerView) (bundle)).mChildHelper.mCallback.getChildAt(j));
                    if (obj == null)
                    {
                        continue;
                    }
                    boolean flag;
                    if ((((android.support.v7.widget.RecyclerView.ViewHolder) (obj)).mFlags & 0x80) != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        obj.mFlags = ((android.support.v7.widget.RecyclerView.ViewHolder) (obj)).mFlags | 6;
                    }
                    j++;
                }
                bundle.markItemDecorInsetsDirty();
                obj = ((RecyclerView) (bundle)).mRecycler;
                int l = ((android.support.v7.widget.RecyclerView.Recycler) (obj)).mCachedViews.size();
                for (int k = ((flag1) ? 1 : 0); k < l; k++)
                {
                    android.support.v7.widget.RecyclerView.ViewHolder viewholder = (android.support.v7.widget.RecyclerView.ViewHolder)((android.support.v7.widget.RecyclerView.Recycler) (obj)).mCachedViews.get(k);
                    if (viewholder != null)
                    {
                        viewholder.mFlags = viewholder.mFlags | 6;
                        viewholder.addChangePayload(null);
                    }
                }

                if (((android.support.v7.widget.RecyclerView.Recycler) (obj)).this$0.mAdapter == null || !((android.support.v7.widget.RecyclerView.Recycler) (obj)).this$0.mAdapter.mHasStableIds)
                {
                    ((android.support.v7.widget.RecyclerView.Recycler) (obj)).recycleAndClearCachedViews();
                }
                bundle.requestLayout();
                view.onAttached();
            }
        }
        mInitDone = true;
    }

    private class DividerDecoration extends android.support.v7.widget.RecyclerView.ItemDecoration
    {

        public boolean mAllowDividerAfterLastItem;
        public Drawable mDivider;
        public int mDividerHeight;
        public final PreferenceFragmentCompat this$0;

        private final boolean shouldDrawDividerBelow(View view, RecyclerView recyclerview)
        {
            android.support.v7.widget.RecyclerView.ViewHolder viewholder = recyclerview.getChildViewHolder(view);
            boolean flag;
            if ((viewholder instanceof PreferenceViewHolder) && ((PreferenceViewHolder)viewholder).mDividerAllowedBelow)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return false;
            }
            boolean flag1 = mAllowDividerAfterLastItem;
            int i = recyclerview.indexOfChild(view);
            if (i < recyclerview.getChildCount() - 1)
            {
                view = recyclerview.getChildViewHolder(recyclerview.getChildAt(i + 1));
                if ((view instanceof PreferenceViewHolder) && ((PreferenceViewHolder)view).mDividerAllowedAbove)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
            }
            return flag1;
        }

        public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerview, android.support.v7.widget.RecyclerView.State state)
        {
            if (shouldDrawDividerBelow(view, recyclerview))
            {
                rect.bottom = mDividerHeight;
            }
        }

        public final void onDrawOver$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN4H9N8OBKCKTIILG_0(Canvas canvas, RecyclerView recyclerview)
        {
            if (mDivider != null)
            {
                int j = recyclerview.getChildCount();
                int k = recyclerview.getWidth();
                int i = 0;
                while (i < j) 
                {
                    View view = recyclerview.getChildAt(i);
                    if (shouldDrawDividerBelow(view, recyclerview))
                    {
                        int l = (int)view.getY();
                        l = view.getHeight() + l;
                        mDivider.setBounds(0, l, k, mDividerHeight + l);
                        mDivider.draw(canvas);
                    }
                    i++;
                }
            }
        }

        DividerDecoration()
        {
            this$0 = PreferenceFragmentCompat.this;
            super();
            mAllowDividerAfterLastItem = true;
        }
    }


    private class _cls1 extends Handler
    {

        private final PreferenceFragmentCompat this$0;

        public final void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                return;

            case 1: // '\001'
                bindPreferences();
                break;
            }
        }

        _cls1()
        {
            this$0 = PreferenceFragmentCompat.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final PreferenceFragmentCompat this$0;

        public final void run()
        {
            mList.focusableViewAvailable(mList);
        }

        _cls2()
        {
            this$0 = PreferenceFragmentCompat.this;
            super();
        }
    }


    private class OnPreferenceDisplayDialogCallback
    {

        public abstract boolean onPreferenceDisplayDialog$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIKCSJ1CTMMARJK8DNMQS31EGTKOOBECHP6UQB45TPNAS3GDTP78BRM6SNN0SJ5CPIN4PBECDIIUK3ICLJ6ASJ5DPHMAEP9B8______0();
    }


    private class OnPreferenceStartScreenCallback
    {

        public abstract boolean onPreferenceStartScreen$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIKCSJ1CTMMARJK8DNMQS31EGTKOOBECHP6UQB45TPNAS3GDTP78BRM6SNN0SJ5CPIN4PBECDIIUK3ICLJ6ASJ5DPHMAKR3E9IMARHR55D0____0();
    }


    private class OnPreferenceStartFragmentCallback
    {

        public abstract boolean onPreferenceStartFragment$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIKCSJ1CTMMARJK8DNMQS31EGTKOOBECHP6UQB45TPNAS3GDTP78BRM6SNN0SJ5CPIN4PBECDIIUK3ICLJ6ASJ5DPHMAEP9B8______0();
    }

}
