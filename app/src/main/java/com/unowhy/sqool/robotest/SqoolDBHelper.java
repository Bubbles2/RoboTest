package com.unowhy.sqool.robotest;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SqoolDBHelper extends SQLiteOpenHelper {
	
	//The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.unowhy.sqoolcp/databases/";
    private static String DB_NAME = "realestatedb";
    private final Context myContext;
    private SQLiteDatabase myDataBase;
    static final int DB_VERSION = 1;
	  

    /**
     * Constructor
     */
    
    public SqoolDBHelper(Context context) {
 
    	super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
    }



    @Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		

	}
    public void createTable(SQLiteDatabase db, String table1,String table2) {
		try {
			db.execSQL(table1);
            db.execSQL(table2);
			}		
		 catch (SQLException e) {
			// DebugTracer.traceError(e);
		}


	}
	
	  public SQLiteDatabase openDataBase() throws SQLException {
		  
	    	//Open the database
	        String myPath = DB_PATH + DB_NAME;
	    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	    	return myDataBase;
	 
	    }
	 
	    @Override
		public synchronized void close() {
	 
	    	    if(myDataBase != null)
	    		    myDataBase.close();
	 
	    	    super.close();
	 
		}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
        Log.w("SqoolCP", "Upgrading from version " +
				oldVersion + " to " +
				newVersion + ", which will destroy all old data");
        //
      

    }


//=================================================================================
// Data Base Manipulation routines - used mainly for testing and updating
// DB manually
//
//=================================================================================
	 
/**
  * Copies your database from your local assets-folder to the just created empty database in the
  * system folder, from where it can be accessed and handled.
  * This is done by transfering bytestream.
  * */
 void importDataBase() throws IOException {
 	//Open your local db as the input stream
 	InputStream myInput = myContext.getAssets().open(DB_NAME);
 	// Path to the just created empty db
 	String outFileName = DB_PATH + DB_NAME;
 	//Open the empty db as the output stream
 	OutputStream myOutput = new FileOutputStream(outFileName);
 	//transfer bytes from the inputfile to the outputfile
 	byte[] buffer = new byte[1024];
 	int length;
 	while ((length = myInput.read(buffer))>0){
 		myOutput.write(buffer, 0, length);
 	}

 	//Close the streams
 	myOutput.flush();
 	myOutput.close();
 	myInput.close();

 }
 //
 public void deleteDB(SQLiteDatabase db) {
		myContext.deleteDatabase(DB_NAME);

		}

   
    
    
    

}
