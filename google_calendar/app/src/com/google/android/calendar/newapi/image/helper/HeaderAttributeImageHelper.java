// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.image.helper;

import android.content.Context;
import android.support.v4.util.LruCache;
import android.text.Html;
import android.text.SpannableStringBuilder;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.android.bitmap.drawable.ExtendedBitmapDrawable;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.common.drawable.ListenableBitmapDrawable;
import com.google.android.calendar.event.image.EventImageCache;
import com.google.android.calendar.event.image.EventImageRequestKey;
import com.google.android.calendar.event.image.PlacePageOrMapUrl;
import com.google.android.calendar.event.image.helper.ImageHelper;
import com.google.android.calendar.timely.TimelineItem;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.image.helper:
//            AttributedImageHelper

public class HeaderAttributeImageHelper extends AttributedImageHelper
    implements com.google.android.calendar.common.drawable.ListenableBitmapDrawable.OnLoadCompleteListener
{

    private static final LruCache attributionCache = new LruCache(25);
    private final FutureCallback attributeLoaderCallback;

    public HeaderAttributeImageHelper(Context context, TimelineItem timelineitem, FutureCallback futurecallback)
    {
        super(context, timelineitem);
        attributeLoaderCallback = futurecallback;
    }

    protected final ExtendedBitmapDrawable getExtendedBitmapDrawable(BitmapCache bitmapcache, com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions extendedoptions)
    {
        bitmapcache = new com.google.android.calendar.event.image.helper.ImageHelper.EventBitmapDrawable(super.context, bitmapcache, false, extendedoptions);
        bitmapcache.onLoadCompleteListener = this;
        return bitmapcache;
    }

    protected ListenableFuture loadAttributeLicenseAsync(Context context, EventImageRequestKey eventimagerequestkey)
    {
        eventimagerequestkey = eventimagerequestkey.mUrlString;
        Object obj = (List)PlacePageOrMapUrl.attributionsMap.get(eventimagerequestkey);
        eventimagerequestkey = new ArrayList();
        if (obj != null)
        {
            eventimagerequestkey.addAll(((java.util.Collection) (obj)));
        }
        obj = new SpannableStringBuilder();
        context = context.getString(0x7f130320);
        for (int i = 0; i < eventimagerequestkey.size(); i++)
        {
            ((SpannableStringBuilder) (obj)).append(Html.fromHtml((String)eventimagerequestkey.get(i)));
            if (i < eventimagerequestkey.size() - 1)
            {
                ((SpannableStringBuilder) (obj)).append(context);
            }
        }

        context = AttributedImageHelper.removeUnderlines(((android.text.Spannable) (obj)));
        if (context == null)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(context);
        }
    }

    public final void onImageLoadComplete()
    {
    }

    public final void onLoadComplete(ListenableBitmapDrawable listenablebitmapdrawable)
    {
        Object obj1;
        if (listenablebitmapdrawable != null)
        {
            if ((obj1 = super.imageCache.getKey(((BasicBitmapDrawable) (listenablebitmapdrawable)).mCurrKey)) != null && ((EventImageRequestKey) (obj1)).mUrlString != null)
            {
                Object obj = (ListenableFuture)attributionCache.get(obj1);
                listenablebitmapdrawable = ((ListenableBitmapDrawable) (obj));
                if (obj == null)
                {
                    listenablebitmapdrawable = loadAttributeLicenseAsync(super.context, ((EventImageRequestKey) (obj1)));
                    attributionCache.put(obj1, listenablebitmapdrawable);
                }
                obj = attributeLoaderCallback;
                obj1 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
                if (obj == null)
                {
                    throw new NullPointerException();
                } else
                {
                    listenablebitmapdrawable.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablebitmapdrawable, ((FutureCallback) (obj))), ((java.util.concurrent.Executor) (obj1)));
                    return;
                }
            }
        }
    }

}
