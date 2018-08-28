// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import java.util.Iterator;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            VEvent, VAlarm, CalendarComponent

final class this._cls0
    implements Validator
{

    public static final long serialVersionUID = 1L;
    private final VEvent this$0;

    public final void validate()
        throws ValidationException
    {
        PropertyValidator.assertOne("DTSTAMP", properties);
        PropertyValidator.assertOne("DTSTART", properties);
        PropertyValidator.assertOne("ORGANIZER", properties);
        PropertyValidator.assertOne("SEQUENCE", properties);
        PropertyValidator.assertOne("SUMMARY", properties);
        PropertyValidator.assertOne("UID", properties);
        PropertyValidator.assertOneOrLess("CATEGORIES", properties);
        PropertyValidator.assertOneOrLess("CLASS", properties);
        PropertyValidator.assertOneOrLess("CREATED", properties);
        PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
        PropertyValidator.assertOneOrLess("DTEND", properties);
        PropertyValidator.assertOneOrLess("DURATION", properties);
        PropertyValidator.assertOneOrLess("GEO", properties);
        PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
        PropertyValidator.assertOneOrLess("LOCATION", properties);
        PropertyValidator.assertOneOrLess("PRIORITY", properties);
        PropertyValidator.assertOneOrLess("RESOURCES", properties);
        PropertyValidator.assertOneOrLess("STATUS", properties);
        PropertyValidator.assertOneOrLess("TRANSP", properties);
        PropertyValidator.assertOneOrLess("URL", properties);
        PropertyValidator.assertNone("RECURRENCE-ID", properties);
        PropertyValidator.assertNone("REQUEST-STATUS", properties);
        for (Iterator iterator = alarms.iterator(); iterator.hasNext(); ((VAlarm)iterator.next()).validate(Method.ADD)) { }
    }

    ()
    {
        this$0 = VEvent.this;
        super();
    }
}
