// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

// Referenced classes of package android.support.v7.view.menu:
//            MenuItemWrapperJB

final class init> extends init>
    implements android.view.roviderWrapper
{

    private android.support.v4.view.erJB.mInner mListener;

    public final boolean isVisible()
    {
        return mInner.isVisible();
    }

    public final void onActionProviderVisibilityChanged(boolean flag)
    {
        if (mListener != null)
        {
            mListener._mth51D2ILG_0();
        }
    }

    public final View onCreateActionView(MenuItem menuitem)
    {
        return mInner.onCreateActionView(menuitem);
    }

    public final boolean overridesItemVisibility()
    {
        return mInner.overridesItemVisibility();
    }

    public final void setVisibilityListener(android.support.v4.view.erJB erjb)
    {
        mListener = erjb;
        mInner.setVisibilityListener(this);
    }

    public (MenuItemWrapperJB menuitemwrapperjb, Context context, ActionProvider actionprovider)
    {
        super(menuitemwrapperjb, context, actionprovider);
    }
}
