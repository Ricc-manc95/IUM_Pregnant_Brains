// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.os.AsyncTask;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskAccount

final class this._cls0 extends AsyncTask
{

    private final ArpTaskAccount this$0;

    protected final Object doInBackground(Object aobj[])
    {
        listener.askAccountLoaded(ArpTaskAccount.this, null);
        return null;
    }

    I()
    {
        this$0 = ArpTaskAccount.this;
        super();
    }
}
