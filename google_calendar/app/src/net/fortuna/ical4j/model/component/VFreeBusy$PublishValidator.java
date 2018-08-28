// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            VFreeBusy

final class this._cls0
    implements Validator
{

    public static final long serialVersionUID = 1L;
    private final VFreeBusy this$0;

    public final void validate()
        throws ValidationException
    {
        PropertyValidator.assertOneOrMore("FREEBUSY", properties);
        PropertyValidator.assertOne("DTSTAMP", properties);
        PropertyValidator.assertOne("DTSTART", properties);
        PropertyValidator.assertOne("DTEND", properties);
        PropertyValidator.assertOne("ORGANIZER", properties);
        PropertyValidator.assertOne("UID", properties);
        PropertyValidator.assertOneOrLess("URL", properties);
        PropertyValidator.assertNone("ATTENDEE", properties);
        PropertyValidator.assertNone("DURATION", properties);
        PropertyValidator.assertNone("REQUEST-STATUS", properties);
    }

    ()
    {
        this$0 = VFreeBusy.this;
        super();
    }
}
