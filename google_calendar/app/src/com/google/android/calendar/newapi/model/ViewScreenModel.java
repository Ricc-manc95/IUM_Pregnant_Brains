// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import com.google.android.calendar.event.image.helper.ImageHelper;
import com.google.android.calendar.timely.TimelineItem;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.newapi.model:
//            ColorHolder, TimelineItemHolder, TitleHolder, ViewTypeHolder

public abstract class ViewScreenModel
    implements Parcelable, ColorHolder, TimelineItemHolder, TitleHolder, ViewTypeHolder
{

    public TimelineItem timelineItem;

    public ViewScreenModel(Parcel parcel)
    {
        setTimelineItem((TimelineItem)parcel.readParcelable(getTimelineItemClass().getClassLoader()));
    }

    public ViewScreenModel(TimelineItem timelineitem)
    {
        setTimelineItem(timelineitem);
    }

    public int describeContents()
    {
        return 0;
    }

    public Drawable getBackgroundDrawable(Context context, FutureCallback futurecallback)
    {
        return (new ImageHelper(context, timelineItem, null, LayoutInflater.from(context), true)).view.getDrawable();
    }

    public String getCategory()
    {
        return "";
    }

    public int getColor(Context context)
    {
        return timelineItem.getColor();
    }

    public final TimelineItem getTimelineItem()
    {
        return timelineItem;
    }

    public abstract Class getTimelineItemClass();

    public String getTitle()
    {
        return timelineItem.getTitle();
    }

    public String getViewType()
    {
        return null;
    }

    public boolean hasImage(Context context)
    {
        return ImageHelper.shouldHaveImage(context.getResources(), timelineItem);
    }

    public boolean hideDetails()
    {
        return false;
    }

    public boolean isEditable()
    {
        return false;
    }

    public void mergeModel(ViewScreenModel viewscreenmodel)
    {
        setTimelineItem(viewscreenmodel.timelineItem);
    }

    public void setTimelineItem(TimelineItem timelineitem)
    {
        timelineItem = timelineitem;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(timelineItem, i);
    }
}
