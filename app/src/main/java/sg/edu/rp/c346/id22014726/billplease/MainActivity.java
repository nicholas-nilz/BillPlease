package sg.edu.rp.c346.id22014726.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView amtDisplay;
    EditText editAmtDisplay;
    TextView noOfPaxDisplay;
    EditText editNoOfPaxDisplay;
    ToggleButton serviceChargeApplicable;
    ToggleButton gstApplicable;
    TextView discountDisplay;
    EditText editDiscountDisplay;
    ToggleButton splitAmount;
    ToggleButton resetPage;
    TextView totalAmt;
    TextView individualAmt;
    RadioButton payNow;
    RadioButton cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAmtDisplay = findViewById(R.id.editAmount);
        editNoOfPaxDisplay = findViewById(R.id.editNoOfPax);
        serviceChargeApplicable = findViewById(R.id.ServiceCharge);
        gstApplicable = findViewById(R.id.GST);
        editDiscountDisplay = findViewById(R.id.editDiscount);
        splitAmount = findViewById(R.id.splitBill);
        resetPage = findViewById(R.id.resetBill);
        totalAmt = findViewById(R.id.TotalBill);
        individualAmt = findViewById(R.id.Individual);
        cash = findViewById(R.id.PaymentMethod1);
        payNow = findViewById(R.id.PaymentMethod2);

        splitAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editAmtDisplay.getText().toString().trim().length() != 0
                        && editNoOfPaxDisplay.getText().toString().trim().length() != 0) {
                   double newTotal = 0.0;
                   if(serviceChargeApplicable.isChecked() == false && gstApplicable.isChecked() == false) {
                       newTotal = Double.parseDouble(editAmtDisplay.getText().toString()); }
                   else if(serviceChargeApplicable.isChecked() == true && gstApplicable.isChecked() == false) {
                       newTotal = Double.parseDouble(editAmtDisplay.getText().toString()) * 1.1; }
                   else if(serviceChargeApplicable.isChecked() == false && gstApplicable.isChecked() == true) {
                       newTotal = Double.parseDouble(editAmtDisplay.getText().toString()) * 1.07; }
                   else {
                       newTotal = Double.parseDouble(editAmtDisplay.getText().toString()) * 1.17;
                   }

                   if(editDiscountDisplay.getText().toString().trim().length() != 0) {
                       double discountPercentage = Double.parseDouble(editDiscountDisplay.getText().toString());
                       double discountAmount = newTotal * (discountPercentage / 100);
                       newTotal = newTotal - discountAmount;
                   }

                   totalAmt.setText("Total Bill: $" + String.format("%.2f", newTotal));
                   int totalPax = Integer.parseInt(editNoOfPaxDisplay.getText().toString());
                   if (totalPax > 1)
                       individualAmt.setText("Each Pays: $" + String.format("%.2f", newTotal));
                   else
                       individualAmt.setText("Each Pays: $" + newTotal);

                }
            }
        });

        resetPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAmtDisplay.setText("");
                editNoOfPaxDisplay.setText("");
                serviceChargeApplicable.setChecked(false);
                gstApplicable.setChecked(false);
                editDiscountDisplay.setText("");

            }
        });

    }
}