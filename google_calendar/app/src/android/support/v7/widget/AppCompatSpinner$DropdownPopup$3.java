// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.ViewTreeObserver;

// Referenced classes of package android.support.v7.widget:
//            AppCompatSpinner

final class val.layoutListener
    implements android.widget.ownPopup._cls3
{

    private final val.layoutListener this$1;
    private final android.view.stener val$layoutListener;

    public final void onDismiss()
    {
        ViewTreeObserver viewtreeobserver = getViewTreeObserver();
        if (viewtreeobserver != null)
        {
            viewtreeobserver.removeGlobalOnLayoutListener(val$layoutListener);
        }
    }

    ()
    {
        this$1 = final_;
        val$layoutListener = android.view.stener.this;
        super();
    }
}
