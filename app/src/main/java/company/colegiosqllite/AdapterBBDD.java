package company.colegiosqllite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class AdapterBBDD  {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "col.db";
    private static final String DATABASE_TABLE = "alumno";
    private static final String DATABASE_TABLE2 = "profesor";
    private static final int DATABASE_VERSION = 1;

    public static final  String KEY_ROWID = "_id";
    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String MEDIA = "media";
    private static final String DESPACHO = "despacho";



    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE +  "(" +KEY_ROWID + " integer primary key autoincrement, nombre text, edad integer, ciclo text, curso text, media double);";
    private static final String DATABASE_CREATE2 = "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE2 +  "(" +KEY_ROWID + " integer primary key autoincrement, nombre text, edad integer, ciclo text, curso text, despacho text);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DATABASE_TABLE+";";
    private static final String DATABASE_DROP2 = "DROP TABLE IF EXISTS "+DATABASE_TABLE2+";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public AdapterBBDD (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }


    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void close()throws SQLiteException
    {

            dbHelper.close();//cerramos la bbdd

        }

    public boolean borrarPersona(int rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }




    public void insertarProfesor(String n, int e, String ci, String cu, double d){

        //nombre, edad, ciclo y curso en donde es tutor y despacho.
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,n);
        newValues.put(EDAD,e);
        newValues.put(CICLO,ci);
        newValues.put(CURSO,cu);
        newValues.put(DESPACHO,d);

        db.insert(DATABASE_TABLE2,null,newValues);
    }


    public void insertarAlumno(String n, int e, String ci, String cu, double m){




        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,n);
        newValues.put(EDAD,e);
        newValues.put(CICLO,ci);
        newValues.put(CURSO,cu);
        newValues.put(MEDIA,m);
        db.insert(DATABASE_TABLE,null,newValues);
    }

    public ArrayList<String> recuperarAlumno(){
        ArrayList<String> discos = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                discos.add(cursor.getString(0)+" - "+cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return discos;
    }

    public ArrayList<String> recuperarprofesor(){
        ArrayList<String> discos = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE2,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                discos.add(cursor.getString(0)+" - "+cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return discos;
    }

    public ArrayList<String> recuperarCiclo(String columna){
        ArrayList<String> discos = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE2,null,columna,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                discos.add(cursor.getString(0)+" - "+cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return discos;
    }

    public ArrayList<String> recuperarCurso(String columna){
        ArrayList<String> discos = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE2,null,columna,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                discos.add(cursor.getString(0)+" - "+cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return discos;
    }
    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            db.execSQL(DATABASE_DROP2);
            onCreate(db);
        }

    }
}
