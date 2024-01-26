package configuracion;

public class Transaciones {
    //nombre de la base de datos
    public static final  String DBName = "PMO12024";
        //nombre de la tabla
    public static final  String Tablepersonas = "personas";
    //creacion de los campus de la base de datos
    public static final  String id = "id";
    public static final  String nombre = "nombre";
    public static final  String apellidos = "apellidos";
    public static final  String edad = "edad";
    public static final  String correo = "correo";

    //ddl crate
    public static final  String CreateTablePersonas = "CREATE TABLE " + Tablepersonas + "(" +
           " id  INTEGER PRIMARY KEY AUTOINCREMENT , nombre  TEXT,  apellidos  TEXT,  edad INTEGER,"+
            "correo TEXT) ";

    //ddl drop
    public static final  String DropTablePersonas = "DROP TABLE IF EXISTS " + Tablepersonas ;

    //dml
    public static final  String SelectAllPersonas = "SELECT *FROM " + Tablepersonas ;

}
