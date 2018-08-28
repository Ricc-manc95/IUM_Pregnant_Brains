// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;
import android.view.WindowInsets;

// Referenced classes of package android.support.v4.view:
//            OnApplyWindowInsetsListener, WindowInsetsCompat

final class val.listener
    implements android.view.indowInsetsListener
{

    private final OnApplyWindowInsetsListener val$listener;

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowinsets)
    {
        if (windowinsets == null)
        {
            windowinsets = null;
        } else
        {
            windowinsets = new WindowInsetsCompat(windowinsets);
        }
        view = val$listener.onApplyWindowInsets(view, windowinsets);
        if (view == null)
        {
            view = null;
        } else
        {
            view = ((View) (((WindowInsetsCompat) (view)).mInsets));
        }
        return (WindowInsets)view;
    }

    InsetsListener()
    {
        val$listener = onapplywindowinsetslistener;
        super();
    }
}
