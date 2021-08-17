package sg.edu.rp.c346.id20025835.petsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "Pets.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_PET = "Pet";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_TYPE = "type";
	private static final String COLUMN_DESCRIPTION = "description";
	private static final String COLUMN_YEAR = "year";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createSongTableSql = "CREATE TABLE " + TABLE_PET + "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_NAME + " TEXT, "
				+ COLUMN_TYPE + " TEXT, "
				+ COLUMN_DESCRIPTION + " TEXT, "
				+ COLUMN_YEAR + " INTEGER)";
		db.execSQL(createSongTableSql);
		Log.i("info", createSongTableSql + "\ncreated tables");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PET);
		onCreate(db);
	}

	public long insertPet(String name, String type, String description, int year) {
		// Get an instance of the database for writing
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, name);
		values.put(COLUMN_TYPE, type);
		values.put(COLUMN_DESCRIPTION, description);
		values.put(COLUMN_YEAR, year);
		long result = db.insert(TABLE_PET, null, values);
		// Close the database connection
		db.close();
		Log.d("SQL Insert", "" + result);
		return result;
	}

	public ArrayList<Pet> getAllPets() {
		ArrayList<Pet> petlist = new ArrayList<Pet>();
		String selectQuery = "SELECT " + COLUMN_ID + ","
				+ COLUMN_NAME + "," + COLUMN_TYPE + ","
				+ COLUMN_DESCRIPTION + "," + COLUMN_YEAR
				+ " FROM " + TABLE_PET;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String name = cursor.getString(1);
				String type = cursor.getString(2);
				String description = cursor.getString(3);
				int year = cursor.getInt(4);

				Pet newPet = new Pet(id, name, type, description, year);
				petlist.add(newPet);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return petlist;
	}

	public int updatePet(Pet data) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, data.getName());
		values.put(COLUMN_TYPE, data.getType());
		values.put(COLUMN_DESCRIPTION, data.getDescription());
		values.put(COLUMN_YEAR, data.getYearReleased());
		String condition = COLUMN_ID + "= ?";
		String[] args = {String.valueOf(data.getId())};
		int result = db.update(TABLE_PET, values, condition, args);
		db.close();
		return result;
	}


	public int deletePet(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String condition = COLUMN_ID + "= ?";
		String[] args = {String.valueOf(id)};
		int result = db.delete(TABLE_PET, condition, args);
		db.close();
		return result;
	}

	public ArrayList<Pet> getAllSongsByType(String typeFilter) {
		ArrayList<Pet> petlist = new ArrayList<Pet>();

		SQLiteDatabase db = this.getReadableDatabase();
		String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_TYPE, COLUMN_DESCRIPTION, COLUMN_YEAR};
		String condition = COLUMN_TYPE + "= ?";
		String[] args = {String.valueOf(typeFilter)};

		Cursor cursor;
		cursor = db.query(TABLE_PET, columns, condition, args, null, null, null, null);

		// Loop through all rows and add to ArrayList
		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String name = cursor.getString(1);
				String type = cursor.getString(2);
				String description = cursor.getString(3);
				int year = cursor.getInt(4);

				Pet newPet = new Pet(id, name, type, description, year);
				petlist.add(newPet);
			} while (cursor.moveToNext());
		}
		// Close connection
		cursor.close();
		db.close();
		return petlist;
	}

	public ArrayList<String> getYears() {
		ArrayList<String> codes = new ArrayList<String>();

		SQLiteDatabase db = this.getReadableDatabase();
		String[] columns = {COLUMN_YEAR};

		Cursor cursor;
		cursor = db.query(true, TABLE_PET, columns, null, null, null, null, null, null);
		// Loop through all rows and add to ArrayList
		if (cursor.moveToFirst()) {
			do {
				codes.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		// Close connection
		cursor.close();
		db.close();
		return codes;
	}

	public ArrayList<Pet> getAllSongsByYear(int yearFilter) {
		ArrayList<Pet> petlist = new ArrayList<Pet>();

		SQLiteDatabase db = this.getReadableDatabase();
		String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_TYPE, COLUMN_DESCRIPTION, COLUMN_YEAR};
		String condition = COLUMN_YEAR + "= ?";
		String[] args = {String.valueOf(yearFilter)};

		Cursor cursor;
		cursor = db.query(TABLE_PET, columns, condition, args, null, null, null, null);

		// Loop through all rows and add to ArrayList
		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String name = cursor.getString(1);
				String type = cursor.getString(2);
				String description = cursor.getString(3);
				int year = cursor.getInt(4);

				Pet newPet = new Pet(id, name, type, description, year);
				petlist.add(newPet);
			} while (cursor.moveToNext());
		}
		// Close connection
		cursor.close();
		db.close();
		return petlist;
	}
}
