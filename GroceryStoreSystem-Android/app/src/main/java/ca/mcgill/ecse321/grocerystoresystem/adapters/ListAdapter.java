package ca.mcgill.ecse321.grocerystoresystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ca.mcgill.ecse321.grocerystoresystem.R;
import ca.mcgill.ecse321.grocerystoresystem.User;

public class ListAdapter extends ArrayAdapter<User> {


    public ListAdapter(Context context, ArrayList<User> userArrayList) {

        super(context, R.layout.list_item, userArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        User user = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        TextView userName = convertView.findViewById(R.id.userName);
        TextView email = convertView.findViewById(R.id.email);

        userName.setText(user.name);
        email.setText(user.email);

        return convertView;
    }
}