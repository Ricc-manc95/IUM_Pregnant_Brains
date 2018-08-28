// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.view.menu:
//            StandardMenuPopup

final class this._cls0
    implements android.view.lobalLayoutListener
{

    private final StandardMenuPopup this$0;

    public final void onGlobalLayout()
    {
label0:
        {
            StandardMenuPopup standardmenupopup = StandardMenuPopup.this;
            boolean flag;
            if (!standardmenupopup.mWasDismissed && ((ListPopupWindow) (standardmenupopup.mPopup)).mPopup.isShowing())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && !((ListPopupWindow) (mPopup)).mModal)
            {
                View view = mShownAnchorView;
                if (view != null && view.isShown())
                {
                    break label0;
                }
                dismiss();
            }
            return;
        }
        mPopup.show();
    }

    ner()
    {
        this$0 = StandardMenuPopup.this;
        super();
    }
}
