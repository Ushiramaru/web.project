package com.epam.conference.connection;

import com.epam.conference.connection.exception.ConnectionPoolRuntimeException;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Lock LOCK = new ReentrantLock();
    private static ConnectionPool instance;

    private Queue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> connectionsInUse;

    private Semaphore connectionsSemaphore;
    private final Lock connectionsLock = new ReentrantLock();

    private ConnectionPool() {

    }

    public static ConnectionPool getInstance() {
        ConnectionPool pool = instance;
        if (pool == null) {
            try {
                LOCK.lock();
                pool = instance;
                if (pool == null) {
                    pool = ConnectionPool.build();
                    instance = pool;
                }
            } finally {
                LOCK.unlock();
            }
        }

        return instance;
    }

    private static ConnectionPool build() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String poolSize = resource.getString("db.poolSize");
        int poolSizeValue = Integer.parseInt(poolSize);

        ConnectionPool pool = new ConnectionPool();

        pool.availableConnections = new ArrayDeque<>(poolSizeValue);
        pool.connectionsInUse = new ArrayDeque<>(poolSizeValue);
        pool.connectionsSemaphore = new Semaphore(poolSizeValue, true);

        try {
            for (int i = 0; i < poolSizeValue; i++) {
                Queue<ProxyConnection> availableConnections = pool.availableConnections;
                availableConnections.add(ConnectionFactory.create(pool));
            }
        } catch (SQLException e) {
            throw new ConnectionPoolRuntimeException(e);
        }


        return pool;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        connectionsLock.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnections.offer(proxyConnection);
                connectionsSemaphore.release();
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection;

        try {
            connectionsSemaphore.acquire();
            connectionsLock.lock();
            connection = availableConnections.poll();
            connectionsInUse.offer(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolRuntimeException(e);
        } finally {
            connectionsLock.unlock();
        }

        return connection;
    }

}