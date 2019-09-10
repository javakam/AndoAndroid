package com.improve.modules.widgets.tagcloud;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve.R;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

/**
 * AndroidTagCloud Demo https://github.com/whilu/AndroidTagView
 *
 * @author javakam
 * @data 2018-6-10 11:06:54
 */
public class MyTagActivity extends AppCompatActivity {

    private TagContainerLayout tagContainerLayout;
    private EditText editTagContent;
    private Button btnAddTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        List<String> list = new ArrayList<String>();
        list.add("China");
        list.add("USA");
        list.add("Austria");
        list.add("Japan");
        list.add("Sudan");
        list.add("Spain");
        list.add("UK");
        list.add("Germany");
        list.add("Niger");
        list.add("Poland");
        list.add("Norway");
        list.add("Uruguay");
        list.add("Brazil");

        setContentView(R.layout.activity_tagcloud);
        tagContainerLayout = (TagContainerLayout) findViewById(R.id.tag_container);
        editTagContent = (EditText) findViewById(R.id.edt_text_tag);
        btnAddTag = (Button) findViewById(R.id.btn_add_tag);

        //静态添加
//        tagContainerLayout.setTags(list);
        tagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
//                if (position < tagContainerLayout.getChildCount()) {
//                    tagContainerLayout.removeTag(position);
//                }
                Toast.makeText(MyTagActivity.this, "onTagClick " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTagLongClick(final int position, String text) {
            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(final int position) {
                Toast.makeText(MyTagActivity.this, "onTagCrossClick  " + position, Toast.LENGTH_SHORT).show();
                AlertDialog dialog = new AlertDialog.Builder(MyTagActivity.this)
                        .setTitle("long click")
                        .setMessage("You will delete this tag!" + " \n Content:  text ")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (position < tagContainerLayout.getChildCount()) {
                                    tagContainerLayout.removeTag(position);
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }
        });
        //动态添加tag
        btnAddTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editTagContent.getText().toString())) {
                    tagContainerLayout.addTag(editTagContent.getText().toString());
                }
                // Add tag in the specified position
//                mTagContainerLayout1.addTag(text.getText().toString(), 4);
            }
        });
    }

}
