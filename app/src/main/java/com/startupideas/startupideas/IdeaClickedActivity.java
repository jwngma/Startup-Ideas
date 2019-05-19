package com.startupideas.startupideas;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IdeaClickedActivity extends AppCompatActivity {
    private static final String TAG = "IdeaClickedActivity";

    private String currentIdea;
    private TextView weltext;
    private Toolbar toolbar;
    private DatabaseReference RootRef;
    String link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_clicked);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        weltext=findViewById(R.id.weltext);
        if (getIntent().hasExtra("currentDataName")){

            currentIdea=getIntent().getExtras().get("currentDataName").toString();
            link=getIntent().getExtras().get("link").toString();

            getSupportActionBar().setTitle(currentIdea);

        }
        RootRef=FirebaseDatabase.getInstance().getReference().child(link);
        
        retrivedata();

    }

    private void retrivedata() {
        RootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    String currentdata_Values = dataSnapshot.child(currentIdea).getValue().toString();
                    weltext.setText(currentdata_Values);
                    weltext.setTextSize(16);
                    
                }
                else {
                    Log.d(TAG, "onDataChange: data does not exist");
                }
               
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.options,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.search_menu:
                break;


        }
        return true;
    }
}
