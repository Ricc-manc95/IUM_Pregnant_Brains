// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, TintTypedArray, AppCompatBackgroundHelper, AppCompatTextHelper, 
//            AppCompatHintHelper

public class AppCompatAutoCompleteTextView extends AutoCompleteTextView
{

    private static final int TINT_ATTRS[] = {
        0x1010176
    };
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatAutoCompleteTextView(Context context)
    {
        this(context, null);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100d3);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeset, int i)
    {
        super(TintContextWrapper.wrap(context), attributeset, i);
        context = getContext();
        context = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, TINT_ATTRS, i, 0));
        if (((TintTypedArray) (context)).mWrapped.hasValue(0))
        {
            setDropDownBackgroundDrawable(context.getDrawable(0));
        }
        ((TintTypedArray) (context)).mWrapped.recycle();
        mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attributeset, i);
        mTextHelper = new AppCompatTextHelper(this);
        mTextHelper.loadFromAttributes(attributeset, i);
        mTextHelper.applyCompoundDrawablesTints();
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if (mBackgroundTintHelper != null)
        {
            mBackgroundTintHelper.applySupportBackgroundTint();
        }
        if (mTextHelper != null)
        {
            mTextHelper.applyCompoundDrawablesTints();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(editorinfo), editorinfo, this);
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        super.setBackgroundDrawable(drawable);
        if (mBackgroundTintHelper != null)
        {
            drawable = mBackgroundTintHelper;
            drawable.mBackgroundResId = -1;
            drawable.setInternalBackgroundTint(null);
            drawable.applySupportBackgroundTint();
        }
    }

    public void setBackgroundResource(int i)
    {
        super.setBackgroundResource(i);
        if (mBackgroundTintHelper != null)
        {
            mBackgroundTintHelper.onSetBackgroundResource(i);
        }
    }

    public void setCustomSelectionActionModeCallback(android.view.ActionMode.Callback callback)
    {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }

    public void setDropDownBackgroundResource(int i)
    {
        setDropDownBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public void setTextAppearance(Context context, int i)
    {
        super.setTextAppearance(context, i);
        if (mTextHelper != null)
        {
            mTextHelper.onSetTextAppearance(context, i);
        }
    }

}
