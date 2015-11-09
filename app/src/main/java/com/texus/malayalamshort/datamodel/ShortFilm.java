/**
 *
 * Copyright 2015 Sandeep Kumar P K
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.texus.malayalamshort.datamodel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.feathernet.masara.db.Databases;
import com.feathernet.masara.utils.LOG;
import com.feathernet.masara.utils.Utility;
import com.texus.malayalamshort.xml.XMLElement;
import com.texus.malayalamshort.xml.XMLTree;

import java.util.ArrayList;

/**
 * @author Sandeep Kumar P K <br>
 *         <a
 *         href="mailto:pksandeepkumar@gmail.com">pksandeepkumar@gmail.com</a>
 */
public class ShortFilm extends BaseDataModel{

	public static final String TABLE_NAME = "TableShortFilm";
	
	
	public static final String ID = "id";
	public static final String NAME = "Name";
	public static final String LINK = "Link";
	public static final String DESCRIPTION = "Description";
	public static final String FAVORITE = "favorite";

	/** PK */
	public int id;
	public String Name;
	public String Link;
	public String Description;
	public boolean favorite;


//	<ShortFilm id="" Name="" Link="" Description=""  />

	public static final String CREATE_TABE_QUERY = "CREATE TABLE  " + TABLE_NAME 
			+ " ( " + "_id" + " INTEGER  PRIMARY KEY AUTOINCREMENT, " 
			+ ID + " INTEGER , "
			+ NAME + " TEXT, "
			+ LINK + " TEXT, "
			+ DESCRIPTION + " TEXT, "
			+ FAVORITE + " VARCHAR( 1 ) DEFAULT '0');";

	public static ArrayList<ShortFilm> getParsed(String jsonString) {
		ArrayList<ShortFilm> lists = new ArrayList<ShortFilm>();
		try {
			XMLTree tree = new XMLTree();
			tree.Load(jsonString, false);
			XMLElement element = tree.RootElement;
			if(element == null) { return null; }
			for(XMLElement childElement : element.children) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}
	
//	{
//	    "CropId": 1,
//	    "CropCode": "Maize",
//	    "CropName": "Maize",
//	    "ActiveYN": true
//	  }

	
	public static ShortFilm getAnObjectFromCursor(Cursor c) {
		ShortFilm instance = null;
		if( c != null) {
			instance = new ShortFilm();
			instance.CropId = c.getInt(c.getColumnIndex(CROP_ID));
			instance.CropCode = c.getString(c.getColumnIndex(CROP_CODE));
			instance.CropName = c.getString(c.getColumnIndex(CROP_NAME));
			instance.ActiveYN = Boolean.getBoolean(c.getString(c.getColumnIndex(ACTIVE_YN)));
		} else {
			LOG.log("getAnObjectFromCursor:", "getAnObjectFromCursor Cursor is null");
		}
		return instance;
	}
	
	public static ShortFilm getAnObject(Databases db, ShortFilm object) {
		ShortFilm instance = null;
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "select * from " + TABLE_NAME + " WHERE " + CROP_ID + " = " + object.CropId + "";
		LOG.log("Query:", "Query:" + query);
		Cursor c = dbRead.rawQuery(query, null);
		if (c.moveToFirst()) {
			instance = getAnObjectFromCursor(c);
		}
		c.close();
		dbRead.close();
		return instance;
	}
	
	public static ShortFilm getAnObject(Databases db,  int CropId) {
		ShortFilm instance = null;
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "select * from " + TABLE_NAME + " WHERE " + CROP_ID + " = " + CropId + "";
		LOG.log("Query:", "Query:" + query);
		Cursor c = dbRead.rawQuery(query, null);
		if (c.moveToFirst()) {
			instance = getAnObjectFromCursor(c);
		}
		c.close();
		dbRead.close();
		return instance;
	}
	
	

	public static ArrayList<ShortFilm> getAllObject(Databases db) {
		ArrayList<ShortFilm> collections = new ArrayList<ShortFilm>();
		SQLiteDatabase dbRead = db.getReadableDatabase();
		String query = "select * from " + TABLE_NAME ;
		LOG.log("Query:", "Query:" + query);
		Cursor c = dbRead.rawQuery(query, null);
		if(c.moveToFirst()){
			do {
				ShortFilm instance = getAnObjectFromCursor(c);
				collections.add(instance);
			} while (c.moveToNext());
		}
		
		c.close();
		dbRead.close();
		return collections;
	}

	public static boolean inseartOperation(Databases db, ShortFilm instance) {
		SQLiteDatabase sql = db.getWritableDatabase();
		String query = "";
		query = "insert into " + TABLE_NAME + " (" 
				+ CROP_ID + ","
				+ CROP_CODE + "," 
				+ CROP_NAME + ","
				+ ACTIVE_YN + " ) values ( " 
				+ "" + instance.CropId + "," 
				+ "'" + instance.CropCode + "'," 
				+ "'" + instance.CropName + "'," 
				+ "'" + instance.ActiveYN + "');";
		LOG.log("Query:", "Query:" + query);
		sql.execSQL(query);
		return true;
	}

	public static boolean updateOperation(Databases db, ShortFilm instance) {

		SQLiteDatabase sql = db.getWritableDatabase();
		String query = "";
		query = "update " + TABLE_NAME + " SET " 
				+ CROP_ID + " = " + instance.CropId + " , " 
				+ CROP_CODE + " = '" + instance.CropCode + "'," 
				+ CROP_NAME + " = '" + instance.CropName + "'," 
				+ ACTIVE_YN + " = '" + instance.ActiveYN + "' WHERE " + CROP_ID + " = " + instance.CropId + ""; 
		LOG.log("Query:", "Query:" + query);
		sql.execSQL(query);
		sql.close();
		return true;
	}

	public static int getNextID(Databases db) {
		try {
			final String MY_QUERY = "SELECT MAX(_id) FROM " + TABLE_NAME;
			SQLiteDatabase dbRead = db.getReadableDatabase();
			Cursor cur = dbRead.rawQuery(MY_QUERY, null);
			cur.moveToFirst();
			int ID = cur.getInt(0) + 1;
			cur.close();
			return ID;
		} catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean deleteTable(Databases db) {
		try {
			SQLiteDatabase sql = db.getWritableDatabase();
			String query = "DELETE from " + TABLE_NAME;
			LOG.log("Query:", "Query:" + query);
			sql.execSQL(query);
			sql.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void inseartOrUpdateOperation(Databases db, ShortFilm object) {
		if(object == null) return;
		ShortFilm fetchedObject = getAnObject(db, object);
		if (fetchedObject == null) {
			inseartOperation(db, object);
		} else {
			updateOperation(db, object);
		}
	}
}
