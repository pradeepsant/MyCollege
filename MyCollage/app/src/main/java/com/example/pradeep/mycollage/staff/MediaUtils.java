package com.example.pradeep.mycollage.staff;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by pradeep on 17/08/2016.
 */
public class MediaUtils {
    /**
     *  Name of the profile pic
     */
    public static final String PROFILE_PIC_NAME = "profile";

    /**
     *  Name of the profile pic
     */
    //TODO need to check why only .jpg extension
    public static final String PROFILE_PIC_EXTENSION = ".jpg";
    /**
     * This function returns the profile pic path.
     * @return String of the profile path
     */
    public static String getProfilePicPath(Context mContext) {
        String profilePicPath = mContext.getExternalFilesDir(null) + File.separator +
                PROFILE_PIC_NAME + PROFILE_PIC_EXTENSION;
        return profilePicPath;
    }

    /**
     * This function saves the Bitmap to the profile pic path.
     * @param profileBitmap Bitmap to be saved
     */
    public static void saveProfilePic(Context context, Bitmap profileBitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(getProfilePicPath(context));
            profileBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }


}
