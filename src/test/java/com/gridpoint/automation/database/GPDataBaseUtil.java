package com.gridpoint.automation.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GPDataBaseUtil {

    private static Logger logger = Logger.getLogger(GPDataBaseUtil.class);
    private Statement stmt;
    private ResultSet rs;

    public String getIdFromDB(String query, Connection conn) {
        String val = null;
        boolean flg = true;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (flg) {
                    val = rs.getString(1);
                    flg = false;
                } else {
                    val += "," + rs.getString(1);
                }
            }
        } catch (Exception e) {
            logger.info("Query didn't return any value: " + query);
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException sqe) {
                logger.debug("Statement didn't close properly..." + sqe.getMessage());
            }
            stmt = null;
            rs = null;
        }
        return val;
    }

    public List<String> getValueFromDB(String query, Connection conn) {
        List<String> valueList = new ArrayList<String>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                while (rs.next()) {
                    valueList.add(rs.getString(1));
                }
            }
        } catch (Exception e) {
            logger.info("Query didn't return any value" + query);
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException sqe) {
                logger.debug("Statement didn't close properly..." + sqe.getMessage());
            }
            stmt = null;
            rs = null;
        }
        return valueList;
    }

    public void execute(String query, Connection conn) throws SQLException {
        try {
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(query);
            if (i > 0) {
                logger.info(i + " rows effected for query : " + query);
            } else {
                logger.info("Zero rows effected for query : " + query);
            }
        } catch (SQLException e) {
            logger.info("Error occured while executing Query : " + query);
            logger.error(e.getMessage());
            throw new SQLException(e);
        } finally {
            try {
                stmt.close();
            } catch (SQLException sqe) {
                logger.debug("Statement didn't close properly..." + sqe.getMessage());
            }
            stmt = null;
        }
    }

    public List<String> getDeviceIdFromDB(String query, Connection conn) {
        List<String> deviceList = new ArrayList<String>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                deviceList.add(rs.getString(1));
            }
        } catch (Exception e) {
            logger.info("Query didn't return any value" + query);
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException sqe) {
                logger.debug("Statement didn't close properly..." + sqe.getMessage());
            }
            stmt = null;
            rs = null;
        }
        return deviceList;
    }

    public List<String> getChannelIdFromDB(String query, Connection conn) {
        List<String> channelList = new ArrayList<String>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                channelList.add(rs.getString(1));
            }
        } catch (Exception e) {
            logger.info("Query didn't return any value" + query);
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException sqe) {
                logger.debug("Statement didn't close properly..." + sqe.getMessage());
            }
            stmt = null;
            rs = null;
        }
        return channelList;
    }

    public int getRowsCountFromDB(String query, Connection conn) {
        int rowsCount = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            rowsCount = rs.getInt(1);
        } catch (Exception e) {
            logger.info("Query didn't return any value" + query);
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException sqe) {
                logger.debug("Statement didn't close properly..." + sqe.getMessage());
            }
            stmt = null;
            rs = null;
        }
        return rowsCount;
    }
}
