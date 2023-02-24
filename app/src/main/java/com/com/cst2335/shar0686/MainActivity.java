package com.com.cst2335.shar0686;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *This is a main class of Lab 5
 * @author samar
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;


    /**
     * This method is called when activity is started
     * @param savedInstanceState Saves the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab5_activity);
        TextView textviewPassword=findViewById(R.id.textView2);
        EditText passwordEntered = findViewById(R.id.editTextTextPassword2);
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(clk -> {
            String password = passwordEntered.getText().toString();
            if(!checkPasswordComplexity(password)){
                textviewPassword.setText("You shall not pass!");
            }
            else{
                textviewPassword.setText("You meet the requirements");
            }
        });
    }

    /**
     *It will check the right format of the input and display output according to that
     * @param pw
     * @return true if valid, otherwise false
     */
    Boolean checkPasswordComplexity(String pw){
        String toast_message;
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial, lengthValid;
        lengthValid= foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;
        if(pw.length()<=20 &&pw.length()>=4){
            lengthValid=true;
        }
        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }
        if (!lengthValid) {
            Toast.makeText(this, R.string.lengthValid, Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!foundNumber) {
            Toast.makeText(this, R.string.Foundnumber, Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!foundLowerCase) {
            Toast.makeText(this, R.string.FoundLowerCase, Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!foundUpperCase) {
            Toast.makeText(this, R.string.FoundUpperCase, Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!foundSpecial) {
            Toast.makeText(this, R.string.FoundSpecial, Toast.LENGTH_LONG).show();
            return false;
        }
        else
            return true;
    }

    /**
     *this method has a character that are needed
     * @param c the character to be reviewed
     * @return true if special character, otherwise false
     */
    boolean isSpecialCharacter(char c) {
        switch(c){
            case '#':
                case '$':
                    case '&':
                        case '%':
                            case '^':
                                case '*':
                                    case '!':
                                        case '@':
                                            case '?':
                                                return  true;
            default:
                return false;
        }
    }

}