package com.example.sqlitedbs;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbh;
    EditText ed1,ed2,ed3,ed4,ed5;
    Button add,view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbh = new DatabaseHelper(this);
        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.email);
        ed3 = findViewById(R.id.phonenum);
        ed4 = findViewById(R.id.desc);
        ed5 = findViewById(R.id.degree);
        add = findViewById(R.id.add);
        view = findViewById(R.id.view);

        //add.getBackground().setAlpha(60);
       // view.getBackground().setAlpha(60);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = dbh.insertion(ed1.getText().toString(),
                        ed2.getText().toString(),
                        ed3.getText().toString(),
                        ed4.getText().toString(),
                        ed5.getText().toString());

                if(isInserted == true)
                {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                    ed5.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = dbh.showData();
                if(res.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this, "Error, No Entries Found!", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("Id :"+ res.getString(0)+ " \n ");
                    buffer.append("Name :"+ res.getString(1)+ " \n ");
                    buffer.append("Email :"+ res.getString(2)+ " \n ");
                    buffer.append("Phone_Number :"+ res.getString(3)+ " \n ");
                    buffer.append("Description :"+ res.getString(4)+ " \n ");
                    buffer.append("Degree :"+ res.getString(5)+ " \n ");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Data Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }


        });
    }
}