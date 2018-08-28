// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.view.menu.MenuWrapperICS;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

// Referenced classes of package android.support.v7.view:
//            ActionMode

public final class SupportActionModeWrapper extends ActionMode
{

    private final Context mContext;
    public final android.support.v7.view.ActionMode mWrappedObject;

    public SupportActionModeWrapper(Context context, android.support.v7.view.ActionMode actionmode)
    {
        mContext = context;
        mWrappedObject = actionmode;
    }

    public final void finish()
    {
        mWrappedObject.finish();
    }

    public final View getCustomView()
    {
        return mWrappedObject.getCustomView();
    }

    public final Menu getMenu()
    {
        return new MenuWrapperICS(mContext, (SupportMenu)mWrappedObject.getMenu());
    }

    public final MenuInflater getMenuInflater()
    {
        return mWrappedObject.getMenuInflater();
    }

    public final CharSequence getSubtitle()
    {
        return mWrappedObject.getSubtitle();
    }

    public final Object getTag()
    {
        return mWrappedObject.mTag;
    }

    public final CharSequence getTitle()
    {
        return mWrappedObject.getTitle();
    }

    public final boolean getTitleOptionalHint()
    {
        return mWrappedObject.mTitleOptionalHint;
    }

    public final void invalidate()
    {
        mWrappedObject.invalidate();
    }

    public final boolean isTitleOptional()
    {
        return mWrappedObject.isTitleOptional();
    }

    public final void setCustomView(View view)
    {
        mWrappedObject.setCustomView(view);
    }

    public final void setSubtitle(int i)
    {
        mWrappedObject.setSubtitle(i);
    }

    public final void setSubtitle(CharSequence charsequence)
    {
        mWrappedObject.setSubtitle(charsequence);
    }

    public final void setTag(Object obj)
    {
        mWrappedObject.mTag = obj;
    }

    public final void setTitle(int i)
    {
        mWrappedObject.setTitle(i);
    }

    public final void setTitle(CharSequence charsequence)
    {
        mWrappedObject.setTitle(charsequence);
    }

    public final void setTitleOptionalHint(boolean flag)
    {
        mWrappedObject.setTitleOptionalHint(flag);
    }
}
