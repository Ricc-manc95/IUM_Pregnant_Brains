// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.android.calendar.utils.viewpager.LayoutDirectionAwareViewPager;

// Referenced classes of package com.google.android.calendar.launch.oobe:
//            WhatsNewFullScreen, OobePagesAdapter

final class arg._cls1
    implements Runnable
{

    private final WhatsNewFullScreen arg$1;

    public final void run()
    {
        WhatsNewFullScreen whatsnewfullscreen = arg$1;
        whatsnewfullscreen.alphaFade(whatsnewfullscreen.basePage.findViewById(0x7f100158), 0.0F);
        OobePagesAdapter oobepagesadapter = whatsnewfullscreen.adapter;
        boolean flag;
        if (OobePagesAdapter.SPLASH_LOGO == whatsnewfullscreen.adapter.topResources[0])
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            whatsnewfullscreen.alphaFade(whatsnewfullscreen.basePage.findViewById(0x7f1000d1), 0.0F);
            android.view.View view = whatsnewfullscreen.basePage.findViewById(0x7f1002ca);
            if (view != null)
            {
                whatsnewfullscreen.alphaFade(view, 0.0F);
            }
        }
        if (whatsnewfullscreen.doneButton != null)
        {
            whatsnewfullscreen.doneButton.setAlpha(0.0F);
            whatsnewfullscreen.doneButton.setEnabled(false);
        }
        whatsnewfullscreen.paginationView.setAlpha(0.0F);
        whatsnewfullscreen.alphaFade(whatsnewfullscreen.paginationView, 1.0F);
        whatsnewfullscreen.alphaFade(whatsnewfullscreen.rightArrow, 1.0F);
        whatsnewfullscreen.viewPager.setAlpha(0.0F);
        whatsnewfullscreen.viewPager.setVisibility(0);
        whatsnewfullscreen.alphaFade(whatsnewfullscreen.viewPager, 1.0F).setListener(new ager.setVisibility(whatsnewfullscreen, flag));
        if (!RtlUtils.isLayoutDirectionRtl(whatsnewfullscreen))
        {
            whatsnewfullscreen.logPage(0);
        }
    }

    ager(WhatsNewFullScreen whatsnewfullscreen)
    {
        arg$1 = whatsnewfullscreen;
    }
}
