package com.mtha.mynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mtha.mynote.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMyNote;
    ArrayList<String> lsNote = new ArrayList<>();
    FloatingActionButton btnAddNote;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnAddNote = findViewById(R.id.fab);
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Goi activity moi
                Intent intent = new Intent(MainActivity.this,InsNoteActivity.class);
                //thuc hien mo activity moi thong qua phuong thuc startActivity
                openInsActivityForResults();


            }
        });

        lvMyNote = findViewById(R.id.lvNote);
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,lsNote);
        lvMyNote.setAdapter(adapter);
    }


    ActivityResultLauncher<Intent> insActivityResults =  registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();

                            //xu ly ket qua tra ve qua intent
                            String title = data.getStringExtra("title");
                            String content = data.getStringExtra("content");
                            MyNote myNote = new MyNote(title,content);
                        Toast.makeText(MainActivity.this,"my note: " + myNote.toString(),
                                Toast.LENGTH_LONG).show();
                            lsNote.add(myNote.toString());
                            adapter.notifyDataSetChanged();
                    }
                    if(result.getResultCode()==Activity.RESULT_CANCELED){
                        Toast.makeText(MainActivity.this,"abc",Toast.LENGTH_LONG).show();
                    }
                }
            });

    public void openInsActivityForResults(){
        Intent intent = new Intent(MainActivity.this, InsNoteActivity.class);
        insActivityResults.launch(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}