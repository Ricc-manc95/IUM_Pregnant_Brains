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
//            VToDo, VAlarm, CalendarComponent

final class this._cls0
    implements Validator
{

    public static final long serialVersionUID = 1L;
    private final VToDo this$0;

    public final void validate()
        throws ValidationException
    {
        PropertyValidator.assertOneOrMore("ATTENDEE", properties);
        PropertyValidator.assertOne("DTSTAMP", properties);
        PropertyValidator.assertOne("ORGANIZER", properties);
        PropertyValidator.assertOne("PRIORITY", properties);
        PropertyValidator.assertOne("SUMMARY", properties);
        PropertyValidator.assertOne("UID", properties);
        PropertyValidator.assertOneOrLess("CATEGORIES", properties);
        PropertyValidator.assertOneOrLess("CLASS", properties);
        PropertyValidator.assertOneOrLess("CREATED", properties);
        PropertyValidator.assertOneOrLess("DESCRIPTION", properties);
        PropertyValidator.assertOneOrLess("DTSTART", properties);
        PropertyValidator.assertOneOrLess("DUE", properties);
        PropertyValidator.assertOneOrLess("DURATION", properties);
        PropertyValidator.assertOneOrLess("GEO", properties);
        PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
        PropertyValidator.assertOneOrLess("LOCATION", properties);
        PropertyValidator.assertOneOrLess("PERCENT-COMPLETE", properties);
        PropertyValidator.assertOneOrLess("RECURRENCE-ID", properties);
        PropertyValidator.assertOneOrLess("RESOURCES", properties);
        PropertyValidator.assertOneOrLess("RRULE", properties);
        PropertyValidator.assertOneOrLess("SEQUENCE", properties);
        PropertyValidator.assertOneOrLess("STATUS", properties);
        PropertyValidator.assertOneOrLess("URL", properties);
        for (Iterator iterator = alarms.iterator(); iterator.hasNext(); ((VAlarm)iterator.next()).validate(Method.COUNTER)) { }
    }

    ()
    {
        this$0 = VToDo.this;
        super();
    }
}
