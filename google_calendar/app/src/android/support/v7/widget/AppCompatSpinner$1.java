// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.ShowableListMenu;
import android.view.View;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.widget:
//            ForwardingListener, AppCompatSpinner, ListPopupWindow

final class <init> extends ForwardingListener
{

    private final AppCompatSpinner this$0;
    private final opdownPopup val$popup;

    public final ShowableListMenu getPopup()
    {
        return val$popup;
    }

    public final boolean onForwardingStarted()
    {
        if (!((ListPopupWindow) (mPopup)).mPopup.isShowing())
        {
            mPopup.show();
        }
        return true;
    }

    opdownPopup(opdownPopup opdownpopup)
    {
        this$0 = final_appcompatspinner;
        val$popup = opdownpopup;
        super(View.this);
    }
}
