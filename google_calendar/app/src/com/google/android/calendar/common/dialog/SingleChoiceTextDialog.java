// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.dialog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.widget.ListAdapter;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.common.dialog:
//            SingleChoiceDialog, SingleIntegerChoiceTextDialog, SingleParcelableChoiceTextDialog

public abstract class SingleChoiceTextDialog extends SingleChoiceDialog
{

    private ArrayList listItems;
    private ArrayList values;

    public SingleChoiceTextDialog()
    {
    }

    public static SingleChoiceTextDialog newIntegerBasedInstance(ArrayList arraylist, ArrayList arraylist1, int i, Fragment fragment, int j)
    {
        SingleIntegerChoiceTextDialog singleintegerchoicetextdialog = new SingleIntegerChoiceTextDialog();
        singleintegerchoicetextdialog.listItems = arraylist;
        singleintegerchoicetextdialog.values = arraylist1;
        singleintegerchoicetextdialog.selectedItem = i;
        singleintegerchoicetextdialog.setTargetFragment(fragment, j);
        return singleintegerchoicetextdialog;
    }

    public static SingleChoiceTextDialog newParcelableBaseInstance(ArrayList arraylist, ArrayList arraylist1, int i, Fragment fragment, int j)
    {
        SingleParcelableChoiceTextDialog singleparcelablechoicetextdialog = new SingleParcelableChoiceTextDialog();
        singleparcelablechoicetextdialog.listItems = arraylist;
        singleparcelablechoicetextdialog.values = arraylist1;
        singleparcelablechoicetextdialog.selectedItem = i;
        singleparcelablechoicetextdialog.setTargetFragment(fragment, j);
        return singleparcelablechoicetextdialog;
    }

    protected final ListAdapter createListAdapter(int i)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        return new SingleChoiceDialogTextAdapter(((Context) (obj)), listItems, i);
    }

    protected final Object getValue(int i)
    {
        return values.get(i);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            listItems = bundle.getStringArrayList("single_choice_text_dialog_items");
            values = restoreValuesFromInstanceState(bundle);
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putStringArrayList("single_choice_text_dialog_items", listItems);
        saveValuesToInstanceState(bundle, values);
        super.onSaveInstanceState(bundle);
    }

    protected abstract ArrayList restoreValuesFromInstanceState(Bundle bundle);

    protected abstract void saveValuesToInstanceState(Bundle bundle, ArrayList arraylist);

    private class SingleChoiceDialogTextAdapter extends BaseAdapter
    {

        private final LayoutInflater inflater;
        private final int selectedItem;
        private final List strings;

        public final int getCount()
        {
            return strings.size();
        }

        public final Object getItem(int i)
        {
            return strings.get(i);
        }

        public final long getItemId(int i)
        {
            return (long)i;
        }

        public final View getView(int i, View view, ViewGroup viewgroup)
        {
            boolean flag = false;
            View view1 = view;
            class ViewHolder
            {

                public final ImageView check;
                public final TextView text;

                public ViewHolder(ViewGroup viewgroup)
                {
                    text = (TextView)viewgroup.findViewById(0x7f100042);
                    check = (ImageView)viewgroup.findViewById(0x7f100148);
                }
            }

            if (view == null)
            {
                view1 = inflater.inflate(0x7f05015e, viewgroup, false);
                view1.setTag(new ViewHolder((ViewGroup)view1));
            }
            view = (ViewHolder)view1.getTag();
            viewgroup = (String)strings.get(i);
            Resources resources;
            int j;
            if (i == selectedItem)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            ((ViewHolder) (view)).text.setText(viewgroup);
            viewgroup = ((ViewHolder) (view)).text;
            resources = ((ViewHolder) (view)).text.getResources();
            if (i != 0)
            {
                j = 0x7f0d01d7;
            } else
            {
                j = 0x7f0d021e;
            }
            viewgroup.setTextColor(resources.getColor(j));
            view = ((ViewHolder) (view)).check;
            if (i != 0)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            view.setVisibility(i);
            return view1;
        }

        public SingleChoiceDialogTextAdapter(Context context, List list, int i)
        {
            inflater = LayoutInflater.from(context);
            strings = list;
            selectedItem = i;
        }
    }

}
