// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CheckBox;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatCompoundButtonHelper

public class AppCompatCheckBox extends CheckBox
{

    private final AppCompatCompoundButtonHelper mCompoundButtonHelper;

    public AppCompatCheckBox(Context context)
    {
        this(context, null);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100d6);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeset, int i)
    {
        super(TintContextWrapper.wrap(context), attributeset, i);
        mCompoundButtonHelper = new AppCompatCompoundButtonHelper(this);
        mCompoundButtonHelper.loadFromAttributes(attributeset, i);
    }

    public int getCompoundPaddingLeft()
    {
        int i = super.getCompoundPaddingLeft();
        AppCompatCompoundButtonHelper appcompatcompoundbuttonhelper;
        if (mCompoundButtonHelper != null)
        {
            appcompatcompoundbuttonhelper = mCompoundButtonHelper;
        }
        return i;
    }

    public void setButtonDrawable(int i)
    {
        setButtonDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public void setButtonDrawable(Drawable drawable)
    {
label0:
        {
            super.setButtonDrawable(drawable);
            if (mCompoundButtonHelper != null)
            {
                drawable = mCompoundButtonHelper;
                if (!((AppCompatCompoundButtonHelper) (drawable)).mSkipNextApply)
                {
                    break label0;
                }
                drawable.mSkipNextApply = false;
            }
            return;
        }
        drawable.mSkipNextApply = true;
        drawable.applyButtonTint();
    }
}
