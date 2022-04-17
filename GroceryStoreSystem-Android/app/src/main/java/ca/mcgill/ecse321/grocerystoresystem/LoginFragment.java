package ca.mcgill.ecse321.grocerystoresystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.grocerystoresystem.databinding.LoginFragmentBinding;
import cz.msebera.android.httpclient.Header;

public class LoginFragment extends Fragment {

    private LoginFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStat) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Redirect user to the register page.
        binding.buttonFirst2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });


        //Clicking on the login button will trigger a series of HTTP request.
        //The system automatically identifies the type of the user and log them in accordingly.



        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                binding.buttonFirst.setEnabled(false);

                String email = binding.editTextTextEmailAddress.getText().toString();
                String password = binding.editTextTextPassword.getText().toString();

                RequestParams params = new RequestParams();
                params.add("password", password);
                params.add("email", email);

                final boolean[] isCustomer = {false};
                final boolean[] isEmployee = {false};
                final boolean[] isOwner = {false};

                //HTTP request to check is user is a customer.
                HttpUtils.post("customer/login", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            // Perform the login by setting the state of login-status
                            String result = response.toString();
                            if(result.equals("true")) {
                                isCustomer[0] = true;
                            }

                            // Return to the fragment that requested a login.
                            // But we only enter here via login, meaning we need to pop twice!
                        } catch (Exception ex) {
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


                        binding.buttonFirst.setEnabled(true);
                    }

                    @Override
                    public void onFinish() {

                    }
                    }
                );

                //User logging in is a customer. Logging them in and redirect to browse page.
                if(isCustomer[0]) {

                    RequestParams params2 = new RequestParams();
                    params2.add("email", email);

                    HttpUtils.get("customer/get/email", params2, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                // Perform the login by setting the state of login-status
                                LoginCustomer.INSTANCE.login(response.getInt("id"), email, password, response.getString("firstName"), response.getString("lastName"));

                                // Return the user to the main home page (Should be browse page)

                            } catch (Exception ex) {
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


                            binding.buttonFirst.setEnabled(true);
                        }
                        }
                    );
                }

                //HTTP Request to check if the logged in user is an employee.
                HttpUtils.post("employee/login", params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                // Perform the login by setting the state of login-status
                                String result = response.toString();
                                if(result.equals("true")) {
                                    isEmployee[0] = true;
                                }

                                // Return to the fragment that requested a login.
                                // But we only enter here via login, meaning we need to pop twice!
                            } catch (Exception ex) {
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


                            binding.buttonFirst.setEnabled(true);
                        }

                        @Override
                        public void onFinish() {

                        }
                    }
                );

                //User is an employee. Login the employee and redirect to the browse page.
                if(isEmployee[0]) {

                    RequestParams params2 = new RequestParams();
                    params2.add("email", email);

                    HttpUtils.get("employee/get/email", params2, new JsonHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    try {
                                        // Perform the login by setting the state of login-status
                                        LoginCustomer.INSTANCE.login(response.getInt("id"), email, password, response.getString("firstName"), response.getString("lastName"));

                                        // Return the user to the main home page (Should be browse page)

                                    } catch (Exception ex) {
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


                                    binding.buttonFirst.setEnabled(true);
                                }
                            }
                    );
                }

                //HTTP Request to check if the user is an owner.
                HttpUtils.post("owner/login", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            // Perform the login by setting the state of login-status
                            String result = response.toString();
                            if(result.equals("true")) {
                                isOwner[0] = true;
                            }

                            // Return to the fragment that requested a login.
                            // But we only enter here via login, meaning we need to pop twice!
                        } catch (Exception ex) {
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


                        binding.buttonFirst.setEnabled(true);
                    }

                    @Override
                    public void onFinish() {

                    }
                }
                );

                //User is an owner. Process their request from there and redirect to browse page.
                if(isOwner[0]) {

                    RequestParams params2 = new RequestParams();
                    params2.add("email", email);

                    HttpUtils.get("owner/get/email", params2, new JsonHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    try {
                                        // Perform the login by setting the state of login-status
                                        LoginCustomer.INSTANCE.login(response.getInt("id"), email, password, response.getString("firstName"), response.getString("lastName"));

                                        // Return the user to the main home page (Should be browse page)

                                    } catch (Exception ex) {
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


                                    binding.buttonFirst.setEnabled(true);
                                }
                            }
                    );
                }


                //NO user has been found with entered parameters
                Snackbar.make(binding.getRoot(), "No user found with this username/password combination", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}