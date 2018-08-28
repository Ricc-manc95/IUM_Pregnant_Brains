// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.calendar.Utils;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.Formatter;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            TitleViewSegment

public final class BirthdayTitleViewSegment extends TitleViewSegment
{

    public BirthdayTitleViewSegment(Context context, ViewScreenModel viewscreenmodel)
    {
        super(context, viewscreenmodel);
    }

    public final void onLayout(boolean flag, int i, int j, int k, int l)
    {
        ImageView imageview;
        Object obj;
label0:
        {
            super.onLayout(flag, i, j, k, l);
            imageview = super.headerView;
            if (imageview != null)
            {
                obj = imageview.getDrawable();
                if (obj != null)
                {
                    break label0;
                }
            }
            return;
        }
        int i1 = ((Drawable) (obj)).getIntrinsicHeight();
        int j1 = ((Drawable) (obj)).getIntrinsicWidth();
        float f = (float)(l - j) / (float)i1;
        obj = imageview.getImageMatrix();
        ((Matrix) (obj)).setScale(f, f);
        if (RtlUtils.isLayoutDirectionRtl(getContext()))
        {
            ((Matrix) (obj)).postScale(-1F, 1.0F);
            ((Matrix) (obj)).postTranslate((float)j1 * f, 0.0F);
        } else
        {
            ((Matrix) (obj)).postTranslate((float)(k - i) - (float)j1 * f, 0.0F);
        }
        imageview.setImageMatrix(((Matrix) (obj)));
    }

    protected final void updateHeaderImageSize(ImageView imageview)
    {
        super.updateHeaderImageSize(imageview);
        imageview.setScaleType(android.widget.ImageView.ScaleType.MATRIX);
    }

    public final void updateUi()
    {
        super.updateUi();
        Object obj = getContext();
        long l = Utils.convertAlldayUtcToLocal(null, super.model.timelineItem.getStartMillis(), Utils.getTimeZoneId(((Context) (obj))));
        obj = DateUtils.formatDateRange(((Context) (obj)), new Formatter(new StringBuilder(), Locale.getDefault()), l, l, 24, Utils.getTimeZoneId(((Context) (obj)), null)).toString();
        ((TextView)findViewById(0x7f100047)).setText(getResources().getString(0x7f1300e3, new Object[] {
            obj
        }));
        class .Lambda._cls0
            implements Runnable
        {

            private final BirthdayTitleViewSegment arg$1;
            private final String arg$2;

            public final void run()
            {
                BirthdayTitleViewSegment birthdaytitleviewsegment = arg$1;
                String s = arg$2;
                if (((TextView)birthdaytitleviewsegment.findViewById(0x7f100047)).getLineCount() > 1)
                {
                    ((TextView)birthdaytitleviewsegment.findViewById(0x7f100047)).setText(birthdaytitleviewsegment.getResources().getString(0x7f1300e4, new Object[] {
                        s
                    }));
                }
            }

            .Lambda._cls0(String s)
            {
                arg$1 = BirthdayTitleViewSegment.this;
                arg$2 = s;
            }
        }

        ((TextView)findViewById(0x7f100047)).post(new .Lambda._cls0(((String) (obj))));
    }
}
