// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.appbar;

import android.support.v4.view.ViewCompat;
import android.view.View;

final class ViewOffsetHelper
{

    public int layoutLeft;
    public int layoutTop;
    private int offsetLeft;
    public int offsetTop;
    public final View view;

    public ViewOffsetHelper(View view1)
    {
        view = view1;
    }

    final void updateOffsets()
    {
        ViewCompat.offsetTopAndBottom(view, offsetTop - (view.getTop() - layoutTop));
        ViewCompat.offsetLeftAndRight(view, offsetLeft - (view.getLeft() - layoutLeft));
    }
}
