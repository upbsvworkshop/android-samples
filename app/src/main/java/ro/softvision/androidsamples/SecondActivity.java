package ro.softvision.androidsamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView mEditText;
    boolean mShouldReturnData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mEditText = (TextView) findViewById(R.id.edit_text);
        findViewById(R.id.btn_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShouldReturnData) {
                    finishWithData();
                } else {
                    finishNormally();
                }
            }
        });

        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent.hasExtra("data")) {
            String data = intent.getStringExtra("data");
            Toast.makeText(this, "Main Activity sent: " + data, Toast.LENGTH_SHORT).show();
            mEditText.setText(data);
        }
        mShouldReturnData = intent.getBooleanExtra("return", false);
    }

    /**
     * Finish this activity and return to the previous one without returning any data
     */
    private void finishNormally() {
        setResult(RESULT_OK);
        finish();
    }

    /**
     * Finish this activity and return to the previous one while returning data (a String)
     */
    private void finishWithData() {
        Intent data = new Intent();
        data.putExtra("data", mEditText.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}
