// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.attendees;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.calendar.avatar.BadgeViewFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.gridviews.attendees:
//            AttendeeInfoView, AttendeeInfo, AttendeeLoadingView

public class AttendeeInfoLayout extends LinearLayout
{

    private final List attendeeInfoViews = new ArrayList();
    private int columnWidth;
    private final int gridLineColor;
    private final Paint gridLinePaint = new Paint();
    private final int gridLineStrokeWidth;
    public final int height;

    public AttendeeInfoLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        columnWidth = 0;
        setWillNotDraw(false);
        setOrientation(0);
        context = context.getResources();
        gridLineColor = context.getColor(0x7f0d011a);
        gridLineStrokeWidth = context.getDimensionPixelOffset(0x7f0e01dc);
        gridLinePaint.setStrokeWidth(gridLineStrokeWidth);
        gridLinePaint.setColor(gridLineColor);
        gridLinePaint.setAntiAlias(false);
        height = context.getDimensionPixelSize(0x7f0e017b);
    }

    private final AttendeeInfoView getAttendeeInfoView(int i)
    {
        if (i < attendeeInfoViews.size())
        {
            return (AttendeeInfoView)attendeeInfoViews.get(i);
        } else
        {
            AttendeeInfoView attendeeinfoview = new AttendeeInfoView(getContext());
            attendeeInfoViews.add(attendeeinfoview);
            return attendeeinfoview;
        }
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int j = getHeight();
        int k = getChildCount();
        int l = columnWidth;
        for (int i = columnWidth; i <= (k - 1) * l; i = columnWidth + i)
        {
            canvas.drawLine(i, 0.0F, i, j, gridLinePaint);
        }

    }

    public final void onUpdate(List list, int i, boolean flag)
    {
        removeAllViews();
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(columnWidth, height);
        Iterator iterator = list.iterator();
        int j = 0;
        while (iterator.hasNext()) 
        {
            list = (AttendeeInfo)iterator.next();
            AttendeeInfoView attendeeinfoview = getAttendeeInfoView(j);
            attendeeinfoview.setLayoutParams(layoutparams);
            addView(attendeeinfoview);
            String s1 = list.getSourceAccount();
            String s = list.getEmail();
            Object obj = list.getDisplayName();
            boolean flag1 = list.isDisabled();
            list = ((List) (obj));
            if (TextUtils.isEmpty(((CharSequence) (obj))))
            {
                list = s;
            }
            attendeeinfoview.displayNameView.setText(list);
            if (flag1)
            {
                obj = String.valueOf(list);
                String s2 = attendeeinfoview.getResources().getString(0x7f130352);
                obj = (new StringBuilder(String.valueOf(obj).length() + 2 + String.valueOf(s2).length())).append(((String) (obj))).append(", ").append(s2).toString();
            } else
            {
                obj = list;
            }
            attendeeinfoview.setContentDescription(((CharSequence) (obj)));
            if (attendeeinfoview.photoView != null)
            {
                obj = attendeeinfoview.photoView;
                int k = attendeeinfoview.imageSize;
                Context context = ((ImageView) (obj)).getContext();
                obj.getClass();
                BadgeViewFactory.setupAttendeeBadge(context, new com.google.android.calendar.avatar.BadgeViewFactory..Lambda._cls0(((ImageView) (obj))), k, s1, list, s);
                ((ImageView) (obj)).setContentDescription(list);
                list = attendeeinfoview.photoView;
                float f;
                if (flag1)
                {
                    f = 0.54F;
                } else
                {
                    f = 1.0F;
                }
                list.setAlpha(f);
            }
            j++;
        }
        if (i > 0)
        {
            list = getAttendeeInfoView(j);
            obj = list.getResources();
            ((AttendeeInfoView) (list)).displayNameView.setText(((Resources) (obj)).getQuantityString(0x7f120017, i, new Object[] {
                Integer.valueOf(i)
            }));
            if (((AttendeeInfoView) (list)).photoView != null)
            {
                ((AttendeeInfoView) (list)).photoView.setImageDrawable(((Resources) (obj)).getDrawable(0x7f0201a8));
                ((AttendeeInfoView) (list)).photoView.setAlpha(1.0F);
            }
            list.setLayoutParams(layoutparams);
            addView(list);
        }
        if (flag)
        {
            list = new AttendeeLoadingView(getContext());
            list.setLayoutParams(layoutparams);
            addView(list);
        }
    }

    public void setColumnWidth(int i)
    {
        if (columnWidth != i)
        {
            columnWidth = i;
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(columnWidth, height);
            for (i = getChildCount() - 1; i >= 0; i--)
            {
                getChildAt(i).setLayoutParams(layoutparams);
            }

        }
    }
}
