// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.net.URISyntaxException;
import java.util.Map;
import net.fortuna.ical4j.model.parameter.XParameter;
import net.fortuna.ical4j.util.CompatibilityHints;

// Referenced classes of package net.fortuna.ical4j.model:
//            AbstractContentFactory, ParameterFactory, Parameter

public class ParameterFactoryImpl extends AbstractContentFactory
    implements ParameterFactory
{

    public static ParameterFactoryImpl instance = new ParameterFactoryImpl();
    public static final long serialVersionUID = 0xc802d931fe03e8b3L;

    protected ParameterFactoryImpl()
    {
        Object obj = new AbbrevFactory();
        super.defaultFactories.put("ABBREV", obj);
        obj = new AltRepFactory();
        super.defaultFactories.put("ALTREP", obj);
        obj = new CnFactory();
        super.defaultFactories.put("CN", obj);
        obj = new CuTypeFactory();
        super.defaultFactories.put("CUTYPE", obj);
        obj = new DelegatedFromFactory();
        super.defaultFactories.put("DELEGATED-FROM", obj);
        obj = new DelegatedToFactory();
        super.defaultFactories.put("DELEGATED-TO", obj);
        obj = new DirFactory();
        super.defaultFactories.put("DIR", obj);
        obj = new EncodingFactory();
        super.defaultFactories.put("ENCODING", obj);
        obj = new FmtTypeFactory();
        super.defaultFactories.put("FMTTYPE", obj);
        obj = new FbTypeFactory();
        super.defaultFactories.put("FBTYPE", obj);
        obj = new LanguageFactory();
        super.defaultFactories.put("LANGUAGE", obj);
        obj = new MemberFactory();
        super.defaultFactories.put("MEMBER", obj);
        obj = new PartStatFactory();
        super.defaultFactories.put("PARTSTAT", obj);
        obj = new RangeFactory();
        super.defaultFactories.put("RANGE", obj);
        obj = new RelatedFactory();
        super.defaultFactories.put("RELATED", obj);
        obj = new RelTypeFactory();
        super.defaultFactories.put("RELTYPE", obj);
        obj = new RoleFactory();
        super.defaultFactories.put("ROLE", obj);
        obj = new RsvpFactory();
        super.defaultFactories.put("RSVP", obj);
        obj = new ScheduleAgentFactory();
        super.defaultFactories.put("SCHEDULE-AGENT", obj);
        obj = new ScheduleStatusFactory();
        super.defaultFactories.put("SCHEDULE-STATUS", obj);
        obj = new SentByFactory();
        super.defaultFactories.put("SENT-BY", obj);
        obj = new TypeFactory();
        super.defaultFactories.put("TYPE", obj);
        obj = new TzIdFactory();
        super.defaultFactories.put("TZID", obj);
        obj = new ValueFactory();
        super.defaultFactories.put("VALUE", obj);
        obj = new VvenueFactory();
        super.defaultFactories.put("VVENUE", obj);
    }

    public final Parameter createParameter(String s, String s1)
        throws URISyntaxException
    {
        Object obj1 = super.defaultFactories.get(s);
        Object obj = obj1;
        if (obj1 == null)
        {
            obj = super.extendedFactories.get(s);
        }
        obj = (ParameterFactory)obj;
        if (obj != null)
        {
            return ((ParameterFactory) (obj)).createParameter(s, s1);
        }
        boolean flag;
        if (s.startsWith("X-") && s.length() > 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return new XParameter(s, s1);
        }
        if (CompatibilityHints.isHintEnabled("ical4j.parsing.relaxed"))
        {
            return new XParameter(s, s1);
        }
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = "Invalid parameter name: ".concat(s);
        } else
        {
            s = new String("Invalid parameter name: ");
        }
        throw new IllegalArgumentException(s);
    }


    private class AbbrevFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new Abbrev(s1);
        }

        AbbrevFactory()
        {
        }
    }


    private class AltRepFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new AltRep(s1);
        }

        AltRepFactory()
        {
        }
    }


    private class CnFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new Cn(s1);
        }

        CnFactory()
        {
        }
    }


    private class CuTypeFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new CuType(s1);
            if (CuType.INDIVIDUAL.equals(s1))
            {
                s = CuType.INDIVIDUAL;
            } else
            {
                if (CuType.GROUP.equals(s1))
                {
                    return CuType.GROUP;
                }
                if (CuType.RESOURCE.equals(s1))
                {
                    return CuType.RESOURCE;
                }
                if (CuType.ROOM.equals(s1))
                {
                    return CuType.ROOM;
                }
                s = s1;
                if (CuType.UNKNOWN.equals(s1))
                {
                    return CuType.UNKNOWN;
                }
            }
            return s;
        }

        CuTypeFactory()
        {
        }
    }


    private class DelegatedFromFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new DelegatedFrom(s1);
        }

        DelegatedFromFactory()
        {
        }
    }


    private class DelegatedToFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new DelegatedTo(s1);
        }

        DelegatedToFactory()
        {
        }
    }


    private class DirFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new Dir(s1);
        }

        DirFactory()
        {
        }
    }


    private class EncodingFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new Encoding(s1);
            if (Encoding.EIGHT_BIT.equals(s1))
            {
                s = Encoding.EIGHT_BIT;
            } else
            {
                s = s1;
                if (Encoding.BASE64.equals(s1))
                {
                    return Encoding.BASE64;
                }
            }
            return s;
        }

        EncodingFactory()
        {
        }
    }


    private class FmtTypeFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new FmtType(s1);
        }

        FmtTypeFactory()
        {
        }
    }


    private class FbTypeFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new FbType(s1);
            if (FbType.FREE.equals(s1))
            {
                s = FbType.FREE;
            } else
            {
                if (FbType.BUSY.equals(s1))
                {
                    return FbType.BUSY;
                }
                if (FbType.BUSY_TENTATIVE.equals(s1))
                {
                    return FbType.BUSY_TENTATIVE;
                }
                s = s1;
                if (FbType.BUSY_UNAVAILABLE.equals(s1))
                {
                    return FbType.BUSY_UNAVAILABLE;
                }
            }
            return s;
        }

        FbTypeFactory()
        {
        }
    }


    private class LanguageFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new Language(s1);
        }

        LanguageFactory()
        {
        }
    }


    private class MemberFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new Member(s1);
        }

        MemberFactory()
        {
        }
    }


    private class PartStatFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new PartStat(s1);
            if (PartStat.NEEDS_ACTION.equals(s1))
            {
                s = PartStat.NEEDS_ACTION;
            } else
            {
                if (PartStat.ACCEPTED.equals(s1))
                {
                    return PartStat.ACCEPTED;
                }
                if (PartStat.DECLINED.equals(s1))
                {
                    return PartStat.DECLINED;
                }
                if (PartStat.TENTATIVE.equals(s1))
                {
                    return PartStat.TENTATIVE;
                }
                if (PartStat.DELEGATED.equals(s1))
                {
                    return PartStat.DELEGATED;
                }
                if (PartStat.COMPLETED.equals(s1))
                {
                    return PartStat.COMPLETED;
                }
                s = s1;
                if (PartStat.IN_PROCESS.equals(s1))
                {
                    return PartStat.IN_PROCESS;
                }
            }
            return s;
        }

        PartStatFactory()
        {
        }
    }


    private class RangeFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new Range(s1);
            if (Range.THISANDFUTURE.equals(s1))
            {
                s = Range.THISANDFUTURE;
            } else
            {
                s = s1;
                if (Range.THISANDPRIOR.equals(s1))
                {
                    return Range.THISANDPRIOR;
                }
            }
            return s;
        }

        RangeFactory()
        {
        }
    }


    private class RelatedFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new Related(s1);
            if (Related.START.equals(s1))
            {
                s = Related.START;
            } else
            {
                s = s1;
                if (Related.END.equals(s1))
                {
                    return Related.END;
                }
            }
            return s;
        }

        RelatedFactory()
        {
        }
    }


    private class RelTypeFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new RelType(s1);
            if (!RelType.PARENT.equals(s1)) goto _L2; else goto _L1
_L1:
            s = RelType.PARENT;
_L4:
            s1 = s;
            if (RelType.SIBLING.equals(s))
            {
                s1 = RelType.SIBLING;
            }
            return s1;
_L2:
            s = s1;
            if (RelType.CHILD.equals(s1))
            {
                s = RelType.CHILD;
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        RelTypeFactory()
        {
        }
    }


    private class RoleFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new Role(s1);
            if (Role.CHAIR.equals(s1))
            {
                s = Role.CHAIR;
            } else
            {
                if (Role.REQ_PARTICIPANT.equals(s1))
                {
                    return Role.REQ_PARTICIPANT;
                }
                if (Role.OPT_PARTICIPANT.equals(s1))
                {
                    return Role.OPT_PARTICIPANT;
                }
                s = s1;
                if (Role.NON_PARTICIPANT.equals(s1))
                {
                    return Role.NON_PARTICIPANT;
                }
            }
            return s;
        }

        RoleFactory()
        {
        }
    }


    private class RsvpFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new Rsvp(s1);
            if (Rsvp.TRUE.equals(s1))
            {
                s = Rsvp.TRUE;
            } else
            {
                s = s1;
                if (Rsvp.FALSE.equals(s1))
                {
                    return Rsvp.FALSE;
                }
            }
            return s;
        }

        RsvpFactory()
        {
        }
    }


    private class ScheduleAgentFactory
        implements ParameterFactory
    {

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new ScheduleAgent(s1);
            if (ScheduleAgent.SERVER.equals(s1))
            {
                s = ScheduleAgent.SERVER;
            } else
            {
                if (ScheduleAgent.CLIENT.equals(s1))
                {
                    return ScheduleAgent.CLIENT;
                }
                s = s1;
                if (ScheduleAgent.NONE.equals(s1))
                {
                    return ScheduleAgent.NONE;
                }
            }
            return s;
        }

        ScheduleAgentFactory()
        {
        }
    }


    private class ScheduleStatusFactory
        implements ParameterFactory
    {

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new ScheduleStatus(s1);
        }

        ScheduleStatusFactory()
        {
        }
    }


    private class SentByFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new SentBy(s1);
        }

        SentByFactory()
        {
        }
    }


    private class TypeFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new Type(s1);
        }

        TypeFactory()
        {
        }
    }


    private class TzIdFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new TzId(Strings.unescape(s1));
        }

        TzIdFactory()
        {
        }
    }


    private class ValueFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            s1 = new Value(s1);
            if (Value.BINARY.equals(s1))
            {
                s = Value.BINARY;
            } else
            {
                if (Value.BOOLEAN.equals(s1))
                {
                    return Value.BOOLEAN;
                }
                if (Value.CAL_ADDRESS.equals(s1))
                {
                    return Value.CAL_ADDRESS;
                }
                if (Value.DATE.equals(s1))
                {
                    return Value.DATE;
                }
                if (Value.DATE_TIME.equals(s1))
                {
                    return Value.DATE_TIME;
                }
                if (Value.DURATION.equals(s1))
                {
                    return Value.DURATION;
                }
                if (Value.FLOAT.equals(s1))
                {
                    return Value.FLOAT;
                }
                if (Value.INTEGER.equals(s1))
                {
                    return Value.INTEGER;
                }
                if (Value.PERIOD.equals(s1))
                {
                    return Value.PERIOD;
                }
                if (Value.RECUR.equals(s1))
                {
                    return Value.RECUR;
                }
                if (Value.TEXT.equals(s1))
                {
                    return Value.TEXT;
                }
                if (Value.TIME.equals(s1))
                {
                    return Value.TIME;
                }
                if (Value.URI.equals(s1))
                {
                    return Value.URI;
                }
                s = s1;
                if (Value.UTC_OFFSET.equals(s1))
                {
                    return Value.UTC_OFFSET;
                }
            }
            return s;
        }

        ValueFactory()
        {
        }
    }


    private class VvenueFactory
        implements ParameterFactory
    {

        public static final long serialVersionUID = 1L;

        public final Parameter createParameter(String s, String s1)
            throws URISyntaxException
        {
            return new Vvenue(s1);
        }

        VvenueFactory()
        {
        }
    }

}
