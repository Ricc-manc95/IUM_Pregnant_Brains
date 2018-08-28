// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            VJournal

final class this._cls0
    implements Validator
{

    public static final long serialVersionUID = 1L;
    private final VJournal this$0;

    public final void validate()
        throws ValidationException
    {
        PropertyValidator.assertOne("DESCRIPTION", properties);
        PropertyValidator.assertOne("DTSTAMP", properties);
        PropertyValidator.assertOne("DTSTART", properties);
        PropertyValidator.assertOne("ORGANIZER", properties);
        PropertyValidator.assertOne("SEQUENCE", properties);
        PropertyValidator.assertOne("UID", properties);
        PropertyValidator.assertOneOrLess("CATEGORIES", properties);
        PropertyValidator.assertOneOrLess("CLASS", properties);
        PropertyValidator.assertOneOrLess("CREATED", properties);
        PropertyValidator.assertOneOrLess("LAST-MODIFIED", properties);
        PropertyValidator.assertOneOrLess("STATUS", properties);
        PropertyValidator.assertOneOrLess("SUMMARY", properties);
        PropertyValidator.assertOneOrLess("URL", properties);
        PropertyValidator.assertNone("ATTENDEE", properties);
        PropertyValidator.assertNone("RECURRENCE-ID", properties);
    }

    ()
    {
        this$0 = VJournal.this;
        super();
    }
}
