package company.colegiosqllite;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Manifest;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AdapterBBDD ddbb = new AdapterBBDD(this); //llamamos a la clase que contiene el acceso a la bbdd y los metodos



       final EditText nombre= (EditText)findViewById(R.id.nombre);
        final EditText edad= (EditText)findViewById(R.id.edadal);
        final EditText ciclo= (EditText)findViewById(R.id.ciclo);
        final EditText curso= (EditText)findViewById(R.id.curso);
        final EditText media= (EditText)findViewById(R.id.medial);
        final EditText despacho= (EditText)findViewById(R.id.despacho);
        final EditText eliminar= (EditText)findViewById(R.id.ideleminar);





        Button alumno =  (Button)findViewById(R.id.agregaralumno);
        Button profesor = (Button)findViewById(R.id.profesor);
        Button borrar = (Button)findViewById(R.id.delete);
        Button recuperar = (Button)findViewById(R.id.recuperar);


        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RecuperarActivity.class);
                startActivity(intent);
            }
        });



        //añadimos un alumno cuando apretemos el boton agregar alumno

        alumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ddbb.open();

                //hacemos un recorrido para que ningun campo esté vacio
                if(nombre.length()!=0 && ciclo.length()!=0 && curso.length()!=0 && edad.getText().toString() != null && media.getText().toString() != null  ) {

                    //insertamos los datos de los EditText
                    ddbb.insertarAlumno(nombre.getText().toString(),Integer.parseInt(edad.getText().toString()) , ciclo.getText().toString(), curso.getText().toString(), Double.parseDouble(
                            media.getText().toString()));
                    ddbb.close();//cerramos la conexion a la bbdd
                    Toast.makeText(getBaseContext(), "añadido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "No se ha podido añadir",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        profesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ddbb.open();
                if(nombre.length()!=0 && ciclo.length()!=0 && curso.length()!=0 && edad.getText().toString() != null && despacho.getText().toString() != null  ){

                    ddbb.insertarProfesor(nombre.getText().toString(), Integer.parseInt(edad.getText().toString()), ciclo.getText().toString(), curso.getText().toString(), Double.parseDouble(
                            despacho.getText().toString()));
                    Toast.makeText(getBaseContext(), "añadido", Toast.LENGTH_SHORT).show();
                    ddbb.close();
                }else{
                    Toast.makeText(getBaseContext(), "No se ha podido añadir", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //borrar una persona por su id
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eliminar.getText().length()!=0){ //comprobamos que no este vacio
                    ddbb.open();
                    ddbb.borrarPersona(Integer.parseInt(eliminar.getText().toString()));
                    //borramos esa persona
                    Toast.makeText(getBaseContext(), "borrado", Toast.LENGTH_SHORT).show();
                    ddbb.close();
                }else
                    Toast.makeText(getBaseContext(), "no se ha podido borrar", Toast.LENGTH_SHORT).show();
                ddbb.close();
            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    }

