// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.settings;

import android.content.Context;
import android.content.res.AssetManager;
import com.google.android.gms.common.util.IOUtils;
import com.google.protobuf.GeneratedMessageLite;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class DirectoryLoader
{

    public static com.google.calendar.v2.libs.proto.DirectoryProto.Directory directoryForLocale(Context context, String s)
    {
        try
        {
            s = String.format(null, "directory/directorydata_%s.pb", new Object[] {
                s
            });
            context = context.getAssets().open(s);
            s = new ByteArrayOutputStream();
            IOUtils.copyStream(context, s, true, 1024);
            context = s.toByteArray();
            context = (com.google.calendar.v2.libs.proto.DirectoryProto.Directory)GeneratedMessageLite.parseFrom(com.google.calendar.v2.libs.proto.DirectoryProto.Directory.DEFAULT_INSTANCE, context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        return context;
    }
}
