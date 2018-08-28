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
import android.view.View;
import android.widget.TextView;

// Referenced classes of package android.support.v7.preference:
//            Preference, PreferenceManager, PreferenceDataStore

public class TwoStatePreference extends Preference
{
    static class SavedState extends Preference.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public boolean checked;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            if (checked)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
        }


        public SavedState(Parcel parcel)
        {
            boolean flag = true;
            super(parcel);
            if (parcel.readInt() != 1)
            {
                flag = false;
            }
            checked = flag;
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


    public boolean mChecked;
    private boolean mCheckedSet;
    public boolean mDisableDependentsState;
    public CharSequence mSummaryOff;
    public CharSequence mSummaryOn;

    public TwoStatePreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    private TwoStatePreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, 0, 0);
    }

    public TwoStatePreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    protected final void onClick()
    {
        boolean flag = false;
        super.onClick();
        boolean flag1;
        if (!mChecked)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (super.mOnChangeListener == null || super.mOnChangeListener.onPreferenceChange(this, Boolean.valueOf(flag1)))
        {
            flag = true;
        }
        if (flag)
        {
            setChecked(flag1);
        }
    }

    protected final Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return Boolean.valueOf(typedarray.getBoolean(i, false));
    }

    protected final void onRestoreInstanceState(Parcelable parcelable)
    {
        if (parcelable == null || !parcelable.getClass().equals(android/support/v7/preference/TwoStatePreference$SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            setChecked(((SavedState) (parcelable)).checked);
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
            obj.checked = mChecked;
            return ((Parcelable) (obj));
        }
    }

    protected final void onSetInitialValue(boolean flag, Object obj)
    {
        if (flag)
        {
            flag = getPersistedBoolean(mChecked);
        } else
        {
            flag = ((Boolean)obj).booleanValue();
        }
        setChecked(flag);
    }

    public final void setChecked(boolean flag)
    {
        boolean flag2;
        flag2 = true;
        boolean flag1;
        if (mChecked != flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1 && mCheckedSet) goto _L2; else goto _L1
_L1:
        mChecked = flag;
        mCheckedSet = true;
        if (shouldPersist()) goto _L4; else goto _L3
_L3:
        if (flag1)
        {
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
_L2:
        return;
_L4:
        boolean flag3;
        if (!flag)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (flag != getPersistedBoolean(flag3))
        {
            PreferenceDataStore preferencedatastore;
            if (super.mPreferenceManager != null)
            {
                preferencedatastore = super.mPreferenceManager.mPreferenceDataStore;
            } else
            {
                preferencedatastore = null;
            }
            if (preferencedatastore != null)
            {
                preferencedatastore.putBoolean(super.mKey, flag);
            } else
            {
                android.content.SharedPreferences.Editor editor = super.mPreferenceManager.getEditor();
                editor.putBoolean(super.mKey, flag);
                if (super.mPreferenceManager.mNoCommit)
                {
                    flag2 = false;
                }
                if (flag2)
                {
                    editor.apply();
                }
            }
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public final boolean shouldDisableDependents()
    {
label0:
        {
            boolean flag1 = false;
            boolean flag;
            if (mDisableDependentsState)
            {
                flag = mChecked;
            } else
            if (!mChecked)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                flag = flag1;
                if (!super.shouldDisableDependents())
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
    }

    public final void syncSummaryView(View view)
    {
        boolean flag = false;
        if (view instanceof TextView) goto _L2; else goto _L1
_L1:
        return;
_L2:
        boolean flag1;
        view = (TextView)view;
        flag1 = true;
        if (!mChecked || TextUtils.isEmpty(mSummaryOn)) goto _L4; else goto _L3
_L3:
        int i;
        view.setText(mSummaryOn);
        i = 0;
_L5:
        if (i != 0)
        {
            CharSequence charsequence = getSummary();
            if (!TextUtils.isEmpty(charsequence))
            {
                view.setText(charsequence);
                i = 0;
            }
        }
        if (i == 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 8;
        }
        if (i != view.getVisibility())
        {
            view.setVisibility(i);
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
        i = ((flag1) ? 1 : 0);
        if (!mChecked)
        {
            i = ((flag1) ? 1 : 0);
            if (!TextUtils.isEmpty(mSummaryOff))
            {
                view.setText(mSummaryOff);
                i = 0;
            }
        }
          goto _L5
    }
}
