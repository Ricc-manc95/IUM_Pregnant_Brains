// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.avatar;

import android.database.Cursor;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.avatar:
//            ContactInfoLoader, ContactInfo

final class arg._cls1
    implements Function
{

    private final ContactInfo arg$1;

    public final Object apply(Object obj)
    {
        return ContactInfoLoader.lambda$loadSingleByEmailFromCP2$0$ContactInfoLoader(arg$1, (Cursor)obj);
    }

    (ContactInfo contactinfo)
    {
        arg$1 = contactinfo;
    }
}
