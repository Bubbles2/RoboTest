package com.unowhy.sqool.robotest;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

public class SqoolCP extends ContentProvider {
	
	// Make sure package name Matches
	// Should match items in manifest
	// <permission android:name="com.matcom.estateagent.Prodets.WRITE_DATABASE" android:label="@string/app_write" android:protectionLevel="normal"></permission>
    // Provider description    android:readPermission="com.matcom.estateagent.Prodets.READ_DATABASE"
    	
	static final String PROVIDER_NAME = "com.unowhy.sqoolcp.SqoolCP";
	public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/chksqool");
	public static final Uri CONTENT_URI_CLOUD_LOG = Uri.parse("content://" + PROVIDER_NAME + "/cloudlog");
	private SqoolDBHelper  myOpenHelper;
	static final String DATABASE_TABLE_TABLE1 = "sqooldatabase";
	static final String DATABASE_TABLE_CLOUD_LOG = "sqoolcloudlog";
	static final String SEARCH_SUGGESTION_CLICKED = "Yes";

		//
	  // SQL statement to create a new database.
	  // Define table creation sql careful of table name 
    static final String DATABASE_CREATE =
    		"create table if not exists " +DATABASE_TABLE_TABLE1 + " ("
    	            + "_id integer primary key autoincrement, "
    	            + "sqoolkey text , "
    	    		+ "resourcename text, "
    	    		+ "objecttype text,  "
    	            + "school text, "
    	    		+ "class text  ,"
    	            + "student text, "
    	    		+ "resecourcepath text, "
    	    		+ "resourcecloudpath text, "
    	            + "resourcedata blob, "
    	            + "fldrlevel text, "
    	            + "teacher text, "
    	            + "subject text, "
    	            + "crtdate text, "
    	            + "moddate text, "
    	            + "sentdate text, "
    	            + "updatestatus integer, "
    	            + "consolidtated_search_field text,"
    	            + "md5_etag text, "
    	            + "synchronized integer, "
    	            + "msg_sender text, "
    	            + "msg_receiver text,"
    	            + "message text, "
    	            + "share integer, "
    	            + "origincloud integer, "
    	            + "origintablet integer,"
    	            + "obj_type integer, "
    	            + "file_or_doc integer,"
    	    		+ "file_size text, "
    	            + "spare1 text, "
    	    		+ "spare2 text, "
    	            + "spare3 text, "
    	    		+ "spare4 text, "
    	            + "spare5 text, "
    	    		+ "large_file_path text, "
    	            + "notif_opened integer, "
    	    		+ "spareint1 integer, "
    	            + "spareint2 integer, "
    	    		+ "spareint3 integer, "
    	            + "spareint4 integer, "
    	    		+ "spareint5 integer,"
    	            + "large_local_file_path text, "
    	    		+ "system_color integer);";
    
    //
    //
	// Define table creation sql careful of table name
	static final String DATABASE_CREATE_CLOUD_LOG =
			"create table if not exists " +DATABASE_TABLE_CLOUD_LOG + " (_id integer primary key autoincrement, "
					+ "student text ,messagetype text ,message text,"
					+ "reqdate text, updatesuccess integer,tabrecid string);";
	// Define field names for cloud log
	public static final String KEY_LOG_ID = "_id";
	static final String KEY_LOG_STUDENT = "student";
	static final String KEY_LOG_MSG_TYPE = "messagetype"; // update type STP, Move etc...
	static final String KEY_LOG_MSG = "message"; // update message
	static final String KEY_LOG_REQUEST_DATE = "reqdate";
	static final String KEY_LOG_UPDATE_SUCCESS = "updatesuccess";
	static final String KEY_LOG_TAB_RECID = "tabrecid";


 // fields  Table
 // Define field names
	  public static final String KEY_ID = "_id";
	  static final String KEY_SQOOLKEY = "sqoolkey";
	  static final String KEY_RESOURCENAME = "resourcename";
	  static final String KEY_OBJECTTYPE = "objecttype";
	  static final String KEY_SCHOOL  = "school";
	  static final String KEY_CLASS = "class";
	  static final String KEY_STUDENT = "student";
	  static final String KEY_RESOURCEPATH = "resecourcepath";
	  static final String KEY_RESOURCECLOUDPATH = "resourcecloudpath";
	  static final String KEY_RESOURCEDATA = "resourcedata";
	  static final String KEY_FLDRLEVEL  = "fldrlevel";
	  static final String KEY_TEACHER  = "teacher";
	  static final String KEY_SUBJECT  = "subject";
	  
	 
	  static final String KEY_UPDATE_STATUS = "updatestatus";
	  static final String KEY_CONSOLIDATED_SEARCH_FIELD = "consolidtated_search_field";
	  static final String KEY_CRT_DATE  = "crtdate";
	  static final String KEY_MOD_DATE  = "moddate";
	  static final String KEY_SENT_DATE  = "sentdate";
	  //
	  // My object additions
	  static final String KEY_MD5_ETAG  = "md5_etag";
	  static final String KEY_SYNCHRONIZED  = "synchronized";// 0=Not Synched 1= Synch Requested 2 = Synched
	  static final String KEY_MSG_SENDER  = "msg_sender";
	  static final String KEY_MSG_RECEIVER  = "msg_receiver";
	  static final String KEY_MSG  = "message";
	  static final String KEY_SHARE  = "share"; // Integer Share =1 or Send = 2 or  0
	  static final String KEY_ORIGIN_CLOUD  = "origincloud";
	  static final String KEY_ORIGIN_TABLET  = "origintablet";
	  static final String KEY_OBJ_TYPE  = "obj_type"; // image doc etc as integer
	  static final String KEY_FILE_DOC  = "file_or_doc";// File(1) or folder(0) (Integer 1 or 0)
	  static final String KEY_FILE_SIZE  = "file_size";
    //
        static final String KEY_SPARE1  = "spare1";
        static final String KEY_SPARE2  = "spare2";
        static final String KEY_SPARE3  = "spare3";
        static final String KEY_SPARE4  = "spare4";
        static final String KEY_SPARE5  = "spare5";

    // Used to show path of large files that were stored on cloud
        static final String KEY_LARGE_FILE_PATH  = "large_file_path";
    //
        static final String KEY_NOTIF_OPENED  = "notif_opened";
        static final String KEY_SPARE_INT1  = "spareint1";
        static final String KEY_SPARE_INT2  = "spareint2";
        static final String KEY_SPARE_INT3  = "spareint3";
        static final String KEY_SPARE_INT4  = "spareint4";
        static final String KEY_SPARE_INT5  = "spareint5";
     // Used to show path of large files that are stored on tablet but too big to save in CP
        public static final String KEY_LARGE_LOCAL_FILE_PATH  = "large_local_file_path";
        public static final String KEY_SYSTEM_COLOR  = "system_color";






    //
		// Create a Hash Map that we will use to display search suggestions  
	  private static final HashMap<String, String> SEARCH_PROJECTION_MAP;
	  static {
	    SEARCH_PROJECTION_MAP = new HashMap<String, String>();
	    SEARCH_PROJECTION_MAP.put(SearchManager.SUGGEST_COLUMN_TEXT_1, KEY_RESOURCENAME +
	      " AS " + SearchManager.SUGGEST_COLUMN_TEXT_1);
	    SEARCH_PROJECTION_MAP.put("_id", "_id" +
	      " AS " + "_id");
	    // This will be returned with the intent when a selection is made
	    //SEARCH_PROJECTION_MAP.put(SearchManager.SUGGEST_COLUMN_INTENT_DATA, KEY_ID +" AS " + SearchManager.SUGGEST_COLUMN_INTENT_DATA);	    
	    //
	    // This will add the ID to the returned uri
	    SEARCH_PROJECTION_MAP.put(SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID, KEY_ID +" AS " + SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID);
	  }
  


		  //
			private static final int ALLROWS = 1;
			private static final int SINGLE_ROW = 2;
			private static final int ALLROWS_LOG = 4;
			private static final int SINGLE_ROW_LOG = 5;
			// Search
			private static final int SEARCH = 3;
			private static final UriMatcher uriMatcher;

			  //Populate the UriMatcher object, where a URI ending in 'contacts' will
			  //correspond to a request for all items, and contacts/[rowID]'
			  //represents a single row.
			  static {
			   uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
			   uriMatcher.addURI(PROVIDER_NAME, "chksqool", ALLROWS);
			   uriMatcher.addURI(PROVIDER_NAME, "chksqool/#", SINGLE_ROW);
			   uriMatcher.addURI(PROVIDER_NAME, "cloudlog", ALLROWS_LOG);
				uriMatcher.addURI(PROVIDER_NAME, "cloudlog/#", SINGLE_ROW_LOG);
				
			   
			// Search
			   uriMatcher.addURI( "com.unowhy.sqoolcp.SqoolCP",
                       SearchManager.SUGGEST_URI_PATH_QUERY, SEARCH);
					    uriMatcher.addURI("com.unowhy.sqoolcp.SqoolCP",
                                SearchManager.SUGGEST_URI_PATH_QUERY + "/*", SEARCH);
					    uriMatcher.addURI("com.unowhy.sqoolcp.SqoolCP",
                                SearchManager.SUGGEST_URI_PATH_QUERY, SEARCH);
					    uriMatcher.addURI("com.unowhy.sqoolcp.SqoolCP",
                                SearchManager.SUGGEST_URI_PATH_QUERY + "/*", SEARCH);
			  } 
	

		
	  public int deleteOld(Uri uri, String selection, String[] selectionArgs) {
	    // Open a read / write database to support the transaction.
		   SQLiteDatabase db = myOpenHelper.getWritableDatabase();
		    
		    // If this is a row URI, limit the deletion to the specified row.
		    switch (uriMatcher.match(uri)) {
		      case SINGLE_ROW : 
		        String rowID = uri.getPathSegments().get(1);
		        selection = KEY_ID + "=" + rowID
		            + (!TextUtils.isEmpty(selection) ?
		              " AND (" + selection + ')' : "");
		        break;
		      default:
		    	  break;
		    }
		    
		    // To return the number of deleted items, you must specify a where
		    // clause. To delete all rows and return a value, pass in "1".
		    if (selection == null)
		      selection = "1";

		    // Execute the deletion.
		    int deleteCount = db.delete(DATABASE_TABLE_TABLE1, selection, selectionArgs);
		    
		    // Notify any observers of the change in the data set.
		    getContext().getContentResolver().notifyChange(uri, null);
		    
		    return deleteCount;
	    }
		 @Override
	  public int delete(Uri uri, String selection, String[] selectionArgs) {
		    // Open a read / write database to support the transaction.
		   SQLiteDatabase db = myOpenHelper.getWritableDatabase();
		   String tableToUse;
		    
		 
			int count;
		    switch (uriMatcher.match(uri)) {
		      case ALLROWS: 
		    	  tableToUse = DATABASE_TABLE_TABLE1;
		        break;
		      case SINGLE_ROW: 
		    	  String rowID = uri.getPathSegments().get(1);
			        selection = KEY_ID + "=" + rowID
			            + (!TextUtils.isEmpty(selection) ?
			              " AND (" + selection + ')' : "");
			        tableToUse = DATABASE_TABLE_TABLE1;
		        break;
		      case ALLROWS_LOG: 
		    	  tableToUse = DATABASE_TABLE_CLOUD_LOG;
			        break;
			      case SINGLE_ROW_LOG: 
			    	   rowID = uri.getPathSegments().get(1);
				        selection = KEY_LOG_ID + "=" + rowID
				            + (!TextUtils.isEmpty(selection) ?
				              " AND (" + selection + ')' : "");
				        tableToUse =DATABASE_TABLE_CLOUD_LOG;
			        break;
		      default: throw new IllegalArgumentException("Unknown URI " + uri);
		    }
		    
		    // To return the number of deleted items, you must specify a where
		    // clause. To delete all rows and return a value, pass in "1".
		    if (selection == null)
		      selection = "1";

		    // Execute the deletion.
		    int deleteCount = db.delete(tableToUse, selection, selectionArgs);
		    
		    // Notify any observers of the change in the data set.
		    getContext().getContentResolver().notifyChange(uri, null);
		    
		    return deleteCount;
	    }


	@Override
	public String getType(Uri uri) {
		  // Return a string that identifies the MIME type
	    // for a Content Provider URI
	    switch (uriMatcher.match(uri)) {
	      case ALLROWS: return "vnd.android.cursor.dir/vnd.matcom.contacts";
	      case SINGLE_ROW: return "vnd.android.cursor.item/vnd.matcom.contacts";
		   // search
	      case SEARCH  : return SearchManager.SUGGEST_MIME_TYPE;

	      default: throw new IllegalArgumentException("Unsupported URI: " + uri);}
	}
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// Open a read / write database to support the transaction.
		SQLiteDatabase db = myOpenHelper.getWritableDatabase();

		// To add empty rows to your database by passing in an empty Content Values
		// object, you must use the null column hack parameter to specify the name of
		// the column that can be set to null. (Need to check this out)
		String nullColumnHack = null;

		//
		Uri insertedId=null;
		long id = 0;

		switch (uriMatcher.match(uri)) {

			case ALLROWS:


				// Insert the values into the table
				id = 0;
				try {
					id = db.insert(DATABASE_TABLE_TABLE1,
							"", values);
				} catch (SQLException mSQLException) {
					//DebugTracer.traceError(mSQLException);
				}


				if (id > -1) {
					// Construct and return the URI of the newly inserted row.
					insertedId = ContentUris.withAppendedId(CONTENT_URI, id);

					// Notify any observers of the change in the data set.
					getContext().getContentResolver().notifyChange(insertedId, null);

					return insertedId;
				} else {
					throw new SQLException("Failed to Insert Record  " + uri);
					//return null;
				}

			case ALLROWS_LOG:


				// Insert the values into the table
				id = 0;
				try {
					id = db.insert(DATABASE_TABLE_CLOUD_LOG,
							"", values);
				} catch (SQLException mSQLException) {
					//DebugTracer.traceError(mSQLException);
				}


				if (id > -1) {
					// Construct and return the URI of the newly inserted row.
					insertedId = ContentUris.withAppendedId(CONTENT_URI_CLOUD_LOG, id);

					// Notify any observers of the change in the data set.
					getContext().getContentResolver().notifyChange(insertedId, null);

					return insertedId;
				} else {
					throw new SQLException("Failed to Insert Record  " + uri);
					//return null;
				}
			default: throw new SQLException("Failed to insert row into " + uri);


		}



	}

	@Override
	public boolean onCreate() {
		// Construct the underlying database.
	    // Defer opening the database until you need to perform
	    // a query or transaction.
	  myOpenHelper = new SqoolDBHelper(getContext());
	  SQLiteDatabase db = myOpenHelper.getWritableDatabase();
	  myOpenHelper.createTable(db, DATABASE_CREATE,DATABASE_CREATE_CLOUD_LOG);
	  //
	  //myOpenHelper.copyTableData(db);
	 	  return true;
	}

	
	public Cursor queryOLd(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		Log.d("DFSQL", "QUERY: " + uri + " >> " + selection);
		//myOpenHelper.close();
		// Open a read-only database.
		  SQLiteDatabase db = myOpenHelper.getReadableDatabase();
		  
			  
		    // Replace these with valid SQL statements if necessary.
		    String groupBy = null;
		    String having = null;
		    
		    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		    
		
		    queryBuilder.setTables(DATABASE_TABLE_TABLE1);
		    //queryBuilder.setTables("sqlite_master");
		    // Test Matcher
		    int test = uriMatcher.match(uri);
		    
		    // If this is a row query, limit the result set to the passed in row.
		    switch (uriMatcher.match(uri)) {
		      case SINGLE_ROW : 
		        String rowID = uri.getPathSegments().get(1);
		        queryBuilder.appendWhere(KEY_ID + "=" + rowID);
		        break;
		      case SEARCH :  // This is only for the suggestions search
			        String txt = uri.getPathSegments().get(1);
			        queryBuilder.appendWhere(KEY_RESOURCENAME + " LIKE \"%" + txt +"%\"");
			        queryBuilder.setProjectionMap(SEARCH_PROJECTION_MAP);
			        break;
		      default: break;
		    }
		    //queryBuilder.appendWhere(String.format("%s = '%s'", KEY_STUDENT, SqoolService.getEtabManager().getUserLogin()));
			queryBuilder.appendWhere(String.format("%s = '%s'", KEY_STUDENT, "df"));

         //String sql = queryBuilder.buildQuery(projection, selection, null, null, sortOrder, null);
        String sql = queryBuilder.buildQuery(projection, selection, groupBy, having, sortOrder, null);

        Log.d("DFSQL Statements", sql);
		    
		    Cursor cursor = queryBuilder.query(db, projection, selection,
		        selectionArgs, groupBy, having, sortOrder);


        return cursor;
	}
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		Log.d("DFSQL", "QUERY: " + uri + " >> " + selection);
		//myOpenHelper.close();
		// Open a read-only database.
		  SQLiteDatabase db = myOpenHelper.getReadableDatabase();
		  
			  
		    // Replace these with valid SQL statements if necessary.
		    String groupBy = null;
		    String having = null;
		    
		    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		    //queryBuilder.setTables("sqlite_master");
		    // Test Matcher
		    int test = uriMatcher.match(uri);
		    
		    // If this is a row query, limit the result set to the passed in row.
		    Cursor cursor=null;
		    switch (uriMatcher.match(uri)) {
		    case ALLROWS : 
			      queryBuilder.setTables(DATABASE_TABLE_TABLE1);
		    	  //queryBuilder.appendWhere(String.format("%s = '%s'", KEY_STUDENT, SqoolService.getEtabManager().getUserLogin()));
				  queryBuilder.appendWhere(String.format("%s = '%s'", KEY_STUDENT, "df"));
		          String sql = queryBuilder.buildQuery(projection, selection, groupBy, having, sortOrder, null);
		          cursor = queryBuilder.query(db, projection, selection,
		  		  selectionArgs, groupBy, having, sortOrder);
		          break;		         
		    case ALLROWS_LOG :
		    	  queryBuilder.setTables(DATABASE_TABLE_CLOUD_LOG);
		    	  //queryBuilder.appendWhere(String.format("%s = '%s'", KEY_LOG_STUDENT, SqoolService.getEtabManager().getUserLogin()));
				  queryBuilder.appendWhere(String.format("%s = '%s'", KEY_STUDENT, "df"));
		          sql = queryBuilder.buildQuery(projection, selection, groupBy, having, sortOrder, null);
		          cursor = queryBuilder.query(db, projection, selection,
				  		  selectionArgs, groupBy, having, sortOrder);
		        break;
		      case SINGLE_ROW : 		    	  
		        String rowID = uri.getPathSegments().get(1);
		        queryBuilder.appendWhere(KEY_ID + "=" + rowID);
		        sql = queryBuilder.buildQuery(projection, selection, groupBy, having, sortOrder, null);
		          cursor = queryBuilder.query(db, projection, selection,
				  		  selectionArgs, groupBy, having, sortOrder);
		        break;
		      case SINGLE_ROW_LOG :
		    	  queryBuilder.setTables(DATABASE_TABLE_CLOUD_LOG);
			        rowID = uri.getPathSegments().get(1);
			        queryBuilder.appendWhere(KEY_ID + "=" + rowID);
			        sql = queryBuilder.buildQuery(projection, selection, groupBy, having, sortOrder, null);
			          cursor = queryBuilder.query(db, projection, selection,
					  		  selectionArgs, groupBy, having, sortOrder);
			        break;
		      case SEARCH :  // This is only for the suggestions search
		    	  queryBuilder.setTables(DATABASE_TABLE_TABLE1);
			        String txt = uri.getPathSegments().get(1);
			        queryBuilder.appendWhere(KEY_RESOURCENAME + " LIKE \"%" + txt +"%\"");
			        queryBuilder.setProjectionMap(SEARCH_PROJECTION_MAP);
			        break;
		      default:
		    	  
		    	  break;
		    }
		

       // Log.d("DFSQL Statements", sql);
		    return cursor;
		    
		   
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		

	
		// Open a read / write database to support the transaction.
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        
		int count;
	    switch (uriMatcher.match(uri)) {
	      case ALLROWS: 
	        count = db.update(DATABASE_TABLE_TABLE1, 
	                                values, selection, selectionArgs);
	        break;
	      case SINGLE_ROW: 
	        String segment = uri.getPathSegments().get(1);
	        count = db.update(DATABASE_TABLE_TABLE1, 
	                                values, KEY_ID
	                                  + "=" + segment
	                                  + (!TextUtils.isEmpty(selection) ? " AND ("
	                                  + selection + ')' : ""), selectionArgs);
	        break;
	      case ALLROWS_LOG: 
		        count = db.update(DATABASE_TABLE_CLOUD_LOG, 
		                                values, selection, selectionArgs);
		        break;
		      case SINGLE_ROW_LOG: 
		        segment = uri.getPathSegments().get(1);
		        count = db.update(DATABASE_TABLE_CLOUD_LOG, 
		                                values, KEY_ID
		                                  + "=" + segment
		                                  + (!TextUtils.isEmpty(selection) ? " AND ("
		                                  + selection + ')' : ""), selectionArgs);
		        break;
	      default: throw new IllegalArgumentException("Unknown URI " + uri);
	    }

	    getContext().getContentResolver().notifyChange(uri, null);
	    return count;
	}
//



    }
