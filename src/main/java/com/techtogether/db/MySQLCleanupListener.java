package com.techtogether.db;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

@WebListener
public class MySQLCleanupListener implements ServletContextListener {

@Override
public void contextDestroyed(ServletContextEvent sce) {

try {
System.out.println("Stopping MySQL Cleanup Thread...");
AbandonedConnectionCleanupThread.checkedShutdown();
}
catch(Exception e){
e.printStackTrace();
}

}

@Override
public void contextInitialized(ServletContextEvent sce) {
System.out.println("MySQL Cleanup Listener Started");
}
}
