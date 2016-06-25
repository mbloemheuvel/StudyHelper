package com.example.mick.studyhelper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mick.studyhelper.Model.Card;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudyActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private boolean mShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.container, new CardFrontFragment())
                .commit();


        Intent intent = getIntent();
        intent.getStringExtra("jsonArray");
        String jsonArray = intent.getStringExtra("jsonArray");
        if (jsonArray != null) {
            try {
                JSONArray terms = new JSONArray(jsonArray);
                try {
                    List<Card> cardList = prepareSetData(terms);
//                    TextView term = (TextView) findViewById(R.id.card_front);
//                    term.setText(cardList.get(0).getTerm());
                    Toast toast = Toast.makeText(this, cardList.get(0).getTerm(), Toast.LENGTH_LONG);
                    toast.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A fragment representing the front of the card.
     */
    public static class CardFrontFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_front, container, false);
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    public static class CardBackFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_back, container, false);
        }
    }

    public void flipCard(View view) {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        getFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(R.animator.card_flip_left_in, R.animator.card_flip_left_out, R.animator.card_flip_right_in,R.animator.card_flip_right_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.container, new CardBackFragment())

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();
    }
    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
    }

    private List<Card> prepareSetData(JSONArray list) throws JSONException, IOException {
        if(list!=null) {
            ObjectMapper mapper = new ObjectMapper();
            List<Card> cardList = new ArrayList<>();
            for (int i = 0; i < list.length(); i++) {
                String card = list.getString(i);
                Card cardObject = mapper.readValue(card, Card.class);
                cardList.add(cardObject);
            }
            return cardList;
        }
        return null;
    }
}
