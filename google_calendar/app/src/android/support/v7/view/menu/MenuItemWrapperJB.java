// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.ActionProvider;

// Referenced classes of package android.support.v7.view.menu:
//            MenuItemWrapperICS

public final class MenuItemWrapperJB extends MenuItemWrapperICS
{

    public MenuItemWrapperJB(Context context, SupportMenuItem supportmenuitem)
    {
        super(context, supportmenuitem);
    }

    final MenuItemWrapperICS.ActionProviderWrapper createActionProviderWrapper(ActionProvider actionprovider)
    {
        return new ActionProviderWrapperJB(mContext, actionprovider);
    }

    private class ActionProviderWrapperJB extends MenuItemWrapperICS.ActionProviderWrapper
        implements android.view.ActionProvider.VisibilityListener
    {

        private android.support.v4.view.ActionProvider.VisibilityListener mListener;

        public final boolean isVisible()
        {
            return mInner.isVisible();
        }

        public final void onActionProviderVisibilityChanged(boolean flag)
        {
            if (mListener != null)
            {
                mListener.onActionProviderVisibilityChanged$51D2ILG_0();
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

        public final void setVisibilityListener(android.support.v4.view.ActionProvider.VisibilityListener visibilitylistener)
        {
            mListener = visibilitylistener;
            mInner.setVisibilityListener(this);
        }

        public ActionProviderWrapperJB(Context context, ActionProvider actionprovider)
        {
            super(MenuItemWrapperJB.this, context, actionprovider);
        }
    }

}
