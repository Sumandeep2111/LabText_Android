package com.example.android_labtest_764942;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {
    PersonDatabase mDataBase;
    List<Person> personList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        listView = findViewById(R.id.lvpersons);
        personList = new ArrayList<>();
        mDataBase =   new PersonDatabase(this);
        loadPerson();

    }
    private void loadPerson() {
        Cursor cursor = mDataBase.getAllPerson();
        if(cursor.moveToFirst()){
            do {
                personList.add(new Person(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));


            }while (cursor.moveToNext());
            cursor.close();
            //show item in a listView
            //we use a custom adapter to show employees

            PersonAdapter personAdapter = new PersonAdapter(this, R.layout.list_layout_person, personList, mDataBase);
            listView.setAdapter(personAdapter);

        }
    }
}
