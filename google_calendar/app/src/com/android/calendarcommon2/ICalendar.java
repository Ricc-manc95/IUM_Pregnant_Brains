// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import java.util.ArrayList;
import java.util.List;

public final class ICalendar
{

    private static String extractValue(ParserState parserstate)
        throws FormatException
    {
        String s = parserstate.line;
        if (parserstate.index >= s.length() || s.charAt(parserstate.index) != ':')
        {
            parserstate = String.valueOf(s);
            if (parserstate.length() != 0)
            {
                parserstate = "Expected ':' before end of line in ".concat(parserstate);
            } else
            {
                parserstate = new String("Expected ':' before end of line in ");
            }
            throw new FormatException(parserstate);
        } else
        {
            String s1 = s.substring(parserstate.index + 1);
            parserstate.index = s.length() - 1;
            return s1;
        }
    }

    public static Component parseComponentImpl(Component component, String s)
        throws FormatException
    {
        Object obj;
        ParserState parserstate;
        String as[];
        int k;
        int l;
        parserstate = new ParserState();
        parserstate.index = 0;
        as = s.split("\n");
        l = as.length;
        k = 0;
        s = component;
        obj = component;
_L12:
        if (k >= l)
        {
            break MISSING_BLOCK_LABEL_959;
        }
        component = as[k];
        int i1;
        parserstate.line = component;
        i1 = parserstate.line.length();
        int i = 0;
        parserstate.index = 0;
_L1:
        int j = i;
        if (parserstate.index >= i1)
        {
            break MISSING_BLOCK_LABEL_135;
        }
        i = component.charAt(parserstate.index);
        j = i;
        if (i == ';')
        {
            break MISSING_BLOCK_LABEL_135;
        }
        j = i;
        if (i == ':')
        {
            break MISSING_BLOCK_LABEL_135;
        }
        parserstate.index = parserstate.index + 1;
          goto _L1
        component = component.substring(0, parserstate.index);
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_169;
        }
        if (!"BEGIN".equals(component))
        {
            throw new FormatException("Expected BEGIN");
        }
        if (!"BEGIN".equals(component))
        {
            break MISSING_BLOCK_LABEL_228;
        }
        component = new Component(extractValue(parserstate), s);
        if (s == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (((Component) (s)).children == null)
        {
            s.children = new ArrayList();
        }
        ((Component) (s)).children.add(component);
        break; /* Loop/switch isn't completed */
        if (!"END".equals(component))
        {
            break MISSING_BLOCK_LABEL_307;
        }
        component = extractValue(parserstate);
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_258;
        }
        if (component.equals(((Component) (s)).name))
        {
            break MISSING_BLOCK_LABEL_299;
        }
        component = String.valueOf(component);
        if (component.length() != 0)
        {
            component = "Unexpected END ".concat(component);
        } else
        {
            component = new String("Unexpected END ");
        }
        throw new FormatException(component);
        component = ((Component) (s)).parent;
        break; /* Loop/switch isn't completed */
        Object obj1 = new Property(component);
        if (j != ';')
        {
            break MISSING_BLOCK_LABEL_940;
        }
_L4:
        String s1;
        s1 = parserstate.line;
        i1 = s1.length();
        component = null;
        j = -1;
        i = -1;
_L8:
        char c;
        if (parserstate.index >= i1)
        {
            break MISSING_BLOCK_LABEL_898;
        }
        c = s1.charAt(parserstate.index);
        if (c != ':') goto _L3; else goto _L2
_L2:
        if (component == null)
        {
            break MISSING_BLOCK_LABEL_444;
        }
        if (i == -1)
        {
            try
            {
                component = String.valueOf(s1);
                if (component.length() != 0)
                {
                    component = "Expected '=' within parameter in ".concat(component);
                } else
                {
                    component = new String("Expected '=' within parameter in ");
                }
                throw new FormatException(component);
            }
            // Misplaced declaration of an exception variable
            catch (Component component)
            {
                obj1 = s;
            }
            break MISSING_BLOCK_LABEL_967;
        }
        component.value = s1.substring(i + 1, parserstate.index);
_L7:
        if (component == null)
        {
            break MISSING_BLOCK_LABEL_940;
        }
        ((Property) (obj1)).addParameter(component);
          goto _L4
_L3:
        if (c != ';') goto _L6; else goto _L5
_L5:
        if (component == null)
        {
            break MISSING_BLOCK_LABEL_536;
        }
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_515;
        }
        component = String.valueOf(s1);
        if (component.length() != 0)
        {
            component = "Expected '=' within parameter in ".concat(component);
        } else
        {
            component = new String("Expected '=' within parameter in ");
        }
        throw new FormatException(component);
        component.value = s1.substring(i + 1, parserstate.index);
          goto _L7
        component = new Parameter();
        j = parserstate.index;
_L9:
        parserstate.index = parserstate.index + 1;
          goto _L8
_L6:
        if (c != '=')
        {
            continue; /* Loop/switch isn't completed */
        }
        i = parserstate.index;
        if (component != null && j != -1)
        {
            break MISSING_BLOCK_LABEL_632;
        }
        component = String.valueOf(s1);
        if (component.length() != 0)
        {
            component = "Expected ';' before '=' in ".concat(component);
        } else
        {
            component = new String("Expected ';' before '=' in ");
        }
        throw new FormatException(component);
        component.name = s1.substring(j + 1, i);
          goto _L9
        if (c != '"') goto _L9; else goto _L10
_L10:
        if (component != null)
        {
            break MISSING_BLOCK_LABEL_703;
        }
        component = String.valueOf(s1);
        if (component.length() != 0)
        {
            component = "Expected parameter before '\"' in ".concat(component);
        } else
        {
            component = new String("Expected parameter before '\"' in ");
        }
        throw new FormatException(component);
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_751;
        }
        component = String.valueOf(s1);
        if (component.length() != 0)
        {
            component = "Expected '=' within parameter in ".concat(component);
        } else
        {
            component = new String("Expected '=' within parameter in ");
        }
        throw new FormatException(component);
        if (parserstate.index > i + 1)
        {
            component = String.valueOf(s1);
            if (component.length() != 0)
            {
                component = "Parameter value cannot contain a '\"' in ".concat(component);
            } else
            {
                component = new String("Parameter value cannot contain a '\"' in ");
            }
            throw new FormatException(component);
        }
        i = s1.indexOf('"', parserstate.index + 1);
        if (i >= 0)
        {
            break MISSING_BLOCK_LABEL_868;
        }
        component = String.valueOf(s1);
        if (component.length() != 0)
        {
            component = "Expected closing '\"' in ".concat(component);
        } else
        {
            component = new String("Expected closing '\"' in ");
        }
        throw new FormatException(component);
        component.value = s1.substring(parserstate.index + 1, i);
        parserstate.index = i + 1;
          goto _L7
        component = String.valueOf(s1);
        if (component.length() != 0)
        {
            component = "Expected ':' before end of line in ".concat(component);
        } else
        {
            component = new String("Expected ':' before end of line in ");
        }
        throw new FormatException(component);
        obj1.value = extractValue(parserstate);
        s.addProperty(((Property) (obj1)));
        component = s;
        break; /* Loop/switch isn't completed */
        return ((Component) (obj));
        s = ((String) (obj));
_L13:
        k++;
        obj = s;
        s = ((String) (obj1));
        if (true) goto _L12; else goto _L11
_L11:
        s = ((String) (obj));
        obj1 = component;
        if (obj == null)
        {
            s = component;
            obj1 = component;
        }
          goto _L13
        if (true) goto _L12; else goto _L14
_L14:
    }

    private class ParserState
    {

        public int index;
        public String line;

        ParserState()
        {
        }
    }


    private class FormatException extends Exception
    {

        public FormatException()
        {
        }

        public FormatException(String s)
        {
            super(s);
        }
    }


    private class Component
    {

        public List children;
        public final String name;
        public final Component parent;
        public final LinkedHashMap propsMap = new LinkedHashMap();

        private final void toString(StringBuilder stringbuilder)
        {
            stringbuilder.append("BEGIN");
            stringbuilder.append(":");
            stringbuilder.append(name);
            stringbuilder.append("\n");
            for (Iterator iterator = propsMap.keySet().iterator(); iterator.hasNext();)
            {
                Object obj = (String)iterator.next();
                obj = ((List)propsMap.get(obj)).iterator();
                while (((Iterator) (obj)).hasNext()) 
                {
                    ((Property)((Iterator) (obj)).next()).toString(stringbuilder);
                    stringbuilder.append("\n");
                }
            }

            if (children != null)
            {
                for (Iterator iterator1 = children.iterator(); iterator1.hasNext(); stringbuilder.append("\n"))
                {
                    ((Component)iterator1.next()).toString(stringbuilder);
                }

            }
            stringbuilder.append("END");
            stringbuilder.append(":");
            stringbuilder.append(name);
        }

        public final void addProperty(Property property)
        {
            String s = property.name;
            ArrayList arraylist1 = (ArrayList)propsMap.get(s);
            ArrayList arraylist = arraylist1;
            if (arraylist1 == null)
            {
                arraylist = new ArrayList();
                propsMap.put(s, arraylist);
            }
            arraylist.add(property);
        }

        public final String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            toString(stringbuilder);
            stringbuilder.append("\n");
            return stringbuilder.toString();
        }

        public Component(String s, Component component)
        {
            children = null;
            name = s;
            parent = component;
        }
    }


    private class Property
    {

        public final String name;
        public LinkedHashMap paramsMap;
        public String value;

        public final void addParameter(Parameter parameter)
        {
            ArrayList arraylist1 = (ArrayList)paramsMap.get(parameter.name);
            ArrayList arraylist = arraylist1;
            if (arraylist1 == null)
            {
                arraylist = new ArrayList();
                paramsMap.put(parameter.name, arraylist);
            }
            arraylist.add(parameter);
        }

        public final String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            toString(stringbuilder);
            return stringbuilder.toString();
        }

        public final void toString(StringBuilder stringbuilder)
        {
            stringbuilder.append(name);
            for (Iterator iterator = paramsMap.keySet().iterator(); iterator.hasNext();)
            {
                Object obj = (String)iterator.next();
                obj = ((List)paramsMap.get(obj)).iterator();
                while (((Iterator) (obj)).hasNext()) 
                {
                    Parameter parameter = (Parameter)((Iterator) (obj)).next();
                    stringbuilder.append(";");
                    stringbuilder.append(parameter.name);
                    stringbuilder.append("=");
                    stringbuilder.append(parameter.value);
                }
            }

            stringbuilder.append(":");
            stringbuilder.append(value);
        }

        public Property(String s)
        {
            paramsMap = new LinkedHashMap();
            name = s;
        }
    }


    private class Parameter
    {

        public String name;
        public String value;

        public final String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(name);
            stringbuilder.append("=");
            stringbuilder.append(value);
            return stringbuilder.toString();
        }

        public Parameter()
        {
        }

        public Parameter(String s, String s1)
        {
            name = s;
            value = s1;
        }
    }

}
