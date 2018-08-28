// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.widget.Checkable;

public class CheckableImageButton extends AppCompatImageButton
    implements Checkable
{

    private static final int DRAWABLE_STATE_CHECKED[] = {
        0x10100a0
    };
    private boolean checked;

    public CheckableImageButton(Context context)
    {
        this(context, null);
    }

    public CheckableImageButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100ae);
    }

    public CheckableImageButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        ViewCompat.setAccessibilityDelegate(this, new _cls1());
    }

    public boolean isChecked()
    {
        return checked;
    }

    public int[] onCreateDrawableState(int i)
    {
        if (checked)
        {
            return mergeDrawableStates(super.onCreateDrawableState(DRAWABLE_STATE_CHECKED.length + i), DRAWABLE_STATE_CHECKED);
        } else
        {
            return super.onCreateDrawableState(i);
        }
    }

    public void setChecked(boolean flag)
    {
        if (checked != flag)
        {
            checked = flag;
            refreshDrawableState();
            sendAccessibilityEvent(2048);
        }
    }

    public void toggle()
    {
        boolean flag;
        if (!checked)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setChecked(flag);
    }


    private class _cls1 extends AccessibilityDelegateCompat
    {

        private final CheckableImageButton this$0;

        public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            accessibilityevent.setChecked(isChecked());
        }

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            accessibilitynodeinfocompat.mInfo.setCheckable(true);
            boolean flag = isChecked();
            accessibilitynodeinfocompat.mInfo.setChecked(flag);
        }

        _cls1()
        {
            this$0 = CheckableImageButton.this;
            super();
        }
    }

}
