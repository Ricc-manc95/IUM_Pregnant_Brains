// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import com.android.datetimepicker.HapticFeedbackController;
import com.android.datetimepicker.Utils;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

// Referenced classes of package com.android.datetimepicker.time:
//            RadialPickerLayout, CircleView, AmPmCirclesView, RadialTextsView, 
//            RadialSelectorView

public final class TimePickerBaseDialog
    implements RadialPickerLayout.OnValueSelectedListener
{

    private boolean allowAutoAdvance;
    private int amKeyCode;
    private View amPmHitspace;
    private TextView amPmTextView;
    public String amText;
    public TimePickerDialog.OnTimeSetListener callback;
    public String deletedKeyFormat;
    private TextView doneButton;
    private String doublePlaceholderText;
    public HapticFeedbackController hapticFeedbackController;
    private String hourPickerDescription;
    private TextView hourSpaceView;
    private TextView hourView;
    public boolean inKbMode;
    public int initialHourOfDay;
    public int initialMinute;
    public boolean is24HourMode;
    private Node legalTimesTree;
    private String minutePickerDescription;
    private TextView minuteSpaceView;
    private TextView minuteView;
    public final DismissiblePicker picker;
    private char placeholderText;
    private int pmKeyCode;
    public String pmText;
    private String selectHours;
    private String selectMinutes;
    private int selectedColor;
    public boolean themeDark;
    public RadialPickerLayout timePicker;
    public ArrayList typedTimes;
    private int unselectedColor;

    TimePickerBaseDialog(DismissiblePicker dismissiblepicker)
    {
        picker = dismissiblepicker;
    }

    private final int[] getEnteredTime(Boolean aboolean[])
    {
        int i;
        int l;
        if (!is24HourMode && isTypedTimeFullyLegal())
        {
            i = ((Integer)typedTimes.get(typedTimes.size() - 1)).intValue();
            int j;
            int k;
            int i1;
            int j1;
            int k1;
            int l1;
            if (i == getAmOrPmKeyCode(0))
            {
                i = 0;
            } else
            if (i == getAmOrPmKeyCode(1))
            {
                i = 1;
            } else
            {
                i = -1;
            }
            j = 2;
            l = i;
            i = j;
        } else
        {
            i = 1;
            l = -1;
        }
        k1 = -1;
        j1 = -1;
        i1 = i;
        while (i1 <= typedTimes.size()) 
        {
            l1 = getValFromKeyCode(((Integer)typedTimes.get(typedTimes.size() - i1)).intValue());
            if (i1 == i)
            {
                k = l1;
                j = k1;
            } else
            if (i1 == i + 1)
            {
                j1 += l1 * 10;
                j = k1;
                k = j1;
                if (aboolean != null)
                {
                    j = k1;
                    k = j1;
                    if (l1 == 0)
                    {
                        aboolean[1] = Boolean.valueOf(true);
                        j = k1;
                        k = j1;
                    }
                }
            } else
            if (i1 == i + 2)
            {
                j = l1;
                k = j1;
            } else
            {
                j = k1;
                k = j1;
                if (i1 == i + 3)
                {
                    k1 += l1 * 10;
                    j = k1;
                    k = j1;
                    if (aboolean != null)
                    {
                        j = k1;
                        k = j1;
                        if (l1 == 0)
                        {
                            aboolean[0] = Boolean.valueOf(true);
                            j = k1;
                            k = j1;
                        }
                    }
                }
            }
            i1++;
            k1 = j;
            j1 = k;
        }
        return (new int[] {
            k1, j1, l
        });
    }

    static int getValFromKeyCode(int i)
    {
        switch (i)
        {
        default:
            return -1;

        case 7: // '\007'
            return 0;

        case 8: // '\b'
            return 1;

        case 9: // '\t'
            return 2;

        case 10: // '\n'
            return 3;

        case 11: // '\013'
            return 4;

        case 12: // '\f'
            return 5;

        case 13: // '\r'
            return 6;

        case 14: // '\016'
            return 7;

        case 15: // '\017'
            return 8;

        case 16: // '\020'
            return 9;
        }
    }

    private final void setHour(int i, boolean flag)
    {
        if (!is24HourMode) goto _L2; else goto _L1
_L1:
        String s = "%02d";
_L4:
        s = String.format(s, new Object[] {
            Integer.valueOf(i)
        });
        hourView.setText(s);
        hourSpaceView.setText(s);
        if (flag)
        {
            RadialPickerLayout radialpickerlayout = timePicker;
            if (radialpickerlayout != null && s != null)
            {
                radialpickerlayout.announceForAccessibility(s);
            }
        }
        return;
_L2:
        String s1 = "%d";
        int j = i % 12;
        s = s1;
        i = j;
        if (j == 0)
        {
            i = 12;
            s = s1;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final void setMinute(int i)
    {
        int j = i;
        if (i == 60)
        {
            j = 0;
        }
        String s = String.format(Locale.getDefault(), "%02d", new Object[] {
            Integer.valueOf(j)
        });
        RadialPickerLayout radialpickerlayout = timePicker;
        if (radialpickerlayout != null && s != null)
        {
            radialpickerlayout.announceForAccessibility(s);
        }
        minuteView.setText(s);
        minuteSpaceView.setText(s);
    }

    final boolean addKeyIfLegal(int i)
    {
        Node node;
        ArrayList arraylist;
        int j;
        int i1;
        if (is24HourMode && typedTimes.size() == 4 || !is24HourMode && isTypedTimeFullyLegal())
        {
            return false;
        }
        typedTimes.add(Integer.valueOf(i));
        node = legalTimesTree;
        arraylist = (ArrayList)typedTimes;
        i1 = arraylist.size();
        j = 0;
_L17:
        if (j >= i1) goto _L2; else goto _L1
_L1:
        int j1 = ((Integer)arraylist.get(j)).intValue();
        if (node.children == null) goto _L4; else goto _L3
_L3:
        ArrayList arraylist1;
        int k;
        int k1;
        arraylist1 = (ArrayList)node.children;
        k1 = arraylist1.size();
        k = 0;
_L16:
        if (k >= k1) goto _L4; else goto _L5
_L5:
        int l;
        node = (Node)arraylist1.get(k);
        l = 0;
_L14:
        if (l >= node.legalKeys.length) goto _L7; else goto _L6
_L6:
        if (node.legalKeys[l] != j1) goto _L9; else goto _L8
_L8:
        l = 1;
_L15:
        if (l == 0) goto _L11; else goto _L10
_L10:
        if (node != null) goto _L13; else goto _L12
_L12:
        j = 0;
_L18:
        if (j == 0)
        {
            deleteLastTypedKey();
            return false;
        }
        break MISSING_BLOCK_LABEL_229;
_L9:
        l++;
          goto _L14
_L7:
        l = 0;
          goto _L15
_L11:
        k++;
          goto _L16
_L4:
        node = null;
          goto _L10
_L13:
        j++;
          goto _L17
_L2:
        j = 1;
          goto _L18
        i = getValFromKeyCode(i);
        RadialPickerLayout radialpickerlayout = timePicker;
        String s = String.format("%d", new Object[] {
            Integer.valueOf(i)
        });
        if (radialpickerlayout != null && s != null)
        {
            radialpickerlayout.announceForAccessibility(s);
        }
        if (isTypedTimeFullyLegal())
        {
            if (!is24HourMode && typedTimes.size() <= 3)
            {
                typedTimes.add(typedTimes.size() - 1, Integer.valueOf(7));
                typedTimes.add(typedTimes.size() - 1, Integer.valueOf(7));
            }
            doneButton.setEnabled(true);
        }
        return true;
          goto _L10
    }

    final int deleteLastTypedKey()
    {
        int i = ((Integer)typedTimes.remove(typedTimes.size() - 1)).intValue();
        if (!isTypedTimeFullyLegal())
        {
            doneButton.setEnabled(false);
        }
        return i;
    }

    final void finishKbMode(boolean flag)
    {
        inKbMode = false;
        if (!typedTimes.isEmpty())
        {
            int ai[] = getEnteredTime(null);
            RadialPickerLayout radialpickerlayout = timePicker;
            int i = ai[0];
            int j = ai[1];
            radialpickerlayout.setItem(0, i);
            radialpickerlayout.setItem(1, j);
            if (!is24HourMode)
            {
                timePicker.setAmOrPm(ai[2]);
            }
            typedTimes.clear();
        }
        if (flag)
        {
            updateDisplay(false);
            timePicker.trySettingInputEnabled(true);
        }
    }

    final int getAmOrPmKeyCode(int i)
    {
        if (amKeyCode != -1 && pmKeyCode != -1) goto _L2; else goto _L1
_L1:
        KeyCharacterMap keycharactermap;
        int j;
        keycharactermap = KeyCharacterMap.load(-1);
        j = 0;
_L7:
        if (j >= Math.max(amText.length(), pmText.length())) goto _L2; else goto _L3
_L3:
        char c;
        char c1;
        c = amText.toLowerCase(Locale.getDefault()).charAt(j);
        c1 = pmText.toLowerCase(Locale.getDefault()).charAt(j);
        if (c == c1) goto _L5; else goto _L4
_L4:
        KeyEvent akeyevent[] = keycharactermap.getEvents(new char[] {
            c, c1
        });
        if (akeyevent != null && akeyevent.length == 4)
        {
            amKeyCode = akeyevent[0].getKeyCode();
            pmKeyCode = akeyevent[2].getKeyCode();
        } else
        {
            Log.e("TimePickerDialog", "Unable to find keycodes for AM and PM.");
        }
_L2:
        if (i == 0)
        {
            return amKeyCode;
        }
        break; /* Loop/switch isn't completed */
_L5:
        j++;
        if (true) goto _L7; else goto _L6
_L6:
        if (i == 1)
        {
            return pmKeyCode;
        } else
        {
            return -1;
        }
    }

    final boolean isTypedTimeFullyLegal()
    {
        if (!is24HourMode) goto _L2; else goto _L1
_L1:
        int ai[] = getEnteredTime(null);
        if (ai[0] < 0 || ai[1] < 0 || ai[1] >= 60) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (!typedTimes.contains(Integer.valueOf(getAmOrPmKeyCode(0))) && !typedTimes.contains(Integer.valueOf(getAmOrPmKeyCode(1))))
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    final void onCreate(Bundle bundle)
    {
        if (bundle != null && bundle.containsKey("hour_of_day") && bundle.containsKey("minute") && bundle.containsKey("is_24_hour_view"))
        {
            initialHourOfDay = bundle.getInt("hour_of_day");
            initialMinute = bundle.getInt("minute");
            is24HourMode = bundle.getBoolean("is_24_hour_view");
            inKbMode = bundle.getBoolean("in_kb_mode");
            themeDark = bundle.getBoolean("dark_theme");
        }
    }

    final View onCreateView$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FEPKMATPF9HGNIRRLEH4MSPJCC5Q6ASHR9HGMSP3IDTKM8BRMD5INEBQMD5INEHRIDTQN0EQCC5N68SJFD5I2URRJ5T17ARJ4DHIJMAACC5N68SJFD5I2UTJ9CLRIULJ9CLRJM___0(Context context, LayoutInflater layoutinflater, Bundle bundle)
    {
        picker.getDialog().getWindow().requestFeature(1);
        View view = layoutinflater.inflate(0x7f05017a, null);
        KeyboardListener keyboardlistener = new KeyboardListener();
        view.findViewById(0x7f100380).setOnKeyListener(keyboardlistener);
        Resources resources = context.getResources();
        hourPickerDescription = resources.getString(0x7f1302f5);
        selectHours = resources.getString(0x7f130434);
        minutePickerDescription = resources.getString(0x7f130334);
        selectMinutes = resources.getString(0x7f130435);
        Object obj;
        Object obj1;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        boolean flag;
        boolean flag2;
        if (themeDark)
        {
            i = 0x7f0d02e7;
        } else
        {
            i = 0x7f0d003c;
        }
        selectedColor = resources.getColor(i);
        if (themeDark)
        {
            i = 0x106000b;
        } else
        {
            i = 0x7f0d0166;
        }
        unselectedColor = resources.getColor(i);
        hourView = (TextView)view.findViewById(0x7f10014d);
        hourView.setOnKeyListener(keyboardlistener);
        hourSpaceView = (TextView)view.findViewById(0x7f10037c);
        minuteSpaceView = (TextView)view.findViewById(0x7f10037d);
        minuteView = (TextView)view.findViewById(0x7f10014c);
        minuteView.setOnKeyListener(keyboardlistener);
        amPmTextView = (TextView)view.findViewById(0x7f10037f);
        amPmTextView.setOnKeyListener(keyboardlistener);
        layoutinflater = (new DateFormatSymbols()).getAmPmStrings();
        amText = layoutinflater[0];
        pmText = layoutinflater[1];
        hapticFeedbackController = new HapticFeedbackController(context);
        timePicker = (RadialPickerLayout)view.findViewById(0x7f100382);
        timePicker.listener = this;
        timePicker.setOnKeyListener(keyboardlistener);
        obj1 = timePicker;
        layoutinflater = hapticFeedbackController;
        j = initialHourOfDay;
        k = initialMinute;
        flag2 = is24HourMode;
        if (((RadialPickerLayout) (obj1)).timeInitialized)
        {
            Log.e("RadialPickerLayout", "Time has already been initialized.");
        } else
        {
            obj1.hapticFeedbackController = layoutinflater;
            obj1.is24HourMode = flag2;
            Object obj2;
            String as[];
            String as1[];
            int ai[];
            boolean flag1;
            if (((RadialPickerLayout) (obj1)).accessibilityManager.isTouchExplorationEnabled())
            {
                flag1 = true;
            } else
            {
                flag1 = ((RadialPickerLayout) (obj1)).is24HourMode;
            }
            obj1.hideAmPm = flag1;
            layoutinflater = ((RadialPickerLayout) (obj1)).circleView;
            flag1 = ((RadialPickerLayout) (obj1)).hideAmPm;
            if (((CircleView) (layoutinflater)).isInitialized)
            {
                Log.e("CircleView", "CircleView may only be initialized once.");
            } else
            {
                obj = context.getResources();
                layoutinflater.is24HourMode = flag1;
                if (flag1)
                {
                    layoutinflater.circleRadiusMultiplier = Float.parseFloat(((Resources) (obj)).getString(0x7f13010d));
                } else
                {
                    layoutinflater.circleRadiusMultiplier = Float.parseFloat(((Resources) (obj)).getString(0x7f13010c));
                    layoutinflater.amPmCircleRadiusMultiplier = Float.parseFloat(((Resources) (obj)).getString(0x7f1300b4));
                }
                layoutinflater.isInitialized = true;
            }
            ((RadialPickerLayout) (obj1)).circleView.invalidate();
            if (!((RadialPickerLayout) (obj1)).hideAmPm)
            {
                layoutinflater = ((RadialPickerLayout) (obj1)).amPmCirclesView;
                if (j < 12)
                {
                    i = 0;
                } else
                {
                    i = 1;
                }
                if (((AmPmCirclesView) (layoutinflater)).isInitialized)
                {
                    Log.e("AmPmCirclesView", "AmPmCirclesView may only be initialized once.");
                } else
                {
                    obj = context.getResources();
                    layoutinflater.unselectedColor = ((Resources) (obj)).getColor(0x106000b);
                    layoutinflater.selectedColor = ((Resources) (obj)).getColor(0x7f0d003c);
                    layoutinflater.amPmTextColor = ((Resources) (obj)).getColor(0x7f0d0008);
                    layoutinflater.selectedAlpha = 51;
                    obj2 = Typeface.create(((Resources) (obj)).getString(0x7f130424), 0);
                    ((AmPmCirclesView) (layoutinflater)).paint.setTypeface(((Typeface) (obj2)));
                    ((AmPmCirclesView) (layoutinflater)).paint.setAntiAlias(true);
                    ((AmPmCirclesView) (layoutinflater)).paint.setTextAlign(android.graphics.Paint.Align.CENTER);
                    layoutinflater.circleRadiusMultiplier = Float.parseFloat(((Resources) (obj)).getString(0x7f13010c));
                    layoutinflater.amPmCircleRadiusMultiplier = Float.parseFloat(((Resources) (obj)).getString(0x7f1300b4));
                    obj = (new DateFormatSymbols()).getAmPmStrings();
                    layoutinflater.amText = obj[0];
                    layoutinflater.pmText = obj[1];
                    layoutinflater.setAmOrPm(i);
                    layoutinflater.amOrPmPressed = -1;
                    layoutinflater.isInitialized = true;
                }
                ((RadialPickerLayout) (obj1)).amPmCirclesView.invalidate();
            }
            obj2 = context.getResources();
            ai = new int[12];
            int[] _tmp = ai;
            ai[0] = 12;
            ai[1] = 1;
            ai[2] = 2;
            ai[3] = 3;
            ai[4] = 4;
            ai[5] = 5;
            ai[6] = 6;
            ai[7] = 7;
            ai[8] = 8;
            ai[9] = 9;
            ai[10] = 10;
            ai[11] = 11;
            as = new String[12];
            obj = new String[12];
            as1 = new String[12];
            i = 0;
            while (i < 12) 
            {
                if (flag2)
                {
                    layoutinflater = String.format("%02d", new Object[] {
                        Integer.valueOf((new int[] {
                            0, 13, 14, 15, 16, 17, 18, 19, 20, 21, 
                            22, 23
                        })[i])
                    });
                } else
                {
                    layoutinflater = String.format("%d", new Object[] {
                        Integer.valueOf(ai[i])
                    });
                }
                as[i] = layoutinflater;
                obj[i] = String.format("%d", new Object[] {
                    Integer.valueOf(ai[i])
                });
                as1[i] = String.format("%02d", new Object[] {
                    Integer.valueOf((new int[] {
                        0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 
                        50, 55
                    })[i])
                });
                i++;
            }
            RadialTextsView radialtextsview = ((RadialPickerLayout) (obj1)).hourRadialTextsView;
            boolean flag3;
            if (flag2)
            {
                layoutinflater = ((LayoutInflater) (obj));
            } else
            {
                layoutinflater = null;
            }
            radialtextsview.initialize(((Resources) (obj2)), as, layoutinflater, ((RadialPickerLayout) (obj1)).hideAmPm, true);
            ((RadialPickerLayout) (obj1)).hourRadialTextsView.invalidate();
            ((RadialPickerLayout) (obj1)).minuteRadialTextsView.initialize(((Resources) (obj2)), as1, null, ((RadialPickerLayout) (obj1)).hideAmPm, false);
            ((RadialPickerLayout) (obj1)).minuteRadialTextsView.invalidate();
            ((RadialPickerLayout) (obj1)).setValueForItem(0, j);
            ((RadialPickerLayout) (obj1)).setValueForItem(1, k);
            layoutinflater = ((RadialPickerLayout) (obj1)).hourRadialSelectorView;
            flag3 = ((RadialPickerLayout) (obj1)).hideAmPm;
            if (((RadialPickerLayout) (obj1)).is24HourMode && j <= 12 && j != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            layoutinflater.initialize(context, flag3, flag2, true, (j % 12) * 30, flag1);
            ((RadialPickerLayout) (obj1)).minuteRadialSelectorView.initialize(context, ((RadialPickerLayout) (obj1)).hideAmPm, false, false, k * 6, false);
            obj1.timeInitialized = true;
        }
        j = 0;
        i = j;
        if (bundle != null)
        {
            i = j;
            if (bundle.containsKey("current_item_showing"))
            {
                i = bundle.getInt("current_item_showing");
            }
        }
        setCurrentItemShowing(i, false, true, true);
        timePicker.invalidate();
        hourView.setOnClickListener(new _cls1());
        minuteView.setOnClickListener(new _cls2());
        doneButton = (TextView)view.findViewById(0x7f100294);
        doneButton.setOnClickListener(new _cls3());
        doneButton.setOnKeyListener(keyboardlistener);
        amPmHitspace = view.findViewById(0x7f10037e);
        if (is24HourMode)
        {
            amPmTextView.setVisibility(8);
            layoutinflater = new android.widget.RelativeLayout.LayoutParams(-2, -2);
            layoutinflater.addRule(13);
            ((TextView)view.findViewById(0x7f10034f)).setLayoutParams(layoutinflater);
        } else
        {
            amPmTextView.setVisibility(0);
            if (initialHourOfDay < 12)
            {
                i = 0;
            } else
            {
                i = 1;
            }
            updateAmPmDisplay(i);
            amPmHitspace.setOnClickListener(new _cls4());
        }
        allowAutoAdvance = true;
        setHour(initialHourOfDay, true);
        setMinute(initialMinute);
        doublePlaceholderText = resources.getString(0x7f13047e);
        deletedKeyFormat = resources.getString(0x7f13015f);
        placeholderText = doublePlaceholderText.charAt(0);
        pmKeyCode = -1;
        amKeyCode = -1;
        legalTimesTree = new Node(new int[0]);
        if (is24HourMode)
        {
            layoutinflater = new Node(new int[] {
                7, 8, 9, 10, 11, 12
            });
            Node node = new Node(new int[] {
                7, 8, 9, 10, 11, 12, 13, 14, 15, 16
            });
            ((Node) (layoutinflater)).children.add(node);
            Node node1 = new Node(new int[] {
                7, 8
            });
            legalTimesTree.children.add(node1);
            obj1 = new Node(new int[] {
                7, 8, 9, 10, 11, 12
            });
            node1.children.add(obj1);
            ((Node) (obj1)).children.add(layoutinflater);
            Node node4 = new Node(new int[] {
                13, 14, 15, 16
            });
            ((Node) (obj1)).children.add(node4);
            obj1 = new Node(new int[] {
                13, 14, 15, 16
            });
            node1.children.add(obj1);
            ((Node) (obj1)).children.add(layoutinflater);
            node1 = new Node(new int[] {
                9
            });
            legalTimesTree.children.add(node1);
            obj1 = new Node(new int[] {
                7, 8, 9, 10
            });
            node1.children.add(obj1);
            ((Node) (obj1)).children.add(layoutinflater);
            obj1 = new Node(new int[] {
                11, 12
            });
            node1.children.add(obj1);
            ((Node) (obj1)).children.add(node);
            node = new Node(new int[] {
                10, 11, 12, 13, 14, 15, 16
            });
            legalTimesTree.children.add(node);
            node.children.add(layoutinflater);
        } else
        {
            layoutinflater = new Node(new int[] {
                getAmOrPmKeyCode(0), getAmOrPmKeyCode(1)
            });
            obj = new Node(new int[] {
                8
            });
            legalTimesTree.children.add(obj);
            ((Node) (obj)).children.add(layoutinflater);
            Node node2 = new Node(new int[] {
                7, 8, 9
            });
            ((Node) (obj)).children.add(node2);
            node2.children.add(layoutinflater);
            Node node3 = new Node(new int[] {
                7, 8, 9, 10, 11, 12
            });
            node2.children.add(node3);
            node3.children.add(layoutinflater);
            Node node5 = new Node(new int[] {
                7, 8, 9, 10, 11, 12, 13, 14, 15, 16
            });
            node3.children.add(node5);
            node5.children.add(layoutinflater);
            node3 = new Node(new int[] {
                13, 14, 15, 16
            });
            node2.children.add(node3);
            node3.children.add(layoutinflater);
            node2 = new Node(new int[] {
                10, 11, 12
            });
            ((Node) (obj)).children.add(node2);
            obj = new Node(new int[] {
                7, 8, 9, 10, 11, 12, 13, 14, 15, 16
            });
            node2.children.add(obj);
            ((Node) (obj)).children.add(layoutinflater);
            node2 = new Node(new int[] {
                9, 10, 11, 12, 13, 14, 15, 16
            });
            legalTimesTree.children.add(node2);
            node2.children.add(layoutinflater);
            obj = new Node(new int[] {
                7, 8, 9, 10, 11, 12
            });
            node2.children.add(obj);
            node2 = new Node(new int[] {
                7, 8, 9, 10, 11, 12, 13, 14, 15, 16
            });
            ((Node) (obj)).children.add(node2);
            node2.children.add(layoutinflater);
        }
        if (inKbMode)
        {
            typedTimes = bundle.getIntegerArrayList("typed_times");
            tryStartingKbMode(-1);
            hourView.invalidate();
        } else
        if (typedTimes == null)
        {
            typedTimes = new ArrayList();
        }
        layoutinflater = timePicker;
        context = context.getApplicationContext();
        flag = themeDark;
        bundle = ((RadialPickerLayout) (layoutinflater)).circleView;
        obj = context.getResources();
        if (flag)
        {
            bundle.circleColor = ((Resources) (obj)).getColor(0x7f0d007a);
            bundle.dotColor = ((Resources) (obj)).getColor(0x7f0d013f);
        } else
        {
            bundle.circleColor = ((Resources) (obj)).getColor(0x106000b);
            bundle.dotColor = ((Resources) (obj)).getColor(0x7f0d0166);
        }
        bundle = ((RadialPickerLayout) (layoutinflater)).amPmCirclesView;
        obj = context.getResources();
        if (flag)
        {
            bundle.unselectedColor = ((Resources) (obj)).getColor(0x7f0d007a);
            bundle.selectedColor = ((Resources) (obj)).getColor(0x7f0d02e7);
            bundle.amPmTextColor = ((Resources) (obj)).getColor(0x106000b);
            bundle.selectedAlpha = 102;
        } else
        {
            bundle.unselectedColor = ((Resources) (obj)).getColor(0x106000b);
            bundle.selectedColor = ((Resources) (obj)).getColor(0x7f0d003c);
            bundle.amPmTextColor = ((Resources) (obj)).getColor(0x7f0d0008);
            bundle.selectedAlpha = 51;
        }
        ((RadialPickerLayout) (layoutinflater)).hourRadialTextsView.setTheme(context, flag);
        ((RadialPickerLayout) (layoutinflater)).minuteRadialTextsView.setTheme(context, flag);
        ((RadialPickerLayout) (layoutinflater)).hourRadialSelectorView.setTheme(context, flag);
        ((RadialPickerLayout) (layoutinflater)).minuteRadialSelectorView.setTheme(context, flag);
        i = resources.getColor(0x106000b);
        i1 = resources.getColor(0x7f0d0065);
        k1 = resources.getColor(0x7f0d0140);
        j = resources.getColor(0x7f0d0166);
        context = resources.getColorStateList(0x7f0d0363);
        k = resources.getColor(0x7f0d007a);
        j1 = resources.getColor(0x7f0d013f);
        l1 = resources.getColor(0x7f0d0142);
        layoutinflater = resources.getColorStateList(0x7f0d0364);
        bundle = view.findViewById(0x7f100381);
        if (themeDark)
        {
            l = k;
        } else
        {
            l = i;
        }
        bundle.setBackgroundColor(l);
        bundle = view.findViewById(0x7f10037a);
        if (!themeDark)
        {
            k = i;
        }
        bundle.setBackgroundColor(k);
        bundle = (TextView)view.findViewById(0x7f10034f);
        if (themeDark)
        {
            k = i;
        } else
        {
            k = j;
        }
        bundle.setTextColor(k);
        bundle = (TextView)view.findViewById(0x7f10037f);
        if (!themeDark)
        {
            i = j;
        }
        bundle.setTextColor(i);
        bundle = view.findViewById(0x7f100383);
        if (themeDark)
        {
            i = l1;
        } else
        {
            i = k1;
        }
        bundle.setBackgroundColor(i);
        bundle = doneButton;
        if (themeDark)
        {
            context = layoutinflater;
        }
        bundle.setTextColor(context);
        context = timePicker;
        if (themeDark)
        {
            i = j1;
        } else
        {
            i = i1;
        }
        context.setBackgroundColor(i);
        context = doneButton;
        if (themeDark)
        {
            i = 0x7f0200fd;
        } else
        {
            i = 0x7f0200fc;
        }
        context.setBackgroundResource(i);
        return view;
    }

    final void onSaveInstanceState(Bundle bundle)
    {
        if (timePicker != null)
        {
            bundle.putInt("hour_of_day", timePicker.currentHoursOfDay);
            bundle.putInt("minute", timePicker.currentMinutes);
            bundle.putBoolean("is_24_hour_view", is24HourMode);
            bundle.putInt("current_item_showing", timePicker.getCurrentItemShowing());
            bundle.putBoolean("in_kb_mode", inKbMode);
            if (inKbMode)
            {
                bundle.putIntegerArrayList("typed_times", typedTimes);
            }
            bundle.putBoolean("dark_theme", themeDark);
        }
    }

    public final void onValueSelected(int i, int j, boolean flag)
    {
        if (i == 0)
        {
            setHour(j, false);
            String s = String.format("%d", new Object[] {
                Integer.valueOf(j)
            });
            RadialPickerLayout radialpickerlayout1;
            if (allowAutoAdvance && flag)
            {
                setCurrentItemShowing(1, true, true, false);
                s = String.valueOf(s);
                String s1 = selectMinutes;
                s = (new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(s1).length())).append(s).append(". ").append(s1).toString();
            } else
            {
                RadialPickerLayout radialpickerlayout2 = timePicker;
                String s3 = hourPickerDescription;
                radialpickerlayout2.setContentDescription((new StringBuilder(String.valueOf(s3).length() + 13)).append(s3).append(": ").append(j).toString());
            }
            radialpickerlayout1 = timePicker;
            if (radialpickerlayout1 != null && s != null)
            {
                radialpickerlayout1.announceForAccessibility(s);
            }
        } else
        {
            if (i == 1)
            {
                setMinute(j);
                RadialPickerLayout radialpickerlayout = timePicker;
                String s2 = minutePickerDescription;
                radialpickerlayout.setContentDescription((new StringBuilder(String.valueOf(s2).length() + 13)).append(s2).append(": ").append(j).toString());
                return;
            }
            if (i == 2)
            {
                updateAmPmDisplay(j);
                return;
            }
            if (i == 3)
            {
                if (!isTypedTimeFullyLegal())
                {
                    typedTimes.clear();
                }
                finishKbMode(true);
                return;
            }
        }
    }

    final void setCurrentItemShowing(int i, boolean flag, boolean flag1, boolean flag2)
    {
        int l = 255;
        Object obj = timePicker;
        int k;
        if (i != 0 && i != 1)
        {
            Log.e("RadialPickerLayout", (new StringBuilder(53)).append("TimePicker does not support view at index ").append(i).toString());
        } else
        {
            k = ((RadialPickerLayout) (obj)).getCurrentItemShowing();
            obj.currentItemShowing = i;
            if (flag && i != k)
            {
                ObjectAnimator aobjectanimator[] = new ObjectAnimator[4];
                if (i == 1)
                {
                    aobjectanimator[0] = ((RadialPickerLayout) (obj)).hourRadialTextsView.getDisappearAnimator();
                    aobjectanimator[1] = ((RadialPickerLayout) (obj)).hourRadialSelectorView.getDisappearAnimator();
                    aobjectanimator[2] = ((RadialPickerLayout) (obj)).minuteRadialTextsView.getReappearAnimator();
                    aobjectanimator[3] = ((RadialPickerLayout) (obj)).minuteRadialSelectorView.getReappearAnimator();
                } else
                if (i == 0)
                {
                    aobjectanimator[0] = ((RadialPickerLayout) (obj)).hourRadialTextsView.getReappearAnimator();
                    aobjectanimator[1] = ((RadialPickerLayout) (obj)).hourRadialSelectorView.getReappearAnimator();
                    aobjectanimator[2] = ((RadialPickerLayout) (obj)).minuteRadialTextsView.getDisappearAnimator();
                    aobjectanimator[3] = ((RadialPickerLayout) (obj)).minuteRadialSelectorView.getDisappearAnimator();
                }
                if (((RadialPickerLayout) (obj)).transition != null && ((RadialPickerLayout) (obj)).transition.isRunning())
                {
                    ((RadialPickerLayout) (obj)).transition.end();
                }
                obj.transition = new AnimatorSet();
                ((RadialPickerLayout) (obj)).transition.playTogether(aobjectanimator);
                ((RadialPickerLayout) (obj)).transition.start();
            } else
            {
                if (i == 0)
                {
                    k = 255;
                } else
                {
                    k = 0;
                }
                if (i != 1)
                {
                    l = 0;
                }
                ((RadialPickerLayout) (obj)).hourRadialTextsView.setAlpha(k);
                ((RadialPickerLayout) (obj)).hourRadialSelectorView.setAlpha(k);
                ((RadialPickerLayout) (obj)).minuteRadialTextsView.setAlpha(l);
                ((RadialPickerLayout) (obj)).minuteRadialSelectorView.setAlpha(l);
            }
        }
        if (i == 0)
        {
            l = timePicker.currentHoursOfDay;
            int j = l;
            if (!is24HourMode)
            {
                j = l % 12;
            }
            obj = timePicker;
            String s = hourPickerDescription;
            ((RadialPickerLayout) (obj)).setContentDescription((new StringBuilder(String.valueOf(s).length() + 13)).append(s).append(": ").append(j).toString());
            if (flag2)
            {
                obj = timePicker;
                String s1 = selectHours;
                if (obj != null && s1 != null)
                {
                    ((View) (obj)).announceForAccessibility(s1);
                }
            }
            obj = hourView;
        } else
        {
            k = timePicker.currentMinutes;
            obj = timePicker;
            String s2 = minutePickerDescription;
            ((RadialPickerLayout) (obj)).setContentDescription((new StringBuilder(String.valueOf(s2).length() + 13)).append(s2).append(": ").append(k).toString());
            if (flag2)
            {
                obj = timePicker;
                String s3 = selectMinutes;
                if (obj != null && s3 != null)
                {
                    ((View) (obj)).announceForAccessibility(s3);
                }
            }
            obj = minuteView;
        }
        if (i == 0)
        {
            k = selectedColor;
        } else
        {
            k = unselectedColor;
        }
        if (i == 1)
        {
            i = selectedColor;
        } else
        {
            i = unselectedColor;
        }
        hourView.setTextColor(k);
        minuteView.setTextColor(i);
        obj = Utils.getPulseAnimator(((View) (obj)), 0.85F, 1.1F);
        if (flag1)
        {
            ((ObjectAnimator) (obj)).setStartDelay(300L);
        }
        ((ObjectAnimator) (obj)).start();
    }

    final void tryStartingKbMode(int i)
    {
        if (timePicker.trySettingInputEnabled(false) && (i == -1 || addKeyIfLegal(i)))
        {
            inKbMode = true;
            doneButton.setEnabled(false);
            updateDisplay(false);
        }
    }

    final void updateAmPmDisplay(int i)
    {
        if (i == 0)
        {
            amPmTextView.setText(amText);
            RadialPickerLayout radialpickerlayout = timePicker;
            String s = amText;
            if (radialpickerlayout != null && s != null)
            {
                radialpickerlayout.announceForAccessibility(s);
            }
            amPmHitspace.setContentDescription(amText);
            return;
        }
        if (i == 1)
        {
            amPmTextView.setText(pmText);
            RadialPickerLayout radialpickerlayout1 = timePicker;
            String s1 = pmText;
            if (radialpickerlayout1 != null && s1 != null)
            {
                radialpickerlayout1.announceForAccessibility(s1);
            }
            amPmHitspace.setContentDescription(pmText);
            return;
        } else
        {
            amPmTextView.setText(doublePlaceholderText);
            return;
        }
    }

    final void updateDisplay(boolean flag)
    {
        int i = 0;
        if (!flag && typedTimes.isEmpty())
        {
            int j = timePicker.currentHoursOfDay;
            int k = timePicker.currentMinutes;
            setHour(j, true);
            setMinute(k);
            if (!is24HourMode)
            {
                if (j >= 12)
                {
                    i = 1;
                }
                updateAmPmDisplay(i);
            }
            setCurrentItemShowing(timePicker.getCurrentItemShowing(), true, true, true);
            doneButton.setEnabled(true);
        } else
        {
            Boolean aboolean[] = new Boolean[2];
            aboolean[0] = Boolean.valueOf(false);
            aboolean[1] = Boolean.valueOf(false);
            int ai[] = getEnteredTime(aboolean);
            String s;
            String s1;
            if (aboolean[0].booleanValue())
            {
                s = "%02d";
            } else
            {
                s = "%2d";
            }
            if (aboolean[1].booleanValue())
            {
                s1 = "%02d";
            } else
            {
                s1 = "%2d";
            }
            if (ai[0] == -1)
            {
                s = doublePlaceholderText;
            } else
            {
                s = String.format(s, new Object[] {
                    Integer.valueOf(ai[0])
                }).replace(' ', placeholderText);
            }
            if (ai[1] == -1)
            {
                s1 = doublePlaceholderText;
            } else
            {
                s1 = String.format(s1, new Object[] {
                    Integer.valueOf(ai[1])
                }).replace(' ', placeholderText);
            }
            hourView.setText(s);
            hourSpaceView.setText(s);
            hourView.setTextColor(unselectedColor);
            minuteView.setText(s1);
            minuteSpaceView.setText(s1);
            minuteView.setTextColor(unselectedColor);
            if (!is24HourMode)
            {
                updateAmPmDisplay(ai[2]);
                return;
            }
        }
    }

    private class Node
    {

        public ArrayList children;
        public int legalKeys[];

        transient Node(int ai[])
        {
            legalKeys = ai;
            children = new ArrayList();
        }
    }


    private class DismissiblePicker
    {

        public abstract void dismiss();

        public abstract Dialog getDialog();
    }


    private class KeyboardListener
        implements android.view.View.OnKeyListener
    {

        private final TimePickerBaseDialog this$0;

        public final boolean onKey(View view, int i, KeyEvent keyevent)
        {
            if (keyevent.getAction() != 1)
            {
                break MISSING_BLOCK_LABEL_394;
            }
            keyevent = TimePickerBaseDialog.this;
            if (i == 111 || i == 4)
            {
                ((TimePickerBaseDialog) (keyevent)).picker.dismiss();
                return true;
            }
            if (i != 61) goto _L2; else goto _L1
_L1:
            if (((TimePickerBaseDialog) (keyevent)).inKbMode)
            {
                if (keyevent.isTypedTimeFullyLegal())
                {
                    keyevent.finishKbMode(true);
                }
                return true;
            }
              goto _L3
_L2:
            if (i == 66)
            {
                if (((TimePickerBaseDialog) (keyevent)).inKbMode)
                {
                    if (!keyevent.isTypedTimeFullyLegal())
                    {
                        return true;
                    }
                    keyevent.finishKbMode(false);
                }
                if (((TimePickerBaseDialog) (keyevent)).callback != null)
                {
                    ((TimePickerBaseDialog) (keyevent)).callback.onTimeSet$51666RRD5TGMSP3IDTKM8BR4C5Q6AT39DLIN0QB3DDIN4BRKD5MMABQIC5I6IOBCA1KM6QR5E9662UBFELQ3MIA955B0____0(((TimePickerBaseDialog) (keyevent)).timePicker.currentHoursOfDay, ((TimePickerBaseDialog) (keyevent)).timePicker.currentMinutes);
                }
                ((TimePickerBaseDialog) (keyevent)).picker.dismiss();
                return true;
            }
            if (i != 67) goto _L5; else goto _L4
_L4:
            if (((TimePickerBaseDialog) (keyevent)).inKbMode && !((TimePickerBaseDialog) (keyevent)).typedTimes.isEmpty())
            {
                i = keyevent.deleteLastTypedKey();
                RadialPickerLayout radialpickerlayout;
                if (i == keyevent.getAmOrPmKeyCode(0))
                {
                    view = ((TimePickerBaseDialog) (keyevent)).amText;
                } else
                if (i == keyevent.getAmOrPmKeyCode(1))
                {
                    view = ((TimePickerBaseDialog) (keyevent)).pmText;
                } else
                {
                    view = String.format("%d", new Object[] {
                        Integer.valueOf(TimePickerBaseDialog.getValFromKeyCode(i))
                    });
                }
                radialpickerlayout = ((TimePickerBaseDialog) (keyevent)).timePicker;
                view = String.format(((TimePickerBaseDialog) (keyevent)).deletedKeyFormat, new Object[] {
                    view
                });
                if (radialpickerlayout != null && view != null)
                {
                    radialpickerlayout.announceForAccessibility(view);
                }
                keyevent.updateDisplay(true);
            }
_L3:
            return false;
_L5:
            if (i != 7 && i != 8 && i != 9 && i != 10 && i != 11 && i != 12 && i != 13 && i != 14 && i != 15 && i != 16 && (((TimePickerBaseDialog) (keyevent)).is24HourMode || i != keyevent.getAmOrPmKeyCode(0) && i != keyevent.getAmOrPmKeyCode(1))) goto _L3; else goto _L6
_L6:
            if (!((TimePickerBaseDialog) (keyevent)).inKbMode)
            {
                if (((TimePickerBaseDialog) (keyevent)).timePicker == null)
                {
                    Log.e("TimePickerDialog", "Unable to initiate keyboard mode, TimePicker was null.");
                    return true;
                } else
                {
                    ((TimePickerBaseDialog) (keyevent)).typedTimes.clear();
                    keyevent.tryStartingKbMode(i);
                    return true;
                }
            }
            if (keyevent.addKeyIfLegal(i))
            {
                keyevent.updateDisplay(false);
            }
            return true;
            return false;
        }

        KeyboardListener()
        {
            this$0 = TimePickerBaseDialog.this;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final TimePickerBaseDialog this$0;

        public final void onClick(View view)
        {
            setCurrentItemShowing(0, true, false, true);
            hapticFeedbackController.tryVibrate();
        }

        _cls1()
        {
            this$0 = TimePickerBaseDialog.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final TimePickerBaseDialog this$0;

        public final void onClick(View view)
        {
            setCurrentItemShowing(1, true, false, true);
            hapticFeedbackController.tryVibrate();
        }

        _cls2()
        {
            this$0 = TimePickerBaseDialog.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        private final TimePickerBaseDialog this$0;

        public final void onClick(View view)
        {
            if (inKbMode && isTypedTimeFullyLegal())
            {
                finishKbMode(false);
            } else
            {
                hapticFeedbackController.tryVibrate();
            }
            if (callback != null)
            {
                callback.onTimeSet$51666RRD5TGMSP3IDTKM8BR4C5Q6AT39DLIN0QB3DDIN4BRKD5MMABQIC5I6IOBCA1KM6QR5E9662UBFELQ3MIA955B0____0(timePicker.currentHoursOfDay, timePicker.currentMinutes);
            }
            picker.dismiss();
        }

        _cls3()
        {
            this$0 = TimePickerBaseDialog.this;
            super();
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        private final TimePickerBaseDialog this$0;

        public final void onClick(View view)
        {
            int i;
            boolean flag = true;
            hapticFeedbackController.tryVibrate();
            view = timePicker;
            if (((RadialPickerLayout) (view)).currentHoursOfDay < 12)
            {
                i = 0;
            } else
            if (((RadialPickerLayout) (view)).currentHoursOfDay < 24)
            {
                i = 1;
            } else
            {
                i = -1;
            }
            if (i != 0) goto _L2; else goto _L1
_L1:
            i = ((flag) ? 1 : 0);
_L4:
            updateAmPmDisplay(i);
            timePicker.setAmOrPm(i);
            return;
_L2:
            if (i == 1)
            {
                i = 0;
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        _cls4()
        {
            this$0 = TimePickerBaseDialog.this;
            super();
        }
    }

}
