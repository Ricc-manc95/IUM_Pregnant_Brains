// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.DecorContentParent;
import android.view.View;
import android.view.Window;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImpl

final class this._cls0
    implements android.support.v7.widget.achListener
{

    private final AppCompatDelegateImpl this$0;

    public final void onAttachedFromWindow()
    {
    }

    public final void onDetachedFromWindow()
    {
        Object obj = AppCompatDelegateImpl.this;
        if (((AppCompatDelegateImpl) (obj)).mDecorContentParent != null)
        {
            ((AppCompatDelegateImpl) (obj)).mDecorContentParent.dismissPopups();
        }
        if (((AppCompatDelegateImpl) (obj)).mActionModePopup != null)
        {
            ((AppCompatDelegateImpl) (obj)).mWindow.getDecorView().removeCallbacks(((AppCompatDelegateImpl) (obj)).mShowActionModePopup);
            if (((AppCompatDelegateImpl) (obj)).mActionModePopup.isShowing())
            {
                try
                {
                    ((AppCompatDelegateImpl) (obj)).mActionModePopup.dismiss();
                }
                catch (IllegalArgumentException illegalargumentexception) { }
            }
            obj.mActionModePopup = null;
        }
        ((AppCompatDelegateImpl) (obj)).endOnGoingFadeAnimation();
        obj = ((AppCompatDelegateImpl) (obj)).getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
        if (obj != null && ((nelFeatureState) (obj)).menu != null)
        {
            ((nelFeatureState) (obj)).menu.close();
        }
    }

    nelFeatureState()
    {
        this$0 = AppCompatDelegateImpl.this;
        super();
    }
}
