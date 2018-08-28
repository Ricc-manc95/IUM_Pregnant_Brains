// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DurationFieldType;

// Referenced classes of package org.joda.time.field:
//            BaseDurationField, ImpreciseDateTimeField

final class this._cls0 extends BaseDurationField
{

    public static final long serialVersionUID = 0xfd2be8b98605f64aL;
    private final ImpreciseDateTimeField this$0;

    public final long add(long l, int i)
    {
        return DateTimeField.this.add(l, i);
    }

    public final long add(long l, long l1)
    {
        return DateTimeField.this.add(l, l1);
    }

    public final long getUnitMillis()
    {
        return iUnitMillis;
    }

    public final boolean isPrecise()
    {
        return false;
    }

    (DurationFieldType durationfieldtype)
    {
        this$0 = ImpreciseDateTimeField.this;
        super(durationfieldtype);
    }
}
