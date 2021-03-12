package com.example.languagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String P_LANG = "LANG";
    private SharedPreferences sp;
    private TextView tvlanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get widget preferences
        tvlanguage = findViewById(R.id.tvlanguage);

        //check for language preference
        sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String language = sp.getString(P_LANG, null);

        //if already set
        if(language!=null){
            //display the previously selected language
            tvlanguage.setText(language);
        }else {
            //else display dialog for selecting language
            selectLanguage();
        }


    }

    public void selectLanguage(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_menu_mapmode)
                .setTitle("Choose the language")
                .setMessage("Which language would you like?")
                .setPositiveButton("Vietnamese", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("Vietnamese");
                    }
                })
                .setNegativeButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("English");
                    }
                })
                .show();
    }


    /**
     *
     * @effects
     *  update the language preference
     *  update the textview
     */
    public void setLanguage(String language){
        //update the language preference
        sp.edit().putString(P_LANG, language).apply();

        //update the textview
        tvlanguage.setText(language);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnLangEn:
                setLanguage("English");
                break;

            case R.id.mnLangVi:
                setLanguage("Vietnamese");
                break;

            case R.id.mnClear:
                //clear the preference
//                sp.edit().putString(P_LANG, null).apply();
                sp.edit().clear().apply();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}