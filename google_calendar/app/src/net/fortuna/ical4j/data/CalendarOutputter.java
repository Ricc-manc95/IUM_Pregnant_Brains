// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Content;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.model.property.XProperty;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.PropertyValidator;

// Referenced classes of package net.fortuna.ical4j.data:
//            AbstractOutputter, FoldingWriter

public final class CalendarOutputter extends AbstractOutputter
{

    public CalendarOutputter()
    {
    }

    public final void output(Calendar calendar, Writer writer)
        throws IOException, ValidationException
    {
        Method method;
        if (!super.validating)
        {
            break MISSING_BLOCK_LABEL_2992;
        }
        PropertyValidator.assertOne("PRODID", calendar.properties);
        PropertyValidator.assertOne("VERSION", calendar.properties);
        if (!CompatibilityHints.isHintEnabled("ical4j.validation.relaxed") && !Version.VERSION_2_0.equals(calendar.properties.getProperty("VERSION")))
        {
            calendar = String.valueOf(calendar.properties.getProperty("VERSION").getValue());
            if (calendar.length() != 0)
            {
                calendar = "Unsupported Version: ".concat(calendar);
            } else
            {
                calendar = new String("Unsupported Version: ");
            }
            throw new ValidationException(calendar);
        }
        PropertyValidator.assertOneOrLess("CALSCALE", calendar.properties);
        PropertyValidator.assertOneOrLess("METHOD", calendar.properties);
        if (calendar.components.isEmpty())
        {
            throw new ValidationException("Calendar must contain at least one component");
        }
        for (Iterator iterator = calendar.properties.iterator(); iterator.hasNext();)
        {
            Property property = (Property)iterator.next();
            if (!(property instanceof XProperty))
            {
                boolean flag;
                if ("PRODID".equalsIgnoreCase(property.name) || "VERSION".equalsIgnoreCase(property.name) || "CALSCALE".equalsIgnoreCase(property.name) || "METHOD".equalsIgnoreCase(property.name))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    calendar = String.valueOf(property.name);
                    if (calendar.length() != 0)
                    {
                        calendar = "Invalid property: ".concat(calendar);
                    } else
                    {
                        calendar = new String("Invalid property: ");
                    }
                    throw new ValidationException(calendar);
                }
            }
        }

        for (Iterator iterator1 = calendar.components.iterator(); iterator1.hasNext();)
        {
            Component component = (Component)iterator1.next();
            if (!(component instanceof CalendarComponent))
            {
                calendar = String.valueOf(component.name);
                if (calendar.length() != 0)
                {
                    calendar = "Not a valid calendar component: ".concat(calendar);
                } else
                {
                    calendar = new String("Not a valid calendar component: ");
                }
                throw new ValidationException(calendar);
            }
        }

        method = (Method)calendar.properties.getProperty("METHOD");
        if (!Method.PUBLISH.equals(method)) goto _L2; else goto _L1
_L1:
        if (calendar.components.getComponent("VEVENT") != null)
        {
            if (calendar.components.getComponent("VFREEBUSY") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VFREEBUSY"
                });
            }
            if (calendar.components.getComponent("VJOURNAL") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VJOURNAL"
                });
            }
            if (!CompatibilityHints.isHintEnabled("ical4j.validation.relaxed") && calendar.components.getComponent("VTODO") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VTODO"
                });
            }
        } else
        if (calendar.components.getComponent("VFREEBUSY") != null)
        {
            if (calendar.components.getComponent("VTODO") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VTODO"
                });
            }
            if (calendar.components.getComponent("VJOURNAL") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VJOURNAL"
                });
            }
            if (calendar.components.getComponent("VTIMEZONE") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VTIMEZONE"
                });
            }
            if (calendar.components.getComponent("VALARM") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VALARM"
                });
            }
        } else
        if (calendar.components.getComponent("VTODO") != null)
        {
            if (calendar.components.getComponent("VJOURNAL") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VJOURNAL"
                });
            }
        } else
        if (calendar.components.getComponent("VJOURNAL") == null);
_L4:
        if (method != null)
        {
            for (Iterator iterator4 = calendar.components.iterator(); iterator4.hasNext(); ((CalendarComponent)iterator4.next()).validate(method)) { }
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (Method.REQUEST.equals(calendar.properties.getProperty("METHOD")))
        {
            if (calendar.components.getComponent("VEVENT") != null)
            {
                if (calendar.components.getComponent("VFREEBUSY") != null)
                {
                    throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                        "VFREEBUSY"
                    });
                }
                if (calendar.components.getComponent("VJOURNAL") != null)
                {
                    throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                        "VJOURNAL"
                    });
                }
                if (calendar.components.getComponent("VTODO") != null)
                {
                    throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                        "VTODO"
                    });
                }
            } else
            {
                if (calendar.components.getComponent("VFREEBUSY") == null)
                {
                    continue; /* Loop/switch isn't completed */
                }
                if (calendar.components.getComponent("VTODO") != null)
                {
                    throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                        "VTODO"
                    });
                }
                if (calendar.components.getComponent("VJOURNAL") != null)
                {
                    throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                        "VJOURNAL"
                    });
                }
                if (calendar.components.getComponent("VTIMEZONE") != null)
                {
                    throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                        "VTIMEZONE"
                    });
                }
                if (calendar.components.getComponent("VALARM") != null)
                {
                    throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                        "VALARM"
                    });
                }
            }
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_1038;
        if (calendar.components.getComponent("VTODO") == null || calendar.components.getComponent("VJOURNAL") == null) goto _L4; else goto _L3
_L3:
        throw new ValidationException("Component [{0}] is not applicable", new Object[] {
            "VJOURNAL"
        });
        if (!Method.REPLY.equals(calendar.properties.getProperty("METHOD")))
        {
            break; /* Loop/switch isn't completed */
        }
        if (calendar.components.getComponent("VEVENT") != null)
        {
            if (calendar.components.getComponents("VTIMEZONE").size() > 1)
            {
                throw new ValidationException("Component [{0}] must only be specified once", new Object[] {
                    "VTIMEZONE"
                });
            }
            if (calendar.components.getComponent("VALARM") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VALARM"
                });
            }
            if (calendar.components.getComponent("VFREEBUSY") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VFREEBUSY"
                });
            }
            if (calendar.components.getComponent("VJOURNAL") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VJOURNAL"
                });
            }
            if (calendar.components.getComponent("VTODO") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VTODO"
                });
            }
        } else
        {
            if (calendar.components.getComponent("VFREEBUSY") == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (calendar.components.getComponent("VTODO") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VTODO"
                });
            }
            if (calendar.components.getComponent("VJOURNAL") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VJOURNAL"
                });
            }
            if (calendar.components.getComponent("VTIMEZONE") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VTIMEZONE"
                });
            }
            if (calendar.components.getComponent("VALARM") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VALARM"
                });
            }
        }
        continue; /* Loop/switch isn't completed */
        if (calendar.components.getComponent("VTODO") == null) goto _L4; else goto _L5
_L5:
        if (calendar.components.getComponents("VTIMEZONE").size() > 1)
        {
            throw new ValidationException("Component [{0}] must only be specified once", new Object[] {
                "VTIMEZONE"
            });
        }
        if (calendar.components.getComponent("VALARM") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VALARM"
            });
        }
        if (calendar.components.getComponent("VJOURNAL") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VJOURNAL"
            });
        }
        if (true) goto _L4; else goto _L6
_L6:
        if (!Method.ADD.equals(calendar.properties.getProperty("METHOD")))
        {
            break; /* Loop/switch isn't completed */
        }
        if (calendar.components.getComponent("VEVENT") != null)
        {
            if (calendar.components.getComponent("VFREEBUSY") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VFREEBUSY"
                });
            }
            if (calendar.components.getComponent("VJOURNAL") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VJOURNAL"
                });
            }
            if (calendar.components.getComponent("VTODO") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VTODO"
                });
            }
        } else
        {
            if (calendar.components.getComponent("VTODO") == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (calendar.components.getComponent("VFREEBUSY") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VFREEBUSY"
                });
            }
            if (calendar.components.getComponent("VJOURNAL") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VJOURNAL"
                });
            }
        }
        continue; /* Loop/switch isn't completed */
        if (calendar.components.getComponent("VJOURNAL") == null) goto _L4; else goto _L7
_L7:
        if (calendar.components.getComponents("VTIMEZONE").size() > 1)
        {
            throw new ValidationException("Component [{0}] must only be specified once", new Object[] {
                "VTIMEZONE"
            });
        }
        if (calendar.components.getComponent("VFREEBUSY") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VFREEBUSY"
            });
        }
        if (true) goto _L4; else goto _L8
_L8:
        if (!Method.CANCEL.equals(calendar.properties.getProperty("METHOD")))
        {
            break; /* Loop/switch isn't completed */
        }
        if (calendar.components.getComponent("VEVENT") != null)
        {
            if (calendar.components.getComponent("VALARM") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VALARM"
                });
            }
            if (calendar.components.getComponent("VFREEBUSY") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VFREEBUSY"
                });
            }
            if (calendar.components.getComponent("VJOURNAL") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VJOURNAL"
                });
            }
            if (calendar.components.getComponent("VTODO") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VTODO"
                });
            }
        } else
        {
            if (calendar.components.getComponent("VTODO") == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (calendar.components.getComponents("VTIMEZONE").size() > 1)
            {
                throw new ValidationException("Component [{0}] must only be specified once", new Object[] {
                    "VTIMEZONE"
                });
            }
            if (calendar.components.getComponent("VALARM") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VALARM"
                });
            }
            if (calendar.components.getComponent("VFREEBUSY") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VFREEBUSY"
                });
            }
            if (calendar.components.getComponent("VJOURNAL") != null)
            {
                throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                    "VJOURNAL"
                });
            }
        }
        continue; /* Loop/switch isn't completed */
        if (calendar.components.getComponent("VJOURNAL") == null) goto _L4; else goto _L9
_L9:
        if (calendar.components.getComponent("VALARM") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VALARM"
            });
        }
        if (calendar.components.getComponent("VFREEBUSY") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VFREEBUSY"
            });
        }
        if (true) goto _L4; else goto _L10
_L10:
        if (!Method.REFRESH.equals(calendar.properties.getProperty("METHOD")))
        {
            break; /* Loop/switch isn't completed */
        }
        if (calendar.components.getComponent("VEVENT") == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (calendar.components.getComponent("VALARM") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VALARM"
            });
        }
        if (calendar.components.getComponent("VFREEBUSY") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VFREEBUSY"
            });
        }
        if (calendar.components.getComponent("VJOURNAL") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VJOURNAL"
            });
        }
        if (calendar.components.getComponent("VTODO") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VTODO"
            });
        }
        continue; /* Loop/switch isn't completed */
        if (calendar.components.getComponent("VTODO") == null) goto _L4; else goto _L11
_L11:
        if (calendar.components.getComponent("VALARM") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VALARM"
            });
        }
        if (calendar.components.getComponent("VFREEBUSY") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VFREEBUSY"
            });
        }
        if (calendar.components.getComponent("VJOURNAL") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VJOURNAL"
            });
        }
        if (calendar.components.getComponent("VTIMEZONE") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VTIMEZONE"
            });
        }
        if (true) goto _L4; else goto _L12
_L12:
        if (!Method.COUNTER.equals(calendar.properties.getProperty("METHOD")))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (calendar.components.getComponent("VEVENT") == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (calendar.components.getComponent("VFREEBUSY") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VFREEBUSY"
            });
        }
        if (calendar.components.getComponent("VJOURNAL") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VJOURNAL"
            });
        }
        if (calendar.components.getComponent("VTODO") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VTODO"
            });
        }
        continue; /* Loop/switch isn't completed */
        if (calendar.components.getComponent("VTODO") == null) goto _L4; else goto _L13
_L13:
        if (calendar.components.getComponents("VTIMEZONE").size() > 1)
        {
            throw new ValidationException("Component [{0}] must only be specified once", new Object[] {
                "VTIMEZONE"
            });
        }
        if (calendar.components.getComponent("VFREEBUSY") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VFREEBUSY"
            });
        }
        if (calendar.components.getComponent("VJOURNAL") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VJOURNAL"
            });
        }
        continue; /* Loop/switch isn't completed */
        if (!Method.DECLINE_COUNTER.equals(calendar.properties.getProperty("METHOD"))) goto _L4; else goto _L14
_L14:
        if (calendar.components.getComponent("VEVENT") == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (calendar.components.getComponent("VFREEBUSY") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VFREEBUSY"
            });
        }
        if (calendar.components.getComponent("VJOURNAL") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VJOURNAL"
            });
        }
        if (calendar.components.getComponent("VTODO") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VTODO"
            });
        }
        if (calendar.components.getComponent("VTIMEZONE") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VTIMEZONE"
            });
        }
        if (calendar.components.getComponent("VALARM") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VALARM"
            });
        }
        continue; /* Loop/switch isn't completed */
        if (calendar.components.getComponent("VTODO") == null) goto _L4; else goto _L15
_L15:
        if (calendar.components.getComponent("VALARM") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VALARM"
            });
        }
        if (calendar.components.getComponent("VFREEBUSY") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VFREEBUSY"
            });
        }
        if (calendar.components.getComponent("VJOURNAL") != null)
        {
            throw new ValidationException("Component [{0}] is not applicable", new Object[] {
                "VJOURNAL"
            });
        }
        if (true) goto _L4; else goto _L16
_L16:
        for (Iterator iterator2 = calendar.properties.iterator(); iterator2.hasNext(); ((Property)iterator2.next()).validate()) { }
        for (Iterator iterator3 = calendar.components.iterator(); iterator3.hasNext(); ((Component)iterator3.next()).validate(true)) { }
        writer = new FoldingWriter(writer, foldLength);
        writer.write(calendar.toString());
        writer.close();
        return;
        calendar;
        writer.close();
        throw calendar;
    }
}
