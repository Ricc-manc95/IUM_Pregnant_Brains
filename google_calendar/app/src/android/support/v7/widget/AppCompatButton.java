// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import java.util.Arrays;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatBackgroundHelper, AppCompatTextHelper, AppCompatTextViewAutoSizeHelper, 
//            TintInfo, AppCompatEditText

public class AppCompatButton extends Button
    implements AutoSizeableTextView
{

    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;

    public AppCompatButton(Context context)
    {
        this(context, null);
    }

    public AppCompatButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100d4);
    }

    public AppCompatButton(Context context, AttributeSet attributeset, int i)
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

    public ColorStateList getSupportBackgroundTintList()
    {
        Object obj = null;
        ColorStateList colorstatelist = obj;
        if (mBackgroundTintHelper != null)
        {
            AppCompatBackgroundHelper appcompatbackgroundhelper = mBackgroundTintHelper;
            colorstatelist = obj;
            if (appcompatbackgroundhelper.mBackgroundTint != null)
            {
                colorstatelist = appcompatbackgroundhelper.mBackgroundTint.mTintList;
            }
        }
        return colorstatelist;
    }

    public android.graphics.PorterDuff.Mode getSupportBackgroundTintMode()
    {
        Object obj = null;
        android.graphics.PorterDuff.Mode mode = obj;
        if (mBackgroundTintHelper != null)
        {
            AppCompatBackgroundHelper appcompatbackgroundhelper = mBackgroundTintHelper;
            mode = obj;
            if (appcompatbackgroundhelper.mBackgroundTint != null)
            {
                mode = appcompatbackgroundhelper.mBackgroundTint.mTintMode;
            }
        }
        return mode;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName(android/widget/Button.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/widget/Button.getName());
    }

    public void onLayout(boolean flag, int i, int j, int k, int l)
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
        boolean flag = true;
        super.onTextChanged(charsequence, i, j, k);
        if (mTextHelper != null && !PLATFORM_SUPPORTS_AUTOSIZE)
        {
            charsequence = mTextHelper.mAutoSizeTextHelper;
            if (!(((AppCompatTextViewAutoSizeHelper) (charsequence)).mTextView instanceof AppCompatEditText))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0 && ((AppCompatTextViewAutoSizeHelper) (charsequence)).mAutoSizeTextType != 0)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                mTextHelper.mAutoSizeTextHelper.autoSizeText();
            }
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
            AppCompatTextViewAutoSizeHelper appcompattextviewautosizehelper = mTextHelper.mAutoSizeTextHelper;
            boolean flag;
            if (!(appcompattextviewautosizehelper.mTextView instanceof AppCompatEditText))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                android.util.DisplayMetrics displaymetrics = appcompattextviewautosizehelper.mContext.getResources().getDisplayMetrics();
                appcompattextviewautosizehelper.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(l, i, displaymetrics), TypedValue.applyDimension(l, j, displaymetrics), TypedValue.applyDimension(l, k, displaymetrics));
                if (appcompattextviewautosizehelper.setupAutoSizeText())
                {
                    appcompattextviewautosizehelper.autoSizeText();
                    return;
                }
            }
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int ai[], int i)
        throws IllegalArgumentException
    {
        boolean flag1 = false;
        if (!PLATFORM_SUPPORTS_AUTOSIZE) goto _L2; else goto _L1
_L1:
        super.setAutoSizeTextTypeUniformWithPresetSizes(ai, i);
_L4:
        return;
_L2:
        if (mTextHelper == null) goto _L4; else goto _L3
_L3:
        int ai1[];
        int ai2[];
        AppCompatTextViewAutoSizeHelper appcompattextviewautosizehelper;
        int k;
        appcompattextviewautosizehelper = mTextHelper.mAutoSizeTextHelper;
        boolean flag;
        if (!(appcompattextviewautosizehelper.mTextView instanceof AppCompatEditText))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L5
_L5:
        k = ai.length;
        if (k <= 0)
        {
            break MISSING_BLOCK_LABEL_182;
        }
        ai2 = new int[k];
        if (i != 0) goto _L7; else goto _L6
_L6:
        ai1 = Arrays.copyOf(ai, k);
_L9:
        appcompattextviewautosizehelper.mAutoSizeTextSizesInPx = AppCompatTextViewAutoSizeHelper.cleanupAutoSizePresetSizes(ai1);
        if (!appcompattextviewautosizehelper.setupAutoSizeUniformPresetSizesConfiguration())
        {
            throw new IllegalArgumentException((new StringBuilder("None of the preset sizes is valid: ")).append(Arrays.toString(ai)).toString());
        }
        continue; /* Loop/switch isn't completed */
_L7:
        android.util.DisplayMetrics displaymetrics = appcompattextviewautosizehelper.mContext.getResources().getDisplayMetrics();
        int j = ((flag1) ? 1 : 0);
        do
        {
            ai1 = ai2;
            if (j >= k)
            {
                break;
            }
            ai2[j] = Math.round(TypedValue.applyDimension(i, ai[j], displaymetrics));
            j++;
        } while (true);
        if (true) goto _L9; else goto _L8
_L8:
        appcompattextviewautosizehelper.mHasPresetAutoSizeValues = false;
        if (!appcompattextviewautosizehelper.setupAutoSizeText()) goto _L4; else goto _L10
_L10:
        appcompattextviewautosizehelper.autoSizeText();
        return;
    }

    public void setAutoSizeTextTypeWithDefaults(int i)
    {
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            super.setAutoSizeTextTypeWithDefaults(i);
        } else
        if (mTextHelper != null)
        {
            AppCompatTextViewAutoSizeHelper appcompattextviewautosizehelper = mTextHelper.mAutoSizeTextHelper;
            boolean flag;
            if (!(appcompattextviewautosizehelper.mTextView instanceof AppCompatEditText))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                android.util.DisplayMetrics displaymetrics;
                switch (i)
                {
                default:
                    throw new IllegalArgumentException((new StringBuilder("Unknown auto-size text type: ")).append(i).toString());

                case 0: // '\0'
                    appcompattextviewautosizehelper.mAutoSizeTextType = 0;
                    appcompattextviewautosizehelper.mAutoSizeMinTextSizeInPx = -1F;
                    appcompattextviewautosizehelper.mAutoSizeMaxTextSizeInPx = -1F;
                    appcompattextviewautosizehelper.mAutoSizeStepGranularityInPx = -1F;
                    appcompattextviewautosizehelper.mAutoSizeTextSizesInPx = new int[0];
                    appcompattextviewautosizehelper.mNeedsAutoSizeText = false;
                    return;

                case 1: // '\001'
                    displaymetrics = appcompattextviewautosizehelper.mContext.getResources().getDisplayMetrics();
                    break;
                }
                appcompattextviewautosizehelper.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12F, displaymetrics), TypedValue.applyDimension(2, 112F, displaymetrics), 1.0F);
                if (appcompattextviewautosizehelper.setupAutoSizeText())
                {
                    appcompattextviewautosizehelper.autoSizeText();
                    return;
                }
            }
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

    public void setSupportBackgroundTintList(ColorStateList colorstatelist)
    {
        if (mBackgroundTintHelper != null)
        {
            mBackgroundTintHelper.setSupportBackgroundTintList(colorstatelist);
        }
    }

    public void setSupportBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (mBackgroundTintHelper != null)
        {
            mBackgroundTintHelper.setSupportBackgroundTintMode(mode);
        }
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
        boolean flag1 = true;
        if (PLATFORM_SUPPORTS_AUTOSIZE)
        {
            super.setTextSize(i, f);
        } else
        if (mTextHelper != null)
        {
            AppCompatTextHelper appcompattexthelper = mTextHelper;
            if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE)
            {
                AppCompatTextViewAutoSizeHelper appcompattextviewautosizehelper = appcompattexthelper.mAutoSizeTextHelper;
                boolean flag;
                if (!(appcompattextviewautosizehelper.mTextView instanceof AppCompatEditText))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && appcompattextviewautosizehelper.mAutoSizeTextType != 0)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    appcompattexthelper.mAutoSizeTextHelper.setTextSizeInternal(i, f);
                    return;
                }
            }
        }
    }
}
