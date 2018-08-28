// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ListPopupWindow

final class this._cls1
    implements android.view.stener
{

    private final this._cls1 this$1;

    public final void onGlobalLayout()
    {
        this._cls1 _lcls1 = this._cls1.this;
        AppCompatSpinner appcompatspinner = _fld0;
        boolean flag;
        if (ViewCompat.isAttachedToWindow(appcompatspinner) && appcompatspinner.getGlobalVisibleRect(_lcls1.isibleRect))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            dismiss();
            return;
        } else
        {
            mputeContentWidth();
            cess._mth301(this._cls1.this);
            return;
        }
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
