package com.soarlog.app.data;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.soarlog.app.models.Flight;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FlightDao_Impl implements FlightDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Flight> __insertionAdapterOfFlight;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Flight> __deletionAdapterOfFlight;

  public FlightDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFlight = new EntityInsertionAdapter<Flight>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `flight` (`id`,`registration`,`p2`,`notes`,`gliderType`,`takeoff`,`landing`,`launchType`,`duration`,`date`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Flight value) {
        stmt.bindLong(1, value.getId());
        if (value.getRegistration() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getRegistration());
        }
        if (value.getP2() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getP2());
        }
        if (value.getNotes() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNotes());
        }
        if (value.getGliderType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getGliderType());
        }
        if (value.getTakeoff() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTakeoff());
        }
        if (value.getLanding() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getLanding());
        }
        if (value.getLaunchType() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getLaunchType());
        }
        stmt.bindLong(9, value.getDuration());
        final Long _tmp = __converters.dateToTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindLong(10, _tmp);
        }
      }
    };
    this.__deletionAdapterOfFlight = new EntityDeletionOrUpdateAdapter<Flight>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `flight` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Flight value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public Object insert(final Flight flight, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFlight.insert(flight);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final Flight flight, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFlight.handle(flight);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<Flight>> getAllFlights() {
    final String _sql = "SELECT * FROM flight ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"flight"}, new Callable<List<Flight>>() {
      @Override
      public List<Flight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRegistration = CursorUtil.getColumnIndexOrThrow(_cursor, "registration");
          final int _cursorIndexOfP2 = CursorUtil.getColumnIndexOrThrow(_cursor, "p2");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfGliderType = CursorUtil.getColumnIndexOrThrow(_cursor, "gliderType");
          final int _cursorIndexOfTakeoff = CursorUtil.getColumnIndexOrThrow(_cursor, "takeoff");
          final int _cursorIndexOfLanding = CursorUtil.getColumnIndexOrThrow(_cursor, "landing");
          final int _cursorIndexOfLaunchType = CursorUtil.getColumnIndexOrThrow(_cursor, "launchType");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Flight> _result = new ArrayList<Flight>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Flight _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpRegistration;
            if (_cursor.isNull(_cursorIndexOfRegistration)) {
              _tmpRegistration = null;
            } else {
              _tmpRegistration = _cursor.getString(_cursorIndexOfRegistration);
            }
            final String _tmpP2;
            if (_cursor.isNull(_cursorIndexOfP2)) {
              _tmpP2 = null;
            } else {
              _tmpP2 = _cursor.getString(_cursorIndexOfP2);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpGliderType;
            if (_cursor.isNull(_cursorIndexOfGliderType)) {
              _tmpGliderType = null;
            } else {
              _tmpGliderType = _cursor.getString(_cursorIndexOfGliderType);
            }
            final String _tmpTakeoff;
            if (_cursor.isNull(_cursorIndexOfTakeoff)) {
              _tmpTakeoff = null;
            } else {
              _tmpTakeoff = _cursor.getString(_cursorIndexOfTakeoff);
            }
            final String _tmpLanding;
            if (_cursor.isNull(_cursorIndexOfLanding)) {
              _tmpLanding = null;
            } else {
              _tmpLanding = _cursor.getString(_cursorIndexOfLanding);
            }
            final String _tmpLaunchType;
            if (_cursor.isNull(_cursorIndexOfLaunchType)) {
              _tmpLaunchType = null;
            } else {
              _tmpLaunchType = _cursor.getString(_cursorIndexOfLaunchType);
            }
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = __converters.fromTimestamp(_tmp);
            _item = new Flight(_tmpId,_tmpRegistration,_tmpP2,_tmpNotes,_tmpGliderType,_tmpTakeoff,_tmpLanding,_tmpLaunchType,_tmpDuration,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
