// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatTextHelper, TintTypedArray, AppCompatHintHelper

public final class AppCompatCheckedTextView extends CheckedTextView
{

    private static final int TINT_ATTRS[] = {
        0x1010108
    };
    private final AppCompatTextHelper mTextHelper;

    public AppCompatCheckedTextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x10103c8);
    }

    private AppCompatCheckedTextView(Context context, AttributeSet attributeset, int i)
    {
        super(TintContextWrapper.wrap(context), attributeset, 0x10103c8);
        mTextHelper = new AppCompatTextHelper(this);
        mTextHelper.loadFromAttributes(attributeset, 0x10103c8);
        mTextHelper.applyCompoundDrawablesTints();
        context = getContext();
        context = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, TINT_ATTRS, 0x10103c8, 0));
        setCheckMarkDrawable(context.getDrawable(0));
        ((TintTypedArray) (context)).mWrapped.recycle();
    }

    protected final void drawableStateChanged()
    {
        super.drawableStateChanged();
        if (mTextHelper != null)
        {
            mTextHelper.applyCompoundDrawablesTints();
        }
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(editorinfo), editorinfo, this);
    }

    public final void setCheckMarkDrawable(int i)
    {
        setCheckMarkDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public final void setCustomSelectionActionModeCallback(android.view.ActionMode.Callback callback)
    {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }

    public final void setTextAppearance(Context context, int i)
    {
        super.setTextAppearance(context, i);
        if (mTextHelper != null)
        {
            mTextHelper.onSetTextAppearance(context, i);
        }
    }

}
