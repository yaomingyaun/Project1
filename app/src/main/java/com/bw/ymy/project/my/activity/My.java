package com.bw.ymy.project.my.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

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
import com.bw.ymy.project.my.bean.GRZL;
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

    @BindView(R.id.tui)
    Button tui;
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

    @OnClick({R.id.my_personal, R.id.my_footprin, R.id.my_circle, R.id.my_address, R.id.my_price, R.id.tx_icon, R.id.tui})
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
                getpop();
                break;
            case R.id.tui:
                Intent intent1 = new Intent(getContext(), MainActivity.class);
                startActivity(intent1);


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
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
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
            //  getUserAvatar(map);


        }
    }
}
