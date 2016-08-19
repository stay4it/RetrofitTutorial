package com.stay4it.retrofit.activity;

import android.os.Environment;
import android.widget.Button;

import com.stay4it.retrofit.R;
import com.stay4it.retrofit.api.APIWrapper;
import com.stay4it.retrofit.base.BaseActivity;
import com.stay4it.retrofit.bean.HttpResponse;
import com.stay4it.retrofit.util.TLog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MultipleTypeFileUploadActivity extends BaseActivity {

    @BindView(R.id.action_btn)
    Button mActionBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pic_and_text_upload;
    }

    @Override
    public void initView() {
        mActionBtn.setText("点我发送朋友圈");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.action_btn})
    public void uploadFile() {
        String path1 = Environment.getExternalStorageDirectory() + File.separator + "test.png";
        String path2 = Environment.getExternalStorageDirectory() + File.separator + "test.jpg";
        ArrayList<String> pathList = new ArrayList<>();
        pathList.add(path1);
        pathList.add(path2);

        Map<String, RequestBody> bodyMap = new HashMap<>();
        if(pathList.size() > 0) {
            for (int i = 0; i < pathList.size(); i++) {
                File file = new File(pathList.get(i));
                bodyMap.put("file"+i+"\"; filename=\""+file.getName(), RequestBody.create(MediaType.parse("image/png"),file));
            }
        }

        APIWrapper.getInstance().uploadMultipleTypeFile("jdsjlzx",bodyMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResponse<List<String>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        TLog.error("onError " + e.toString());
                    }

                    @Override
                    public void onNext(HttpResponse<List<String>> response) {
                        TLog.error("onNext " + response.toString());

                    }
                });

    }

}
