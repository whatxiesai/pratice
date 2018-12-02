package com.example.administrator.green;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import javax.xml.namespace.NamespaceContext;

public class DBManager {

    private static DBManager instance;
    private final static String dbName =  "test_db";
    private Context context;
    private DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName);
    };

    private DaoMaster.DevOpenHelper openHelper = null;

    public static DBManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager(context);
                }
            }
        }

        return instance;
    }

    private SQLiteDatabase getWriteableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName);
        }

        return openHelper.getWritableDatabase();
    }


    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName);
        }

        return openHelper.getReadableDatabase();
    }

    public void saveUser(User user) {
        DaoMaster daoMaster= new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.save(user);
    }

    public void saveUsers(List<User> users) {
        DaoMaster daoMaster= new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.saveInTx(users);
    }

    public void delUser(User user) {
        DaoMaster daoMaster= new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.delete(user);
    }

    public void updateUser(User user) {
        DaoMaster daoMaster= new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.update(user);
    }

    public List<User> queryUser() {
        DaoMaster daoMaster= new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
        List<User> list = userQueryBuilder.list();
        return list;
    }

    public List<User> queryUser(int age) {
        DaoMaster daoMaster= new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
        userQueryBuilder.where(UserDao.Properties.Age.eq(age));
        List<User> list = userQueryBuilder.list();
        return list;
    }
}
