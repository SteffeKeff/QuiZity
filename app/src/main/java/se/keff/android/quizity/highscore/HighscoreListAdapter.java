package se.keff.android.quizity.highscore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import se.keff.android.quizity.R;

public final class HighscoreListAdapter extends ArrayAdapter<HighscorePerson>
{
    public HighscoreListAdapter(Context context, List<HighscorePerson> highScorePerson)
    {
        super(context, 0, highScorePerson);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_highscore, null);
        }

        TextView idText = (TextView) convertView.findViewById(R.id.highscore_person);
        TextView usernameText = (TextView) convertView.findViewById(R.id.highscore_score);

        HighscorePerson person = getItem(position);

        idText.setText(String.valueOf(position+1) + ". " + person.name);
        usernameText.setText(convertView.getResources().getString(R.string.hs_score) + person.score);

        return convertView;
    }
}