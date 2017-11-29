/*
* Copyright 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.user0.cardviewwebjson;

import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.CLIPBOARD_SERVICE;


public class ContactFragment extends Fragment {

    private static final String TAG = ContactFragment.class.getSimpleName();

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contact_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv = getView().findViewById(R.id.text_contact);
        //tv.setText( tv.getText() + " CHADGHET " );
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ClipboardManager clipboardManager;
                ClipData clipData;
                clipboardManager= (ClipboardManager) getActivity()
                        .getBaseContext()
                        .getSystemService(CLIPBOARD_SERVICE);

                clipData = ClipData.newPlainText("text","+79262777547");
                clipboardManager.setPrimaryClip(clipData);
                Toast toast = Toast.makeText(getActivity().getBaseContext(),"telephone in Clipboard !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}

