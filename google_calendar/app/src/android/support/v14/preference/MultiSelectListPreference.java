// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v14.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDataStore;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.internal.AbstractMultiSelectListPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MultiSelectListPreference extends AbstractMultiSelectListPreference
{
    static class SavedState extends android.support.v7.preference.Preference.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public Set mValues;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(mValues.size());
            parcel.writeStringArray((String[])mValues.toArray(new String[mValues.size()]));
        }


        SavedState(Parcel parcel)
        {
            super(parcel);
            int i = parcel.readInt();
            mValues = new HashSet();
            String as[] = new String[i];
            parcel.readStringArray(as);
            Collections.addAll(mValues, as);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
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


    public CharSequence mEntries[];
    public CharSequence mEntryValues[];
    private Set mValues;

    public MultiSelectListPreference(Context context)
    {
        this(context, null);
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeset)
    {
        int i = 0x7f01022c;
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x7f01022c, typedvalue, true);
        if (typedvalue.resourceId == 0)
        {
            i = 0x1010091;
        }
        this(context, attributeset, i);
    }

    private MultiSelectListPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    private MultiSelectListPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, 0);
        mValues = new HashSet();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, android.support.v7.preference.R.styleable.MultiSelectListPreference, i, 0);
        i = android.support.v7.preference.R.styleable.MultiSelectListPreference_entries;
        j = android.support.v7.preference.R.styleable.MultiSelectListPreference_android_entries;
        attributeset = typedarray.getTextArray(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getTextArray(j);
        }
        mEntries = context;
        i = android.support.v7.preference.R.styleable.MultiSelectListPreference_entryValues;
        j = android.support.v7.preference.R.styleable.MultiSelectListPreference_android_entryValues;
        attributeset = typedarray.getTextArray(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getTextArray(j);
        }
        mEntryValues = context;
        typedarray.recycle();
    }

    public final CharSequence[] getEntries()
    {
        return mEntries;
    }

    public final CharSequence[] getEntryValues()
    {
        return mEntryValues;
    }

    public final Set getValues()
    {
        return mValues;
    }

    protected final Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        typedarray = typedarray.getTextArray(i);
        HashSet hashset = new HashSet();
        int j = typedarray.length;
        for (i = 0; i < j; i++)
        {
            hashset.add(typedarray[i].toString());
        }

        return hashset;
    }

    protected final void onRestoreInstanceState(Parcelable parcelable)
    {
        if (parcelable == null || !parcelable.getClass().equals(android/support/v14/preference/MultiSelectListPreference$SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            setValues(((SavedState) (parcelable)).mValues);
            return;
        }
    }

    protected final Parcelable onSaveInstanceState()
    {
        Object obj = super.onSaveInstanceState();
        if (super.mPersistent)
        {
            return ((Parcelable) (obj));
        } else
        {
            obj = new SavedState(((Parcelable) (obj)));
            obj.mValues = getValues();
            return ((Parcelable) (obj));
        }
    }

    protected final void onSetInitialValue(boolean flag, Object obj)
    {
        if (flag)
        {
            obj = getPersistedStringSet(mValues);
        } else
        {
            obj = (Set)obj;
        }
        setValues(((Set) (obj)));
    }

    public final void setValues(Set set)
    {
        Object obj;
        boolean flag;
        boolean flag2;
        obj = null;
        flag2 = true;
        mValues.clear();
        mValues.addAll(set);
        if (super.mPreferenceManager == null || !super.mPersistent)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        if (!TextUtils.isEmpty(super.mKey))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        flag = true;
        if (flag && !set.equals(getPersistedStringSet(null)))
        {
            if (super.mPreferenceManager != null)
            {
                obj = super.mPreferenceManager.mPreferenceDataStore;
            }
            if (obj != null)
            {
                ((PreferenceDataStore) (obj)).putStringSet(super.mKey, set);
                return;
            }
            obj = super.mPreferenceManager.getEditor();
            ((android.content.SharedPreferences.Editor) (obj)).putStringSet(super.mKey, set);
            boolean flag1;
            if (!super.mPreferenceManager.mNoCommit)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                ((android.content.SharedPreferences.Editor) (obj)).apply();
                return;
            }
        }
        return;
        flag = false;
        break MISSING_BLOCK_LABEL_57;
    }
}
