// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.image.helper;

import android.content.Context;
import android.text.Spannable;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.drawable.ExtendedBitmapDrawable;
import com.google.android.calendar.common.drawable.ListenableBitmapDrawable;
import com.google.android.calendar.event.image.helper.ImageHelper;
import com.google.android.calendar.timely.TimelineItem;

public abstract class AttributedImageHelper extends ImageHelper
{

    public AttributedImageHelper(Context context, TimelineItem timelineitem)
    {
        super(context, timelineitem, null, LayoutInflater.from(context), false);
    }

    public static Spannable removeUnderlines(Spannable spannable)
    {
        URLSpan aurlspan[] = (URLSpan[])spannable.getSpans(0, spannable.length(), android/text/style/URLSpan);
        int j = aurlspan.length;
        for (int i = 0; i < j; i++)
        {
            URLSpan urlspan = aurlspan[i];
            int k = spannable.getSpanStart(urlspan);
            int l = spannable.getSpanEnd(urlspan);
            spannable.removeSpan(urlspan);
            spannable.setSpan(new CustomURLSpan(urlspan.getURL()), k, l, 0);
        }

        return spannable;
    }

    protected ExtendedBitmapDrawable getExtendedBitmapDrawable(BitmapCache bitmapcache, com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions extendedoptions)
    {
        bitmapcache = new ListenableBitmapDrawable(super.context.getResources(), bitmapcache, true, extendedoptions);
        bitmapcache.onLoadCompleteListener = new OnImageLoaded();
        return bitmapcache;
    }

    public abstract void onImageLoadComplete();

    private class CustomURLSpan extends URLSpan
    {

        public final void updateDrawState(TextPaint textpaint)
        {
            super.updateDrawState(textpaint);
            textpaint.setUnderlineText(false);
        }

        public CustomURLSpan(String s)
        {
            super(s);
        }
    }


    private class OnImageLoaded
        implements com.google.android.calendar.common.drawable.ListenableBitmapDrawable.OnLoadCompleteListener
    {

        private final AttributedImageHelper this$0;

        public final void onLoadComplete(ListenableBitmapDrawable listenablebitmapdrawable)
        {
            onImageLoadComplete();
        }

        OnImageLoaded()
        {
            this$0 = AttributedImageHelper.this;
            super();
        }
    }

}
