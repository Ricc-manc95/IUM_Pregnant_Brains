// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.event.scope:
//            ScopeSelectionDialog

final class arg._cls1
    implements Function
{

    private final ScopeSelectionDialog arg$1;

    public final Object apply(Object obj)
    {
        ScopeSelectionDialog scopeselectiondialog = arg$1;
        obj = (arg._cls1)obj;
        return scopeselectiondialog.requireContext().getResources().getString(((arg._cls1) (obj)).ription());
    }

    (ScopeSelectionDialog scopeselectiondialog)
    {
        arg$1 = scopeselectiondialog;
    }
}
