package com.example.pradeep.mycollage.staff.userinterface.fragments;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.MediaUtils;
import com.example.pradeep.mycollage.staff.Utils;

import java.io.File;
import java.util.Calendar;

public class AssignmentFragment extends DialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String TAG = "AssignmentFragment";

    Spinner mClassSpinner, mSubjectSpinner;
    EditText mAssignmentNumber, mAssignmentNameEditText;
    Button mAttachFileButton, mCapturePhotoButton;
    ImageView mImageView;
    TextView mAttachedFileNameTextView, mSubmissionDateTextView;
    private ImageButton mImageButton;
    private Calendar mCalender;
    private int mDay, mMonth, mYear;
    private DatePickerDialog mDatePickerDialog;
    private DatePicker mDatePicker;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.staff_fragment_assigment, container, false);
        backButtonPressed(rootView);
        initializeViews(rootView);
        setOnClick();
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Assignment");

    }

    private void backButtonPressed(View rootView) {
        //Back pressed Logic for fragment
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        //getActivity().finish();
                        Fragment fragment = new HomeFragment();
                        getFragmentManager().beginTransaction().replace(R.id.main_contains, fragment).commit();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void setOnClick() {
        mImageButton.setOnClickListener(this);
        mAttachFileButton.setOnClickListener(this);
        mCapturePhotoButton.setOnClickListener(this);
        mClassSpinner.setOnItemSelectedListener(this);
        mSubjectSpinner.setOnItemSelectedListener(this);
    }


    private void initializeViews(View rootView) {
        mClassSpinner = (Spinner) rootView.findViewById(R.id.Assignment_class_spinner);
        mSubjectSpinner = (Spinner) rootView.findViewById(R.id.Assignment_subject_spinner);
        mAssignmentNumber = (EditText) rootView.findViewById(R.id.Assignment_number_edit_text);
        mImageButton = (ImageButton) rootView.findViewById(R.id.Assignment_calender_image_button);
        mSubmissionDateTextView = (TextView) rootView.findViewById(R.id.Assignment_submission_date_text_view);
        mAssignmentNameEditText = (EditText) rootView.findViewById(R.id.Assignment_name_edit_text);
        mAttachFileButton = (Button) rootView.findViewById(R.id.Assignment_Attach_file_button);
        mAttachedFileNameTextView = (TextView) rootView.findViewById(R.id.Assignment_Attached_file_text_view);
        mImageView = (ImageView) rootView.findViewById(R.id.Assignment_Image_View);
        mCapturePhotoButton = (Button) rootView.findViewById(R.id.Assignment_Capture_photo);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // In fragment class callback
        String realPath;
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK && data != null)

        {
            if (data.getData().toString().contains("content://")) {
                realPath = getRealPathFromURI_API11to18(data.getData());
                mAttachedFileNameTextView.setText(realPath);
            } else {
                realPath = data.getData().toString();
                String filenameArrayFormPhone[] = realPath.split("//"); // this remove file:// from string
                realPath = filenameArrayFormPhone[1];
                String[] uploadedFileName = realPath.split("/");
                String newFormularyFileName = uploadedFileName[uploadedFileName.length - 1];

                String filenameArray[] = newFormularyFileName.split("\\.");
                String extension = filenameArray[filenameArray.length - 1];
                mAttachedFileNameTextView.setText(newFormularyFileName);
            }
        } else if (requestCode == 2 && resultCode == getActivity().RESULT_OK) {

            String path = MediaUtils.getProfilePicPath(getActivity());
            //3
            final Bitmap bitmap = Utils.getImage(getActivity(), path, 400, 400);
            Matrix matrix = new Matrix();
            //4
            matrix.postRotate(Utils.getImageOrientation(path));
            Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            MediaUtils.saveProfilePic(getActivity(), rotatedBitmap);
            imageSelected(path);
        }
    }

    private void imageSelected(String path) {
        String uriImage = Uri.parse(path).toString();

        //TODO This file should be uploaded to server
        File file = new File(uriImage);
        Bitmap bitmap = Utils.getImage(getActivity(), uriImage, 400, 400);
        mImageView.setImageBitmap(bitmap);

        /*Bitmap bitmap=(BitmapFactory.decodeFile(path));
        mImageView.setImageBitmap(bitmap);*/
    }


    public String getRealPathFromURI_API11to18(Uri data) {
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
            case R.id.Assignment_calender_image_button:
                showCalender();
                break;

            case R.id.Assignment_Attach_file_button:
                int reqCode = 1;
                Intent action = new Intent(Intent.ACTION_GET_CONTENT);
                action = action.setType("application/*");
                this.startActivityForResult(Intent.createChooser(action, "Select file"), reqCode);
                break;

            case R.id.Assignment_Capture_photo:

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    try {
                        //1									//2
                        File bitmap = Utils.createImageFile(MediaUtils.PROFILE_PIC_NAME, MediaUtils.PROFILE_PIC_EXTENSION, getActivity());
                        Uri uri = Uri.fromFile(bitmap);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(takePictureIntent, 2);
                    } catch (Exception e) {

                    }
                }

                break;

            default:
                break;
        }
    }

    private void showCalender() {
        mCalender = Calendar.getInstance();
        mDay = mCalender.get(Calendar.DAY_OF_MONTH);
        mMonth = mCalender.get(Calendar.MONTH);
        mYear = mCalender.get(Calendar.YEAR);
        mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                mSubmissionDateTextView.setText(dayOfMonth + "-"
                        + (monthOfYear + 1) + "-" + year);
            }
        }, mYear, mMonth, mDay);
        //mDatePickerDialog.setTitle("Select Date");
        /***To disable past date in date picker***/
        mDatePicker = mDatePickerDialog.getDatePicker();
        mDatePicker.setMinDate(mCalender.getTimeInMillis());
        /**
         * OR
         */
        //d.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        mDatePickerDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
