// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v7.preference:
//            CollapsiblePreferenceGroupController, Preference, PreferenceScreen, PreferenceGroup, 
//            PreferenceViewHolder

public class PreferenceGroupAdapter extends android.support.v7.widget.RecyclerView.Adapter
    implements Preference.OnPreferenceChangeInternalListener
{

    public Handler mHandler;
    private PreferenceGroup mPreferenceGroup;
    private CollapsiblePreferenceGroupController mPreferenceGroupController;
    private List mPreferenceLayouts;
    public List mPreferenceList;
    private List mPreferenceListInternal;
    public Runnable mSyncRunnable;
    private PreferenceLayout mTempPreferenceLayout;

    public PreferenceGroupAdapter(PreferenceGroup preferencegroup)
    {
        this(preferencegroup, new Handler());
    }

    private PreferenceGroupAdapter(PreferenceGroup preferencegroup, Handler handler)
    {
        mTempPreferenceLayout = new PreferenceLayout();
        mSyncRunnable = new _cls1();
        mPreferenceGroup = preferencegroup;
        mHandler = handler;
        mPreferenceGroupController = new CollapsiblePreferenceGroupController(preferencegroup, this);
        mPreferenceGroup.mListener = this;
        mPreferenceList = new ArrayList();
        mPreferenceListInternal = new ArrayList();
        mPreferenceLayouts = new ArrayList();
        if (mPreferenceGroup instanceof PreferenceScreen)
        {
            boolean flag = ((PreferenceScreen)mPreferenceGroup).mShouldUseGeneratedIds;
            if (super.mObservable.hasObservers())
            {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            super.mHasStableIds = flag;
        } else
        {
            if (super.mObservable.hasObservers())
            {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            super.mHasStableIds = true;
        }
        syncMyPreferences();
    }

    private static PreferenceLayout createPreferenceLayout(Preference preference, PreferenceLayout preferencelayout)
    {
        if (preferencelayout == null)
        {
            preferencelayout = new PreferenceLayout();
        }
        preferencelayout.mName = preference.getClass().getName();
        preferencelayout.mResId = preference.mLayoutResId;
        preferencelayout.mWidgetResId = preference.mWidgetLayoutResId;
        return preferencelayout;
    }

    private final void flattenPreferenceGroup(List list, PreferenceGroup preferencegroup)
    {
        preferencegroup;
        JVM INSTR monitorenter ;
        Collections.sort(preferencegroup.mPreferenceList);
        preferencegroup;
        JVM INSTR monitorexit ;
        int j = preferencegroup.mPreferenceList.size();
        for (int i = 0; i < j; i++)
        {
            Preference preference = (Preference)preferencegroup.mPreferenceList.get(i);
            list.add(preference);
            PreferenceLayout preferencelayout = createPreferenceLayout(preference, null);
            if (!mPreferenceLayouts.contains(preferencelayout))
            {
                mPreferenceLayouts.add(preferencelayout);
            }
            if (preference instanceof PreferenceGroup)
            {
                PreferenceGroup preferencegroup1 = (PreferenceGroup)preference;
                if (preferencegroup1.isOnSameScreenAsChildren())
                {
                    flattenPreferenceGroup(list, preferencegroup1);
                }
            }
            preference.mListener = this;
        }

        break MISSING_BLOCK_LABEL_135;
        list;
        preferencegroup;
        JVM INSTR monitorexit ;
        throw list;
    }

    public final int getItemCount()
    {
        return mPreferenceList.size();
    }

    public final long getItemId(int i)
    {
        if (!super.mHasStableIds)
        {
            return -1L;
        }
        Preference preference;
        if (i < 0 || i >= getItemCount())
        {
            preference = null;
        } else
        {
            preference = (Preference)mPreferenceList.get(i);
        }
        return preference.getId();
    }

    public final int getItemViewType(int i)
    {
        Preference preference;
        if (i < 0 || i >= getItemCount())
        {
            preference = null;
        } else
        {
            preference = (Preference)mPreferenceList.get(i);
        }
        mTempPreferenceLayout = createPreferenceLayout(preference, mTempPreferenceLayout);
        i = mPreferenceLayouts.indexOf(mTempPreferenceLayout);
        if (i != -1)
        {
            return i;
        } else
        {
            i = mPreferenceLayouts.size();
            mPreferenceLayouts.add(new PreferenceLayout(mTempPreferenceLayout));
            return i;
        }
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        PreferenceViewHolder preferenceviewholder = (PreferenceViewHolder)viewholder;
        if (i < 0 || i >= getItemCount())
        {
            viewholder = null;
        } else
        {
            viewholder = (Preference)mPreferenceList.get(i);
        }
        viewholder.onBindViewHolder(preferenceviewholder);
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        PreferenceLayout preferencelayout = (PreferenceLayout)mPreferenceLayouts.get(i);
        LayoutInflater layoutinflater = LayoutInflater.from(viewgroup.getContext());
        TypedArray typedarray = viewgroup.getContext().obtainStyledAttributes(null, R.styleable.BackgroundStyle);
        android.graphics.drawable.Drawable drawable = typedarray.getDrawable(R.styleable.BackgroundStyle_android_selectableItemBackground);
        Object obj = drawable;
        if (drawable == null)
        {
            obj = ContextCompat.getDrawable(viewgroup.getContext(), 0x1080062);
        }
        typedarray.recycle();
        viewgroup = layoutinflater.inflate(preferencelayout.mResId, viewgroup, false);
        if (viewgroup.getBackground() == null)
        {
            ViewCompat.setBackground(viewgroup, ((android.graphics.drawable.Drawable) (obj)));
        }
        obj = (ViewGroup)viewgroup.findViewById(0x1020018);
        if (obj != null)
        {
            if (preferencelayout.mWidgetResId != 0)
            {
                layoutinflater.inflate(preferencelayout.mWidgetResId, ((ViewGroup) (obj)));
            } else
            {
                ((ViewGroup) (obj)).setVisibility(8);
            }
        }
        return new PreferenceViewHolder(viewgroup);
    }

    public final void onPreferenceChange(Preference preference)
    {
        int i = mPreferenceList.indexOf(preference);
        if (i != -1)
        {
            super.mObservable.notifyItemRangeChanged(i, 1, preference);
        }
    }

    public final void onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0()
    {
        mHandler.removeCallbacks(mSyncRunnable);
        mHandler.post(mSyncRunnable);
    }

    public final void onPreferenceVisibilityChange(Preference preference)
    {
        boolean flag = false;
        if (mPreferenceListInternal.contains(preference))
        {
            Object obj = mPreferenceGroupController;
            int i;
            if (((CollapsiblePreferenceGroupController) (obj)).mHasExpandablePreference)
            {
                obj = ((CollapsiblePreferenceGroupController) (obj)).mPreferenceGroupAdapter;
                ((PreferenceGroupAdapter) (obj)).mHandler.removeCallbacks(((PreferenceGroupAdapter) (obj)).mSyncRunnable);
                ((PreferenceGroupAdapter) (obj)).mHandler.post(((PreferenceGroupAdapter) (obj)).mSyncRunnable);
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                if (preference.mVisible)
                {
                    Iterator iterator = mPreferenceListInternal.iterator();
                    i = -1;
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            break;
                        }
                        Preference preference1 = (Preference)iterator.next();
                        if (preference.equals(preference1))
                        {
                            break;
                        }
                        if (preference1.mVisible)
                        {
                            i++;
                        }
                    } while (true);
                    mPreferenceList.add(i + 1, preference);
                    super.mObservable.notifyItemRangeInserted(i + 1, 1);
                    return;
                }
                int j = mPreferenceList.size();
                for (i = ((flag) ? 1 : 0); i < j && !preference.equals(mPreferenceList.get(i)); i++) { }
                mPreferenceList.remove(i);
                super.mObservable.notifyItemRangeRemoved(i, 1);
                return;
            }
        }
    }

    final void syncMyPreferences()
    {
        for (Iterator iterator = mPreferenceListInternal.iterator(); iterator.hasNext();)
        {
            ((Preference)iterator.next()).mListener = null;
        }

        ArrayList arraylist = new ArrayList(mPreferenceListInternal.size());
        flattenPreferenceGroup(arraylist, mPreferenceGroup);
        List list = mPreferenceGroupController.createInnerVisiblePreferencesList(mPreferenceGroup);
        List list1 = mPreferenceList;
        mPreferenceList = list;
        mPreferenceListInternal = arraylist;
        if (((Preference) (mPreferenceGroup)).mPreferenceManager == null);
        super.mObservable.notifyChanged();
        arraylist = (ArrayList)arraylist;
        int j = arraylist.size();
        for (int i = 0; i < j; i++)
        {
            arraylist.get(i);
        }

    }

    private class PreferenceLayout
    {

        public String mName;
        public int mResId;
        public int mWidgetResId;

        public final boolean equals(Object obj)
        {
            if (obj instanceof PreferenceLayout)
            {
                if (mResId == ((PreferenceLayout) (obj = (PreferenceLayout)obj)).mResId && mWidgetResId == ((PreferenceLayout) (obj)).mWidgetResId && TextUtils.equals(mName, ((PreferenceLayout) (obj)).mName))
                {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode()
        {
            return ((mResId + 527) * 31 + mWidgetResId) * 31 + mName.hashCode();
        }

        PreferenceLayout()
        {
        }

        PreferenceLayout(PreferenceLayout preferencelayout)
        {
            mResId = preferencelayout.mResId;
            mWidgetResId = preferencelayout.mWidgetResId;
            mName = preferencelayout.mName;
        }
    }


    private class _cls1
        implements Runnable
    {

        private final PreferenceGroupAdapter this$0;

        public final void run()
        {
            syncMyPreferences();
        }

        _cls1()
        {
            this$0 = PreferenceGroupAdapter.this;
            super();
        }
    }

}
