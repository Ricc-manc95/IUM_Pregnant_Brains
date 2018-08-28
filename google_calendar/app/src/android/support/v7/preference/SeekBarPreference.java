// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

// Referenced classes of package android.support.v7.preference:
//            Preference, PreferenceViewHolder, PreferenceManager, PreferenceDataStore

public class SeekBarPreference extends Preference
{
    static class SavedState extends Preference.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public int max;
        public int min;
        public int seekBarValue;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(seekBarValue);
            parcel.writeInt(min);
            parcel.writeInt(max);
        }


        public SavedState(Parcel parcel)
        {
            super(parcel);
            seekBarValue = parcel.readInt();
            min = parcel.readInt();
            max = parcel.readInt();
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


    public boolean mAdjustable;
    private int mMax;
    public int mMin;
    public SeekBar mSeekBar;
    private android.widget.SeekBar.OnSeekBarChangeListener mSeekBarChangeListener;
    private int mSeekBarIncrement;
    private android.view.View.OnKeyListener mSeekBarKeyListener;
    public int mSeekBarValue;
    private TextView mSeekBarValueTextView;
    private boolean mShowSeekBarValue;
    public boolean mTrackingTouch;

    public SeekBarPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f010238);
    }

    private SeekBarPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    private SeekBarPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, 0);
        mSeekBarChangeListener = new _cls1();
        mSeekBarKeyListener = new _cls2();
        context = context.obtainStyledAttributes(attributeset, R.styleable.SeekBarPreference, i, 0);
        mMin = context.getInt(R.styleable.SeekBarPreference_min, 0);
        j = context.getInt(R.styleable.SeekBarPreference_android_max, 100);
        i = j;
        if (j < mMin)
        {
            i = mMin;
        }
        if (i != mMax)
        {
            mMax = i;
            notifyChanged();
        }
        i = context.getInt(R.styleable.SeekBarPreference_seekBarIncrement, 0);
        if (i != mSeekBarIncrement)
        {
            mSeekBarIncrement = Math.min(mMax - mMin, Math.abs(i));
            notifyChanged();
        }
        mAdjustable = context.getBoolean(R.styleable.SeekBarPreference_adjustable, true);
        mShowSeekBarValue = context.getBoolean(R.styleable.SeekBarPreference_showSeekBarValue, true);
        context.recycle();
    }

    public final void onBindViewHolder(PreferenceViewHolder preferenceviewholder)
    {
        super.onBindViewHolder(preferenceviewholder);
        preferenceviewholder.itemView.setOnKeyListener(mSeekBarKeyListener);
        mSeekBar = (SeekBar)preferenceviewholder.findViewById(0x7f1002db);
        mSeekBarValueTextView = (TextView)preferenceviewholder.findViewById(0x7f1002dc);
        if (mShowSeekBarValue)
        {
            mSeekBarValueTextView.setVisibility(0);
        } else
        {
            mSeekBarValueTextView.setVisibility(8);
            mSeekBarValueTextView = null;
        }
        if (mSeekBar == null)
        {
            Log.e("SeekBarPreference", "SeekBar view is null in onBindViewHolder.");
            return;
        }
        mSeekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);
        mSeekBar.setMax(mMax - mMin);
        if (mSeekBarIncrement != 0)
        {
            mSeekBar.setKeyProgressIncrement(mSeekBarIncrement);
        } else
        {
            mSeekBarIncrement = mSeekBar.getKeyProgressIncrement();
        }
        mSeekBar.setProgress(mSeekBarValue - mMin);
        if (mSeekBarValueTextView != null)
        {
            mSeekBarValueTextView.setText(String.valueOf(mSeekBarValue));
        }
        mSeekBar.setEnabled(isEnabled());
    }

    protected final Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return Integer.valueOf(typedarray.getInt(i, 0));
    }

    protected final void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!parcelable.getClass().equals(android/support/v7/preference/SeekBarPreference$SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            mSeekBarValue = ((SavedState) (parcelable)).seekBarValue;
            mMin = ((SavedState) (parcelable)).min;
            mMax = ((SavedState) (parcelable)).max;
            notifyChanged();
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
            obj.seekBarValue = mSeekBarValue;
            obj.min = mMin;
            obj.max = mMax;
            return ((Parcelable) (obj));
        }
    }

    protected final void onSetInitialValue(boolean flag, Object obj)
    {
        int i;
        if (flag)
        {
            i = getPersistedInt(mSeekBarValue);
        } else
        {
            i = ((Integer)obj).intValue();
        }
        setValueInternal(i, true);
    }

    final void setValueInternal(int i, boolean flag)
    {
        int j = i;
        if (i < mMin)
        {
            j = mMin;
        }
        i = j;
        if (j > mMax)
        {
            i = mMax;
        }
        if (i == mSeekBarValue) goto _L2; else goto _L1
_L1:
        mSeekBarValue = i;
        if (mSeekBarValueTextView != null)
        {
            mSeekBarValueTextView.setText(String.valueOf(mSeekBarValue));
        }
          goto _L3
_L5:
        if (flag)
        {
            notifyChanged();
        }
_L2:
        return;
_L3:
        if (!shouldPersist() || i == getPersistedInt(~i)) goto _L5; else goto _L4
_L4:
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
            preferencedatastore.putInt(super.mKey, i);
        } else
        {
            android.content.SharedPreferences.Editor editor = super.mPreferenceManager.getEditor();
            editor.putInt(super.mKey, i);
            if (!super.mPreferenceManager.mNoCommit)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                editor.apply();
            }
        }
        if (true) goto _L5; else goto _L6
_L6:
    }

    private class _cls1
        implements android.widget.SeekBar.OnSeekBarChangeListener
    {

        private final SeekBarPreference this$0;

        public final void onProgressChanged(SeekBar seekbar, int i, boolean flag)
        {
            SeekBarPreference seekbarpreference;
label0:
            {
                if (flag && !mTrackingTouch)
                {
                    seekbarpreference = SeekBarPreference.this;
                    i = seekbarpreference.mMin;
                    int j = seekbar.getProgress() + i;
                    if (j != seekbarpreference.mSeekBarValue)
                    {
                        if (((Preference) (seekbarpreference)).mOnChangeListener == null || ((Preference) (seekbarpreference)).mOnChangeListener.onPreferenceChange(seekbarpreference, Integer.valueOf(j)))
                        {
                            i = 1;
                        } else
                        {
                            i = 0;
                        }
                        if (i == 0)
                        {
                            break label0;
                        }
                        seekbarpreference.setValueInternal(j, false);
                    }
                }
                return;
            }
            seekbar.setProgress(seekbarpreference.mSeekBarValue - seekbarpreference.mMin);
        }

        public final void onStartTrackingTouch(SeekBar seekbar)
        {
            mTrackingTouch = true;
        }

        public final void onStopTrackingTouch(SeekBar seekbar)
        {
            SeekBarPreference seekbarpreference;
label0:
            {
                mTrackingTouch = false;
                if (seekbar.getProgress() + mMin != mSeekBarValue)
                {
                    seekbarpreference = SeekBarPreference.this;
                    int i = seekbarpreference.mMin;
                    int j = seekbar.getProgress() + i;
                    if (j != seekbarpreference.mSeekBarValue)
                    {
                        boolean flag;
                        if (((Preference) (seekbarpreference)).mOnChangeListener == null || ((Preference) (seekbarpreference)).mOnChangeListener.onPreferenceChange(seekbarpreference, Integer.valueOf(j)))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (!flag)
                        {
                            break label0;
                        }
                        seekbarpreference.setValueInternal(j, false);
                    }
                }
                return;
            }
            seekbar.setProgress(seekbarpreference.mSeekBarValue - seekbarpreference.mMin);
        }

        _cls1()
        {
            this$0 = SeekBarPreference.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnKeyListener
    {

        private final SeekBarPreference this$0;

        public final boolean onKey(View view, int i, KeyEvent keyevent)
        {
            while (keyevent.getAction() != 0 || !mAdjustable && (i == 21 || i == 22) || i == 23 || i == 66) 
            {
                return false;
            }
            if (mSeekBar == null)
            {
                Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
                return false;
            } else
            {
                return mSeekBar.onKeyDown(i, keyevent);
            }
        }

        _cls2()
        {
            this$0 = SeekBarPreference.this;
            super();
        }
    }

}
