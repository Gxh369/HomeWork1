package ccom.jy.gxh.day3homeworka.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.jy.gxh.day3homeworka.beans.DatasBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DATAS_BEAN".
*/
public class DatasBeanDao extends AbstractDao<DatasBean, Long> {

    public static final String TABLENAME = "DATAS_BEAN";

    /**
     * Properties of entity DatasBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Title = new Property(0, String.class, "title", false, "TITLE");
        public final static Property Thumbnail = new Property(1, String.class, "thumbnail", false, "THUMBNAIL");
        public final static Property Author = new Property(2, String.class, "author", false, "AUTHOR");
        public final static Property Flag = new Property(3, int.class, "flag", false, "FLAG");
        public final static Property Id = new Property(4, long.class, "id", true, "_id");
    }


    public DatasBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DatasBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DATAS_BEAN\" (" + //
                "\"TITLE\" TEXT," + // 0: title
                "\"THUMBNAIL\" TEXT," + // 1: thumbnail
                "\"AUTHOR\" TEXT," + // 2: author
                "\"FLAG\" INTEGER NOT NULL ," + // 3: flag
                "\"_id\" INTEGER PRIMARY KEY NOT NULL );"); // 4: id
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DATAS_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DatasBean entity) {
        stmt.clearBindings();
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(1, title);
        }
 
        String thumbnail = entity.getThumbnail();
        if (thumbnail != null) {
            stmt.bindString(2, thumbnail);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(3, author);
        }
        stmt.bindLong(4, entity.getFlag());
        stmt.bindLong(5, entity.getId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DatasBean entity) {
        stmt.clearBindings();
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(1, title);
        }
 
        String thumbnail = entity.getThumbnail();
        if (thumbnail != null) {
            stmt.bindString(2, thumbnail);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(3, author);
        }
        stmt.bindLong(4, entity.getFlag());
        stmt.bindLong(5, entity.getId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 4);
    }    

    @Override
    public DatasBean readEntity(Cursor cursor, int offset) {
        DatasBean entity = new DatasBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // title
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // thumbnail
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // author
            cursor.getInt(offset + 3), // flag
            cursor.getLong(offset + 4) // id
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DatasBean entity, int offset) {
        entity.setTitle(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setThumbnail(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAuthor(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFlag(cursor.getInt(offset + 3));
        entity.setId(cursor.getLong(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DatasBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DatasBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DatasBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
