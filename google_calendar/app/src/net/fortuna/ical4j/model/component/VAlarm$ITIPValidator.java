// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            VAlarm

final class this._cls0
    implements Validator
{

    public static final long serialVersionUID = 1L;
    private final VAlarm this$0;

    public final void validate()
        throws ValidationException
    {
        PropertyValidator.assertOne("ACTION", properties);
        PropertyValidator.assertOne("TRIGGER", properties);
        PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
        PropertyValidator.assertOneOrLess("DURATION", properties);
        PropertyValidator.assertOneOrLess("REPEAT", properties);
        PropertyValidator.assertOneOrLess("SUMMARY", properties);
    }

    ()
    {
        this$0 = VAlarm.this;
        super();
    }
}