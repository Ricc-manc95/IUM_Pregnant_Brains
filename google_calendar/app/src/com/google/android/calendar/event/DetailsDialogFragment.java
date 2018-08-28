// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.calendar.common.view.overlay.OverlayFragment;

public abstract class DetailsDialogFragment extends OverlayFragment
{

    public DetailsDialogFragment()
    {
    }

    public abstract int getContentViewId();

    protected final int getDialogTheme()
    {
        return 0x7f140107;
    }

    public abstract com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground getLoadingBackground();

    public String getPrimesLogTag()
    {
        return "";
    }

    public final boolean isFullScreen(Resources resources)
    {
        return resources.getBoolean(0x7f0c0014);
    }

    protected final void onBackgroundChanged(com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground overlaybackground)
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (!TextUtils.isEmpty(getPrimesLogTag()))
        {
            bundle = PerformanceMetricCollectorHolder.instance;
            if (bundle == null)
            {
                throw new NullPointerException(String.valueOf("PrimesLogger not set"));
            }
            ((PerformanceMetricCollector)bundle).recordMemory(String.format("%s.Created", new Object[] {
                getPrimesLogTag()
            }));
        }
    }

    public void onDestroy()
    {
        if (!TextUtils.isEmpty(getPrimesLogTag()))
        {
            PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
            if (performancemetriccollector == null)
            {
                throw new NullPointerException(String.valueOf("PrimesLogger not set"));
            }
            ((PerformanceMetricCollector)performancemetriccollector).recordMemory(String.format("%s.Destroyed", new Object[] {
                getPrimesLogTag()
            }));
        }
        super.onDestroy();
    }

    public final void onViewStateRestored(Bundle bundle)
    {
        super.onViewStateRestored(bundle);
        if (!isFullScreen(requireContext().getResources()) && overlayBackground != getLoadingBackground())
        {
            getLoadingBackground().setToCard(this);
        }
    }

    public void repositionDialog()
    {
        Object obj = super.mView.findViewById(getContentViewId());
        Object obj1 = getOverlayView();
        int i = ((View) (obj)).getHeight();
        int j = ((View)((View) (obj)).getParent()).getHeight();
        obj = new Point();
        overlaySetting.getOverlaySettingWindow().getWindowManager().getDefaultDisplay().getRealSize(((Point) (obj)));
        Rect rect = new Rect(0, 0, ((View) (obj1)).getWidth(), ((View) (obj1)).getHeight());
        obj1 = ((View) (obj1)).getBackground();
        Rect rect1 = new Rect();
        if (obj1 != null && ((Drawable) (obj1)).getPadding(rect1))
        {
            rect.left = rect.left + rect1.left;
            rect.right = rect.right - rect1.right;
            rect.top = rect.top + rect1.top;
            rect.bottom = rect.bottom - rect1.bottom;
        }
        if (j > i)
        {
            rect.bottom = rect.bottom - (j - i);
        }
        int k = ((Point) (obj)).y;
        int l = rect.height();
        boolean flag;
        if (i <= j && (k - l) / 2 > minimumGutter + shadowPaddingBottom)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            getShortBackground().setToCard(this);
            return;
        } else
        {
            getTallBackground().setToCard(this);
            return;
        }
    }
}
