// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.exchange;

import android.content.Context;
import com.google.android.calendar.Utils;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.utils.exchange.ExchangeUtil;

// Referenced classes of package com.google.android.calendar.newapi.exchange:
//            EasSupport

public final class EasSupportLoader extends AsyncTaskLoader
{

    private final Context context;

    public EasSupportLoader(Context context1)
    {
        context = context1.getApplicationContext();
    }

    protected final Object runInBackground(Object aobj[])
    {
        boolean flag1;
        flag1 = true;
        aobj = context;
        if (EasSupport.proposeTimeSupported != null && EasSupport.addNoteSupported != null) goto _L2; else goto _L1
_L1:
        if (!ExchangeUtil.isEasServiceResolvable(((Context) (aobj)), "com.google.android.gm")) goto _L4; else goto _L3
_L3:
        boolean flag = flag1;
_L6:
        EasSupport.proposeTimeSupported = Boolean.valueOf(flag);
        EasSupport.addNoteSupported = Boolean.valueOf(ExchangeUtil.isAddNoteSupported(((Context) (aobj))));
_L2:
        return null;
_L4:
        flag = flag1;
        if (ExchangeUtil.isEasServiceResolvable(((Context) (aobj)), "com.google.android.gm.lite"))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (Utils.isApkVersionAtLeast(((Context) (aobj)), "com.google.android.gm.exchange", 0x7b53a))
        {
            flag = flag1;
            if (ExchangeUtil.isEasServiceResolvable(((Context) (aobj)), "com.google.android.gm.exchange"))
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        flag = false;
        if (true) goto _L6; else goto _L5
_L5:
    }
}
