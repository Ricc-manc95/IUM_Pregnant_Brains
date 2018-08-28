// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v14.preference;

import android.support.v7.preference.Preference;
import android.support.v7.preference.TwoStatePreference;
import android.widget.CompoundButton;

// Referenced classes of package android.support.v14.preference:
//            SwitchPreference

final class this._cls0
    implements android.widget.angeListener
{

    private final SwitchPreference this$0;

    public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        boolean flag2 = true;
        SwitchPreference switchpreference = SwitchPreference.this;
        boolean flag1;
        if (((Preference) (switchpreference)).mOnChangeListener == null || ((Preference) (switchpreference)).mOnChangeListener.onPreferenceChange(switchpreference, Boolean.valueOf(flag)))
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

    geListener()
    {
        this$0 = SwitchPreference.this;
        super();
    }
}
