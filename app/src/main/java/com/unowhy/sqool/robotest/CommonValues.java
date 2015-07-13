package com.unowhy.sqool.robotest;

/**
 * Created by dfinlay-air on 13/03/15.
 */
public class CommonValues {
    // Folder to be used as a directory for temp downloads
    public static final String KEY_WORK_DIRECTORY = "cloudDownload";
    // Data Base Fields
    // Define field names for cloud log
    // The following requests are logged Synch,Copy,STP,Move,Delete
    public static final String KEY_LOG_ID = "_id";
    public static final String KEY_LOG_STUDENT = "student";
    public static final String KEY_LOG_MSG_TYPE = "messagetype"; // update type STP, Move etc...
    public static final String KEY_LOG_MSG = "message"; // update message
    public static final String KEY_LOG_REQUEST_DATE = "reqdate";
    public static final String KEY_LOG_UPDATE_SUCCESS = "updatesuccess";
    public static final String KEY_LOG_TAB_RECID = "tabrecid";

    // Constants
    public static final String KEY_ID = "_id";
    public static final String KEY_SQOOLKEY = "sqoolkey";
    public static final String KEY_RESOURCENAME = "resourcename";
    public static final String KEY_OBJECTTYPE = "objecttype";
    public static final String KEY_SCHOOL  = "school";
    public static final String KEY_CLASS = "class";
    public static final String KEY_STUDENT = "student";
    public static final String KEY_RESOURCEPATH = "resecourcepath";
    public static final String KEY_RESOURCEDATA = "resourcedata";
    public static final String KEY_FLDRLEVEL  = "fldrlevel";
    public static final String KEY_TEACHER  = "teacher";
    public static final String KEY_SUBJECT  = "subject";


    public static final String KEY_UPDATE_STATUS = "updatestatus";
    public static final String KEY_CONSOLIDATED_SEARCH_FIELD = "consolidtated_search_field";
    public static final String KEY_CRT_DATE  = "crtdate";
    public static final String KEY_MOD_DATE  = "moddate";
    //
    // My object additions
    public static final String KEY_MD5_ETAG  = "md5_etag";
    public static final String KEY_SYNCHRONIZED  = "synchronized";// 0=Not Synched 1= Synch Requested 2 = Synched
    public static final String KEY_MSG_SENDER  = "msg_sender";
    public static final String KEY_MSG_RECEIVER  = "msg_receiver";
    public static final String KEY_MSG  = "message";
    public static final String KEY_SHARE  = "share"; // Integer 1 shared yes or 0 no direct send
    public static final String KEY_ORIGIN_CLOUD  = "origincloud";
    public static final String KEY_ORIGIN_TABLET  = "origintablet";
    public static final String KEY_OBJ_TYPE  = "obj_type"; // image doc etc as integer
    public static final String KEY_FILE_DOC  = "file_or_doc";// File(1) or folder(0) (Integer 1 or 0)
    public static final String KEY_FILE_SIZE  = "file_size";
    public static final String KEY_RESOURCE_CLOUD_PATH  = "resourcecloudpath";

    //
    public final static String TAG = "Sqool production ";

    public final static Boolean IS_DEBUG = true;

    //
    public static final String KEY_SPARE1  = "spare1";
    public static final String KEY_SPARE2  = "spare2";
    public static final String KEY_SPARE3  = "spare3";
    public static final String KEY_SPARE4  = "spare4";
    public static final String KEY_SPARE5  = "spare5";
    // Used to show path of large files that were stored on cloud
    public static final String KEY_LARGE_FILE_PATH  = "large_file_path";



    public static final String KEY_NOTIF_OPENED  = "notif_opened";
    public static final String KEY_SPARE_INT1  = "spareint1";
    public static final String KEY_SPARE_INT2  = "spareint2";
    public static final String KEY_SPARE_INT3  = "spareint3";
    public static final String KEY_SPARE_INT4  = "spareint4";
    public static final String KEY_SPARE_INT5  = "spareint5";

    public static final long LOCAL_MAX_SIZE_FILE = 10*1024*1024; //10Mo
    public static final long CLOUD_MAX_SIZE_FILE = 100000; //10Mo
    // Used to show path of large files that are stored on tablet but too big to save in CP
    public static final String KEY_LARGE_LOCAL_FILE_PATH  = "large_local_file_path";
    public static final String KEY_SYSTEM_COLOR  = "system_color"; // 1: system, 0 :



}
