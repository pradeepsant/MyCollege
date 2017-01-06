package com.example.pradeep.mycollage.staff.userinterface.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.MediaUtils;
import com.example.pradeep.mycollage.staff.Utils;
import com.example.pradeep.mycollage.staff.userinterface.activities.ChangePasswordActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Staff profile .
 */
public class MyProfileFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    public static final String TAG = "MyProfileFragment";
    public Button mClearButton, mUpload, mTakephoto;
    Spinner mDepartmentSpinner;
    ImageView ivHeaderPhoto;

    public EditText mFirstNameEditText, mMiddleNameEditText, mLastNameEditText, mEmailIdEidtText, mMobileNumberEditText;

    public MyProfileFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.staff_fragment_my_profile, container, false);
        backButtonPressed(rootView);
        initializeViews(rootView);
        mClearButton.setOnClickListener(this);
        mDepartmentSpinner.setOnItemSelectedListener(this);
        mUpload = (Button) rootView.findViewById(R.id.upload_gallary_button);
        mTakephoto = (Button) rootView.findViewById(R.id.take_photo_button);
        mUpload.setOnClickListener(this);
        mTakephoto.setOnClickListener(this);
        return rootView;

    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("MyProfile");

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

    private void initializeViews(View rootView) {
        mClearButton = (Button) rootView.findViewById(R.id.Clear_button);
        mFirstNameEditText = (EditText) rootView.findViewById(R.id.First_Name_Edit_Text);
        mMiddleNameEditText = (EditText) rootView.findViewById(R.id.Middle_Name_Edit_Text);
        mLastNameEditText = (EditText) rootView.findViewById(R.id.Last_Name_Edit_Text);
        mEmailIdEidtText = (EditText) rootView.findViewById(R.id.Email_Id_Edit_Text);
        mMobileNumberEditText = (EditText) rootView.findViewById(R.id.Mobile_Number_Edit_Text);
        mDepartmentSpinner = (Spinner) rootView.findViewById(R.id.Department_spinner);
        ivHeaderPhoto = (ImageView) rootView.findViewById(R.id.image_view);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_gallary_button:
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);
                break;


            case R.id.take_photo_button:
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

            case R.id.Clear_button:
                mFirstNameEditText.setText("");
                mMiddleNameEditText.setText("");
                mLastNameEditText.setText("");
                mEmailIdEidtText.setText("");
                mMobileNumberEditText.setText("");
                mDepartmentSpinner.setSelection(0);
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            String decode=decodeFile(picturePath,200,200);

            ivHeaderPhoto.setImageBitmap(BitmapFactory.decodeFile(decode));
            cursor.close();
        } else if (requestCode == 2 && resultCode == getActivity().RESULT_OK) {

            String path = MediaUtils.getProfilePicPath(getActivity());
            //3
            final Bitmap bitmap = Utils.getImage(getActivity(), path, 100, 100);
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
        Bitmap bitmap = Utils.getImage(getActivity(), uriImage, 50, 50);
        ivHeaderPhoto.setImageBitmap(bitmap);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.staff_menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.change_password_item:
                changePasswordMethod();
                return true;

            case R.id.help_item:
                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changePasswordMethod() {
        Intent i = new Intent(getActivity(), ChangePasswordActivity.class);
        startActivity(i);
    }

    private void showHelp() {

        Toast.makeText(getActivity(), "Help", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // String str= (String) parent.getItemAtPosition(position);
        //Toast.makeText(getActivity(), "Selected Item:-"+ str, Toast.LENGTH_LONG).show();
        //Toast.makeText(getActivity(), DataList.[position], Toast.LENGTH_LONG).show();


    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private String decodeFile(String path, int DESIREDWIDTH, int DESIREDHEIGHT) {
        String strMyImagePath = null;
        Bitmap scaledBitmap = null;

        // Part 1: Decode image
        Bitmap unscaledBitmap = Utils.decodeFile(path, DESIREDWIDTH, DESIREDHEIGHT, Utils.ScalingLogic.FIT);

        if (!(unscaledBitmap.getWidth() <= DESIREDWIDTH && unscaledBitmap.getHeight() <= DESIREDHEIGHT)) {
            // Part 2: Scale image
            scaledBitmap = Utils.createScaledBitmap(unscaledBitmap, DESIREDWIDTH, DESIREDHEIGHT, Utils.ScalingLogic.FIT);
        } else {
            unscaledBitmap.recycle();
            return path;
        }

        // Store to tmp file

        String extr = Environment.getExternalStorageDirectory().toString();
        File mFolder = new File(extr + "/TMMFOLDER");
        if (!mFolder.exists()) {
            mFolder.mkdir();
        }

        String s = "tmp.png";

        File f = new File(mFolder.getAbsolutePath(), s);

        strMyImagePath = f.getAbsolutePath();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            scaledBitmap.recycle();
        } catch (Throwable e) {
        }

        if (strMyImagePath == null) {
            return path;
        }
        return strMyImagePath;
    }


}
