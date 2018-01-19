package com.rishabhrawat.devlight.Account_Activity.Profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rishabhrawat.devlight.R;

import java.io.PrintStream;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    CircleImageView profileImage;
    TextView profilename;
    TextView profileemail;
    TextView profilephone;
    TextView profileweb;
    TextView followers;
    TextView following;
    TextView posts;
    String name;
    String email;
    Uri photoUrl;
    String uid;
    String phoneno;
    Button followmebtn;


    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview=inflater.inflate(R.layout.fragment_profile, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
            photoUrl = user.getPhotoUrl();
            uid = user.getUid();
            phoneno=user.getPhoneNumber();


        }

           profileImage=(CircleImageView)rootview.findViewById(R.id.profile_photo);
           profilename=(TextView)rootview.findViewById(R.id.profile_name);
           profileemail=(TextView)rootview.findViewById(R.id.profile_email);
           profilephone=(TextView)rootview.findViewById(R.id.profile_phone);
           profileweb=(TextView)rootview.findViewById(R.id.profile_web);
           followers=(TextView)rootview.findViewById(R.id.no_of_followers);
           following=(TextView)rootview.findViewById(R.id.no_of_following);
           posts=(TextView)rootview.findViewById(R.id.no_of_posts);
           followmebtn=(Button)rootview.findViewById(R.id.followmebtn);

         Glide.with(getActivity().getBaseContext()).load(photoUrl).into(profileImage);
         profilename.setText(name);
           profileemail.setText(email);
           profilephone.setText(phoneno);
           profileweb.setText("https://www.rishabhisin.com");
           followmebtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Toast.makeText(getActivity().getBaseContext(), "You are following rishabh soon....", Toast.LENGTH_SHORT).show();
               }
           });


        return rootview;
    }
}
