package com.example.wren.chancemarley;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Wren on 11/7/2016.
 */

public class RoomListFragment extends Fragment {
    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.roomlist_fragment, parent, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        listView = (ListView) view.findViewById(R.id.listview);
        editText = (EditText) view.findViewById(R.id.txtsearch);
        initList();
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    // reset listview
                    initList();
                } else {
                    // perform search
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }

    public void searchItem(String textToSearch) {

        for (String item : items) {
            if (!item.contains(textToSearch)) {
                listItems.remove(item);
            }
        }

        adapter.notifyDataSetChanged();

    }

    public void initList() {
        items = new String[]{"Olin 101", "Olin 102", "Olin 103",
                "Olin 104", "Olin 105", "RKC 100", "RKC 107"
                , "RKC 200"};
        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(super.getContext(), R.layout.list_item, R.id.txtitem, listItems);
        listView.setAdapter(adapter);

    }
}



