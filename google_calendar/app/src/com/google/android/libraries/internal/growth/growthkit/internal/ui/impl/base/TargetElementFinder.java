// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.visualelements.VisualElementViewFinder;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Queue;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base:
//            UiUtils

public final class TargetElementFinder
    implements Serializable
{

    private static final Logger logger = new Logger();
    private final Optional optVisualElementViewFinder;

    public TargetElementFinder(Optional optional)
    {
        optVisualElementViewFinder = optional;
    }

    public static View findElementById(Activity activity, View view, String s)
    {
        int i = activity.getResources().getIdentifier(s, "id", activity.getPackageName());
        if (i == 0)
        {
            logger.w("Did not find resource with id %s", new Object[] {
                s
            });
            activity = null;
        } else
        {
            View view1;
            if (view == null)
            {
                view = activity.findViewById(0x1020002);
            }
            view1 = view.findViewById(i);
            view = view1;
            if (view1 == null)
            {
                activity = UiUtils.getDialogRootViewIfExists(activity);
                view = view1;
                if (activity != null)
                {
                    view = activity.findViewById(i);
                }
            }
            activity = view;
            if (view == null)
            {
                logger.w("Did not find a view with id %s", new Object[] {
                    s
                });
                return view;
            }
        }
        return activity;
    }

    public static View findElementByTag(Activity activity, View view, String s)
    {
        if (s == null)
        {
            logger.w("Received null tag, can't find a view with tag.", new Object[0]);
            activity = null;
        } else
        {
            class .Lambda._cls0
                implements Function
            {

                private final String arg$1;

                public final Object apply(Object obj)
                {
                    return TargetElementFinder.lambda$findElementByTag$0$TargetElementFinder(arg$1, (View)obj);
                }

            .Lambda._cls0(String s)
            {
                arg$1 = s;
            }
            }

            View view1;
            .Lambda._cls0 _lcls0;
            if (view == null)
            {
                view = activity.findViewById(0x1020002);
            }
            _lcls0 = new .Lambda._cls0(s);
            view1 = findElementInRootView(view, _lcls0);
            view = view1;
            if (view1 == null)
            {
                activity = UiUtils.getDialogRootViewIfExists(activity);
                view = view1;
                if (activity != null)
                {
                    view = findElementInRootView(activity, _lcls0);
                }
            }
            activity = view;
            if (view == null)
            {
                logger.w("Did not find a view with tag %s", new Object[] {
                    s
                });
                return view;
            }
        }
        return activity;
    }

    private static View findElementInRootView(View view, Function function)
    {
        ArrayDeque arraydeque = new ArrayDeque();
        arraydeque.add(view);
        do
        {
            if (arraydeque.isEmpty())
            {
                break;
            }
            view = (View)arraydeque.poll();
            if (((Boolean)function.apply(view)).booleanValue())
            {
                return view;
            }
            if (view instanceof ViewGroup)
            {
                int i = 0;
                int j = ((ViewGroup)view).getChildCount();
                while (i < j) 
                {
                    arraydeque.offer(((ViewGroup)view).getChildAt(i));
                    i++;
                }
            }
        } while (true);
        return null;
    }

    static final Boolean lambda$findElementByTag$0$TargetElementFinder(String s, View view)
    {
        return Boolean.valueOf(s.equals(view.getTag(0x7f10001f)));
    }

    public final View findElementByVeId(Activity activity, View view, int i)
    {
        if (optVisualElementViewFinder.isPresent())
        {
            return ((VisualElementViewFinder)optVisualElementViewFinder.get()).findElementByVeId$51662RJ4E9NMIP1FC5O70BQ1CDQ6ITJ9EHSJMJ31DPI74RR9CGNNCQB5ESNLCQB5ESTKIAACC5N68SJFD5I2UTJ9CLRIULJ9CLRJM___0();
        } else
        {
            logger.e("No Visual Element View Finder was bound, can't find View", new Object[0]);
            return null;
        }
    }

}
