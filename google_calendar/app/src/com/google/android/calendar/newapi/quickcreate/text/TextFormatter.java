// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.content.Context;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.text:
//            ChipFactory

public final class TextFormatter
{

    public final ChipFactory chipFactory;
    public final ImmutableList chipTypes;
    public final Context context;

    TextFormatter(Context context1, ChipFactory chipfactory, ImmutableList immutablelist)
    {
        context = context1;
        chipFactory = chipfactory;
        chipTypes = immutablelist;
    }
}
