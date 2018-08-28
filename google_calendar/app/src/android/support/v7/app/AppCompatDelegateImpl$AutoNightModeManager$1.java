// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package android.support.v7.app:
//            TwilightManager, AppCompatDelegate

final class this._cls1 extends BroadcastReceiver
{

    private final is._cls0 this$1;

    public final void onReceive(Context context, Intent intent)
    {
        context = this._cls1.this;
        boolean flag = ((this._cls1) (context)).wilightManager.isNight();
        if (flag != ((wilightManager) (context)).sNight)
        {
            context.sNight = flag;
            ((sNight) (context))._fld0.applyDayNight();
        }
    }

    I()
    {
        this$1 = this._cls1.this;
        super();
    }
}
