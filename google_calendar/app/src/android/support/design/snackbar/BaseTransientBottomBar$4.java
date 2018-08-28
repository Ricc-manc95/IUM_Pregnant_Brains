// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.view.View;

// Referenced classes of package android.support.design.snackbar:
//            BaseTransientBottomBar, SnackbarManager

final class this._cls0
    implements android.support.design.behavior.smissListener
{

    private final BaseTransientBottomBar this$0;

    public final void onDismiss(View view)
    {
        view.setVisibility(8);
        dispatchDismiss(0);
    }

    public final void onDragStateChanged(int i)
    {
        switch (i)
        {
        default:
            return;

        case 1: // '\001'
        case 2: // '\002'
            if (SnackbarManager.snackbarManager == null)
            {
                SnackbarManager.snackbarManager = new SnackbarManager();
            }
            SnackbarManager.snackbarManager.pauseTimeout(managerCallback);
            return;

        case 0: // '\0'
            break;
        }
        if (SnackbarManager.snackbarManager == null)
        {
            SnackbarManager.snackbarManager = new SnackbarManager();
        }
        SnackbarManager.snackbarManager.restoreTimeoutIfPaused(managerCallback);
    }

    smissListener()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
