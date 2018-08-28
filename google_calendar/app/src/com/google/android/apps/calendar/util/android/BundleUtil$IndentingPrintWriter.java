// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.android;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.io.Writer;

// Referenced classes of package com.google.android.apps.calendar.util.android:
//            BundleUtil

static final class writer
{

    public String indent;
    private final Writer writer;

    final void println(String s)
    {
        try
        {
            writer.append(indent);
            writer.append(" ");
            writer.append(s);
            writer.flush();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            ThrowableExtension.STRATEGY.printStackTrace(s);
        }
    }

    esugaringStrategy(Writer writer1)
    {
        indent = "--";
        writer = writer1;
    }
}
