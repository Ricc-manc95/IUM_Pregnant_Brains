// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package android.support.v7.preference:
//            PreferenceFragmentCompat

final class this._cls0 extends Handler
{

    private final PreferenceFragmentCompat this$0;

    public final void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return;

        case 1: // '\001'
            bindPreferences();
            break;
        }
    }

    ()
    {
        this$0 = PreferenceFragmentCompat.this;
        super();
    }
}
