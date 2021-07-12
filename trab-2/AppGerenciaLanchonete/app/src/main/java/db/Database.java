package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database  extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Lanchonete.db";

    private static final String SQL_CREATE_GARCONS = "CREATE TABLE garcons (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, CPF TEXT, genero TEXT, nascimento DATETIME)";
    private static final String SQL_POPULATE_GARCONS = "INSERT INTO garcons VALUES (null,'Lucas Lima','02246898744','masculino','1996-04-03')";
    private static final String SQL_DELETE_GARCONS = "DROP TABLE IF EXISTS garcons";

    private static final String SQL_CREATE_CARDAPIO = "CREATE TABLE cardapio (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, preco FLOAT)";
    private static final String SQL_POPULATE_CARDAPIO = "INSERT INTO cardapio VALUES (null,'Pizza de peperoni',50.0)";
    private static final String SQL_DELETE_CARDAPIO = "DROP TABLE IF EXISTS cardapio";

    private static final String SQL_CREATE_MESAS = "CREATE TABLE mesas (id INTEGER PRIMARY KEY AUTOINCREMENT, numeroClientes INTEGER, ocupada BOOLEAN)";
    private static final String SQL_POPULATE_MESAS = "INSERT INTO mesas VALUES (null,0,false)";
    private static final String SQL_DELETE_MESAS = "DROP TABLE IF EXISTS mesas";

    private static final String SQL_CREATE_PEDIDOS = "CREATE TABLE pedidos (id INTEGER PRIMARY KEY AUTOINCREMENT, pedido_numero INTEGER, mesa_numero INTEGER, quantidade INTEGER, status TEXT)";
    private static final String SQL_POPULATE_PEDIDOS = "INSERT INTO pedidos VALUES (null,1,1,2,'pendente')";
    private static final String SQL_DELETE_PEDIDOS = "DROP TABLE IF EXISTS pedidos";


    public Database(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_GARCONS);
        db.execSQL(SQL_POPULATE_GARCONS);

        db.execSQL(SQL_CREATE_CARDAPIO);
        db.execSQL(SQL_POPULATE_CARDAPIO);

        db.execSQL(SQL_CREATE_MESAS);
        db.execSQL(SQL_POPULATE_MESAS);

        db.execSQL(SQL_CREATE_PEDIDOS);
        db.execSQL(SQL_POPULATE_PEDIDOS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersionm, int newVersion){
        db.execSQL(SQL_DELETE_GARCONS);
        db.execSQL(SQL_DELETE_CARDAPIO);
        db.execSQL(SQL_DELETE_MESAS);
        db.execSQL(SQL_DELETE_PEDIDOS);
        onCreate(db);
    }
}
