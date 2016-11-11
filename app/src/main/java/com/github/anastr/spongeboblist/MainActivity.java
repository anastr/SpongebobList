package com.github.anastr.spongeboblist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.anastr.speedviewlib.Speedometer;
import com.github.anastr.spongeboblistview.SpongebobList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SpongebobList spongeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spongeList = (SpongebobList) findViewById(R.id.spongebob_list);
        spongeList.getRecyclerView().setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        spongeList.getRecyclerView().setLayoutManager(llm);
        List<Character> list = new ArrayList<>();
        list.add(new Character("Spongebob",R.drawable.spongebob, 92));
        list.add(new Character("Patrick Star", R.drawable.patrick_star, 80));
        list.add(new Character("Sandy Cheeks", R.drawable.sandy_cheeks, 40));
        list.add(new Character("Mr. Krabs", R.drawable.mr_krabs, 70));
        list.add(new Character("Squidward", R.drawable.squidward, 20));
        list.add(new Character("Gary", R.drawable.gary, 75));
        list.add(new Character("Larry", R.drawable.larry, 15));
        list.add(new Character("Plankton", R.drawable.plankton, 10));
        list.add(new Character("Karen", R.drawable.karen, 35));
        list.add(new Character("Mrs. Puff", R.drawable.mrs_puff, 82));
        list.add(new Character("Mother", R.drawable.spongebob_mother, 50));
        list.add(new Character("Father", R.drawable.spongebob_father, 50));
        list.add(new Character("Jellyfish", R.drawable.jellyfish, 78));
        list.add(new Character("Pearl Krabs", R.drawable.pearl_krabs, 23));
        list.add(new Character("Android", R.drawable.android, 100));
        RVAdapter adapter = new RVAdapter(list);
        spongeList.getRecyclerView().setAdapter(adapter);
    }

    public class Character {
        String name;
        int imageId, lovePercent;

        Character(String name, int imageId, int lovePercent){
            this.name = name;
            this.imageId = imageId;
            this.lovePercent = lovePercent;
        }
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CharacterViewHolder> {

        List<Character> characters;

        RVAdapter(List<Character> characters) {
            this.characters = characters;
        }

        @Override
        public CharacterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv, viewGroup, false);
            return new CharacterViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CharacterViewHolder holder, int position) {
            holder.tv_name.setText(characters.get(position).name);
            holder.iv_icon.setImageResource(characters.get(position).imageId);
            holder.speedometer.speedPercentTo(characters.get(position).lovePercent);
        }

        @Override
        public int getItemCount() {
            return characters.size();
        }

        public class CharacterViewHolder extends RecyclerView.ViewHolder{

            ImageView iv_icon;
            TextView tv_name;
            Speedometer speedometer;

            CharacterViewHolder(View itemView) {
                super(itemView);
                iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
                speedometer = (Speedometer) itemView.findViewById(R.id.speedometer);
            }
        }
    }
}
