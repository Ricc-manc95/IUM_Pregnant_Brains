// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v4.widget.TextViewCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatBackgroundHelper, AppCompatTextHelper, AppCompatTextViewAutoSizeHelper, 
//            AppCompatHintHelper

public class AppCompatTextView extends TextView
    implements AutoSizeableTextView
{

    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatTextView(Context context)
    {
        this(context, null);
    }

    public AppCompatTextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010084);
    }

    public AppCompatTextView(Context context, AttributeSet attributeset, int i)
    {
        super(TintContextWrapper.wrap(context), attributeset, i);
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

    public int getAutoSizeMaxTextSize()
    {
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            return super.getAutoSizeMaxTextSize();
        }
        if (mTextHelper != null)
        {
            return Math.round(mTextHelper.mAutoSizeTextHelper.mAutoSizeMaxTextSizeInPx);
        } else
        {
            return -1;
        }
    }

    public int getAutoSizeMinTextSize()
    {
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            return super.getAutoSizeMinTextSize();
        }
        if (mTextHelper != null)
        {
            return Math.round(mTextHelper.mAutoSizeTextHelper.mAutoSizeMinTextSizeInPx);
        } else
        {
            return -1;
        }
    }

    public int getAutoSizeStepGranularity()
    {
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            return super.getAutoSizeStepGranularity();
        }
        if (mTextHelper != null)
        {
            return Math.round(mTextHelper.mAutoSizeTextHelper.mAutoSizeStepGranularityInPx);
        } else
        {
            return -1;
        }
    }

    public int[] getAutoSizeTextAvailableSizes()
    {
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            return super.getAutoSizeTextAvailableSizes();
        }
        if (mTextHelper != null)
        {
            return mTextHelper.mAutoSizeTextHelper.mAutoSizeTextSizesInPx;
        } else
        {
            return new int[0];
        }
    }

    public int getAutoSizeTextType()
    {
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            return super.getAutoSizeTextType() != 1 ? 0 : 1;
        }
        if (mTextHelper != null)
        {
            return mTextHelper.mAutoSizeTextHelper.mAutoSizeTextType;
        } else
        {
            return 0;
        }
    }

    public int getFirstBaselineToTopHeight()
    {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    public int getLastBaselineToBottomHeight()
    {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(editorinfo), editorinfo, this);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if (mTextHelper != null)
        {
            AppCompatTextHelper appcompattexthelper = mTextHelper;
            if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE)
            {
                appcompattexthelper.mAutoSizeTextHelper.autoSizeText();
            }
        }
    }

    protected void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        super.onTextChanged(charsequence, i, j, k);
        if (mTextHelper != null && !PLATFORM_SUPPORTS_AUTOSIZE && mTextHelper.isAutoSizeEnabled())
        {
            mTextHelper.mAutoSizeTextHelper.autoSizeText();
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int j, int k, int l)
        throws IllegalArgumentException
    {
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, j, k, l);
        } else
        if (mTextHelper != null)
        {
            mTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i, j, k, l);
            return;
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int ai[], int i)
        throws IllegalArgumentException
    {
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            super.setAutoSizeTextTypeUniformWithPresetSizes(ai, i);
        } else
        if (mTextHelper != null)
        {
            mTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(ai, i);
            return;
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i)
    {
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            super.setAutoSizeTextTypeWithDefaults(i);
        } else
        if (mTextHelper != null)
        {
            mTextHelper.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
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

    public void setFirstBaselineToTopHeight(int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 28)
        {
            super.setFirstBaselineToTopHeight(i);
            return;
        } else
        {
            TextViewCompat.setFirstBaselineToTopHeight(this, i);
            return;
        }
    }

    public void setLastBaselineToBottomHeight(int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 28)
        {
            super.setLastBaselineToBottomHeight(i);
            return;
        } else
        {
            TextViewCompat.setLastBaselineToBottomHeight(this, i);
            return;
        }
    }

    public void setLineHeight(int i)
    {
        TextViewCompat.setLineHeight(this, i);
    }

    public void setTextAppearance(Context context, int i)
    {
        super.setTextAppearance(context, i);
        if (mTextHelper != null)
        {
            mTextHelper.onSetTextAppearance(context, i);
        }
    }

    public void setTextSize(int i, float f)
    {
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            super.setTextSize(i, f);
        } else
        if (mTextHelper != null)
        {
            mTextHelper.setTextSize(i, f);
            return;
        }
    }
}
