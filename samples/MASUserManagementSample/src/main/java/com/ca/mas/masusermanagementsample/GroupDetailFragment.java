/*
 * Copyright (c) 2016 CA. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package com.ca.mas.masusermanagementsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ca.mas.foundation.MASGroup;
import com.ca.mas.identity.group.MASMember;
import com.ca.mas.masusermanagementsample.adapter.DividerDecoration;
import com.ca.mas.masusermanagementsample.adapter.MemberRecyclerAdapter;

import java.util.List;

/**
 * A fragment representing a single Group detail screen.
 * This fragment is either contained in a {@link GroupListActivity}
 * in two-pane mode (on tablets) or a {@link GroupDetailActivity}
 * on handsets.
 */
public class GroupDetailFragment extends Fragment {
    private static final String TAG = GroupDetailFragment.class.getSimpleName();
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String GROUP_NAME = "group_name";
    private MASGroup mGroup;
    private MemberRecyclerAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GroupDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(GROUP_NAME)) {
            String groupId = getArguments().getString(GROUP_NAME);

            mGroup = GroupsManager.INSTANCE.getGroupById(groupId);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mGroup.getGroupName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.group_list, container, false);

        if (mGroup != null) {
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.group_list);
            if (recyclerView != null) {
                List<MASMember> members = mGroup.getMembers();
                if (members != null && members.size() > 0) {
                    Activity activity = getActivity();
                    mAdapter = new MemberRecyclerAdapter(activity, members);
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.addItemDecoration(new DividerDecoration(activity));
                }
            }
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}