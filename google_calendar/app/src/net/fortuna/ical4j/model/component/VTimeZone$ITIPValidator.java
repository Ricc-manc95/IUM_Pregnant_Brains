// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import java.util.Iterator;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            VTimeZone, Observance

final class this._cls0
    implements Validator
{

    public static final long serialVersionUID = 1L;
    private final VTimeZone this$0;

    public final void validate()
        throws ValidationException
    {
        Observance observance;
        for (Iterator iterator = observances.iterator(); iterator.hasNext(); PropertyValidator.assertOneOrLess("TZNAME", ((Component) (observance)).properties))
        {
            observance = (Observance)iterator.next();
            PropertyValidator.assertOne("DTSTART", ((Component) (observance)).properties);
            PropertyValidator.assertOne("TZOFFSETFROM", ((Component) (observance)).properties);
            PropertyValidator.assertOne("TZOFFSETTO", ((Component) (observance)).properties);
        }

    }

    ()
    {
        this$0 = VTimeZone.this;
        super();
    }
}
