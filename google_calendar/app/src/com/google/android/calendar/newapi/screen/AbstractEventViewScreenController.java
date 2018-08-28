// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.segment.title.TitleViewSegment;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.animations.EventInfoAnimationView;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreenController, ViewScreen, SegmentViews, ViewScreenOpenCloseHelper

public abstract class AbstractEventViewScreenController extends ViewScreenController
{

    public AbstractEventViewScreenController()
    {
    }

    static final void lambda$notifyContentProviderUpdateIfAvailable$0$AbstractEventViewScreenController(AbstractEventViewScreenController abstracteventviewscreencontroller, Uri uri)
    {
        if (!((ViewScreenController) (abstracteventviewscreencontroller)).started)
        {
            return;
        } else
        {
            abstracteventviewscreencontroller.onContentProviderUpdated(uri);
            return;
        }
    }

    public static void notifyContentProviderUpdateIfAvailable(FragmentManager fragmentmanager, Uri uri)
    {
        fragmentmanager = (ViewScreenController)fragmentmanager.findFragmentByTag("ViewScreenController");
        if (!(fragmentmanager instanceof AbstractEventViewScreenController) || !((ViewScreenController) (fragmentmanager)).started)
        {
            return;
        } else
        {
            fragmentmanager = (AbstractEventViewScreenController)fragmentmanager;
            class .Lambda._cls0
                implements Runnable
            {

                private final AbstractEventViewScreenController arg$1;
                private final Uri arg$2;

                public final void run()
                {
                    AbstractEventViewScreenController.lambda$notifyContentProviderUpdateIfAvailable$0$AbstractEventViewScreenController(arg$1, arg$2);
                }

            .Lambda._cls0(Uri uri)
            {
                arg$1 = AbstractEventViewScreenController.this;
                arg$2 = uri;
            }
            }

            (new Handler(Looper.getMainLooper())).post(fragmentmanager. new .Lambda._cls0(uri));
            return;
        }
    }

    public void onContentProviderUpdated(Uri uri)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj != null)
        {
            if ((obj = (EventKey)((TimelineEvent)super.model.timelineItem).eventKey) != null)
            {
                obj = ((EventKey) (obj)).uri().orNull();
                boolean flag;
                if (uri == obj || uri != null && uri.equals(obj))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    uri = (TitleViewSegment)super.viewScreen.segmentViews.headerView;
                    if (uri != null)
                    {
                        uri.updateImage();
                    }
                    uri = super.animationHelper;
                    if (((ViewScreenOpenCloseHelper) (uri)).animationData != null)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        ((ViewScreenOpenCloseHelper) (uri)).animationView.reinitialize(((ViewScreenOpenCloseHelper) (uri)).timelineItem, uri.createAnimationHeader());
                    }
                    setLoader(createLoader(false));
                    super.loader.load();
                    return;
                }
            }
        }
    }

    public final void updateSegments()
    {
        TimelineEvent timelineevent = (TimelineEvent)super.model.timelineItem;
        ViewScreenModel viewscreenmodel = super.model;
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        timelineevent.color = viewscreenmodel.getColor(((android.content.Context) (obj)));
        super.updateSegments();
    }
}
