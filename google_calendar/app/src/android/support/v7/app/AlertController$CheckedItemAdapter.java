// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.widget.ArrayAdapter;

final class  extends ArrayAdapter
{

    public final long getItemId(int i)
    {
        return (long)i;
    }

    public final boolean hasStableIds()
    {
        return true;
    }

    public (Context context, int i, int j, CharSequence acharsequence[])
    {
        super(context, i, 0x1020014, acharsequence);
    }
}
