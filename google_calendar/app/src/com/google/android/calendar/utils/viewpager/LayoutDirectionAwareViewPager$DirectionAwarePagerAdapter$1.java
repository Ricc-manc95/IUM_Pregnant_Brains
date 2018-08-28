// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;

final class this._cls0 extends DataSetObserver
{

    private final this._cls0 this$0;

    public final void onChanged()
    {
        notifyDataSetChanged();
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
