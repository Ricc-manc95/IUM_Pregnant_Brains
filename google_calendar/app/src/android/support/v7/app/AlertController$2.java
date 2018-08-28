// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v4.widget.NestedScrollView;
import android.view.View;

// Referenced classes of package android.support.v7.app:
//            AlertController

final class val.bottom
    implements android.support.v4.widget.nScrollChangeListener
{

    private final View val$bottom;
    private final View val$top;

    public final void onScrollChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TRMIP37CLQ2UJJ5EDQ6AP2JCDP6UR3CAPKMATPR954KII99AO______0(NestedScrollView nestedscrollview)
    {
        AlertController.manageScrollIndicators(nestedscrollview, val$top, val$bottom);
    }

    w(View view1)
    {
        val$top = view;
        val$bottom = view1;
        super();
    }
}
