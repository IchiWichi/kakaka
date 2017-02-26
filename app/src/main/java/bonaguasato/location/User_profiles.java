package bonaguasato.location;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class User_profiles extends AppCompatActivity {

    ImageButton myimagebutton, myimagebutton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profiles);

        myimagebutton = (ImageButton) findViewById(R.id.imageButtonowner);
        myimagebutton2 = (ImageButton)findViewById(R.id.imageButtonguest);

        myimagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(User_profiles.this, Homepage.class);
                startActivity(myIntent);
            }
        });
        myimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(User_profiles.this, Homepage.class);
                startActivity(myIntent);


            }
        });
    }
}
