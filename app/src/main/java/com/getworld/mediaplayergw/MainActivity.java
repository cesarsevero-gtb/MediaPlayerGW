package com.getworld.mediaplayergw;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listView);
        retutimePermission();


    }

    //Pedindo permisão para o usuário comando
    public void retutimePermission()
    {

        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();

    }


    //encontrar as musicas no diretorio do celular
    public ArrayList<File> findSong(File file)
    {

        ArrayList<File> arrayList = new ArrayList<>();
                File[] files = file.listFiles();

                for(File singleFile : files)
                {
                        if(singleFile.isDirectory() && !singleFile.isHidden())
                        {

                            arrayList.addAll(findSong(singleFile));
                        }
                        else
                        {

                            if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".mav"))

                            {
                                arrayList.add(singleFile);
                            }
                        }
                }

                return arrayList;
    }





}