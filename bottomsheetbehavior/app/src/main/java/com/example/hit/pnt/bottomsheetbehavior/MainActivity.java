package com.example.hit.pnt.bottomsheetbehavior;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hit.pnt.bottomsheetbehavior.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity {

    TextView textViewBottomSheetState;
    Button toggleBottomSheet;
    BottomSheetBehavior bottomSheetBehavior;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // bind UI
        toggleBottomSheet = findViewById(R.id.buttonToggleBottomSheet);
        textViewBottomSheetState = findViewById(R.id.textViewState);

        // get the bottom sheet view
        ConstraintLayout bottomSheetLayout = findViewById(R.id.bottom_sheet);

        // init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        textViewBottomSheetState.setText("STATE HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        textViewBottomSheetState.setText("STATE EXPANDED");
                        // update toggle button text
                        toggleBottomSheet.setText("Expand BottomSheet");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        textViewBottomSheetState.setText("STATE COLLAPSED");
                        // update collapsed button text
                        toggleBottomSheet.setText("Collapse BottomSheet");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        textViewBottomSheetState.setText("STATE DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        textViewBottomSheetState.setText("STATE SETTLING");
                        break;
                }

                Log.d(TAG, "onStateChanged: " + newState);
            }

            @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        // set listener on button click
        toggleBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    toggleBottomSheet.setText("Collapse BottomSheet");
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    toggleBottomSheet.setText("Expand BottomSheet");
                }
            }
        });
    }
}