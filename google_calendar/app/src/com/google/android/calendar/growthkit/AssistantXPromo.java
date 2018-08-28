// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.growthkit;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.FluentFuture;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

final class AssistantXPromo
{

    private static final Uri URI = (new android.net.Uri.Builder()).scheme("content").authority("com.google.android.googlequicksearchbox.GsaPublicContentProvider").appendPath("publicvalue").appendPath("opa_calendar_xpromo_enabled").build();

    private static boolean getValue(Context context)
    {
        Object obj;
        int i;
        boolean flag;
        boolean flag1;
        try
        {
            obj = context.getContentResolver().query(URI, null, null, null, null);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return false;
        }
        if (obj != null) goto _L2; else goto _L1
_L1:
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        ((Cursor) (obj)).close();
        flag = false;
_L4:
        return flag;
_L2:
        i = ((Cursor) (obj)).getCount();
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_56;
        }
        ((Cursor) (obj)).close();
        return false;
        i = ((Cursor) (obj)).getColumnIndex("value");
        if (i >= 0)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        ((Cursor) (obj)).close();
        return false;
        ((Cursor) (obj)).moveToFirst();
        context = ((Cursor) (obj)).getString(i);
        if (context != null)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_112;
        }
        ((Cursor) (obj)).close();
        return false;
        flag1 = Boolean.parseBoolean(context);
        flag = flag1;
        if (obj == null) goto _L4; else goto _L3
_L3:
        ((Cursor) (obj)).close();
        return flag1;
        Throwable throwable;
        throwable;
        throw throwable;
        context;
_L11:
        if (obj == null) goto _L6; else goto _L5
_L5:
        if (throwable == null) goto _L8; else goto _L7
_L7:
        ((Cursor) (obj)).close();
_L6:
        throw context;
        obj;
        ThrowableExtension.STRATEGY.addSuppressed(throwable, ((Throwable) (obj)));
        continue; /* Loop/switch isn't completed */
_L8:
        ((Cursor) (obj)).close();
        if (true) goto _L6; else goto _L9
_L9:
        context;
        throwable = null;
        if (true) goto _L11; else goto _L10
_L10:
    }

    static FluentFuture isEnabled(Context context)
    {
        class .Lambda._cls0
            implements Callable
        {

            private final Context arg$1;

            public final Object call()
            {
                return AssistantXPromo.lambda$isEnabled$0$AssistantXPromo(arg$1);
            }

            .Lambda._cls0(Context context)
            {
                arg$1 = context;
            }
        }

        return (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls0(context));
    }

    static final Boolean lambda$isEnabled$0$AssistantXPromo(Context context)
        throws Exception
    {
        return Boolean.valueOf(getValue(context));
    }

}
