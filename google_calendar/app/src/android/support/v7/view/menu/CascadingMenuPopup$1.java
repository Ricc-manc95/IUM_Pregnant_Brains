// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v7.view.menu:
//            CascadingMenuPopup

final class this._cls0
    implements android.view.obalLayoutListener
{

    private final CascadingMenuPopup this$0;

    public final void onGlobalLayout()
    {
        if (isShowing() && mShowingMenus.size() > 0 && !((ListPopupWindow) (((scadingMenuInfo)mShowingMenus.get(0)).window)).mModal)
        {
            View view = mShownAnchorView;
            if (view == null || !view.isShown())
            {
                dismiss();
            } else
            {
                Iterator iterator = mShowingMenus.iterator();
                while (iterator.hasNext()) 
                {
                    ((scadingMenuInfo)iterator.next()).window.show();
                }
            }
        }
    }

    scadingMenuInfo()
    {
        this$0 = CascadingMenuPopup.this;
        super();
    }
}
