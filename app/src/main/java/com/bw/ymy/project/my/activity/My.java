package com.bw.ymy.project.my.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.App.App;
import com.bw.ymy.project.MainActivity;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.my.activity1.AddressActivity;
import com.bw.ymy.project.my.activity1.MyCircleActivity;
import com.bw.ymy.project.my.activity1.MyFooterActivity;
import com.bw.ymy.project.my.activity1.MyPriceActivity;
import com.bw.ymy.project.my.activity1.MypersonalActivity;
import com.bw.ymy.project.my.bean.FileBean;
import com.bw.ymy.project.my.bean.GRZL;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bw.ymy.project.R.id.photo;

public class My extends Fragment  implements IView {


    @BindView(R.id.myname)
    TextView myname;

    @BindView(R.id.tx_icon)
    SimpleDraweeView tx_icon;

    @BindView(R.id.my_personal)
    TextView my_personal;

    @BindView(R.id.my_footprin)
    TextView my_footprin;

    @BindView(R.id.my_circle)
    TextView my_circle;

    @BindView(R.id.my_address)
    TextView my_address;

    @BindView(R.id.my_price)
    TextView my_price;


        PopupWindow popupWindow;
    IPresenter iPresenter;
    private final int REQUEST_PICK = 200;
    private final int REQUEST_CAMEAR = 100;
    private final int REQUEST_PICTRUE = 300;
    private final String PATH_FILE = Environment.getExternalStorageDirectory() + "/file.png";
    private final String path = Environment.getExternalStorageDirectory() + "/image.png";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.action_my, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //绑定
        ButterKnife.bind(this, view);
        iPresenter = new IPresenter(this);
        //获取昵称
        iPresenter.get(Apis.GRZLURL, GRZL.class);
    }

    @OnClick({R.id.my_personal, R.id.my_footprin, R.id.my_circle, R.id.my_address, R.id.my_price,R.id.tx_icon})
    public void setonclick(View v) {
        switch (v.getId()) {
            //我的个人资料
            case R.id.my_personal:
                Intent intent = new Intent(getContext(), MypersonalActivity.class);
                startActivity(intent);
                break;
            //我的足迹
            case R.id.my_footprin:
                Intent zuji = new Intent(getContext(), MyFooterActivity.class);
                startActivity(zuji);
                break;
            //我的圈子
            case R.id.my_circle:
                Intent circle = new Intent(getContext(), MyCircleActivity.class);
                startActivity(circle);
                break;

            //我的收货地址
            case R.id.my_address:
                Intent address = new Intent(getContext(), AddressActivity.class);
                startActivity(address);
                break;
            //我的钱包
            case R.id.my_price:
                Intent price = new Intent(getContext(), MyPriceActivity.class);
                startActivity(price);
                break;
            //点击pop头像
            case R.id.tx_icon:
                //判断权限
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    String[] mStatenetwork = new String[]{
                            //写的权限
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            //读的权限
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            //入网权限
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            //WIFI权限
                            Manifest.permission.ACCESS_WIFI_STATE,
                            //读手机权限
                            Manifest.permission.READ_PHONE_STATE,
                            //网络权限
                            Manifest.permission.INTERNET,
                            //相机
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_APN_SETTINGS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                    };
                    ActivityCompat.requestPermissions(getActivity(), mStatenetwork, 100);
                }

                getpop();
                break;
            default:
                break;
        }
    }

    //pop
    private void getpop() {

        View view = View.inflate(getContext(), R.layout.action_my_pop_item, null);
        TextView photo = view.findViewById(R.id.photo);
        TextView camera = view.findViewById(R.id.camera);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // popupWindow.setFocusable(true);
        //点击pop外部 隐藏
        popupWindow.setTouchable(true);
        //   popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(tx_icon, 50, 50);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_PICK);
                popupWindow.dismiss();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(intent, REQUEST_CAMEAR);
                popupWindow.dismiss();
            }
        });
    }
    private void getUserAvatar(Map<String,String> map) {
        iPresenter.getFile(Apis.SCTX,map,FileBean.class);
    }
    @Override
    public void onSuccess(Object data) {

        if (data instanceof GRZL) {
            GRZL grzl = (GRZL) data;
            //头像
            Uri uri = Uri.parse(grzl.getResult().getHeadPic());
            tx_icon.setImageURI(uri);
            //昵称
            String na = grzl.getResult().getNickName();
            myname.setText(na);
            iPresenter.get(Apis.GRZLURL, GRZL.class);
        }
        else  if(data instanceof FileBean)
        {
            FileBean fileBean= (FileBean) data;
            if(fileBean.getMessage().equals("上传成功"))
            {
                iPresenter.get(Apis.GRZLURL,GRZL.class);
                Uri uri=Uri.parse(fileBean.getHeadpath());
                   RoundingParams params = RoundingParams.asCircle();
                 tx_icon.getHierarchy().setRoundingParams(params);
                tx_icon.setImageURI(uri);

                Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(getContext(), "上传失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMEAR) {
            //打开裁剪
            Intent intent = new Intent("com.android.camera.action.CROP");
            //将图片设置给裁剪
            intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");
            //设置是否支持裁剪
            intent.putExtra("CROP", true);
            //设置宽高比
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //设置输出后图片大小
            intent.putExtra("outputX", 100);
            intent.putExtra("outputY", 100);
            //返回到data
            intent.putExtra("return-data", true);
            //启动
            startActivityForResult(intent, REQUEST_PICTRUE);
        }
        if(requestCode==REQUEST_PICK)
        {
            Intent intent=new Intent("com.android.camera.action.CROP");
            Uri uri=data.getData();
            intent.setDataAndType(uri,"image/*");
            //设置是否可裁剪
            intent.putExtra("CROP", true);
            //设置宽高比
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //设置输出
            intent.putExtra("outputX", 100);
            intent.putExtra("outputY", 100);
            //返回data
            intent.putExtra("return-data", true);
            startActivityForResult(intent,REQUEST_PICTRUE);
        }
        if (requestCode == REQUEST_PICTRUE) {
            Bitmap bitmap = data.getParcelableExtra("data");
            try {
                App.saveBitmap(bitmap, PATH_FILE, 50);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("TAG", e.getMessage());
            }
            // File file = new File(PATH_FILE);
            Map<String, String> map = new HashMap<>();
            map.put("image", PATH_FILE);
              getUserAvatar(map);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
