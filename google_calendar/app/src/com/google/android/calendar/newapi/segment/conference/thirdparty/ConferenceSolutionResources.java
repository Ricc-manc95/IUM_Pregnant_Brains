// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference.thirdparty;

import android.net.Uri;
import android.util.DisplayMetrics;
import com.google.android.apps.calendar.graphics.net.AutoValue_BitmapDecodeOptions;
import com.google.android.apps.calendar.graphics.net.AutoValue_NetworkBitmapId;
import com.google.android.apps.calendar.graphics.net.NetworkBitmaps;
import com.google.android.apps.calendar.util.collect.ReferenceCache;
import com.google.android.apps.calendar.util.concurrent.ConcurrentFunctions;
import com.google.android.apps.calendar.util.concurrent.FutureReferenceCache;
import com.google.android.apps.calendar.util.dimension.Dimensions;
import com.google.android.apps.calendar.util.dimension.ExactDimension;
import com.google.android.calendar.api.event.conference.ConferenceSolution;
import com.google.common.base.Platform;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.Reference;
import java.util.Map;

public final class ConferenceSolutionResources
{

    private static final ExactDimension MAX_ICON_SIZE = Dimensions.dp(24F);

    public static ListenableFuture iconMax24(DisplayMetrics displaymetrics, ConferenceSolution conferencesolution)
    {
        Object obj;
        byte byte0;
        obj = conferencesolution.getKey().getType();
        byte0 = -1;
        ((String) (obj)).hashCode();
        JVM INSTR lookupswitch 4: default 56
    //                   -972730403: 145
    //                   92659296: 100
    //                   774960958: 115
    //                   1601152418: 130;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break MISSING_BLOCK_LABEL_145;
_L6:
        switch (byte0)
        {
        default:
            displaymetrics = com.google.android.apps.calendar.image.Images..Lambda._cls0.$instance;
            com.google.android.apps.calendar.util.concurrent.FutureReferenceCache..Lambda._cls0 _lcls0;
            if (displaymetrics == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(displaymetrics);
            }

        case 0: // '\0'
            if (Platform.stringIsNullOrEmpty(conferencesolution.getIconUri()))
            {
                displaymetrics = com.google.android.apps.calendar.image.Images..Lambda._cls0.$instance;
                if (displaymetrics == null)
                {
                    return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                } else
                {
                    return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(displaymetrics);
                }
            }
            conferencesolution = new AutoValue_NetworkBitmapId(Uri.parse(conferencesolution.getIconUri()), new AutoValue_BitmapDecodeOptions(MAX_ICON_SIZE.asSize(displaymetrics), MAX_ICON_SIZE.asSize(displaymetrics)));
            obj = NetworkBitmaps.memoryCache;
            _lcls0 = new com.google.android.apps.calendar.util.concurrent.FutureReferenceCache..Lambda._cls0(new com.google.android.apps.calendar.graphics.net.NetworkBitmaps..Lambda._cls0(conferencesolution));
            displaymetrics = (Reference)((FutureReferenceCache) (obj)).valueReferenceCache.keyToValueReferenceMap.get(conferencesolution);
            if (displaymetrics == null)
            {
                displaymetrics = null;
            } else
            {
                displaymetrics = ((DisplayMetrics) (displaymetrics.get()));
            }
            if (displaymetrics != null)
            {
                if (displaymetrics == null)
                {
                    displaymetrics = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                } else
                {
                    displaymetrics = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(displaymetrics);
                }
            } else
            {
                displaymetrics = ConcurrentFunctions.computeIfNotPending(((FutureReferenceCache) (obj)).pendingFutureMap, conferencesolution, new com.google.android.apps.calendar.util.concurrent.FutureReferenceCache..Lambda._cls1(((FutureReferenceCache) (obj)), _lcls0, conferencesolution));
            }
            if (displaymetrics instanceof FluentFuture)
            {
                displaymetrics = (FluentFuture)displaymetrics;
            } else
            {
                displaymetrics = new ForwardingFluentFuture(displaymetrics);
            }
            displaymetrics = (FluentFuture)AbstractCatchingFuture.create((FluentFuture)AbstractTransformFuture.create(displaymetrics, com.google.android.apps.calendar.util.concurrent.FutureOptionals..Lambda._cls4.$instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), java/lang/Throwable, com.google.android.apps.calendar.util.concurrent.FutureOptionals..Lambda._cls5.$instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            conferencesolution = com.google.android.apps.calendar.image.Images..Lambda._cls3.$instance;
            obj = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
            displaymetrics = AbstractTransformFuture.create(displaymetrics, new com.google.android.apps.calendar.util.concurrent.FutureOptionals..Lambda._cls3(conferencesolution), ((java.util.concurrent.Executor) (obj)));
            conferencesolution = com.google.android.apps.calendar.image.Images..Lambda._cls4.$instance;
            obj = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
            return AbstractTransformFuture.create(displaymetrics, new com.google.android.apps.calendar.util.concurrent.FutureOptionals..Lambda._cls0(conferencesolution), ((java.util.concurrent.Executor) (obj)));

        case 1: // '\001'
            displaymetrics = new com.google.android.apps.calendar.image.Images..Lambda._cls2(0x7f0201be);
            if (displaymetrics == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(displaymetrics);
            }

        case 2: // '\002'
        case 3: // '\003'
            displaymetrics = new com.google.android.apps.calendar.image.Images..Lambda._cls2(0x7f0201bd);
            break;
        }
        break MISSING_BLOCK_LABEL_461;
_L3:
        if (((String) (obj)).equals("addOn"))
        {
            byte0 = 0;
        }
          goto _L6
_L4:
        if (((String) (obj)).equals("hangoutsMeet"))
        {
            byte0 = 1;
        }
          goto _L6
_L5:
        if (((String) (obj)).equals("eventHangout"))
        {
            byte0 = 2;
        }
          goto _L6
        if (((String) (obj)).equals("eventNamedHangout"))
        {
            byte0 = 3;
        }
          goto _L6
        if (displaymetrics == null)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(displaymetrics);
        }
    }

}
