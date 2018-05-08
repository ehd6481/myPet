package com.example.mypet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.app.Activity;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.AdapterView;
import android.app.DatePickerDialog;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.List;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


public class CatActivity extends Activity implements View.OnClickListener {
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_IMAGE = 2;
    private Uri mImageCaptureUri;
    private ImageView iv_UserPhoto;
    private int id_view;
    private String absoultePath;
    //날짜
    int mYear, mMonth, mDay, mHour, mMinute;
    TextView mTxtDate1;
    TextView mTxtDate2;
    TextView mTxtDate3;
    TextView mTxtDate4;
    TextView mTxtDate;

    //Spinner


    //private DB_Maneger dbmanger;

    //DB저장?
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        //dbmanger = new DB_Manger();
        iv_UserPhoto = (ImageView) this.findViewById(R.id.user_image);
        Button btn_agreeJoin = (Button) this.findViewById(R.id.btn_UploadPicture);
        btn_agreeJoin.setOnClickListener(this);
        //날짜
        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언
        mTxtDate1 = (TextView) findViewById(R.id.txtdate1);
        mTxtDate2 = (TextView) findViewById(R.id.txtdate2);
        mTxtDate3 = (TextView) findViewById(R.id.txtdate3);
        mTxtDate4 = (TextView) findViewById(R.id.txtdate4);
        mTxtDate = (TextView) findViewById(R.id.txtdate);
        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow1();//화면에 텍스트뷰에 업데이트 해줌
        UpdateNow2();
        UpdateNow3();
        UpdateNow4();
        UpdateNow();

        //spinner
        Spinner spinner = (Spinner) findViewById(R.id.back7);//send.xml의 스피너 아이디
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.back, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }


    //날짜대화상자
    public void mOnClick(View v) {
        switch (v.getId()) {
            //날짜 대화상자 버튼이 눌리면 대화상자를 보여줌
            case R.id.btnchangedate1:
                //여기서 리스너도 등록함
                new DatePickerDialog(CatActivity.this, mDateSetListener1, mYear,
                        mMonth, mDay).show();
                break;
            case R.id.btnchangedate2:
                //여기서 리스너도 등록함
                new DatePickerDialog(CatActivity.this, mDateSetListener2, mYear,
                        mMonth, mDay).show();
                break;
            case R.id.btnchangedate3:
                //여기서 리스너도 등록함
                new DatePickerDialog(CatActivity.this, mDateSetListener3, mYear,
                        mMonth, mDay).show();
                break;
            case R.id.btnchangedate4:
                //여기서 리스너도 등록함
                new DatePickerDialog(CatActivity.this, mDateSetListener4, mYear,
                        mMonth, mDay).show();
                break;
            case R.id.txtdate:
                //여기서 리스너도 등록함
                new DatePickerDialog(CatActivity.this, txtdate, mYear,
                        mMonth, mDay).show();
                break;
        }
    }

    DatePickerDialog.OnDateSetListener mDateSetListener1 =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    //사용자가 입력한 값을 가져온뒤
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    //텍스트뷰의 값을 업데이트함
                    UpdateNow1();
                }
            };
    DatePickerDialog.OnDateSetListener mDateSetListener2 =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    //사용자가 입력한 값을 가져온뒤
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    //텍스트뷰의 값을 업데이트함
                    UpdateNow2();
                }
            };
    DatePickerDialog.OnDateSetListener mDateSetListener3 =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    //사용자가 입력한 값을 가져온뒤
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    //텍스트뷰의 값을 업데이트함
                    UpdateNow3();
                }
            };
    DatePickerDialog.OnDateSetListener mDateSetListener4 =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    //사용자가 입력한 값을 가져온뒤
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    //텍스트뷰의 값을 업데이트함
                    UpdateNow4();
                }
            };
    DatePickerDialog.OnDateSetListener txtdate =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    //사용자가 입력한 값을 가져온뒤
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    //텍스트뷰의 값을 업데이트함
                    UpdateNow();
                }
            };

    void UpdateNow1() {
        mTxtDate1.setText(String.format("%d/%d/%d", mYear,
                mMonth + 1, mDay));
    }

    void UpdateNow2() {
        mTxtDate2.setText(String.format("%d/%d/%d", mYear,
                mMonth + 1, mDay));
    }

    void UpdateNow3() {
        mTxtDate3.setText(String.format("%d/%d/%d", mYear,
                mMonth + 1, mDay));
    }

    void UpdateNow4() {
        mTxtDate4.setText(String.format("%d/%d/%d", mYear,
                mMonth + 1, mDay));
    }

    void UpdateNow() {
        mTxtDate.setText(String.format("%d/%d/%d", mYear,
                mMonth + 1, mDay));
    }


    //사진찍기
    public void doTackPhotoAction() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //임시로 사용할 파일의 경로를 생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    //앨범에서 이미지
    public void doTakeAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case PICK_FROM_ALBUM: {
                mImageCaptureUri = data.getData();
                Log.d("SmartWheel", mImageCaptureUri.getPath().toString());
            }
            case PICK_FROM_CAMERA: {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 200);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_IMAGE);
                break;
            }
            case CROP_FROM_IMAGE: {
                if (resultCode != RESULT_OK) {
                    return;
                }
                final Bundle extras = data.getExtras();
                //CROP 된 이미지를 저장하기 위한 FILE경로
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmartWheel/" + System.currentTimeMillis() + ".jpg";
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    iv_UserPhoto.setImageBitmap(photo);
                    storeCropImage(photo, filePath);
                    break;
                }
                File f = new File(mImageCaptureUri.getPath());
                if (f.exists()) {
                    f.delete();
                }
            }
        }
    }

    public void onClick(View v) {
        id_view = v.getId();
        if (v.getId() == R.id.btn_UploadPicture) {
            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    doTackPhotoAction();
                }
            };
            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    doTakeAlbumAction();
                }
            };
            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
            new AlertDialog.Builder(this)
                    .setTitle("업로드할 이미지 선택")
                    .setPositiveButton("사진촬영", cameraListener)
                    .setNeutralButton("앨범선택", albumListener)
                    .setNegativeButton("취소", cancelListener)
                    .show();
        }
    }

    private void storeCropImage(Bitmap bitmap, String filePath) {
        //Smart Wheel폴더 생성해서 이미지 저장 방식
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmartWheel";
        File directory_SmartWheel = new File(dirPath);
        if (!directory_SmartWheel.exists()) directory_SmartWheel.mkdir();
        File copyFile = new File(filePath);
        BufferedOutputStream out = null;
        try {
            copyFile.createNewFile();
            out = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(copyFile)));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}