// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.content.Context;
import android.content.res.Resources;
import android.os.Trace;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarFutures;
import com.google.android.apps.calendar.util.concurrent.Cancelable;
import com.google.android.apps.calendar.util.concurrent.CancelableHolder;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timeline.chip.image.AutoValue_ImageResolverContext;
import com.google.android.calendar.timeline.chip.image.BackgroundImageViewModel;
import com.google.android.calendar.timeline.chip.image.ImageResolver;
import com.google.android.calendar.timeline.chip.image.ImageViewModel;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip, ChipViewModel

final class arg._cls1
    implements Consumer
{

    private final Chip arg$1;

    public final void accept(Object obj)
    {
        Chip chip = arg$1;
        BackgroundImageViewModel backgroundimageviewmodel = (BackgroundImageViewModel)obj;
        obj = chip.getContext().getResources();
        AutoValue_ImageResolverContext autovalue_imageresolvercontext = new AutoValue_ImageResolverContext(chip.getContext(), ((Resources) (obj)).getDimensionPixelSize(0x7f0e009d), ((Resources) (obj)).getDimensionPixelSize(0x7f0e009c));
        ImageViewModel imageviewmodel = backgroundimageviewmodel.imageViewModel();
        Cancelable cancelable;
        if (chip.viewModel == null)
        {
            obj = "[Unconfigured chip]";
        } else
        {
            String s;
            if (chip.viewModel.getPrimaryText().isEmpty())
            {
                s = "";
            } else
            {
                s = (String)chip.viewModel.getPrimaryText().get(0);
            }
            obj = s;
            if (s.length() >= 30)
            {
                obj = s.substring(0, 30);
            }
        }
        obj = String.valueOf(obj);
        if (((String) (obj)).length() != 0)
        {
            obj = "setEventImage - ".concat(((String) (obj)));
        } else
        {
            obj = new String("setEventImage - ");
        }
        Trace.beginSection(((String) (obj)));
        chip.requestBackgroundImageStartTimeMillis = System.currentTimeMillis();
        chip.requestBackgroundImageWasImmediate = true;
        obj = chip.backgroundImageCancelableHolder;
        cancelable = CalendarFutures.asyncGet(imageviewmodel.imageResolver().resolveImage(autovalue_imageresolvercontext), new <init>(chip, backgroundimageviewmodel), new com.google.android.apps.calendar.util.concurrent.r..Lambda._cls0(CalendarExecutor.MAIN));
        ((Cancelable)((CancelableHolder) (obj)).previousCancelableRef.getAndSet(cancelable)).cancel(true);
        chip.requestBackgroundImageWasImmediate = false;
        Trace.endSection();
        return;
        obj;
        Trace.endSection();
        throw obj;
    }

    le(Chip chip)
    {
        arg$1 = chip;
    }
}
