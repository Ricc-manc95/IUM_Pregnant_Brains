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

public class EditTextPreference extends DialogPreference
{
    static class SavedState extends Preference.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public String text;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeString(text);
        }


        public SavedState(Parcel parcel)
        {
            super(parcel);
            text = parcel.readString();
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


    public String mText;

    public EditTextPreference(Context context)
    {
        this(context, null);
    }

    public EditTextPreference(Context context, AttributeSet attributeset)
    {
        int i = 0x7f01022d;
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x7f01022d, typedvalue, true);
        if (typedvalue.resourceId == 0)
        {
            i = 0x1010092;
        }
        this(context, attributeset, i);
    }

    private EditTextPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    private EditTextPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, 0);
    }

    protected final Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return typedarray.getString(i);
    }

    protected final void onRestoreInstanceState(Parcelable parcelable)
    {
        if (parcelable == null || !parcelable.getClass().equals(android/support/v7/preference/EditTextPreference$SavedState))
        {
            super.onRestoreInstanceState(parcelable);
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            parcelable = ((SavedState) (parcelable)).text;
            boolean flag = shouldDisableDependents();
            mText = parcelable;
            persistString(parcelable);
            boolean flag1 = shouldDisableDependents();
            if (flag1 != flag)
            {
                notifyDependencyChange(flag1);
                return;
            }
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
            obj.text = mText;
            return ((Parcelable) (obj));
        }
    }

    protected final void onSetInitialValue(boolean flag, Object obj)
    {
        boolean flag1;
        if (flag)
        {
            obj = getPersistedString(mText);
        } else
        {
            obj = (String)obj;
        }
        flag = shouldDisableDependents();
        mText = ((String) (obj));
        persistString(((String) (obj)));
        flag1 = shouldDisableDependents();
        if (flag1 != flag)
        {
            notifyDependencyChange(flag1);
        }
    }

    public final boolean shouldDisableDependents()
    {
        return TextUtils.isEmpty(mText) || super.shouldDisableDependents();
    }
}
