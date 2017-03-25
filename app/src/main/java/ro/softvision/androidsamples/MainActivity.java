package ro.softvision.androidsamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final int CODE_SECOND_ACTIVITY = 0;

    private TextView mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (TextView) findViewById(R.id.edit_text);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_start_data).setOnClickListener(this);
        findViewById(R.id.btn_start_data_both).setOnClickListener(this);
        findViewById(R.id.btn_start_finish).setOnClickListener(this);
    }

    /**
     * Start SecondActivity without sending any data or receiving
     */
    private void startActivity2Normally() {
        Log.d(TAG, "Starting SecondActivity without any data sent or received");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    /**
     * Start SecondActivity sending data without receiving anything
     */
    private void startActivity2SendingData() {
        Log.d(TAG, "Starting SecondActivity and sending data");
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("data", mEditText.getText().toString());
        startActivity(intent);
    }

    /**
     * Start SecondActivity sending data and receiving back
     */
    private void startActivity2DataBothWays() {
        Log.d(TAG, "Starting SecondActivity with data sent and received");
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("data", mEditText.getText().toString());
        //  In this sample we are signalling that we want some data back
        intent.putExtra("return", true);
        startActivityForResult(intent, CODE_SECOND_ACTIVITY);
    }

    /**
     * Start SecondActivity and finish the current one
     */
    private void startActivity2FinishingCurrent() {
        Log.d(TAG, "Starting SecondActivity without any data sent or received and finishing current");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * In this method we can check if the Activity we started returned any data, as well as the return
     * code (Cancelled or OK)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_SECOND_ACTIVITY:
                    Log.d(TAG, "onActivityResult: SecondActivity sent back: " + data.getStringExtra("data"));
                    Toast.makeText(this, "SecondActivity sent back: " + data.getStringExtra("data"),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                startActivity2Normally();
                break;
            case R.id.btn_start_data:
                startActivity2SendingData();
                break;
            case R.id.btn_start_data_both:
                startActivity2DataBothWays();
                break;
            case R.id.btn_start_finish:
                startActivity2FinishingCurrent();
                break;
        }
    }
}
