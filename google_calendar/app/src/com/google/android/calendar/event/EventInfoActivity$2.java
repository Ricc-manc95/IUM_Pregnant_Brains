// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.widget.Toast;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.common.util.concurrent.FutureCallback;
import java.util.concurrent.CancellationException;

// Referenced classes of package com.google.android.calendar.event:
//            EventInfoActivity

final class this._cls0
    implements FutureCallback
{

    private final EventInfoActivity this$0;

    public final void onFailure(Throwable throwable)
    {
        if (!(throwable instanceof CancellationException))
        {
            Toast.makeText(EventInfoActivity.this, 0x7f1301d8, 0).show();
            finish();
        }
    }

    public final void onSuccess(Object obj)
    {
        obj = (OverlayFragment)obj;
        showOverlayFragment("ViewScreenController", ((OverlayFragment) (obj)));
    }

    yFragment()
    {
        this$0 = EventInfoActivity.this;
        super();
    }
}
