// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            FormattedText

public final class BarcodeAction extends GenericJson
{

    public String barcodeText;
    public List barcodes;
    public FormattedText caption;
    public FormattedText expandedTitle;
    public String format;
    public FormattedText title;

    public BarcodeAction()
    {
    }

    public final volatile GenericJson clone()
    {
        return (BarcodeAction)clone();
    }

    public final volatile GenericData clone()
    {
        return (BarcodeAction)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (BarcodeAction)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (BarcodeAction)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (BarcodeAction)super.set(s, obj);
    }
}
