package com.startupideas.startupideas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IndianStartupProceduresActivity extends AppCompatActivity {
    private static final String TAG = "IndianStartupProcedures";

    private Toolbar mtoolbar;
    private ListView listView;
    ArrayAdapter<String > arrayAdapter;
    ArrayList<String > list_of_ideas= new ArrayList<>();
    private DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_startup_procedures);


        mtoolbar=findViewById(R.id.business_idea_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Business Ideas ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RootRef=FirebaseDatabase.getInstance().getReference();

        initListView();

    }

    private void initListView() {
        listView=findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list_of_ideas);
        listView.setAdapter(arrayAdapter);

        RetriveAlldata();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String currentDataName=parent.getItemAtPosition(position).toString();

                Intent IdeaIntent=new Intent(IndianStartupProceduresActivity.this,IdeaClickedActivity.class);
                IdeaIntent.putExtra("currentDataName",currentDataName);
                IdeaIntent.putExtra("link","category/Indian Startup Procedures");
                startActivity(IdeaIntent);
            }
        });
    }

    private void RetriveAlldata() {

        RootRef.child("category").child("Indian Startup Procedures").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<>();
                Iterator iterator = dataSnapshot.getChildren().iterator();

                while (iterator.hasNext()) {
                    set.add(((DataSnapshot) iterator.next()).getKey());

                }
                list_of_ideas.clear();
                list_of_ideas.addAll(set);
                arrayAdapter.notifyDataSetChanged();

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
