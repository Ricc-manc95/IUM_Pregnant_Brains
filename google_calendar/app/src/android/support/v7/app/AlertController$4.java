// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.view.View;
import android.widget.AbsListView;

// Referenced classes of package android.support.v7.app:
//            AlertController

final class val.bottom
    implements android.widget.llListener
{

    private final View val$bottom;
    private final View val$top;

    public final void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        AlertController.manageScrollIndicators(abslistview, val$top, val$bottom);
    }

    public final void onScrollStateChanged(AbsListView abslistview, int i)
    {
    }

    er(View view1)
    {
        val$top = view;
        val$bottom = view1;
        super();
    }
}
