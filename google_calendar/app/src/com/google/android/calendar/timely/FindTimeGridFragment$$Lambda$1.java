// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeAttendee, FindTimeGridFragment

final class arg._cls1
    implements Function
{

    private final int arg$1;

    public final Object apply(Object obj)
    {
        return FindTimeGridFragment.lambda$updateGrid$1$FindTimeGridFragment(arg$1, (FindTimeAttendee)obj);
    }

    (int i)
    {
        arg$1 = i;
    }
}
