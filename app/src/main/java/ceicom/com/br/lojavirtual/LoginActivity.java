package ceicom.com.br.lojavirtual;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private EditText edtLogin, edtSenha;

    private Button btnLogin;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLogin = (EditText) findViewById(R.id.et_login);
        edtSenha = (EditText) findViewById(R.id.et_passWord);

        btnLogin = (Button) findViewById(R.id.btn_login);

        preferences = getPreferences(Context.MODE_PRIVATE);
        String login = preferences.getString("login", null);
        String password = preferences.getString("password", null);

        if(login != null && password != null){
            Toast toast = Toast.makeText(this, "valido", Toast.LENGTH_LONG);
            toast.show();
            //Intent i = new Intent(LoginActivity.this, novaActivit.class);
            //startActivity(i);
            finish();
        }
    }

    private boolean validarCamposLogin(String login, String password) {
        boolean result = true;
        if (login == null || "".equals(login)) {
            edtLogin.setError("Campo obrigatório");
            edtSenha.requestFocus();
            result = false;
        } else if (password == null || "".equals(password)) {
            edtSenha.setError("Campo obrigatório");
            edtSenha.requestFocus();
            result = false;
        }

        if(result){
            if(!login.equals("admin") || !password.equals("admin")){
                Toast toast = Toast.makeText(this, "Login/Senha Inválidos", Toast.LENGTH_LONG);
                toast.show();
                result = false;
            }else{
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("login", login);
                editor.putString("password", password);
                editor.commit();
            }
        }
        return result;
    }

    public void btnLogin_click(View v) {
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        boolean isValido = validarCamposLogin(login, senha);
        if (isValido) {
            Toast toast = Toast.makeText(this, "valido", Toast.LENGTH_LONG);
            toast.show();
            //Intent i = new Intent(LoginActivity.this, novaActivit.class);
            //startActivity(i);
            finish();
        }
    }
}
