// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.image;

import android.widget.ImageView;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.image:
//            Image

public final class arg._cls1
    implements Consumer
{

    private final ImageView arg$1;

    public final void accept(Object obj)
    {
        ImageView imageview = arg$1;
        imageview.setImageDrawable(((Image)obj).createDrawable(imageview.getResources()));
    }

    public (ImageView imageview)
    {
        arg$1 = imageview;
    }
}
