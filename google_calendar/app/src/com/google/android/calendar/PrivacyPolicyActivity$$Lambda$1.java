// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.accounts.Account;
import android.content.DialogInterface;
import java.util.List;

// Referenced classes of package com.google.android.calendar:
//            PrivacyPolicyActivity

final class arg._cls2
    implements android.content.y..Lambda._cls1
{

    private final PrivacyPolicyActivity arg$1;
    private final List arg$2;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        arg$1.showPrivacyPolicy((Account)arg$2.get(i));
    }

    A(PrivacyPolicyActivity privacypolicyactivity, List list)
    {
        arg$1 = privacypolicyactivity;
        arg$2 = list;
    }
}
