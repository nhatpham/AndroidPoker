package io.phamily.poker;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DrawableUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Poker p = new Poker();
    Button b;
    TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.shuffleButton);
        view = (TextView) findViewById(R.id.pokerHand);
        PlayPoker();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayPoker();
            }
        });

    }

    private void PlayPoker() {
        p.ShuffleCards();

        for(Poker.Card c: p.GetPokerHand())
        {
            Log.i("Poker", c.toString());
        }

        ImageView iv = (ImageView) findViewById(R.id.iv1);
        ImageView iv2 = (ImageView) findViewById(R.id.iv2);
        ImageView iv3 = (ImageView) findViewById(R.id.iv3);
        ImageView iv4 = (ImageView) findViewById(R.id.iv4);
        ImageView iv5 = (ImageView) findViewById(R.id.iv5);

//        String s = p.cards.remove().toString();
//        int res = getResources().getIdentifier(s.toLowerCase(), "drawable", getPackageName());
//        iv.setImageResource(res);

        String[] cards = new String[5];

        for(int i = 0; i < 5; i++)
        {
            cards[i] = p.dealtCards.get(i).toString();
        }

        try {
            InputStream stream = getAssets().open(cards[0].toLowerCase() + ".png");
            InputStream stream2 = getAssets().open(cards[1].toLowerCase() + ".png");
            InputStream stream3 = getAssets().open(cards[2].toLowerCase() + ".png");
            InputStream stream4 = getAssets().open(cards[3].toLowerCase() + ".png");
            InputStream stream5 = getAssets().open(cards[4].toLowerCase() + ".png");

            Drawable d = Drawable.createFromStream(stream, null);
            Drawable d2 = Drawable.createFromStream(stream2, null);
            Drawable d3 = Drawable.createFromStream(stream3, null);
            Drawable d4 = Drawable.createFromStream(stream4, null);
            Drawable d5 = Drawable.createFromStream(stream5, null);

            iv.setImageDrawable(d);
            iv2.setImageDrawable(d2);
            iv3.setImageDrawable(d3);
            iv4.setImageDrawable(d4);
            iv5.setImageDrawable(d5);


        } catch (IOException e) {
            e.printStackTrace();
        }

        view.setText(p.GetPokerHands());

    }
}
