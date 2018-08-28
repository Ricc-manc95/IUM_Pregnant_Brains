// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.widget.SeekBar;

// Referenced classes of package android.support.v7.preference:
//            SeekBarPreference, Preference

final class this._cls0
    implements android.widget.ngeListener
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

    nceChangeListener()
    {
        this$0 = SeekBarPreference.this;
        super();
    }
}
