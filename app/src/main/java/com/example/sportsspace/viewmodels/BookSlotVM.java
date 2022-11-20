package com.example.sportsspace.viewmodels;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.sportsspace.model.bookslot.BookSlot;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BookSlotVM {
    FragmentManager fragmentManager;
    int startMinute , startHour , endMinute , endHour;
    public String TAG , date , sport;
    Context context;


    public BookSlotVM(FragmentManager manager , Context context){
        this.context = context;
        this.fragmentManager = manager;
    }

    public void datePicker(){
        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .build();

        datePicker.show(fragmentManager, "UserHome");
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                date = datePicker.getHeaderText();
            }
        });
    }

    public void startTimePicker(){
        TimePicker("Start Time");
    }

    public void endTimePicker(){
        TimePicker("End Time");
    }

    public void TimePicker(String TAG){
        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTitleText(TAG)
                .setHour(12)
                .setMinute(10)
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build();

        materialTimePicker.show(fragmentManager , "UserHome");

        materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TAG.equals("Start Time")){
                    startHour = materialTimePicker.getHour();
                    startMinute = materialTimePicker.getMinute();

                }else{
                    endHour = materialTimePicker.getHour();
                    endMinute = materialTimePicker.getMinute();
                }
            }
        });
    }


    public void bookSlot(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //Making different id for faster lookup times
        String uid = user.getUid();

        String date = this.date.replace(" ","_");
        String slot_id = String.valueOf(
                date +startHour +""+ startMinute +""  + endHour +"" + endMinute);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        //Check if the time slot is not booked already
        reference.child("slots").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(slot_id).exists()!=true){
                    BookSlot slot = new BookSlot( uid,TAG ,sport, date ,startMinute , startHour , endMinute , endHour );
                    reference.child("slots").child(slot_id).setValue(slot);
                    Toast.makeText(context, "Booked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Time Slot already booked", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
