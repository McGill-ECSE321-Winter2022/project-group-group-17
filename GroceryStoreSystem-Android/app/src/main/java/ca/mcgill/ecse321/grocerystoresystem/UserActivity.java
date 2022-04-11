package ca.mcgill.ecse321.grocerystoresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import ca.mcgill.ecse321.grocerystoresystem.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){

            String name = intent.getStringExtra("name");
            String email = intent.getStringExtra("email");

            binding.nameProfile.setText(name);
            binding.emailProfile.setText(email);


        }

    }
}
