// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.android.ex.chips:
//            BaseRecipientAdapter

final class this._cls0 extends Handler
{

    private final BaseRecipientAdapter this$0;

    public final void handleMessage(Message message)
    {
        if (remainingDirectoryCount > 0)
        {
            message = BaseRecipientAdapter.this;
            java.util.List list = constructEntryList();
            message.entries = list;
            ((BaseRecipientAdapter) (message)).entriesUpdatedObserver.onChanged(list);
            message.notifyDataSetChanged();
        }
    }

    ()
    {
        this$0 = BaseRecipientAdapter.this;
        super();
    }
}
