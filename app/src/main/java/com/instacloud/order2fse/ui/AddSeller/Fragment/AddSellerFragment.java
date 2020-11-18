package com.instacloud.order2fse.ui.AddSeller.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.snackbar.Snackbar;
import com.instacloud.order2fse.R;
import com.instacloud.order2fse.remote.APIService;
import com.instacloud.order2fse.remote.RetrofitClient;
import com.instacloud.order2fse.ui.AddSeller.Config.AppLocationService;
import com.instacloud.order2fse.ui.AddSeller.Config.LocationAddress;
import com.instacloud.order2fse.ui.AddSeller.Model.AddCustomerModel;
import com.instacloud.order2fse.ui.AddSeller.Model.AddManagerModel;
import com.instacloud.order2fse.ui.AddSeller.Model.StateModel;
import com.instacloud.order2fse.ui.Item.Activity.ItemListActivity;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSellerFragment extends Fragment {


    Button sing_up;
    EditText showLocation, shop_name, customer_name, email_address, mobile_number, password, minimum_order;
    TextView lati, longi;
    Button btnGPSShowLocation, btnShowAddress, btnGetLocation;
    LocationManager locationManager;
    String shopname, customername, emailaddress,
            mobilenumber, pass, minimumorder, address,
            isExclusive, stateSpin, citySpin, latitude, longitude, shopid, managerId;

    BetterSpinner isExclusiveSpinner, spinnerCity, spinnerState;


    private static final int REQUEST_LOCATION = 5;
    private static ProgressDialog mProgressDialog;
    private ArrayList<StateModel> goodModelArrayList;
    private ArrayList<String> names = new ArrayList<String>();
    private BetterSpinner spinner;
    private ArrayAdapter<String> distAdapter;
    private String[] distItem;

    private View view;
    AppLocationService appLocationService;

    private SharedPreferences token;
    private String extremes = "extremeStorage", type;

    private static final String TAG = "LocationAddress";


    public AddSellerFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_seller, container, false);

        token = getActivity().getSharedPreferences(extremes,
                Context.MODE_PRIVATE);

        String tokenid = token.getString("token", "");
        Log.d("Response: ", tokenid);
        //Buttons
        sing_up = view.findViewById(R.id.sing_up);
        btnGetLocation = view.findViewById(R.id.getliveLocation);
        //Spinners
        isExclusiveSpinner = (BetterSpinner) view.findViewById(R.id.IsExclusiveSpinner);
        spinnerCity = (BetterSpinner) view.findViewById(R.id.citySpinner);
        spinnerState = (BetterSpinner) view.findViewById(R.id.stateSpinner);
        //AddCustomer Form
        shop_name = view.findViewById(R.id.shop_name);
        customer_name = view.findViewById(R.id.customer_name);
        email_address = view.findViewById(R.id.email_address);
        mobile_number = view.findViewById(R.id.mobile_number);
        password = view.findViewById(R.id.password);
        minimum_order = view.findViewById(R.id.minimum_order);
        lati = (TextView) view.findViewById(R.id.lati);
        longi = (TextView) view.findViewById(R.id.longi);
        showLocation = (EditText) view.findViewById(R.id.liveLocationAddress);

        //Spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        isExclusiveSpinner.setAdapter(adapter);

        String[] ITEMS = {"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa",
                "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh",
                "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu",
                "Telangana", "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal", "Andaman and Nicobar Islands", "Chandigarh", "Dadra and Nagar Haveli",
                "Daman and Diu", "Delhi", "Lakshadweep", "Puducherry"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, ITEMS);
        spinnerState.setAdapter(adapter4);
        spinnerState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Getting State", parent.getItemAtPosition(position).toString());
                String state = parent.getItemAtPosition(position).toString();
                district(state);
            }
        });

        String[] ITEMS2 = {"No District"};
        distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, ITEMS2);
        spinnerCity.setAdapter(distAdapter);


        sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    //onNextFailed();
                    Snackbar.make(view, "Please fill above details", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                addManager();
                addResto();
                if (shopid != null && managerId != null) {

                    Intent intent = new Intent(getActivity(), ItemListActivity.class);
                    intent.putExtra("id", shopid);
                    intent.putExtra("managerId", managerId);
                    Toast.makeText(getContext(), "Successfully", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    // getActivity().finish();

                }


            }
        });

        //Location Services
        appLocationService = new AppLocationService(
                getContext());

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }
        Location location = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    getContext(), new GeocoderHandler());
        } else {
            showSettingsAlert();
        }


        return view;

    }

    public Boolean validate() {
        boolean valid = true;

        isExclusive = isExclusiveSpinner.getText().toString().trim();
        shopname = shop_name.getText().toString().trim();
        customername = customer_name.getText().toString().trim();
        emailaddress = email_address.getText().toString().trim();
        mobilenumber = mobile_number.getText().toString().trim();
        pass = password.getText().toString().trim();
        minimumorder = minimum_order.getText().toString().trim();
        stateSpin = spinnerState.getText().toString().trim();
        citySpin = spinnerCity.getText().toString().trim();
        address = showLocation.getText().toString().trim();
        latitude = lati.getText().toString().trim();
        longitude = longi.getText().toString().trim();


        if (isExclusive.trim().isEmpty()) {
            isExclusiveSpinner.setError("Not Select");
            //Toast.makeText(this, "Enter Travel Allowance.", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            isExclusiveSpinner.setError(null);
        }

        if (shopname.trim().isEmpty()) {
            shop_name.setError("Enter Shop Name");
            //Toast.makeText(this, "Enter Travel Allowance.", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            shop_name.setError(null);
        }

        if (customername.trim().isEmpty()) {
            customer_name.setError("Enter Customer Name");
            valid = false;
        } else {
            customer_name.setError(null);
        }

        if (emailaddress.trim().isEmpty()) {
            email_address.setError("Enter Email");
            valid = false;
        } else {
            email_address.setError(null);
        }

        if (mobilenumber.trim().isEmpty()) {
            mobile_number.setError("Enter Mobile");
            valid = false;
        } else {
            mobile_number.setError(null);
        }

        if (pass.trim().isEmpty()) {
            password.setError("Enter Password");
            valid = false;
        } else {
            password.setError(null);
        }

        if (minimumorder.trim().isEmpty()) {
            minimum_order.setError("Enter Order Quantity");
            valid = false;
        } else {
            minimum_order.setError(null);
        }

        if (stateSpin.trim().isEmpty()) {
            spinnerState.setError("Select State");
            valid = false;
        } else {
            spinnerState.setError(null);
        }


        if (citySpin.trim().isEmpty()) {
            spinnerCity.setError("Select City");
            valid = false;
        } else {
            spinnerCity.setError(null);
        }


        if (address.trim().isEmpty()) {
            showLocation.setError("Enter Location");
            valid = false;
        } else {
            showLocation.setError(null);
        }


        if (latitude.trim().isEmpty()) {
            lati.setError("Enter Latitude");
            valid = false;
        } else {
            lati.setError(null);
        }

        if (longitude.trim().isEmpty()) {
            longi.setError("Enter Longitude");
            valid = false;
        } else {
            longi.setError(null);
        }


        return valid;
    }

    private void addResto() {
        Retrofit retrofit = RetrofitClient.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.addCustomer(token.getString("token", ""), shopname, "DEMO", address,
                latitude, longitude, mobilenumber, mobilenumber,
                "DEMO", "12", "15", "24",
                "8", "1", "0", "12", "DEMO IMFORMATION", "1");

        call.enqueue(new Callback<AddCustomerModel>() {

            @Override
            public void onResponse(Call<AddCustomerModel> call, Response<AddCustomerModel> response) {
                Log.d("Response: ", String.valueOf(response.body()));
                if (response.isSuccessful()) {
                    AddCustomerModel addCustomerModel = response.body();
                    shopid = String.valueOf(addCustomerModel.getData().getId());
                    SharedPreferences.Editor editor = token.edit();
                    editor.putString("restaurant_id", shopid);
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error: ", t.getMessage());
            }
        });

    }


    public void addManager() {
        Retrofit retrofit = RetrofitClient.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.addManager(customername, emailaddress, pass);

        call.enqueue(new Callback<AddManagerModel>() {
            @Override
            public void onResponse(Call <AddManagerModel>call, Response <AddManagerModel>response) {
                if (response.body() != null) {
                    AddManagerModel addManagerModel = response.body();
                    Log.d("Response: ", String.valueOf(addManagerModel.getMessage()));
                    if (addManagerModel.getSuccess().equals(true)) {
                        managerId = String.valueOf(addManagerModel.getData().getId());
                        SharedPreferences.Editor editor = token.edit();
                        editor.putString("manager_id", managerId);
                        editor.commit();
                        Log.d("Response: ", String.valueOf(managerId));
                    } else {
                        //   progressDialog.cancel();
                        // Toast.makeText(getContext(), addCustomerModel.getData(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error: ", t.getMessage());
            }
        });


    }


    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getContext());
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        getContext().startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress, locationLatitude, locationLongitude;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    locationLatitude = bundle.getString("Latitude");
                    locationLongitude = bundle.getString("Longitude");
                    break;
                default:
                    locationAddress = null;
                    locationLatitude = null;
                    locationLongitude = null;
            }
            showLocation.setText(locationAddress);
            lati.setText(locationLatitude);
            longi.setText(locationLongitude);


        }
    }


    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {

    }


    private static final String[] COUNTRIES = new String[]{

            "YES", "NO",

    };


    private void district(String str) {

        Log.d("jksjd", str);
        BetterSpinner spinnerCity = (BetterSpinner) view.findViewById(R.id.citySpinner);
        System.out.println(str);
        switch (str) {
            case "Andhra Pradesh":
                distItem = new String[]{"Anantapur", "Chittoor", "East Godavari", "Guntur", "Krishna", "Kurnool", "Prakasam", "Srikakulam", "Sri Potti Sriramulu Nellore", "Visakhapatnam", "Vizianagaram", "West Godavari", "YSR District, Kadapa (Cuddapah)"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Arunachal Pradesh":
                distItem = new String[]{"Anjaw", "Changlang", "Dibang Valley", "East Kameng", "East Siang", "Kra Daadi", "Kurung Kumey", "Lohit", "Longding", "Lower Dibang Valley", "Lower Siang", "Lower Subansiri", "Namsai", "Papum Pare", "Siang", "Tawang", "Tirap", "Upper Siang", "Upper Subansiri", "West Kameng", "West Siang"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Assam":
                distItem = new String[]{"Baksa", "Barpeta", "Biswanath", "Bongaigaon", "Cachar", "Charaideo", "Chirang", "Darrang", "Dhemaji", "Dhubri", "Dibrugarh", "Dima Hasao (North Cachar Hills)", "Goalpara", "Golaghat", "Hailakandi", "Hojai", "Jorhat", "Kamrup", "Kamrup Metropolitan", "Karbi Anglong", "Karimganj", "Kokrajhar", "Lakhimpur", "Majuli", "Morigaon", "Nagaon", "Nalbari", "Sivasagar", "Sonitpur", "South Salamara-Mankachar", "Tinsukia", "Udalguri", "West Karbi Anglong"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Bihar":
                distItem = new String[]{"Araria", "Arwal", "Aurangabad", "Banka", "Begusarai", "Bhagalpur", "Bhojpur", "Buxar", "Darbhanga", "East Champaran (Motihari)", "Gaya", "Gopalganj", "Jamui", "Jehanabad", "Kaimur (Bhabua)", "Katihar", "Khagaria", "Kishanganj", "Lakhisarai", "Madhepura", "Madhubani", "Munger (Monghyr)", "Muzaffarpur", "Nalanda", "Nawada", "Patna", "Purnia (Purnea)", "Rohtas", "Saharsa", "Samastipur", "Saran", "Sheikhpura", "Sheohar", "Sitamarhi", "Siwan", "Supaul", "Vaishali", "West Champaran"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Chhattisgarh":
                distItem = new String[]{"Balod", "Baloda Bazar", "Balrampur", "Bastar", "Bemetara", "Bijapur", "Bilaspur", "Dantewada (South Bastar)", "Dhamtari", "Durg", "Gariyaband", "Janjgir-Champa", "Jashpur", "Kabirdham (Kawardha)", "Kanker (North Bastar)", "Kondagaon", "Korba", "Korea (Koriya)", "Mahasamund", "Mungeli", "Narayanpur", "Raigarh", "Raipur", "Rajnandgaon", "Sukma", "Surajpur  ", "Surguja"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Goa":
                distItem = new String[]{"North Goa", "South Goa"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Gujarat":
                distItem = new String[]{"Ahmedabad", "Amreli", "Anand", "Aravalli", "Banaskantha (Palanpur)", "Bharuch", "Bhavnagar", "Botad", "Chhota Udepur", "Dahod", "Dangs (Ahwa)", "Devbhoomi Dwarka", "Gandhinagar", "Gir Somnath", "Jamnagar", "Junagadh", "Kachchh", "Kheda (Nadiad)", "Mahisagar", "Mehsana", "Morbi", "Narmada (Rajpipla)", "Navsari", "Panchmahal (Godhra)", "Patan", "Porbandar", "Rajkot", "Sabarkantha (Himmatnagar)", "Surat", "Surendranagar", "Tapi (Vyara)", "Vadodara", "Valsad"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Haryana":
                distItem = new String[]{"Ambala", "Bhiwani", "Charkhi Dadri", "Faridabad", "Fatehabad", "Gurgaon", "Hisar", "Jhajjar", "Jind", "Kaithal", "Karnal", "Kurukshetra", "Mahendragarh", "Mewat", "Palwal", "Panchkula", "Panipat", "Rewari", "Rohtak", "Sirsa", "Sonipat", "Yamunanagar"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Himachal Pradesh":
                distItem = new String[]{"Bilaspur", "Chamba", "Hamirpur", "Kangra", "Kinnaur", "Kullu", "Lahaul & Spiti", "Mandi", "Shimla", "Sirmaur (Sirmour)", "Solan", "Una"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Jammu and Kashmir":
                distItem = new String[]{"Anantnag", "Bandipore", "Baramulla", "Budgam", "Doda", "Ganderbal", "Jammu", "Kargil", "Kathua", "Kishtwar", "Kulgam", "Kupwara", "Leh", "Poonch", "Pulwama", "Rajouri", "Ramban", "Reasi", "Samba", "Shopian", "Srinagar", "Udhampur"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinner.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Jharkhand":
                distItem = new String[]{"Bokaro", "Chatra", "Deoghar", "Dhanbad", "Dumka", "East Singhbhum", "Garhwa", "Giridih", "Godda", "Gumla", "Hazaribag", "Jamtara", "Khunti", "Koderma", "Latehar", "Lohardaga", "Pakur", "Palamu", "Ramgarh", "Ranchi", "Sahibganj", "Seraikela-Kharsawan", "Simdega", "West Singhbhum"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Karnataka":
                distItem = new String[]{"Bagalkot", "Ballari (Bellary)", "Belagavi (Belgaum)", "Bengaluru (Bangalore) Rural", "Bengaluru (Bangalore) Urban", "Bidar", "Chamarajanagar", "Chikballapur", "Chikkamagaluru (Chikmagalur)", "Chitradurga", "Dakshina Kannada", "Davangere", "Dharwad", "Gadag", "Hassan", "Haveri", "Kalaburagi (Gulbarga)", "Kodagu", "Kolar", "Koppal", "Mandya", "Mysuru (Mysore)", "Raichur", "Ramanagara", "Shivamogga (Shimoga)", "Tumakuru (Tumkur)", "Udupi", "Uttara Kannada (Karwar)", "Vijayapura (Bijapur)", "Yadgir"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Kerala":
                distItem = new String[]{"Alappuzha", "Ernakulam", "Idukki", "Kannur", "Kasaragod", "Kollam", "Kottayam", "Kozhikode", "Malappuram", "Palakkad", "Pathanamthitta", "Thiruvananthapuram", "Thrissur", "Wayanad"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Madhya Pradesh":
                distItem = new String[]{"Agar Malwa", "Alirajpur", "Anuppur", "Ashoknagar", "Balaghat", "Barwani", "Betul", "Bhind", "Bhopal", "Burhanpur", "Chhatarpur", "Chhindwara", "Damoh", "Datia", "Dewas", "Dhar", "Dindori", "Guna", "Gwalior", "Harda", "Hoshangabad", "Indore", "Jabalpur", "Jhabua", "Katni", "Khandwa", "Khargone", "Mandla", "Mandsaur", "Morena", "Narsinghpur", "Neemuch", "Panna", "Raisen", "Rajgarh", "Ratlam", "Rewa", "Sagar", "Satna", "Sehore", "Seoni", "Shahdol", "Shajapur", "Sheopur", "Shivpuri", "Sidhi", "Singrauli", "Tikamgarh", "Ujjain", "Umaria", "Vidisha"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Maharashtra":
                distItem = new String[]{"Ahmednagar", "Akola", "Amravati", "Aurangabad", "Beed", "Bhandara", "Buldhana", "Chandrapur", "Dhule", "Gadchiroli", "Gondia", "Hingoli", "Jalgaon", "Jalna", "Kolhapur", "Latur", "Mumbai City", "Mumbai Suburban", "Nagpur", "Nanded", "Nandurbar", "Nashik", "Osmanabad", "Palghar", "Parbhani", "Pune", "Raigad", "Ratnagiri", "Sangli", "Satara", "Sindhudurg", "Solapur", "Thane", "Wardha", "Washim", "Yavatmal"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Manipur":
                distItem = new String[]{"Bishnupur", "Chandel", "Churachandpur", "Imphal East", "Imphal West", "Jiribam", "Kakching", "Kamjong", "Kangpokpi", "Noney", "Pherzawl", "Senapati", "Tamenglong", "Tengnoupal", "Thoubal", "Ukhrul"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Meghalaya":
                distItem = new String[]{"East Garo Hills", "East Jaintia Hills", "East Khasi Hills", "North Garo Hills", "Ri Bhoi", "South Garo Hills", "South West Garo Hills ", "South West Khasi Hills", "West Garo Hills", "West Jaintia Hills", "West Khasi Hills"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Mizoram":
                distItem = new String[]{"Aizawl", "Champhai", "Kolasib", "Lawngtlai", "Lunglei", "Mamit", "Saiha", "Serchhip"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Nagaland":
                distItem = new String[]{"Dimapur", "Kiphire", "Kohima", "Longleng", "Mokokchung", "Mon", "Peren", "Phek", "Tuensang", "Wokha", "Zunheboto"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Odisha":
                distItem = new String[]{"Angul", "Balangir", "Balasore", "Bargarh", "Bhubaneswar", "Bhadrak", "Boudh", "Cuttack", "Deogarh", "Dhenkanal", "Gajapati", "Ganjam", "Jagatsinghapur", "Jajpur", "Jharsuguda", "Kalahandi", "Kandhamal", "Kendrapara", "Kendujhar (Keonjhar)", "Khordha", "Koraput", "Malkangiri", "Mayurbhanj", "Nabarangpur", "Nayagarh", "Nuapada", "Puri", "Rayagada", "Sambalpur", "Sonepur", "Sundargarh"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Punjab":
                distItem = new String[]{"Amritsar", "Barnala", "Bathinda", "Faridkot", "Fatehgarh Sahib", "Fazilka", "Ferozepur", "Gurdaspur", "Hoshiarpur", "Jalandhar", "Kapurthala", "Ludhiana", "Mansa", "Moga", "Muktsar", "Nawanshahr (Shahid Bhagat Singh Nagar)", "Pathankot", "Patiala", "Rupnagar", "Sahibzada Ajit Singh Nagar (Mohali)", "Sangrur", "Tarn Taran"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Rajasthan":
                distItem = new String[]{"Ajmer", "Alwar", "Banswara", "Baran", "Barmer", "Bharatpur", "Bhilwara", "Bikaner", "Bundi", "Chittorgarh", "Churu", "Dausa", "Dholpur", "Dungarpur", "Hanumangarh", "Jaipur", "Jaisalmer", "Jalore", "Jhalawar", "Jhunjhunu", "Jodhpur", "Karauli", "Kota", "Nagaur", "Pali", "Pratapgarh", "Rajsamand", "Sawai Madhopur", "Sikar", "Sirohi", "Sri Ganganagar", "Tonk", "Udaipur"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Sikkim":
                distItem = new String[]{"East Sikkim", "North Sikkim", "South Sikkim", "West Sikkim"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Tamil Nadu":
                distItem = new String[]{"Ariyalur", "Chennai", "Coimbatore", "Cuddalore", "Dharmapuri", "Dindigul", "Erode", "Kanchipuram", "Kanyakumari", "Karur", "Krishnagiri", "Madurai", "Nagapattinam", "Namakkal", "Nilgiris", "Perambalur", "Pudukkottai", "Ramanathapuram", "Salem", "Sivaganga", "Thanjavur", "Theni", "Thoothukudi (Tuticorin)", "Tiruchirappalli", "Tirunelveli", "Tiruppur", "Tiruvallur", "Tiruvannamalai", "Tiruvarur", "Vellore", "Viluppuram", "Virudhunagar"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Telangana":
                distItem = new String[]{"Adilabad", "Bhadradri Kothagudem", "Hyderabad", "Jagtial", "Jangaon", "Jayashankar Bhoopalpally", "Jogulamba Gadwal", "Kamareddy", "Karimnagar", "Khammam", "Komaram Bheem Asifabad", "Mahabubabad", "Mahabubnagar", "Mancherial", "Medak", "Medchal", "Nagarkurnool", "Nalgonda", "Nirmal", "Nizamabad", "Peddapalli", "Rajanna Sircilla", "Rangareddy", "Sangareddy", "Siddipet", "Suryapet", "Vikarabad", "Wanaparthy", "Warangal (Rural)", "Warangal (Urban)", "Yadadri Bhuvanagiri"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Tripura":
                distItem = new String[]{"Dhalai", "Gomati", "Khowai", "North Tripura", "Sepahijala", "South Tripura", "Unakoti", "West Tripura"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Uttarakhand":
                distItem = new String[]{"Almora", "Bageshwar", "Chamoli", "Champawat", "Dehradun", "Haridwar", "Nainital", "Pauri Garhwal", "Pithoragarh", "Rudraprayag", "Tehri Garhwal", "Udham Singh Nagar", "Uttarkashi"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Uttar Pradesh":
                distItem = new String[]{"Agra", "Aligarh", "Allahabad", "Ambedkar Nagar", "Amethi (Chatrapati Sahuji Mahraj Nagar)", "Amroha (J.P. Nagar)", "Auraiya", "Azamgarh", "Baghpat", "Bahraich", "Ballia", "Balrampur", "Banda", "Barabanki", "Bareilly", "Basti", "Bhadohi", "Bijnor", "Budaun", "Bulandshahr", "Chandauli", "Chitrakoot", "Deoria", "Etah", "Etawah", "Faizabad", "Farrukhabad", "Fatehpur", "Firozabad", "Gautam Buddha Nagar", "Ghaziabad", "Ghazipur", "Gonda", "Gorakhpur", "Hamirpur", "Hapur (Panchsheel Nagar)", "Hardoi", "Hathras", "Jalaun", "Jaunpur", "Jhansi", "Kannauj", "Kanpur Dehat", "Kanpur Nagar", "Kanshiram Nagar (Kasganj)", "Kaushambi", "Kushinagar (Padrauna)", "Lakhimpur - Kheri", "Lalitpur", "Lucknow", "Maharajganj", "Mahoba", "Mainpuri", "Mathura", "Mau", "Meerut", "Mirzapur", "Moradabad", "Muzaffarnagar", "Pilibhit", "Pratapgarh", "RaeBareli", "Rampur", "Saharanpur", "Sambhal (Bhim Nagar)", "Sant Kabir Nagar", "Shahjahanpur", "Shamali (Prabuddh Nagar)", "Shravasti", "Siddharth Nagar", "Sitapur", "Sonbhadra", "Sultanpur", "Unnao", "Varanasi"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "West Bengal":
                distItem = new String[]{"Alipurduar", "Bankura", "Birbhum", "Cooch Behar", "Dakshin Dinajpur (South Dinajpur)", "Darjeeling", "Hooghly", "Howrah", "Jalpaiguri", "Jhargram", "Kalimpong", "Kolkata", "Malda", "Murshidabad", "Nadia", "North 24 Parganas", "Paschim Medinipur (West Medinipur)", "Paschim (West) Burdwan (Bardhaman)", "Purba Burdwan (Bardhaman)", "Purba Medinipur (East Medinipur)", "Purulia", "South 24 Parganas", "Uttar Dinajpur (North Dinajpur)"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Andaman and Nicobar Islands":
                distItem = new String[]{"Nicobar", "North and Middle Andaman", "South Andaman"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Chandigarh":
                distItem = new String[]{"Chandigarh"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Dadra and Nagar Haveli":
                distItem = new String[]{"Dadra & Nagar Haveli"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Daman and Diu":
                distItem = new String[]{"Daman", "Diu"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Delhi":
                distItem = new String[]{"Central Delhi", "East Delhi", "New Delhi", "North Delhi", "North East  Delhi", "North West  Delhi", "Shahdara", "South Delhi", "South East Delhi", "South West  Delhi", "West Delhi"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Lakshadweep":
                distItem = new String[]{"Lakshadweep"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
            case "Puducherry":
                distItem = new String[]{"Karaikal", "Mahe", "Pondicherry", "Yanam"};
                distAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, distItem);
                spinnerCity.setAdapter(distAdapter);
                distAdapter.notifyDataSetChanged();
                break;
        }
    }

}



