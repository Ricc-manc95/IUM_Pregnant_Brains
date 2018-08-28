// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import java.util.List;

// Referenced classes of package android.support.v7.preference:
//            PreferenceGroup, Preference, PreferenceManager

public final class PreferenceScreen extends PreferenceGroup
{

    public boolean mShouldUseGeneratedIds;

    public PreferenceScreen(Context context, AttributeSet attributeset)
    {
        int i = 0x7f010223;
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x7f010223, typedvalue, true);
        if (typedvalue.resourceId == 0)
        {
            i = 0x101008b;
        }
        super(context, attributeset, i);
        mShouldUseGeneratedIds = true;
    }

    protected final boolean isOnSameScreenAsChildren()
    {
        return false;
    }

    protected final void onClick()
    {
        PreferenceManager.OnNavigateToScreenListener onnavigatetoscreenlistener;
        if (super.mIntent == null && super.mFragment == null && super.mPreferenceList.size() != 0)
        {
            if ((onnavigatetoscreenlistener = super.mPreferenceManager.mOnNavigateToScreenListener) != null)
            {
                onnavigatetoscreenlistener.onNavigateToScreen(this);
                return;
            }
        }
    }
}
