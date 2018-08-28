// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreenController, GrooveViewScreenController

final class arg._cls2
    implements Runnable
{

    private final GrooveViewScreenController arg$1;
    private final ListenableFuture arg$2;

    public final void run()
    {
        Context context;
label0:
        {
            GrooveViewScreenController grooveviewscreencontroller = arg$1;
            ListenableFuture listenablefuture = arg$2;
            context = grooveviewscreencontroller.getContext();
            if (context != null)
            {
                if (!((Boolean)Futures.getUnchecked(listenablefuture)).booleanValue())
                {
                    break label0;
                }
                boolean flag;
                if (((Fragment) (grooveviewscreencontroller)).mHost != null && ((Fragment) (grooveviewscreencontroller)).mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    ViewScreen viewscreen = ((ViewScreenController) (grooveviewscreencontroller)).viewScreen;
                    viewscreen.announceForAccessibility(viewscreen.getContext().getText(0x7f1302b0));
                }
                grooveviewscreencontroller.closeViewScreen();
            }
            return;
        }
        Toast.makeText(context, 0x7f1301ab, 0).show();
    }

    (GrooveViewScreenController grooveviewscreencontroller, ListenableFuture listenablefuture)
    {
        arg$1 = grooveviewscreencontroller;
        arg$2 = listenablefuture;
    }
}
