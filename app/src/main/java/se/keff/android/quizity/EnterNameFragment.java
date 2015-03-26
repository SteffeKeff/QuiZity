package se.keff.android.quizity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Steffe on 15-03-26.
 */
public final class EnterNameFragment extends DialogFragment {

    View rootView;

    public EnterNameFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_enter_name, container, false);


        return rootView;
    }


//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//
//        builder.setView(inflater.inflate(R.layout.fragment_enter_name, null))
//                .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        TextView name = (TextView) rootView.findViewById(R.id.name);
//                        Toast.makeText(getActivity(), name.getText().toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        return builder.create();
//    }
}
