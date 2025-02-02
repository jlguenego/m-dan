/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.orange.ease.dan.guide.accessibility.lvl2;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orange.ease.dan.R;
import com.orange.ease.dan.navFragments.OnNewFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Clément Roussillon on 12/02/16.
 */
@EFragment(R.layout.example_frag_lvl2)
public class ExFocusNav1Fragment extends Fragment {

    private String mTitle = "";
    private OnNewFragment mOnNewFragment;

    @ViewById(R.id.textViewTitleExample)
    public TextView mTextViewTitleExample;

    @ViewById(R.id.textViewDescriptionExample)
    public TextView mTextViewDescriptionExample;

    @ViewById(R.id.textViewTitleExempleAxsYes)
    public TextView mTextViewTitleExempleAxsYes;

    @ViewById(R.id.frameLayoutExampleAxsYes)
    public FrameLayout mFrameLayoutExampleAxsYes;

    @ViewById(R.id.textViewTitleExempleAxsNo)
    public TextView mTextViewTitleExempleAxsNo;

    @ViewById(R.id.frameLayoutExampleAxsNo)
    public FrameLayout mFrameLayoutExampleAxsNo;

    @ViewById(R.id.textViewOptionEnabled)
    public TextView mTextViewOptionEnabled;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.example_frag_lvl2, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mOnNewFragment = (OnNewFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnNewFragment");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTextViewTitleExample.setText(R.string.criteria_focusnav_ex1_title);
        mTextViewDescriptionExample.setText(R.string.criteria_focusnav_ex1_description);
        mTextViewTitleExempleAxsYes.setText(R.string.criteria_accessible_example);
        mTextViewOptionEnabled.setText(getString(R.string.criteria_template_option_focus));

        //
        // ACCESSIBILITY YES
        //
        TextView texteExempleAccessible = new TextView(getContext());
        texteExempleAccessible.setText(getString(R.string.criteria_focusnav_ex1_axs));
        LinearLayout lyaxs = new LinearLayout(getContext());
        float scale = getResources().getDisplayMetrics().density;
        lyaxs.setPadding((int) (15*scale + 0.5f), 0, (int) (15*scale + 0.5f), 0);
        lyaxs.setOrientation(LinearLayout.VERTICAL);
        lyaxs.addView(texteExempleAccessible);
        mFrameLayoutExampleAxsYes.addView(lyaxs);

        //
        // ACCESSIBILITY NO
        //
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout myView = (LinearLayout) inflater.inflate(R.layout.exfocusnav1_frag, null);
        LinearLayout ly = (LinearLayout) myView.findViewById(R.id.listViewAxsNoFocusNav);
        String[] items = getResources().getStringArray(R.array.criteria_focusnav_ex1_list);
        LinearLayout mFakeListView;
        for(String item : items){
            mFakeListView = (LinearLayout) inflater.inflate(R.layout.list_item_content_change, null);
            ((TextView) mFakeListView.findViewById(R.id.textCategory)).setText(item);
            ly.addView(mFakeListView);
        }

        mFrameLayoutExampleAxsNo.addView(myView);
    }

}
