// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

// Referenced classes of package android.support.v7.preference:
//            DialogPreference, Preference

public class ListPreference extends DialogPreference
{
    static class SavedState extends Preference.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public String value;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeString(value);
        }


        public SavedState(Parcel parcel)
        {
            super(parcel);
            value = parcel.readString();
        }

        public SavedState(Parcelable parcelable)
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
    private String mSummary;
    public String mValue;
    private boolean mValueSet;

    public ListPreference(Context context)
    {
        this(context, null);
    }

    public ListPreference(Context context, AttributeSet attributeset)
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

    private ListPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ListPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        TypedArray typedarray1 = context.obtainStyledAttributes(attributeset, R.styleable.ListPreference, i, j);
        int k = R.styleable.ListPreference_entries;
        int l = R.styleable.ListPreference_android_entries;
        CharSequence acharsequence1[] = typedarray1.getTextArray(k);
        CharSequence acharsequence[] = acharsequence1;
        if (acharsequence1 == null)
        {
            acharsequence = typedarray1.getTextArray(l);
        }
        mEntries = acharsequence;
        k = R.styleable.ListPreference_entryValues;
        l = R.styleable.ListPreference_android_entryValues;
        acharsequence1 = typedarray1.getTextArray(k);
        acharsequence = acharsequence1;
        if (acharsequence1 == null)
        {
            acharsequence = typedarray1.getTextArray(l);
        }
        mEntryValues = acharsequence;
        typedarray1.recycle();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.Preference, i, j);
        i = R.styleable.Preference_summary;
        j = R.styleable.Preference_android_summary;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mSummary = context;
        typedarray.recycle();
    }

    public final int findIndexOfValue(String s)
    {
        if (s != null && mEntryValues != null)
        {
            for (int i = mEntryValues.length - 1; i >= 0; i--)
            {
                if (mEntryValues[i].equals(s))
                {
                    return i;
                }
            }

        }
        return -1;
    }

    public final CharSequence getSummary()
    {
        int i = findIndexOfValue(mValue);
        CharSequence charsequence;
        if (i >= 0 && mEntries != null)
        {
            charsequence = mEntries[i];
        } else
        {
            charsequence = null;
        }
        if (mSummary == null)
        {
            return super.getSummary();
        }
        String s = mSummary;
        Object obj = charsequence;
        if (charsequence == null)
        {
            obj = "";
        }
        return String.format(s, new Object[] {
            obj
        });
    }

    protected final Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return typedarray.getString(i);
    }

    protected final void onRestoreInstanceState(Parcelable parcelable)
    {
        if (parcelable == null || !parcelable.getClass().equals(android/support/v7/preference/ListPreference$SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            setValue(((SavedState) (parcelable)).value);
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
            obj.value = mValue;
            return ((Parcelable) (obj));
        }
    }

    protected final void onSetInitialValue(boolean flag, Object obj)
    {
        if (flag)
        {
            obj = getPersistedString(mValue);
        } else
        {
            obj = (String)obj;
        }
        setValue(((String) (obj)));
    }

    public void setEntries(CharSequence acharsequence[])
    {
        mEntries = acharsequence;
    }

    public final void setSummary(CharSequence charsequence)
    {
        super.setSummary(charsequence);
        if (charsequence == null && mSummary != null)
        {
            mSummary = null;
        } else
        if (charsequence != null && !charsequence.equals(mSummary))
        {
            mSummary = charsequence.toString();
            return;
        }
    }

    public final void setValue(String s)
    {
        boolean flag;
        if (!TextUtils.equals(mValue, s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || !mValueSet)
        {
            mValue = s;
            mValueSet = true;
            persistString(s);
            if (flag)
            {
                notifyChanged();
            }
        }
    }
}
