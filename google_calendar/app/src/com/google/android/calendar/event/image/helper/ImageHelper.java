// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.android.bitmap.drawable.ExtendedBitmapDrawable;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.graphics.RtlMirroring;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.common.drawable.ListenableBitmapDrawable;
import com.google.android.calendar.event.image.BitmapCacheHolder;
import com.google.android.calendar.event.image.EventImageCache;
import com.google.android.calendar.event.image.EventImageFutureCache;
import com.google.android.calendar.event.image.EventImageRequestKey;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

public class ImageHelper
{
    public static final class EventBitmapDrawable extends ListenableBitmapDrawable
    {

        private boolean isRtl;

        protected final Executor getExecutor()
        {
            return CalendarExecutor.BACKGROUND;
        }

        protected final void onDrawBitmap(Canvas canvas, Rect rect, Rect rect1)
        {
            Object obj = super.mCurrKey;
            boolean flag;
            if (!isRtl || !(obj instanceof EventImageRequestKey))
            {
                flag = false;
            } else
            {
                flag = ((EventImageRequestKey)obj).rtlMirroring.mirrorInRtl();
            }
            if (flag)
            {
                canvas.save();
                obj = new Matrix();
                ((Matrix) (obj)).setScale(-1F, 1.0F);
                ((Matrix) (obj)).postTranslate(canvas.getWidth(), 0.0F);
                canvas.concat(((Matrix) (obj)));
                super.onDrawBitmap(canvas, rect, rect1);
                canvas.restore();
                return;
            } else
            {
                super.onDrawBitmap(canvas, rect, rect1);
                return;
            }
        }

        public EventBitmapDrawable(Context context1, BitmapCache bitmapcache, boolean flag, com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions extendedoptions)
        {
            super(context1.getResources(), bitmapcache, false, extendedoptions);
            isRtl = false;
            isRtl = RtlUtils.isLayoutDirectionRtl(context1);
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/event/image/helper/ImageHelper);
    public final Context context;
    public final ExtendedBitmapDrawable drawable;
    public final EventImageCache imageCache = BitmapCacheHolder.getEventImageCache();
    private ListenableFuture requestKey;
    public final ImageView view;

    public ImageHelper(Context context1, TimelineItem timelineitem, ViewGroup viewgroup, LayoutInflater layoutinflater, boolean flag)
    {
        if (!timelineitem.hasHeadlineImage())
        {
            throw new IllegalArgumentException();
        }
        context = context1;
        int j = context.getResources().getColor(0x7f0d0134);
        viewgroup = timelineitem.getEventImageResolver();
        if (viewgroup == null)
        {
            viewgroup = null;
        } else
        {
            viewgroup = EventImageFutureCache.getFuture(context1, viewgroup, context1.getResources().getDimensionPixelSize(0x7f0e009d), context1.getResources().getDimensionPixelSize(0x7f0e009c));
        }
        requestKey = viewgroup;
        if (requestKey != null)
        {
            viewgroup = new com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions(4);
            int i;
            byte byte0;
            boolean flag1;
            if (timelineitem.getColor() == 0)
            {
                i = ContextCompat.getColor(context1, 0x7f0d01d7);
            } else
            {
                i = timelineitem.getColor();
            }
            viewgroup.backgroundColor = i;
            viewgroup.decodeHorizontalCenter = 1.0F;
            viewgroup.decodeVerticalCenter = 0.49F;
            drawable = getExtendedBitmapDrawable(imageCache, viewgroup);
            flag1 = TimelineItemUtil.hasAssist(timelineitem);
            if (flag1)
            {
                byte0 = 3;
            } else
            {
                byte0 = 2;
            }
            timelineitem = new Drawable[byte0];
            if (flag1)
            {
                timelineitem[0] = new ColorDrawable(i);
                i = 1;
            } else
            {
                i = 0;
            }
            timelineitem[i] = drawable;
            timelineitem[i + 1] = new ColorDrawable(j);
            drawable.setDecodeDimensions(context1.getResources().getDimensionPixelSize(0x7f0e009d), context1.getResources().getDimensionPixelSize(0x7f0e009c));
            context1 = new LayerDrawable(timelineitem);
            timelineitem = layoutinflater.inflate(0x7f050061, null, false);
            if (timelineitem instanceof ImageView)
            {
                view = (ImageView)timelineitem;
                view.setImageDrawable(context1);
            } else
            {
                view = null;
            }
            if (flag)
            {
                bind();
            }
            return;
        } else
        {
            drawable = null;
            view = null;
            return;
        }
    }

    public static boolean shouldHaveImage(Resources resources)
    {
        boolean flag2 = false;
        boolean flag;
        boolean flag1;
        if (resources.getConfiguration().screenHeightDp >= 490)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!resources.getBoolean(0x7f0c0014))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag || flag1)
        {
            flag2 = true;
        }
        return flag2;
    }

    public static boolean shouldHaveImage(Resources resources, TimelineItem timelineitem)
    {
        return timelineitem != null && timelineitem.hasHeadlineImage() && shouldHaveImage(resources);
    }

    public final void bind()
    {
        if (requestKey != null && drawable != null)
        {
            ListenableFuture listenablefuture = requestKey;
            _cls1 _lcls1 = new _cls1();
            com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0 _lcls0 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
            if (_lcls1 == null)
            {
                throw new NullPointerException();
            }
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, _lcls1), _lcls0);
        }
    }

    public ExtendedBitmapDrawable getExtendedBitmapDrawable(BitmapCache bitmapcache, com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions extendedoptions)
    {
        return new EventBitmapDrawable(context, bitmapcache, false, extendedoptions);
    }


    private class _cls1
        implements FutureCallback
    {

        private final ImageHelper this$0;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.e(ImageHelper.TAG, throwable, "Key resolution failed.", new Object[0]);
            drawable.bind(null);
        }

        public final void onSuccess(Object obj)
        {
            obj = (EventImageRequestKey)obj;
            drawable.bind(((com.android.bitmap.RequestKey) (obj)));
        }

        _cls1()
        {
            this$0 = ImageHelper.this;
            super();
        }
    }

}
