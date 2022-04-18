package ca.mcgill.ecse321.grocerystoresystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.mcgill.ecse321.grocerystoresystem.adapters.ViewPersonalOrdersAdapter;

public class ViewPersonalOrdersFragment extends Fragment {


    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter<ViewPersonalOrdersAdapter.ViewHolder> adapter;


    public ViewPersonalOrdersFragment() {
        super(R.layout.view_personal_orders_v2);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_personal_orders_v2, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View itemView, Bundle savedInstanceState) {
        super.onViewCreated(itemView, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.scrollToPosition(0);
        this.layoutManager = layoutManager;
        this.adapter = new ViewPersonalOrdersAdapter(getContext(), this.getOrdersList());

    }

    public ArrayList<String> getOrdersList() {

        ArrayList<String> orders = new ArrayList<String>();

        orders.add("Order #1 - March 1, 2022");
        orders.add("Order #2 - March 3, 2022");
        orders.add("Order #3 - March 10, 2022");
        orders.add("Order #4 - March 13, 2022");
        orders.add("Order #5 - March 16, 2022");
        orders.add("Order #6 - March 19, 2022");
        orders.add("Order #7 - March 23, 2022");

        return orders;

    }




}
