package com.example.pradeep.mycollage.staff.userinterface.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pradeep.mycollage.R;

/**
 * Created by pradeep on 21/07/2016.
 */
public class NotesFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String TAG="NotesFragment";

    Spinner mClassSpinner,mSubjectSpinner;
    EditText mTitle,mProvideLinkEditText,mVedioUrlEditText;
    Button mAttachFileButton,mCancleButton;
    TextView mAttachFileTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.staff_fragment_notes, container, false);
        backButtonPressed(rootView);

        initializeViews(rootView);
        setOnClick();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Notes");

    }
    //Back pressed Logic for fragment
    private void backButtonPressed(View rootView) {
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        //getActivity().finish();
                        Fragment fragment = new HomeFragment();
                        getFragmentManager().beginTransaction().replace(R.id.main_contains,fragment).commit();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void setOnClick() {
        mAttachFileButton.setOnClickListener(this);
        mCancleButton.setOnClickListener(this);
        mSubjectSpinner.setOnItemSelectedListener(this);
        mClassSpinner.setOnItemSelectedListener(this);
    }

    private void initializeViews(View rootView) {
        mClassSpinner= (Spinner) rootView.findViewById(R.id.Notes_class_spinner);
        mSubjectSpinner=(Spinner) rootView.findViewById(R.id.Notes_subject_spinner);
        mTitle=(EditText) rootView.findViewById(R.id.Notes_title_edit_text);
        mAttachFileButton=(Button) rootView.findViewById(R.id.Notes_Attach_file_button);
        mAttachFileTextView= (TextView) rootView.findViewById(R.id.Notes_attach_file_text_view);
        mProvideLinkEditText= (EditText) rootView.findViewById(R.id.Notes_provide_link_edit_text);
        mVedioUrlEditText= (EditText) rootView.findViewById(R.id.Notes_provide_link_edit_text);
        mCancleButton=(Button) rootView.findViewById(R.id.Notes_cancle_button);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // In fragment class callback
        String realPath;
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK && data != null)

        {
            if (data.getData().toString().contains("content://")) {
                realPath = getRealPathFromURI_API11to18(data.getData());
                mAttachFileTextView.setText(realPath);
            } else {
                realPath = data.getData().toString();
                String filenameArrayFormPhone[] = realPath.split("//"); // this remove file:// from string
                realPath = filenameArrayFormPhone[1];
                String[] uploadedFileName = realPath.split("/");
                String newFormularyFileName = uploadedFileName[uploadedFileName.length - 1];

                String filenameArray[] = newFormularyFileName.split("\\.");
                String extension = filenameArray[filenameArray.length - 1];
                mAttachFileTextView.setText(newFormularyFileName);
            }
        }
    }

    private String getRealPathFromURI_API11to18(Uri data) {
        String mimeType = getActivity().getContentResolver().getType(data);
        Cursor returnCursor = getActivity().getContentResolver().query(data, null, null, null, null);
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
        returnCursor.moveToFirst();
        String result = returnCursor.getString(nameIndex);
        return result;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Notes_Attach_file_button:
                int reqCode = 1;
                Intent action = new Intent(Intent.ACTION_GET_CONTENT);
                action = action.setType("application/*");
                this.startActivityForResult(Intent.createChooser(action,"Select file"), reqCode);
                break;
            case R.id.Notes_cancle_button:
                clearControlText();
                FragmentManager fragmentManager = getFragmentManager();
                HomeFragment fragment=new HomeFragment();
                fragmentManager.beginTransaction().replace(R.id.main_contains,fragment).commit();
                break;

            default:
                break;
        }
    }

    private void clearControlText() {
        mClassSpinner.setSelection(0);
        mSubjectSpinner.setSelection(0);
        mTitle.setText(" ");
        mAttachFileTextView.setText("");
        mProvideLinkEditText.setText("");
        mVedioUrlEditText.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
