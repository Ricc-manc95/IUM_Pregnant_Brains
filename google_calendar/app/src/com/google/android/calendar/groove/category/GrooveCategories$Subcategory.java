// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove.category;

import android.content.res.Resources;
import android.util.SparseIntArray;

// Referenced classes of package com.google.android.calendar.groove.category:
//            GrooveCategories

public final class question
{

    public final String name;
    public final String question;
    public final boolean requiresInput;
    public final int type;

    (Resources resources, int i, boolean flag)
    {
        this(resources, i, false, 0);
    }

    <init>(Resources resources, int i, boolean flag, int j)
    {
        type = i;
        name = resources.getString(GrooveCategories.GROOVE_NAME_IDS.get(i));
        requiresInput = flag;
        if (j != 0)
        {
            resources = resources.getString(j);
        } else
        {
            resources = null;
        }
        question = resources;
    }
}
