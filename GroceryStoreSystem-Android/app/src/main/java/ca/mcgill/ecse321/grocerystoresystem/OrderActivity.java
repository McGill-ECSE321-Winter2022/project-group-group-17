package ca.mcgill.ecse321.grocerystoresystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import ca.mcgill.ecse321.grocerystoresystem.adapters.ListAdapter;
import ca.mcgill.ecse321.grocerystoresystem.adapters.ViewPagerAdapter;
import ca.mcgill.ecse321.grocerystoresystem.databinding.ViewOrdersBinding;

public class OrderActivity extends AppCompatActivity {
    private ViewOrdersBinding binding;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ViewOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpTabs();

        String[] name = {"Christopher", "Craig", "Sergio", "Mubariz", "Mike", "Michael", "Toa", "Ivana", "Alex"};
        String[] email = {"Christopher@gmail.com", "Craig@hotmail.com", "Sergio@gmail.com", "Mubariz@bdo-ea.com",
                "Mike@gmail.com", "Michael@outlook.com", "Toa@mcgill.ca", "Ivana@mcgill.ca", "Alex@gmail.com"};
        ArrayList<User> userArrayList = new ArrayList<>();

        for (int i = 0; 1 < name.length; i++) {
            User user = new User(name[i], email[i]);
            userArrayList.add(user);
        }
        ListAdapter listAdapter = new ListAdapter(OrderActivity.this, userArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(OrderActivity.this, UserActivity.class);
                i.putExtra("name", name[position]);
                i.putExtra("email", email[position]);
                startActivity(i);
            }
        });
    }

    private void setUpTabs() {
        tabLayout = findViewById(R.id.tablayout2);
        viewPager = findViewById(R.id.viewPager2);
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new DeliveryOrderFragment(), "Delivery");
        adapter.addFragment(new PickupOrderFragment(), "Pickup");
        viewPager.setAdapter(adapter);
    }

}
