// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.format:
//            DateTimeParserBucket

final class sCount
{

    public final Integer iOffset;
    public final sCount iSavedFields[];
    public final int iSavedFieldsCount;
    public final DateTimeZone iZone;
    public final DateTimeParserBucket this$0;

    ()
    {
        this$0 = DateTimeParserBucket.this;
        super();
        iZone = DateTimeParserBucket.this.iZone;
        iOffset = DateTimeParserBucket.this.iOffset;
        iSavedFields = DateTimeParserBucket.this.iSavedFields;
        iSavedFieldsCount = DateTimeParserBucket.this.iSavedFieldsCount;
    }
}
