// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.birthday;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Parcel;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.utils.ColorUtils;
import com.google.common.util.concurrent.FutureCallback;

public class BirthdayViewScreenModel extends ViewScreenModel
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private static final String TAG = com/google/android/calendar/newapi/screen/birthday/BirthdayViewScreenModel.getSimpleName();

    BirthdayViewScreenModel(Parcel parcel)
    {
        super(parcel);
    }

    public BirthdayViewScreenModel(TimelineBirthday timelinebirthday)
    {
        super(timelinebirthday);
    }

    public final Drawable getBackgroundDrawable(Context context, FutureCallback futurecallback)
    {
        return new LayerDrawable(new Drawable[] {
            context.getResources().getDrawable(0x7f020145), new ColorDrawable(context.getResources().getColor(0x7f0d0134))
        });
    }

    public final int getColor(Context context)
    {
        Drawable drawable = context.getResources().getDrawable(0x7f020145);
        int i = context.getResources().getColor(0x7f0d0134);
        if (drawable instanceof BitmapDrawable)
        {
            return ColorUtils.blend(((BitmapDrawable)drawable).getBitmap().getPixel(0, 0), i);
        } else
        {
            LogUtils.wtf(TAG, "birthdayImage is not of type BitmapDrawable", new Object[0]);
            return -1;
        }
    }

    protected final Class getTimelineItemClass()
    {
        return com/google/android/calendar/timely/TimelineBirthday;
    }

    public final String getTitle()
    {
        return null;
    }

    public final boolean hasImage(Context context)
    {
        return true;
    }

    public final boolean isEditable()
    {
        return false;
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new BirthdayViewScreenModel(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new BirthdayViewScreenModel[i];
        }

        _cls1()
        {
        }
    }

}
