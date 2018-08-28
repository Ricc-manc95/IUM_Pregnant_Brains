// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.ooo;

import com.google.android.calendar.viewedit.segment.edit.EditSegment;

// Referenced classes of package com.google.android.calendar.newapi.segment.ooo:
//            OooEditSegment

final class arg._cls1
    implements com.google.android.calendar.utils.text.ChangedListener
{

    private final OooEditSegment arg$1;

    public final void onTextChanged(String s)
    {
        ((istener)((EditSegment) (arg$1)).mListener).nAutoDeclineMessageTextChanged(s);
    }

    istener(OooEditSegment oooeditsegment)
    {
        arg$1 = oooeditsegment;
    }
}
