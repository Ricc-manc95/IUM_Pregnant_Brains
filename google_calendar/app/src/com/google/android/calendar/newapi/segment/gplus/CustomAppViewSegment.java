// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.gplus;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.model.GooglePlusHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.common.base.Optional;

public class CustomAppViewSegment extends TextTileView
    implements android.view.View.OnClickListener, ViewSegment
{

    private CustomEvent customEvent;
    public final EventHolder model;

    public CustomAppViewSegment(Context context, EventHolder eventholder)
    {
        super(context);
        model = eventholder;
        context = getContext();
        eventholder = model.getEvent();
        customEvent = CustomEvent.extractInformation(context, (Uri)eventholder.getDescriptor().getKey().uri().orNull(), eventholder.getStartMillis(), eventholder.getCustomAppPackage(), eventholder.getCustomAppUri());
        if (customEvent != null)
        {
            setIconDrawable(getIconDrawable());
        }
    }

    protected Drawable getIconDrawable()
    {
        if (customEvent.icon == null)
        {
            return customEvent.logo;
        } else
        {
            return customEvent.icon;
        }
    }

    public void onClick(View view)
    {
        getContext().startActivity(customEvent.intent);
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        treatAsButton(true);
        super.denseMode = false;
        setOnClickListener(this);
    }

    protected boolean showSegment()
    {
        return !((GooglePlusHolder)model).isGooglePlusEvent();
    }

    public final void updateUi()
    {
        boolean flag;
        if (customEvent != null && showSegment())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (this != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            setVisibility(i);
        }
        if (!flag)
        {
            return;
        } else
        {
            CharSequence charsequence = customEvent.label;
            setPrimaryText(new CharSequence[] {
                getResources().getString(0x7f130148, new Object[] {
                    charsequence
                })
            });
            useTextAsContentDescription(0x7f130169);
            return;
        }
    }

    private class CustomEvent
    {

        public final Drawable icon;
        public final Intent intent;
        public final CharSequence label;
        public final Drawable logo;

        static CustomEvent extractInformation(Context context, Uri uri, long l, String s, String s1)
        {
            if (uri != null && !TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1))
            {
                context = context.getPackageManager();
                if (context != null)
                {
                    android.content.pm.ApplicationInfo applicationinfo;
                    try
                    {
                        applicationinfo = context.getApplicationInfo(s, 0);
                    }
                    // Misplaced declaration of an exception variable
                    catch (Context context)
                    {
                        return null;
                    }
                    if (applicationinfo != null)
                    {
                        uri = new Intent("android.provider.calendar.action.HANDLE_CUSTOM_EVENT", uri);
                        uri.setPackage(s);
                        uri.putExtra("customAppUri", s1);
                        uri.putExtra("beginTime", l);
                        ActivityUtils.prepareIntentToOpenLink(uri);
                        if (context.resolveActivity(uri, 0) != null)
                        {
                            s = context.getApplicationIcon(applicationinfo);
                            s1 = context.getApplicationLabel(applicationinfo);
                            if (s1 != null && s1.length() != 0 || s != null)
                            {
                                return new CustomEvent(uri, s, s1, context.getApplicationLogo(applicationinfo));
                            }
                        }
                    }
                }
            }
            return null;
        }

        private CustomEvent(Intent intent1, Drawable drawable, CharSequence charsequence, Drawable drawable1)
        {
            intent = intent1;
            icon = drawable;
            label = charsequence;
            logo = drawable1;
        }
    }

}
