// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.os.Bundle;

// Referenced classes of package com.google.android.calendar.event:
//            CustomDurationDialog

public static final class args
{

    public Bundle args;

    public (int i)
    {
        args = new Bundle();
        args.putInt("duration_in_minutes", i);
    }
}
