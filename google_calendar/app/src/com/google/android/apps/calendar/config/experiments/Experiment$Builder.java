// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.experiments;

import android.text.TextUtils;

// Referenced classes of package com.google.android.apps.calendar.config.experiments:
//            Experiment

public class mSalt
{

    public Boolean mForceActive;
    public final int mId;
    public final String mName;
    public final int mSalt;

    protected Experiment buildInternal()
    {
        return null;
    }

    protected void checkConstraints()
    {
        if (TextUtils.isEmpty(mName))
        {
            throw new IllegalStateException("Experiment name can not be null or empty.");
        } else
        {
            return;
        }
    }

    (int i, String s, int j)
    {
        mId = i;
        mName = s;
        mSalt = j;
    }
}
