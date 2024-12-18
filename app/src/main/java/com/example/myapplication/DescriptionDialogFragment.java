package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescriptionDialogFragment extends DialogFragment {

    private static final String ARG_DESCRIPTION = "description";

    public static DescriptionDialogFragment newInstance(String description) {
        DescriptionDialogFragment fragment = new DescriptionDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DESCRIPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description_dialog, container, false);

        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
        String description = getArguments().getString(ARG_DESCRIPTION);
        descriptionTextView.setText(description);

        return view;
    }
}