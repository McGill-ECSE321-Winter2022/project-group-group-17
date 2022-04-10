package ca.mcgill.ecse321.grocerystoresystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreItemsAdapter extends BaseAdapter {
    Context context;
    String listItems[];
    String listItemPrice[];
    int listImages[];
    LayoutInflater inflater;

    StoreItemsAdapter(Context ctx,String [] itemList, String[] itemPrice, int [] images ){
        this.context=ctx;
        this.listImages=images;
        this.listItems = itemList;
        this.listItemPrice = itemPrice;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return listItems.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =inflater.inflate(R.layout.activity_store_items, null);
        TextView nameView = (TextView) convertView.findViewById(R.id.textView);
        TextView priceView = (TextView) convertView.findViewById(R.id.textView);
        ImageView itemImage =(ImageView) convertView.findViewById(R.id.imageIcon);

        nameView.setText(listItems[position]);
        priceView.setText(listItems[position]);
        itemImage.setImageResource(listImages[position]);
        return convertView;
    }
}
