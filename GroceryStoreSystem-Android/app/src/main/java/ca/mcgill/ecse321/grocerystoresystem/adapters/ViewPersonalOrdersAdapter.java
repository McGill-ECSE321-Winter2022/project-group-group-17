package ca.mcgill.ecse321.grocerystoresystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.grocerystoresystem.R;

public class ViewPersonalOrdersAdapter extends RecyclerView.Adapter<ViewPersonalOrdersAdapter.ViewHolder> {

    private List<String> orders;
    private LayoutInflater layoutInflater;
    private Context context;
    private ItemClickListener clickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView orderNumberTextView;
        public CardView cardView;
        //public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            orderNumberTextView = (TextView) itemView.findViewById(R.id.order_number);
            cardView = (CardView) itemView.findViewById(R.id.card_view_order);
            //messageButton = (Button) itemView.findViewById(R.id.message_button);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }

    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }



    public ViewPersonalOrdersAdapter(Context context, ArrayList<String> orders) {
        this.layoutInflater = LayoutInflater.from(context);
        this.orders = orders;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.order_custom_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewPersonalOrdersAdapter.ViewHolder holder, int position) {
        String orderNum = this.orders.get(position);

        holder.orderNumberTextView.setText(orderNum);

        if (position % 2 == 0) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(this.context, androidx.cardview.R.color.cardview_light_background));
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(this.context, R.color.white));
        }

    }

    @Override
    public int getItemCount() {
        return this.orders.size();
    }

    String getItem(int position) {
        return orders.get(position);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
