// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package android.support.v7.preference:
//            ListPreference, Preference, DropDownPreference

final class this._cls0
    implements android.widget.ectedListener
{

    private final DropDownPreference this$0;

    public final void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        if (i >= 0)
        {
            adapterview = mEntryValues[i].toString();
            if (!adapterview.equals(mValue))
            {
                view = DropDownPreference.this;
                if (((Preference) (view)).mOnChangeListener == null || ((Preference) (view)).mOnChangeListener.onPreferenceChange(view, adapterview))
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    setValue(adapterview);
                }
            }
        }
    }

    public final void onNothingSelected(AdapterView adapterview)
    {
    }

    ceChangeListener()
    {
        this$0 = DropDownPreference.this;
        super();
    }
}
