package ca.mcgill.ecse321.grocerystoresystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.grocerystoresystem.databinding.RegisterFragmentBinding;
import cz.msebera.android.httpclient.Header;

public class RegisterFragment extends Fragment {

    private RegisterFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RegisterFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   binding.button2.setEnabled(false);

                   String firstName = binding.firstName.getText().toString();
                   String lastName = binding.lastName.getText().toString();

                   String email = binding.email.getText().toString();
                   String password = binding.password.getText().toString();

                   String street_name = binding.streetName.getText().toString();
                   String street_number = binding.streetNum.getText().toString();
                   String country = binding.country.getText().toString();
                   String city = binding.country.getText().toString();
                   String postal_code = binding.postalCode.getText().toString();

                   boolean isLocal = binding.isLocal.isChecked();

                   RequestParams params = new RequestParams();
                   params.add("firstName", firstName);
                   params.add("lastName", lastName);
                   params.add("email", email);
                   params.add("password", password);

                   final int[] firstSuccess = {0};
                   final int[] userId = {0};

                   HttpUtils.post(
                       "/customer/create ", params, new JsonHttpResponseHandler() {
                               @Override
                               public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                   try {
                                       // Perform the login by setting the state of login-status
                                       userId[0] = response.getInt("id");

                                       LoginCustomer.INSTANCE.login(userId[0], email, password, firstName, lastName);

                                       firstSuccess[0] = 1;

                                       // Return to the fragment that requested a login.
                                       // But we only enter here via login, meaning we need to pop twice!
                                   } catch (JSONException ex) {
                                       Snackbar.make(binding.getRoot(), "Something went wrong and it's not your fault!\nFile a bug report!", Snackbar.LENGTH_LONG)
                                               .setAction("Error: ", null).show();
                                   }
                               }

                               @Override
                               public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                   // Report the first error to avoid excessive popups
                                   //String errorMessage = ApiError.firstOr(ApiError.decodeError(responseString), "Unknown error, try again later");

                                   Snackbar.make(binding.getRoot(), "An error has occurred. Please contact support", Snackbar.LENGTH_LONG)
                                           .setAction("Action", null).show();

                                   System.out.println(errorResponse.toString());


                                   binding.button2.setEnabled(true);
                               }

                               @Override
                               public void onFinish() {

                               }
                       }
                   );

//                   final int[] addressId = new int[1];
//                   final int[] secondSuccess = new int[1];
//
//                   RequestParams params2 = new RequestParams();
//                   params2.add("streetName", street_name);
//                   params2.add("streetNum", street_number);
//                   params2.add("city", city);
//                   params2.add("country", country);
//                   params2.add("postalCode", postal_code);
//                   params2.add("isLocal", String.valueOf(isLocal));

//                   if(firstSuccess[0] == 1) {
//                       HttpUtils.post(
//                               "address/create/", params2, new JsonHttpResponseHandler() {
//                                   @Override
//                                   public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                                       try {
//                                           // Perform the login by setting the state of login-status
//                                           addressId[0] = response.getInt("id");
//                                           secondSuccess[0] = 1;
//
//                                           // Return to the fragment that requested a login.
//                                           // But we only enter here via login, meaning we need to pop twice!
//                                       } catch (JSONException ex) {
//                                           Snackbar.make(binding.getRoot(), "Something went wrong and it's not your fault!\nFile a bug report!", Snackbar.LENGTH_LONG)
//                                                   .setAction("Error: ", null).show();
//                                       }
//                                   }
//
//                                   @Override
//                                   public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                                       // Report the first error to avoid excessive popups
//                                       //String errorMessage = ApiError.firstOr(ApiError.decodeError(responseString), "Unknown error, try again later");
//                                       Snackbar.make(binding.getRoot(), responseString, Snackbar.LENGTH_LONG)
//                                               .setAction("Action", null).show();
//                                   }
//
//                                   @Override
//                                   public void onFinish() {
//                                   }
//                               }
//                       );
//                   }

//                   RequestParams params3 = new RequestParams();
//                   params3.add("id", String.valueOf(userId));
//                   params3.add("addressID", String.valueOf(addressId));


//                   if(secondSuccess[0] == 1) {
//                       HttpUtils.post(
//                               "customer/update/address/", params3, new JsonHttpResponseHandler() {
//                                   @Override
//                                   public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//
//                                       final NavController navController = NavHostFragment.findNavController(RegisterFragment.this);
//                                       navController.popBackStack();
//                                   }
//
//                                   @Override
//                                   public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                                       // Report the first error to avoid excessive popups
//                                       //String errorMessage = ApiError.firstOr(ApiError.decodeError(responseString), "Unknown error, try again later");
//                                       Snackbar.make(binding.getRoot(), responseString, Snackbar.LENGTH_LONG)
//                                               .setAction("Action", null).show();
//                                   }
//
//                                   @Override
//                                   public void onFinish() {
//                                       // re-enable our signup button!
//                                       binding.button2.setEnabled(true);
//                                   }
//                               }
//                       );
//                   }
               }
           }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}