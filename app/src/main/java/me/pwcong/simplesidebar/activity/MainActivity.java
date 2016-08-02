package me.pwcong.simplesidebar.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.pwcong.simplesidebar.R;
import me.pwcong.simplesidebar.adapter.SimpleAdapter;
import me.pwcong.simplesidebar.conf.Constants;
import me.pwcong.simplesidebar.entity.SimpleData;
import me.pwcong.simplesidebar.view.SimpleSideBar;

/**
 * Created by pwcong on 2016/8/2.
 */
public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    SimpleSideBar sideBar;
    RecyclerView recyclerView;

    List<SimpleData> simpleDatas;

    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initVariable();

        linearLayout= (LinearLayout) findViewById(R.id.root);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        simpleAdapter=new SimpleAdapter(MainActivity.this, simpleDatas, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) findViewById(R.id.tv_simple);

                Snackbar.make(linearLayout,textView.getText(),Snackbar.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(simpleAdapter);

        sideBar= (SimpleSideBar) findViewById(R.id.sideBar);

        sideBar.setOnLetterTouchedChangeListener(new SimpleSideBar.OnLetterTouchedChangeListener() {
            @Override
            public void onTouchedLetterChange(String letterTouched) {

                int pos=simpleAdapter.getLetterPosition(letterTouched);
                if(pos!=-1){
                    recyclerView.scrollToPosition(pos);
                }
            }
        });


    }

    private void initVariable(){

        simpleDatas=new ArrayList<>();

        for(int i=0;i<26;i++){

            simpleDatas.add(new SimpleData(Constants.TYPE_HEADER,(char)(i+65)+""));
            for(int j=0;j<5;j++){
                simpleDatas.add(new SimpleData(Constants.TYPE_DATA,(char)(i+65)+" Hello World "+ j));
            }

        }

    }


}
