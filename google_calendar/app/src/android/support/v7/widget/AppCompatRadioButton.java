// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.RadioButton;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatCompoundButtonHelper, AppCompatTextHelper

public final class AppCompatRadioButton extends RadioButton
{

    private final AppCompatCompoundButtonHelper mCompoundButtonHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatRadioButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100d9);
    }

    private AppCompatRadioButton(Context context, AttributeSet attributeset, int i)
    {
        super(TintContextWrapper.wrap(context), attributeset, i);
        mCompoundButtonHelper = new AppCompatCompoundButtonHelper(this);
        mCompoundButtonHelper.loadFromAttributes(attributeset, i);
        mTextHelper = new AppCompatTextHelper(this);
        mTextHelper.loadFromAttributes(attributeset, i);
    }

    public final int getCompoundPaddingLeft()
    {
        int i = super.getCompoundPaddingLeft();
        AppCompatCompoundButtonHelper appcompatcompoundbuttonhelper;
        if (mCompoundButtonHelper != null)
        {
            appcompatcompoundbuttonhelper = mCompoundButtonHelper;
        }
        return i;
    }

    public final void setButtonDrawable(int i)
    {
        setButtonDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public final void setButtonDrawable(Drawable drawable)
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
