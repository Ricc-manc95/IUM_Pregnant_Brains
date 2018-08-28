// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v7.preference:
//            Preference, PreferenceManager

public class PreferenceGroup extends Preference
{
    static class SavedState extends Preference.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public int mInitialExpandedChildrenCount;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(mInitialExpandedChildrenCount);
        }


        SavedState(Parcel parcel)
        {
            super(parcel);
            mInitialExpandedChildrenCount = parcel.readInt();
        }

        SavedState(Parcelable parcelable, int i)
        {
            super(parcelable);
            mInitialExpandedChildrenCount = i;
        }

        class _cls1
            implements android.os.Parcelable.Creator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public final Object[] newArray(int i)
            {
                return new SavedState[i];
            }

                _cls1()
                {
                }
        }

    }


    private boolean mAttachedToHierarchy;
    private final Runnable mClearRecycleCacheRunnable;
    private int mCurrentPreferenceOrder;
    private final Handler mHandler;
    public final SimpleArrayMap mIdRecycleCache;
    public int mInitialExpandedChildrenCount;
    private boolean mOrderingAsAdded;
    public List mPreferenceList;

    public PreferenceGroup(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mOrderingAsAdded = true;
        mCurrentPreferenceOrder = 0;
        mAttachedToHierarchy = false;
        mInitialExpandedChildrenCount = 0x7fffffff;
        mIdRecycleCache = new SimpleArrayMap();
        mHandler = new Handler();
        mClearRecycleCacheRunnable = new _cls1();
        mPreferenceList = new ArrayList();
        context = context.obtainStyledAttributes(attributeset, R.styleable.PreferenceGroup, i, j);
        mOrderingAsAdded = context.getBoolean(R.styleable.PreferenceGroup_orderingFromXml, context.getBoolean(R.styleable.PreferenceGroup_orderingFromXml, true));
        if (context.hasValue(R.styleable.PreferenceGroup_initialExpandedChildrenCount))
        {
            setInitialExpandedChildrenCount(context.getInt(R.styleable.PreferenceGroup_initialExpandedChildrenCount, context.getInt(R.styleable.PreferenceGroup_initialExpandedChildrenCount, 0x7fffffff)));
        }
        context.recycle();
    }

    public final boolean addPreference(Preference preference)
    {
        if (!mPreferenceList.contains(preference)) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        int j;
        if (preference.mKey != null)
        {
            PreferenceGroup preferencegroup;
            for (preferencegroup = this; ((Preference) (preferencegroup)).mParentGroup != null; preferencegroup = ((Preference) (preferencegroup)).mParentGroup) { }
            String s = preference.mKey;
            if (preferencegroup.findPreference(s) != null)
            {
                Log.e("PreferenceGroup", (new StringBuilder("Found duplicated key: \"")).append(s).append("\". This can cause unintended behaviour, please use unique keys for every preference.").toString());
            }
        }
        if (preference.mOrder == 0x7fffffff)
        {
            if (mOrderingAsAdded)
            {
                int i = mCurrentPreferenceOrder;
                mCurrentPreferenceOrder = i + 1;
                if (i != preference.mOrder)
                {
                    preference.mOrder = i;
                    if (preference.mListener != null)
                    {
                        preference.mListener.onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
                    }
                }
            }
            if (preference instanceof PreferenceGroup)
            {
                ((PreferenceGroup)preference).mOrderingAsAdded = mOrderingAsAdded;
            }
        }
        int k = Collections.binarySearch(mPreferenceList, preference);
        j = k;
        if (k < 0)
        {
            j = -k - 1;
        }
        preference.onParentChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMMH9AO______0(shouldDisableDependents());
        this;
        JVM INSTR monitorenter ;
        mPreferenceList.add(j, preference);
        this;
        JVM INSTR monitorexit ;
        PreferenceManager preferencemanager = super.mPreferenceManager;
        String s1 = preference.mKey;
        long l;
        if (s1 != null && mIdRecycleCache.containsKey(s1))
        {
            l = ((Long)mIdRecycleCache.get(s1)).longValue();
            mIdRecycleCache.remove(s1);
        } else
        {
            l = preferencemanager.getNextId();
        }
        preference.mId = l;
        preference.mHasId = true;
        preference.onAttachedToHierarchy(preferencemanager);
        preference.mHasId = false;
        preference.mParentGroup = this;
        if (mAttachedToHierarchy)
        {
            preference.onAttached();
        }
        if (super.mListener == null) goto _L1; else goto _L3
_L3:
        super.mListener.onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
        return true;
        preference;
        this;
        JVM INSTR monitorexit ;
        throw preference;
        Exception exception;
        exception;
        preference.mHasId = false;
        throw exception;
    }

    protected final void dispatchRestoreInstanceState(Bundle bundle)
    {
        super.dispatchRestoreInstanceState(bundle);
        int j = mPreferenceList.size();
        for (int i = 0; i < j; i++)
        {
            ((Preference)mPreferenceList.get(i)).dispatchRestoreInstanceState(bundle);
        }

    }

    protected final void dispatchSaveInstanceState(Bundle bundle)
    {
        super.dispatchSaveInstanceState(bundle);
        int j = mPreferenceList.size();
        for (int i = 0; i < j; i++)
        {
            ((Preference)mPreferenceList.get(i)).dispatchSaveInstanceState(bundle);
        }

    }

    public final Preference findPreference(CharSequence charsequence)
    {
        if (TextUtils.equals(super.mKey, charsequence))
        {
            return this;
        }
        int j = mPreferenceList.size();
        for (int i = 0; i < j; i++)
        {
            Preference preference = (Preference)mPreferenceList.get(i);
            String s = preference.mKey;
            if (s != null && s.equals(charsequence))
            {
                return preference;
            }
            if (!(preference instanceof PreferenceGroup))
            {
                continue;
            }
            preference = ((PreferenceGroup)preference).findPreference(charsequence);
            if (preference != null)
            {
                return preference;
            }
        }

        return null;
    }

    protected boolean isOnSameScreenAsChildren()
    {
        return true;
    }

    public final void notifyDependencyChange(boolean flag)
    {
        super.notifyDependencyChange(flag);
        int j = mPreferenceList.size();
        for (int i = 0; i < j; i++)
        {
            ((Preference)mPreferenceList.get(i)).onParentChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMMH9AO______0(flag);
        }

    }

    public final void onAttached()
    {
        super.onAttached();
        mAttachedToHierarchy = true;
        int j = mPreferenceList.size();
        for (int i = 0; i < j; i++)
        {
            ((Preference)mPreferenceList.get(i)).onAttached();
        }

    }

    public final void onDetached()
    {
        super.onDetached();
        mAttachedToHierarchy = false;
        int j = mPreferenceList.size();
        for (int i = 0; i < j; i++)
        {
            ((Preference)mPreferenceList.get(i)).onDetached();
        }

    }

    protected final void onRestoreInstanceState(Parcelable parcelable)
    {
        if (parcelable == null || !parcelable.getClass().equals(android/support/v7/preference/PreferenceGroup$SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        parcelable = (SavedState)parcelable;
        if (mInitialExpandedChildrenCount != ((SavedState) (parcelable)).mInitialExpandedChildrenCount)
        {
            mInitialExpandedChildrenCount = ((SavedState) (parcelable)).mInitialExpandedChildrenCount;
            if (super.mListener != null)
            {
                super.mListener.onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
            }
        }
        super.onRestoreInstanceState(parcelable.getSuperState());
    }

    protected final Parcelable onSaveInstanceState()
    {
        return new SavedState(super.onSaveInstanceState(), mInitialExpandedChildrenCount);
    }

    public final void removeAll()
    {
        this;
        JVM INSTR monitorenter ;
        List list;
        int i;
        list = mPreferenceList;
        i = list.size() - 1;
_L2:
        if (i < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        removePreferenceInt((Preference)list.get(0));
        i--;
        if (true) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        if (super.mListener != null)
        {
            super.mListener.onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
        }
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final boolean removePreferenceInt(Preference preference)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        preference.Preference.unregisterDependency();
        if (preference.mParentGroup == this)
        {
            preference.mParentGroup = null;
        }
        flag = mPreferenceList.remove(preference);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        String s = preference.mKey;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_82;
        }
        mIdRecycleCache.put(s, Long.valueOf(preference.getId()));
        mHandler.removeCallbacks(mClearRecycleCacheRunnable);
        mHandler.post(mClearRecycleCacheRunnable);
        if (mAttachedToHierarchy)
        {
            preference.onDetached();
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        preference;
        this;
        JVM INSTR monitorexit ;
        throw preference;
    }

    public final void setInitialExpandedChildrenCount(int i)
    {
        if (i != 0x7fffffff)
        {
            boolean flag;
            if (!TextUtils.isEmpty(super.mKey))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                Log.e("PreferenceGroup", (new StringBuilder()).append(getClass().getSimpleName()).append(" should have a key defined if it contains an expandable preference").toString());
            }
        }
        mInitialExpandedChildrenCount = i;
    }

    private class _cls1
        implements Runnable
    {

        private final PreferenceGroup this$0;

        public final void run()
        {
            this;
            JVM INSTR monitorenter ;
            mIdRecycleCache.clear();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls1()
        {
            this$0 = PreferenceGroup.this;
            super();
        }
    }

}
