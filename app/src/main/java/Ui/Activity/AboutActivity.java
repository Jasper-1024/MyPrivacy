package Ui.Activity;

import android.didikee.donate.AlipayDonate;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jasperhale.myprivacy.R;

import org.w3c.dom.Text;

public class AboutActivity extends AppCompatActivity {

    private final String payCode = "FKX09422HHZKGGZXYXYI8D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar_about);
        toolbar.setTitle(R.string.about);
        setSupportActionBar(toolbar);
        //返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        TextView Bug = (TextView)findViewById(R.id.Bug);
        Bug.setMovementMethod(LinkMovementMethod.getInstance());

        TextView or = (TextView)findViewById(R.id.or);
        or.setMovementMethod(LinkMovementMethod.getInstance());

        TextView Email = (TextView)findViewById(R.id.Email);
        Email.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void onClick(View view){
        donateAlipay(payCode);
    }

    //返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void donateAlipay(String payCode) {
        boolean hasInstalledAlipayClient = AlipayDonate.hasInstalledAlipayClient(this);
        if (hasInstalledAlipayClient) {
            AlipayDonate.startAlipayClient(this, payCode);
        }
    }
}
