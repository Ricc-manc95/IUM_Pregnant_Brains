// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.app.Activity;
import android.content.Context;
import android.view.View;

final class val.context
    implements android.view.ner
{

    private final Context val$context;

    public final void onClick(View view)
    {
        if (val$context instanceof Activity)
        {
            ((Activity)val$context).onBackPressed();
        }
    }

    _cls9()
    {
        val$context = context1;
        super();
    }
}
