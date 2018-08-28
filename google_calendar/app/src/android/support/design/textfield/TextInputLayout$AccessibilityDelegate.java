// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.textfield;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;

// Referenced classes of package android.support.design.textfield:
//            TextInputLayout, IndicatorViewController

public static final class layout extends AccessibilityDelegateCompat
{

    private final TextInputLayout layout;

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        Object obj = null;
        boolean flag4 = false;
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        view = layout.editText;
        CharSequence charsequence;
        android.text.Editable editable;
        CharSequence charsequence1;
        TextInputLayout textinputlayout;
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if (view != null)
        {
            editable = view.getText();
        } else
        {
            editable = null;
        }
        view = layout;
        if (((TextInputLayout) (view)).hintEnabled)
        {
            charsequence1 = ((TextInputLayout) (view)).hint;
        } else
        {
            charsequence1 = null;
        }
        view = layout;
        if (((TextInputLayout) (view)).indicatorViewController.errorEnabled)
        {
            view = ((TextInputLayout) (view)).indicatorViewController.errorText;
        } else
        {
            view = null;
        }
        textinputlayout = layout;
        charsequence = obj;
        if (textinputlayout.counterEnabled)
        {
            charsequence = obj;
            if (textinputlayout.counterOverflowed)
            {
                charsequence = obj;
                if (textinputlayout.counterView != null)
                {
                    charsequence = textinputlayout.counterView.getContentDescription();
                }
            }
        }
        if (!TextUtils.isEmpty(editable))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!TextUtils.isEmpty(charsequence1))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!TextUtils.isEmpty(view))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 || !TextUtils.isEmpty(charsequence))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (flag)
        {
            accessibilitynodeinfocompat.mInfo.setText(editable);
        } else
        if (flag2)
        {
            accessibilitynodeinfocompat.mInfo.setText(charsequence1);
        }
        if (flag2)
        {
            boolean flag5;
            if (android.os.setText >= 26)
            {
                accessibilitynodeinfocompat.mInfo.setHintText(charsequence1);
            } else
            {
                accessibilitynodeinfocompat.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charsequence1);
            }
            if (!flag && flag2)
            {
                flag5 = true;
            } else
            {
                flag5 = false;
            }
            if (android.os.getExtras >= 26)
            {
                accessibilitynodeinfocompat.mInfo.setShowingHintText(flag5);
            } else
            {
                Bundle bundle = accessibilitynodeinfocompat.mInfo.getExtras();
                if (bundle != null)
                {
                    int i = bundle.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0);
                    byte byte0 = flag4;
                    if (flag5)
                    {
                        byte0 = 4;
                    }
                    bundle.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i & -5 | byte0);
                }
            }
        }
        if (flag3)
        {
            if (!flag1)
            {
                view = charsequence;
            }
            accessibilitynodeinfocompat.mInfo.setError(view);
            accessibilitynodeinfocompat.mInfo.setContentInvalid(true);
        }
    }

    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        Object obj = null;
        super.onPopulateAccessibilityEvent(view, accessibilityevent);
        view = layout.editText;
        if (view != null)
        {
            view = view.getText();
        } else
        {
            view = null;
        }
        if (TextUtils.isEmpty(view))
        {
            TextInputLayout textinputlayout = layout;
            view = obj;
            if (textinputlayout.hintEnabled)
            {
                view = textinputlayout.hint;
            }
        }
        if (!TextUtils.isEmpty(view))
        {
            accessibilityevent.getText().add(view);
        }
    }

    public (TextInputLayout textinputlayout)
    {
        layout = textinputlayout;
    }
}
