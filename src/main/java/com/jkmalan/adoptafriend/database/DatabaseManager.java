package com.jkmalan.adoptafriend.database;

import com.jkmalan.adoptafriend.listing.Listing;
import com.jkmalan.adoptafriend.user.Account;
import com.jkmalan.adoptafriend.user.User;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseManager {

    private final File file = new File("");
	private final Database database;

	public DatabaseManager() {
		database = new Database(file);
        try {
            database.connect();
            database.getStatement().execute("PRAGMA foreign_keys = ON;");
        } catch (SQLException e) {
            // TODO Handle errors
        }
    }

	public void createTables() throws SQLException {
        String ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS `account` ( "
                + "`uid` INTEGER NOT NULL"
                + "`salt` BYTE NOT NULL"
                + "`hash` CHAR(40) NOT NULL"
                + "PRIMARY KEY (`uid`)"
                + "FOREIGN KEY (`uid`) REFERENCES USER (`uid`) ON DELETE CASCADE"
                + ");";
        String USER_TABLE = "CREATE TABLE IF NOT EXISTS `user` ( "
                + "`uid` INTEGER NOT NULL AUTO_INCREMENT"
                + "`name` CHAR(16) NOT NULL"
                + "`email` CHAR(32) NOT NULL"
                + "`phone` CHAR(10)"
                + "`street` CHAR(48)"
                + "`city` CHAR(16)"
                + "`state` CHAR(2) NOT NULL"
                + "`zip` CHAR(5) NOT NULL"
                + "`desc` CHAR(256)"
                + "`photo` CHAR(128)"
                + "PRIMARY KEY (`uid`)"
                + "UNIQUE (`name`)"
                + ");";
        String LISTING_TABLE = "CREATE TABLE IF NOT EXISTS `listing` ( "
                + "`lid` INTEGER NOT NULL AUTO_INCREMENT"
                + "`owner` INTEGER NOT NULL"
                + "`title` CHAR(16) NOT NULL"
                + "`zip` CHAR(5) NOT NULL"
                + "`type` CHAR(12)"
                + "`sex` CHAR(1)"
                + "`age` INTEGER"
                + "`desc` CHAR(256)"
                + "`photo` CHAR(128)"
                + "PRIMARY KEY (`lid`)"
                + "FOREIGN KEY (`owner`) REFERENCES `user` (`uid`) ON DELETE CASCADE"
                + "UNIQUE (`title`)"
                + ");";
        database.getStatement().execute(USER_TABLE);
		database.getStatement().execute(ACCOUNT_TABLE);
		database.getStatement().execute(LISTING_TABLE);
	}

    public void insertUser(String name, String email, String phone, String street, String city, String state, String zip, String desc, String photo) {
        String USER_INSERT = "INSERT INTO `user` (`name`, `email`, `phone`, `street`, `city`, `state`, `zip`, `desc`, `photo`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(USER_INSERT);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, street);
            ps.setString(5, city);
            ps.setString(6, state);
            ps.setString(7, zip);
            ps.setString(8, desc);
            ps.setString(9, photo);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Handles errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
    }

    public void insertAccount(int uid, byte[] salt, String hash) {
        String ACCOUNT_INSERT = "INSERT INTO `account` (`uid`, `salt`, `hash`) VALUES (?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(ACCOUNT_INSERT);
            ps.setInt(1, uid);
            ps.setBytes(2, salt);
            ps.setString(3, hash);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Handles errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
    }

	public void insertListing(int owner, String title, String zip, String type, String sex, int age, String desc, String photo) {
        String LISTING_INSERT = "INSERT INTO `listing` (`owner`, `title`, `zip`, `type`, `sex`, `age`, `desc`, `photo`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(LISTING_INSERT);
            ps.setInt(1, owner);
            ps.setString(2, title);
            ps.setString(3, zip);
            ps.setString(4, type);
            ps.setString(5, sex);
            ps.setInt(6, age);
            ps.setString(7, desc);
            ps.setString(8, photo);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
    }

    public void updateUser(int uid, String name, String email, String phone, String street, String city, String state, String zip, String desc, String photo) {
        String USER_UPDATE = "UPDATE `user` SET `name` = ?, `email` = ?, `phone` = ?, `street` = ?, "
                + "`city` = ?, `state` = ?, `zip` = ?, `desc` = ?, `photo` = ? WHERE `uid` = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(USER_UPDATE);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, street);
            ps.setString(5, city);
            ps.setString(6, state);
            ps.setString(7, zip);
            ps.setString(8, desc);
            ps.setString(9, photo);
            ps.setInt(10, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
    }

    public void updateListing(int lid, String title, String zip, String type, String sex, int age, String desc, String photo) {
        String LISTING_UPDATE = "UPDATE `listing` SET `title` = ?, `zip` = ?, `type` = ?, "
                + "`sex` = ?, `age` = ?, `desc` = ?, `photo` = ? WHERE `lid` = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(LISTING_UPDATE);
            ps.setString(1, title);
            ps.setString(2, zip);
            ps.setString(3, type);
            ps.setString(4, sex);
            ps.setInt(5, age);
            ps.setString(6, desc);
            ps.setString(7, photo);
            ps.setInt(8, lid);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
    }

    public void deleteUser(int uid) {
        String USER_DELETE = "DELETE FROM `user` WHERE `uid` = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(USER_DELETE);
            ps.setInt(1, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
    }

    public void deleteListing(int lid) {
        String LISTING_DELETE = "DELETE FROM `listing` WHERE `uid` = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(LISTING_DELETE);
            ps.setInt(1, lid);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
    }

    public Optional<User> selectUser(String name) {
        String USER_QUERY = "SELECT * FROM `user` WHERE `name` = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(USER_QUERY);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("uid"));

                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setStreet(rs.getString("street"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setDesc(rs.getString("desc"));

                String path = rs.getString("photo");
                File file = new File(path);
                user.setPhoto(file);

                return Optional.of(user);
            }
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
        return Optional.empty();
    }

    public Optional<User> selectUser(int uid) {
        String USER_QUERY = "SELECT * FROM `user` WHERE `uid` = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(USER_QUERY);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(uid);

                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setStreet(rs.getString("street"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setDesc(rs.getString("desc"));

                String path = rs.getString("photo");
                File file = new File(path);
                user.setPhoto(file);

                return Optional.of(user);
            }
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
        return Optional.empty();
    }

    public Optional<Account> selectAccount(int uid) {
        String ACCOUNT_QUERY = "SELECT * FROM `account` WHERE `uid` = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(ACCOUNT_QUERY);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getInt("uid"), rs.getBytes("salt"), rs.getString("hash"));
                return Optional.of(account);
            }
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
        return Optional.empty();
    }

    public Optional<Listing> selectListing(String title) {
        String LISTING_QUERY = "SELECT * FROM `listing` WHERE `title` = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(LISTING_QUERY);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Listing listing = new Listing(rs.getInt("lid"), rs.getInt("owner"));

                listing.setTitle(rs.getString("title"));
                listing.setZip(rs.getString("zip"));
                listing.setType("type");
                listing.setSex("sex");
                listing.setAge("age");
                listing.setDesc("desc");

                String path = rs.getString("photo");
                File file = new File(path);
                listing.setPhoto(file);

                return Optional.of(listing);
            }
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
        return Optional.empty();
    }

    public Optional<Listing> selectListing(int lid) {
        String LISTING_QUERY = "SELECT * FROM `listing` WHERE `lid` = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(LISTING_QUERY);
            ps.setInt(1, lid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer owner = rs.getInt("owner");
                Listing listing = new Listing(lid, owner);

                listing.setTitle(rs.getString("title"));
                listing.setZip(rs.getString("zip"));
                listing.setType("type");
                listing.setSex("sex");
                listing.setAge("age");
                listing.setDesc("desc");

                String path = rs.getString("photo");
                File file = new File(path);
                listing.setPhoto(file);

                return Optional.of(listing);
            }
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
        return Optional.empty();
    }

	public List<Listing> selectListings(String title, String zip, String type, String sex, Integer age) {
        List<Listing> listings = new ArrayList<>();
        String LISTING_QUERY = "SELECT lid FROM `listing` "
                + "WHERE `title` LIKE ? "
                + "AND `zip` LIKE ? "
                + "AND `type` LIKE ? "
                + "AND `sex` LIKE ? "
                + "AND `age` = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(LISTING_QUERY);
            ps.setString(1, title);
            ps.setString(2, zip);
            ps.setString(3, type);
            ps.setString(4, sex);
            ps.setInt(5, age);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer lid = rs.getInt("lid");
                Integer owner = rs.getInt("owner");
                Listing listing = new Listing(lid, owner);

                listing.setTitle(rs.getString("title"));
                listing.setZip(rs.getString("zip"));
                listing.setType("type");
                listing.setSex("sex");
                listing.setAge("age");
                listing.setDesc("desc");

                String path = rs.getString("photo");
                File file = new File(path);
                listing.setPhoto(file);

                listings.add(listing);
            }
        } catch (SQLException e) {
            // TODO Handle errors
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Handles errors
            }
        }
        return listings;
	}

}
