// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.view.accessibility.AccessibilityManager;
import java.util.List;

// Referenced classes of package android.support.design.snackbar:
//            BaseTransientBottomBar

final class this._cls0
    implements LayoutChangeListener
{

    private final BaseTransientBottomBar this$0;

    public final void onLayoutChange$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0()
    {
        boolean flag = true;
        view.onLayoutChangeListener = null;
        List list = accessibilityManager.getEnabledAccessibilityServiceList(1);
        if (list == null || !list.isEmpty())
        {
            flag = false;
        }
        if (flag)
        {
            animateViewIn();
            return;
        } else
        {
            onViewShown();
            return;
        }
    }

    ackbarBaseLayout()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
