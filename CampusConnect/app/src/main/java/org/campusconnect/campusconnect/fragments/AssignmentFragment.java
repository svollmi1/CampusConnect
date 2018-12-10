package org.campusconnect.campusconnect.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import org.campusconnect.campusconnect.R;
import org.campusconnect.campusconnect.activities.AssignmentThirdActivity;
import org.campusconnect.campusconnect.activities.AssignmentFirstActivity;
import org.campusconnect.campusconnect.activities.AssignmentSecondActivity;

public class AssignmentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_assignment, container, false);
        super.onCreate(savedInstanceState);
        Button button1 = rootView.findViewById(R.id.btn1);
        Button button2 = rootView.findViewById(R.id.btn2);
        Button button3 = rootView.findViewById(R.id.btn3);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(AssignmentFragment.this.getActivity(),AssignmentFirstActivity.class);
                startActivity(int1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(AssignmentFragment.this.getActivity(),AssignmentSecondActivity.class);
                startActivity(int2);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int3 = new Intent(AssignmentFragment.this.getActivity(),AssignmentThirdActivity.class);
                startActivity(int3);
            }
        });
        return rootView;
    }
}
