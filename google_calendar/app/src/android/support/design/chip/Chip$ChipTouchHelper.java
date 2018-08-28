// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.chip;

import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

// Referenced classes of package android.support.design.chip:
//            Chip, ChipDrawable

final class nit> extends ExploreByTouchHelper
{

    private final Chip this$0;

    protected final int getVirtualViewAt(float f, float f1)
    {
        return !hasCloseIcon() || !getCloseIconTouchBounds().contains(f, f1) ? -1 : 0;
    }

    protected final void getVisibleVirtualViews(List list)
    {
        if (hasCloseIcon())
        {
            list.add(Integer.valueOf(0));
        }
    }

    protected final boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
    {
        if (j == 16 && i == 0)
        {
            return performCloseIconClick();
        } else
        {
            return false;
        }
    }

    protected final void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        Object obj;
        boolean flag;
        if (chipDrawable != null && chipDrawable.checkable)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        accessibilitynodeinfocompat.mInfo.setCheckable(flag);
        obj = android/support/design/chip/Chip.getName();
        accessibilitynodeinfocompat.mInfo.setClassName(((CharSequence) (obj)));
        if (chipDrawable != null)
        {
            obj = chipDrawable.rawText;
        } else
        {
            obj = "";
        }
        if (android.os. >= 23)
        {
            accessibilitynodeinfocompat.mInfo.setText(((CharSequence) (obj)));
            return;
        } else
        {
            accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj)));
            return;
        }
    }

    protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        if (hasCloseIcon())
        {
            Object obj = Chip.this;
            if (((Chip) (obj)).chipDrawable != null)
            {
                obj = ((Chip) (obj)).chipDrawable;
            }
            boolean flag;
            if (false)
            {
                accessibilitynodeinfocompat.mInfo.setContentDescription(null);
            } else
            {
                Object obj1 = getText();
                Context context = getContext();
                if (TextUtils.isEmpty(((CharSequence) (obj1))))
                {
                    obj1 = "";
                }
                obj1 = context.getString(0x7f130344, new Object[] {
                    obj1
                }).trim();
                accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj1)));
            }
            obj = getCloseIconTouchBoundsInt();
            accessibilitynodeinfocompat.mInfo.setBoundsInParent(((android.graphics.Rect) (obj)));
            obj = android.support.v4.view.accessibility.Compat.AccessibilityActionCompat.ACTION_CLICK;
            accessibilitynodeinfocompat.mInfo.addAction((android.view.accessibility..AccessibilityAction)((android.support.v4.view.accessibility.Compat.AccessibilityActionCompat) (obj)).mAction);
            flag = isEnabled();
            accessibilitynodeinfocompat.mInfo.setEnabled(flag);
            return;
        } else
        {
            accessibilitynodeinfocompat.mInfo.setContentDescription("");
            android.graphics.Rect rect = Chip.EMPTY_BOUNDS;
            accessibilitynodeinfocompat.mInfo.setBoundsInParent(rect);
            return;
        }
    }

    tyNodeInfoCompat(Chip chip1)
    {
        this$0 = Chip.this;
        super(chip1);
    }
}
