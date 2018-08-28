// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            VEvent

final class this._cls0
    implements Validator
{

    public static final long serialVersionUID = 1L;
    private final VEvent this$0;

    public final void validate()
        throws ValidationException
    {
        PropertyValidator.assertOne("ATTENDEE", properties);
        PropertyValidator.assertOne("DTSTAMP", properties);
        PropertyValidator.assertOne("ORGANIZER", properties);
        PropertyValidator.assertOne("UID", properties);
        PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
        PropertyValidator.assertNone("ATTACH", properties);
        PropertyValidator.assertNone("CATEGORIES", properties);
        PropertyValidator.assertNone("CLASS", properties);
        PropertyValidator.assertNone("CONTACT", properties);
        PropertyValidator.assertNone("CREATED", properties);
        PropertyValidator.assertNone("DESCRIPTION", properties);
        PropertyValidator.assertNone("DTEND", properties);
        PropertyValidator.assertNone("DTSTART", properties);
        PropertyValidator.assertNone("DURATION", properties);
        PropertyValidator.assertNone("EXDATE", properties);
        PropertyValidator.assertNone("EXRULE", properties);
        PropertyValidator.assertNone("GEO", properties);
        PropertyValidator.assertNone("LAST-MODIFIED", properties);
        PropertyValidator.assertNone("LOCATION", properties);
        PropertyValidator.assertNone("PRIORITY", properties);
        PropertyValidator.assertNone("RDATE", properties);
        PropertyValidator.assertNone("RELATED-TO", properties);
        PropertyValidator.assertNone("REQUEST-STATUS", properties);
        PropertyValidator.assertNone("RESOURCES", properties);
        PropertyValidator.assertNone("RRULE", properties);
        PropertyValidator.assertNone("SEQUENCE", properties);
        PropertyValidator.assertNone("STATUS", properties);
        PropertyValidator.assertNone("SUMMARY", properties);
        PropertyValidator.assertNone("TRANSP", properties);
        PropertyValidator.assertNone("URL", properties);
        if (alarms.getComponent("VALARM") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VALARM"
            });
        } else
        {
            return;
        }
    }

    ()
    {
        this$0 = VEvent.this;
        super();
    }
}
