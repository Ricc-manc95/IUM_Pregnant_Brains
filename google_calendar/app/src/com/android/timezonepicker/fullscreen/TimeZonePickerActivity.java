// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker.fullscreen;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.android.timezonepicker.TimeZoneParams;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;

// Referenced classes of package com.android.timezonepicker.fullscreen:
//            TimeZonePickerInitializer

public class TimeZonePickerActivity extends AppCompatActivity
    implements TimeZonePickerInitializer.Listener
{

    private int theme$9HHMUR9FC5N68SJFD5I2UT39DLINKRRECLO6IORBCLP2UL39DLILKRRECL86IORBCLP5AT39DHPI8L38CLMMAEO_0;

    public TimeZonePickerActivity()
    {
    }

    public final void onCancel()
    {
        setResult(0);
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(findViewById(0x7f100389).getWindowToken(), 0);
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = getIntent();
        View view;
        Object obj;
        int i;
        int j;
        long l;
        if (bundle.hasExtra("toolbar_color"))
        {
            theme$9HHMUR9FC5N68SJFD5I2UT39DLINKRRECLO6IORBCLP2UL39DLILKRRECL86IORBCLP5AT39DHPI8L38CLMMAEO_0 = android.support.v4.content.ModernAsyncTask.Status.LIGHT$9HHMUR9FC5N68SJFD5I2UT39DLINKRRECLO6IORBCLP2UL39DLILKRRECL86IORBCLP5AT39DHPI8L38CLMMAEO_0;
            setTheme(0x7f140305);
        } else
        {
            theme$9HHMUR9FC5N68SJFD5I2UT39DLINKRRECLO6IORBCLP2UL39DLILKRRECL86IORBCLP5AT39DHPI8L38CLMMAEO_0 = android.support.v4.content.ModernAsyncTask.Status.DARK$9HHMUR9FC5N68SJFD5I2UT39DLINKRRECLO6IORBCLP2UL39DLILKRRECL86IORBCLP5AT39DHPI8L38CLMMAEO_0;
            setTheme(0x7f140304);
        }
        setContentView(0x7f05017c);
        i = bundle.getIntExtra("toolbar_color", getResources().getColor(0x7f0d0325));
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        l = bundle.getLongExtra("time_to_display", l);
        bundle = bundle.getStringArrayListExtra("recent_time_zone_ids");
        obj = getWindow();
        view = findViewById(0x1020002);
        j = theme$9HHMUR9FC5N68SJFD5I2UT39DLINKRRECLO6IORBCLP2UL39DLILKRRECL86IORBCLP5AT39DHPI8L38CLMMAEO_0;
        obj = StatusbarAnimatorCompat.createInstance(((android.view.Window) (obj)));
        ((StatusbarAnimatorCompat) (obj)).setStatusbarColor(ColorUtils.blend(i, 0x33000000));
        ((StatusbarAnimatorCompat) (obj)).setLightStatusbar(false);
        new TimeZonePickerInitializer(this, view, j, i, l, bundle);
    }

    public final void onTimeZoneSelected(TimeZoneParams timezoneparams)
    {
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(findViewById(0x7f100389).getWindowToken(), 0);
        Intent intent = new Intent();
        intent.putExtra("time_zone_id", timezoneparams.getId());
        intent.putExtra("time_zone_display_name", timezoneparams.getDisplayName());
        setResult(-1, intent);
        finish();
    }
}
