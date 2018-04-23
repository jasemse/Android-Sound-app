package com.example.jasemse.apkamuzyczna;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    public static final int sound1 = R.raw.fx1;
    public static final int sound2 = R.raw.fx2;
    public static final int sound3 = R.raw.fx3;
    public static final int sound4 = R.raw.fx4;
    public static final int sound5 = R.raw.fx5;
    public static final int sound6 = R.raw.fx6;

    Button Dzwiek1;
    Button Dzwiek2;
    Button Dzwiek3;
    Button Dzwiek4;
    Button Dzwiek5;
    Button Dzwiek6;

    static final int READ_BLOCK_SIZE = 100;

    String kolejnoscZagraniaDzwiekow ="";

    public void playSound(Context context, int soundID) {
        MediaPlayer mp = MediaPlayer.create(context, soundID);
        mp.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dzwiek1 = (Button) findViewById(R.id.buttonSound1);
        Dzwiek2 = (Button) findViewById(R.id.buttonSound2);
        Dzwiek3 = (Button) findViewById(R.id.buttonSound3);
        Dzwiek4 = (Button) findViewById(R.id.buttonSound4);
        Dzwiek5 = (Button) findViewById(R.id.buttonSound5);
        Dzwiek6 = (Button) findViewById(R.id.buttonSound6);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //TODO obsluga
        } else {
            Toast.makeText(this, "Za stary system", Toast.LENGTH_SHORT).show();
        }
    }

    public void grajSound1(View v) {

        Dzwiek1.setBackgroundColor(0xFFFF0000);
        Dzwiek2.setBackgroundColor(0xFFFF0000);
        Dzwiek3.setBackgroundColor(0xFFFF0000);
        Dzwiek4.setBackgroundColor(0xFFFF0000);
        Dzwiek5.setBackgroundColor(0xFFFF0000);
        Dzwiek6.setBackgroundColor(0xFFFF0000);

        int sound = 0;
        if (Dzwiek1.getId() == v.getId()) {
            Dzwiek1.setBackgroundColor(0xFF00FF00);
            sound = sound1;
            kolejnoscZagraniaDzwiekow += 'A';
        } else if (Dzwiek2.getId() == v.getId()) {
            Dzwiek2.setBackgroundColor(0xFF00FF00);
            sound = sound2;
            kolejnoscZagraniaDzwiekow += 'B';
        } else if (Dzwiek3.getId() == v.getId()) {
            Dzwiek3.setBackgroundColor(0xFF00FF00);
            sound = sound3;
            kolejnoscZagraniaDzwiekow += 'C';
        } else if (Dzwiek4.getId() == v.getId()) {
            Dzwiek4.setBackgroundColor(0xFF00FF00);
            sound = sound4;
            kolejnoscZagraniaDzwiekow += 'D';
        } else if (Dzwiek5.getId() == v.getId()) {
            Dzwiek5.setBackgroundColor(0xFF00FF00);
            sound = sound5;
            kolejnoscZagraniaDzwiekow += 'E';
        } else if (Dzwiek6.getId() == v.getId()) {
            Dzwiek6.setBackgroundColor(0xFF00FF00);
            sound = sound6;
            kolejnoscZagraniaDzwiekow += 'F';
        }
        playSound(this, sound);
    }

    public void WriteBtn(View v)
    {
        try
        {
            FileOutputStream fileout= openFileOutput("datafile.txt",MODE_PRIVATE);
            OutputStreamWriter outputWriter= new OutputStreamWriter((fileout));
            outputWriter.write(kolejnoscZagraniaDzwiekow);
            outputWriter.close();
            kolejnoscZagraniaDzwiekow="";
            Toast.makeText(getBaseContext(),"Zapisano !",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void ReadBtn(View v) throws FileNotFoundException {
        try {
            FileInputStream fileIn = openFileInput("datafile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;

            }
            InputRead.close();
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
            for (int i = 0; i < s.length(); i++) {
                int odegraj = 0;
                if (s.charAt(i) == 'A') {
                    odegraj = sound1;
                } else if (s.charAt(i) == 'B') {
                    odegraj = sound2;
                    ;
                } else if (s.charAt(i) == 'C') {
                    odegraj = sound3;
                    ;
                } else if (s.charAt(i) == 'D') {
                    odegraj = sound4;
                } else if (s.charAt(i) == 'E') {
                    odegraj = sound5;
                } else if (s.charAt(i) == 'F') {
                    odegraj = sound6;
                }
                playSound(this, odegraj);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
