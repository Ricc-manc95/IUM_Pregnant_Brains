// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.view.View;

// Referenced classes of package android.support.v7.app:
//            AlertController

final class val.bottom
    implements Runnable
{

    private final AlertController this$0;
    private final View val$bottom;
    private final View val$top;

    public final void run()
    {
        AlertController.manageScrollIndicators(mListView, val$top, val$bottom);
    }

    ()
    {
        this$0 = final_alertcontroller;
        val$top = view;
        val$bottom = View.this;
        super();
    }
}
