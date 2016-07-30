package company.colegiosqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class RecuperarActivity extends AppCompatActivity {
    private AdapterBBDD dbAdapter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);


        dbAdapter = new AdapterBBDD(this);
        dbAdapter.open();

         textView = (TextView) this.findViewById(R.id.textView5);
        final Button alumnos = (Button)findViewById(R.id.buscarAlumno);
        Button profesores = (Button)findViewById(R.id.buscarProfesor);
        Button ciclos = (Button)findViewById(R.id.BuscarCiclo);
        Button curso = (Button)findViewById(R.id.buscarCurso);
        EditText ciclo = (EditText)findViewById(R.id.recCiclo);
        EditText cursos=(EditText)findViewById(R.id.recCurso);


        alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> personas = dbAdapter.recuperarAlumno();
                String devolver ="";

                for(int i = 0 ; i<personas.size(); i++) {
                   devolver = devolver + personas.get(i);
                }

                textView.setText(devolver + "\n");
                }

        });

        profesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> personas = dbAdapter.recuperarprofesor();
                for (int i = 0; i < personas.size(); i++) {
                    textView.setText(personas.get(0) + "\n");
                }
            }

        });


        final String s = "ciclo="+ciclo.getText().toString();
        final String r = "curso="+ciclo.getText().toString();

        ciclos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> personas = dbAdapter.recuperarCiclo(s);
                for(int i = 0 ; i<personas.size(); i++) {
                    textView.setText(personas.get(0) + "\n");
                }
            }
        });

        curso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> personas = dbAdapter.recuperarCurso(r);
                for(int i = 0 ; i<personas.size(); i++) {
                    textView.setText(personas.get(0) + "\n");
                }
            }
        });
    }
}
