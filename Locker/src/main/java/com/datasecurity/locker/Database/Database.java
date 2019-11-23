package com.datasecurity.locker.Database;

import com.datasecurity.locker.Utility.LockerUtility;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Database{
    MongoClient mongoClient;
    DB database;
    DBCollection collection;
    DBObject user;
    DBCursor cursor=null;

    public Database(){
        LockerUtility util = new LockerUtility();
        this.mongoClient = new MongoClient(new MongoClientURI(util.getMongoUrl()));
        database = mongoClient.getDB("SDS");
        collection = database.getCollection("users");
    }

    public boolean writeData(String name, String user_name, String password, byte[] key){
        try{
            user = new BasicDBObject()
                .append("name", name)
                .append("user_name", user_name)
                .append("password", password)
                .append("key", key);
            collection.insert(user);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean readData(){return false;}

    public boolean authenticate(String user_name, String password) {
        LockerUtility util = new LockerUtility();
        user = new BasicDBObject("user_name", user_name);
        try {
            cursor = collection.find(user);
            String c_password = (String) cursor.one().get("password");
            if(password.equals(c_password)){
                byte[] key = (byte[]) cursor.one().get("key");
                util.storeKeyHash(key);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

}