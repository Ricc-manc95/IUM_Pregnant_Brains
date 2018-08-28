// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.bitmap.BitmapCache;
import com.google.android.calendar.event.image.BitmapCacheHolder;

public class AttachmentView extends FrameLayout
{

    public ImageView background;
    public TextView fileNameView;
    public TextView fileTypeView;
    public final BitmapCache iconCache = BitmapCacheHolder.getAttachmentIconCache();
    public final int iconHeight = getResources().getDimensionPixelSize(0x7f0e005c);
    public final int iconWidth = getResources().getDimensionPixelSize(0x7f0e005d);

    public AttachmentView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        fileNameView = (TextView)findViewById(0x7f100248);
        fileTypeView = (TextView)findViewById(0x7f100249);
        background = (ImageView)findViewById(0x7f1000d1);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        if (getContentDescription() == null)
        {
            setContentDescription(getResources().getString(0x7f130315, new Object[] {
                fileNameView.getText(), fileTypeView.getText()
            }));
        }
        accessibilityevent.setClassName(android/widget/Button.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/widget/Button.getName());
    }
}
