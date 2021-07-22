package com.toon.estore.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.toon.estore.Model.SignUser;
import com.toon.estore.R;
import com.toon.estore.databinding.FragmentLoginBinding;
import com.toon.estore.databinding.FragmentSignBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseAuth firebaseAuth;
    private  FragmentSignBinding binding;



    public SignFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static SignFrag newInstance(String param1, String param2) {
        SignFrag fragment = new SignFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider

        firebaseAuth=FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.smail.getText().toString();
                String password=binding.sPass.getText().toString();
                String name=binding.sName.getText().toString();
                String phone=binding.sNum.getText().toString();

                 userSignUp(email,password,name,phone);
            }
        });
    }

    private void userSignUp(String email,String password,String name,String phone) {
        binding.sprogressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            SignUser sUser= new SignUser(name,email,phone);
                            FirebaseDatabase.getInstance().getReference("Usres")
                                    .child(firebaseAuth.getCurrentUser().getUid())
                                    .setValue(sUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    binding.sprogressBar.setVisibility(View.GONE);
                                        Toast.makeText(getContext(), "Registration Successful..", Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(getContext(),MainActivity.class));
                                }
                            });

                        }

                        else{
                            binding.sprogressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Email Already Registered", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}