// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v7.preference:
//            Preference, PreferenceGroup, PreferenceViewHolder

final class mId extends Preference
{

    private long mId;

    public final long getId()
    {
        return mId;
    }

    public final void onBindViewHolder(PreferenceViewHolder preferenceviewholder)
    {
        super.onBindViewHolder(preferenceviewholder);
        preferenceviewholder.mDividerAllowedAbove = false;
    }

    (Context context, List list, long l)
    {
        super(context);
        super.mLayoutResId = 0x7f050062;
        setIcon(ContextCompat.getDrawable(super.mContext, 0x7f02011f));
        super.mIconResId = 0x7f02011f;
        setTitle(super.mContext.getString(0x7f1301e3));
        if (999 != super.mOrder)
        {
            super.mOrder = 999;
            if (super.mListener != null)
            {
                super.mListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
            }
        }
        context = null;
        ArrayList arraylist = new ArrayList();
        Iterator iterator = list.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Preference preference = (Preference)iterator.next();
            list = preference.mTitle;
            if ((preference instanceof PreferenceGroup) && !TextUtils.isEmpty(list))
            {
                arraylist.add((PreferenceGroup)preference);
            }
            if (arraylist.contains(preference.mParentGroup))
            {
                if (preference instanceof PreferenceGroup)
                {
                    arraylist.add((PreferenceGroup)preference);
                }
            } else
            if (!TextUtils.isEmpty(list))
            {
                if (context == null)
                {
                    context = list;
                } else
                {
                    context = super.mContext.getString(0x7f130469, new Object[] {
                        context, list
                    });
                }
            }
        } while (true);
        setSummary(context);
        mId = 0xf4240L + l;
    }
}
