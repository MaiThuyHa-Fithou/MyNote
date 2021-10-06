package com.mtha.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsNoteActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etTitle, etContent;
    Button btnSave, btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_note);
        getViews();
    }

    private void getViews(){
        etContent = findViewById(R.id.etContent);
        etTitle = findViewById(R.id.etTile);
        btnSave = findViewById(R.id.btnSave);
        btnClose = findViewById(R.id.btnClose);
        //dang ky xu ly su kien cho cac nut lenh
        btnSave.setOnClickListener(this::onClick);
        btnClose.setOnClickListener(this::onClick);
    }

    private MyNote getMyNote(){
        return new MyNote(etTitle.getText().toString(),etContent.getText().toString());
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnClose:
                setResult(RESULT_CANCELED);
                //khong co xu ly du lieu
                finish();
                break;
            case R.id.btnSave:
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                Intent intentResult = new Intent();
                //put gia tri truyen ve thong qua phuong thuc putExtra
                intentResult.putExtra("title", title);
                intentResult.putExtra("content", content);
                //truyen ket qua ve cho activity main thong qua phuong thuc setResult
                setResult(RESULT_OK,intentResult);
                //dong activity nay
                finish();
                break;
            default:
                break;
        }
    }
}