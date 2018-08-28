// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.widget.CompoundButton;

// Referenced classes of package android.support.v7.preference:
//            CheckBoxPreference, Preference, TwoStatePreference

final class this._cls0
    implements android.widget.geListener
{

    private final CheckBoxPreference this$0;

    public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        boolean flag2 = true;
        CheckBoxPreference checkboxpreference = CheckBoxPreference.this;
        boolean flag1;
        if (((Preference) (checkboxpreference)).mOnChangeListener == null || ((Preference) (checkboxpreference)).mOnChangeListener.onPreferenceChange(checkboxpreference, Boolean.valueOf(flag)))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            if (!flag)
            {
                flag = flag2;
            } else
            {
                flag = false;
            }
            compoundbutton.setChecked(flag);
            return;
        } else
        {
            setChecked(flag);
            return;
        }
    }

    eListener()
    {
        this$0 = CheckBoxPreference.this;
        super();
    }
}
